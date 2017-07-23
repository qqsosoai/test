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
<script>
    function go(){
        var path=location.search;
        if(path.indexOf()!=-1){
            path.substr(path.indexOf());
            alert(path);
        }
    }
</script>
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
                    <li id="active"><a href="billList.jsp">账单管理</a></li>
                    <li><a href="providerList.jsp">供应商管理</a></li>
                    <c:if test="${user.userType<3}"><li><a href="userList.jsp">用户管理</a></li></c:if>
                    <li><a href="password.jsp">密码修改</a></li>
                    <li><a href="Login?method=out">退出系统</a></li>
                </ul>
            </nav>
        </div>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>账单管理页面</span>
            </div>
            <div class="search">
                <span>商品名称：</span>
                <input type="text" placeholder="请输入商品的名称"/>
                
                <span>供应商：</span>
                <select name="tigong">
                    <option value="">全部</option>
                </select>
                <span>是否付款：</span>
                <select name="fukuan">
                    <option value="">--请选择--</option>
                    <option value="1">已付款</option>
                    <option value="0">未付款</option>
                </select>
                <input type="button" value="查询"/>
                <a href="billAdd.jsp">添加订单</a>
            </div>
            <!--账单表格 样式和供应商公用-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
            <thead>
                <tr class="firstTr">
                    <th width="10%">账单编码</th>
                    <th width="20%">商品名称</th>
                    <th width="10%">供应商</th>
                    <th width="10%">账单金额</th>
                    <th width="10%">是否付款</th>
                    <th width="10%">创建时间</th>
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
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该订单吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

    <footer class="footer">
        版权归北大青鸟
    </footer>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/dateFormat.js"></script>
<script type="text/javascript">
function sh(id){
	$(".zhezhao").show();
	$("#removeBi").show();
	$("#yes").attr("href","BillUpdate?method=delete&id="+id);
}
$(function(){
	var pageIndex="";//当前页码
	var pageSize="";//页大小
	var pageCount="";//总页数
	var sqlCount="";//总记录数
	$.post("Bill",{"method":"pro"},function(data){
		$(data).each(function(){
			$("[name='tigong']").append("<option value="+this.id+">"+this.proName+"</option>");
		});
	},"json");//获取供应商名称下拉列表
	init(pageIndex);
	function init(pageIndex){
		$.post("Bill",{"pageIndex":pageIndex,"search":$("input:first").val(),
		"provider":$("select:first").val(),"isPay":$("select:last").val(),
		"method":"select"},f,"json");
	}
	function f(data){
			$("tbody").html("");
			$(data).each(function(){
				if(this.billCode){//代表为账单
				var ispay=this.isPayment==0?"未付款":"已付款";
				$("tbody").append("<tr>"
            +"       <td>"+this.billCode+"</td>"
            +"       <td>"+this.productName+"</td>"
            +"       <td>"+this.productUnit+"</td> "
            +"       <td>"+this.totalPrice+"</td>"
            +"       <td>"+ispay+"</td>"
            +"       <td>"+format(this.creationDate)+"</td>"
            +"       <td>"
            +"           <a href='billView.jsp?id="+this.id+"'><img src='img/read.png' alt='查看' title='查看'/></a>"
            +"           <a href='billUpdate.jsp?id="+this.id+"'><img src='img/xiugai.png' alt='修改' title='修改'/></a>"
            +"           <a href='javascript:sh("+this.id+")' class='removeBill'><img src='img/schu.png' alt='删除' title='删除'/></a>"
            +"       </td>"
            +"   </tr>");
				}else if(this.pageIndex){//代表为页面信息
					pageIndex=this.pageIndex;
					pageSize=this.pageSize;
					pageCount=this.pageCount;
					sqlCount=this.sqlCount;
					$("#page").html("");
					$("#page").html(pageIndex+"/"+pageCount+"页，共"+sqlCount+"条记录");
				}else{
					$("tbody").html("<tr><td colspan='7' style='font-size:25px'>"+this.flag+"</td></tr>");
					$("#page").html("");
					$("#page").html("1/1页，共0条记录");
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
	$(document).keyup(function(event){
		if(event.keyCode==13){
			init("");
		}
	});
	$("[value='查询']").click(function(){
		init("");
	});
});
</script>
<script src="js/js.js"></script>
<script src="js/time.js"></script>

</body>
</html>