package com.coup.model;

import java.sql.Date;
import java.util.List;

public class CoupService {
	
	private I_CoupDAO dao;

	public CoupService() {
		dao = new CoupDAO();
	}

	public CoupVO addCoup(String coup_name, String introduce, Integer discount, Date startdate, Date enddate,
			Integer status) {

		CoupVO coupVO = new CoupVO();

		coupVO.setCoup_name(coup_name);
		coupVO.setIntroduce(introduce);
		coupVO.setDiscount(discount);
		coupVO.setStartdate(startdate);
		coupVO.setEnddate(enddate);
		coupVO.setStatus(status);

		dao.insert(coupVO);

		return coupVO;
	}

	public CoupVO updateCoup(Integer coup_no, String coup_name, String introduce, Integer discount, Date startdate,
			Date enddate, Integer status) {

		CoupVO coupVO = new CoupVO();

		coupVO.setCoup_no(coup_no);
		coupVO.setCoup_name(coup_name);
		coupVO.setIntroduce(introduce);
		coupVO.setDiscount(discount);
		coupVO.setStartdate(startdate);
		coupVO.setEnddate(enddate);
		coupVO.setStatus(status);
		
		dao.update(coupVO);

		return coupVO;
	}

	public CoupVO updateCoupStatus(Integer status) {

		CoupVO coupVO = new CoupVO();

		coupVO.setStatus(status);
		
		dao.updateStatus(coupVO);

		return coupVO;
	}

	public CoupVO getOneCoup(Integer coup_no) {
		return dao.findByPrimaryKey(coup_no);
	}

	public List<CoupVO> getAll() {
		return dao.getAll();
	}

	public List<CoupVO> getByEndDate(Date enddate) {
		return dao.getByEndDate(enddate);
	}
	
}
