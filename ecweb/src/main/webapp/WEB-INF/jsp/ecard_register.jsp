<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html><head><meta charset="utf-8" />
</head>
<body>
<H2>持卡人注册</H2>
<div class="message" style="color:#ff4500">
<c:out value="${message}"/>
</div>
 
<form:form action="ecard_register" method="post" modelAttribute="registerForm">
<form:errors path="*"  />
<table>
<tr><td>电子劵号码:</td><td><form:input  path="eccode" /></td>
<td><form:errors path="eccode"/></td></tr>
<tr><td>有效密码:</td><td><form:input  path="oldpasswd" /></td>
<td><form:errors path="oldpasswd"/></td></tr>
<tr><td>手机:</td><td><form:input  path="cell" /></td>
<td><form:errors path="cell"/></td></tr>
<tr><td>姓名:</td><td><form:input  path="name" /></td>
<td><form:errors path="name"/></td></tr>

<tr><td>微信号:</td><td><form:input  path="wechat" /></td>
<td><form:errors path="wechat"/></td></tr>
<tr><td>手机短信接收密码:</td><td><form:input  path="newpasswd" /></td>
<td><form:errors path="newpasswd"/></td></tr>

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