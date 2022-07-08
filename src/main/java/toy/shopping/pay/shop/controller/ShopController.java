package toy.shopping.pay.shop.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import toy.shopping.pay.member.model.vo.Admin;
import toy.shopping.pay.member.model.vo.Member;
import toy.shopping.pay.shop.model.service.ShopService;
import toy.shopping.pay.shop.model.vo.Cart;
import toy.shopping.pay.shop.model.vo.Image;
import toy.shopping.pay.shop.model.vo.Order;
import toy.shopping.pay.shop.model.vo.OrderDetail;
import toy.shopping.pay.shop.model.vo.OrderStatus;
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
		
		// 한 페이지에 들어갈 게시물 수
		int boardLimit = 12;
		
		// 3. 페이징 계산
		PageInfo pi = new Pagination().getPageInfo(currentPage, listCount, boardLimit);
		
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
	public void checkCart(@RequestParam("productNo") int productNo, HttpServletResponse response, HttpSession session) {
//		System.out.println("productNo : " + productNo);
		
		// 1. session에서 loginUser의 emailId 받아오기
		String emailId = ((Member)session.getAttribute("loginUser")).getEmailId();
		
		// 2. cart 객체에 emailId와 productNo 넣어주기
		Cart crt = new Cart();
		crt.setEmailId(emailId);
		crt.setProductNo(productNo);
		
		Cart cart = spService.checkCart(crt);
		
		System.out.println("cart : " + cart);
		
		
		// 중복 품목 존재 여부
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
	public ModelAndView paymentForm(@RequestParam("cartList") int[] cartList, 
			@RequestParam("cartAmountArr") int[] cartAmountArr, ModelAndView mv) {
		/* 방법 1
		 * // 1. 품목의 cart 정보 받아오기 ArrayList<Cart> crtList = spService.selectCartList();
		 * 
		 * // 2. cartList의 장바구니 번호에 해당하는 객체만 리스트에 담기 ArrayList<Cart> carts = new
		 * ArrayList<Cart>(); for(int i = 0; i<crtList.size();i++) { for(int j=0;
		 * j<cartList.length;j++) { if(crtList.get(i).getCartNo() == cartList[i]) {
		 * carts.add(crtList.get(i)); } } }
		 */
		
//		// 방법 2
//		// 2. 장바구니 정보 받아와서 완성하기
//		// 2.1 선택 품목의 cartNo을 cart객체에 담아 리스트에 넣기 => 복잡함
//		ArrayList<Cart> cartNos = new ArrayList<Cart>();
//		for(int i = 0; i<cartList.length;i++) {
//			Cart cart = new Cart();
//			cart.setCartNo(cartList[i]);
//			cart.setProductAmount(cartAmountArr[i]);
//			cartNos.add(cart);
//		}
//
//		// 2.2 db에서 선택 장바구니 정보 받아오기
//		ArrayList<Cart> carts = spService.cartsForPay(cartNos);
		
//		// 2.3 장바구니 변경 수량 넣기
//		for(int i = 0; i<carts.size();i++) {
//				for(int j = 0; j<cartNos.size(); j++) {
//					if(carts.get(i).getCartNo() == cartNos.get(j).getCartNo()) {
//						carts.get(i).setProductAmount(cartNos.get(i).getProductAmount());	
//					}
//				}
//		}
		
		// 방법 3 배열 그대로 보내서 장바구니 정보 받아오기
		// 2. 장바구니 정보 받아와서 완성하기
		// 2.1 db에서 cartNo에 따른 cart 객체 리스트 받아오기
		ArrayList<Cart> carts = spService.cartsForPay(cartList);
		
//		System.out.println("carts.size : " + carts.size());
//		System.out.println("cartAmountArr : " + cartAmountArr);
		
		// 2.2 장바구니 db 수량 변경하기
		for(int i = 0; i<carts.size();i++) {
			carts.get(i).setProductAmount(cartAmountArr[i]);
		}
		int ctAmountUpdate = spService.ctAmountUpdate(carts);
		
		if(ctAmountUpdate < carts.size()) {
			throw new BoardException("장바구니 수량 변경에 실패했습니다.");
		} else {
			// 3. 이미지 리스트 받기
			ArrayList<Image> images = spService.imgForCartPay(carts);
			
			// 4. 보내기
			mv.addObject("carts", carts);
			mv.addObject("images", images);
			mv.setViewName("buyPage");
			return mv;
		}
	}
	
	// 주문 내역 저장
	@RequestMapping("insertOrder.sp")
	public String insertOrder(@RequestParam("cartNos") int[] cartNos, 
			@ModelAttribute Order order, @RequestParam("post") String post,
			@RequestParam("adr1") String adr1, @RequestParam("adr2") String adr2,
			HttpSession session) {
		// carts -> 각 결제 품목 정보
		// order -> totalPrice, orderRequest, emailId, recipientName, recipientAdr, recipientPhone;
		
		// 1. Cart(결제 장바구니 품목) 가져오기
		ArrayList<Cart> carts = spService.paidCartList(cartNos);
		
		// 2. Order 정보 
		// 2.1 세션에서 emailId 가져오기 + Order 객체에 넣기
		Member loginUser = (Member)session.getAttribute("loginUser");
		String emailId = loginUser.getEmailId();

		
		// 2.2 post + adr1 + adr2 => recipeintAdr 만들기
		String recipientAdr = post+", " + adr1 + " " + adr2;
		order.setRecipientAdr(recipientAdr);
		
		// 2.3 order의 recipientName, recipientAdr, recipientPhone 중 하나 이상 비면 
			// 세션의 loginUser 정보로 채우기
		String a = "  ";
		System.out.println("a : " + a.isEmpty());
		if(post.isEmpty()) {
			order.setRecipientAdr(loginUser.getAddress());
		} else {
			order.setRecipientAdr(recipientAdr);
		}
		
		System.out.println("carts : " + carts);
		System.out.println("order : " + order);
		
		// 3. OrderDetail(개별 품목 정보)
		// 3.1 carts, Order에서 정보 빼내 OrderDetail 객체에 개별 품목 넣어주고 리스트에 추가하기
		ArrayList<OrderDetail> odList = new ArrayList<OrderDetail>();
		for(int i = 0; i<carts.size();i++) {
			OrderDetail od = new OrderDetail();
			od.setTotalPrice(order.getTotalPrice());
			od.setOrderRequest(order.getOrderRequest());
			od.setEmailId(emailId);
			od.setRecipientName(order.getRecipientName());
			od.setRecipientAdr(order.getRecipientAdr());
			od.setRecipientPhone(order.getRecipientPhone());
			od.setOrderAmount(carts.get(i).getProductAmount());
			od.setProductNo(carts.get(i).getProductNo());
			
			odList.add(od);
		}
		
		System.out.println("odList : " + odList);
		
		// 4. DB 저장 및 이동
		// 4.1 OrderDetail 리스트 보내기
		int result = spService.insertOrder(odList);
		System.out.println("result : " + result);
		
		// 4.2 이동
		if (result < odList.size()+2) {
			throw new BoardException("주문 정보 저장에 실패했습니다.");			
		} else {
			int dltCarts = 0;
			for(int i =0; i<carts.size();i++) {
				int deleteCart = spService.deleteCart(carts.get(i).getCartNo());
				dltCarts +=deleteCart;
			}
			if(dltCarts < carts.size()) {
				throw new BoardException("주문 장바구니 삭제에 실패했습니다.");
			} else {
				return "redirect:orderList.sp";
			}		
		}
	}
	
	// 주문 내역으로 이동하기
	@RequestMapping("orderList.sp")
	public ModelAndView orderList(@RequestParam(value="page", required=false) Integer page,
			HttpSession session, ModelAndView mv) {	
		// 1. 주문자 이메일 가져오기
		String emailId = ((Member)session.getAttribute("loginUser")).getEmailId();

		// 2. 페이징	
		// 2.1 주문 정보 리스트 숫자 구하기
		int listCount = spService.getMyOrderListCount(emailId);
		
		// 2.2 현재 페이지 구하기
		int currentPage=1;
		if(page != null) {
			currentPage = page;
		}
		
		// 2.3 한 페이지에 들어갈 게시물 수
		int boardLimit = 10;
		
		// 2.4 페이징 계산		
		PageInfo pi = new Pagination().getPageInfo(currentPage, listCount, boardLimit);
		
		// 3. 특정 페이지의 주문 정보 가져오기 
		// 3.1 Order에서 주문자의 주문 정보가져오기
		ArrayList<OrderStatus> orderList = spService.getMyOrderList(emailId, pi);
		

		// 4. mv에 담아 이동
		if(orderList.size()>0) {
			mv.addObject("orderList", orderList);
			mv.setViewName("orderList");
		} else {
			throw new BoardException("주문 내역 조회에 실패했습니다.");
		}
		return mv;
	}
	
	// 주문 내역 - 상세보기
	@RequestMapping("orderDetail.sp")
	public ModelAndView orderDetail(@RequestParam("orderNo") int orderNo, ModelAndView mv) {
		// 1. 주문 상세 정보 가져오기
		ArrayList<OrderDetail> orderDetailList = spService.getMyOrderDetailList(orderNo);
		
		// 2. 주문 상세 이미지 가져오기
		ArrayList<Image> orderImgList = spService.getMyOrderImgList(orderDetailList);
		
		// 3. 이동
		if(orderDetailList !=null && orderImgList != null) {
			mv.addObject("orderDetailList", orderDetailList);
			mv.addObject("orderImgList", orderImgList);	
			mv.setViewName("orderDetail");
		} else {
			throw new BoardException("주문 상세보기에 실패했습니다.");
		}
		return mv;
	}
	
	// 주문 관리 페이지
	@RequestMapping("orderAdminList.sp")
	public ModelAndView orderAdminList(@RequestParam(value="page", required=false) Integer page, 
			 HttpSession session, ModelAndView mv) {
//		// 1. 관리자 여부 확인 -> 나중에 TP로 완성하기
//		Admin admin = (Admin)session.getAttribute("admin");
//		if(admin != null) {
//		
//		} else {
//			throw new BoardException()
//		}
		
		// 2. 페이징	
		// 2.1 주문 정보 리스트 숫자 구하기
		int listCount = spService.getOrderListCount();
		
		// 2.2 현재 페이지 구하기
		int currentPage=1;
		if(page != null) {
			currentPage = page;
		}
		
		// 2.3 한 페이지에 들어갈 게시물 수
		int boardLimit = 10;
		
		// 2.4 페이징 계산		
		PageInfo pi = new Pagination().getPageInfo(currentPage, listCount, boardLimit);
		
		// 3. 특정 페이지의 주문 정보 가져오기 
		// 3.1 Order에서 주문자의 주문 정보가져오기
		ArrayList<OrderStatus> orderAdminList = spService.getAdminOrderList(pi);
		
		// 4. mv에 담아 이동
		if(orderAdminList.size()>0) {
			mv.addObject("orderAdminList", orderAdminList);
			mv.setViewName("orderAdminList");
		} else {
			throw new BoardException("주문 관리 조회에 실패했습니다.");
		}
		return mv;		
	}
	
	// 주문 관리 - 주문 상태 변경
	@RequestMapping("changeOrderStatus.sp")
	public void changeOrderStatus(@ModelAttribute OrderStatus os, HttpServletResponse response) {
		int result = spService.changeOrderStatus(os);
		
		
		if(result<1) {
			throw new BoardException("주문 상태 변경에 실패했습니다.");
		}
		
		try {
			new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(result, response.getWriter());
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 주문 관리 - 주문 목록
	@RequestMapping("orderedList.sp")
	public ModelAndView getOrderedList(@RequestParam(value="page", required=false) Integer page, 
			 HttpSession session, ModelAndView mv) {
//		// 1. 관리자 여부 확인 -> 나중에 TP로 완성하기
		
		// 2. 페이징	
		// 2.1 주문 정보 리스트 숫자 구하기
		int listCount = spService.getOrderedListCount();
		
		// 2.2 현재 페이지 구하기
		int currentPage=1;
		if(page != null) {
			currentPage = page;
		}
		
		// 2.3 한 페이지에 들어갈 게시물 수
		int boardLimit = 10;
		
		// 2.4 페이징 계산		
		PageInfo pi = new Pagination().getPageInfo(currentPage, listCount, boardLimit);		
		
		// 3. 주문(ordered) 목록 가져오기
		ArrayList<OrderDetail> orderedList = spService.getOrderedList(pi);
		System.out.println("ordered : " + orderedList);
		
		// 4. 이동
		
		mv.addObject("orderedList", orderedList);
		mv.addObject("pi", pi);
		mv.setViewName("orderedList");

		return mv;
	}
	
	
	
	
	
	
}












































