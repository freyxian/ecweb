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
<h2>报关订单查询修改</h2><c:out value="${message}"/>
<form:form action="order_input" method="post" modelAttribute="oiform">
  定单号:<form:input  path="orderId" /> 
  <input type="submit" name="OrderSearch" value="查询">
  <input type="submit" name="NewOrder" value="新建">
    <input type="submit" name="ModifyOrder" value="修改">
      <input type="submit" name="DeleteOrder" value="删除">
  <table style="width:80%"><tr>
  <td>日期：<form:input path="orderDate"/></td>
  <td>定货人：<form:input path="customerId"/></td>
  </tr>
  <tr>
  <td>包装材料:<form:input path="packingMaterial" /></td>
  <td>入货仓库:<form:input path="warehouseId"/></td>
  </tr>
  <tr><td>TPL：<form:input path="whRefTpl"/></td>
  <td>定单类型：<form:input path="orderType"/></td>
  </tr>
  <tr><td>electriccode:<form:input path="electriccode"/></td>
  <td>deliveryCode:<form:input path="deliveryCode"/></td>
  </tr>
  <tr><td>cbepcomcode:<form:input path="cbepcomcode"/></td>
  <td>备注：<form:input path="notes"/></td>
  </tr>
  </table>
  	<br>
收货人地址：<br>
<table style="width:80%">
<tr><td>姓名：<form:input path="name"/></td>
<td>收货方式：<form:input path="receiveType"/></td>
</tr>

<tr><td>收货号码：<form:input path="receiveNo"/></td>
<td>手机号：<form:input path="mobilePhone"/></td></tr>

<tr><td>电话：<form:input path="phone"/></td>
<td>省份：<form:input path="province"/></td></tr>

<tr><td>城市：<form:input path="city"/></td>
<td>地区：<form:input path="district"/></td>
<td>邮编：<form:input path="postcode"/></td></tr>
</table>
<br>货物目录：<button>新增货物</button><button>删除货物</button>
<br>
<table style="width:80%" border="1"> 
  <tr>
    <th>货物编号</th>
    <th >数量</th>
        <th >价格</th>
  </tr>
  
  <c:forEach items="${oiform.orderItemList}" var="item" varStatus="status">
<tr>
<td align="center">${status.count}</td>
<td><input type="text" name="orderItemList[${status.index}].goodId" value="${item.goodId}" ></td>
<td><input type="text" name="orderItemList[${status.index}].amount" value="${item.amount}" ></td>
<td><input type="text" name="orderItemList[${status.index}].price" value="${item.price}" ></td>

</tr>
</c:forEach>
</table>
	
 
</form:form> 
</body>
</html>