package com.tkt_img.model;

public class TktImgVO implements java.io.Serializable{
	private Integer tktImgNO;
	private Integer tktNO;
	private byte[] tktimg;
	
	public TktImgVO() {
		super();
	}

	public TktImgVO(Integer tktImgNO, Integer tktNO, byte[] tktImg) {
		super();
		this.tktImgNO = tktImgNO;
		this.tktNO = tktNO;
		this.tktimg = tktImg;
	}

	public Integer gettktImgNO() {
		return tktImgNO;
	}

	public void settktImgNO(Integer tktImgNO) {
		this.tktImgNO = tktImgNO;
	}

	public Integer gettktNO() {
		return tktNO;
	}

	public void settktNO(Integer tktNO) {
		this.tktNO = tktNO;
	}

	public byte[] gettktimg() {
		return tktimg;
	}

	public void settktimg(byte[] tktImg) {
		this.tktimg = tktImg;
	}
	
	
}
