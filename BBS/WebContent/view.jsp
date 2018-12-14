<!-- 게시글 내용 페이지 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="bbs.Bbs" %>
<%@ page import="bbs.BbsDAO" %>
<%@ page import="reply.Reply" %>
<%@ page import="reply.ReplyDAO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		// 게시판 보기 글이 유효한지 확인
		int bbsID = 0;
		if (request.getParameter("bbsID") != null) {
			bbsID = Integer.parseInt(request.getParameter("bbsID"));
			session.setAttribute("replyBbs", bbsID);
		}
		if (bbsID == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 글입니다.')");
			script.println("location.href = 'bbs.jsp'");
			script.println("</script>");
		}
		int pageNumber = 1;
		if (request.getParameter("pageNumber") != null) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
		Bbs bbs = new BbsDAO().getBbs(bbsID);
	%>
	<!-- 네비게이션(위쪽 화면(페이지 이동)) 구성 -->
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="main.jsp">JSP 게시판 웹 사이트</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.jsp">메인</a></li>
				<li class="active"><a href="bbs.jsp">게시판</a></li>
				<li><a href="guest.jsp">방명록</a></li>			
			</ul>
			<%
				if (userID == null) {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopu="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<li><a href="join.jsp">회원가입</a></li>
					</ul>
				</li>
			</ul>
			<% 
				} else{
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopu="true"
						aria-expanded="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul>
				</li>
			</ul>
			<%
				}
			%>
			
		</div>
	</nav>
	<!-- 게시글 내용 구성 -->
	<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3" style="background-color: #eeeeee; text-align: center;">게시판 글 보기</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 20%;">글 제목</td>	<!-- replaceAll: 해킹(스크립트) 방지 -->
						<td colspan="2"><%= bbs.getBbsTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>;") %></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="2"><%= bbs.getUserID() %></td>
					</tr>
					<tr>
						<td>작성일자</td>
						<td colspan="2"><%= bbs.getBbsDate().substring(0, 11) + bbs.getBbsDate().substring(11, 13) + "시" + bbs.getBbsDate().substring(14, 16) + "분" %></td>
					</tr>
					<tr>
						<td>내용</td> <!-- replaceAll: 특수문자 처리 -->
						<td colspan="2" style="min-height: 200px; text-align: left;"><%= bbs.getBbsContent() %></td>
					</tr>
				</tbody>
			</table>
			<a href="bbs.jsp" class="btn btn-primary">목록</a>
			<!-- 글의 작성자가 본인일 경우 수정&삭제 가능 -->
			<%
				if (userID != null && userID.equals(bbs.getUserID())) {
			%>
					<a href="update.jsp?bbsID=<%= bbsID %>" class="btn btn-primary">수정</a>
					<a onclick="return confirm('정말로 삭제하시겠습니까?')" href="deleteAction.jsp?bbsID=<%= bbsID %>" class="btn btn-primary">삭제</a>
			<%
				}
			%>
		
			<hr>
		
			<!-- 댓글(미완)  -->
		    <div class="container">
		    	<div class="row">
					<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
						<thead>
							<tr>
								<th style="background-color: #eeeeee; text-align: center;">작성자</th>
								<th style="background-color: #eeeeee; text-align: center;">댓글</th>
								<th style="background-color: #eeeeee; text-align: center;">작성일</th>
							</tr>
						</thead>
						<!-- 댓글 구성 -->
						<tbody>
							<%
								ReplyDAO replyDAO = new ReplyDAO();
								ArrayList<Reply> list = replyDAO.getList(pageNumber, bbsID);
								for(int i = 0; i < list.size(); i++) {
							%>
								<tr>
									<td><%= list.get(i).getReplyUser() %></td>
									<td><%= list.get(i).getReplyContent() %></td>
									<td><%= list.get(i).getReplyDate().substring(0, 11) + list.get(i).getReplyDate().substring(11, 13) + "시" + list.get(i).getReplyDate().substring(14, 16) + "분" %></td>
								</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
		        <label for="content">comment</label>
		        <form name="commentInsertForm" method="post" action="replyAction.jsp">
		            <div class="input-group">
		               <input type="hidden" name="bno"/>
		               <input type="text" class="form-control" name="replyContent" placeholder="내용을 입력하세요.">
		               <span class="input-group-btn">
		                    <input type="submit" class="btn btn-default" name="replyBtn" value="등록">
		               </span>
		              </div>
		        </form>
		    </div>
		   


		</div>
	</div>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>