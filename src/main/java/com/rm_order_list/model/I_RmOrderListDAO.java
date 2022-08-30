package com.rm_order_list.model;

import java.util.List;

public interface I_RmOrderListDAO {
	public RmOrderListVO insert(RmOrderListVO rmOrderListVO);
    public void update(RmOrderListVO rmOrderListVO);
    public void delete(Integer rm_order_list_no);
    public RmOrderListVO findByPrimaryKey(Integer rm_order_list_no);
    public List<RmOrderListVO> getAll();
	public List<RmOrderListVO> getAllByRmOrderNo(Integer rm_order_no);
	public void changeROL(RmOrderListVO rmOrderListVO);
	public List<RmOrderListVO> getAllByRmTypeNo(Integer rm_type_no);
	public void insert2 (RmOrderListVO rmOrderListVO , java.sql.Connection con);
}
