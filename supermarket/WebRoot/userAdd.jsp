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
                <c:if test="${user.userType<3}"><li id="active"><a href="userList.jsp">用户管理</a></li></c:if>
                <li><a href="password.jsp">密码修改</a></li>
                <li><a href="Login?method=out">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
            <form action="User" method="post">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <input type="hidden" name="method" value="insert">
                <div class="">
                    <label for="userId">用户账号：</label>
                    <input type="text" name="userId" id="userId"/>
                    <span>*请输入用户编码，且不能重复</span>
                </div>
                <div>
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName"/>
                    <span >*请输入用户名称</span>
                </div>
                <div>
                    <label for="userpassword">用户密码：</label>
                    <input type="text" name="userpassword" id="userpassword"/>
                    <span>*密码长度必须大于6位小于20位</span>

                </div>
                <div>
                    <label for="userRemi">确认密码：</label>
                    <input type="text" name="userRemi" id="userRemi"/>
                    <span>*请输入确认密码</span>
                </div>
               <div>
                    <label >用户性别：</label>
                    <select name="sex" id='sex'>
                        <option value="2">男</option>
                        <option value="1">女</option>
                    </select>
                </div>
               <div>
                    <label for="date">出生日期：</label>
                    <input type="date" name="date" id="date" placeholder="2016年2月1日" min="1900-01-01"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="userphone">用户电话：</label>
                    <input type="text" name="userphone" id="userphone"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="userAddress">用户地址：</label>
                    <input type="text" name="userAddress" id="userAddress" placeholder="北京"/>
               		<span></span>
                </div>
                <div>
                    <label>用户类别：</label>
                    <c:if test="${user.userType==1}"><input type="radio" name="userlei" value="1"/>管理员
                    <input type="radio" name="userlei" value="2"/>经理</c:if>
                    <input type="radio" name="userlei" value="3" checked/>普通用户
                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.html">返回</a>-->
                    <input type="submit" value="保存"/>
                    <input type="button" value="返回" onclick="history.back(-1)"/>
                </div>
            </form>
        </div>

    </div>
</section>
<footer class="footer">
    版权归北大青鸟
</footer>
<script src="js/time.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/birthday.js"></script>
<script type="text/javascript">
function ok(Element){//验证正确的提示
	$(Element).attr("flag","true").parent().removeClass().addClass("ok");
	$(Element).next().css("display","none");
};
function error(Element,String){//验证错误的提示
	$(Element).attr("flag","false").parent().removeClass().addClass("error");
	$(Element).next().css({"display":"inline"}).html(String);
};
function isFocus(Element,String){//获取焦点的提示
	Element.parent().removeClass();
	Element.next().css({"display":"inline"}).html(String);
}
function isUserName(){//验证用名称
	if($("#userName").val()!=""){ok($("#userName"));return true;}else{error($("#userName"), "用户名称不能为空");return false;}
}
function isDate(){//验证出生日期
	if($("#date").val()!=""){ok($("#date"));return true;}else{error($("#date"), "出生日期不匹配");return false;}
}
function isPhone(){//验证手机号码
	if(/^\d{11}$/.test($("#userphone").val())){ok($("#userphone"));return true;}else{error($("#userphone"), "请输入正确的电话号码");return true;}
}
function isAddress(){//验证用户地址
	if($("#userAddress").val()!=""){ok($("#userAddress"));return true;}else{error($("#userAddress"), "地址不能为空");return false;}
}
function isPassword(){//验证用户密码
	if(/^\w{6,12}$/.test($("#userpassword").val())){ok($("#userpassword"));return true;}else{error($("#userpassword"), "密码由数字字符组成最少6位最多12位");return false;}
}
function isRePassword(){//验证用户确认密码
	var password=$("#userpassword").val();
	var rePassword=$("#userRemi").val();
	if(password==rePassword){ok($("#userRemi"));return true;}else{error($("#userRemi"), "两次密码输入不一致");return false;}
}
function isUserCode(){//验证用户账号
	if(/^\w{6,10}$/.test($("#userId").val())){
		$.post("User",{"method":"code","code":$("#userId").val()},function(data){
			if(data.flag=="true"){ok($("#userId"));}else{error($("#userId"),"账号已存在请重新输入");}
		},"json");
	}else{
		error($("#userId"),"用户账号由6位字符数字组成");
	}
}
$(function(){

	$("form span").css("display","none");
	$("#userName").focus(function(){//用户名称
		isFocus($("#userName"), "请输入用户名称");
	}).blur(isUserName);
	$("#date").focus(function(){isFocus($("#date"), "请输入出生日期，规格yyyy-MM-dd")}).blur(isDate);//出生日期
	$("#userphone").focus(function(){isFocus($("#userphone"), "请输入电话号码")}).blur(isPhone);//电话号码
	$("#userAddress").focus(function(){isFocus($("#userAddress"), "请输入地址")}).blur(isAddress);//用户地址
	$("#userpassword").focus(function(){isFocus($("#userpassword"), "请输入密码由6位，最多12位数字字母或下划线组成")}).blur(isPassword);
	$("#userRemi").focus(function(){isFocus($("#userRemi"), "请重复输入密码")}).blur(isRePassword);
	$("#userId").focus(function(){isFocus($("#userId"),"请输入用户账号，由6位数字或字符组成");}).blur(isUserCode);
	$("form").submit(function(){
		if($("#userId").attr("flag")=="true"){//代表用户账号验证通过
			if(isUserName() && isPassword() && isRePassword() && isDate() &&isPhone() && isAddress()){
				return true;
			}
		}else{
			error($("#userId"), "用户已存在或输入不规范");
		}
		return false;
	});
});
</script>
</body>
</html>