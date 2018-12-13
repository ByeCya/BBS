<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/include/header.jspf" %>
</head>
<body>
   <table>
      <colgroup>
         <col class="title_line">
         <col>
         <col class="title_line">
      </colgroup>
      <form action="modify" method="post">
         <input type="hidden" name="bId" value="${content_view.bId}">
         <tr>
            <td> 번호 </td>
            <td> ${content_view.bId} </td>
         </tr>
         <tr>
            <td> 조회수 </td>
            <td> ${content_view.bHit} </td>
         </tr>
         <tr>
            <td> 이름 </td>
            <td> <input type="text" name="bName" value="${content_view.bName}"></td>
         </tr>
         <tr>
            <td> 제목 </td>
            <td> <input type="text" name="bTitle" value="${content_view.bTitle}"></td>
         </tr>
         <tr>
            <td> 내용 </td>
            <td> <textarea rows="10" name="bContent" >${content_view.bContent}</textarea></td>
         </tr>
   </table>
	   <div class="write">
	      <input type="submit" class="write" value="수정">
	      <a href="list">목록보기</a>
	      <a href="delete?bId=${content_view.bId}">삭제</a>
	      <a href="reply_view?bId=${content_view.bId}">답변</a>
	   </div>
      </form>
</body>
</html>