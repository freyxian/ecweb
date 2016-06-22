package com.cigouyun.ebiz.edi.zhuozhi.process;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.cigouyun.ebiz.edi.zhuozhi.beans.Order;
import com.echolab.common.batch.IBatch;
import com.echolab.common.batch.IElementProcessor;
import com.echolab.common.batch.IListCreator;
import com.echolab.common.log.BasicLogger;

public class OrderListProcessBatch extends BasicLogger implements IBatch<Order>{

	private IListCreator<Order> listCreator;
	private IElementProcessor<Order>  elementProcessor;
	
	private List<Order> list;
	
	public void setlistCreator( IListCreator<Order> v ) { listCreator=v; return; }
	
	public void setelementProcessor( IElementProcessor<Order> v ) { elementProcessor=v; return; }
	
	@Override
	public List<Order> getList() {
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
		Locale myLocale = new Locale("zh", "CN");

		Locale.setDefault(myLocale);
		
	}

	/*
	 * Call IElementProcessor elementProcessor to process one item
	 * (non-Javadoc)
	 * @see com.echolab.common.batch.IBatch#processItem(java.lang.Object)
	 */
	@Override
	public Object processItem(Order item) {
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
			for (Order obj: list ){
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
	public void setList(List<Order> l) {
		// TODO Auto-generated method stub
		list = l;
	}

	

}
