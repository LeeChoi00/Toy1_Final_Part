package toy.shopping.pay.shop.model.service;

import java.util.ArrayList;

import toy.shopping.pay.shop.model.vo.Cart;
import toy.shopping.pay.shop.model.vo.Image;
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

	Cart checkCart(int productNo);
	
	// 장바구니 리스트 받아오기
//	// 방법 2
//	ArrayList<Cart> cartsForPay(ArrayList<Cart> cartNos);
	
	// 방법 3 배열 그대로 보내서 받아오기
	ArrayList<Cart> cartsForPay(int[] cartList);

	ArrayList<Image> imgForCartPay(ArrayList<Cart> carts);

	



	

	


}
