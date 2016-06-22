package com.cigouyun.ebiz.edi.zhuozhi.beans;

/*
 * 3.10	运单号回推接口
 */

public class DeliveryInfo {
	
	    private String enOrderCode="";
	    private String wayBillNo="";
	    private String logisticsname="";
	    private String notes="";
		
	    public String getenOrderCode() {
			return enOrderCode;
		}
		public void setenOrderCode(String enOrderCode) {
			this.enOrderCode = enOrderCode;
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
		
		public String getnotes() {
			return notes;
		}
		public void setnotes(String notes) {
			this.notes = notes;
		}
	    
	    
	

}
