package com.rm_order_list.model;

import java.util.List;

public interface I_RmOrderListDAO {
	public RmOrderListVO insert(RmOrderListVO rmOrderListVO);
    public void update(RmOrderListVO rmOrderListVO);
    public void delete(Integer rm_order_list_no);
    public RmOrderListVO findByPrimaryKey(Integer rm_order_list_no);
    public List<RmOrderListVO> getAll();
	public List<RmOrderListVO> getAllByRmOrderNo(Integer rm_order_no);
}
