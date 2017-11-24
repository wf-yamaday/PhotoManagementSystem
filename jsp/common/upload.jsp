<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="beans.Album" %>
<%@ page import="java.util.ArrayList" %>
<% ArrayList<Album> list = (ArrayList<Album>)request.getAttribute("list"); %>
<HTML>
	<link rel="stylesheet" type="text/css" href="/PhotoManagementSystem/Semantic-UI-CSS-master/semantic.min.css">
	<script src="/PhotoManagementSystem/js/jquery-3.2.1.min.js" charset="utf-8"></script>
	<script src="/PhotoManagementSystem/Semantic-UI-CSS-master/semantic.min.js"></script>
	<head>
	<title>アップロード</title>
	</head>
	<body>
	<div class="ui inverted menu">
	<div class="ui inverted secondary pointing menu">
	<a class="item" href="/PhotoManagementSystem/Information">
	ホーム
	</a>
	<a class="item" href="/PhotoManagementSystem/ViewAlbumServlet">
	アルバム
	</a>
	<a class="active item" href="/PhotoManagementSystem/NewUpload">
	アップロード
	</a>
	</div>
	<div class="right menu">
	<a class="ui button pink" href="/PhotoManagementSystem/LogoutServlet">
	Logout
	</a>
	</div>
	</div>
	<h1 class="ui header">写真をアップロード</h1>
	<form method="POST" enctype="multipart/form-data" accept="image/*" action="/PhotoManagementSystem/UploadServlet">
	<label>写真ファイル</label>
	<input type="file" name="file" accept="image/*"><br>
	<br>
	<div class="ui form">
	<div class="field">
	<div class="field">
	<label>年度</label>
	<select class="ui dropdown" name="year">
		<option value="2017">2017年</option>
		<option value="2016">2016年</option>
	</select>
	</div>
	<label>新規アルバム名</label>
	<input type="text" name="album_name" placeholder="アルバム名">
	<input type="hidden" name="flag" value=1>
	<br>
	<!-- <label>コメント</label>
	<textarea name="comment"></textarea><br> -->
	</div>
	
	<button class="ui primary button" type="submit">アップロード</button>
	</div>
	</form>
	${ERROR}
	${message}
	</body>
</HTML>