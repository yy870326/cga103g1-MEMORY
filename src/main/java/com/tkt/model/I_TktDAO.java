package com.tkt.model;

import java.util.List;

public interface I_TktDAO {
	
	public void insert(TktVO tktVO);
    public void update(TktVO tktVO);
//    public void delete(Integer tkt_no);
    public TktVO findByPrimaryKey(Integer tkt_no);
    public List<TktVO> getAll();
//    public List<TktVO> getLocate(String locate);
    
}
