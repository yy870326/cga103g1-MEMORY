package com.mem_coup.model;

import java.util.List;

import com.mem.model.MemService;
import com.mem.model.MemVO;

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
	
	public MemVO sendCoupToMem(Integer mem_no, Integer coup_no, Integer coup_state) {
		
		MemService memSrv = new MemService();
		// 透過會員帳號找出會員
//		MemVO memVO = memSrv.getByMemAcc(mem_acc); // getByMemAcc
		
		// 透過會員編號找出會員
		MemVO memVO = memSrv.getOneMem(mem_no);
		
		// 新增優惠券給會員
		MemCoupVO memCoupVO = new MemCoupVO();
		memCoupVO.setMem_no(mem_no);
		memCoupVO.setCoup_no(coup_no);
		memCoupVO.setCoup_state(coup_state);
		dao.insert(memCoupVO);
		
		return memVO;
	}
	
}