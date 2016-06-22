package com.cigouyun.ebiz.edi.zhuozhi.process;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;


import com.cigouyun.ebiz.edi.zhuozhi.beans.Order;
import com.cigouyun.ebiz.edi.zhuozhi.beans.OrderResponse;
import com.cigouyun.ebiz.edi.zhuozhi.dao.DBInsertWHOrderConf;
import com.cigouyun.ebiz.edi.zhuozhi.ws.client.RESTClient;
import com.echolab.common.batch.IElementProcessor;
import com.echolab.common.log.BasicLogger;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ZhuoZhiOrderProcessor extends BasicLogger implements IElementProcessor<Order> {

	private RESTClient client=null ;
	private String url="";
	private String responseFileDir="./response/";
	public static final String fileSuffix=".json";
	
	private DBInsertWHOrderConf dbinsert = null; //new DBInsertWHOrderConf();
	
	private int i=0;
	
	ObjectMapper mapper = new ObjectMapper();

	public void setconfInsertor(DBInsertWHOrderConf v) {
		dbinsert = v;
		return;
	}
	
	public String getResponseFileDir() {
		return responseFileDir;
	}

	public void setResponseFileDir(String responseFileDir) {
		this.responseFileDir = responseFileDir;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public RESTClient getClient() {
		return client;
	}

	public void setClient(RESTClient client) {
		this.client = client;
	}



	@Override
	public Object processItem(Order order) throws Exception {
		// TODO Auto-generated method stub
		String ret="";
		if ( client == null ) {
			return null;
		}
		
		mapper.writeValue(new File("tmp\\"+order.getOrderId()+".json"), order);
		i++;

		// if ( item instanceof Order ) {
		
			// Order order = (Order) item;
			ret = client.postJSON(url, order);
			
			// mapper.writeValue(new File("tmp\\"+order.getOrderId()+"x.json"), order);
					    
		    String orderId = order.getOrderId();
		    //FileWriter fw = new FileWriter(responseFileDir+orderId+fileSuffix,false);
		    
		    FileOutputStream fos = new FileOutputStream(responseFileDir+orderId+fileSuffix,false);
		    OutputStreamWriter fw = new OutputStreamWriter(fos,Charset.forName("UTF-8"));
		    fw.append(ret);
		    fw.close();
		    
		    OrderResponse resp = mapper.readValue(ret, OrderResponse.class);
		    
		    //OrderResponse resp = new OrderResponse();
		    //resp.setNotes(response.getErrorMsg());
		    //resp.setOrderId(orderId);
		    //resp.setStatus(response.getErrorNo());
		    // dbinsert.setDatasource(ds);
		    dbinsert.dbInsertWHOrderConfirm(resp);
		// }
		// else { 
		//	warn("item is not object Order");
		//	return ret;
		// }
		
		return ret;
	}


}
