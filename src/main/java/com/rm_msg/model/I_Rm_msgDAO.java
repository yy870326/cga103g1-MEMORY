package com.rm_msg.model;

import java.util.List;


public interface I_Rm_msgDAO {
	public void insert(Rm_msgVO rm_msgVO);
    public void update(Rm_msgVO rm_msgVO);
    public void delete(Integer rm_msg_no);
    public Rm_msgVO findByPrimaryKey(Integer rm_msg_no);
    public List<Rm_msgVO> getAll();
}
