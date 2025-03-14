<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.JDBCConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String id = request.getParameter("id");
String title = request.getParameter("title");
String content = request.getParameter("content");

	//DB 연결
	JDBCConnect jdbc = new JDBCConnect();
	
	//쿼리문 생성
	String sql = "Insert into board(id, title, content) Values (?, ?, ?)";
	PreparedStatement psm = jdbc.con.prepareStatement(sql);
	
	psm.setString(1, id);
	psm.setString(2, title);
	psm.setString(3, content);
	//board테이블의 postdate는 값을 안넣으면 디폴트가 오늘(board> ! > DDL)
	
	//쿼리 수행
	int result = psm.executeUpdate();
	out.println(result + "행이 입력되었습니다");
	
	//연결 닫기
	jdbc.close();
%>
	

</body>
</html>