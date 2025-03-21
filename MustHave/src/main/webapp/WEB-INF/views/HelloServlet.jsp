<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloServlet.jsp</title>
</head>
<body>
	<h2>web.xml에서 매핑 후 JSP에서 출력하기</h2>
	<p>
		<strong><%= request.getAttribute("message") %></strong><!-- 호출안됨>> jsp로 열면 null뜨니까 여기 두는게 맞음 -->
		<br/>
		<a href="./HelloServlet.do">바로가기</a>
	</p>
</body>
</html>