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
<H2>电子券退款：</H2>
<div class="error" style="color:#ff4500">
<c:out value="${message}"/>
</div>

 
<form:form action="ec_cashback" method="post" modelAttribute="cashbackForm">
<form:errors path="*" cssClass="error" />
<form:input type="hidden" path="goodsUrl"/>
<form:input type="hidden" path="goodsDesc"/>
<table>
<tr><td>电子劵号码：</td><td><form:input type="label" path="eccode" readonly="true"/></td></tr>
<tr><td>持劵人手机：</td><td><form:input type="label" path="cell" readonly="true"/></td></tr>
<tr><td>商品信息:</td><td><a href="<c:out value="${cashForm.goodsUrl}"/>">
<c:out value="${cashForm.goodsDesc}"/></a></td></tr>
<tr><td>商品价格:</td><td><form:input type="label" path="number" readonly="true"/></td></tr>
<tr><td>退款金额:</td><td><form:input type="label" path="cashback" readonly="true"/></td></tr>
</table><br>

<table>

<tr><td>收款人姓名:</td><td><form:input  path="name" /></td>
</tr>
<tr><td>收款人账号:</td><td><form:radiobutton path="accountType" value="wechat"/>微信（优选，处理更快）</td></tr>
<tr><td></td><td><form:radiobutton path="accountType" value="zhifubao"/>支付宝</td>
</tr>
<tr><td></td><td><form:radiobutton path="accountType" value="bank"/>银行账号</td></tr>
<tr><td>开户行信息： </td><td><form:input  path="account" /></td>
</tr>

<tr><td>
<a href="#modal" id="show_popup">
我同意退款政策</a></td><td><form:checkbox path="police" /></td></tr>
</table>

<br>
  <%--  >input type="submit" name="cashback" value="提交"/--%>
    <button class="btn btn-primary" type="submit" name="cashback">提交</button>
</form:form>
<div id="modal" class="popupContainer" style="display:none;">
	<header class="popupHeader">
		<span class="header_title">退货提示</span>
	</header>
    <section class="popupBody">
   鉴于本电子券为折扣礼品券，如您决定不提货，选择退款，我们愿意提供此项服务。退款金额，按照商品价格的65%退还给您。差额部分补偿我们的手续费、营运费用等。<br>

如您不同意此条款，请退出，重新填写送货资料，我们会按您的信息，送货给您。<br>
如您同意此条款，表示全面理解其意思，并无异议，选择不提货，并接受用产品原价65%兑换现金。
   
    </section>
    <%-- button class="modal_close">我同意</button--%> <a class="modal_close" href="#">我同意</a>
       <a href="ecard_verify" class="btn btn-primary" role="button">拒绝并返回上一页</a>
</div>
</body>
</html>