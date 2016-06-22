package com.echolab.common.log;

public interface ILogger {

	public void info(Object o);
	public void debug(Object o);

	public void warn(Object o);
	public void warn(Object o, Throwable t);

	public void error(Object o);
	public void error(Object o, Throwable t);

	public void fatal(Object o);
	public void fatal(Object o, Throwable t);

	
}
