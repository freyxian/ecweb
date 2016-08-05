package ecweb.ecoupon.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterForm {
	@NotNull 
	@Size(min = 2,max=18, message = "请准确输入电子劵号码。")
	private String eccode;
	
	@NotNull
	//@Pattern(regexp="^(|[0-9]{6})$",message = "密码，6个数字。")  //can be empty
	//@Pattern(regexp="\\d{6}",message = "密码，6个数字。")
	private String oldpasswd;
	
	@NotNull 
	@Pattern(regexp="\\d{11}",message = "手机号，11位数字")
	private String cell;
	
	private String wechat;
	
	@NotNull 
	@Pattern(regexp="\\d{6}",message = "密码，6个数字。")
	private String newpasswd;
	@NotNull 
	@Size(min = 2,max=50, message = "持劵人姓名。")
	private String name;
	
	private String captcha;
	
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEccode() {
		return eccode;
	}
	public void setEccode(String eccode) {
		this.eccode = eccode;
	}
	public String getOldpasswd() {
		return oldpasswd;
	}
	public void setOldpasswd(String oldpasswd) {
		this.oldpasswd = oldpasswd;
	}
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getNewpasswd() {
		return newpasswd;
	}
	public void setNewpasswd(String newpasswd) {
		this.newpasswd = newpasswd;
	}
}
