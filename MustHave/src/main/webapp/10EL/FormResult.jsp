<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현언어(EL)-폼값처리</title>
</head>
<body>
	<h3>EL로 폼값 받기</h3>
	<ul>
		<li>이름 : ${ param.name }</li>
		<li>성별 : ${ param.gender }</li>
		<li>학력 : ${ param.grade }</li>
		<li>관심사항 : ${ paramValues.inter[0]}
		 			${ paramValues.inter[1]}
		 			${ paramValues.inter[2]}
		 			${ paramValues.inter[3]}</li>
		<li>paramValues: ${paramValues }</li><!-- 폼에서 넘어온 모든 값 -->
		<li>paramValues.inter: ${paramValues.inter }</li><!-- inter배열의 해시코드 -->
		<li>param.inter: ${param.inter }</li><!-- 선택한것 중 제일 앞에꺼 출력 -->
	</ul>
</body>
</html>