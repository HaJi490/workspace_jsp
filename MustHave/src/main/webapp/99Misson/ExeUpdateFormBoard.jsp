<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board form</title>
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

form {
	display : flex;
	justify-content : center;
	align-items : center;
	
}
</style>
<body>
	<form action="ExeUpdateBoard.jsp" method="post">
		<table>
		<tr>
			<td>title : <input type="text" name="title"></td>
		</tr>
		<tr>
			<td>content : <textarea rows="5" cols="20" name="content"></textarea></td>
		</tr>
		<tr>
			<td>id : 
				<select name="id">
					<option value="willhave">willhave</option>
					<option value="dohave">dohave</option>
					<option value="musthave">musthave</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colSpan="2"><input type="submit" value="데이터 전송"></td>
		</tr>
		</table>
	</form>
</body>
</html>