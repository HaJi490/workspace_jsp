<%@page import="common.JDBCConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Query submit</title>
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
<% 
	//DB 연결
	JDBCConnect jdbc = new JDBCConnect();
	
	//쿼리문 작성
	String sql = "select * from board";
	jdbc.stmt = jdbc.con.createStatement();
	
	//쿼리문 실행
	jdbc.rs = jdbc.stmt.executeQuery(sql);
%>
<table>
	<tr>
		<td>num</td>	
		<td>title</td>	
		<td>id</td>	
		<td>postdate</td>	
		<td>visitcount</td>	
	</tr>
	<%	
	//결과 확인
	while(jdbc.rs.next()){
	%>
		<tr>
		<%
		out.print("<td>" + jdbc.rs.getString("num")+ "</td>");
		out.print("<td>" + jdbc.rs.getString("title")+ "</td>");
		out.print("<td>" + jdbc.rs.getString("id")+ "</td>");
		out.print("<td>" + jdbc.rs.getString("postdate")+ "</td>");
		out.print("<td>" + jdbc.rs.getString("visitcount")+ "</td>");
		
		%>
		</tr>
<%
	};
%>
</table>
</body>
</html>