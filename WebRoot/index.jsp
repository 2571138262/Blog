<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@page import="com.baidu.dao.UserDao"%>
<%@page import="com.baidu.dao.ArticleDao"%>
<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.baidu.entity.Article"%>
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
			<h1>博客后台管理系统.</h1>
			<p class="text-right">用户:${username}</p>
		</div>
		<div class="form-inline">
			<form action="Controller" method="post">
				<div class="form-group">
					<label class="sr-only" for="exampleInputAmount">Amount (in
						dollars)</label>
					<div class="input-group">
						<input type="text" class="form-control" id="exampleInputAmount"
							placeholder="Query by title or type." name="search">
					</div>
				</div>
				<input type="submit" class="btn btn-primary" value="搜索" />
			</form>
			<form action="Controller?action=creNew" method="post">
				<input type="submit" class="btn btn-primary" value="新建" />
			</form>
		</div>

		<table class="table">
			<c:if test="${arts==null}">
				<caption>还没有添加博客</caption>
			</c:if>
			<c:if test="${search==null}">
				<caption>全部博客列表.</caption>
			</c:if>
			<c:if test="${search!=null}">
				<caption>
					与 <font color=#cc0000>${search}</font> 有关的博客列表.
				</caption>
			</c:if>
			<thead>
				<tr>
					<th>ID</th>
					<th>标题</th>
					<th>作者</th>
					<th>创建时间</th>
					<th>更新时间</th>
					<th>类型</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="i" value="1" />
				<c:if test="${arts != null && arts.size() > 0}">
					<c:forEach items="${arts}" var="a">
						<tr>
							<th scope="row">${i+1}</th>
							<td>${a.getTitle()}</td>
							<td>${a.getAuthor()}</td>
							<td>${a.getCreate_time()}</td>
							<td>${a.getUpdate_time()}</td>
							<td>${a.getType()}</td>
							<td>
								<form class="form-inline"
									action="Controller?action=update&id=${a.getId()}" method="post"
									style="display: block;">
									<input type="submit" class="btn btn-primary btn-sm" value="编辑" />
								</form>
							</td>
							<td>
								<form class="form-inline"
									action="Controller?action=delete&id=${a.getId()}&index=${param.index}"
									method="post" style="display: block;">
									<input type="submit" class="btn btn-default btn-sm" value="删除" />
								</form>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${search==null}">
					<tr>
						<c:if test="${param.index-1>=1}">
							<td>
								<form action="Controller?index=${param.index-1}" method="post">
									<input type="submit" class="btn btn-success" value="上一页" />
								</form></td>
						</c:if>
						<c:if test="${param.index+1<=middle}">
							<td>
								<form action="Controller?index=${param.index+1}" method="post">
									<input type="submit" class="btn btn-success" value="下一页" />
								</form></td>
						</c:if>
					</tr>
				</c:if>
				<c:if test="${search!=null}">
					<tr>
						<td>
							<form action="Controller?action=return" method="post">
								<input type="submit" class="btn btn-success" value="返回" />
							</form></td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</c:if>
</body>
</html>
