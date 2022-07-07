<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>주문 내역</title>
    <script src="${contextPath }/resources/js/jquery-3.6.0.min.js"></script>
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
    <div>
        <h2>주문 내역</h2>

    <!-- <div id="navOrder">
            <nav class="uk-navbar-container" uk-navbar>
                <div class="uk-navbar-left">
                    <ul class="uk-navbar-nav">
  
                        <li><a location.href="">주문</a></li>
                        <li><a location.href="">장바구니</a></li>
                        <li><a locaton.href=""></a></li>
                    </ul>
                </div>
            </nav>
    </div>  -->



    </div>

    <table id="check">
        <tr>
            <td>
                <p>주문 완료</p> 
                <p> 2</p>                  
            </td>
            <td> 
                <p>배송 중</p> 
                <p>4</p>  
            </td>
            <td> 
                <p>배송 완료</p> 
                <p>0</p>  
            </td>
        </tr>
    </table>

    <div id="list">
            <table class="uk-table uk-table-divider">
                    <thead>
                        <tr>
                            <th class="uk-table-shrink">상품</th>
                            <th class="uk-table-expand">상태</th>
                            <th class="uk-width-small">문의</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                                <td>사진</td>
                                <td>
                                    <p>
                                        <div class="click" onclick="location.href='detail.do'">새우깡</div>
                                        <div>1400원</div>
                                        <div class="click" id="deliveryDetail">배송 중</div>
                                        <input type="hidden" name="deliveryNo" value="1">
                                        <div>고객님의 상품을 안전하게 배송 중입니다</div>
                                    </p>
                                </td>
                            <td><button onclick="location.href='qna.do'" id="qna">문의하기</button></td>
                        </tr>
                        <tr>
                                <td>사진</td>
                                <td>
                                    <p>
                                        <div>새우깡</div>
                                         <div>1400원</div>
                                        <div>배송 중</div>
                                        <div>고객님의 상품을 안전하게 배송 중입니다</div>
                                    </p>
                                </td>
                            <td><button onclick="location.href='qna.bo'" id="qna">문의하기</button></td>
                        </tr>
                        <tr>
                                <td>사진</td>
                                <td>
                                    <p>
                                        <div>새우깡</div>
                                         <div>1400원</div>
                                        <div>배송 중</div>
                                        <div>고객님의 상품을 안전하게 배송 중입니다</div>
                                    </p>
                                </td>
                            <td><button class="click" onclick="location.href='qna.bo'" id="qna">문의하기</button></td>
                        </tr>                       
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
    	document.getElementById('deliveryDetail').onclick=function(){
    		window.open('deliDetail.do', '주문/배송 상세', 'width=1000, height=600');
    	}
    
    </script>
</body>
</html>