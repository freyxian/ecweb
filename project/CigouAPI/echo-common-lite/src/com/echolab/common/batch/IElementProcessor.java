package com.echolab.common.batch;

import java.io.FileNotFoundException;

public interface IElementProcessor<T> {
	
	public Object processItem(T item) throws Exception ;

}
