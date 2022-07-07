package toy.shopping.pay.shop.model.vo;

public class OrderStatus {
	private int orderStatusNo;
	private String orderStatusName;
	private int orderNo;
	
	public OrderStatus() {}

	public OrderStatus(int orderStatusNo, String orderStatusName, int orderNo) {
		super();
		this.orderStatusNo = orderStatusNo;
		this.orderStatusName = orderStatusName;
		this.orderNo = orderNo;
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

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "OrderStatus [orderStatusNo=" + orderStatusNo + ", orderStatusName=" + orderStatusName + ", orderNo="
				+ orderNo + "]";
	}

}
