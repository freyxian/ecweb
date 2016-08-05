package ecweb.ecoupon.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/ec_input")
public class eccontroller {
    private static final Logger logger = LoggerFactory.getLogger(eccontroller.class);
    
    @Autowired
    private ecstateDao dao;
    
//	@RequestMapping(method=GET)
	public String HomeController(Map<String, Object> model,HttpServletRequest request){
		//input with parameter /ec_input？eccode="18 digital e-coupon code"
		String eccode=request.getParameter("eccode");
		logger.debug("received eccode is: "+eccode);
		if(eccode==null || eccode.length()!=18){
			return "error";
		}
		
		Ecoupon ec;
		try{
		if(dao.getecstate()!=1&&dao.getecstate()!=2){
			return "error";
		}
		ec=dao.getECbyECCode(eccode);
		logger.debug(ec.toString());
		} catch (DataAccessException e){
			return "error";
		}
		GoodsForm gf=BindingGoodsForm(ec);
		model.put("goodsForm",gf);
		
		return "goodsorder";
	}
	
	public GoodsForm BindingGoodsForm(Ecoupon ec){
		GoodsForm form= new GoodsForm();
		form.setEccode(ec.getEc_code());
		form.setGoodsDesc(ec.getGoods_des());
		form.setGoodsUrl(ec.getGoods_url());
		return form;
	}
	
	@RequestMapping(method=POST, params = { "OrderInput" })
	public ModelAndView processOrdernput(Map<String, Object> model,@Valid GoodsForm myform,Errors errors) {
		if(errors.hasErrors())
		 {
			logger.debug("Errors: "+errors.toString());
			model.put("goodsForm",myform);
		 	 return new ModelAndView("goodsorder");
		 }
		logger.debug("received myform is: "+myform.toString());
		
		//verify info again will be better
		
		//修改电子劵状态，写入购物信息
		String PN=dao.saveOrder(myform);
		String message="购物订单已经发出，处理单编号："+PN+"。请允许我们在2工作日内完成处理。";
		ModelAndView mv=new ModelAndView();
		mv.addObject("message",message);
		mv.setViewName("orderconfirmrf");
		//return "order_input";
		return mv;
	}
}
