package com.rm_order.model;

import java.util.List;

public class RmOrderService {
	private I_RmOrderDAO dao;

	public RmOrderService() {
		dao = new RmOrderDAO();
	}
	
	public RmOrderVO insert(Integer mem_no, Integer store_no, Integer rm_order_status, Integer rm_charge, Integer rm_review) {
		RmOrderVO rm = new RmOrderVO();
		rm.setMem_no(mem_no);
		rm.setStore_no(store_no);
		rm.setRm_order_status(rm_order_status);
		rm.setRm_charge(rm_charge);
		rm.setRm_review(rm_review);
		return dao.insert(rm);
	}
	
	public List<RmOrderVO> getAllRmOrder() {
		return dao.getAll();
	}
	
	public List<RmOrderVO> getAllStatus(Integer rm_order_status) {
		return dao.getAllStatus(rm_order_status);
	}
	
	public RmOrderVO getOne(Integer rm_order_no) {
		return dao.getOne(rm_order_no);
	}



	public void update(RmOrderVO rmOrderVO) {
		
		
	}

	
	public void updateStatus(RmOrderVO rmOrderVO) {
		
		
	}
}
