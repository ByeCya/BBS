<!-- 로그인 확인 페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" class="user.User" scope="page" />
<jsp:setProperty name="user" property="userID" />
<jsp:setProperty name="user" property="userPassword" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		//이미 로그인을 한 사람은 로그인 페이지로 이동 불가
		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		if (userID != null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 로그인이 되어있습니다.')");
			script.println("loaction.href = 'main.jsp'");	// 로그인 페이지로 다시 돌려보냄
			script.println("</script>");
		}

		// ID, Password 확인 메소드
		UserDAO userDAO = new UserDAO();
		// login.jsp 페이지에서 ID, Password를 불러옴
		int result = userDAO.login(user.getUserID(), user.getUserPassword());
		// 로그인 성공
		if (result == 1) {
			session.setAttribute("userID", user.getUserID());
			session.setAttribute("userName", user.getUserName());
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href = 'main.jsp'");
			script.println("</script>");
		}
		// 비밀번호가 일치하지 않을 경우
		else if (result == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 틀립니다.')");
			script.println("history.back()");	// 로그인 페이지로 다시 돌려보냄
			script.println("</script>");
		}
		// 아이디가 존재하지 않을 경우
		else if (result == -1) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('존재하지 않는 아이디입니다.')");
			script.println("history.back()");	// 로그인 페이지로 다시 돌려보냄
			script.println("</script>");
		}
		// 데이터베이스에 오류가 발생할 경우
		else if (result == -2) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류가 발생했습니다.')");
			script.println("history.back()");	// 로그인 페이지로 다시 돌려보냄
			script.println("</script>");
		}
	%>
</body>
</html>