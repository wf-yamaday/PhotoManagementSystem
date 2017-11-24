<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<HTML>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<link rel="stylesheet" type="text/css" href="/PhotoManagementSystem/Semantic-UI-CSS-master/semantic.min.css">
	<link rel="stylesheet" type="text/css" href="/PhotoManagementSystem/jsp/css/main.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.0/jquery.js" charset="utf-8"></script>
	<script src="/PhotoManagementSystem/Semantic-UI-CSS-master/semantic.min.js"></script>
	<HEAD>
		<TITLE>ログイン</TITLE>
	</HEAD>
	<BODY class="background">
	<div class="ui middle aligned center aligned grid">
	<div class="column">
		<h1 class="ui center aligned icon header"><i class="circular photo icon"></i>アルバム管理システム</h1>
		<div class="ui large form">
			<FORM action="/PhotoManagementSystem/Login" method="post">
			<div class="ui stacked secondary segment">
			<div class="field">
		<label>ユーザーID</label>
				<div class="ui left icon input">
				<input type="text" name="user_id" placeholder="User_id">
				<i class="user icon"></i>
				</div>
			</div>
				<div class="field">
				<label>パスワード</label>
					<div class="ui left icon input">
					<input type="password" name="password" placeholder="Password">
					<i class="lock icon"></i>
					</div>
				</div>
			<button class="ui fluid large teal submit button" type="submit">
			<i class="unlock alternate icon"></i>Login</button>
			</div>
			</FORM>
			
			${ERROR}
			
			</div>
		</div>
	</div>
	</BODY>
</HTML>