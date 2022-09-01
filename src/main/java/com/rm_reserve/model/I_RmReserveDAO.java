package com.rm_reserve.model;

import java.sql.Date;
import java.util.List;


public interface I_RmReserveDAO {
	
	
	public void insert(); // 新增預約表(排程用)
    public void reserve(Integer qty, Integer rm_type_no, Date start_date, Date end_date); // 預約訂房
    public void cancel(Integer qty, Integer rm_type_no, Date start_date, Date end_date);  // 取消訂房
    public void delete();  // 刪除預約表(排程用)
    public RmReserveVO findByPrimaryKey(Integer serial_no);
    public List<RmReserveVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}