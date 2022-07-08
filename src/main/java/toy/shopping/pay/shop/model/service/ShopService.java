package toy.shopping.pay.shop.model.service;

import java.util.ArrayList;

import toy.shopping.pay.shop.model.vo.Cart;
import toy.shopping.pay.shop.model.vo.Image;
import toy.shopping.pay.shop.model.vo.Order;
import toy.shopping.pay.shop.model.vo.OrderDetail;
import toy.shopping.pay.shop.model.vo.OrderStatus;
import toy.shopping.pay.shop.model.vo.PageInfo;
import toy.shopping.pay.shop.model.vo.Product;

public interface ShopService {

	int getPdtListCount();

	ArrayList<Product> selectPdtList(PageInfo pi);

	ArrayList<Image> selectThmbList();

	int insertProduct(Product pdt, ArrayList<Image> imageList);

	int insertCart(Cart cart);

	ArrayList<Cart> selectCartList();

	int deleteCart(int cartNo);

	Cart checkCart(Cart crt);
	
	// 장바구니 리스트 받아오기
//	// 방법 2
//	ArrayList<Cart> cartsForPay(ArrayList<Cart> cartNos);
	
	// 방법 3 배열 그대로 보내서 받아오기
	ArrayList<Cart> cartsForPay(int[] cartList);
	
	int ctAmountUpdate(ArrayList<Cart> carts);
	
	ArrayList<Image> imgForCartPay(ArrayList<Cart> carts);
	
	// 주문 정보 넘기기
	ArrayList<Cart> paidCartList(int[] cartNos);
	
	int insertOrder(ArrayList<OrderDetail> odList);
	
	// 주문 내역
	// 1. 페이징
	int getMyOrderListCount(String emailId);
	
	// 2. 주문 정보 받기
	ArrayList<OrderStatus> getMyOrderList(String emailId, PageInfo pi);
	
	// 주문 내역 - 상세보기
	ArrayList<OrderDetail> getMyOrderDetailList(int orderNo);

	ArrayList<Image> getMyOrderImgList(ArrayList<OrderDetail> orderDetailList);
	
	// 주문 관리
	int getOrderListCount();

	ArrayList<OrderStatus> getAdminOrderList(PageInfo pi);
	
	// 주문 관리 - 주문 상태 변경
	int changeOrderStatus(OrderStatus os);
	
	// 주문 관리 - 주문 목록
	int getOrderedListCount();
	
	ArrayList<OrderDetail> getOrderedList(PageInfo pi);
	





	


	
	



	



	

	


}
