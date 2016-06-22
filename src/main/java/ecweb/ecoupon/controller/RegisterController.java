package ecweb.ecoupon.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ecweb.ecoupon.util.PassWDGenerate;
import ecweb.ecoupon.util.ShortMessage;

@Controller
@RequestMapping("/ecard_register")
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(eccontroller.class);
    
    @Autowired
    private ecstateDao dao;
    @Autowired
    private SendSMS sms;
    @Autowired
    private HttpSession httpSession;
    
	@RequestMapping(method=GET)
	public String HomeController(Map<String, Object> model){
		//input with parameter /ec_input？eccode="18 digital e-coupon code"
		RegisterForm myform=new RegisterForm();
		model.put("registerForm", myform);
		return "ecard_register";
	}
	
	@RequestMapping(method=POST, params = { "sendpwtocell" })
	public ModelAndView processSendSMS(@ModelAttribute("registerForm") RegisterForm myform,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		String passwd=PassWDGenerate.passwdGenerate();
		ShortMessage sm = new ShortMessage();
		sm.setMessage(passwd);
		logger.debug("set SMS, SM="+sm.getMessage()+":"+sm.getCreatedTime().toString());
		httpSession.setAttribute("ShortMessage", sm);
		
		//send SMS
		String ms="喜购云店测试电子劵6位数字手机验证码："+passwd;
		sms.sendSMS(myform.getCell(), ms);
		
		mv.addObject("registerForm", myform);
		mv.addObject("ShortMessage", sm);

		String message="请在3分钟内输入通过手机短信收到的6位数字验证码；同时此验证码将成为转让或购物密码，请妥善保存。";
		mv.addObject("message",message);
		//return "order_input";
		mv.setViewName("ecard_register");
		return mv;
	}
	
	@RequestMapping(method=POST, params = { "verifyPWcell" })
	public ModelAndView processSavePasswd(Map<String, Object> model,@Valid RegisterForm myform,Errors errors) {
		ModelAndView mv = new ModelAndView();
		String message=null;
		if(errors.hasErrors())
		 {
			logger.debug("Errors: "+errors.toString());
			mv.addObject("registerForm", myform);
			mv.setViewName("ecard_register");
		 	 return mv;
		 }
		
		//verify passwd and time
		ShortMessage sm =(ShortMessage) httpSession.getAttribute("ShortMessage");

		String passwd=myform.getNewpasswd();
		if((sm==null)||(!sm.getMessage().equals(passwd))){
			message="输入的6位数字验证码不正确，请重试！";
			mv.addObject("message",message);
			mv.setViewName("ecard_register");
			mv.addObject("registerForm", myform);
			return mv;
		}
		logger.debug("SM="+sm.getMessage()+":"+sm.getCreatedTime().toString());
			
		if(!CheckingTime(sm,3)){
			message="输入的6位数字验证码超时，请重试！";
			mv.addObject("message",message);
			mv.setViewName("ecard_register");
			mv.addObject("registerForm", myform);
			return mv;
		}
		
		Ecoupon ec=dao.getECbyECCode(myform.getEccode());
		if (ec==null){
			message="输入的购物券不存在。请重试！";
			mv.addObject("message",message);
			mv.setViewName("ecard_register");
			mv.addObject("registerForm", myform);
			return mv;
		}
	
		if (ec.getState()!=0){
			message="输入的购物券已经使用，不能转让！";
			mv.addObject("message",message);
			mv.setViewName("ecard_register");
			mv.addObject("registerForm", myform);
			return mv;
		}
		
		String cell=ec.getCell();
		if(cell!=null&&cell.length()>0){ //at initail state, cell is empty, so as passwd
			logger.debug("cell="+ec.getCell());
			if(!dao.getPasswdVerify(myform.getEccode(), myform.getOldpasswd())){
			message="输入的购物券和密码不匹配。请重试！";
			mv.addObject("message",message);
			mv.setViewName("ecard_register");
			mv.addObject("registerForm", myform);
			return mv;
			}
		}
		//save new passwd and cell number
		int i=dao.updatePasswd(myform.getEccode(), myform.getCell(),
				myform.getNewpasswd(),myform.getName(),myform.getWechat());
		if(i!=1){
			mv.setViewName("error");
			return mv;
		}
		
		message="手机号及密码更改成功。请妥善保存相关信息。";
		mv.addObject("message",message);
		//return "order_input";
		mv.setViewName("orderconfirmrf");
		return mv;
	}
	
	//When user forgot their password, they can just input ec_code, cell number, name, and wechat name.
	//If all of those infor are match, save new verify code as new password.
	@RequestMapping(method=POST, params = { "resetPW" })
	public ModelAndView processResetPasswd(Map<String, Object> model,@Valid RegisterForm myform,Errors errors) {
		ModelAndView mv = new ModelAndView();
		String message=null;
		if(errors.hasErrors())
		 {
			logger.debug("Errors: "+errors.toString());
			mv.addObject("registerForm", myform);
			mv.setViewName("ecard_register");
		 	 return mv;
		 }
		
		//verify passwd and time
		ShortMessage sm =(ShortMessage) httpSession.getAttribute("ShortMessage");

		String passwd=myform.getNewpasswd();
		if((sm==null)||(!sm.getMessage().equals(passwd))){
			message="输入的6位数字验证码不正确，请重试！";
			mv.addObject("message",message);
			mv.setViewName("ecard_register");
			mv.addObject("registerForm", myform);
			return mv;
		}
		logger.debug("SM="+sm.getMessage()+":"+sm.getCreatedTime().toString());
			
		if(!CheckingTime(sm,3)){
			message="输入的6位数字验证码超时，请重试！";
			mv.addObject("message",message);
			mv.setViewName("ecard_register");
			mv.addObject("registerForm", myform);
			return mv;
		}
		
		Ecoupon ec=dao.getECbyECCode(myform.getEccode());
		if (ec==null){
			message="输入的购物券不存在。请重试！";
			mv.addObject("message",message);
			mv.setViewName("ecard_register");
			mv.addObject("registerForm", myform);
			return mv;
		}
//		if((ec.getPasswd()!=null)&&(!ec.getPasswd().equals(myform.getOldpasswd()))){
//			message="输入的购物券和密码不匹配。请重试！";
//			mv.addObject("message",message);
//			mv.setViewName("ecard_register");
//			mv.addObject("registerForm", myform);
//			return mv;
//		}
		if (ec.getState()!=0){
			message="输入的购物券已经使用，不能转让！";
			mv.addObject("message",message);
			mv.setViewName("ecard_register");
			mv.addObject("registerForm", myform);
			return mv;
		}
		logger.debug("EC:"+ec.getCell()+":"+ec.getName()+":"+ec.getWechat()		);
		logger.debug("FM:"+myform.getCell()+":"+myform.getName()+":"+myform.getWechat()		);
		if(ec.getCell().equals(myform.getCell())&&ec.getName().equals(myform.getName())){
			if(ec.getWechat()!=null&&ec.getWechat().equals(myform.getWechat())){
				//save new passwd and cell number
				int i=dao.updatePasswd(myform.getEccode(), myform.getCell(),
						myform.getNewpasswd(),myform.getName(),myform.getWechat());
				if(i!=1){
					mv.setViewName("error");
					return mv;
				}
				message="密码重置成功。请妥善保存相关信息。";
				mv.addObject("message",message);
				//return "order_input";
				mv.setViewName("orderconfirmrf");
				return mv;
			}
		}
		
		
		message="相关信息不匹配。请仔细检查输入信息。";
		mv.addObject("message",message);
		mv.setViewName("ecard_register");
		mv.addObject("registerForm", myform);
		return mv;
	}
	
	//compare time is within minutes
	public boolean CheckingTime(ShortMessage sm, int minutes){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -minutes);
		long now=cal.getTimeInMillis();
		
		long old=sm.getCreatedTime().getTime();
		boolean b=now<old;
		return b;
	}
}
