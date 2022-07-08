<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>주문 내역</title>
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <!-- UIkit CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.14.3/dist/css/uikit.min.css" />
	
    <!-- UIkit JS -->
    <script src="https://cdn.jsdelivr.net/npm/uikit@3.14.3/dist/js/uikit.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/uikit@3.14.3/dist/js/uikit-icons.min.js"></script>
    <style>
    	body{font-family: noto sans;}
        #check{ margin: auto;  width: 60%; height: 60px; padding-bottom: 40px; padding-top: 20px;}
        #check td{text-align: center; border: 1px solid rgb(255, 92, 88);; width: 120px; height: 60px; 
                   color: black;}
        #list{width:800px; margin:auto;}
        #qna{width:70px; background-color: rgb(255, 92, 88);}
        
        
        .click:hover{cursor: pointer;}
    </style>
</head>
<body>
	<c:import url="../common/menubar.jsp"/>

    <div>
        <h2>주문 내역</h2>
    </div>

    <table id="check">
    	<!-- forEach 돌려서 배송 준비, 배송 중, 배송 완료, 환불, 취소 해당될 때마다 1씩 증가-->
    	<c:set var="ordered" value="0"/>
    	<c:set var="delivering" value="0"/>
    	<c:set var="complete" value="0"/>
    	<c:set var="refund" value="0"/>
    	<c:set var="cancel" value="0"/>
    	<c:forEach var="i" items='${orderList }'>
    		<c:if test="${i.orderStatusName == '주문' || i.orderStatusName == '상품 준비' || i.orderStatusName =='배송 준비' }">
    			<c:set var= "ordered" value="${ordered + 1 }"/>
    		</c:if>
    		<c:if test="${i.orderStatusName == '배송 중'}">
    			<c:set var="delivering" value="${delivering +1 }"/>
    		</c:if>
    		<c:if test="${i.orderStatusName == '배송 완료' }">
    			<c:set var="complete" value="${complete +1 }"/>
    		</c:if>
    		<c:if test="${i.orderStatusName == '환불' }">
    			<c:set var="refund" value="${refund +1 }"/>
    		</c:if>
    		<c:if test="${i.orderStatusName =='취소' }">
    			<c:set var="cancel" value="${cancel +1 }"/>
    		</c:if>
    	</c:forEach>
    	
        <tr>
            <td>
                <p>주문 완료</p> 
                <p> ${ordered }</p>                  
            </td>
            <td> 
                <p>배송 중</p> 
                <p> ${delivering }</p>  
            </td>
            <td> 
                <p>배송 완료</p> 
                <p> ${complete }</p>  
            </td>
            <td> 
                <p>환불</p> 
                <p> ${refund }</p>  
            </td>
            <td> 
                <p>취소</p> 
                <p>${cancel }</p>  
            </td>
        </tr>
    </table>
    
    <br>
	<button onclick="location.href='qna.do'" id="qna">문의하기</button>
    <br>
    
    <div id="list">
            <table class="uk-table uk-table-divider">
                    <thead>
                        <tr>
                        	<th class="uk-width-small">주문 번호</th>
                            <th class="uk-table-expand">상태</th>
                            <th class="uk-width-small">상세보기</th>
                        </tr>
                    </thead>
                    <tbody>
                    
                    	<c:forEach var="i" items="${orderList }" varStatus="vs">
	                        <tr>
	                        	<td align="center">
	                        		<div> ${i.orderNo }</div>
	                        	</td>
	                            <td>
	                                <p>
	                                    <c:if test="${i.orderStatusName == '주문' || i.orderStatusName == '상품 준비' || i.orderStatusName =='배송 준비' }">                                    	
	                                    	<div>${i.orderDate }에 주문</div>
	                                    	<div>${i.totalPrice }원</div>
	                                    	<!-- <div id="orderStatus">준비 중</div> -->
	                                    	<div>고객님의 상품을 준비 중입니다</div>
	                                    </c:if>
	                                    <c:if test="${i.orderStatusName == '배송 중'}">
	                                    	<div>${i.orderDate }에 주문</div>
	                                    	<div>${i.totalPrice }원</div>
	                                    	<!-- <div id="orderStatus">배송 중</div> -->	                                    
	                                    	<div>고객님의 상품을 신속하게 배송 중입니다</div>
	                                    </c:if>	  
	                                    <c:if test="${i.orderStatusName == '배송 완료'}">
	                                    	<div>${i.orderDate }에 주문</div>
	                                    	<div>${i.totalPrice }원</div>
	                                    	<!-- <div id="orderStatus">배송 완료</div>	 --> 	                                    
	                                    	<div>고객님의 상품이 안전하게 도착했습니다</div>
	                                    </c:if>	
	                                    <c:if test="${i.orderStatusName == '환불' }">
	                                    	<div>주문 번호 ${i.orderNo }이 환불되었습니다</div>
	                                    </c:if>	
	                                    <c:if test="${i.orderStatusName == '취소'}">
	                                    	<div>주문 번호 ${i.orderNo }이 취소되었습니다</div>
	                                    </c:if>		                                    	                                    	                                                                      
	                                </p>
	                            </td>
	                            <td>
	                            	<button class="orderDetail">상세보기</button>
	                            	<input type="hidden" id="orderNo" name="orderNo" value="${i.orderNo }">
	                            </td>
	                        </tr> 
                        </c:forEach>                    
                    </tbody>
            </table>
    </div>
 
    
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
    
    
    <script>
    	$('.orderDetail').on('click', function(){
    		var orderNo = $(this).siblings('#orderNo').val();
    		
    		window.open('detail.sp?orderNo='+orderNo, '주문 상세', 'width=1000, height=600');    		
    	});
    
    </script>
</body>
</html>