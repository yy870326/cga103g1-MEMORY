package com.rm_pic.model;

import java.util.List;

import com.auth_fun.model.AuthFunVO;

public class RmPicService {
	
	private I_RmPicDAO dao;
	
	public RmPicService() {
		dao = new RmPicDAO();
	}

	public RmPicVO addRmPic(Integer rm_pic_no, Integer rm_type_no,byte[] rm_pic_img) {
		RmPicVO rmPicVO = new RmPicVO();
		
		rmPicVO.setRm_pic_no(rm_pic_no);
		rmPicVO.setRm_type_no(rm_type_no);
		rmPicVO.setRm_pic_img(rm_pic_img);
		dao.insert(rmPicVO);
		
		return rmPicVO;
		
	}

	public RmPicVO updateRmPic(Integer rm_pic_no, Integer rm_type_no,byte[] rm_pic_img) {
		RmPicVO rmPicVO = new RmPicVO();
		
		rmPicVO.setRm_pic_no(rm_pic_no);
		rmPicVO.setRm_type_no(rm_type_no);
		rmPicVO.setRm_pic_img(rm_pic_img);
		dao.update(rmPicVO);
		
		return rmPicVO;
	}
	
	public void deleteRmPic(Integer rm_pic_no) {
		dao.delete(rm_pic_no);
	}
	
	public RmPicVO getOneAuthFun(Integer rm_pic_no) {
		return dao.findByPrimaryKey(rm_pic_no);
}
	public List<RmPicVO> getAll() {
		return dao.getAll();
}
	
}	
