package com.rm_reserve.model;

import java.util.List;


public interface I_RmReserveDAO {
	
	public void insert(RmReserveVO rm_reserveVO);
    public void update(RmReserveVO rm_reserveVO);
    public void chanel(RmReserveVO rmreserveVO);
    public RmReserveVO getOne (Integer serial_no);
    public List<RmReserveVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}