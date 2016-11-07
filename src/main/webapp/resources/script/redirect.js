var redirect={
	URL:{
		redirectURl:function(userId,url){
			return userId+'/'+url;
		}
	},	
	projectName:function(){
		var curWwwPath=window.document.location.href;
		var pathName=window.document.location.pathname;
		var pos=curWwwPath.indexOf(pathName);
		var localhostPaht=curWwwPath.substring(0,pos);
		var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
		return projectName;
	},
	countdown:function(count){
		if(count==0){
			var userId=$.cookie("userid"); 
			var url=$.cookie("url"); 
			var redirectUrl=redirect.URL.redirectURl(userId, url)
			window.location.href=redirectUrl;
		}else{
			$(".second").html(count);
			count--;
			setTimeout("redirect.countdown("+count+")", 1000); 
		}
	},	
	init:function(){
		var count=5;
		$(".second").html(count);
		redirect.countdown(count);
	}
};