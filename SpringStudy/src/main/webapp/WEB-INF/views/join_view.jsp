<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/header.jspf" %>
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