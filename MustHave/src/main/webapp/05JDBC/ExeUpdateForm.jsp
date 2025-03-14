<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="common.JDBCConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC</title>
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
	<form action="ExeUpdate2.jsp" method="get">
		<table>
			<!-- name=""을 통해 식별하여 읽음 -->
			<tr><td>아이디</td> <td><input type="text" name="id"></td></tr>
			<tr><td>제목</td> <td><input type="text" name="title"></td></tr>
			<tr><td>컨텐츠</td> <td><input type="text" name="content"></td></tr>
		</table>
			<input type="submit" value="데이터 전송"/></td>
	</form>

</body>
</html>