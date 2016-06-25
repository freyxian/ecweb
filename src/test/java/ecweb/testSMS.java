package ecweb;

import ecweb.ecoupon.controller.SendSMS;
import ecweb.ecoupon.util.PassWDGenerate;
import ecweb.ecoupon.util.SMSException;

public class testSMS {
	public static void main(String[] args) throws SMSException {
		// TODO Auto-generated method stub
		SendSMS sms = new SendSMS();
		sms.setHttpUrl("http://api.smsbao.com/sms");
		sms.setUsername("benyu123");
		sms.setPassword("benyu123");
		String messages="【喜购云店】测试，你的验证码是439809";
		sms.sendSMS("12345678901", messages);
		sms.sendSMS("13602839344", messages); //朱云峰的国内手机号
	}
}
