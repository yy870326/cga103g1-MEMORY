package com.store.model;

public class StoreService {
	private I_StoreDAO dao;
	
	public StoreService() {
		dao = new StoreDAO();
	}
}
