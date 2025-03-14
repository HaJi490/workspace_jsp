<%@page import="common.JDBCConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Query Form</title>
</head>
<body>
<%
	//DB연결
	JDBCConnect jdbc = new JDBCConnect();

	//쿼리문 작성
	String sql = "select id from member";
	jdbc.stmt = jdbc.con.createStatement();
	
	//쿼리문 실행
	jdbc.rs = jdbc.stmt.executeQuery(sql);
	
%>
	<form action="ExeQuery2.jsp" method="post">
		<select name="id">
		<%
		//결과 확인
		while(jdbc.rs.next()){ %>
			<option value="<%= jdbc.rs.getString("id")%>"><%= jdbc.rs.getString("id")%></option>
			<%} %>
		</select>
		<input type="submit" value="검색">
	</form>
</body>
</html>