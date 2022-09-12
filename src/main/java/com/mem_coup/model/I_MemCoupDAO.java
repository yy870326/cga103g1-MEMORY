package com.mem_coup.model;

import java.sql.Connection;
import java.util.List;

public interface I_MemCoupDAO {
	
	public void insert(MemCoupVO memCoupVO);
    public void update(MemCoupVO memCoupVO);
    public MemCoupVO findByPrimaryKey(Integer mem_coup_no);
    public List<MemCoupVO> getAll();
    public List<MemCoupVO> getOneMember(Integer mem_no); // 會員查看自己有哪些優惠券
    public void changeState(Connection conn, Integer mem_coup_no, Integer coup_state);
    //  public void delete(Integer memCoupVO);

    
}
