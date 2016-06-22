package com.cigouyun.ebiz.edi.zhuozhi.beans;

public class DBGoodEntryInfo {
	
	private String reportId=""; //进境单ID
	private String orderNo=""; // 订单号
	private java.sql.Timestamp applyTime; // //申报时间
	private int status=1;  //
	private String notes=""; //申报信息
	
	
	public String getreportId() {
		return reportId;
	}
	public void setreportId(String reportId) {
		this.reportId = reportId;
	}
	
	
	public String getorderNo() {
		return orderNo;
	}
	public void setorderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public java.sql.Timestamp getapplyTime() {
		return applyTime;
	}
	public void setapplyTime(java.sql.Timestamp applyTime) {
		this.applyTime = applyTime;
	}
	
	
	public int getstatus() {
		return status;
	}
	public void setstatus(int status) {
		this.status = status;
	}
	
	
	public String getnotes() {
		return notes;
	}
	public void setnotes(String notes) {
		this.notes = notes;
	}
	
    
	
	
	
}
