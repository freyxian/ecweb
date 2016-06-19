package ecweb.ecoupon.controller;

public class CashbackBean {
	String eccode;
	String name;
	String account;
	double number;
	String ProcessNumber;
	int state;
	
	public String getEccode() {
		return eccode;
	}
	public void setEccode(String eccode) {
		this.eccode = eccode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public double getNumber() {
		return number;
	}
	public void setNumber(double number) {
		this.number = number;
	}
	public String getProcessNumber() {
		return ProcessNumber;
	}
	public void setProcessNumber(String processNumber) {
		ProcessNumber = processNumber;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
