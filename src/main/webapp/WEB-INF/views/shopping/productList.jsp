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
				<c:forEach var="i" items="${list }" varStatus='vs'>
					
					<div class="thumb-list" align="center">
					
<!-- 					<form onsubmit="return checkCart();" action="addCart.sp" method="post" > -->
 							<div>
									<c:forEach var="a" items="${thmbList }">
										<c:if test="${i.productNo== a.productNo }">
											<img src="resources/product_upload_images/${a.changeName}" width="200px" height="150px"> <!-- src안 넣으면 그냥 검은 테두리의 흰 박스로 나옴-->
										</c:if>
									</c:forEach>
							</div> 
							<p>			
								상품 명 : ${i.productName} }<br>
								<input type="hidden" id="productName" name="productName" value="${i.productName}">
								가격 : ${i.productPrice }원<br>
								<input type="hidden" id="productPrice" name="productPrice" value="${i.productPrice}">
								수량 선택 :<br>			
								<input type="number" id="productAmount" name="productAmount" min="1" max="10" name="age"><br>
								<!-- <input type="text" id="amount" name="amount" placeholder="희망 수량" -->
								<input type='button' value='장바구니' class="cart">
								<button type="button" id="payment">결제</button>
							</p>
							<input type="hidden" id="productNo${vs.index}" name="productNo" value="${i.productNo}">
<!-- 					</form>		 -->
					
					</div>
					
				</c:forEach>
			</c:if>
		</div>
		
		<div class="buttonArea">
			<input type="button" onclick="location.href='writeProductForm.sp'" id="insertBtn" value="상품 등록">
		</div>
		
	</div>
	
	<!-- 장바구니 이동 전 같은 품목이 이미 장바구니에 있는지 확인하기 -->
	<script>
		$('.cart').on('click', function(){
// 			console.log($(this).parent().next().val())
// 			var productNo = document.getElementById('productNo+${i}').val();
			var productNo = $(this).parent().next().val();
			var productName = $(this).siblings().prev('#productName').val();
			var productPrice = $(this).siblings().prev('#productPrice').val();
			var productAmount= $(this).siblings().prev('#productAmount').val();
			
			/* console.log(productName + ", " + productPrice+ ", " + productAmount); */
			
			
			$.ajax({
				url:'checkCart.sp',
				data: {productNo:productNo},
				type:'post',
				async : false,
				success : function(data){
					console.log(data);
					console.log(typeof data);
					
  					if(data == 'true'){
						console.log(data);
						alert('이미 장바구니에 추가된 품목입니다');	
					} else{
						console.log("false : " + data); 
						 console.log(productName + ", " + productPrice+ ", " + productAmount); 
						location.href='addCart.sp?productNo='+productNo+'&productName='+productName+'&productPrice='+productPrice+'&productAmount='+productAmount; 	
					}  
				},
				error : function(data){
					alert('오류입니다');
				}
			});
		});

	</script>
	
	
	<!-- 결제 페이지 이동 -->
<!-- 	<script>
		var productAmount = document.getElementById('productAmount').value
		$('#payment').click(function(){   /* 여기서 url ? 뒤를 숨기는 방법은 없는가? */  /* 나중에 사용자 아이디도 넣기 */
			location.href="payment.sp?productAmount="+productAmount +"&productNo="+${i.productNo}+
					"&productPrice="+${i.productPrice}+"&productName="+${i.productName};
		});
	</script> -->


<!-- 바로 결제, 장바구니를 ajax를 써볼까?  -->

</body>
</html>