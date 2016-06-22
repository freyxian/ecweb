package com.cigouyun.ebiz.edi.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cigouyun.ebiz.edi.zhuozhi.beans.*;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSample {

	public static void main(String[] args) {
		JsonSample obj = new JsonSample();
		obj.run();
	}

	private void run() {
		ObjectMapper mapper = new ObjectMapper();

		Order order = createDummyOrder();
		try {
			// Convert object to JSON string and save into a file directly
			mapper.writeValue(new File("tmp\\order.json"), order);

			// Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(order);
			System.out.println(jsonInString);

			// Convert object to JSON string and pretty print
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(order);
			System.out.println(jsonInString);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void showOrder( Order order ) {
		
		
		System.out.println("---------Order-------------");

		System.out.println ( "cbepcomcode="+order.getCbepcomcode());
		System.out.println ( "customerId="+order.getCustomerId());
		System.out.println ( "electriccode="+order.getElectriccode());
		System.out.println ( "notes="+order.getNotes());
		System.out.println ( "OrderDate="+order.getOrderDate());
		System.out.println ( "OrderId="+order.getOrderId());
		System.out.println ( "OrderType="+order.getOrderType());
		System.out.println ( "PackingMaterial="+order.getPackingMaterial());
		System.out.println ( "Tpl="+order.getTpl());
		System.out.println ( "WarehouseId="+order.getWarehouseId());		
		
		showRecipient( order.getRecipient() );
		
		for (Good g: order.getGoodList()){
			showGood(g);
		}
		System.out.println("---------Order--end---------");

		
		// order.getGoodList(goodList);
		// order.getRecipient(recipient);
		
		return;
	}
	
	public static void showGood( Good g2 ) {
		
		System.out.println("=======================");

		System.out.println("ID="+g2.getGoodId());
		System.out.println("amount="+g2.getAmount());
		System.out.println("price="+g2.getPrice());
		System.out.println("=======================");

	}
	
	public static void showRecipient(Recipient r) {
		
		System.out.println("addr="+ r.getAddress());
		System.out.println("city="+ r.getCity());
		System.out.println("country="+ r.getCountry());
		System.out.println("district="+ r.getDistrict());
		System.out.println("mobile="+ r.getMobilePhone());
		System.out.println("name="+ r.getName());
		System.out.println("phone="+ r.getPhone());
		System.out.println("postcode="+ r.getPostCode());
		System.out.println("prov="+ r.getProvince());
		System.out.println("receiNo="+ r.getReceiveNo());
		System.out.println("receiType="+ r.getReceiveType());
		
		
	}
	
	public static Order createDummyOrder() {
		
		Order order = new Order();
		Recipient recipient = new Recipient();
		
		List<Good> goodList = new ArrayList<Good>();
		
		Good g1 = new Good();
		
		g1.setAmount(100);
		g1.setPrice(10.00);
		g1.setGoodId("goodId001");
		
		Good g2 = new Good();
		
		g2.setAmount(130);
		g2.setPrice(14.00);
		g2.setGoodId("goodId002");
		
		goodList.add(g1);
		goodList.add(g2);
		
		recipient.setAddress("address");
		recipient.setCity("city");
		recipient.setCountry("country");
		recipient.setDistrict("district");
		recipient.setMobilePhone("mobilePhone");
		recipient.setName("name");
		recipient.setPhone("phone");
		recipient.setPostCode("postCode");
		recipient.setProvince("province");
		recipient.setReceiveNo("receiveNo");
		recipient.setReceiveType(2);
		
		
		order.setCbepcomcode("cbepcomcode");
		order.setCustomerId("customerId");
		order.setDeliveryCode("deliveryCode");
		order.setElectriccode("electriccode");
		order.setNotes("notes");
		order.setOrderDate("orderDate");
		order.setOrderId("orderId");
		order.setOrderType(2);
		order.setPackingMaterial("packingMaterial");
		order.setTpl("tpl");
		order.setWarehouseId("warehouseId");
		order.setGoodList(goodList);
		order.setRecipient(recipient);
		
		return order;
	}
}