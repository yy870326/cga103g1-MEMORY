package com.rm_type.model;

import java.sql.Date;
import java.util.List;

import com.emp.model.EmpVO;


public class RmTypeService {
	private I_RmTypeDAO dao;

	public RmTypeService() {
		dao = new RmTypeDAO();
	}
	public RmTypeVO getOneRm(Integer rm_type_no) {
		return dao.getOne(rm_type_no);
	}

	public RmTypeVO insertRm(Integer store_no, String rm_name, Integer rm_total
			, Integer rm_people, Integer rm_price, Integer rm_area, String rm_intro) {
	
		RmTypeVO rm = new RmTypeVO();

		rm.setStore_no(store_no);
		rm.setRm_name(rm_name);
		rm.setRm_total(rm_total);
		rm.setRm_people(rm_people);
		rm.setRm_price(rm_price);
		rm.setRm_area(rm_area);
		rm.setRm_intro(rm_intro);
	
		
		return dao.insert(rm);
	}

	public RmTypeVO updateRm(String rm_name, Integer rm_total
			, Integer rm_people, Integer rm_price, Integer rm_area, String rm_intro, Integer rm_update, Integer rm_type_no) {
		RmTypeVO rm = new RmTypeVO();

		rm.setRm_name(rm_name);
		rm.setRm_total(rm_total);
		rm.setRm_people(rm_people);
		rm.setRm_price(rm_price);
		rm.setRm_area(rm_area);
		rm.setRm_intro(rm_intro);
		rm.setRm_eval_sum(rm_type_no);
		rm.setRm_update(rm_update);
		rm.setRm_type_no(rm_type_no);
		
		return dao.update(rm);
	}

	
	// 房型上下架
	public RmTypeVO changeState(Integer rm_update, Integer rm_type_no) {
		RmTypeVO rmTypeVO = new RmTypeVO();
		rmTypeVO.setRm_update(rm_update);
		rmTypeVO.setRm_type_no(rm_type_no);
		
		dao.changeState(rm_type_no, rm_update);
		return rmTypeVO;
		
	}

	public List<RmTypeVO> getAllRm() {
		
		return dao.getAll();
	}
	public List<RmTypeVO> getAllRoomFront() {
		return dao.getAllFront();
	}
	
	// 所有已上架房型
	public List<RmTypeVO> getAllRmRsv() {
		
		return dao.getAllRsv();
	}
	
	public List<RmTypeVO> getEnoughType(Date start_date, Date end_date, Integer qty, Integer guest) {
		return dao.getEnoughType(start_date, end_date, qty, guest);
	}
	public List<RmTypeVO> getNotEnoughType(Date start_date, Date end_date, Integer qty, Integer guest) {
		return dao.getNotEnoughType(start_date, end_date, qty, guest);
	}
	public RmTypeVO getOneRoomType(Integer rm_type_no) {
		return dao.getOne(rm_type_no);
	}
	public List<RmTypeVO> paymentCheck(Date start_date, Date end_date, Integer roomtotal, Integer rm_type_no) {
		return dao.paymentCheck(start_date, end_date, roomtotal, rm_type_no);
}
}