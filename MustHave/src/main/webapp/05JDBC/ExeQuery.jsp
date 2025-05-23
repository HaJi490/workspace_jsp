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
	<h2>회원 목록 조회 테스트(executeQuery() 사용)</h2>
	<%
	//DB 연결
	JDBCConnect jdbc = new JDBCConnect();
	
	//쿼리문 생성
	String sql = "SELECT id, pass, name, regidate FROM member";
	jdbc.stmt = jdbc.con.createStatement();
	
	//쿼리 수행
	jdbc.rs = jdbc.stmt.executeQuery(sql);
	
	//결과 확인(웹 페이지에 출력)
	while(jdbc.rs.next()){
		String id = jdbc.rs.getString(1);
		String pw = jdbc.rs.getString(2);
		String name = jdbc.rs.getString("name");
		java.sql.Date regidate = jdbc.rs.getDate("regidate");
	
		out.println(String.format("%s %s %s %s", id, pw, name, regidate) +
				"<br/>");
	}
	
	//연결 닫기
	jdbc.close();
	%>
</body>
</html>