<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html lang="zh-CN">
  <head>  <meta charset="utf-8"> 
    <title><sitemesh:write property='title'/></title>
    <sitemesh:write property='head'/>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
body { background-color:#eeeeee;}
page-header {border-style:solid;
	border-width:3px;
	border-color:black;
	text-align:center;
	width:410px;
	}
footer {font-size:.1.1em;
	font-weight:bold;
	color:blue
}
</style>
  </head>
  
  <body>
     
  <div class="container">
  <header>
   <div class="page-header">
        <h3>裕达月饼，喜购云特售</h3>
    </div></header>
    
    <sitemesh:write property='body'/>
    
     <footer>
     <div class="container">
           <a href="http://www.xmcgo.com" class="btn btn-info pull-center" >返回喜购云</a>
    </div>
    </footer>
    </div>
       
  </body>
</html>