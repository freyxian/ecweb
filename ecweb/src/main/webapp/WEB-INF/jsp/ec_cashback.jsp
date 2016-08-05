<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html><head>

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
<tr><td>商品信息:</td><td><a href="<c:out value="${cashbackForm.goodsUrl}"/>">
<c:out value="${cashbackForm.goodsDesc}"/></a></td></tr>
<tr><td>商品价格:</td><td><form:input type="label" path="number" readonly="true"/></td></tr>
<tr><td>退款金额:</td><td><form:input type="label" path="cashback" readonly="true"/></td></tr>
</table><br>

<table>

<tr><td>收款人姓名:</td><td><form:input  path="name" /></td>
<td class="required"><form:errors path="name" cssClass="error"/></td>
</tr>
<tr><td>收款人联系电话:</td><td><form:input  path="contactNum" /></td>
<td class="required"><form:errors path="contactNum" cssClass="error"/></td>
</tr>
<tr><td>收款账号类型:</td><td><form:radiobutton path="accountType" value="wechat"/>微信</td></tr>
<tr><td></td><td><form:radiobutton path="accountType" value="zhifubao"/>支付宝</td>
</tr>
<tr><td></td><td><form:radiobutton path="accountType" value="bank"/>银行账号</td></tr>
<tr><td>账号信息： </td><td><form:input  path="account" /></td>
<td class="required"><form:errors path="account" cssClass="error"/></td>
</tr>

<tr><td>
<a href="#modal" id="show_popup">
我同意退款政策</a></td><td><form:checkbox path="police" />（必选。不同意请返回主页）</td></tr>
</table>
<br>
  <%--  >input type="submit" name="cashback" value="提交"/--%>
    <button class="btn btn-primary" type="submit" name="cashback">提交</button>
</form:form>

<h3 style="color:white;line-height:150%;">使  用  指  南</h3><br>
<h4 style="color:white;line-height:150%;">1.	账号信息：根据您选择的退款方式，填写微信账号，或支付宝账号，或银行账号及开户行信息<br>
2.	收款人姓名：请填写收款账户真实姓名；<br>
3.	如何找到自己的微信号：主页右下角-我-上面图标旁：微信号<br>
4.	如何找到自己支付宝账号：主页右下角-￥我的-上面昵称下的手机号，就是支付宝账号。<br>
5.	提交后，系统会赋予唯一的退款单号。请务必记住！<br>
</h4>

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