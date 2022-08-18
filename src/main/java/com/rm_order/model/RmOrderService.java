package com.rm_order.model;

import java.util.List;

public class RmOrderService {
	private I_RmOrderDAO dao;


	public RmOrderService() {
		dao = new RmOrderDAO();
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
}
