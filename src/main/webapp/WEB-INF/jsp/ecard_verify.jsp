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
<H2>电子券提货-退款-查询</H2>
<div class="message" style="color:#ff4500">
<c:out value="${message}"/>
</div>
 
<form:form action="ecard_verify" method="post" modelAttribute="verifyForm">
<form:errors path="*" cssClass="error" />
<table>
<tr><td>电子劵号码:</td><td><form:input  path="eccode" /></td>
<td class="required"><form:errors path="eccode" cssClass="error"/></td></tr>
<tr><td>密码:</td><td><form:input  path="passwd" /></td>
<td class="required"><form:errors path="passwd" cssClass="error"/></td></tr>
<tr><td>手机:</td><td><form:input  path="cell" /></td>
<td class="required"><form:errors path="cell" cssClass="error"/></td></tr>
<tr>
<td>兑换<form:radiobutton path="flag_cd" value="D"></form:radiobutton></td>
<td>&#160&#160退款<form:radiobutton path="flag_cd" value="C"></form:radiobutton></td>
</tr>
</table>
<br>
    <%-- input type="submit" name="verifyEcard" value="验证密码"/--%>
        <button class="btn btn-primary" type="submit" name="verifyEcard">验证密码</button>

</form:form>
</body>
</html>