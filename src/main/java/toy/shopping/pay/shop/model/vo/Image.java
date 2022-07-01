package toy.shopping.pay.shop.model.vo;

import java.sql.Date;

public class Image {
	private int imageNo;
	private String changeName;
	private String originName;
	private String filePath;
	private Date uploadDate;
	private int fileLevel;  // 0: 썸네일 , 1: 영양사진
	private int productNo;
	private String imageStatus;
	
	@Override
	public String toString() {
		return "Image [imageNo=" + imageNo + ", changeName=" + changeName + ", originName=" + originName + ", filePath="
				+ filePath + ", uploadDate=" + uploadDate + ", fileLevel=" + fileLevel + ", productNo=" + productNo
				+ ", imageStatus=" + imageStatus + "]";
	}

	public int getImageNo() {
		return imageNo;
	}

	public void setImageNo(int imageNo) {
		this.imageNo = imageNo;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getImageStatus() {
		return imageStatus;
	}

	public void setImageStatus(String imageStatus) {
		this.imageStatus = imageStatus;
	}

	public Image(int imageNo, String changeName, String originName, String filePath, Date uploadDate, int fileLevel,
			int productNo, String imageStatus) {
		super();
		this.imageNo = imageNo;
		this.changeName = changeName;
		this.originName = originName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.fileLevel = fileLevel;
		this.productNo = productNo;
		this.imageStatus = imageStatus;
	}

	public Image() {}


}
