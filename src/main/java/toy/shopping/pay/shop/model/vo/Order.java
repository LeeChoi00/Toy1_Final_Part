package toy.shopping.pay.shop.model.vo;

import java.sql.Date;

// 주문 건 당 정보
public class Order {
	private int orderNo;
	private Date orderDate;
	private int totalPrice;
	private String orderRequest;
	private String emailId;
	private String orderStatusName;
	private String recipientName;
	private String recipientAdr;
	private String recipientPhone;
	
	public Order() {}

	public Order(int orderNo, Date orderDate, int totalPrice, String orderRequest, String emailId,
			String orderStatusName, String recipientName, String recipientAdr, String recipientPhone) {
		super();
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.orderRequest = orderRequest;
		this.emailId = emailId;
		this.orderStatusName = orderStatusName;
		this.recipientName = recipientName;
		this.recipientAdr = recipientAdr;
		this.recipientPhone = recipientPhone;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderRequest() {
		return orderRequest;
	}

	public void setOrderRequest(String orderRequest) {
		this.orderRequest = orderRequest;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientAdr() {
		return recipientAdr;
	}

	public void setRecipientAdr(String recipientAdr) {
		this.recipientAdr = recipientAdr;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", orderDate=" + orderDate + ", totalPrice=" + totalPrice
				+ ", orderRequest=" + orderRequest + ", emailId=" + emailId + ", orderStatusName=" + orderStatusName
				+ ", recipientName=" + recipientName + ", recipientAdr=" + recipientAdr + ", recipientPhone="
				+ recipientPhone + "]";
	}
}
