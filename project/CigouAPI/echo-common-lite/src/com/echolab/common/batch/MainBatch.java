package com.echolab.common.batch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.echolab.common.batch.*;

/**
 * This class is the main entry to run the batch job.
 * 
 * @author zheng.wang
 *
 */
public class MainBatch {

	//private final static String _SPRING_XML="D:\\workspace\\tmp\\fin2\\cfg\\ks.xml";
	// private final static String _SPRING_XML="cfg/ks.xml";
	private final static String _SPRING_XML="cfg/AnalysisHP.xml";
	
	
	private IBatch batch;
	
		
	public void config(){
		
	}

	public static void main(String[] args) 
	{
		// String[] ars = {"D:\\tmp\\all\\cfg\\AnalysisHP.xml","Batch"};
		// String[] ars = {"cfg/ks.xml","KSBatch"};
//		String[] ars = {"cfg/cvtHTML.xml","ConvertBatch"};
		String[] ars = {"cfg/AnalysisHP.xml","PrintPriceBatch"};

	    BasicConfigurator.configure();
	    
		run(args);
		return;
	}

	/**
	 * run the program
	 * @param args
	 */
	public static void run(String[] args){ 
		
		if ( args.length < 2 )
		{
			System.out.println("Usage: config app");
			return;
		}

	ApplicationContext context =
		// new ClassPathXmlApplicationContext(_SPRING_XML);
		// new FileSystemXmlApplicationContext(_SPRING_XML);
		new FileSystemXmlApplicationContext( args[0] );
		
	IBatch batch = (IBatch) context.getBean( args[1] );
		
	batch.init();
	batch.run();

	return;
	}
}
