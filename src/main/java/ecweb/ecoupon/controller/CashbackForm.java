package ecweb.ecoupon.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CashbackForm {
	private String eccode;
	private String cell; 
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	private String goodsDesc;
	private String goodsUrl;
	private double number;
	private double cashback;
	public double getCashback() {
		return cashback;
	}
	public void setCashback(double cashback) {
		this.cashback = cashback;
	}
	@NotNull 
	@Size(min = 2,max=50, message = " 账号，至少2个字符")
	private String account;
	@NotNull 
	@Size(min = 2,max=50, message = "姓名，至少2个字符")
	private String name;
	@NotNull 
	@Size(min = 2,max=50, message = "联系方式，至少2个字符")
	private String contact;
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEccode() {
		return eccode;
	}
	public void setEccode(String eccode) {
		this.eccode = eccode;
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
	public double getNumber() {
		return number;
	}
	public void setNumber(double number) {
		this.number = number;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
