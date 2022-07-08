<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script src="resources/js/jquery-3.6.0.min.js"></script><!-- .js 넣기 -->
    <!-- UIkit CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.14.3/dist/css/uikit.min.css" />

    <!-- UIkit JS -->
    <script src="https://cdn.jsdelivr.net/npm/uikit@3.14.3/dist/js/uikit.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/uikit@3.14.3/dist/js/uikit-icons.min.js"></script>
	
<style>
	body{font-family:noto sans; text-align:center;}
	.all{border:1px solid rgb(255, 92, 88); width: 800px; height: 500px; margin:auto;}
	table,tr,td{border: 1px solid gray; margin:auto; width: 700px; border-collapse : collapse;}
	#marginCnr{height:100px;}
	#but{margin:auto; display:block;}
	
</style>
</head>
<body>
<div class="all">
	<div id="marginCnr">
	
	</div>
	<table>
		<thead>
			<tr>
				<td>주문 날짜</td>
				<td>주문 상품</td>
				<td>단위 금액</td>
				<td>택배사</td>
				<td>상태</td>
				<td>기타</td>
			</tr>
		</thead>
		<tbody>
		
		
			<tr>
				<td>2022-03-01</td>
				<td>새우깡</td>
				<td>1500원</td>
				<td>3</td>
				<td>CJ</td>
				<td>배송 중</td>
				<td>담당 택배기사 코로나 확진으로 지연 예정</td>
			</tr>
			
			
		</tbody>
		<tfoot>
			<tr>
				<td>결제 금액</td>
				<td>4500</td>
			</tr>
		</tfoot>
	</table>
	
	<div id="marginCnr">
	
	</div>
	
    <span id="inputBtn">
    <p uk-margin>
        <button class="uk-button uk-button-danger" onclick="closeWindow()" id="but">확인</button>
    </p>
    </span>
</div>


<script>
	function closeWindow(){
		window.close();
	}
</script>
</body>
</html>