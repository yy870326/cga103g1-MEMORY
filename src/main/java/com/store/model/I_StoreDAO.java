package com.store.model;

import java.util.List;

public  interface  I_StoreDAO {
	public StoreVO insert(StoreVO storeVO);
	
	public void update(StoreVO storeVO);
	
	public void delete(Integer store_no);
	
	public StoreVO queryStore(Integer store_no);
	
	public List<StoreVO> getAllStore();
	
}
