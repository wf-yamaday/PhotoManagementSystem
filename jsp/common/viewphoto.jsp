<%@page import="beans.Photo" %>
<%@page import="beans.Album" %>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% ArrayList<Photo> list = (ArrayList)request.getAttribute("list"); %>
<% Album album = (Album)request.getAttribute("album"); %>
<html>
	<link rel="stylesheet" type="text/css" href="/PhotoManagementSystem/Semantic-UI-CSS-master/semantic.min.css">
	<script type="text/javascript" src="/PhotoManagementSystem/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="/PhotoManagementSystem/js/zooming.min.js"></script>
	<script src="/PhotoManagementSystem/Semantic-UI-CSS-master/semantic.min.js"></script>
	
	<script type="text/javascript" src="/PhotoManagementSystem/js/modal-multi.js"></script>
	<link rel="stylesheet" type="text/css" href="/PhotoManagementSystem/jsp/css/modal-multi.css">
	
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
	<div class="ui breadcrumb">
	<a class="section" href="/PhotoManagementSystem/ViewAlbumServlet">年度選択</a>
	<i class="right angle icon divider"></i>
	<a class="section" href="/PhotoManagementSystem/ViewAlbumListServlet?year=<%=album.getYear() %>"><%=album.getYear() %>年</a>
	<i class="right angle icon divider"></i>
	<div class="activ section"><c:out value="<%=album.getAlbum_name() %>" /></div>
	</div>
	<br>
	<br>
	<a class="modal-syncer" data-target="edit-album-name"><h1><c:out value="<%=album.getAlbum_name() %>" /></h1></a>
	<br>
	
	<a class="modal-syncer" data-target="upload">
	<button class="ui teal button" type="button"><i class="upload icon"></i>写真を追加
	</button>
	</a>
	<br>
		${ERROR}
		${message}
	<br>
	<div class="ui four column grid">
	<% for(int i=0;i<list.size();i++){ %>
	<%Photo photo =(Photo)list.get(i); %>
	<div class="column">
		<div class="ui segment">
		<img class="ui fluid image" src="<%=photo.getPhoto_path()%>" data-action="zoom">
		<div class="ui text menu">
		<a href="<%=photo.getPhoto_path() %>" download><button class="mini ui teal basic button" type="button">Download</button></a>
		<div class="right menu">
		<a href="/PhotoManagementSystem/RemovePhoto?id=<%=photo.getPhoto_id() %>" onclick="if(!confirm('写真を削除しますか？\n※ファイルは削除されません．'))return false"><i class="trash outline icon"></i></a>
		</div>
		</div>
		</div>
	</div>
	<% } %>
	</div>
	<br>
	<br>
	
<!-- モーダルウィンドウのコンテンツ -->
	<div id="upload" class="modal-content">
	<h2>アルバムへの写真追加</h2>
	<form method="POST" enctype="multipart/form-data" accept="image/*" action="/PhotoManagementSystem/AddUpload?id=<%=album.getAlbum_id() %>">
	<input type="file" name="file" accept="image/*"><br>
	<br>
	<button class="ui primary button" type="submit">Upload</button>
	<button id="modal-close" type="button" class="ui button">Cancel</button>
	</form>
	</div>
	
	<div id="edit-album-name" class="modal-content">
	<h2>アルバム名の編集</h2>
	<form method="POST" action="/PhotoManagementSystem/EditAlbum?id=<%=album.getAlbum_id() %>">
	<div class="ui form">
	<label>アルバム名</label>
	<input type="text" name="album_name" value="<%=album.getAlbum_name() %>">
	<input type="hidden" name="flag" value=2>
	<br>
	<br>
	<button class="ui primary button" type="submit">Update</button>
	<button id="modal-close" type="button" class="ui button">Cancel</button>
	</div>
	</form>
	</div>
	
	</body>
</html>