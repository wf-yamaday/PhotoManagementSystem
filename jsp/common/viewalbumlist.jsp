<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="beans.Album" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% ArrayList list =(ArrayList)request.getAttribute("list"); %>
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
	<div class="ui breadcrumb">
	<a class="section" href="/PhotoManagementSystem/ViewAlbumServlet">年度選択</a>
	<i class="right angle icon divider"></i>
	<div class="activ section"><%=request.getAttribute("year") %>年</div>
	</div>
	<h1>アルバム一覧</h1>
	${message }
	<br>
	<% for(int i=0;i<list.size();i++){ %>
	<% Album album = (Album)list.get(i); %>
	<div class="ui cards">
		<div class="card">
			<div class="content">
			<a href="/PhotoManagementSystem/RemoveAlbum?id=<%=album.getAlbum_id() %>" onclick="if(!confirm('アルバムを削除しますか？\nアルバム内の写真を見ることができなくなります．\n※ディレクトリ，ファイルは削除されません．'))return false"><i class="right floated trash outline icon"></i></a>
			<div class="header">
				<i class=" folder icon"></i>
				<a href="/PhotoManagementSystem/ViewPhotoServlet?id=<%=album.getAlbum_id() %>">
				<c:out value="<%=album.getAlbum_name() %>" />
				</a>
			</div>
			</div>
		</div>
		</div>
	<% } %>
	</body>
</HTML>