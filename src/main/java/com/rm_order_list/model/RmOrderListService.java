package com.rm_order_list.model;

import java.util.List;

public class RmOrderListService {
	
	private I_RmOrderListDAO dao;
	
	public RmOrderListService() {
		dao = new RmOrderListDAO();
	}
	
	public RmOrderListVO addRm_order_list(Integer rm_type_no,Integer rm_order_no, Integer rm_amount,
			Integer rm_price, java.sql.Date arrival_date, java.sql.Date departure_date,String rm_check_in
			) {
		RmOrderListVO rmOrderListVO = new RmOrderListVO();
		rmOrderListVO.setRm_type_no(rm_type_no);
		rmOrderListVO.setRm_order_no(rm_order_no);
		rmOrderListVO.setRm_amount(rm_amount);
		rmOrderListVO.setRm_price(rm_price);
		rmOrderListVO.setArrival_date(arrival_date);
		rmOrderListVO.setDeparture_date(departure_date);
		rmOrderListVO.setRm_check_in(rm_check_in);
		dao.insert(rmOrderListVO);
		
		return rmOrderListVO;
	}
	
	public RmOrderListVO updateRm_order_list(Integer rm_order_list_no,Integer rm_type_no,Integer rm_order_no, Integer rm_amount,
			Integer rm_price, java.sql.Date arrival_date, java.sql.Date departure_date,String rm_check_in) {
		
		RmOrderListVO rmOrderListVO = new RmOrderListVO();
		
		rmOrderListVO.setRm_order_list_no(rm_order_list_no);
		rmOrderListVO.setRm_type_no(rm_type_no);
		rmOrderListVO.setRm_order_no(rm_order_no);
		rmOrderListVO.setRm_amount(rm_amount);
		rmOrderListVO.setRm_price(rm_price);
		rmOrderListVO.setArrival_date(arrival_date);
		rmOrderListVO.setDeparture_date(departure_date);
		rmOrderListVO.setRm_check_in(rm_check_in);
		dao.update(rmOrderListVO);
		
		return rmOrderListVO;
	}
	
	public void deleteRm_order_list(Integer rm_order_list_no) {
		dao.delete(rm_order_list_no);
	}
	
	public RmOrderListVO getOneRmOrderList(Integer rm_order_list_no) {
		return dao.findByPrimaryKey(rm_order_list_no);
	}
	
	public List<RmOrderListVO> getAllByRmOrderNo(Integer rm_order_no) {
		return dao.getAllByRmOrderNo(rm_order_no);
	}
	
	public List<RmOrderListVO> getAll() {
		return dao.getAll();
	}
}
