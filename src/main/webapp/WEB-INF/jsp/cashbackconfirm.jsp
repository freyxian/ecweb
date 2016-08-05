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
<H1>
<div class="message" style="color:#ff4500">
<h4>* 您已经完成退款第一步！请用笔记录或截图记录处理单编号：<c:out value="${message}"/>;<br><br>
* 第二步：退款资料确认。请关注喜购云公众号：XM-CGO；点击左下角键盘栏，输入“TK1”，按提示操作。<br><br>
我们在2工作日内退款至你指定账户。</h4>
</div>
</H1>

</body>
</html>