package com.tkt_img.model;

import java.util.List;

public interface I_TktImgDAO {
	 public void insert(TktImgVO tktimgVO);
     public void update(TktImgVO tktimgVO);
     public void delete(Integer TktImgVO);
     public TktImgVO findByPrimaryKey(Integer TktImgVO);
     public List<TktImgVO> getAll();
}
