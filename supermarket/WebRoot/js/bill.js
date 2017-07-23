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
//订单编码验证
function isBillId(){
	if(/^[0-9]{3}$/.test($("#billId").val())){
		ok($("#billId"));
		return true;
	}else{
		error($("#billId"), "请输入全数字，最少3位订单号");
		return false;
	}
}
//商品名称验证
function isBillName(){
	if($("#billName").val()!=""){
		ok($("#billName"));
		return true;
	}else{
		error($("#billName"), '商品名称不能为空');
		return false;
	}
}
//商品单位验证
function isBillCom(){
	if($("#billCom").val()!=""){
		ok($("#billCom"));
		return true;
	}else{
		error($("#billCom"), "商品单位不能为空");
		return false;
	}
}
//商品数量验证
function isBillNum(){
	if($("#billNum").val()==""){
		error($("#billNum"), "商品数量不能为空");
		return false;
	}else if(!(/^[0-9]*[1-9][0-9]*$/.test($("#billNum").val()))){
		error($("#billNum"), "请输入大于0的正整数");
		return false;
	}else{
		ok($("#billNum"));
		return true;
	}
}
//商品总金额验证
function isMoney(){
	if($("#money").val()==""){
		error($("#money"), '总金额不能为空');
		return false;
	}else if(!(/^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/.test($("#money").val()))){
		error($("#money"), '请输入正小数');
		return false;
	}else{
		ok($("#money"));
		return true;
	}
}
//验证供应商
function isProvider(){
	if($("#provider").val()==0){
		error($("#provider"), '请选择供应商');
		return false;
	}else{
		ok($("#provider"));
		return true;
	}
}
$(function(){
	var id=getFirstParam();
	if(id){
		init(id);
	}else{
		init();
	}
	
	$("form span").css("display","none");
		$("#billId").focus(function(){//订单编码验证
			isFocus($("#billId"), "请输入大于3位数的数字");
		}).blur(isBillId);
	$("#billName").focus(function(){//商品名称验证
		isFocus($("#billName"), "请输入商品名称");
	}).blur(isBillName);
	
	$("#billCom").focus(function(){//商品单位验证
		isFocus($("#billCom"), "请输入商品单位")}).blur(isBillCom);
	
	$("#billNum").focus(function(){//商品数量验证
		isFocus($("#billNum"), "请输入商品数量");}).blur(isBillNum);
	
	$("#money").focus(function(){//商品总金额验证
		isFocus($("#money"), "请输入商品总金额");}).blur(isMoney);
	
	$("#provider").blur(isProvider);
	
	//初始化方法
	function init(id){
		if(id){//数据回显
			$.post("BillUpdate",{"method":"init"},pro,"json");
			$.post("BillUpdate",{"method":"updateInit","id":id},showBill,"json");
		}else{
			$.post("BillUpdate",{"method":"init"},pro,"json");
		}
	}
	function pro(data){//获取供应商下拉列表信息回调函数
		$(data).each(function(){
			$("#provider").append("<option value='"+this.proName+"'>"+this.proName+"</option>");
		})
	}
	function showBill(data){//数据回显回掉函数
		$(data).each(function(){
			$("#billId").val(this.billCode);
			$("#billName").val(this.productName);
			$("#billCom").val(this.productDesc);
			$("#billNum").val(this.productCount);
			$("#money").val(this.totalPrice);
			$("#provider>option").each(function(){
				if($(this).val()==data.productUnit){$(this).attr("selected","selected");}
			});
			$("[name='zhifu']").each(function(){
				if($(this).val()==data.isPayment){$(this).attr("checked","checked");}
			});
			$("[name='id']").val(this.id);
		});
	}
	$("form").submit(function(){
		if(isBillId() && isBillName() && isBillCom() && isBillNum() && isMoney() && isProvider()){
			return true;
		}else{
			return false;
		}
	});
});