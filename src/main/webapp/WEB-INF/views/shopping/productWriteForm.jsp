<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록 페이지</title>
<style>
	.area{clear:both;}
	.part{width:800px; height:400px;}
</style>
</head>
<body>
<c:import url="../common/menubar.jsp"/>

<br>

<div class="area">
	<div class="part" align="center">
		<br>
		<h4>상품 등록</h4>
		<!-- 상품 명, 가격, 한줄 평, ProductType='Y'기본,  -->
		<form action="insertProduct.sp" method="post" enctype="multipart/form-data">
			<table>
					<tr>
						<th>상품 명</th>
						<td>
							<input type="text" name="productName" id="value" placeholder="상품 명">
						</td>
					</tr>
					<tr>
						<th>가격</th>
						<td>
							<input type="text" name="productPrice" id="value" placeholder="상품 가격(원 표시 제외)">
						</td>
					</tr>			
					<tr>
						<th>한줄 평</th>
						<td>
							<textarea rows="10" cols="30" name="productOneline">
							</textarea>
						</td>
					</tr>				
					<tr>
						<th>썸네일</th>
						<td>
							<input type="file" id="thumbnail" name="uploadFiles">
						</td>
					</tr>				
					<tr> 
						<th>영양 사진</th>
						<td>
							<input type="file" id="thumbnail" name="uploadFiles">
						</td>
					</tr> 
	
			</table>
			<input type="submit" value="등록">
		</form>
	</div>




</div>


</body>
</html>