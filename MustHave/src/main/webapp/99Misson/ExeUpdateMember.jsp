<%@page import="common.JDBCConnect"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member submit</title>
</head>
<body>
<% 
	//입력값 가져오기
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	String name = request.getParameter("name");
	
	//DB 연결
	JDBCConnect jdbc = new JDBCConnect();
	
	//쿼리문 생성
	String str = "Insert Into member(id, pass, name) Values ( ?, ?, ?)";
	PreparedStatement psm = jdbc.con.prepareStatement(str);
	
	psm.setString(1, id);
	psm.setString(2, pass);
	psm.setString(3, name);
	
	//쿼리 수행
	int result = psm.executeUpdate();
	out.println(result + "행이 완료되었습니다.");
	
	//연결 닫기
	//psm.close();
	jdbc.close();
	
%>	
	
</body>
</html>