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
	<!-- 입력한 단을 통해 url변경 -->
	<input type="text" name="val">
	<input type="submit">
</form>
<%
int val = 2;

out.print(request.getRequestURI());
//기본값 설정
String sval = request.getParameter("val");
if(sval != null){
	out.println("?" + request.getQueryString());
	val = Integer.parseInt(sval);
}
out.println("<br>");
out.print( val + "단입니다.<br >");
	for(int j = 1 ; j < 10 ; j++){
		out.print(val + "x" + j +"="+ val*j +"<br>");
	}

%>
</body>
</html>