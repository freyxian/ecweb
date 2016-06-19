package ecweb.ecoupon.controller;

import java.util.Date;

public class Ecoupon {
private	  int id;
private	  String ec_code; //18位数字虚拟兑换码，非空。
private	  double number; //金额，单位，元。
private	  int state; //DEFAULT '0' COMMENT '缺省为0—初始状态，1—e-coupon处理完毕，2—xmcgo处理完毕。',
private	  int method; // '1—兑换，2—退款，缺省为空',
private	  String name; // varchar(50) DEFAULT NULL COMMENT '收货人或提款人姓名，缺省为空',
private	  String cell; // varchar(20) DEFAULT NULL COMMENT '手机号，缺省为空',
private	  String address; //varchar(200) DEFAULT NULL COMMENT '地址，缺省为空',
private	  String postcode; //varchar(20) DEFAULT NULL COMMENT '邮编，缺省为空',
private	  int goods_id; //int(11) NOT NULL COMMENT '商品编号，非空。',
private	  String goods_des; //varchar(50) NOT NULL COMMENT '产品描述，非空。',
private	  String goods_url; //varchar(50) NOT NULL COMMENT '产品网址，非空。',
private	  String wechat; // varchar(50) NOT NULL COMMENT '微信号，缺省为空',
private	  double cashback; //int(11) NOT NULL COMMENT '退款金额，缺省为空',
private	  Date idate; // datetime DEFAULT NULL COMMENT '数据录入日期',
private	  Date edate; // datetime DEFAULT NULL COMMENT 'e-coupon处理日期',
private	  Date xdate; // datetime DEFAULT NULL COMMENT 'xmcgo处理日期',
private	  String ProcessNumber; //varchar(8) DEFAULT NULL COMMENT '处理单号码，8位',
private   String passwd; //varchar(50) default null comment '密码，手机验证码',

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getEc_code() {
	return ec_code;
}
public void setEc_code(String ec_code) {
	this.ec_code = ec_code;
}
public double getNumber() {
	return number;
}
public void setNumber(double number) {
	this.number = number;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public int getMethod() {
	return method;
}
public void setMethod(int method) {
	this.method = method;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
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
public int getGoods_id() {
	return goods_id;
}
public void setGoods_id(int goods_id) {
	this.goods_id = goods_id;
}
public String getGoods_des() {
	return goods_des;
}
public void setGoods_des(String goods_des) {
	this.goods_des = goods_des;
}
public String getGoods_url() {
	return goods_url;
}
public void setGoods_url(String goods_url) {
	this.goods_url = goods_url;
}
public String getWechat() {
	return wechat;
}
public void setWechat(String wechat) {
	this.wechat = wechat;
}
public double getCashback() {
	return cashback;
}
public void setCashback(double cashback) {
	this.cashback = cashback;
}
public String getPasswd() {
	return passwd;
}
public void setPasswd(String passwd) {
	this.passwd = passwd;
}
public Date getIdate() {
	return idate;
}
public void setIdate(Date idate) {
	this.idate = idate;
}
public Date getEdate() {
	return edate;
}
public void setEdate(Date edate) {
	this.edate = edate;
}
public Date getXdate() {
	return xdate;
}
public void setXdate(Date xdate) {
	this.xdate = xdate;
}
public String getProcessNumber() {
	return ProcessNumber;
}
public void setProcessNumber(String processNumber) {
	ProcessNumber = processNumber;
}
}
