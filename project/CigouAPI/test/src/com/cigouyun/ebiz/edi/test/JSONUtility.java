package com.cigouyun.ebiz.edi.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.cigouyun.ebiz.edi.zhuozhi.beans.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtility {
	
	private static ObjectMapper mapper = new ObjectMapper();
	public static String contentType="application/json; charset=utf-8";
	public static String charset="UTF-8";
	
	public static int connectTimeout=15000;
	public static int readTimeout=15000;


public static GoodEntryInfo loadGoodEntryFile(String filePath ) throws JsonParseException, JsonMappingException, IOException {
		
		InputStream is = new FileInputStream( filePath );
		InputStreamReader reader = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(reader);
		String line;
		String string="";
	
	
		while ((line = br.readLine()) != null) {
			string += line + "\n";
		}
		
		GoodEntryInfo bean = mapper.readValue(string, GoodEntryInfo.class);

		return bean;
	
	}


	public static LogisticsInfo loadLogisticsInfoFile(String filePath ) throws JsonParseException, JsonMappingException, IOException {
		
		InputStream is = new FileInputStream( filePath );
		InputStreamReader reader = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(reader);
		String line;
		String string="";
	
	
		while ((line = br.readLine()) != null) {
			string += line + "\n";
		}
		
		LogisticsInfo bean = mapper.readValue(string, LogisticsInfo.class);

		return bean;
	
	}


	public static DeliveryInfo loadDeliveryInfoFile(String filePath ) throws JsonParseException, JsonMappingException, IOException {
		
		InputStream is = new FileInputStream( filePath );
		InputStreamReader reader = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(reader);
		String line;
		String string="";
	
	
		while ((line = br.readLine()) != null) {
			string += line + "\n";
		}
		
		DeliveryInfo bean = mapper.readValue(string, DeliveryInfo.class);

		return bean;
	
	}


/*
 * Send the object Order to REST WS in JSON 
 */
public static String sendJSON(String urlString,Object obj, String reqMethod) {

	
	String jsonInString="";
	StringBuffer stringBuffer = new StringBuffer(100);
	String ret="";

	
	try {

			jsonInString = mapper.writeValueAsString(obj);
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
			connection.setRequestProperty("Content-Type",contentType );
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
