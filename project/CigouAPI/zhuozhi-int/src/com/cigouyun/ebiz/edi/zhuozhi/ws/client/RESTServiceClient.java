package com.cigouyun.ebiz.edi.zhuozhi.ws.client;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
 
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.cigouyun.ebiz.edi.zhuozhi.beans.Good;
import com.cigouyun.ebiz.edi.zhuozhi.beans.Order;
 
/**
 * @author Crunchify.com
 * 
 */
 
public class RESTServiceClient {
	
	public static final String filePath="tmp\\order.json";
	
	//public static final String _host="192.168.0.212";
	//public static final int _urlPort = CrunchifyRESTService.port;
	
	// public static final String _url = "http://localhost:8080/api/crunchifyService";
	// public static final String _url = "http://localhost:8080/MyRESTService/processOrder";
	public static final String _url = "http://192.168.0.212:8080/api/processOrder";
	
	public static void main(String[] args) {

		String string = "";
		ObjectMapper mapper = new ObjectMapper();

		StringBuffer stringBuffer = new StringBuffer(100);

		try {
 
			// Step1: Let's 1st read file from fileSystem
			// Change order.json path here
			InputStream is = new FileInputStream( filePath );
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(reader);
			String line;
			while ((line = br.readLine()) != null) {
				string += line + "\n";
			}
			
			Order order = mapper.readValue(string, Order.class);
			
			order.setCustomerId("Zheng_Wang");
			order.setOrderDate("2016-04-03 12:34:33");
			//order.setOrderId("00043473857");
			order.setCbepcomcode("喜购运电子商务公司");
			
			Good good = new Good();
			good.setGoodId("深海鱼油");
			// good.setGoodId("fish oil");
			good.setAmount(120);
			good.setPrice(99.9);
			
			List<Good> list = order.getGoodList();
			list.add(good);
			
			order.setGoodList(list);
			
			
			String jsonInString="";
 
			Long ordId = Long.parseLong(order.getOrderId());
			
			for ( int i=0,j=100000; i<j; i++ ) {
			
				ordId++;
				
				order.setOrderId(ordId.toString());
				jsonInString = mapper.writeValueAsString(order);
				JSONObject jsonObject = new JSONObject(jsonInString);
				System.out.println(jsonObject);
				
			// Step2: Now pass JSON File Data to REST Service
			try {
				// URL url = new URL("http://localhost:"+ urlPort + "/CrunchifyTutorials/api/crunchifyService");
				// URL url = new URL("http://localhost:"+ urlPort + "/api/crunchifyService");
				URL url = new URL(_url);
				
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				// connection.setRequestProperty("Content-Type", "application/json");
				connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
				connection.setRequestProperty("Accept-Charset", "UTF-8");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(jsonObject.toString());
				out.close();
 
				BufferedReader in = new BufferedReader(
						new InputStreamReader( connection.getInputStream() )
						);
 
				String st = "";
				while (true) {
					
					st=in.readLine();
					if ( st == null ) break;
					stringBuffer.append(st);
					
				}
				System.out.println(stringBuffer.toString());
				
				System.out.println("\nCrunchify REST Service Invoked Successfully..");
				
				in.close();
				stringBuffer.delete(0, stringBuffer.length());
				
			} catch (Exception e) {
				System.out.println("\nError while calling Crunchify REST Service");
				System.out.println(e);
			}
			
		} // end of for loop
 
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
