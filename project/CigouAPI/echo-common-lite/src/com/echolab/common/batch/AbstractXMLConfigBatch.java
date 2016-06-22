
package com.echolab.common.batch;

import java.io.File;
import java.util.*;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

// import org.apache.commons.configuration.*;
import com.echolab.common.batch.IBatch;
// import com.echolab.common.log.*;
import com.echolab.common.config.IXMLConfigurable;
import com.echolab.common.log.BasicLogger;

/*
 * This abstract class is to show the basic function of the batch
 */
public abstract class AbstractXMLConfigBatch extends BasicLogger 
	implements IBatch,IXMLConfigurable {
	
// default configuration file path	
	public static final String defaultConfig = "DBConfigBatch.xml";
// default environment variable for configuration file path
	public static final String ENV_BATCH_CONFIG = "BATCH_CONFIG";
	// configuration file path
	protected String configFile = null;
	// XML configuration
	protected XMLConfiguration xmlConfig;

// the list of objects to be processed	
	protected List objectList = new ArrayList();



	public AbstractXMLConfigBatch( )
	{
		return;
	}

	/*
	 * Set the list of the object to be processed in the batch
	 * (non-Javadoc)
	 * @see com.echolab.common.batch.IBatch#setList(java.util.List)
	 */
	public void setList( List l  )
	{
		objectList = l;
		return;
	}
	
	/*
	 * Get the list of the object to be processed in the batch
	 * (non-Javadoc)
	 * @see com.echolab.common.batch.IBatch#getList()
	 */
	public List getList( ) { return objectList ; }


	/*
	 * Load default configuration
	 * (non-Javadoc)
	 * @see com.echolab.common.batch.IBatch#config()
	 */
	public void config() {
		
		if ( configFile == null ) {
			configFile =  System.getProperties().getProperty(
					ENV_BATCH_CONFIG,defaultConfig
					);
		}
		config(configFile);
		return;
	}

	
	/*
	 * Load XML configuration from a file
	 */
	public void config( String file ) {
		
		configFile = file;
		info("BATCH_CONFIG="+configFile);
		
		File f = new File(configFile);
		if ( f.exists() ) { 
		
		
		
				try {
					xmlConfig = new XMLConfiguration(configFile);
					config(xmlConfig);
				} catch (ConfigurationException e) {
					// TODO Auto-generated catch block
					error(e);
				}
				config(xmlConfig);
				
		//	} catch (ConfigurationException e) {

				// TODO Auto-generated catch block
			//	error(e);
			// }

		
		}
		return;
	}

	/*
	 * Load XML configuration from a file
	 * Need implemented by sub-class upon a real instance
	 */
	public void config (XMLConfiguration c ) {
		
		xmlConfig = c;
		return;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.echolab.common.batch.IBatch#init()
	 */
	public void init() {
		config();
		return;
	}

	/*
	 * run the batch
	 * (non-Javadoc)
	 * @see com.echolab.common.batch.IBatch#run()
	 */
	public void run( ) {
		
		int i,len;
		
		prepare();
		
		for ( i=0,len=objectList.size(); i<len; i++ ) {
			
			processItem(objectList.get(i));
		}
		
		post();
		
		return;
	}


	/*
	 * process one item in the list
	 * (non-Javadoc)
	 * @see com.echolab.common.batch.IBatch#processItem(java.lang.Object)
	 */
//	public abstract Object processItem( Object item );

	/*
	 * Set the args of this batch
	 * (non-Javadoc)
	 * @see com.echolab.common.batch.IBatch#setArgs(java.lang.String[])
	 */
//	public abstract void setArgs( String args[] );
	
	/*
	public void setArgs( String args[] )
	{
		if ( args.length >= 1 )
		{
			configFile = args[0];
//			subAppType = args[1].toUpperCase();
		}
		else
			System.err.println("Args: config \n" );

		System.out.println( "configFile="+configFile );
//		System.out.println( "subAppType="+subAppType );

		return;
	}

	*/
	/*
	 * prepare the batch before run
	 */
	public abstract void prepare( );

	/*
	 * process after the batch 
	 */
	public abstract void post( );
	
/*
	public static void main(String[] args) 
	{
		if ( args.length < 1 )
		{
			System.out.println("Usage: config ");
			return;
		}

		System.out.println( "Starting...\n" );

		BatchBase batch = new BatchBase( );

		batch.setArgs( args );
		batch.config( );
		batch.init( );
		batch.run( );

		return;
	}
*/

	
};  /* end of class */
