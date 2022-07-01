<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.6.0.min.js"></script>
<style>
	.outer{width:1000px; height:700px; background: rgba(255,255, 255, 0.4); boarder: 5px solid white;
	margin-left:suto; margin-right:auto; margin-top:50px;}
	.thumbnatilArea{width:80px; margin-left:auto; margin-right:auto;}
	.buttonArea{width:80px; margin-left:auto; margin_right:auto;}
	.thumb-list{width:220px; border:1px solid black; display:inline-block; margin:10px; align:left;}
	.thumb-list:hover{opacity:0.8; cursor:pointer;}
	#insertBtn{background: #00008C; color:white;}
	#insertBtn:hover{cursor:pointer;}
</style> 
</head>
<body>
	<c:import url="../common/menubar.jsp"/>
	
	<div class="outer">
		<br>
		<h2 align="center">상품 게시판</h2>
		<div class="thumbnailArea">
			<c:if test="${list.isEmpty() || thmbList.isEmpty()}">
				<div>
				등록된 상품이 없습니다.
				</div>
			</c:if>
			<c:if test="${!list.isEmpty() && !thmbList.isEmpty() }">
				<c:forEach var="i" items="${list }">
					
					<div class="thumb-list" align="center">
					<form>
 							<div>
									<c:forEach var="a" items="${thmbList }">
										<c:if test="${i.productNo== a.productNo }">
											<img src="resources/product_upload_images/${a.changeName}" width="200px" height="150px"> <!-- src안 넣으면 그냥 검은 테두리의 흰 박스로 나옴-->
										</c:if>
									</c:forEach>
							</div> 
							<p>			
								상품 명 : ${i.productName} }<br>
								가격 : ${i.productPrice }원<br>
								수량 선택 :<br>			
								<input type="text" id="amount" name="amount" placeholder="희망 수량"><br>
								<input type="submit" value="결제">
								<input type="button" id="cart" value="장바구니">
							<p>
							<input type="hidden" name="productNo" value="${i.productNo}">
					</form>		
					</div>
					
				</c:forEach>
			</c:if>
		</div>
		
		<div class="buttonArea">
			<input type="button" onclick="location.href='writeProductForm.sp'" id="insertBtn" value="상품 등록">
		</div>
		
	</div>
	
	
	
	<script>
		$('#cart').click(function(){
			location.href="cart.sp?amount="+document.getElementById('amount').value;
		});
	</script>

</body>
</html>