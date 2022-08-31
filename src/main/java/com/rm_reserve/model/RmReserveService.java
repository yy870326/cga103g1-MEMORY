package com.rm_reserve.model;

import java.sql.Date;
import java.util.List;

public class RmReserveService {

	private I_RmReserveDAO dao;
	
	public RmReserveService() {
		dao = new RmReserveDAO();
	}
	
	public void addRmRsv() {
		dao.insert();
	}
	
	public void reserveRmRsv(Integer qty, Integer rm_type_no, Date start_date, Date end_date) {
		dao.reserve(qty, rm_type_no, start_date, end_date);
	}
	
	public void cancelRmRsv(Integer qty, Integer rm_type_no, Date start_date, Date end_date) {
		dao.reserve(qty, rm_type_no, start_date, end_date);
	}
	
	public void deleteRmRsv() {
		dao.delete();
	}
	
	
	public RmReserveVO getOneRmReserve(Integer serial_no) {
		return dao.findByPrimaryKey(serial_no);
}
	public List<RmReserveVO> getAll() {
		return dao.getAll();
}
}
