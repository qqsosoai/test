<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
            <p><span>下午好！</span><span style="color: #fff21b">${user.userName}</span>, 欢迎你！</p>
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
                    <li ><a href="billList.jsp">账单管理</a></li>
                    <li><a href="providerList.jsp">供应商管理</a></li>
                    <c:if test="${user.userType<3}"><li><a href="userList.jsp">用户管理</a></li></c:if>
                    <li id="active"><a href="password.jsp">密码修改</a></li>
                    <li><a href="Login?method=out">退出系统</a></li>
                </ul>
            </nav>
        </div>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>密码修改页面</span>
            </div>
            <div class="providerAdd">
                <form action="User" method="post">
                    <!--div的class 为error是验证错误，ok是验证成功-->
                    <input type="hidden" name="method" value="updatePassword">
                    <div class="">
                        <label for="oldPassword">旧密码：</label>
                        <input type="password" name="oldPassword" id="oldPassword" flag="false" required/>
                        <span>*请输入原密码</span>
                    </div>
                    <div>
                        <label for="newPassword">新密码：</label>
                        <input type="password" name="newPassword" id="newPassword" required/>
                        <span >*请输入新密码</span>
                    </div>
                    <div>
                        <label for="reNewPassword">确认新密码：</label>
                        <input type="password" name="reNewPassword" id="reNewPassword" required/>
                        <span >*请输入新确认密码，保证和新密码一致</span>
                    </div>
                    <div class="providerAddBtn">
                        <!--<a href="#">保存</a>-->
                        <input type="button" value="保存"/>
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
function isPassword(){//判断新密码是否与旧密码一致
	if($("#oldPassword").attr("flag")=="true"){//原密码输入是否正确
		if(/^\w{6,12}$/.test($("#newPassword").val())){//新密码符合要求
			if($("#newPassword").val()!=$("#oldPassword").val()){
				ok($("#newPassword"));
				return true;
			}else{
				error($("#newPassword"),"新密码与旧密码不能一致");
				return false;
			}
			
		}else{//新密码不符合要求
			error($("#newPassword"), "密码格式不正确，请输入由6位最多12位数字或字母组成的密码");
			return false;
		}
	}else{//原密码输入不正确
		error($("#newPassword"), "原密码输入不正确");
		return false;
	}
}
function isRePassword(){//判断确认密码
	var pass=$("#newPassword").val();//新密码
	var password=$("#reNewPassword").val();//确认密码
	if(pass==password){
		ok($("#reNewPassword"));
		return true;
	}else{
		error($("#reNewPassword"), "两次密码输入不一致");
		return false;
	}
}
function bSubmit(){//提交按钮方法
	var flag;
	if($("#oldPassword").attr("flag")=="true"){
		if(isPassword() && isRePassword()){//判断表单是否通过
			flag=true;
		}else{
			flag=false;
		}
	}else{
		error($("#oldPassword"),"原密码输入不正确");
		flag=false;
	}
	if(flag){//判断是否提交
		$.post("User",{"method":"updatePassword",
		"newPassword":$("#newPassword").val()},function(data){
			if(data.flag=="true"){//修改密码成功
				alert("修改密码成功，跳转主页面");
				window.location="welcome.jsp";
			}
		},"json");
	}
}
	$(function(){
		$("form span").css("display","none");
		//判断原密码是否正确
		$("#oldPassword").focus(function(){isFocus($("#oldPassword"), "请输入原密码")}).blur(function(){
			$.post("User",{"method":"isPassword","password":$("#oldPassword").val()},function(data){
			if(data.flag=="true"){ok($("#oldPassword"));}else{error($("#oldPassword"), "您输入的原密码不正确");}
			},"json");}
		);
		$("#newPassword").focus(function(){isFocus($("#newPassword"), "请输入由6位最多12位数字或字母组成的密码");}).blur(isPassword);
		$("#reNewPassword").focus(function(){isFocus($("#reNewPassword"), "请输入确认密码")}).blur(isRePassword);
		$("[type='button']").click(bSubmit);
		$(document).keyup(function(data){
			if(data.keyCode==13){
				bSubmit();
			}
		})
	});
</script>
</body>
</html>