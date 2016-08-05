package ecweb.ecoupon.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ec_cashback")
public class CashbackController {
    private static final Logger logger = LoggerFactory.getLogger(eccontroller.class);
    
    @Autowired
    private ecstateDao dao;
    
	@RequestMapping(method=POST, params = { "cashback" })
	public ModelAndView processCashback(Map<String, Object> model,@Valid CashbackForm myform,Errors errors,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if(errors.hasErrors())
		 {
			logger.debug("Errors: "+errors.toString());
			mv.addObject("cashbackForm", myform);
			mv.setViewName("ec_cashback");
		 	return mv;
		 }
		logger.debug("received AccountType="+myform.getAccountType()+" Account="+myform.getAccount());
		
		//verify info again will be better
		StringBuffer err=new StringBuffer();
		if (myform.getAccountType().trim()==null) err.append("必须选择收款人账号：微信，支付宝， 或银行账号。");
		if  ((myform.getAccount()==null||myform.getAccount().length()==0))
			err.append("若选择银行账号，须同时输入开户行信息。");
		if (!myform.isPolice()) err.append("必须选择同意退款政策才能退款。请点击链接‘我同意退款政策’查看完整的退款政策。");
		if(err.length()>1){
			
			mv.addObject("message",err.toString());
			mv.addObject("cashbackForm", myform);
			mv.setViewName("ec_cashback");
		 	return mv;
		}
		
		//修改电子劵状态，写入购物信息
		String PN=dao.saveCashbckOrder(myform);

		mv.addObject("message",PN);
		mv.setViewName("cashbackconfirm");
		//return "order_input";
		return mv;
	}
}
