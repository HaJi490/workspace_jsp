<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.JDBCConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board submit</title>
</head>
<body>
<% 
//입력데이터 가져오기
String title = request.getParameter("title");
String content = request.getParameter("content");
String id = request.getParameter("id");

//DB 연결
JDBCConnect jdbc = new JDBCConnect();

//쿼리문 작성
String sql = "Insert Into board(title, content, id) Values (?, ?, ?)";
PreparedStatement psm = jdbc.con.prepareStatement(sql);

psm.setString(1, title);
psm.setString(2, content);
psm.setString(3, id);

//쿼리 수행
int result = psm.executeUpdate();
out.println(result + "행을 수행했습니다.");

//연결 닫기
jdbc.close();
%>

</body>
</html>