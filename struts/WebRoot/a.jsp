<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'a.jsp' starting page</title>
    
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
    <table>
    	<tr>
    		<td>用户账号</td>
    		<td>用户密码</td>
    		<td>行号</td>
    		<td>下标</td>
    	</tr>
    
    <s:iterator var="user" value="users" status="st">
    	<tr 
    		<s:if test="#st.even">bgcolor="red"</s:if>
    		<s:else>bgcolor="blue"</s:else>
    	>
    		<td><s:property value="#user.value.username"/></td>
    		<td><s:property value="#user.value.password"/></td>
    		<td><s:property value="#st.count"/></td>
    		<td><s:property value="#st.index"/></td>
    	</tr>
    </s:iterator>
    </table>
    
    <s:form theme="xhtml">
    	<s:textfield label="用户名"></s:textfield>
    	<s:password label="用户密码"></s:password>
    </s:form>
    <s:debug/>
  </body>
</html>
