package com.mem_coup.model;

import java.util.List;

public interface I_MemCoupDAO {
	
	public void insert(MemCoupVO memCoupVO);
//    public void update(MemCoupVO memCoupVO);
    public MemCoupVO findByPrimaryKey(Integer mem_coup_no);
    public List<MemCoupVO> getAll();
  
//  public void delete(Integer memCoupVO);
    
}
