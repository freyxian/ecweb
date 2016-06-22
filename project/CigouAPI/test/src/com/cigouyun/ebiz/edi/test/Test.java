package com.cigouyun.ebiz.edi.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.cigouyun.ebiz.edi.logic.DBConnection;
import com.cigouyun.ebiz.edi.zhuozhi.beans.DBGoodEntryInfo;
import com.cigouyun.ebiz.edi.zhuozhi.beans.DeliveryInfo;
import com.cigouyun.ebiz.edi.zhuozhi.beans.Good;
import com.cigouyun.ebiz.edi.zhuozhi.beans.GoodEntryInfo;
import com.cigouyun.ebiz.edi.zhuozhi.beans.LogisticsInfo;
import com.cigouyun.ebiz.edi.zhuozhi.beans.LogisticsStatus;
import com.cigouyun.ebiz.edi.zhuozhi.beans.Order;
import com.cigouyun.ebiz.edi.zhuozhi.beans.OrderResponse;
import com.cigouyun.ebiz.edi.zhuozhi.beans.Recipient;
import com.cigouyun.ebiz.edi.zhuozhi.dao.DBGetWHOrder;
import com.cigouyun.ebiz.edi.zhuozhi.dao.DBInsertWHDeliveryInfo;
import com.cigouyun.ebiz.edi.zhuozhi.dao.DBInsertWHGoodEntry;
import com.cigouyun.ebiz.edi.zhuozhi.dao.DBInsertWHLogisticsInfo;
import com.cigouyun.ebiz.edi.zhuozhi.dao.DBInsertWHOrderConf;
import com.cigouyun.ebiz.edi.zhuozhi.process.ZZDBOrderListCreator;
import com.cigouyun.ebiz.edi.zhuozhi.process.ZhuoZhiOrderProcessor;
import com.cigouyun.ebiz.edi.zhuozhi.ws.client.RESTClient;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

	DataSource datasource ; // = new BasicDataSource();

	private static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://192.168.0.212:3306/cigou";
	// private static final String JDBC_URL = "jdbc:mysql://173.35.140.249/cigou";
		
	private static final String JDBC_USER = "stuser";
	private static final String JDBC_PASS="suntech";
	
	
	ObjectMapper mapper = new ObjectMapper();

	public Test () {
		this.createDataSource();
	}
		
	public void createDataSource() {
		
		BasicDataSource ds = new BasicDataSource();
		
  	     ds.setDriverClassName( JDBC_DRIVER );
  	     ds.setUrl(JDBC_URL);
		 ds.setUsername( JDBC_USER );
		 ds.setPassword(JDBC_PASS);
		 this.datasource = ds;
		
	}
	
	public DataSource getDatasource() {
		return datasource;
	}
	
	public void testGetOrder( ){
		
		DBGetWHOrder req = new DBGetWHOrder();
		// this.createDataSource();
		req.setDatasource(this.getDatasource());
		
		Order order = req.dbGetWHOrder("00043473809");
		
		System.out.println("CustomerId:"+order.getCustomerId());
		
		
		Recipient rec = order.getRecipient();
		System.out.println("Name:"+rec.getName());
		System.out.println("mobile:"+rec.getMobilePhone());

		System.out.println("number of goods:"+order.getGoodList().size());
	
		for (Good g:order.getGoodList()){
			System.out.println("=======================");
			System.out.println("good id:"+g.getGoodId());
			System.out.println("amount:"+g.getAmount());
			System.out.println("price:"+g.getPrice());
			System.out.println("=======================");

		}
		
		
	}

	public void testList() {
	
		List<Good> list = new ArrayList<Good>();
		Order o1 = new Order();
		
		Good g1 = new Good();
		
		g1.setGoodId("喜购云");
		g1.setAmount(100);
		g1.setPrice(10.99);
		
		Good g2 = new Good();
		 
		g2.setGoodId("深海鱼油");
		g2.setAmount(100);
		g2.setPrice(10.99);
		
		list.add(g1);
		list.add(g2);
		
		for ( Good g : list ) {
			
			System.out.println("ID"+g.getGoodId());
			System.out.println("amount"+g.getAmount());
			System.out.println("price"+g.getPrice());
		}
		
		Order order = JsonSample.createDummyOrder();
		JsonSample.showOrder(order);
	}

	
	public void testGoodEntry() {
		
	
		// String url="http://192.168.0.212:8080/api/goodsEntry";
		String url="http://173.35.140.249:8080/api/goodsEntry";
		
		// Order order = new Order();
		
		String filePath="tmp\\goodEntry.json";
		
		GoodEntryInfo bean;
		String ret="";
		try {
			
			bean = JSONUtility.loadGoodEntryFile(filePath);
			
			DBGoodEntryInfo dbBean = bean.toDBGoodEntryInfo();
			
			String orderId = bean.getorderNo();
			
			// String stTime = bean.getapplyTime();
			
			bean.setapplyTime("2016-04-21 14:45:00");
			
			System.out.println("===========dbBean===========");
			System.out.println( mapper.writeValueAsString(dbBean) );
			System.out.println("===========dbBean===========");

			
			
			ret = JSONUtility.sendJSON( url, bean, "POST" );
			
			mapper.writeValue(new File("tmp\\"+orderId+"-entry.json"), bean);

			System.out.println("===========return message===========");
			System.out.println(ret);
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	
	}


	public void testLogisticsInfo() {
		
		
		// String url="http://192.168.0.212:8080/api/logisticsInfo";
		
		String url="http://173.35.140.249:8080/api/logisticsInfo";
		
		// Order order = new Order();
		
		String filePath="tmp\\logisticsInfo.json";
		
		LogisticsInfo bean;
		String ret="";
		try {
			
			bean = JSONUtility.loadLogisticsInfoFile(filePath);
			
			String orderId = bean.getorderId();
		
			
			ret = JSONUtility.sendJSON( url, bean, "POST" );
			
			mapper.writeValue(new File("tmp\\"+orderId+"-log.json"), bean);

			System.out.println("===========return message===========");
			System.out.println(ret);
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	
	}

	public void testDeliveryInfo() {
		
		
		// String url="http://192.168.0.212:8080/api/deliveryInfo";
		
		String url="http://173.35.140.249:8080/api/deliveryInfo";
		
		// Order order = new Order();
		
		String filePath="tmp\\deliveryInfo.json";
		
		DeliveryInfo bean;
		String ret="";
		try {
			
			bean = JSONUtility.loadDeliveryInfoFile(filePath);
					
			
			String orderId = bean.getenOrderCode();
		
			
			ret = JSONUtility.sendJSON( url, bean, "POST" );
			
			mapper.writeValue(new File("tmp\\"+orderId+"-del.json"), bean);

			System.out.println("===========return message===========");
			System.out.println(ret);
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	
	}
	
	
	public void testClient() {
		
		RESTClient client = new RESTClient();
		
		// client.seturl("http://192.168.0.212:8080/api/processOrder");
		
		client.seturl("http://173.35.140.249:8080/api/processOrder");
		
		// Order order = new Order();
		
		String filePath="tmp\\order.json";
		
		Order order;
		String ret="";
		try {
			
			order = client.loadFromFile(filePath);
			
			String orderId = order.getOrderId();
			
			ret = client.postJSON( order );
			
			mapper.writeValue(new File("tmp\\"+orderId+"-out.json"), order);

			System.out.println("===========return message===========");
			System.out.println(ret);
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	
	}
	
	public List<Order> testDAO() {
		
		// this.createDataSource();
		
		ZZDBOrderListCreator lc = new ZZDBOrderListCreator();
		
		lc.setDataSource(this.getDatasource());
		
		// lc.setOrder_id("00043473809");
		lc.setwhereClause(" not exists ( select 1 from wh_order_confirm c where c.order_id = oh.order_id ) ");
		
		
		List<Order> list = lc.createList();
		
		for ( Order obj : list ) {
			JsonSample.showOrder(obj);
		}
		
		
		System.out.println("reach to end of method");
		return (list);
		
	}
	
	public void testDBInsert() {
		
		DBInsertWHOrderConf dbins = new DBInsertWHOrderConf();
		dbins.setdataSource(this.getDatasource());
		
		 OrderResponse resp = new OrderResponse();
		 resp.setNotes("No error found");
		 resp.setOrderId("00043473811");
		 resp.setStatus(0);
		    
		dbins.dbInsertWHOrderConfirm(resp);
		return;
	}
	
	public void testDBInsertLogistic() {
		
		DBConnection conn = DBConnection.getDBConnection();
		
		DBInsertWHLogisticsInfo dbins = new DBInsertWHLogisticsInfo();
		dbins.setdataSource(conn.getdataSource());
		
		LogisticsInfo bean = new LogisticsInfo();
		List<LogisticsStatus> list = new ArrayList<LogisticsStatus>();

		bean.setorderId("743847351");
		bean.setlogisticsId("00032");
		bean.setlogisticsname("京东");
		bean.setnotes("notes");
		bean.setwayBillNo("bill-00073496");
		
		LogisticsStatus status = new LogisticsStatus();
		
		status.setacceptAddress("广东广州海珠区");
		status.setacceptNotes("notes");
		status.setacceptStatus("02");
		status.setacceptTime("2016-4-12 20:45:00");
		
		list.add(status);
		
		status = new LogisticsStatus();
		status.setacceptAddress("广东广州白云区");
		status.setacceptNotes("notes");
		status.setacceptStatus("02");
		status.setacceptTime("2016-4-13 21:10:00");
		list.add(status);
		
		bean.setlogisticsInfos(list);
		
		
		    
		dbins.dbInsertWHLogisticsInfo(bean);
		
		return;
	}
	

	public void testDBInsertDelivery() {
		
		DBConnection conn = DBConnection.getDBConnection();
		
		DBInsertWHDeliveryInfo dbins = new DBInsertWHDeliveryInfo();
		dbins.setdataSource(conn.getdataSource());
		
		DeliveryInfo bean = new DeliveryInfo();

		bean.setenOrderCode("del-000847352");
		bean.setlogisticsname("京东2");
		bean.setnotes("notes here");
		bean.setwayBillNo("bill-000847352");
				    
		dbins.dbInsertWHDeliveryInfo(bean);
		
		return;
	}

	public void testDBInsertWHGoodEntry() {
		
		DBConnection conn = DBConnection.getDBConnection();
		
		DBInsertWHGoodEntry dbins = new DBInsertWHGoodEntry();
		dbins.setdataSource(conn.getdataSource());
		
		GoodEntryInfo bean = new GoodEntryInfo();

		bean.setapplyTime("2016-4-11 21:00:08");
		bean.setnotes("notes here");
		bean.setorderNo("ord-000847352");
		bean.setreportId("reportId");
		bean.setstatus(2);
		
		
		dbins.dbInsertWHDeliveryInfo(bean.toDBGoodEntryInfo());
		
		return;
	}
	
	
	
	public void testRun() throws Exception {
		
		List<Order> list = testDAO();
		String url="http://173.35.140.249:8080/api/processOrder";
		String ret="";
		RESTClient client = new RESTClient();
		client.seturl(url);
		// client.seturl("http://192.168.0.212:8080/api/processOrder");
		
		DBInsertWHOrderConf dbins = new DBInsertWHOrderConf();
		dbins.setdataSource(this.getDatasource());
		
		ZhuoZhiOrderProcessor proc = new ZhuoZhiOrderProcessor();
		proc.setClient(client);
		proc.setUrl(url);
		proc.setconfInsertor(dbins);

		
		// proc.setResponseFileDir(".//zz_response");
		
		for ( Order order : list ) {
			ret = (String)proc.processItem(order);		
			// ret = client.postJSON( order );
			System.out.println(ret);
		}
	}
	
	public static void main(String[] args) {

		// Locale myLocale = new Locale("zh", "CN");
		// Locale.setDefault(myLocale);
		
		Test test = new Test();
		
		// test.testClient();
		
		// test.testDAO();
		
		// test.testGetOrder();
		// test.testList();
		
		//test.testDBInsert();
		
		test.testGoodEntry();
		test.testLogisticsInfo();
		test.testDeliveryInfo();
		
		// test database insert date
		//test.testDBInsertLogistic();
		//test.testDBInsertDelivery();
		//test.testDBInsertWHGoodEntry();
		
		/*
		try {
			test.testRun();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		
		System.out.println("reach to end of main");
		
		return;
	}
}
