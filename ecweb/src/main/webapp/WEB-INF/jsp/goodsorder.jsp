<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html><head><meta charset="utf-8">
</head>
<body>
<H2>电子券购物：</H2>

 
<form:form action="ec_input" method="post" modelAttribute="goodsForm">
<table>
<tr><td>电子劵号码：</td><td><form:input readonly="true" path="eccode" /></td></tr>
<tr><td>持劵人手机：</td><td><form:input readonly="true" path="cell" /></td></tr>
</table><br>
<form:errors path="*"  />

<table>
<tr><td>收货人姓名:</td><td><form:input  path="name" /></td>
<td><form:errors path="name"/></td></tr>
<tr><td>联系方式:</td><td><form:input  path="contact" /></td>
<td><form:errors path="contact"/></td></tr>
<tr><td>收货人地址:</td><td><form:input  path="address" /></td>
<td><form:errors path="address"/></td></tr>
<tr><td>收货人邮编:</td><td><form:input  path="postcode" /></td>
<td><form:errors path="postcode"/></td></tr>
<tr><td>商品信息:</td><td><a href="<c:out value="${goodsform.goodsUrl}"/>">
<c:out value="${goodsform.goodsDesc}"/></a></td></tr>
<tr><td>商品价格:</td><td><form:input readonly="true" path="number" /></td></tr>
</table>
  <input type="submit" name="OrderInput" value="提交"/>
</form:form>
</body>
</html>