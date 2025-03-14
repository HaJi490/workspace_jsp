<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>gugudan4</title>
</head>
<body>
<form method="get">
	<input type="number" name="val">
	<input type="submit" value="단 입력">
</form>

<%
//가로열로 안됨
int val = 3;
if(request.getParameter("val") != null){
	String sval = request.getParameter("val");
	val = Integer.parseInt(sval);
}

for(int i = val ; i < 10 ; i++){
	out.print( "<h2>"+ i +"단</h2>");

	for(int j = 1 ; j < 10 ; j++){
		out.print(i + "x" + j +"="+ i*j +"<br>");
	}
}


%>
</body>
</html>