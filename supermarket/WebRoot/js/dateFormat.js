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


