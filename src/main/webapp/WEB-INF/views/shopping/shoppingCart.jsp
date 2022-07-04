<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  <!-- 리스트 길이 구하기 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>장바구니</title>
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    
    <script src="resources/js/jquery-3.6.0.min.js"></script><!-- .js 넣기 -->
    <!-- UIkit CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.14.3/dist/css/uikit.min.css" />

    <!-- UIkit JS -->
    <script src="https://cdn.jsdelivr.net/npm/uikit@3.14.3/dist/js/uikit.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/uikit@3.14.3/dist/js/uikit-icons.min.js"></script>
	
    <style>
    	body{font-family: noto sans;}
        #imge{width:100px;}
        #checkbox{width: 30px;}
        #list{width:800px; margin:auto;}
        #pay{padding-left: 900px;}
        #all{content-align:right; }
        #checkAll{ background-color: rgb(255, 92, 88);}
    </style>
</head>
<body>
	<%-- <c:import url="../common/menubar.jsp"/>  --%>
    <div>
        <h2>장바구니</h2>
 
		    
		    <div id="list">
		    <table class="uk-table uk-table-divider">
						<tr>
							<td>전체선택</td>
							<td></td>
							<td id="all"><input type="checkbox" id="checkAll" class="item"><td>
						</tr>
		            <thead>
		                <tr>
		                    <th class="uk-table-shrink" id="imge"> 상품명 </th>
		                    <th class="uk-table-expand">정보</th>
		                    <th class="uk-width-small">선택</th>
		                    <th class="uk-width-small">삭제</th>
		                </tr>
		            </thead>
		            <tbody>
		            	<c:forEach var="i" items="${cartList }">
		                   		<tr>
		                            <td>
										<c:forEach var="a" items="${thmbList }">
											<c:if test="${i.productNo== a.productNo }">
												<img src="resources/product_upload_images/${a.changeName}" width="150px" height="200px"> <!-- src안 넣으면 그냥 검은 테두리의 흰 박스로 나옴-->
											</c:if>
										</c:forEach>
									</td>
		                            <td>
		                                <p>
		                                    <div><a location.href=''>${i.productName }</a></div>
		                                    <div>총 가격 : ${i.productPrice }</div>                   
		                                    <div>안내사항</div>
		                                    <div>갯수 :
		                                    	<input type="number" id="amount" name="productAmount" min="1" max="10" name="age" value="${i.productAmount }"><br>
		                                    	<!-- <input type="text" name="noProduct"> -->
		                                    </div>
		                                </p>
		                            </td>
		                            <td id="checkbox">
		                            	<input type="checkbox" name="items" class="item" value="${i.cartNo }">
		                            </td> <!-- value에 상품번호 넣기 -->
		                       		<!-- <form action="deleteCart.sp" method="post"> -->
		                       		<td>
		                       			<input type="hidden" name="cartNo" value="${i.cartNo}">
		                       			<!-- <input type="button" id="deleteCart" value="삭제"> -->
		                       			<button type="button" onclick="location.href='deleteCart.sp?cartNo='+${i.cartNo}">삭제</button>
		                       			<%-- <button type="button" id="deleteCart" data-cartNo="${i.cartNo }">삭제</button> --%>
		                       		</td>
		                       		<!-- </form> --> 
		                        </tr>
		                  </c:forEach>

		            </tbody>
		        </table>
		    </div>
		
=			<!-- <form action="pay.do" method="post"> -->
		    <div id="pay">
		    <p uk-margin>
		        <button class="uk-button uk-button-danger" id="but">선택 결제</button>
		    </p>
		    </div>
		    
		    <!-- 선택 결제 -->
		    <script>
		    $('#but').on('click', function(){
		    	var cartList = new Array();
		    	/* var cartPdt = document.getElementsByName('items') */
		    	
		    	$("input[name=items]").each(function(index, item){
		    		if($(item).prop("checked")){
		    			cartList.push($(item).val());
		    		}
		    	});
		    	/* alert(cartList); */	
		    	
		    	location.href = 'pay.sp?cartList='+cartList;
		    });
		    </script>
		    
		    
		    <!-- </form> -->
		=
		    <ul class="uk-pagination uk-flex-center" uk-margin>
		            <li><a href="#"><span uk-pagination-previous></span></a></li>
		            <li><a href="#">1</a></li>
		            <li class="uk-disabled"><span>...</span></li>
		            <li><a href="#">5</a></li>
		            <li><a href="#">6</a></li>
		            <li class="uk-active"><span>7</span></li>
		            <li><a href="#">8</a></li>
		            <li><a href="#"><span uk-pagination-next></span></a></li>
		    </ul>
			
			<!-- 상품 삭제 폼 -->
			<form action="deleteCart.sp" method="post" class="deleteCartNo">
				<input type="hidden" name="cartNo" class="cartNo">
			</form>
			
		
		    <!-- 전체 선택버튼 누르면 전체 선택 됨 -->
		    <script>
		    	$(document).ready(function(){
		    		/* 전체 선택 버튼 클릭 */
			    	$('#checkAll').click(function(){
			    		if($('#checkAll').prop("checked")){
			    			$('.item').prop("checked", true);
			    		} else{
			    			$('.item').prop("checked", false);
			    		}
			    	}); 
		    		
		    		// 전체 체크된 체크 박스 중 하나가 해제되면 전체 버튼 해제
		    		$(".item").click(function(){
		    			if($("input[name='items']:checked").length == ${fn:length(cartList)}){
		    				$('#checkAll').prop("checked", true);
		    			} else{
		    				$('#checkAll').prop("checked", false);
		    			}
		    				
		    		});
		    	});
		    	
		    	// jquery 문제 
		    	// button없애고 form 안으로 들여옴
		    	// https://yuien.tistory.com/entry/Jquery-checkbox-%EC%A0%84%EC%B2%B4-%EC%B2%B4%ED%81%AC-%ED%95%B4%EC%A0%9C-%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95
				
/* 		    	$("#deleteCart").on("click", function(e){
		    		e.preventDefault();
		    		const cartNo = $(this).data("cartNo");
		    		$(".cartNo").val(cartNo);
		    		$(".deleteCartNo").submit();
		    	}); */
    		</script>
</body>
</html>