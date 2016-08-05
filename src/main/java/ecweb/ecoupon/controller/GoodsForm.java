package ecweb.ecoupon.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class GoodsForm {

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getGoodsDesc() {
	return goodsDesc;
}
public void setGoodsDesc(String goodsDesc) {
	this.goodsDesc = goodsDesc;
}
public String getGoodsUrl() {
	return goodsUrl;
}
public void setGoodsUrl(String goodsUrl) {
	this.goodsUrl = goodsUrl;
}
public String getCell() {
	return cell;
}
public void setCell(String cell) {
	this.cell = cell;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPostcode() {
	return postcode;
}
public void setPostcode(String postcode) {
	this.postcode = postcode;
}
public String getEccode() {
	return eccode;
}
public void setEccode(String eccode) {
	this.eccode = eccode;
}
private String goodsDesc;
private String goodsUrl;
private double number;
private String contactNum;
public String getContactNum() {
	return contactNum;
}
public void setContactNum(String contactNum) {
	this.contactNum = contactNum;
}
public double getNumber() {
	return number;
}
public void setNumber(double number) {
	this.number = number;
}
@NotNull 
@Size(min = 2,max=50, message = "姓名，至少2个字符")
String name;

@NotNull
@Pattern(regexp="\\d{11}",message = "手机号，11位数字")
private String cell; 
@NotNull
@Size(min = 1,max=200, message = "地址，非空")
private String address; 
@NotNull
@Pattern(regexp="\\d{6}",message = "邮编，6位数字")
private String postcode;

private String eccode;
@NotNull 
@Size(min = 2,max=50, message = "姓名，至少2个字符")
private String contact;


public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
@Override
public String toString(){
	String s=eccode+":"+name+":"+cell+":"+address+":"+postcode;
	return s;
}
}
