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
<H2>电子券购物：</H2>

 
<form:form action="ec_cashback" method="post" modelAttribute="cashForm">
<table>
<tr><td>电子劵号码：</td><td><form:input type="label" path="eccode" readonly="true"/></td></tr>
<tr><td>持劵人手机：</td><td><form:input type="label" path="cell" readonly="true"/></td></tr>
</table><br>
<form:errors path="*"  />

<table>
<tr><td>商品信息:</td><td><a href="<c:out value="${cashForm.goodsUrl}"/>">
<c:out value="${cashForm.goodsDesc}"/></a></td></tr>
<tr><td>商品价格:</td><td><form:input type="label" path="number" readonly="true"/></td></tr>
<tr><td>收款人姓名:</td><td><form:input  path="name" /></td>
<td><form:errors path="name"/></td></tr>
<tr><td>收款人账号:</td><td><form:input  path="account" /></td>
<td><form:errors path="account"/></td></tr>

<tr><td>退款金额:</td><td><form:input type="label" path="cashback" readonly="true"/></td></tr>
</table>
  <%--  >input type="submit" name="cashback" value="提交"/--%>
    <button class="btn btn-primary" type="submit" name="cashback">提交</button>
</form:form>
</body>
</html>