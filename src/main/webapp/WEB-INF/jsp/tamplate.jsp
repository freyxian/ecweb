<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html lang="zh-CN">
  <head>  <meta charset="utf-8"> 
    <title><sitemesh:write property='title'/></title>
    <sitemesh:write property='head'/>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <%--link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"--%>
  <%--script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script--%>
  <%--script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script--%>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
  <script src="../js/jquery.min.js"></script>
  <script src="../js/bootstrap.min.js"></script>
  <script type="text/javascript" src="../js/jquery.leanModal.min.js"></script>
<style>
body { background-image: url("../pics/phone.jpg");

    	background-position: left top;
		background-color:#eeeeee;
	}
header {
	color: ff5733;
	width:100%;
	text-align:center;
	}
footer {
display: block;
	font-weight:bold;
	color:blue
	text-align:center;
		width:100%;
}
.error{
color: red;
}
table {
    width: 80%;
  /*  table-layout: fixed;*/
}
input:-moz-read-only { /* For Firefox */
    color: grey;
    font-style: italic;
}

input:read-only {
    color: grey;
    font-style: italic;
}
#lean_overlay {
    position: fixed;
    z-index:100;
    top: 0px;
    left: 0px;
    height:100%;
    width:100%;
    background: #000;
    display: none;
}
#modal {
  width: 300px;
  padding: 15px 20px;
  background: #f3f6fa;
  -webkit-border-radius: 6px;
  -moz-border-radius: 6px;
  border-radius: 6px;
  -webkit-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
  -moz-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
  box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5);
}
.required:after { content:" *"; 
color:red;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	   $("#show_popup").leanModal({ top : 200, overlay : 0.4, closeButton: ".modal_close" });
});
 </script>
  </head>
  
  <body>
     
  <div class="container">
 <header >
    <div class="page-header">
        <h2>Suntech-喜购云店<br> 电子券应用管理系统</h2><br><br><br>
        <h4 style="color:yellow;">2016年裕达月饼兑换活动</h4>
    </div></header>
    
    <sitemesh:write property='body'/>
    
     <footer>
     <br>
           <a href="http://www.xmcgo.com" class="btn btn-info" >进入喜购云</a>
           <a href="../index.html" class="btn btn-info" >返回兑换主页</a>
    </footer>
    </div>
       
  </body>
</html>