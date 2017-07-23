$(function(){
	$()
		$.post("Bill?method=view",{"id":id},function(data){
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
	 +"       <p><strong>是否付款：</strong><span>"+pay(data.isPayment)+"</span></p>              "
	 +"       <a href='javascript:'>返回</a>                             "
	 +"   </div>");
		$("div>a").click(function(){
			
		});
		},"json");
	
});