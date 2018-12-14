<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/header.jspf" %>
<body id="list">
     <table>
		<colgroup>
			<col class="number">
			<col class="writer">
			<col class="title">
			<col class="watching">
			<col class="write_day">
		</colgroup>
        <caption><h1>게시판 목록</h1></caption>
        <tr>
           <td>번호</td>
           <td>이름</td>
           <td>제목</td>
           <td>날짜</td>
           <td>조회수</td>
        </tr>
         <c:forEach items="${list}" var="dto">
            <tr>
               <td>${dto.bId}</td>
               <td>${dto.bName}</td>
               <td>
                  <c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
                  <a href="content_view?bId=${dto.bId}">${dto.bTitle}</a></td>
               <td>${dto.bDate}</td>
               <td>${dto.bHit}</td>
            </tr>
         </c:forEach>
     <tr>
     	<td colspan="5" class="list_page">
     	<a href="list?page=1">[끝]</a>
     		<c:forEach var="i" begin="1" end="${totalCount}">
     			<a href="list?page=${i}">[${i}]</a>
     		</c:forEach>
     	<a href="list?page=${totalCount}">[처음]</a>
     </tr>
     </table>
     <div class="write">
        <a href="write_view">글작성</a>
     </div>
</body>
</html>