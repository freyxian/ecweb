package ecweb.ecoupon.util;

import java.util.Calendar;
import java.util.Date;

public class ShortMessage implements java.io.Serializable{
private String message;
private Date createdTime;

public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public Date getCreatedTime() {
	return createdTime;
}
public void setCreatedTime(Date createdTime) {
	this.createdTime = createdTime;
}
 
public ShortMessage(){
	createdTime=Calendar.getInstance().getTime();
}
}
