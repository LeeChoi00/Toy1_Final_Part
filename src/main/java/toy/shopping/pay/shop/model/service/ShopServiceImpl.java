package toy.shopping.pay.shop.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import toy.shopping.pay.shop.model.dao.ShopDAO;
import toy.shopping.pay.shop.model.vo.Cart;
import toy.shopping.pay.shop.model.vo.Image;
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
	public Cart checkCart(int productNo) {
		return spDAO.checkCart(sqlSession, productNo);
	}



}
