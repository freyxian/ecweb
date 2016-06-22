
package com.echolab.common.batch;
 
import java.lang.*;
import java.util.*;



//import org.apache.commons.beanutils.*;

//import com.echolab.common.utils.*;
//import com.echolab.common.template.*;

/* 
 This interface model the batches in package com.echolab.finanlyst.batch
 This class use List to hold all items should be processed in this batch
 The method of implementation class could be like this:
 run( )
 {
	int i,len;
	Object obj,outputObject;

	List list = this.getList();


	len = list.size();
	for (i=0;i<len;i++)
	{
		obj = get(i);
		outputObject = processItem( obj );

		... //other process
	}

 }

  main( )
  {
	IBatch batch = new IBatch( );
	batch.setArgs( args );
	batch.config( );
	batch.init( );
	batch.run( );

  }
*/
public interface IBatch<T> extends Runnable
{

/*
** set the properties from conf_file
*/
//public void config( );

/*
** set the argument
*/
//public void setArgs( String args[] );

/*
** init the batch
*/
public void init( );

/*
** set the list of the batch
*/
public void setList( List<T> l  );

/*
** get the list of the batch
*/
public List<T> getList( );

/*
** processing the batch
*/
public void run( );


/*
** process one item in the list
*/
public Object processItem( T item );


};  /* end of class */
