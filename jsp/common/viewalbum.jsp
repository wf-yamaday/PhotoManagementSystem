<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% ArrayList list =(ArrayList)request.getAttribute("list");%>
<HTML>
	<link rel="stylesheet" type="text/css" href="/PhotoManagementSystem/Semantic-UI-CSS-master/semantic.min.css">
	<script src="/PhotoManagementSystem/js/jquery-3.2.1.min.js" charset="utf-8"></script>
	<script src="/PhotoManagementSystem/Semantic-UI-CSS-master/semantic.min.js"></script>
	<script type="text/javascript" src="/PhotoManagementSystem/js/main.js"></script>
	<head>
	<title>アルバム</title>
	</head>
	<body>
	<div class="ui inverted menu">
	<div class="ui inverted secondary pointing menu">
	<a class="item" href="/PhotoManagementSystem/Information">
	ホーム
	</a>
	<a class="active item" href="/PhotoManagementSystem/ViewAlbumServlet">
	アルバム
	</a>
	<a class="item" href="/PhotoManagementSystem/NewUpload">
	アップロード
	</a>
	</div>
	<div class="right menu">
	<a class="ui button pink" href="/PhotoManagementSystem/LogoutServlet">
	Logout
	</a>
	</div>
	</div>
	<%if(list.size() > 0){ %>
	<h1>年度選択</h1>
	<% for( int i=0;i<list.size();i++){ %>
	<div class="ui cards">
		<div class="card">
			<div class="content">
			<div class="header">
				<i class="folder icon"></i>
				<a href="/PhotoManagementSystem/ViewAlbumListServlet?year=<%=list.get(i)%>">
				<c:out value="<%=list.get(i) %>" />年
				</a>
			</div>
			</div>
		</div>
	</div>
	<% } %>
	<%}else{ %>
	<div class="ui positive icon message">
	<i class="info circle icon"></i>
	<div class="content">
	<div class="header">
	まだアルバムがありません
	</div>
	<p>タブメニューのアップロードからアルバムを作成し，写真をアップロードできます．</p>
	</div>
	</div>
	<%} %>
	</body>
</HTML>