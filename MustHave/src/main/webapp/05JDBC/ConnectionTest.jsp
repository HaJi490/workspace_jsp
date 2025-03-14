<%@page import="common.DBConnPool"%>
<%@page import="common.JDBCConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC</title>
</head>
<body>
	<h2>JDBC 테스트 1</h2>
	<%
	JDBCConnect jdbc1 = new JDBCConnect();
	jdbc1.close();
	%>
	
	<h2>JDBC 테스트 2</h2>
	<%
	//application #서버당 하나생기는 전역객체/ 내장객체중 가장 범위가 넓음 > 거의 안씀
	String driver = application.getInitParameter("MySQLDriver");
	String url = application.getInitParameter("MySQLURL");
	String id = application.getInitParameter("MySQLId");
	String pwd = application.getInitParameter("MySQLPwd");
	
	JDBCConnect jdbc2 = new JDBCConnect(driver, url, id, pwd);
	jdbc2.close();
	%>
	
	<h2>JDBC 테스트 3</h2>
	<%
	JDBCConnect jdbc3 = new JDBCConnect(application);
	jdbc3.close();
	%>
	
	<h2>커넥션 풀 테스트</h2>
	<%
	DBConnPool pool = new DBConnPool();
	pool.close();
	%>
</body>
</html>