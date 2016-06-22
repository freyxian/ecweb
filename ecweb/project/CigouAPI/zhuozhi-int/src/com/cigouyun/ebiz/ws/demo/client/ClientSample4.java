package com.cigouyun.ebiz.ws.demo.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.cigouyun.ebiz.edi.zhuozhi.beans.Order;
import com.google.gson.Gson;

public class ClientSample4 {
	
	public void post( String url ) throws ClientProtocolException, IOException {
		
	Order order = new Order();
	
	// String       postUrl       = "www.site.com";// put in your url
	String       postUrl       = url;
	Gson         gson          = new Gson();
	HttpClient   httpClient    = HttpClientBuilder.create().build();
	HttpPost     post          = new HttpPost(postUrl);
	StringEntity postingString = new StringEntity(gson.toJson(order));//gson.tojson() converts your pojo to json
	post.setEntity(postingString);
	post.setHeader("Content-type", "application/json");
	HttpResponse  response = httpClient.execute(post);
	
	return;
	}

}
