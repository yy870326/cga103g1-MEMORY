package com.cart.model;

public class CartVO {
	private Integer tkt_no;
	private Integer mem_no;
	
	public CartVO() {}

	public CartVO(Integer tkt_no, Integer mem_no) {
		super();
		this.tkt_no = tkt_no;
		this.mem_no = mem_no;
	}

	public Integer getTkt_no() {
		return tkt_no;
	}

	public void setTkt_no(Integer tkt_no) {
		this.tkt_no = tkt_no;
	}

	public Integer getMem_no() {
		return mem_no;
	}

	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}

	@Override
	public String toString() {
		return "CartVO [tkt_no=" + tkt_no + ", mem_no=" + mem_no + "]";
	}
	
	
	
}
