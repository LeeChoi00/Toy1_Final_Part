<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:import url="menubar.jsp"/>

	<!-- 1. 스크립틀릿 사용 -->
	<div id="error-container" style="text-align: center;">
		<h1>Error</h1>

		<h2 style="color: red;">${emg }</h2>
		<h2 style="color: blue;">${requestScope['javax.servlet.error.message'] }</h2>
		
		<a href="home.do">시작페이지로 돌아가기</a>
	</div>
</body>
</html>