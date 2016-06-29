package ecweb.ecoupon.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class VerifyForm {
	@NotNull 
	@Size(min = 2,max=18, message = "请准确输入电子劵号码。")
	private String eccode;
	

	@Pattern(regexp="^(|[0-9]{6})$",message = "密码，6个数字。")
	private String passwd;
	
	@NotNull 
	@Pattern(regexp="\\d{11}",message = "手机号，11位数字")
	private String cell;
	
	private String flag_cd; //flag for pickup order or cash back, default is pickup order
	
	public VerifyForm(){
		flag_cd="D";
	}
	
	public String getFlag_cd() {
		return flag_cd;
	}

	public void setFlag_cd(String flag_cd) {
		this.flag_cd = flag_cd;
	}

	public String getEccode() {
		return eccode;
	}

	public void setEccode(String eccode) {
		this.eccode = eccode;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}


}
