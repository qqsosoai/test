$(function(){
	$("td>a:first").click(function(){//为bill页面的查询
		$.post("Bill?method=view",{"id":$("td>a:first").attr("id")},function(data){
			$(".right").html('');
			$(".right").append("<div class='location'> "
     +"       <strong>你现在所在的位置是:</strong>                                "
     +"       <span>账单管理页面 >> 信息查看</span>                                "
     +"   </div>                                                         "
     +"   <div class='providerView'>                                     "
     +"       <p><strong>订单编号：</strong><span>"+data.billCode+"</span></p>              "
     +"       <p><strong>商品名称：</strong><span>"+data.productName+"</span></p>              "
     +"       <p><strong>商品单位：</strong><span>"+data.productDesc+"</span></p>               "
     +"       <p><strong>商品数量：</strong><span>"+data.productCount+"</span></p>               "
     +"       <p><strong>总金额：</strong><span>"+data.totalPrice+"</span></p>                "
     +"       <p><strong>供应商：</strong><span>"+data.productUnit+"</span></p>                "
     +"       <p><strong>是否付款：</strong><span>"+data.isPayment+"</span></p>              "
     +"       <a href='javascript:'>返回</a>                             "
     +"   </div>");
		},"json");
		$("div>a").click(function(){back()});
	});
	//返回Bill主页面方法
	function back(){
		$(".right").html('');
		$('.right').append("<div class='location'>"
    +"           <strong>你现在所在的位置是:</strong>                       "
    +"           <span>账单管理页面</span>                               "
    +"       </div>                                                "
    +"       <div class='search'>                                  "
    +"           <span>商品名称：</span>                                "
    +"           <input type='text' placeholder='请输入商品的名称'/>       "
    +"                                                             "
    +"           <span>供应商：</span>                                 "
    +"           <select name='tigong' >                           "
    +"           </select>                                         "
    +"                                                             "
    +"           <span>是否付款：</span>                                "
    +"           <select name='fukuan'>                            "
    +"               <option value='2'>--请选择--</option>            "
    +"               <option value='0'>未付款</option>                "
    +"               <option value='1'>已付款</option>                "
    +"           </select>                                         "
    +"                                                             "
    +"           <input type='button' value='查询'/>                 "
    +"           <a href='billAdd.html'>添加订单</a>                   "
    +"       </div>                                                "
    +"       <!--账单表格 样式和供应商公用-->  "
    +"       <table class='providerTable' cellpadding='0' cellspaci> "
    +"           <thead><tr class='firstTr'>                       "
    +"               <th width='10%'>账单编码</th>                     "
    +"               <th width='20%'>商品名称</th>                     "
    +"               <th width='10%'>供应商</th>                      "
    +"               <th width='10%'>账单金额</th>                     "
    +"               <th width='10%'>是否付款</th>                     "
    +"               <th width='10%'>创建时间</th>                     "
    +"               <th width='30%'>操作</th>                       "
    +"           </tr></thead>                                     "
    +"           <tbody></tbody>                                   "
    +"       </table>                                              "
	+"		<p><a href='javascript:'>首页</a>            "
	+"		<a href='javascript:'>上一页</a>              "
	+"		<a href='javascript:'>下一页</a>"
	+"		<a href='javascript:'>末页</a></p>");
	init(1);
	}
});