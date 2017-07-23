<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
        <p><span>下午好！</span><span style="color: #fff21b">${user.userName}</span> , 欢迎你！</p>
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
                <li ><a href="providerList.jsp">供应商管理</a></li>
                <li id="active"><a href="userList.jsp">用户管理</a></li>
                <li><a href="password.jsp">密码修改</a></li>
                <li><a href="Login?method=out">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户信息查看页面</span>
        </div>
        <div class="providerView">
            
        </div>
    </div>
</section>
<footer class="footer">
    版权归北大青鸟
</footer>
<script src="js/time.js"></script>
<script type="text/javascript" src="js/dateFormat.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	function getFirstParam(){//获取当前URL第一个参数
	var paramString=location.search;
	if(paramString.indexOf('?')>-1){
		paramString=paramString.substr(1);
	}
	return paramString.split("=")[1];
} 
$(function(){
	var id=getFirstParam();
	$.post("User",{"method":"view","id":id},function(data){
		$(".providerView").html("");
		$(data).each(function(){
			var sex=this.gender==1?"女":"男";//判断用户性别
            var userType;
            switch (this.userType) {//判断用户类型
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
			$(".providerView").append("<p><strong>用户编号：</strong><span>"+this.userCode+"</span></p>   "
       +"     <p><strong>用户名称：</strong><span>"+this.userName+"</span></p>                               "
       +"     <p><strong>用户性别：</strong><span>"+sex+"</span></p>                                "
       +"     <p><strong>出生日期：</strong><span>"+format(this.birthday)+"</span></p>                        "
       +"     <p><strong>用户电话：</strong><span>"+this.phone+"</span></p>                      "
       +"     <p><strong>用户地址：</strong><span>"+this.address+"</span></p>                               "
       +"     <p><strong>用户类别：</strong><span>"+userType+"</span></p>                               "
       +"     <a href='userList.jsp'>返回</a>");
		});
	},"json");
});
</script>
</body>
</html>