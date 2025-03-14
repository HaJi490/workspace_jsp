<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member form</title>
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
	<form action="ExeUpdateMember.jsp" method="post">
	<table>
	<!-- DB에 board 입력하는 jsp작성 -->
		<tr>
			<td>아이디 : <input type="text" name="id"></td>
		</tr>
		<tr>
			<td>패스워드 : <input type="text" name="pass"></td>
		</tr>
		<tr>	
			<td>이름 : <input type="text" name="name"></td>
		</tr>
			<td><input type="submit" value="입력"/></td>
	</table>
	</form>
</body>
</html>