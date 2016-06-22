package com.cigouyun.ebiz.edi.zhuozhi.ws.client;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
 
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.cigouyun.ebiz.edi.zhuozhi.beans.Order;
 
/**
 * @author Crunchify.com
 * 
 */
 
public class RESTClient {
	
	// public static final String filePath="tmp\\order.json";
	
	//public static final String _host="192.168.0.212";
	//public static final int _urlPort = CrunchifyRESTService.port;
	
	// public static final String _url = "http://localhost:8080/api/crunchifyService";
	// public static final String _url = "http://localhost:8080/MyRESTService/processOrder";
	private String _url = "http://192.168.0.212:8080/api/processOrder";
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private String contentType="application/json; charset=utf-8";
	private String charset="UTF-8";
	
	private int connectTimeout=5000;
	private int readTimeout=5000;
	
	

	public String geturl() {
		return _url;
	}




	public void seturl(String _url) {
		this._url = _url;
	}




	public String getContentType() {
		return contentType;
	}




	public void setContentType(String contentType) {
		this.contentType = contentType;
	}




	public String getcharset() {
		return charset;
	}




	public void setcharset(String acceptCharset) {
		this.charset = acceptCharset;
	}




	public int getConnectTimeout() {
		return connectTimeout;
	}




	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}




	public int getReadTimeout() {
		return readTimeout;
	}




	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}




	public Order loadFromFile(String filePath ) throws JsonParseException, JsonMappingException, IOException {
		
		InputStream is = new FileInputStream( filePath );
		InputStreamReader reader = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(reader);
		String line;
		String string="";
	
	
		while ((line = br.readLine()) != null) {
			string += line + "\n";
		}
		
		Order order = mapper.readValue(string, Order.class);

		return order;
	
	}
	
	public String postJSON( Order order) {
		
		return sendJSON( this._url, order, "POST");
	}

	public String postJSON(String urlString,Order order) {
		
		return sendJSON( urlString, order, "POST");
	}
	
	public String sendJSON(Order order, String reqMethod) {
		
		return sendJSON( this._url,order,reqMethod);
		
	}
	
	/*
	 * Send the object Order to REST WS in JSON 
	 */
	public String sendJSON(String urlString,Order order, String reqMethod) {

		
		String jsonInString="";
		StringBuffer stringBuffer = new StringBuffer(100);
		String ret="";

		
		try {
 
 			jsonInString = mapper.writeValueAsString(order);
			JSONObject jsonObject = new JSONObject(jsonInString);
			System.out.println(jsonInString);
				
			// Step2: Now pass JSON File Data to REST Service
			try {

				URL url = new URL(urlString);
				
				 // HttpURLConnection conn = url.openConnection();
				//    conn.setRequestMethod("POST");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				// connection.setRequestMethod("POST");
				connection.setRequestMethod(reqMethod);
				// connection.setRequestProperty("Content-Type", "application/json");
				// connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
				connection.setRequestProperty("Content-Type",contentType );
				// connection.setRequestProperty("Accept-Charset", "UTF-8");
				connection.setRequestProperty("Accept-Charset",charset);
				connection.setConnectTimeout(connectTimeout);
				connection.setReadTimeout(readTimeout);
				
				/*
				 * send JSON object to REST WS
				 */
				OutputStreamWriter out = new OutputStreamWriter(
						connection.getOutputStream(),
						charset	);
				
				out.write(jsonObject.toString());
				// out.write(jsonInString);
				out.close();
 
				
				/*
				 * receive the response from REST WS
				 */
				BufferedReader in = new BufferedReader(
						new InputStreamReader( connection.getInputStream(), charset )
						);
 
				String st = "";
				while (true) {
					
					st=in.readLine();
					if ( st == null ) break;
					stringBuffer.append(st);
					
				}
				System.out.println(stringBuffer.toString());
				
				System.out.println("Calling REST Service Invoked Successfully..");
				
				in.close();
				
				ret = stringBuffer.toString();
				stringBuffer.delete(0, stringBuffer.length());
				
			} catch (Exception e) {
				System.out.println("\nError while calling  REST Service");
				System.out.println(e);
			}
			
 
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}




}
