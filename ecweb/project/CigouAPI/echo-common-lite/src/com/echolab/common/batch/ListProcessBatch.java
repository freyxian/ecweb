package com.echolab.common.batch;

import java.util.Date;
import java.util.List;

import com.echolab.common.log.BasicLogger;

public class ListProcessBatch extends BasicLogger implements IBatch<Object>{

	private IListCreator<Object> listCreator;
	private IElementProcessor<Object> elementProcessor;
	
	private List<Object> list;
	
	public void setlistCreator( IListCreator<Object> v ) { listCreator=v; return; }
	
	public void setelementProcessor( IElementProcessor<Object> v ) { elementProcessor=v; return; }
	
	@Override
	public List<Object> getList() {
		// TODO Auto-generated method stub
		if ( list == null ) {
			if ( listCreator != null ) {
			list=listCreator.createList();
			} else {
				warn("List Creator is null");
			}
		}
		return list;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	/*
	 * Call IElementProcessor elementProcessor to process one item
	 * (non-Javadoc)
	 * @see com.echolab.common.batch.IBatch#processItem(java.lang.Object)
	 */
	@Override
	public Object processItem(Object item) {
		// TODO Auto-generated method stub
		
		Object ret=null;
		
			if (elementProcessor != null ) {
				
				try {
					ret = elementProcessor.processItem(item);	
				} catch (Exception e) {
					error("Failed to process Element:"+item);
					error(e);
				}
				
			}
			else {
				warn("ElementProcessor is null, can't process element");
			}
			
		return ret;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		info("Start to run batch at:"+ new Date());
		try {
			this.getList();
			for (Object obj: list ){
				processItem(obj);
			}
			
		} catch ( Exception e) {
			error("Failure in run method:"+e);
			error(e.getStackTrace());
	
		}
		
		info("Batch end at:"+ new Date());
	
		return;
		
	}

	@Override
	public void setList(List<Object> l) {
		// TODO Auto-generated method stub
		list = l;
		return;
	} 
	

}
