package com.cigouyun.ebiz.edi.zhuozhi.ws.client;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.cigouyun.ebiz.edi.zhuozhi.beans.Good;
import com.cigouyun.ebiz.edi.zhuozhi.beans.Order;
import com.cigouyun.ebiz.edi.zhuozhi.beans.Recipient;
import com.cigouyun.ebiz.edi.zhuozhi.dao.DBGetWHOrder;
import com.cigouyun.ebiz.edi.zhuozhi.process.ZZDBOrderListCreator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

	  DataSource datasource ; // = new BasicDataSource();

	private static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://192.168.0.212:3306/cigou";
		
	private static final String JDBC_USER = "stuser";
	private static final String JDBC_PASS="suntech";
		
	public void createDataSource() {
		
		BasicDataSource ds = new BasicDataSource();
		 /*
		 dataSource.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
		 dataSource.setUrl("jdbc:jtds:sqlserver://localhost:1433/GIS");
		 dataSource.setUsername("sa");
		 dataSource.setPassword("sprint34");
		*/ 
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
		this.createDataSource();
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
			ret = client.postJSON( order );
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
	
	public void testDAO() {
		
		ZZDBOrderListCreator lc = new ZZDBOrderListCreator();
		lc.setOrder_id("00043473809");
		List<Order> list = lc.createList();
		
		for ( Object obj : list ) {
			
			Order order = (Order)obj;
			
			System.out.println("CustomerId:"+order.getCustomerId());
			
			
			Recipient rec = order.getRecipient();
			System.out.println("Name:"+rec.getName());
			System.out.println("Name:"+rec.getMobilePhone());

			System.out.println("number of goods:"+order.getGoodList().size());
			
			
			
		}
		
		
		System.out.println("reach to end of method");
		return;
		
	}
	
	public static void main(String[] args) {

		Test test = new Test();
		//test.testClient();
		
		// test.testDAO();
		
		test.testGetOrder();
		
		System.out.println("reach to end of main");
		
		return;
	}
}
