<%@page import="java.text.SimpleDateFormat"%>
<%@page import="beans.Information_board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<% ArrayList<Information_board> information = (ArrayList<Information_board>)request.getAttribute("information"); %>
<HTML>
	<link rel="stylesheet" type="text/css" href="/PhotoManagementSystem/Semantic-UI-CSS-master/semantic.min.css">
	<script src="/PhotoManagementSystem/js/jquery-3.2.1.min.js" charset="utf-8"></script>
	<script src="/PhotoManagementSystem/Semantic-UI-CSS-master/semantic.min.js"></script>
	<head>
		<title>トップページ</title>
	</head>
	<body>
	<div class="ui inverted menu">
	<div class="ui inverted secondary pointing menu">
	<a class="active item">
	ホーム
	</a>
	<a class="item" href="/PhotoManagementSystem/ViewAlbumServlet">
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
	<h1 class="ui header">お知らせ</h1>
	<% if(information.size() > 0){ %>
	<table class="ui teal table">
	<thead>
	<tr><th>Date</th>
	<th>Overview</th>
	</tr></thead>
	<tbody>
	<%for(int i= 0;i<information.size();i++){ %>
	<%Information_board information_board = (Information_board)information.get(i); %>
	<tr>
	<td><%=new SimpleDateFormat("yyyy/MM/dd").format(information_board.getDate()) %></td>
	<td><%=information_board.getUser_name() %>さんが<%=information_board.getAction() %></td>
	</tr>
	<%} %>
	</tbody>
	</table>
	<%}else{ %>
	<div class="ui positive icon message">
	<i class="info circle icon"></i>
	<div class="content">
	<div class="header">
	現在，お知らせはありません
	</div>
	<p>ここには最新の５件のお知らせが表示されます．</p>
	</div>
	</div>
	<%} %>
	</body>
</HTML>