<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/header.jspf" %>
</head>
<body>
	<form action="login" method="post" id="login">
		<div>
			<h1>로그인 화면</h1>
			<input type="text" name="userID" value="아이디">
			<input type="text" name="userPassword" value="비밀번호">
			<input type="submit" value="로그인">
			<a href="join_view">회원가입</a>
		</div>
	</form>
</body>
</html>