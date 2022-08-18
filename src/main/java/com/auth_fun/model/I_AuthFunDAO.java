package com.auth_fun.model;

import java.util.List;

public interface I_AuthFunDAO {
	
	public void insert (AuthFunVO authFunVO);
	
	public void update(AuthFunVO authFunVO);
	
	public AuthFunVO getOne(Integer authFunVo);
	
	public List<AuthFunVO> getAll();
	
}
