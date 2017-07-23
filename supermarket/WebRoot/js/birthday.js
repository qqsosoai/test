function birthday(time){
	var date=new Date(time);
	var datetime="";
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	var day=date.getDate();
	datetime+=year;
	if(month<10){
		month="0"+month;
	}
	datetime+="-"+month;
	if(day<10){
		day="0"+day
	}
	datetime+="-"+day;
	return datetime;
}