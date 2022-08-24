 package com.rm_reserve.model;

import java.io.Serializable;
import java.sql.Date;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;

public class RmReserveVO implements Serializable{
	private Integer serial_no;
	private Integer rm_type_no;
	private Integer rm_type_amount;
	private Date rm_schedule_date;
	private Integer reservation_amount;
	public Integer getSerial_no() {
		return serial_no;
	}
	public void setSerial_no(Integer serial_no) {
		this.serial_no = serial_no;
	}
	public Integer getRm_type_no() {
		return rm_type_no;
	}
	public void setRm_type_no(Integer rm_type_no) {
		this.rm_type_no = rm_type_no;
	}
	public Integer getRm_type_amount() {
		return rm_type_amount;
	}
	public void setRm_type_amount(Integer rm_type_amount) {
		this.rm_type_amount = rm_type_amount;
	}
	public Date getRm_schedule_date() {
		return rm_schedule_date;
	}
	public void setRm_schedule_date(Date rm_schedule_date) {
		this.rm_schedule_date = rm_schedule_date;
	}
	public Integer getReservation_amount() {
		return reservation_amount;
	}
	public void setReservation_amount(Integer reservation_amount) {
		this.reservation_amount = reservation_amount;
	}
	
	
	
	
}
