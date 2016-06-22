package com.cigouyun.ebiz.edi.zhuozhi.beans;

import java.sql.Timestamp;

public class LogisticsStatus {

	private String acceptTime;
	private String acceptAddress="";
	private String acceptStatus="";
	private String acceptNotes="";
	
	/* !!! need to change to class to String */
	public String getAcceptTime() {
		return acceptTime;
	}
	public void setacceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}
	
	
	public String getacceptAddress() {
		return acceptAddress;
	}
	public void setacceptAddress(String acceptAddress) {
		this.acceptAddress = acceptAddress;
	}
	
	
	public String getacceptStatus() {
		return acceptStatus;
	}
	public void setacceptStatus(String acceptStatus) {
		this.acceptStatus = acceptStatus;
	}
	
	public String getacceptNotes() {
		return acceptNotes;
	}
	public void setacceptNotes(String acceptNotes) {
		this.acceptNotes = acceptNotes;
	}
	
	
	
}
