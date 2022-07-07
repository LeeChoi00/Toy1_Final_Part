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
	// 1. 사용자 주문 내역 가져오기
	@Override
	public ArrayList<OrderDetail> myOrderList(String emailId) {
		return spDAO.myOrderList(sqlSession, emailId);
	}
	
	// 2. 주문 상태 내역 가져오기
	@Override
	public ArrayList<OrderStatus> myOrderStatusList(ArrayList<OrderDetail> orderList) {
		return spDAO.myOrderStatusList(sqlSession, orderList);
	}
	






}
