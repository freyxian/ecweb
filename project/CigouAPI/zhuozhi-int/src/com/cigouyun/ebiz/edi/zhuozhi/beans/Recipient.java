package com.cigouyun.ebiz.edi.zhuozhi.beans;


/*
 * data bean for 
 * 3.8	电商交易订单接口
 */
public class Recipient {

	 
	 private String name=""; 
	 public static final int NAME_SIZE=20;
	 
	 private int receiveType;
	 
	 private String receiveNo="";
	 public static final int RECEIVENO_SIZE=30;

	 private String mobilePhone="";
	 public static final int MOBLEPHONE_SIZE=20;

	 private String phone="";
	 public static final int PHONE_SIZE=10;

	 private String country="";
	 public static final int COUNTRY_SIZE=10;

	 private String province="";
	 public static final int PROVINCE_SIZE=10;

	 private String city="";
	 public static final int CITY_SIZE=10;

	 private String district="";
	 public static final int DISTRICT_SIZE=10;

	 private String address="";
	 public static final int ADDRESS_SIZE=250;

	 private String postCode="";
	 public static final int POSTCODE_SIZE=10;

	 
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getReceiveType() {
		return receiveType;
	}
	public void setReceiveType(int receiveType) {
		this.receiveType = receiveType;
	}
	public String getReceiveNo() {
		return receiveNo;
	}
	public void setReceiveNo(String receiveNo) {
		this.receiveNo = receiveNo;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

}
