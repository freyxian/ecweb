package com.cigouweb.controller;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import CigouDAO.CDAO.OrderFetch;
import CigouDAO.CDAO.WholeOrder;
import CigouDAO.cigoudb.HibernateUtil;
import CigouDAO.cigoudb.WhOrderItems;
import CigouDAO.cigoudb.WhOrderItemsHome;
import CigouDAO.cigoudb.WhOrderItemsId;

@Controller
@RequestMapping("/order_input")
public class order_input {

	@RequestMapping(method=GET)
	public String HomeController(Map<String, Object> model){
		//initial empty form
		OrderInputForm myform=new OrderInputForm();
		model.put("oiform", myform);
		return "order_input";
	}
	
	//value="OrderSearch",method=POST
	//@RequestMapping(method=POST)
	@RequestMapping(method=POST, params = { "OrderSearch" })
	public String processOrderSearch(@ModelAttribute("oiform") OrderInputForm myform) {
		OrderFetch ordf=new OrderFetch();
		WholeOrder wo =ordf.fetchWholeOrder(myform.getOrderId());
		
		//after search, flash web page
		myform.BindOrder(wo);
		return "order_input";
	}
	
	@RequestMapping(method=POST, params = { "NewOrder" })
	public ModelAndView processNewOrder(@ModelAttribute("oiform") OrderInputForm myform) {
		WholeOrder wo=myform.Form2Order();
		OrderFetch ordf=new OrderFetch();
		ordf.saveWholeOrder(wo);
		String message = "NewOrder get order id:"+myform.getOrderId()+"saved";
		return new ModelAndView("order_input", "message", message);
	}

	@RequestMapping(method=POST, params = { "ModifyOrder" })
	public ModelAndView processModifyOrder(@ModelAttribute("oiform") OrderInputForm myform) {

		
		String message = "NewOrder get order id:"+myform.getOrderId();
		return new ModelAndView("order_input", "message", message);
	}

	@RequestMapping(method=POST, params = { "DeleteOrder" })
	public ModelAndView processDeleteOrder(@ModelAttribute("oiform") OrderInputForm myform) {

		
		String message = "NewOrder get order id:"+myform.getOrderId();
		return new ModelAndView("order_input", "message", message);
	}

}
