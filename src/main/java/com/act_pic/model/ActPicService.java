package com.act_pic.model;

import java.util.List;
import java.util.stream.Collectors;

public class ActPicService {
	
	private I_ActPicDAO dao;
	
	public ActPicService() {
		dao = new ActPicDAO();
	}

	// 新增 活動照片
	public void uploadActPic(Integer act_no, byte[] act_pic) {
		ActPicVO actPic = new ActPicVO();
		actPic.setAct_no(act_no);
		actPic.setAct_pic(act_pic);
		dao.insert(actPic);
	}

	// 更新、修改 活動照片
	public void alterActPic(Integer act_pic_no, Integer act_no, byte[] act_pic) {
		ActPicVO actPic = new ActPicVO();
		actPic.setAct_pic(act_pic);
		actPic.setAct_pic_no(act_pic_no);
		actPic.setAct_no(act_no);
		dao.update(actPic);
	}
	
	// 取得 活動 照片
	public List<ActPicVO> getOneActPic(Integer actPicNo, Integer actNo) {
//		return dao.findActPic(act_pic_no, actNo);
		
		return dao
				.getAll()
				.stream()
				.filter(act -> act.getAct_no() == actPicNo && act.getAct_pic_no() == actPicNo)
				.toList();
	}
	
	public List<ActPicVO> getAll() {
		return dao.getAll();
	}
	
	
}
