<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/resources/css/content_style.css" />" rel="stylesheet">
<title>로그인</title>
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