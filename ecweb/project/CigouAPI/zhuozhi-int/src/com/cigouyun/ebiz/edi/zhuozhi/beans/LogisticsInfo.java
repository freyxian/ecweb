package com.cigouyun.ebiz.edi.zhuozhi.beans;

import java.util.List;

/*
 * 3.12	物流进程推送接口
 */
public class LogisticsInfo {

	private String orderId="";
	private String wayBillNo="";
	private String logisticsname="";
	private String logisticsId="";
	private String notes="";
	
	List<LogisticsStatus> logisticsInfos=null;

	public String getorderId() {
		return orderId;
	}

	public void setorderId(String orderId) {
		this.orderId = orderId;
	}

	public String getwayBillNo() {
		return wayBillNo;
	}

	public void setwayBillNo(String wayBillNo) {
		this.wayBillNo = wayBillNo;
	}

	public String getlogisticsname() {
		return logisticsname;
	}

	public void setlogisticsname(String logisticsname) {
		this.logisticsname = logisticsname;
	}

	public String getlogisticsId() {
		return logisticsId;
	}

	public void setlogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}

	public String getnotes() {
		return notes;
	}

	public void setnotes(String notes) {
		this.notes = notes;
	}

	public List<LogisticsStatus> getlogisticsInfos() {
		return logisticsInfos;
	}

	public void setlogisticsInfos(List<LogisticsStatus> logisticsInfos) {
		this.logisticsInfos = logisticsInfos;
	}
	
	
	
}
