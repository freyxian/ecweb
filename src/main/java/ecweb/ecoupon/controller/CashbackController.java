package ecweb.ecoupon.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Map;

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
	public ModelAndView processCashback(Map<String, Object> model,@Valid CashbackForm myform,Errors errors) {
		if(errors.hasErrors())
		 {
			logger.debug("Errors: "+errors.toString());
			model.put("cashForm",myform);
		 	 return new ModelAndView("cashback");
		 }
		logger.debug("received myform is: "+myform.toString());
		
		//verify info again will be better

		
		//修改电子劵状态，写入购物信息
		String PN=dao.saveCashbckOrder(myform);
		String message="退款要求已经发出，处理单编号："+PN+"。请允许我们在24小时内完成处理。";
		ModelAndView mv=new ModelAndView();
		mv.addObject("message",message);
		mv.setViewName("orderconfirmrf");
		//return "order_input";
		return mv;
	}
}
