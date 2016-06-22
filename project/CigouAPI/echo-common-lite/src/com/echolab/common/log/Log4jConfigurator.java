/*
 * 
 * Copyright (C)2004 Sprint Canada Inc.
 * 
 * All rights reserved.
 * 
 * The source code in this file is confidential and is the sole property of
 * Sprint Canada Inc. Its use is restricted to those readers who are authorized
 * by the corporation. Any reverse engineering or reproduction or divulgence of
 * the content of this report without the written consent from Sprint Canada
 * Inc. is strictly prohibited.
 * 
 * Created on Feb 22, 2004
 */
package com.echolab.common.log;

import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

/**
 * This class provides facilties for initialing the Log4J system
 * programmatically.
 * 
 * @author Eugene Abramov
 */
public class Log4jConfigurator {

	public static final String DEFAULT_PATTERN = "%d{ISO8601} %-5p [%t] %c{2} - %m%n";

	/**
	 * Configure Logger with default configuration.
	 * <em>Output is sent to System.err</em>
	 */
	public static void configure() {
		configure(System.err, Level.INFO);
	}

	/**
	 * Configure Logger with FileAppender, Pattern and Logging Level.
	 * 
	 * @param fileName
	 * @param pattern
	 * @param level
	 * @throws IOException
	 */
	public static void configure(String fileName, String pattern, Level level) throws IOException {
		PatternLayout pl = new PatternLayout(pattern);
		FileAppender fa = new FileAppender(pl, fileName);
		BasicConfigurator.configure(fa);
		Logger.getRootLogger().setLevel(level);
	}

	/**
	 * Configure Logger with FileAppender and Logging Level.
	 * 
	 * @param fileName
	 * @param level
	 * @throws IOException
	 */
	public static void configure(String fileName, Level level) throws IOException {
		configure(fileName, DEFAULT_PATTERN, level);
	}

	/**
	 * Configure Logger with FileAppender
	 * 
	 * @param out output file name
	 * @throws IOException
	 */
	public static void configure(String out) throws IOException {
		configure(out, DEFAULT_PATTERN, Level.INFO);
	}

	/**
	 * Configure logger with an OutputStream, pattern and Level
	 * 
	 * @param out
	 * @param pattern
	 * @param level
	 */
	public static void configure(OutputStream out, String pattern, Level level) {
		PatternLayout pl = new PatternLayout(pattern);
		WriterAppender wappender = new WriterAppender(pl, out);
		BasicConfigurator.configure(wappender);
		Logger.getRootLogger().setLevel(level);
	}

	/**
	 * @param out
	 */
	public static void configure(OutputStream out, Level level) {
		configure(out, DEFAULT_PATTERN, level);
	}

	/**
	 * Reconfigures root logger with the supplied log file.
	 * 
	 * @param logfile
	 * @throws IOException
	 */
	public static void setLogFile(String logfile) throws IOException {
		FileWriter outw = new FileWriter(logfile);
		Layout l = new PatternLayout(Log4jConfigurator.DEFAULT_PATTERN);
		Appender w = new WriterAppender(l, outw);
		Logger root = Logger.getRootLogger();
		root.removeAllAppenders();
		root.addAppender(w);
	}

	/**
	 * Enable debugging
	 */
	public static void enableDebug() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
	}

	/**
	 * Configure Log4J system from an XML file defined by the supplied URL
	 * 
	 * @param url
	 */
	public static void configureXml(URL url) {
		DOMConfigurator.configure(url);
	}
}