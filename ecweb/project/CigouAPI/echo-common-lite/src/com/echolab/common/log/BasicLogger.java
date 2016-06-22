/*
 * 
 * Copyright (C)2010 Echo Lab
 * 
 * All rights reserved.
 * 
 * The source code in this file is confidential and is the sole property of
 * Echo Lab. Its use is restricted to those readers who are authorized
 * by the corporation. Any reverse engineering or reproduction or divulgence of
 * the content of this report without the written consent from Echo Lab
 * is strictly prohibited.
 * 
 * Created on Jun 15, 2010
 */
package com.echolab.common.log;

import java.util.Enumeration;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.NDC;

/**
 * This class wraps access to Log4j categories, thus allowing less code
 * replication.
 * 

 */
public class BasicLogger implements ILogger{

	private transient Logger log_;

	public BasicLogger() {
	}

	public BasicLogger(Class c) {
		log_ = Logger.getLogger(c);
	}

	protected Logger log() {
		if (log_ == null) {
			log_ = Logger.getLogger(getClass());
		}
		return log_;
	}

	/**
	 * Enable debugging for this logger.
	 * 
	 * @param flag if true debug level is enabled.
	 */
	public void enableDebug(boolean flag) {
		if (flag) {
			log().setLevel(Level.DEBUG);
		} else {
			log().setLevel(Level.INFO);
		}
	}

	public boolean isDebugEnabled() {
		return log().isDebugEnabled();
	}

	public boolean isInfoEnabled() {
		return log().isInfoEnabled();
	}

	public void info(Object o) {
		log().info(o);
	}

	public void debug(Object o) {
		log().debug(o);
	}

	public void warn(Object o) {
		log().warn(o);
	}

	public void warn(Object o, Throwable t) {
		log().warn(o, t);
	}

	public void error(Object o) {
		log().error(o);
	}

	public void error(Object o, Throwable t) {
		log().error(o, t);
	}

	public void fatal(Object o) {
		log().fatal(o);
	}

	public void fatal(Object o, Throwable t) {
		log().fatal(o, t);
	}

	/**
	 * Push a message onto a synchronized thread stack
	 * 
	 * @param message string
	 */
	public static void ndcPush(String message) {
		NDC.push(message);
	}

	/**
	 * Pop a message from the NDC stack
	 * 
	 * @return message previously pushed on the stack
	 */
	public static String ndcPop() {
		return NDC.pop();
	}

	/**
	 * Create an instance of logger so that Logger.getLogger is not called
	 * exlicitely
	 */
	public static BasicLogger getInstance(Class c) {
		return new BasicLogger(c);
	}

	/**
	 * Wrapper around Logger.getLogger
	 */
	public static Logger getLogger(Class c) {
		return Logger.getLogger(c);
	}

	/**
	 * A utility method to initialize logging system, if it is not already
	 * initialized
	 *  
	 */
	public static void initDefaultLogging() {
		Logger root = Logger.getRootLogger();
		Enumeration appenders = root.getAllAppenders();
		if (!appenders.hasMoreElements()) {
			Log4jConfigurator.configure();
		}
	}
}