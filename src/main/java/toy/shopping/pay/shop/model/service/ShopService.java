package toy.shopping.pay.shop.model.service;

import java.util.ArrayList;

import toy.shopping.pay.shop.model.vo.Image;
import toy.shopping.pay.shop.model.vo.PageInfo;
import toy.shopping.pay.shop.model.vo.Product;

public interface ShopService {

	int getListCount();

	ArrayList<Product> selectPdtList(PageInfo pi);

	ArrayList<Image> selectThmbList();

	int insertProduct(Product pdt, ArrayList<Image> imageList);


}
