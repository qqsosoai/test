<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <li id="active"><a href="providerList.jsp">供应商管理</a></li>
                <c:if test="${user.userType<3}"><li><a href="userList.jsp">用户管理</a></li></c:if>
                <li><a href="password.jsp">密码修改</a></li>
                <li><a href="Login?method=out">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面 >> 供应商添加页面</span>
        </div>
        <div class="providerAdd">
            <form action="Provider" method="post">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <input type="hidden" value="insert" name="method">
                <div class="">
                    <label for="providerId">供应商编码：</label>
                    <input type="text" name="providerId" id="providerId" required/>
                    <span></span>
                </div>
                <div>
                    <label for="providerName">供应商名称：</label>
                    <input type="text" name="providerName" id="providerName" required/>
                    <span ></span>
                </div>
                <div>
                    <label for="people">联系人：</label>
                    <input type="text" name="people" id="people" required/>
                    <span></span>

                </div>
                <div>
                    <label for="phone">联系电话：</label>
                    <input type="text" name="phone" id="phone" required/>
                    <span></span>
                </div>
                <div>
                    <label for="address">联系地址：</label>
                    <input type="text" name="address" id="address" required/>
                    <span></span>
                </div>
                <div>
                    <label for="fax">传真：</label>
                    <input type="text" name="fax" id="fax" required/>
                    <span></span>
                </div>
                <div>
                    <label for="describe">描述：</label>
                    <input type="text" name="describe" id="describe" />
                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="providerList.html">返回</a>-->
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
<script type="text/javascript" src="js/providerAddUpdate.js"></script>
</body>
</html>