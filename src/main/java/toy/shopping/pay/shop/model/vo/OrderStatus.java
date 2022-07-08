package toy.shopping.pay.shop.model.vo;

import java.sql.Date;

public class OrderStatus extends Order{
	private int orderStatusNo;
	private String orderStatusName;

	
	public OrderStatus() {}


	public OrderStatus(int orderNo, Date orderDate, int totalPrice, String orderRequest, String emailId,
			String orderStatusName, String recipientName, String recipientAdr, String recipientPhone, int orderStatusNo,
			String orderStatusName2) {
		super(orderNo, orderDate, totalPrice, orderRequest, emailId, orderStatusName, recipientName, recipientAdr,
				recipientPhone);
		this.orderStatusNo = orderStatusNo;
		orderStatusName = orderStatusName2;
	}


	public int getOrderStatusNo() {
		return orderStatusNo;
	}


	public void setOrderStatusNo(int orderStatusNo) {
		this.orderStatusNo = orderStatusNo;
	}


	public String getOrderStatusName() {
		return orderStatusName;
	}


	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}


	@Override
	public String toString() {
		return "OrderStatus [orderStatusNo=" + orderStatusNo + ", orderStatusName=" + orderStatusName
				+ ", getOrderNo()=" + getOrderNo() + ", getOrderDate()=" + getOrderDate() + ", getTotalPrice()="
				+ getTotalPrice() + ", getOrderRequest()=" + getOrderRequest() + ", getEmailId()=" + getEmailId()
				+ ", getRecipientName()=" + getRecipientName() + ", getRecipientAdr()=" + getRecipientAdr()
				+ ", getRecipientPhone()=" + getRecipientPhone() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
