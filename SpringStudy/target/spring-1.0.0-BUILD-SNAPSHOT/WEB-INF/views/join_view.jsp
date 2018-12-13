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
	<form action="join" method="post" id="login">
		<h1>회원가입 화면<h1>
		<input type="text" name="userID">
		<input type="text" name="userPassword">
		<input type="text" name="userName">
		<input type ="radio" name="userGender" value="남자" checked>남자
		<input type ="radio" name="userGender" value="여자" checked>여자
		<input type="text" name="userEamil">
		<input type="submit" value="회원가입">
	</form>
</body>
</html>