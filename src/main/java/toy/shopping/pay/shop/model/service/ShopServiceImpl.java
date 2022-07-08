package toy.shopping.pay.shop.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import toy.shopping.pay.shop.model.dao.ShopDAO;
import toy.shopping.pay.shop.model.vo.Cart;
import toy.shopping.pay.shop.model.vo.Image;
import toy.shopping.pay.shop.model.vo.Order;
import toy.shopping.pay.shop.model.vo.OrderDetail;
import toy.shopping.pay.shop.model.vo.OrderStatus;
import toy.shopping.pay.shop.model.vo.PageInfo;
import toy.shopping.pay.shop.model.vo.Product;

@Service
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopDAO spDAO;
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int getPdtListCount() {
		return spDAO.getPdtListCount(sqlSession);
	}

	@Override
	public ArrayList<Product> selectPdtList(PageInfo pi) {
		return spDAO.selectPdtList(sqlSession, pi);
	}

	@Override
	public ArrayList<Image> selectThmbList() {
		return spDAO.selectThmbList(sqlSession);
	}

	@Override
	@Transactional
	public int insertProduct(Product pdt, ArrayList<Image> imageList) {
		int pdtResult = spDAO.insertProduct(sqlSession, pdt);
		int imgResult = 0;
		if(pdtResult>0) {
			imgResult = spDAO.insertPdtImages(sqlSession, imageList);
		}
		
		return imgResult;
	}

	@Override
	public int insertCart(Cart cart) {
		return spDAO.insertCart(sqlSession, cart);
	}

	@Override
	public ArrayList<Cart> selectCartList() {
		return spDAO.selectCartList(sqlSession);
	}

	@Override
	public int deleteCart(int cartNo) {
		return spDAO.deleteCart(sqlSession, cartNo);
	}

	@Override
	public Cart checkCart(Cart crt) {
		return spDAO.checkCart(sqlSession, crt);
	}

	// 장바구니 리스트 받아오기
//	// 방법 2
//	@Override
//	public ArrayList<Cart> cartsForPay(ArrayList<Cart> cartNos) {
//		return spDAO.cartsForPay(sqlSession, cartNos);
//	}

	@Override
	public ArrayList<Cart> cartsForPay(int[] cartList) {
		return spDAO.cartsForPay(sqlSession, cartList);
	}	

	@Override
	public int ctAmountUpdate(ArrayList<Cart> carts) {
		return spDAO.ctAmountUpdate(sqlSession, carts);
	}	
	
	@Override
	public ArrayList<Image> imgForCartPay(ArrayList<Cart> carts) {
		return spDAO.imgForCartPay(sqlSession, carts);
	}
	
	// 주문 정보 저장
	// 1. 주문한 장바구니 품목 가져오기
	@Override
	public ArrayList<Cart> paidCartList(int[] cartNos) {
		return spDAO.paidCartList(sqlSession, cartNos);
	}	
	
	// 2. 주문 정보 넘기기
	@Override
	public int insertOrder(ArrayList<OrderDetail> odList) {
		return spDAO.insertOrder(sqlSession, odList);
	}
	
	// 주문 내역
	// 1. 페이징
	@Override
	public int getMyOrderListCount(String emailId) {
		return spDAO.getMyOrderListCount(sqlSession, emailId);
	}	

	// 2. 사용자 주문 정보 가져오기
	// 2.1 주문 내역 가져오기
	@Override
	public ArrayList<OrderStatus> getMyOrderList(String emailId, PageInfo pi) {
		return spDAO.getMyOrderList(sqlSession, emailId, pi);
	}


	// 주문 내역 - 상세보기
	// 1. 상세보기 리스트 가져오기
	@Override
	public ArrayList<OrderDetail> getMyOrderDetailList(int orderNo) {
		return spDAO.getMyOrderDetailList(sqlSession, orderNo);
	}

	// 2. 상세보기 이미지 리스트 가져오기
	@Override
	public ArrayList<Image> getMyOrderImgList(ArrayList<OrderDetail> orderDetailList) {
		return spDAO.getMyOrderImgList(sqlSession, orderDetailList);
	}
	
	// 주문 관리
	@Override
	public int getOrderListCount() {
		return spDAO.getOrderListCount(sqlSession);
	}

	@Override
	public ArrayList<OrderStatus> getAdminOrderList(PageInfo pi) {
		return spDAO.getAdminOrderList(sqlSession, pi);
	}
	
	// 주문 관리 - 주문 상태 변경
	@Override
	public int changeOrderStatus(OrderStatus os) {
		return spDAO.changeOrderStatus(sqlSession, os);
	}
	
	// 주문 관리 - 주문 목록
	@Override
	public int getOrderedListCount() {
		return spDAO.getOrderedListCount(sqlSession);
	}

	@Override
	public ArrayList<OrderDetail> getOrderedList(PageInfo pi) {
		return spDAO.getOrderedList(sqlSession, pi);
	}
	
	
	
	






}
