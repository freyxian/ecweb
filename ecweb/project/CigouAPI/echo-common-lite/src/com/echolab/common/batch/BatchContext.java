package com.echolab.common.batch;

import java.util.*;
import com.echolab.common.log.*;


/**
 * This class is used as the context of running batch
 * @author zheng.wang
 *
 */
public class BatchContext extends BasicLogger {

	
	private static BatchContext instance = BatchContext.getInstance();
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	protected BatchContext() {
	      // Exists only to defeat instantiation.
	}

	public static synchronized BatchContext getInstance() {
	      if(instance == null) {
	         instance = new BatchContext();
	      }
	      return instance;
	}
	
	/**
	 * save object into context by key
	 * @param key
	 * @param value
	 */
	public synchronized void  setObject(String key, Object value ){

		if ( map == null ) map = new HashMap<String, Object>();
		
		if ( map.containsKey(key) ) map.remove(key);
		
		map.put(key, value);
		
		return;
	}
	
	/**
	 * get object from context by key
	 * @param key
	 * @return
	 */
	public Object getObject(String key){
		
		if ( map == null ) map = new HashMap<String, Object>();
		return(map.get(key));
	}
	
	
	
}// end of class
