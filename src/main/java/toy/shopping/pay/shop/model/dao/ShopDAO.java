package toy.shopping.pay.shop.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import toy.shopping.pay.shop.model.vo.Cart;
import toy.shopping.pay.shop.model.vo.Image;
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

	public Cart checkCart(SqlSessionTemplate sqlSession, int productNo) {
		return sqlSession.selectOne("shoppingMapper.checkCart", productNo);
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
	
	public ArrayList<Image> imgForCartPay(SqlSessionTemplate sqlSession, ArrayList<Cart> carts) {
		ArrayList<Image> images = new ArrayList<Image>();
		for(int i = 0; i<carts.size();i++) {
			Image img = sqlSession.selectOne("shoppingMapper.imgForCartPay", carts.get(i));
			images.add(img);
		}
		
		return images;
	}









}
