<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'demo2.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <button type="button">点击创建A标签</button>
   <div></div>
   
   <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
   <script type="text/javascript">
   	$(function(){
	   	$("button").click(function(){
	   		$("div").append("<a href='javascript:tp()' id='1'>弹出</a>");
	   		$("a").click(function(){tp();});
	   	});
   		function tp(){alert(111);}
   	});
   	
   </script>
  </body>
</html>
