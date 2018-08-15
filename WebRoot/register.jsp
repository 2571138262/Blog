<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<!-- Page title -->
<title>imooc - Login</title>
<!-- End of Page title -->
<!-- Libraries -->
<link type="text/css" href="css/login.css" rel="stylesheet" />
<link type="text/css" href="css/smoothness/jquery-ui-1.7.2.custom.html"
	rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/easyTooltip.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.7.2.custom.min.js"></script>
<!-- End of Libraries -->
</head>
<body>
	<c:if test="${message1==null}">
		<c:if test="${param.message==null}">
			<c:redirect url="login.jsp" />
		</c:if>
		<c:if test="${param.message==1}">
			<c:set var="tip" value="${请牢记你的密码}" />
		</c:if>
	</c:if>
	<c:if test="${message1!=null}">
		<c:set var="tip" value="${message1}" />
	</c:if>
	<div id="container">
		<div class="logo">
			<a href="#"><img src="assets/logo.png" alt="" />
			</a>
		</div>
		<div id="box">
			<form action="DoRegister" method="post">
				<p class="main">
					<label>输入用户名: </label> <input name="username" value="" />
				</p>
				<br> <br> <br> <br>
				<p class="main">
					<label>请输入密码: </label> <input type="password" name="password"
						value=""> <label>${tip} </label>
				</p>
				<br> <br> <br> <br>
				<p class="main">
					<label>请确认密码: </label> <input type="password" name="checkpass"
						value="">
				</p>
				<p class="space">
					<input type="submit" value="注册" class="login"
						style="cursor: pointer;" />
				</p>
			</form>
		</div>
	</div>
</body>
</html>