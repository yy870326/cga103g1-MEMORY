package com.auth.model;

import java.io.Serializable;

public class AuthVO implements Serializable{
	
	private Integer fun_no;
	private Integer emp_no;
	
	public Integer getFun_no() {
		return fun_no;
	}
	public void setFun_no(Integer fun_no) {
		this.fun_no = fun_no;
	}
	public Integer getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}
	
	
}
