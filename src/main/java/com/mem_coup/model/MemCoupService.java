package com.mem_coup.model;

import java.util.List;

public class MemCoupService {
	
	private I_MemCoupDAO dao;
	
	public MemCoupService() {
		dao = new MemCoupDAO();
	}
	
	public MemCoupVO getOne(Integer mem_coup_no, Integer mem_no, Integer coup_no, Integer tkt_order_no, Integer coup_state) {
		return dao.findByPrimaryKey(mem_coup_no);
	}
	
	public List<MemCoupVO> getAll() {
		return dao.getAll();
	}
	
}