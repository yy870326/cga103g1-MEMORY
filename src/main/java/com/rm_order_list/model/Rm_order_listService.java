package com.rm_order_list.model;

import java.util.List;

public class Rm_order_listService {
	
	private I_Rm_order_listDAO dao;
	
	public Rm_order_listService() {
		dao = new Rm_order_listDAO();
	}
	
	public Rm_order_listVO addRm_order_list(Integer rm_type_no,Integer rm_order_no, Integer rm_amount,
			Integer rm_price, java.sql.Date arrival_date, java.sql.Date departure_date,String rm_check_in
			) {
		Rm_order_listVO rm_order_listVO = new Rm_order_listVO();
		rm_order_listVO.setRm_type_no(rm_type_no);
		rm_order_listVO.setRm_order_no(rm_order_no);
		rm_order_listVO.setRm_amount(rm_amount);
		rm_order_listVO.setRm_price(rm_price);
		rm_order_listVO.setArrival_date(arrival_date);
		rm_order_listVO.setDeparture_date(departure_date);
		rm_order_listVO.setRm_check_inl(rm_check_in);
		dao.insert(rm_order_listVO);
		
		return rm_order_listVO;
	}
	
	public Rm_order_listVO updateRm_order_list(Integer rm_order_list_no,Integer rm_type_no,Integer rm_order_no, Integer rm_amount,
			Integer rm_price, java.sql.Date arrival_date, java.sql.Date departure_date,String rm_check_in) {
		
		Rm_order_listVO rm_order_listVO = new Rm_order_listVO();
		
		rm_order_listVO.setRm_order_list_no(rm_order_list_no);
		rm_order_listVO.setRm_type_no(rm_type_no);
		rm_order_listVO.setRm_order_no(rm_order_no);
		rm_order_listVO.setRm_amount(rm_amount);
		rm_order_listVO.setRm_price(rm_price);
		rm_order_listVO.setArrival_date(arrival_date);
		rm_order_listVO.setDeparture_date(departure_date);
		rm_order_listVO.setRm_check_inl(rm_check_in);
		dao.update(rm_order_listVO);
		
		return rm_order_listVO;
	}
	
	public void deleteRm_order_list(Integer rm_order_list_no) {
		dao.delete(rm_order_list_no);
	}
	
	public Rm_order_listVO getOneRm_order_listVO(Integer rm_order_list_no) {
		return dao.findByPrimaryKey(rm_order_list_no);
	}
	
	public List<Rm_order_listVO> getAll(){
		return dao.getAll();
	}
}
