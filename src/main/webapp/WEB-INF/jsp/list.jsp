<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="common/tag.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>套餐页面展示</title>
<%@include file="common/head.jsp" %>
<link href="<%=getServletContext().getContextPath()%>/resources/css/feepackage.css" rel="stylesheet" type="text/css"/> 
</head>
<body>
	<div class="main">
		<div class="container">
			<div class="header">
				主套餐
			</div>
			<hr class="hr"/>
			<div class="equal">
			    <div class="row_header">
				     <div class="left">名称</div>
				     <div class="middle">描述</div>
				     <div class="right">操作</div>
			    </div>
			    <c:forEach var="object" items="${subjectList}">
				     <div class="row">
					     <div class="left">${object.name }</div>
						 <div class="middle" style="text-align: justify;">${object.description }</div>
						 <div class="right"><a class="detail_btn" value='${object.id}'></a></div>
				    </div>
			    </c:forEach>
			</div>   
  		</div>
  		
  		<div class="container">
			<div class="header">
				增值套餐
			</div>
			<hr class="hr">
			<div class="equal">
			    <div class="row_header">
				     <div class="left">名称</div>
				     <div class="middle">描述</div>
				     <div class="right">操作</div>
			    </div>
				 <c:forEach var="object" items="${supplementList}">
				     <div class="row">
					     <div class="left">${object.name }</div>
						 <div class="middle" style="text-align: justify;">${object.description}</div>
						 <div class="right"><a class="detail_btn" value='${object.id}' ></a></div>
				    </div>
			    </c:forEach>
			</div>   
  		</div>
	</div>
	<!-- 弹窗 -->	  		
	<div class="float_div">
		<div class="float_header">
			详情
		</div>
		<hr class="float_hr">
		<div class="float_equal">
		</div>	 
		<div class="float_bottom">
		 	<a class="buy_btn" ></a>
		 	<a class="close_btn" ></a>
		</div>
	</div>
  		
</body>
	<script type="text/javascript" src="<%=getServletContext().getContextPath()%>/resources/script/feepackage.js"></script>
	<script type="text/javascript" >
		$(function(){
			var userId=${userId};
			feepackage.list.init({userId:userId});
		});
	</script>

</html>