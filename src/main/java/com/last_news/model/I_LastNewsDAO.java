package com.last_news.model;

import java.util.List;

public interface I_LastNewsDAO {
	//新增訊息
	public void insert(LastNewsVO lastNewsVO);
	//下架訊息
	public void delete(LastNewsVO lastNewsVO);
	//顯示訊息
	public List<LastNewsVO> getall();

}
