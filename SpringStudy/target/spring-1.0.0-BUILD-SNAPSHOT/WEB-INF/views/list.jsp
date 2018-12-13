<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> 
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
   <div id="wrap">
      <table>
      <colgroup>
         <col class="first_line">
         <col class="second_line">
         <col class="third_line">
         <col class="forth_line">
         <col class="fifth_line">
      </colgroup>
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
      </table>
      <div class="write">
         <a href="write_view">글작성</a>
      </div>
   </div>
</body>
</html>