<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form>
	<input type="text" name="dan" value="단을 입력하세요">
	<input type="submit">
</form>
<%
for(int i = 1 ; i < 10 ; i++){
	out.print(  i+"단입니다.<br >");
	for(int j = 1 ; j < 10 ; j++){
		out.print(i + "x" + j +"="+ i*j +"<br>");
	}
}
%>
</body>
</html>