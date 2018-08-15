<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.baidu.dao.ArticleDao"%>
<%@page import="com.baidu.entity.Article"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang=zh-CN>
<head>
<meta charset=utf-8>
<meta http-equiv=X-UA-Compatible content="IE=edge">
<title>详细信息</title>
<meta name=description
	content="#">
<meta name=keywords
	content="#">
<meta name=HandheldFriendly content=True>
<meta name=viewport content="width=device-width,initial-scale=1">
<link rel="shortcut icon"
	href=https://www.golaravel.com/assets/icons/favicon.ico>
<link rel=stylesheet
	href=https://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css>
<link rel=stylesheet
	href=https://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css>
<link rel=stylesheet
	href=https://cdn.bootcss.com/highlight.js/9.0.0/styles/vs.min.css>
<link rel=stylesheet type=text/css
	href=https://www.golaravel.com/assets/css/screen.min.css?1530120249519>
</head>
<body class=post-template>
	<header class=main-header
		style="background-image: url(http://image.golaravel.com/5/c9/44e1c4e50d55159c65da6a41bc07e.jpg)">
		<div class=container>
			<div class=row>
				<div class=col-xs-12>
					<h1>
						<span class=hide>Laravel - </span> 为编程工作者创造的 博客。
					</h1>
					<h2 class=hide>PHP THAT DOESN'T HURT. CODE HAPPY &amp;ENJOY
						THE FRESH AIR.</h2>
					<img
						src=http://image.golaravel.com/e/b0/4e4bd788405aab87f03d26edc4ab4.png
						alt=Laravel class=hide>
				</div>
			</div>
	</header>
	<nav class=main-navigation>
		<div class=container>
			<div class=row>
				<div class=col-sm-12>
					<div class=navbar-header>
						<span class="nav-toggle-button collapsed" data-toggle=collapse
							data-target=#main-menu> <span class=sr-only>Toggle
								navigation</span> <i class="fa fa-bars"></i> </span>
					</div>
					<div class="collapse navbar-collapse" id=main-menu>
						<ul class=menu>
							<li role=presentation><a href=/ title=首页>首页</a></li>
							<li role=presentation><a
								href=https://docs.golaravel.com/docs / title="Laravel 中文文档"
								target=_blank
								onclick="_hmt.push(['_trackEvent', 'main-navigation', 'click', 'Laravel 中文文档'])">Laravel
									中文文档</a></li>
							<li role=presentation style="font-weight: bold"><a
								href=https://pkg.phpcomposer.com / title="Packagist 中国镜像"
								target=_blank
								onclick="_hmt.push(['_trackEvent', 'main-navigation', 'click', 'Packagist 中国镜像'])">Packagist
									中国镜像</a></li>
							<li role=presentation><a href=http://www.phpcomposer.com
								/ title="Composer 中文文档" target=_blank
								onclick="_hmt.push(['_trackEvent', 'main-navigation', 'click', 'Composer 中文文档'])">Composer
									中文文档</a></li>
							<li role=presentation><a href=https://php.golaravel.com
								/ title="PHP 中文手册" target=_blank
								onclick="_hmt.push(['_trackEvent', 'main-navigation', 'click', 'PHP 中文手册'])">PHP
									中文手册</a></li>
							<li role=presentation><a
								href=https://phptherightway.golaravel.com / title="PHP 编程之道"
								target=_blank
								onclick="_hmt.push(['_trackEvent', 'main-navigation', 'click', 'PHP 编程之道'])">PHP
									编程之道</a></li>
							<li role=presentation><a href=http://wenda.golaravel.com
								/ title=问答社区 target=_blank
								onclick="_hmt.push(['_trackEvent', 'main-navigation', 'click', '问答社区'])">问答社区</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</nav>
	<c:if test="${a==null}">
		<c:redirect url="ControllerHome" />
	</c:if>
	<c:if test="${a!=null}">
		<section class=content-wrap>
			<div class=container>
				<div class=row>
					<main class="col-md-8 main-content">
					<article class=post>
						<header class=post-head>
							<h1 class=post-title>${a.getTitle()}</h1>
							<section class=post-meta>
								<span class=author> 作者：<a
									href="https://www.bootcss.com
								/" target="_blank">${a.getAuthor()}</a>
								</span> &bull;
								<time class=post-date datetime=2018年02月08日 title=2018年02月08日>${a.getUpdate_time()}</time>
							</section>
						</header>
						<section class=featured-media>
							<img src=${a.getPoster() } alt="Thorn Poppy">
						</section>
						<section class=post-content>
							<p>${a.getContent()}</p>
							<hr>
							<p>
								英文原文地址：<a href="#">https://laravel-news.com/laravel-5-6</a>
							</p>
						</section>
						<footer class="post-footer clearfix"></footer>
					</article>
					<div class="about-author clearfix">
						<div class=details>
							<div class=author>
								关于作者 <a href="#">白晓文</a>
							</div>
							<div class=meta-info>
								<span class=loaction> <i class="fa fa-home"></i> 北京 </span> <span
									class=website> <i class="fa fa-globe"></i> <a href="#"
									targer=_blank>个人网站/博客</a> </span>
							</div>
							<div class=bio>源码面前，了无秘密。</div>
						</div>
					</div>
					<div class="prev-next-wrap clearfix">
						<c:if test="${aLast != a.getId()}">
							<a class="btn btn-default" href="ControllerHome?id=${aLast}" />
							<i class="fa fa-angle-left fa-fw"></i>
					${aLastTitle}
					</a>
						</c:if>
						&nbsp; <a class="btn btn-default"
							href="ControllerHome?action=back" /> <i class="#"></i>返回主页 </a>
						&nbsp;
						<c:if test="${aNext != a.getId()}">
							<a class="btn btn-default" href="ControllerHome?id=${aNext}" />
							<i class="fa fa-angle-right fa-fw"></i>
					${aNextTitle}
					</a>
						</c:if>
					</div>
					</main>
					<aside class="col-md-4 sidebar">
						<div class=widget>
							<h4 class=title>社区</h4>
							<div class="content community">
								<p>QQ：2571138262</p>
							</div>
						</div>
					</aside>
				</div>
			</div>
		</section>
	</c:if>
	<footer class=main-footer>
		<div class=container>
			<div class=row>
				<div class=col-sm-4>
					<div class=widget>
						<h4 class=title>友链</h4>
						<div class="content tag-cloud friend-links">
							<a href=http://www.bootcss.com title="Bootstrap 中文网"
								onclick="_hmt.push(['_trackEvent', 'link', 'click', 'bootcsscom'])"
								target=_blank>Bootstrap中文网</a> <a href=https://www.webpackjs.com
								title="Webpack 中文网"
								onclick="_hmt.push(['_trackEvent', 'link', 'click', 'webpackjscom'])"
								target=_blank>Webpack中文网</a> <a href=https://www.nodeapp.cn
								title="Node.js 中文文档"
								onclick="_hmt.push(['_trackEvent', 'link', 'click', 'nodeappcn'])"
								target=_blank>Node.js中文文档</a> <a href=https://www.npmjs.com.cn
								/ title="NPM 中文网"
								onclick="_hmt.push(['_trackEvent', 'link', 'click', 'npmjscomcn'])"
								target=_blank>NPM中文网</a> <a href=http://www.sasschina.com
								/ title="SASS 中文网"
								onclick="_hmt.push(['_trackEvent', 'link', 'click', 'sasschinacom'])"
								target=_blank>SASS中文网</a> <a href=https://www.jquery123.com
								/ title="jQuery 中文文档"
								onclick="_hmt.push(['_trackEvent', 'link', 'click', 'jquery123com'])"
								target=_blank>jQuery中文文档</a> <a href=https://www.parceljs.cn
								/ title="Parcel 中文文档"
								onclick="_hmt.push(['_trackEvent', 'link', 'click', 'parcel'])"
								target=_blank>Parcel中文文档</a> <a href=http://www.rollupjs.com
								/ title="Rollup 中文文档"
								onclick="_hmt.push(['_trackEvent', 'link', 'click', 'rollup'])"
								target=_blank>Rollup中文文档</a>
							<hr>
							<a href=https://www.upyun.com / title=又拍云
								onclick="_hmt.push(['_trackEvent', 'link', 'click', 'upyun'])"
								target=_blank>又拍云</a> <a href=http://www.lecloud.com / title=乐视云
								onclick="_hmt.push(['_trackEvent', 'link', 'click', 'lecloud'])"
								target=_blank>乐视云</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<a href=# id=back-to-top> <i class="fa fa-angle-up"></i> </a>
	<script src=https://cdn.bootcss.com/jquery/1.11.2/jquery.min.js></script>
	<script src=https://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js></script>
	<script src=https://cdn.bootcss.com/fitvids/1.1.0/jquery.fitvids.min.js></script>
	<script src=https://cdn.bootcss.com/highlight.js/9.0.0/highlight.min.js></script>
	<script src=/assets/js/main.min.js?1530120249519></script>
</body>
</html>
