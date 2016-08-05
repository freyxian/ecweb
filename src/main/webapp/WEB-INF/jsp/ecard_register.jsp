<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html><head>
<script>

function changeImg(){  
    var imgSrc = $("#validateCode");  
    var src = imgSrc.attr("src");  
    imgSrc.attr("src",chgUrl(src));  
}  
//时间戳  
//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳  
function chgUrl(url){  
    var timestamp = (new Date()).valueOf();  
    url = url.substring(0,17);  
    if((url.indexOf("&")>=0)){  
        url = url + "×tamp=" + timestamp;  
    }else{  
        url = url + "?timestamp=" + timestamp;  
    }  
    return url;  
}  
</script>
</head>
<body>
<H2>密码验证</H2>
<div class="message" style="color:#ff4500">
<c:out value="${message}"/>
</div>
 
<form:form action="ecard_register" method="post" modelAttribute="registerForm">
<form:errors path="*" cssClass="error" />
<table>
<tr><td style="width:50%" >电子劵号码:</td><td><form:input  path="eccode" /></td>
<td class="required"><form:errors path="eccode" cssClass="error"/></td></tr>
<tr><td style="width:50%" >有效密码:</td><td><form:input  path="oldpasswd" /></td>
<td class="required"><form:errors path="oldpasswd" cssClass="error"/></td></tr>
<tr><td style="width:50%" >手机:</td><td><form:input  path="cell" /></td>
<td class="required"><form:errors path="cell" cssClass="error"/></td></tr>
<tr><td style="width:50%" >姓名（昵称）：</td><td><form:input  path="name" /></td>
<td class="required"><form:errors path="name" cssClass="error"/></td></tr>

<tr><td style="width:50%">微信号（选填）</td><td><form:input  path="wechat" /></td>
<td><form:errors path="wechat" cssClass="error"/></td></tr>
<tr><td style="width:50%" >手机收到的6位新密码</td><td><form:input  path="newpasswd" /></td>
<td class="required"><form:errors path="newpasswd" cssClass="error"/></td></tr>

<%--<tr><td style="width:50%"><img src="validatecode.jpg" name="validateCode" id="validateCode"> 
<a href="javascript:changeImg();">换一个</a>  </td>
<td><form:input  path="captcha" /></td>
</tr>--%>

</table>
<br>
  <%-- >input type="submit" name="sendpwtocell" value="发送手机密码"/--%>
     <button class="btn btn-primary" type="submit" name="sendpwtocell">发送新密码</button>
    <%--input type="submit" name="verifyPWcell" value="验证密码并注册"/--%>
        <button class="btn btn-primary" type="submit" name="verifyPWcell">修改密码</button>
      <%--input type="submit" name="resetPW" value="密码重设"/--%>
          <button class="btn btn-primary" type="submit" name="resetPW">密码重设</button>

</form:form>
<h3 style="color:white;line-height:150%;">使  用  指  南</h3><br>
<h4 style="color:white;line-height:150%;">&#9899;修改密码<br>
1)	请依次填写资料<br>
2)	初始密码为必填项，如没有，请向销售商获取。<br>
3)	填写好验证码后，点击“发送新密码”<br>
4)	您手机收到的6位数字，即新的有效密码，填写在“手机收到的6位新密码”处，点击“修改密码”。

密码修改完成，请保管好新密码以及原注册信息（姓名、微信号、手机号）。<br>
<br>
&#9899;密码重设<br>
1)	输入原注册信息: 电子劵号码, 手机，姓名，微信号。无需填初始密码。<br>
2)	点击“发送新密码”<br>
3)	填写手机收到的新密码，点击“密码重设”。<br>
密码修改完成，请保管好新密码（即手机新收到的6位数字）

</h4>
</body>
</html>