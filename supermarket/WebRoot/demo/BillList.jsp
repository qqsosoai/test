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
            <p><span>下午好！</span><span style="color: #fff21b">${user.userName}</span> , 欢迎你！</p>
            <a href="login.html">退出</a>
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
                    <li id="active"><a href="billList.html">账单管理</a></li>
                    <li><a href="providerList.html">供应商管理</a></li>
                    <li><a href="userList.html">用户管理</a></li>
                    <li><a href="password.html">密码修改</a></li>
                    <li><a href="login.html">退出系统</a></li>
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
                <select name="tigong" >
                    <option value="0">--请选择--</option>
                    <option value="">北京市粮油总公司</option>
                    <option value="">邯郸市五得利面粉厂</option>
                </select>

                <span>是否付款：</span>
                <select name="fukuan">
                    <option value="2">--请选择--</option>
                    <option value="0">未付款</option>
                    <option value="1">已付款</option>
                </select>

                <input type="button" value="查询"/>
                <a href="billAdd.html">添加订单</a>
            </div>
            <!--账单表格 样式和供应商公用-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <thead><tr class="firstTr">
                    <th width="10%">账单编码</th>
                    <th width="20%">商品名称</th>
                    <th width="10%">供应商</th>
                    <th width="10%">账单金额</th>
                    <th width="10%">是否付款</th>
                    <th width="10%">创建时间</th>
                    <th width="30%">操作</th>
                </tr></thead>
                <tbody></tbody>
            </table>
			<p><a href='javascript:' name='page'>首页</a>
			<a href='javascript:' name='page'>上一页</a>
			<a href='javascript:' name='page'>下一页</a>
			<a href='javascript:' name='page'>末页</a></p>
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
<script type="text/javascript">
$(function(){
	var pageIndex="";//当前页码
	var pageSize="";//页大小
	var pageCount="";//总页数
	var sqlCount="";//总记录数
	init(pageIndex);
	function init(pageIndex){
		$.post("Bill?method=select",{"pageIndex":pageIndex},f,"json");
	}
	function f(data){
			$("tbody").html("");
			$("[name='tigong']").html("");
			$("[name='tigong']").append("<option value='0'>--请选择--</option>");
			$(data).each(function(){
				if(this.billCode!=undefined){//代表为账单
				$("tbody").append("<tr>"
            +"       <td>"+this.billCode+"</td>"
            +"       <td>"+this.productName+"</td>"
            +"       <td>"+this.productUnit+"</td> "
            +"       <td>"+this.totalPrice+"</td>"
            +"       <td>"+pay(this.isPayment)+"</td>"
            +"       <td>"+format(this.creationDate)+"</td>"
            +"       <td>"
            +"           <a href='javascript:' id="+this.id+"><img src='img/read.png' alt='查看' title='查看'/></a>"
            +"           <a href='javascript:' id="+this.id+"><img src='img/xiugai.png' alt='修改' title='修改'/></a>"
            +"           <a href='javascript:' id="+this.id+" class='removeBill'><img src='img/schu.png' alt='删除' title='删除'/></a>"
            +"       </td>"
            +"   </tr>");
				}else if(this.proCode!=undefined){//代表为供应商
					$("[name='tigong']").append("<option value='"+this.id+"'>"+this.proName+"</option>");
				}else if(this.pageIndex!=undefined){//代表为页面信息
					pageIndex=this.pageIndex;
					pageSize=this.pageSize;
					pageCount=this.pageCount;
					sqlCount=this.sqlCount;
				}
			});
				$.getScript("js/bill.js",function(){});
		}
	function format(time){//时间转换方法 参数为long类型的值
		var date=new Date(time);
		var datetime="";
		var year=date.getFullYear();
		var month=date.getMonth()+1;
		var day=date.getDate();
		var hh=date.getHours();
		var mm=date.getMinutes();
		var ss=date.getSeconds();
		datetime+=year;
		if(month<10){
			month="0"+month;
		}
		datetime+="-"+month;
		if(day<10){
			day="0"+day
		}
		datetime+="-"+day;
		if(hh<10){
			hh="0"+hh
		}
		datetime+="&nbsp;&nbsp;"+hh;
		if(mm<10){
			mm="0"+mm
		}
		datetime+=":"+mm;
		if(ss<10){
			ss="0"+ss;
		}
		datetime+=":"+ss;
		return datetime;
	}
	function pay(isPayment){//判断是否付款
		if(isPayment=='0'){
			return '已付款';
		}else{
			return '未付款';
		}
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
	//显示修改账单方法
	function update(id){alert(1111111)}
	//删除账单方法
	function deleteBill(id){}
	
});
</script>

</body>
</html>
