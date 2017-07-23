<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<!--头部-->
    <header class="publicHeader">
        <h1>超市账单管理系统</h1>
        <div class="publicHeaderR">
            <p><span>下午好！</span><span style="color: #fff21b"> ${user.userName}</span> , 欢迎你！</p>
            <a href="Login?method=out">退出</a>
        </div>
    </header>
<!--时间-->
    <section class="publicTime">
        <span id="time">2015年1月1日 11:11  星期一</span>
        <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
    </section>
<!--主体内容-->
    <section class="publicMian ">
        <div class="left">
            <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
            <nav>
                <ul class="list">
                    <li><a href="billList.jsp">账单管理</a></li>
                    <li><a href="providerList.jsp">供应商管理</a></li>
                   <c:if test="${user.userType<3}"><li id="active"><a href="userList.jsp">用户管理</a></li></c:if>
                    <li><a href="password.jsp">密码修改</a></li>
                    <li><a href="Login?method=out">退出系统</a></li>
                </ul>
            </nav>
        </div>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>用户管理页面</span>
            </div>
            <div class="search">
                <span>用户名：</span>
                <input type="text" placeholder="请输入用户名"/>
                <input type="button" value="查询"/>
                <a href="userAdd.jsp">添加用户</a>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
            <thead>
                <tr class="firstTr">
                    <th width="10%">用户编码</th>
                    <th width="20%">用户名称</th>
                    <th width="10%">性别</th>
                    <th width="10%">年龄</th>
                    <th width="10%">电话</th>
                    <th width="10%">用户类型</th>
                    <th width="30%">操作</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
            </table>
				<p><a href='javascript:' name='page'>首页</a>
				<a href='javascript:' name='page'>上一页</a>
				<a href='javascript:' name='page'>下一页</a>
				<a href='javascript:' name='page'>末页</a></p>
				<span id="page" style="font-size: 30px;"></span>
        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="javascript:" id="yes">确定</a>
            <a href="javascript:hid()" id="no">取消</a>
        </div>
    </div>
</div>

    <footer class="footer">
        版权归北大青鸟
    </footer>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script src="js/time.js"></script>
<script type="text/javascript">
function getFirstParam(){//获取当前URL第一个参数
	var paramString=location.search;
	if(paramString.indexOf('?')>-1){
		paramString=paramString.substr(1);
	}
	return paramString.split("=")[1];
} 
function sh(id){
	$(".zhezhao").show();
	$("#removeUse").show();
	$("#yes").attr("href","User?method=delete&id="+id);
}
function hid(){
	$(".zhezhao").hide();
	$("#removeUse").hide();
}
	$(function(){
		var pageIndex="";//当前页码
		var pageSize="";//页大小
		var pageCount="";//总页数
		var sqlCount="";//总记录数
		init("");
		var flag=getFirstParam();
		if(flag=="delFalse"){alert("不可以删除自己的账户")}
		if(flag=="false"){alert("您没有权限删除该用户")}
		function init(pageIndex){
			$.post("User",{"method":"select","pageIndex":pageIndex,"search":$("input:first").val()},f,"json");
		}
		function f(data){
			$("tbody").html("");//清除tbody标签
			$(data).each(function(){
                if(this.id){
                	    var sex=this.gender==1?"女":"男";
                    var userType;
                    switch (this.userType) {
					case 1:
						userType='系统管理员';
						break;
					case 2:
						userType='经理';
						break;
					case 3:
						userType='普通员工';
						break;
					}
					$("tbody").append("<tr>"
		            +"        <td>"+this.userCode+"</td>"
		            +"        <td>"+this.userName+"</td>  "
		            +"        <td>"+sex+"</td>"
		            +"        <td>"+this.age+"</td>"
		            +"        <td>"+this.phone+"</td>"
		            +"        <td>"+userType+"</td>"
		            +"        <td> "
		            +"            <a href='userView.jsp?id="+this.id+"'><img src='img/read.png' alt='查看' title='查看'/></a>"
		            +"            <a href='userUpdate.jsp?id="+this.id+"'><img src='img/xiugai.png' alt='修改' title='修改'/></a>    "
		            +"            <a href='javascript:sh("+this.id+")' class='removeUser'><img src='img/schu.png' alt='删除' title='删除'/></a> "
		            +"        </td>"
		            +"    </tr>");
               }else if(this.flag){
		         	$("tbody").html("<tr><td colspan='7' style='font-size:25px'>"+this.flag+"</td></tr>");
		         	$("#page").html("");
					$("#page").html("1/1页，共0条记录");
		       }else{
					pageIndex=this.pageIndex;
					pageSize=this.pageSize;
					pageCount=this.pageCount;
					sqlCount=this.sqlCount;
					$("#page").html("");
					$("#page").text(pageIndex+"/"+pageCount+"页，共"+sqlCount+"条记录");
				}
			});
		}
		$('p').on("click","a:eq(0)",function(){//首页
		init(1);
	});
	$('p').on("click","a:eq(1)",function(){//上一页
		if(pageIndex>1){
			init(pageIndex-1);
		}else{
			alert('没有上一页了');
		}
	});
	$('p').on("click","a:eq(2)",function(){//下一页
		if(pageIndex<pageCount){
			init(pageIndex+1);
		}else{
			alert('没有下一页了');
		}
	});
	$('p').on("click","a:eq(3)",function(){//末页
		init(pageCount);
	});
	$("input:first").focus(function(){
		$("input:first").keyup(function(event){
			if(event.keyCode==13){
				init("");
			}
		});
	});
	$("[value='查询']").click(function(){
		init("");
	});
	});
</script>
</body>
</html>