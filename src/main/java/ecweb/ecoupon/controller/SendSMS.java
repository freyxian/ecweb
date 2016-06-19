package ecweb.ecoupon.controller;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 *  短信发送API(http://api.smsbao.com/):
http://api.smsbao.com/sms?u=USERNAME&p=PASSWORD&m=PHONE&c=CONTENT
USERNAME：在本短信平台注册的用户名
PASSWORD：平台登录密码MD5后的值(32位，不区分大小写)
PHONE：目标手机号码，多个手机号码用半角逗号分割
CONTENT：发送内容，采用UTF-8 URL ENCODE
返回 '0' 视为发送成功，其他内容为错误提示内容

 错误代码表
30：密码错误
40：账号不存在
41：余额不足
42：帐号过期
43：IP地址限制
50：内容含有敏感词
51：手机号码不正确
 */

public class SendSMS {
    private static final Logger logger = LoggerFactory.getLogger(SendSMS.class);
    
	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getHttpUrl() {
		return HttpUrl;
	}

	public void setHttpUrl(String httpUrl) {
		HttpUrl = httpUrl;
	}

	private String Username = "userame"; //在短信宝注册的用户名
	private String Password = "password"; //在短信宝注册的密码
	private String HttpUrl = "http://api.smsbao.com/sms";
	
	public static String request(String httpUrl, String httpArg) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		httpUrl = httpUrl + "?" + httpArg;

		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = reader.readLine();
			if (strRead != null) {
				sbf.append(strRead);
				while ((strRead = reader.readLine()) != null) {
					sbf.append("\n");
					sbf.append(strRead);
				}
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String md5(String plainText) {
		StringBuffer buf = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

	public static String encodeUrlString(String str, String charset) {
		String strret = null;
		if (str == null)
			return str;
		try {
			strret = java.net.URLEncoder.encode(str, charset);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return strret;
	}
	
	public void sendSMS(String Phone_number, String messages){
		StringBuffer httpArg = new StringBuffer();
		httpArg.append("u=").append(Username).append("&");
		httpArg.append("p=").append(md5(Password)).append("&");
		httpArg.append("m=").append(Phone_number).append("&");
		httpArg.append("c=").append(encodeUrlString(messages, "UTF-8"));
		
		String result = request(HttpUrl,httpArg.toString());
		logger.debug("send to "+Phone_number+"SMS return: "+result+"message:"+messages);
		
		if(!result.equals("0")){
			logger.debug("send to "+Phone_number+"SMS return: "+result);
			//need raise exception here
		}
	}
}
