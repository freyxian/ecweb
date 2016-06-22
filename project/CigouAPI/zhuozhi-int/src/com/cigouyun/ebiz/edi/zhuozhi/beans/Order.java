package com.cigouyun.ebiz.edi.zhuozhi.beans;

import java.util.List;

/*
 * data bean for 
 * 3.8	电商交易订单接口
 */
public class Order {

	private String orderId;
    public static final int ORDERID_SIZE=50;
	
	private String orderDate;
	public static final int ORDERDATE_SIZE=19;
	
	private String customerId;
	public static final int CUSTOMERID_SIZE=20;
	
	private String packingMaterial;
	public static final int PACKINGMATERIAL_SIZE=50;
	
	private String warehouseId;
	public static final int WAREHOUSEID_SIZE=50;
	
	private String tpl;
	public static final int TPL_SIZE=50;
	
	private int orderType;
	
	private String electriccode;
	public static final int ELECTRICCODE_SIZE=64;
	
	private String cbepcomcode;
	public static final int CBEPCOMCODE_SIZE=64;
	
	private String deliveryCode;
	public static final int DELIVERYCODE_SIZE=64;
	
	private String notes;
	public static final int NOTES_SIZE=2000;
	
	private List<Good> goodList;

	private Recipient recipient;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPackingMaterial() {
		return packingMaterial;
	}

	public void setPackingMaterial(String packingMaterial) {
		this.packingMaterial = packingMaterial;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getTpl() {
		return tpl;
	}

	public void setTpl(String tpl) {
		this.tpl = tpl;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public String getElectriccode() {
		return electriccode;
	}

	public void setElectriccode(String electriccode) {
		this.electriccode = electriccode;
	}

	public String getDeliveryCode() {
		return deliveryCode;
	}

	public void setDeliveryCode(String deliveryCode) {
		this.deliveryCode = deliveryCode;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<Good> getGoodList() {
		return goodList;
	}

	public void setGoodList(List<Good> goodList) {
		this.goodList = goodList;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}
	
	public String getCbepcomcode() {
		return cbepcomcode;
	}

	public void setCbepcomcode(String cbepcomcode) {
		this.cbepcomcode = cbepcomcode;
	}
	
}
