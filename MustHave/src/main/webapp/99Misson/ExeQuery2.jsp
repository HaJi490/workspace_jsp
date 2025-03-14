<%@page import="common.JDBCConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
table {
	border: 1px solid;
	border-collapse: collapse;
}

td, tr {
	border: 1px solid gray;
	padding: 4px 10px;
}

.title {
	text-align: center;
	background-color: lightgray;
}
	
}
</style>
<body>
<% 
String id = request.getParameter("id");
	//DB 연결
	JDBCConnect jdbc = new JDBCConnect();
	
	//쿼리문 작성
 	 String sql;
	if(id != null){
		sql= "select * from board where id =?";
		jdbc.psmt = jdbc.con.prepareStatement(sql);
		
		//쿼리문 실행
		jdbc.psmt.setString(1, id);
		jdbc.rs = jdbc.psmt.executeQuery();
	}
	//id==null인경우 > ExeQuery2에서 바로 실행될때
	else{
		sql= "select * from board";
		jdbc.stmt = jdbc.con.createStatement();
		jdbc.rs= jdbc.stmt.executeQuery(sql);
	}  
	
/* 	 String sql;
		sql= "select * from board where id =?";
		jdbc.psmt = jdbc.con.prepareStatement(sql);
		
		//쿼리문 실행
		jdbc.psmt.setString(1, id);
		jdbc.rs = jdbc.psmt.executeQuery();
	
	if(jdbc.rs == null){
		sql= "select * from board";
		jdbc.stmt = jdbc.con.createStatement();
		jdbc.rs= jdbc.stmt.executeQuery(sql);
	}  */
	
%>
<table class="id">
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