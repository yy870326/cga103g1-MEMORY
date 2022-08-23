package com.rm_reserve.model;

import java.sql.Date;
import java.util.List;

import com.auth_fun.model.AuthFunVO;

public class RmReserveService {

	private I_RmReserveDAO dao;
	
	public RmReserveService() {
		dao = new RmReserveDAO();
	}
	public RmReserveVO addRmReserve(Integer serial_no,Integer rm_type_no,Integer rm_type_amount,Date rm_schedule_date,Integer reservation_amount) {
		RmReserveVO rmReserveVO = new RmReserveVO();
		
		rmReserveVO.setSerial_no(serial_no);
		rmReserveVO.setRm_type_no(rm_type_no);
		rmReserveVO.setRm_type_amount(rm_type_amount);
		rmReserveVO.setRm_schedule_date(rm_schedule_date);
		rmReserveVO.setReservation_amount(reservation_amount);
		dao.insert(rmReserveVO);
		
		return rmReserveVO;
		
	}
	
	public RmReserveVO updateRmReserve(Integer serial_no,Integer rm_type_no,Integer rm_type_amount,Date rm_schedule_date,Integer reservation_amount) {
		RmReserveVO rmReserveVO = new RmReserveVO();
		
		rmReserveVO.setSerial_no(serial_no);
		rmReserveVO.setRm_type_no(rm_type_no);
		rmReserveVO.setRm_type_amount(rm_type_amount);
		rmReserveVO.setRm_schedule_date(rm_schedule_date);
		rmReserveVO.setReservation_amount(reservation_amount);
		dao.update(rmReserveVO);
		
		return rmReserveVO;
	
	}
	public RmReserveVO getOneRmReserve(Integer serial_no) {
		return dao.findByPrimaryKey(serial_no);
}
	public List<RmReserveVO> getAll() {
		return dao.getAll();
}
}
