package com.cigouyun.ebiz.ws.demo.client;

import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;
// import org.codehaus.jettison.json.JSONObject;
import org.json.JSONObject;
import org.json.JSONTokener;



public class ClientSample2 {


public static JSONObject post(String url,JSONObject json){

	HttpClient client = HttpClientBuilder.create().build();

	HttpPost post = new HttpPost(url);

	JSONObject response = null;

	try {

		StringEntity s = new StringEntity(json.toString());
		s.setContentEncoding("UTF-8");
		s.setContentType("application/json");
		post.setEntity(s);

 
		HttpRequestBase res = (HttpRequestBase) client.execute(post);

		if(((HttpResponse) res).getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			HttpEntity entity = ((HttpResponse) res).getEntity();
			String charset = EntityUtils.getContentCharSet(entity);
			response = new JSONObject(new JSONTokener(new InputStreamReader(entity.getContent(),charset)));
		}

	} catch (Exception e) {

		throw new RuntimeException(e);

	}

	return response;

}

}
