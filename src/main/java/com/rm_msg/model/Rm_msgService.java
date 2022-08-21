package com.rm_msg.model;

import java.sql.Date;
import java.util.List;

public class Rm_msgService {
	private	I_Rm_msgDAO dao; 
	
	public Rm_msgService() {
		dao = new Rm_msgDAO();
	}
	
	public Rm_msgVO addRm_msg(
		Integer emp_no,
		Integer mem_no,
		Integer store_no,
		String rm_msg_reason,
		Date rm_msg_date,
		Integer rm_msg_status,
		Date rm_msg_donetime
			) {
		Rm_msgVO rm_msgVO = new Rm_msgVO();
		rm_msgVO.setEmp_no(emp_no);
		rm_msgVO.setMem_no(mem_no);
		rm_msgVO.setStore_no(store_no);
		rm_msgVO.setRm_msg_reason(rm_msg_reason);
		rm_msgVO.setRm_msg_date(rm_msg_date);
		rm_msgVO.setRm_msg_status(rm_msg_status);
		rm_msgVO.setRm_msg_donetime(rm_msg_donetime);
		dao.insert(rm_msgVO);
		return rm_msgVO;
	}
	
	public Rm_msgVO updateRm_msg(
			Integer rm_msg_no,
			Integer emp_no,
			Integer mem_no,
			Integer store_no,
			String rm_msg_reason,
			Date rm_msg_date,
			Integer rm_msg_status,
			Date rm_msg_donetime
			) {
		Rm_msgVO rm_msgVO = new Rm_msgVO();
		rm_msgVO.setRm_msg_no(rm_msg_no);
		rm_msgVO.setEmp_no(emp_no);
		rm_msgVO.setMem_no(mem_no);
		rm_msgVO.setStore_no(store_no);
		rm_msgVO.setRm_msg_reason(rm_msg_reason);
		rm_msgVO.setRm_msg_date(rm_msg_date);
		rm_msgVO.setRm_msg_status(rm_msg_status);
		rm_msgVO.setRm_msg_donetime(rm_msg_donetime);
		dao.insert(rm_msgVO);
		
		
		return rm_msgVO;
		
	}
	
	
	public void deleteRm_msg(Integer rm_msg_no) {
		dao.delete(rm_msg_no);
	}
	
	public Rm_msgVO getOnerm_msg(Integer rm_msg_no) {
		
		return dao.findByPrimaryKey(rm_msg_no);
	}
	
	public List<Rm_msgVO> getAll(){
		return dao.getAll();
	}
}
