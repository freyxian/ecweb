package ecweb.ecoupon.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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


@Controller
@RequestMapping("/ecard_verify")
public class ECVerifyController {
    private static final Logger logger = LoggerFactory.getLogger(eccontroller.class);
    
    @Autowired
    private ecstateDao dao;
    @Autowired
    private SendSMS sms;
    
	@RequestMapping(method=GET)
	public String VerifyGetController(Map<String, Object> model){
		//input with parameter /ec_input？eccode="18 digital e-coupon code"
		VerifyForm myform=new VerifyForm();
		model.put("verifyForm", myform);
		return "ecard_verify";
	}
	

	@RequestMapping(method=POST)
	public ModelAndView processECVerify(Map<String, Object> model,@Valid VerifyForm myform,Errors errors,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String message=null;
		
		if(errors.hasErrors())
		 {
			logger.debug("Errors: "+errors.toString());
			mv.addObject("verifyForm", myform);
			mv.setViewName("ecard_verify");
		 	return mv;
		 }
		
		Ecoupon ec=dao.getECbyECCode(myform.getEccode());
		if (ec==null){
			message="输入的购物券不存在。请重试！";
			mv.addObject("message",message);
			mv.setViewName("ecard_verify");
			mv.addObject("verifyForm", myform);
			return mv;
		}
		
		//checking cell number
		if(!ec.getCell().equals(myform.getCell())){
			message="输入的购物券和手机号不匹配。请重试！";
			mv.addObject("message",message);
			mv.setViewName("ecard_verify");
			mv.addObject("verifyForm", myform);
			return mv;
		}
		
		if(!dao.getPasswdVerify(myform.getEccode(), myform.getPasswd())){
			message="输入的购物券和密码不匹配。请重试！";
			mv.addObject("message",message);
			mv.setViewName("ecard_verify");
			mv.addObject("verifyForm", myform);
			return mv;
		}

		if(ec.getState()==0){ //购物券还没有使用

				if(dao.getecstate()==1){
					//商品兑换期
					GoodsForm gf=new GoodsForm();
					gf.setEccode(myform.getEccode());
					gf.setCell(myform.getCell());
					gf.setGoodsDesc(ec.getGoods_des());
					gf.setGoodsUrl(ec.getGoods_url());
					gf.setNumber(ec.getNumber());
					mv.addObject("goodsForm", gf);
					mv.setViewName("goodsorder");
				}if(dao.getecstate()==2){
					//销劵退款期
					CashbackForm cf=new CashbackForm();
					cf.setEccode(ec.getEc_code());
					cf.setCell(ec.getCell());
					cf.setGoodsDesc(ec.getGoods_des());
					cf.setGoodsUrl(ec.getGoods_url());
					cf.setNumber(ec.getNumber());
					double cb=dao.getecRate()*ec.getNumber();
					cf.setCashback(cb);
					mv.addObject("cashForm", cf);
					mv.setViewName("cashback");
				}


		}else if(ec.getState()==1){//已经使用，仅显示状态
			message="此购物券相关订单尚在处理中，请等待24小时后查询！";
			mv.addObject("message",message);
			mv.setViewName("orderconfirmrf");
		}else if(ec.getState()==2){
			if(ec.getProcessNumber().charAt(0)=='D'){
				message="此购物券相关订单已经处理完毕，快递单为："+dao.getDelieverNum(ec.getEc_code())
				+"。请向快递公司查询，或联系喜购云客服。";
			}else if(ec.getProcessNumber().charAt(0)=='C'){
				message="此购物券已完成退款，退款账号为："+dao.getCashback(ec.getEc_code()).getAccount()
				+"。请查询你的账号，或联系喜购云客服。";
			}
			
			mv.addObject("message",message);
			mv.setViewName("orderconfirmrf");
		}

		return mv;
	}

}
