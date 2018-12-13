<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/header.jspf" %>
<body>
      <div id="wrap">
      <table>
         <tr>
            <td>��ȣ</td>
            <td>�̸�</td>
            <td>����</td>
            <td>��¥</td>
            <td>��ȸ��</td>
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
	     	<td colspan="5">
	     	<a href="list?page=1">[��]</a>
	     		<c:forEach var="i" begin="1" end="${totalCount}">
	     			<a href="list?page=${i}">[${i}]</a>
	     		</c:forEach>
	     	<a href="list?page=${totalCount}">[ó��]</a>
	     </tr>
      </table>
      <div class="write">
         <a href="write_view">���ۼ�</a>
      </div>
   </div>
</body>
</html>