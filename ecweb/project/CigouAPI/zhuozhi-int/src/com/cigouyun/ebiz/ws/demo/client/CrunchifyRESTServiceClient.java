package com.cigouyun.ebiz.ws.demo.client;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import org.json.JSONObject;

//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.fasterxml.jackson.databind.JsonMappingException;
import com.cigouyun.ebiz.edi.zhuozhi.beans.Order;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.cigouyun.ebiz.edi.test.Staff;
// import com.cigouyun.ebiz.edi.zhuozhi.beans.*;
// import com.cigouyun.ebiz.edi.zhuozhi.beans.Order;
// import com.cigouyun.ebiz.ws.demo.server.CrunchifyRESTService;
 
/**
 * @author Crunchify.com
 * 
 */
 
public class CrunchifyRESTServiceClient {
	
	public static final String filePath="tmp\\order.json";
	
	public static final String _host="192.168.0.212";
	public static final int _urlPort = 8080;
	
	public static final String _url = "http://192.168.0.212:8080/api/CigouRESTWS";
	
	public static void main(String[] args) {

		String string = "";
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
 
			// Step1: Let's 1st read file from fileSystem
			// Change CrunchifyJSON.txt path here
			// InputStream crunchifyInputStream = new FileInputStream("/Users/<username>/Documents/CrunchifyJSON.txt");
			InputStream crunchifyInputStream = new FileInputStream( filePath );
			InputStreamReader crunchifyReader = new InputStreamReader(crunchifyInputStream);
			BufferedReader br = new BufferedReader(crunchifyReader);
			String line;
			while ((line = br.readLine()) != null) {
				string += line + "\n";
			}
			
			Order order = mapper.readValue(string, Order.class);
			
			order.setCustomerId("Zheng_Wang");
			order.setOrderDate(format.format(new Date()));
			// order.setOrderId("00043473847");
			
			String jsonInString = mapper.writeValueAsString(order);


 
			JSONObject jsonObject = new JSONObject(jsonInString);
			System.out.println(jsonObject);
 
			// Step2: Now pass JSON File Data to REST Service
			try {
				// URL url = new URL("http://localhost:"+ urlPort + "/CrunchifyTutorials/api/crunchifyService");
				// URL url = new URL("http://localhost:"+ urlPort + "/api/crunchifyService");
				URL url = new URL(_url);
				
				URLConnection connection = url.openConnection();
				// URLConnection connection = new HttpURLConnection(url);
				connection.setDoOutput(true);
//				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				
				connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
				connection.setRequestProperty("Accept-Charset", "UTF-8");
				
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(jsonObject.toString());
				out.close();
 
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
 
				while (in.readLine() != null) {
				}
				System.out.println("\nCrunchify REST Service Invoked Successfully..");
				in.close();
			} catch (Exception e) {
				System.out.println("\nError while calling Crunchify REST Service");
				System.out.println(e);
			}
 
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
