package toy.shopping.pay.shop.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import toy.shopping.pay.shop.model.service.ShopService;
import toy.shopping.pay.shop.model.vo.Cart;
import toy.shopping.pay.shop.model.vo.Image;
import toy.shopping.pay.shop.model.vo.PageInfo;
import toy.shopping.pay.shop.model.vo.Pagination;
import toy.shopping.pay.shop.model.vo.Product;
import toy.shopping.pay.shop.model.vo.exception.BoardException;

@Controller
public class ShopController {
	@Autowired 
	ShopService spService;
	
	// 상품 게시판 이동
	@RequestMapping("productList.sp")
	public ModelAndView productList(@RequestParam(value="page", required=false) Integer page, ModelAndView mv) {
		// 페이징
		// 1. listCount(총 게시물 수 가져오기)
		int listCount = spService.getPdtListCount();
		
		// 2. currentPage(이동하려는 대상 페이지) 가져오기
		int currentPage=1;
		if(page!= null) {
			currentPage = page;
		}
		
		// 3. 페이징 계산
		PageInfo pi = new Pagination().getPageInfo(currentPage, listCount);
		
		// 정보 가져오기
		// 4. 게시물 리스트 가져오기
		ArrayList<Product> list = spService.selectPdtList(pi);
		System.out.println("list : " + list);
		
		
		// 5. 썸네일 리스트 가져오기
		ArrayList<Image> thmbList = spService.selectThmbList();
		System.out.println("thmbList : " + thmbList);
				
		// 6. 이동하기
		if(list != null) {
			mv.addObject("list", list);
			mv.addObject("thmbList", thmbList);
			mv.addObject("pi", pi);
			mv.setViewName("productList");
		} else {
			throw new BoardException("게시판 조회에 실패했습니다.");
		}
		
		return mv;
	}
	
	// 상품 등록 페이지
	@RequestMapping("writeProductForm.sp")
	public String writeProductForm() {
		return "productWriteForm";
	}
	
	// 상품 등록
	@RequestMapping("insertProduct.sp")
	public String insertProduct(@ModelAttribute Product pdt,
			@RequestParam(value="uploadFiles", required=false) ArrayList<MultipartFile> uploadFiles,
			HttpServletRequest request) {
	
		// 사진 파일 저장
		// 1. 저장 이미지 파일 담을 arraylist와 객체 생성
		ArrayList<Image> imageList = new ArrayList<Image>();
		Image img = new Image();
		
		// 2. 이미지 파일 Image 객체화 + 사진 파일 product_upload_images에 저장 후 arraylist에 담기
		for(int i = 0; i<uploadFiles.size(); i++) {
			if(uploadFiles.get(i) != null) {
				img = saveFile(uploadFiles.get(i), request);
				
				// 2.1 썸네일 여부
				if(i ==0) {
					img.setFileLevel(0);
				} else {
					img.setFileLevel(1);
				}
				
				// 2.3 저장 리스트에 넣기
				imageList.add(img);
			}
		}
		
		// 3. 글쓴이는 나중에 로그인, 회원가입 추가하면서 정리해 넣기

		
		// 4. db에 게시물 저장
		int imgResult = spService.insertProduct(pdt, imageList);
		
		// 5. 이동
		if(imgResult < imageList.size()) {
			throw new BoardException("상품 등록에 실패했습니다.");
		} else {
			return "redirect:productList.sp";			
		}
	}

	private Image saveFile(MultipartFile multipartFile, HttpServletRequest request) {
		// 1. 이미지 폴더 경로
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		String savePath = root + "\\product_upload_images";
		
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		// 2. 변경 파일 이름(changeName) 지정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
		String originalFileName= multipartFile.getOriginalFilename();
		String renamedFileName = sdf.format(new Date(System.currentTimeMillis())) + 
				originalFileName.substring(originalFileName.lastIndexOf("."));
		
		// 3. 파일 경로 설정
		String renamePath = folder + "\\" + renamedFileName;
		
		// 4. 파일 경로에 파일 저장
		try {
			multipartFile.transferTo(new File(renamePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 5. Image 객체에 저장
		Image img = new Image();
		img.setFilePath(savePath);
		img.setOriginName(originalFileName);
		img.setChangeName(renamedFileName);
		
		return img;
	}
	
	// 상품페이지에서 장바구니 상품 추가
	@RequestMapping("addCart.sp")
	public String insertShoppingCart(@ModelAttribute Cart cart) {
		
		// + 1. 이메일아이디 넣기 (나중에 없애기)
		cart.setEmailId("aaa@aaa.com");
		
		// 2. 장바구니 등록
		int result = spService.insertCart(cart);
		
		if(result>0) {
			return "redirect:cart.sp";
		} else {
			throw new BoardException("장바구니 등록에 실패했습니다.");
		}
	}
	
	// 상품페이지에서 장바구니에 상품 추가 전 장바구니에 같은 상품 존재 여부 확인
	@RequestMapping("checkCart.sp")
	public void checkCart(@RequestParam("productNo") int productNo, HttpServletResponse response) {
		System.out.println("productNo : " + productNo);
		
		Cart cart = spService.checkCart(productNo);
		
		System.out.println("cart : " + cart);
		
		boolean tf = false;
		if(cart != null) {
			tf=true;
		}
		System.out.println("tf: " + tf);
		
		try {
			new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(tf, response.getWriter());
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// 네비바에서 장바구니로 이동
	@RequestMapping("cart.sp")
	public ModelAndView cartList(ModelAndView mv) {
		// 1. 나중에 페이징 처리하기
		
		// 2. 나중에 세션에서 이메일 아이디 가져오기
		
		// 3. 장바구니 리스트 가져오기
		ArrayList<Cart> cartList = spService.selectCartList();
		
		// 4. 이미지 리스트 가져오기
		ArrayList<Image> thmbList = spService.selectThmbList();
		
		// 5. 이동		
		if(cartList!=null) {
			mv.addObject("cartList", cartList);
			mv.addObject("thmbList", thmbList);
			mv.setViewName("shoppingCart");
		} else {
			throw new BoardException("장바구니 목록 불러오기에 실패했습니다.");
		}
		
		return mv;
	}
	
	// 장바구니 : 선택 상품 삭제  => ajax로 구현하기
	@RequestMapping("deleteCart.sp")
	public String deleteCart(@RequestParam("cartNo") Integer cartNo) {
		
		int result = spService.deleteCart(cartNo);
		
		if(result>0) {
			return "redirect:cart.sp";
		} else {
			throw new BoardException("장바구니 삭제에 실패했습니다.");
		}
	}
	
	// 장바구니 선택 품목 -> 결제페이지로 이동
	@RequestMapping("pay.sp")
	public ModelAndView paymentForm(@RequestParam("cartList") int[] cartList, ModelAndView mv) {
		// 1. 이미지 리스트 받기
		
		// 2. 보내기
		mv.addObject("cartList", cartList);
		// 3. 이미지 리스트도 추가해야 함
		mv.setViewName("buyPage");
		return mv;
	}
	
	
}












































