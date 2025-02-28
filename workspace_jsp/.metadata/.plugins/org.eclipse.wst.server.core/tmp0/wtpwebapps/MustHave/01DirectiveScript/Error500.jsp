<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지시어 - errorPage, isErrorPage 속성</title>
</head>
<body>
<%
try{
	int myAge = Integer.parseInt(request.getParameter("age")) + 10; //에러발생 > request 내장객체가 가지는 getParameter 메서드에 key인 age값을 줘야함
out.println("10년 후 당신의 나이는 " + myAge + "입니다.");
} catch(Exception e){
	out.println("오류가 발생했습니다. 다시 시도하세요");
}
%>
</body>
</html>