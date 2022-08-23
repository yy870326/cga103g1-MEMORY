package com.rm_type.model;

import java.util.List;

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
			, Integer rm_people, Integer rm_price, Integer rm_area, String rm_intro, Boolean rm_update, Integer rm_type_no) {
		RmTypeVO rm = new RmTypeVO();

		rm.setRm_name(rm_name);
		rm.setRm_total(rm_total);
		rm.setRm_people(rm_people);
		rm.setRm_price(rm_price);
		rm.setRm_area(rm_area);
		rm.setRm_intro(rm_intro);
		rm.setRm_update(rm_update);
		rm.setRm_type_no(rm_type_no);
		
		return dao.update(rm);
	}

	
	// 房型上下架
	public void changeState(Integer rm_type_no, Boolean rm_update) {
		
		dao.changeState(rm_type_no, rm_update);
		
	}

	public List<RmTypeVO> getAllRm() {
		
		return dao.getAll();
	}
	
	// 所有已上架房型
	public List<RmTypeVO> getAllRmRsv() {
		
		return dao.getAllRsv();
	}
}
