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
function isProviderId(){
		if(/^\d{3}$/.test($("#providerId").val())){
			ok($("#providerId"));
			return true;
		}else{
			error($("#providerId"),"供应商编码要由3位数组组成");
			return false;
		}
}
function isProviderName(){
		if($("#providerName").val()!=""){
			ok($("#providerName"));
			return true;
		}else{
			error($("#providerName"), "供应商名称不能为空");
			return false;
		}
}
function isPeople(){
	if($("#people").val()!=""){
		ok($("#people"));
		return true;
	}else{
		error($("#people"), "供应商联系人不能为空");
		return false;
	}
};
function isPhone(){
		if(/^\d{11}$/.test($("#phone").val())){
			ok($("#phone"));return true;
		}else{error($("#phone"), "请输入正确的电话号码");return false;}
}
function isAddress(){
	if($("#address").val()!=""){ok($("#address"));return true;}else{
	error($("#address"), "供应商联系地址不能为空");return false;}
}
function isFax(){
		if($("#fax").val()!=""){ok($("#fax"));return true;}else{error($("#fax"), "供应商传真不能为空");return false;}
}
$(function(){
	var id=getFirstParam();
	if(id){
		init(id);
	}
	//初始化方法
	function init(id){
		if(id){//数据回显
			$.post("Provider",{"method":"updateInit","id":id},function(data){
				$(data).each(function(){
					$("#id").val(this.id);
					$("#providerId").val(this.proCode);
					$("#providerName").val(this.proName);
					$("#people").val(this.proContact);
					$("#phone").val(this.proPhone);
					$("#address").val(this.proAddress);
					$("#fax").val(this.proFax);
					$("#describe").val(this.proDesc);
				});
			},"json");
		}
	}
	$("form span").css("display","none");
	$("#providerId").focus(function(){
		isFocus($("#providerId"), "请输入供应商编码，由3位数字组成");
	}).blur(isProviderId);
	
	$("#providerName").focus(function(){
	isFocus($("#providerName"), "请输入供应商名称");
	}).blur(isProviderName);
	
	$("#people").focus(function(){
	isFocus($("#people"), "请输入供应商联系人");
	}).blur(isPeople);
	
	$("#phone").focus(function(){
	isFocus($("#phone"), "请输入供应商联系电话");
	}).blur(isPhone);
	
	$("#address").focus(function(){
	isFocus($("#address"), "请输入供应商联系地址");
	}).blur(isAddress);

	$("#fax").focus(function(){
		isFocus($("#fax"), "请输入供应商传真");
	}).blur(isFax);
	
	$("#describe").focus(function(){
	isFocus($("#describe"), "请输入供应商描述");
	}).blur(ok($("#describe")));
	$("form").submit(function(){
		if(isProviderId() && isProviderName() && isPeople() && isPhone() && isAddress() && isFax()){
			return true;
		}else{
			return false;
		}
	});
});