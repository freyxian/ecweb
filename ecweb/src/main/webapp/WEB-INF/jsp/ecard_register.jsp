<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html><head><meta charset="utf-8" />
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
<H2>持卡人注册</H2>
<div class="message" style="color:#ff4500">
<c:out value="${message}"/>
</div>
 
<form:form action="ecard_register" method="post" modelAttribute="registerForm">
<form:errors path="*" cssClass="error" />
<table>
<tr><td>电子劵号码:</td><td><form:input  path="eccode" /></td>
<td><form:errors path="eccode" cssClass="error"/></td></tr>
<tr><td>有效密码:</td><td><form:input  path="oldpasswd" /></td>
<td><form:errors path="oldpasswd" cssClass="error"/></td></tr>
<tr><td>手机:</td><td><form:input  path="cell" /></td>
<td><form:errors path="cell" cssClass="error"/></td></tr>
<tr><td>姓名:</td><td><form:input  path="name" /></td>
<td><form:errors path="name" cssClass="error"/></td></tr>

<tr><td>微信号:</td><td><form:input  path="wechat" /></td>
<td><form:errors path="wechat" cssClass="error"/></td></tr>
<tr><td>手机短信接收密码:</td><td><form:input  path="newpasswd" /></td>
<td><form:errors path="newpasswd" cssClass="error"/></td></tr>
<tr><td><img src="validatecode.jpg" name="validateCode" id="validateCode"> 
<a href="javascript:changeImg();">看不清？</a>  </td>
<td><form:input  path="captcha" /></td>
</tr>
</table>
<br>
  <%-- >input type="submit" name="sendpwtocell" value="发送手机密码"/--%>
     <button class="btn btn-primary" type="submit" name="sendpwtocell">发送手机密码</button>
    <%--input type="submit" name="verifyPWcell" value="验证密码并注册"/--%>
        <button class="btn btn-primary" type="submit" name="verifyPWcell">验证密码并注册</button>
      <%--input type="submit" name="resetPW" value="密码重设"/--%>
          <button class="btn btn-primary" type="submit" name="resetPW">密码重设</button>

</form:form>
</body>
</html>