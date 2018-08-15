<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@page import="com.baidu.dao.UserDao"%>
<%@page import="com.baidu.entity.Article"%>
<%@page import="com.baidu.dao.ArticleDao"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'post.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css"
	href="assets/css/bootstrap.min.css">
<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>

</head>

<body class="container">
	<c:if test="${sessionScope.user==null}">
		<c:redirect url="login.jsp" />
	</c:if>
	<c:if test="${sessionScope.user!=null}">
		<div class="page-header">
			<h1>博客后台管理系统</h1>
			<p class="text-right">用户:${username}</p>
		</div>
		<c:if test="${a!=null}">
		<form class="form-horizontal" action="ControllerPost?id=${a.getId()}"
			enctype="multipart/form-data" method="post">

			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">标题</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputEmail3"
						placeholder="${a.getTitle()}" name="title">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">作者</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputPassword3"
						placeholder="${a.getAuthor()}" name="author">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">海报</label>
				<div class="col-sm-10">
					<input type="file" class="#" id="inputPassword3" name="#">
				</div>
			</div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">类型</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputEmail3"
						placeholder="${a.getType()}" name="type">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-2"></div>
				<div class="radio col-sm-10">
					<label> <input type="radio" name="ishow"
						id="optionsRadios1" value="true" checked>是否立即发布</label>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-2"></div>
				<div class="col-sm-10">
					<textarea class="form-control" rows="3" name="content"
						placeholder="${a.getContent()}"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<%-- 
				<button type="submit" class="btn btn-default">Sign in</button>
				--%>
					<input type="submit" class="btn btn-default" value="Sign in">
				</div>
			</div>
		</form>
		</c:if>
		<c:if test="${a==null}">

		<form class="form-horizontal" action="ControllerPost" method="post"
			enctype="multipart/form-data">

			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">标题</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputEmail3"
						placeholder="title" name="title">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">作者</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputPassword3"
						placeholder="author" name="author">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">海报</label>
				<div class="form-group">
					<input type="file" class="#" id="inputPassword3" name="poster">
				</div>
			</div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">类型</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputEmail3"
						placeholder="type" name="type">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-2"></div>
				<div class="radio col-sm-10">
					<label> <input type="radio" name="ishow"
						id="optionsRadios1" value="true" checked>是否立即发布</label>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-2"></div>
				<div class="col-sm-10">
					<textarea class="form-control" rows="3" name="content"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-default" value="Sign in">
					<input type="reset" class="btn btn-default" value="sign out" />
				</div>
			</div>
		</form>
		</c:if>
	</c:if>
</body>
</html>