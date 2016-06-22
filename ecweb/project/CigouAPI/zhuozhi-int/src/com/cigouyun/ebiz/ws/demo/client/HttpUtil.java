package com.cigouyun.ebiz.ws.demo.client;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpUtil {
	
	private HttpClient mClient;
	private HttpResponse mRes = null;
	private String method, url;
	private JSONObject json,data;
	private static final String errorMessage = "Something wrong!";
	private static final String invalidKeyMessage = "no such key exists!";	
	
	public HttpUtil(String method, String url){
		this.method = method;
		this.url = url;
	}
	
	public void excute(){
		mClient = HttpClientBuilder.create().build();
		
		try {
			
			if (method.compareTo("GET") == 0) {
				mRes = mClient.execute(new HttpGet(url));
			} else
			if (method.compareTo("POST") == 0) {
				mRes = mClient.execute(new HttpPost(url));
			} 
			
	
			if ( mRes == null ) return;
		
			json = new JSONObject(EntityUtils.toString(mRes.getEntity()));
			data = json.getJSONObject("data");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*
	 * 检查返回值是否有效，检查json值是否有效
	 */
	private boolean checkValid(){
		return mRes == null || json == null;
	}
	/*
	 * 取得服务端回复的状态.
	 */
	public String getStatus(){
		if (checkValid()) return errorMessage;
		return mRes.getStatusLine().toString();
	}
	/*
	 * 取得状态码
	 */
	public String getStatusCode(){
		if (checkValid()) return errorMessage;
		return Integer.valueOf(mRes.getStatusLine().getStatusCode()).toString();
	}
	/*
	 * 取得返回的message信息
	 */
	public String getMessage(){
		if (checkValid()) return errorMessage;
		try {
			return json.getString("message");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return errorMessage;
	}
	/*
	 * 返回整个data信息，字符串表示
	 */
	public String getDataString(){
		if (checkValid()) return errorMessage;
		return data.toString();
	}
	/*
	 * 默认返回数据是json格式，主json中有一个子树data，默认data是我们的要处理的json数据
	 * 此处返回data的一个键key的值
	 */
	public String getDataItem(String key){
		if (checkValid()) return errorMessage;
		if (data.isNull(key)) return key+": "+invalidKeyMessage;
		try {
			return data.getString(key);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return errorMessage;
	}

	public HttpClient getmClient() {
		return mClient;
	}

	public void setmClient(HttpClient mClient) {
		this.mClient = mClient;
	}

	public HttpResponse getmRes() {
		return mRes;
	}

	public void setmRes(HttpResponse mRes) {
		this.mRes = mRes;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public static String getErrormessage() {
		return errorMessage;
	}

	public static String getInvalidkeymessage() {
		return invalidKeyMessage;
	}
	
	
	
	
}
