<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//cdn.ckeditor.com/4.10.0/standard/ckeditor.js"></script>
<%@ include file="/WEB-INF/include/header.jspf" %>
</head>
<body id="write">
	<table>
		<colgroup>
			<col class="inform">
			<col class="insert">
		</colgroup>
        <caption><h1>게시글 작성</h1></caption>
		<form action="write" method="post">
			<tr>
				<td> 이름 </td>
				<td> <input type="text" name="bName"> </td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td> <input type="text" name="bTitle"> </td>
			</tr>
			<tr>
				<td> 내용 </td>
				<!-- <td> <textarea name="bContent"></textarea> </td>  -->
	            <td>
					<textarea name="bContent" id="editor1"></textarea>
					<script>
						CKEDITOR.replace('editor1');
					</script>
				</td>
			</tr>
		</form>
	</table>
	<input type="submit" value="입력"> 
	<a href="list">목록보기</a>
</body>
</html>