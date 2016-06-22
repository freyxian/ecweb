package com.cigouyun.ebiz.edi.zhuozhi.beans;

/*
 * data bean for 
 * 3.8	电商交易订单接口 3.8.4	报文回执
 */
public class OrderResponse {
	
	
	private String orderId="";
	private int status=0;
	private String notes="";
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	


}
