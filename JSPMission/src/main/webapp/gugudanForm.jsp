<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gugudan Form</title>
</head>
<body>
<form action="gugudanProc.jsp" method="get">
	<input type="radio" name="1" > Type1 : 세로 <br />
	<input type="radio" name="2" > Type2 : 가로 <br />
	<input type="radio" name="3" > Type3 : 하나만 출력 <br />
	<input type="radio" name="4" >Type4 : 여러개씩 묶어서 출력 <br />
	<input type="number" name="val">단
	<input type="submit" value="선택">
</form>
</body>
</html>