package com.rm_pic.model;

import java.io.Serializable;

public class RmPicVO implements Serializable{
	
	private Integer rm_pic_no;
	private Integer rm_type_no;
	private byte[] rm_pic_img;
	
	public Integer getRm_pic_no() {
		return rm_pic_no;
	}
	public void setRm_pic_no(Integer rm_pic_no) {
		this.rm_pic_no = rm_pic_no;
	}
	public Integer getRm_type_no() {
		return rm_type_no;
	}
	public void setRm_type_no(Integer rm_type_no) {
		this.rm_type_no = rm_type_no;
	}
	public byte[] getRm_pic_img() {
		return rm_pic_img;
	}
	public void setRm_pic_img(byte[] rm_pic_img) {
		this.rm_pic_img = rm_pic_img;
	}
	

}
