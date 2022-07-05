<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS Link  -->
<link href="buyPage.css" rel="stylesheet">
<!-- JQuery 최신버젼 CDN 링크  -->
<script src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
    <div class="tit_page">
        <h2 class="tit">주문서</h2>
    </div>

    <div id="main">
        <div id="content">
        
        <div class="user_form">
        <script id="delivery"></script>
        
       
       <hr>
       <div>
       	CART NOs :
       	${ cartList}
		
		<button type="button" onclick="'paymentNoti.jsp'">결제 다 되면</button>
       </div>
       <hr>
        
        <!-- 결제 목록 -->
        <h2 class="tit_section fst">주문상품</h2>
        <div id="itemList" class="page_aticle order_goodslist"><div class="info_product"><a class="btn" href="#none"><span class="screen_out">펼침 / 닫힘</span></a>
          <div class="short_info">[닥터아토] 알로에베라 수딩젤 150ml외 <span class="num">3개</span>상품을 주문합니다.</div></div>
          <ul class="list_product">
          
          	<c:forEach var='i' items='${carts}'>
	            <li>
	            
	              <div class="thumb">
	              	<c:forEach var='a' items='${images}'>
	              		<c:if test="${i.productNo == a.productNo }">
	              			<img src="resources/product_upload_images/${a.changeName}" width="200px" height="150px" alt="상품이미지">
	              		</c:if>
	              	</c:forEach>
	              </div>
	              <div class="name">
	                <div class="inner_name">${i.productName}</div>
	              </div>
	              <div class="ea">${i.productAmount }개</div>
	              <div class="info_price">
	                <span class="num">
	                  <span class="price">${i.productAmount * i.productPrice }원</span>
	                  <!-- <span class="cost"></span> -->
	                </span>
	              </div>
	            </li>
            </c:forEach>
            
            	<li>
            		<div>
            			<c:set var="total" value="0"/>
            			총 가격 :
            			<c:forEach var='i' items='${carts}'>
            				<c:set var="total" value="${total + (i.productAmount * i.productPrice) }"/>            				
            			</c:forEach>
            			<c:out value="${total}"/>
            		</div>
            	</li>
       
            
          </ul><input type="hidden" id="isPickupType" value="false"></div>        
  

        </div>
        <div class="bg_loading" id="bgLoading" style="display: none;">
        <div class="loading_show"></div>
        </div>
        
     
        </div>
        </div>
</body>
</html>