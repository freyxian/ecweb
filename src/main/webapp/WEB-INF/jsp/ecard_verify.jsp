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
<H2>电子劵验证</H2>
<div class="message" style="color:#ff4500">
<c:out value="${message}"/>
</div>
 
<form:form action="ecard_verify" method="post" modelAttribute="verifyForm">
<form:errors path="*"  />
<table>
<tr><td>电子劵号码:</td><td><form:input  path="eccode" /></td>
<td><form:errors path="eccode"/></td></tr>
<tr><td>密码:</td><td><form:input  path="passwd" /></td>
<td><form:errors path="passwd"/></td></tr>
<tr><td>手机:</td><td><form:input  path="cell" /></td>
<td><form:errors path="cell"/></td></tr>

</table>

    <input type="submit" name="verifyEcard" value="验证密码"/>

        <input type="submit" name="cancell" value="取消"/>
</form:form>
</body>
</html>