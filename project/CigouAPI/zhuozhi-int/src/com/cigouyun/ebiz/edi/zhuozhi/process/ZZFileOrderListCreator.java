package com.cigouyun.ebiz.edi.zhuozhi.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.cigouyun.ebiz.edi.zhuozhi.beans.Order;
import com.echolab.common.batch.IListCreator;
import com.echolab.common.log.BasicLogger;
import com.echolab.commons.directory.ListFiles;

public class ZZFileOrderListCreator extends BasicLogger implements IListCreator<Order>{

	private String inputFilePath="./ZZ_orders/";
	ObjectMapper mapper = new ObjectMapper();
	ListFiles fileListor = new ListFiles();
	
	
	
	public String getInputFilePath() {
		return inputFilePath;
	}



	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}



	@Override
	public List<Order> createList() {
		
		List<Order> orderList = new ArrayList<Order>();
		List<File> list = fileListor.getFiles(inputFilePath, ".json");
		BufferedReader fr;
		StringBuffer sb = new StringBuffer();
		
		for ( File f: list ){

				sb.delete(0, sb.length());
				try {
					fr = new BufferedReader( new FileReader(f) );
					String line = null;
					while ((line = fr.readLine()) != null) {
						sb.append(line);
					}
					
				Order order = mapper.readValue(sb.toString(), Order.class);

				orderList.add(order);
				
					
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					error("File not found "+f.getName());
					error(e);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					error("Read file error "+f.getName());
					error(e);
				}
				
		
		
		
		}
		// TODO Auto-generated method stub
		return orderList;
	}

}
