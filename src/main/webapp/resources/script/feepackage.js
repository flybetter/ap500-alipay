var feepackage={
	URL:{
		detail:function(projectName,feepackageId){
			return projectName+'/alipay/'+feepackageId+'/detail';
		},
		exposer:function(projectName,feepackageId){
			return projectName+'/alipay/'+feepackageId+'/exposer';
		},
		execution:function(projectName,feepackageId,md5){
			return projectName+'/alipay/'+feepackageId+'/'+md5+'/execution';
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
	projectUrl:function(){
		var curWwwPath=window.document.location.href;
		var projectUrl=curWwwPath.substring(curWwwPath.lastIndexOf('/')+1,curWwwPath.length);
		return projectUrl;
	},
	detail_bind:function(){
		$(".detail_btn").on("click",function(){
			var feepackageId=$(this).attr("value");
			$(".detail_btn").unbind();
			$(this).css({opacity:0.4});
			var projectName=feepackage.projectName();
			$.get(feepackage.URL.detail(projectName,feepackageId),{},function(result){
				if(result &&result['success']){
					var feepackageData=result['data'];
					var txt='<div class="float_row">';	 
					txt += '     <div class="float_left">名称	:</div>';	
					txt += '     <div class="float_right">'+feepackageData['name']+'</div>';	
					txt += ' </div>';	
					txt += '  <div class="float_row">';	
					txt += '     <div class="float_left">金额(元)	:</div>';	
					txt += '	     <div class="float_right">'+feepackageData['price']+'</div>';	
					txt += '	 </div>';	
					txt += '	  <div class="float_row">';	
					txt += '	     <div class="float_left">续费时长(月)	:</div>';	
					txt += '	     <div class="float_right">'+feepackageData['leastPurchase']+'</div>';	
					txt += '	 </div>';	
					txt += '	  <div class="float_row">';	
					txt += '	     <div class="float_left">折扣(元)	:</div>';	
					txt += '	     <div class="float_right">'+feepackageData['discount']+'</div>';	
					txt += '	 </div>';	
					txt += '	 <div class="float_row">';	
					txt += '	     <div class="float_left">合计(元)	:</div>';	
					txt += '	     <div class="float_right">'+feepackageData['total']+'</div>';	
					txt += '	 </div>';	
					$(".float_equal").html(txt);
					$(".float_div").show();
					$(".buy_btn").show();
					$.post(feepackage.URL.exposer(projectName,feepackageId),{},function(result){
						if(result &&result['success']){
							$('.buy_btn').one('click',function(){
								$(this).css({ opacity:0.4});
								var exposer=result['data'];
								$.post(feepackage.URL.execution(projectName, feepackageId, exposer['md5']),{},function(result){
									if(result &&result['success']){
										var html=result['data'];
										$(".float_equal").append(html['form']);
									}else{
										$(".float_equal").html(result['error']);
									}
								});
							});
						}else{
							$(".float_equal").html(result['error']);
						}
					});
				}else{
					$(".float_equal").html(result['error']);
				}
			});
		});
	},
	list:{
		init:function(params){
			var userId=params.userId;
			var projectName=feepackage.projectName();
			$.cookie('userid',userId,{expires:7,path:projectName+'/'});
			$.cookie('url',feepackage.projectUrl(),{expires:7,path:projectName+'/'});
			//详情页面按钮
			feepackage.detail_bind();
			//关闭按钮
			$(".close_btn").click(function(){
				$(".float_div").hide();
				$(".buy_btn").hide();
				//详情页面按钮
				feepackage.detail_bind();
				$(".detail_btn").css({opacity:1});
				$(".buy_btn").css({ opacity:1});
			});
		}
	}
}