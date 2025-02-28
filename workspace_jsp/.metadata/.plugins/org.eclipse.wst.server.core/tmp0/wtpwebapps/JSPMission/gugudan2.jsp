<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gugudan2</title>
<style>
table {
	border: 1px solid;
	border-collapse: collapse;
}

td, tr {
	border: 1px solid;
	padding: 4px 10px;
}

.title {
	text-align: center;
	background-color: lightgray;
}
</style>
</head>
<body>
<!-- jsp로 바뀔때 out.print되서 들어옴 -->
<table>
<%
for(int i = 2 ; i < 10 ; i++){
	out.print("<td>" + i+"단</td>");
}

 for(int i = 1 ; i < 10 ; i++){
	 /* 어떻게 묶어진건지, 태그에 class속성 적용, 하는방식 맞는지 */
	 out.print("<tr></tr>");
	for(int j = 2 ; j < 10 ; j++){
		out.print( "<td>"+j + "x" + i +"="+ i*j + "</td>");
	}
} 
%>
</table>
</body>
</html>