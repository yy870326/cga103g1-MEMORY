package com.rm_order_list.model;

import java.util.List;

public interface I_Rm_order_listDAO {
	public void insert(Rm_order_listVO rm_order_list);
    public void update(Rm_order_listVO rm_order_list);
    public void delete(Integer rm_order_list_no);
    public Rm_order_listVO findByPrimaryKey(Integer rm_order_list_no);
    public List<Rm_order_listVO> getAll();
}
