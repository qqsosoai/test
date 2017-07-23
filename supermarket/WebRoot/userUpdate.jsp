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
            <span>用户管理页面 >> 用户修改页面</span>
        </div>
        <div class="providerAdd">
            <form action="User" method="post">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <input type="hidden" id="id" name="id" >
                <input type="hidden" name="method" value='update'>
                <div>
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName" placeholder="韩露"/>
                    <span >*</span>
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
                    <input type="text" name="userphone" id="userphone" placeholder="13533667897"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="userAddress">用户地址：</label>
                    <input type="text" name="userAddress" id="userAddress" placeholder="北京"/>
                </div>
                <c:if test="${user.userType==1}"><div>
                    <label>用户类别：</label>
                    <input type="radio" name="userlei" value="1"/>管理员
                    <input type="radio" name="userlei" value="2"/>经理
                    <input type="radio" name="userlei" value="3"/>普通用户
                </div></c:if>
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
function getFirstParam(){//获取当前URL第一个参数
	var paramString=location.search;
	if(paramString.indexOf('?')>-1){
		paramString=paramString.substr(1);
	}
	return paramString.split("=")[1];
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

$(function(){
	var id=getFirstParam();
	if(id){
		init(id);
	}
	//初始化方法
	function init(id){
		if(id){//数据回显
			$.post("User",{"method":"updateInit","id":id},function(data){
				$(data).each(function(){
					$("#id").val(this.id);
					$("#userName").val(this.userName);
					$("#sex>option").each(function(){
						if($(this).val()==data.gender){$(this).attr("selected","selected");}
					});
					$("#date").val(birthday(this.birthday));
					$("#userphone").val(this.phone);
					$("#userAddress").val(this.address);
					$("[name='userlei']").each(function(){
						if($(this).val()==data.userType){$(this).attr("checked","checked");}
					});
				});
			},"json");
		}
	}
	$("form span").css("display","none");//隐藏form里面的span
	$("#userName").focus(function(){//用户名称
		isFocus($("#userName"), "请输入用户名称");
	}).blur(function(){isUserName});
	$("#date").focus(function(){isFocus($("#date"), "请输入出生日期，规格yyyy-MM-dd")}).blur(isDate);//出生日期
	$("#userphone").focus(function(){isFocus($("#userphone"), "请输入电话号码")}).blur(isPhone);//电话号码
	$("#userAddress").focus(function(){isFocus($("#userAddress"), "请输入地址")}).blur(isAddress);//用户地址
	$("form").submit(function(){
		if(isUserName() && isDate() && isPhone() && isAddress()){
			return true;
		}
		return false;
	});
});
</script>
</body>
</html>