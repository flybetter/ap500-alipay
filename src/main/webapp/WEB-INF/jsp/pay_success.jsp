<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="common/head.jsp" %>
<title>支付成功</title>
<link href="<%=getServletContext().getContextPath()%>/resources/css/pay_success.css" rel="stylesheet" type="text/css"/> 
</head>
<body>
	<div class="second">
	</div>
</body>
	<script type="text/javascript" src="<%=getServletContext().getContextPath()%>/resources/script/redirect.js"></script>
	<script type="text/javascript" >
		$(function(){
			redirect.init();
		});
	</script>
</html>