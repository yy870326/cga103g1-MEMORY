package com.store.model;

import java.util.List;

public  interface  I_StoreDAO {
	public void insert(StoreVO storeVO);
	
	public void update(StoreVO storeVO);
	
	public void delete(Integer store_no);
	
	public StoreVO getOneStore(Integer store_no);
	
	public StoreVO getOneStoreByAcc(String store_acc);
	
	public List<StoreVO> getAllStore();
	
	public StoreVO Login(String store_acc, String store_pwd);
	
	public List<StoreVO> Survy();
	
}
