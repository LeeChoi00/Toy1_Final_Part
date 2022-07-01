<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- uikit -->
<!-- UIkit CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.14.3/dist/css/uikit.min.css" />

<!-- UIkit JS -->
<script src="https://cdn.jsdelivr.net/npm/uikit@3.14.3/dist/js/uikit.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/uikit@3.14.3/dist/js/uikit-icons.min.js"></script>

<style>
	.uk-navbar-left{background: #00008C;}
	.uk-navbar-right{background: #00008C;}
	.uk-navbar-nav{color:white;}
	.loginForm{border: 1px solid #00008C; border-radius: 5px; width: 320px; height: 50px;}
	#loginArea{float:right;  clear:both;}
	button{background: #00008C; border-radius: 10px; color: white;}
</style>
</head>
<body>

<nav class="uk-navbar-container" uk-navbar>
    <div class="uk-navbar-left">
        <ul class="uk-navbar-nav">
        </ul>
    </div>

    <div class="uk-navbar-right">

        <ul class="uk-navbar-nav">
            <li><a href="<%= request.getContextPath() %>/productList.sp" >상품</a></li>
            <li><a href="<%= request.getContextPath() %>/cart.sp" >장바구니-> 결제</a></li>
            <li><a href="<%= request.getContextPath() %>/shoppingList.sp" >주문 내역(고객)</a></li>
            <li><a href="<%= request.getContextPath() %>/shopManage.sp" >주문 관리(관리자)</a></li>
            <li><a href="<%= request.getContextPath() %>/login.sp" >로그인</a></li>
            <li><a href="<%= request.getContextPath() %>/signUp.sp" >회원가입</a></li>
        </ul>

    </div>

</nav>

<br>
<br>
<br>

<div id="loginArea">
	<form action="login.me" method="post">
		<table class="loginForm">
			<tr>
				<td>아이디</td>
				<td><input type=text id="userId" name="id" placeholder="아이디를 입력하세요"><td>
				<td rowspan="2">
					<button>로그인</button>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type=text id="userPwd" name="pwd" placeholder="비밀번호를 입력하세요"></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="button" onclick="location.href='signUp.me'">회원가입</button>
					<button type="button" onclick="location.href='find.me'">아이디/비밀번호 찾기</button>			
				</td>
			</tr>
		</table>
	</form>
 </div>  

</body>
</html>