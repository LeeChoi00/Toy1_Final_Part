package toy.shopping.pay.shop.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import toy.shopping.pay.shop.model.vo.Cart;
import toy.shopping.pay.shop.model.vo.Image;
import toy.shopping.pay.shop.model.vo.Order;
import toy.shopping.pay.shop.model.vo.OrderDetail;
import toy.shopping.pay.shop.model.vo.OrderStatus;
import toy.shopping.pay.shop.model.vo.PageInfo;
import toy.shopping.pay.shop.model.vo.Product;

@Repository
public class ShopDAO {
	// 1. Product 카테고리
	public int getPdtListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("shoppingMapper.getPdtListCount");
	}
	
	public ArrayList<Product> selectPdtList(SqlSessionTemplate sqlSession, PageInfo pi) {
		// 1. 건너뛸 페이지 수
		int offset = (pi.getCurrentPage()-1) * pi.getBoardLimit();
		
		// 2. Rowbounds
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		return (ArrayList)sqlSession.selectList("shoppingMapper.selectPdtList", null, rowBounds);
	}

	public ArrayList<Image> selectThmbList(SqlSessionTemplate sqlSession) {
		return (ArrayList)sqlSession.selectList("shoppingMapper.selectThmbList");
	}

	public int insertProduct(SqlSessionTemplate sqlSession, Product pdt) {
		return sqlSession.insert("shoppingMapper.insertProduct", pdt);
	}

	public int insertPdtImages(SqlSessionTemplate sqlSession, ArrayList<Image> imageList) {
		int imgResult = 0;
		for(int i = 0; i<imageList.size();i++) {
			sqlSession.insert("shoppingMapper.insertPdtImages", imageList.get(i));
			imgResult++;
		}
		return imgResult;
	}

	public int insertCart(SqlSessionTemplate sqlSession, Cart cart) {
		return sqlSession.insert("shoppingMapper.insertCart", cart);
	}

	public ArrayList<Cart> selectCartList(SqlSessionTemplate sqlSession) {
		return (ArrayList)sqlSession.selectList("shoppingMapper.selectCartList");
	}

	public int deleteCart(SqlSessionTemplate sqlSession, int cartNo) {
		return sqlSession.delete("shoppingMapper.deleteCart", cartNo);
	}

	public Cart checkCart(SqlSessionTemplate sqlSession, Cart crt) {
		return sqlSession.selectOne("shoppingMapper.checkCart", crt);
	}

	
	// 장바구니 리스트 받아오기
//	// 방법 2	
//	public ArrayList<Cart> cartsForPay(SqlSessionTemplate sqlSession, ArrayList<Cart> cartNos) {
//		ArrayList<Cart> carts = new ArrayList<Cart>();
//		for(int i = 0; i<cartNos.size();i++) {
//			Cart cart = null;
//			cart = sqlSession.selectOne("shoppingMapper.cartsForPay", cartNos.get(i));
//			carts.add(cart);
//		}
//		return carts;
//	}
	
	// 방법 3
	public ArrayList<Cart> cartsForPay(SqlSessionTemplate sqlSession, int[] cartList) {
		ArrayList<Cart> carts = new ArrayList<Cart>();
		
		for(int i = 0; i<cartList.length;i++) {
			int cartNo = cartList[i];
			Cart cart = sqlSession.selectOne("shoppingMapper.cartsForPay", cartNo);
			carts.add(cart);
		}
		return carts;
	}	

	public int ctAmountUpdate(SqlSessionTemplate sqlSession, ArrayList<Cart> carts) {
		int result = 0;
		for(int i = 0; i<carts.size();i++) {
			result = result + sqlSession.update("shoppingMapper.ctAmountUpdate", carts.get(i));
		}
		return result;
	}	
	
	public ArrayList<Image> imgForCartPay(SqlSessionTemplate sqlSession, ArrayList<Cart> carts) {
		ArrayList<Image> images = new ArrayList<Image>();
		for(int i = 0; i<carts.size();i++) {
			Image img = sqlSession.selectOne("shoppingMapper.imgForCartPay", carts.get(i));
			images.add(img);
		}
		
		return images;
	}
	
	// 주문 정보 저장
	// 1. 주문 장바구니 품목 가져오기
	public ArrayList<Cart> paidCartList(SqlSessionTemplate sqlSession, int[] cartNos) {
		ArrayList<Cart> carts = new ArrayList<Cart>();
		for(int i = 0; i<cartNos.length;i++) {
			Cart cart = new Cart();
			int cartNo = cartNos[i];
			cart = sqlSession.selectOne("shoppingMapper.paidCartList", cartNo);
			carts.add(cart);
		}
		return carts;
	}
	
	// 2. 주문 정보 넘기기
	public int insertOrder(SqlSessionTemplate sqlSession, ArrayList<OrderDetail> odList) {
		int oRslt = 0;
		int osRslt = 0;
		int odRslt = 0;
		System.out.println(odList);
		if(!odList.isEmpty()) {
			oRslt = sqlSession.insert("shoppingMapper.insertOrder", odList.get(0));
			osRslt = sqlSession.insert("shoppingMapper.insertOrderStatus");
		}
		for(int i = 0; i<odList.size();i++) {
			odRslt = odRslt + sqlSession.insert("shoppingMapper.insertOrderDetail", odList.get(i));
		}
		
		int result = oRslt + osRslt + odRslt;
		// 정상 실행의 경우
		// oRslt(1) + osRlt(1) + odRslt(odList.size()) 만큼의 값이 나와야 함
		
		return result;
	}
	
	// 주문 내역
	// 1. 페이징
	public int getMyOrderListCount(SqlSessionTemplate sqlSession, String emailId) {
		return sqlSession.selectOne("shoppingMapper.getMyOrderListCount", emailId);
	}
	
	// 2. 주문 정보 받기
	// 2.1 주문 리스트 받기
	public ArrayList<OrderStatus> getMyOrderList(SqlSessionTemplate sqlSession, String emailId, PageInfo pi) {
		// 1. 건너뛸 페이지 수
		int offset = (pi.getCurrentPage()-1)*pi.getBoardLimit();
		
		// 2. Rowbounds
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());	
		return (ArrayList)sqlSession.selectList("shoppingMapper.myOrderList", emailId, rowBounds);
	}


	// 주문 내역 - 상세보기
	// 1. 상세보기 리스트 가져오기
	public ArrayList<OrderDetail> getMyOrderDetailList(SqlSessionTemplate sqlSession, int orderNo) {
		return (ArrayList)sqlSession.selectList("shoppingMapper.myOrderDetailList", orderNo);
	} 
	
	// 2 주문 상세보기 이미지 리스트 받기
	public ArrayList<Image> getMyOrderImgList(SqlSessionTemplate sqlSession, ArrayList<OrderDetail> orderDetailList) {
		ArrayList<Image> orderImgList = new ArrayList<Image>();
		for(int i = 0; i<orderDetailList.size();i++) {
			Image img = sqlSession.selectOne("shoppingMapper.getMyOrderImgList", orderDetailList.get(i).getProductNo());
			orderImgList.add(img);
		}
		return orderImgList;
	}
	
	// 주문 관리
	// 1. 페이징
	public int getOrderListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("shoppingMapper.getOrderListCount");
	}
	
	// 2. 주문 내역 가져오기
	public ArrayList<OrderStatus> getAdminOrderList(SqlSessionTemplate sqlSession, PageInfo pi) {
		// 1. 건너뛸 페이지 수
		int offset = (pi.getCurrentPage()-1)*pi.getBoardLimit();
		
		// 2. Rowbounds
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());	
		return (ArrayList)sqlSession.selectList("shoppingMapper.getAdminOrderList", null, rowBounds);
	}
	
	// 주문 관리 - 주문 상태 변경
	public int changeOrderStatus(SqlSessionTemplate sqlSession, OrderStatus os) {
		return sqlSession.update("shoppingMapper.changeOrderStatus", os);
	}
	
	// 주문 관리 - 주문 목록
	public int getOrderedListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("shoppingMapper.getOrderedListCount");
	}

	public ArrayList<OrderDetail> getOrderedList(SqlSessionTemplate sqlSession, PageInfo pi) {
		// 1. 건너뛸 페이지 수
		int offset = (pi.getCurrentPage()-1)*pi.getBoardLimit();
		
		// 2. Rowbounds
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());	
		return (ArrayList)sqlSession.selectList("shoppingMapper.getOrderedList", null, rowBounds);
	}


	



















}
