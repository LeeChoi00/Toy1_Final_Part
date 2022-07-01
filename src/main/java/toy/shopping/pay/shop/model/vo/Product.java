package toy.shopping.pay.shop.model.vo;

import java.sql.Date;

public class Product {
	private int productNo;
	private String productName;
	private int productPrice;
	private int productStock;
	private String productOneline;
	private int productType;
	
	private Date productUpdateDate;
	private String productStatus;
	private int productPurchases;
	
	public Product() {}

	public Product(int productNo, String productName, int productPrice, int productStock, String productOneline,
			int productType, Date productUpdateDate, String productStatus, int productPurchases) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productStock = productStock;
		this.productOneline = productOneline;
		this.productType = productType;
		this.productUpdateDate = productUpdateDate;
		this.productStatus = productStatus;
		this.productPurchases = productPurchases;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public String getProductOneline() {
		return productOneline;
	}

	public void setProductOneline(String productOneline) {
		this.productOneline = productOneline;
	}

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

	public Date getProductUpdateDate() {
		return productUpdateDate;
	}

	public void setProductUpdateDate(Date productUpdateDate) {
		this.productUpdateDate = productUpdateDate;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public int getProductPurchases() {
		return productPurchases;
	}

	public void setProductPurchases(int productPurchases) {
		this.productPurchases = productPurchases;
	}

	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productStock=" + productStock + ", productOneline=" + productOneline + ", productType="
				+ productType + ", productUpdateDate=" + productUpdateDate + ", productStatus=" + productStatus
				+ ", productPurchases=" + productPurchases + "]";
	}


}
