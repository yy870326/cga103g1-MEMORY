package com.act_pic.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.act_pic.model.ActPicService;
import com.act_pic.model.ActPicVO;

@WebServlet("/getAllActPic")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class GetAllActPicServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 可能用不到
//		req.setCharacterEncoding("UTF-8");
//		res.setContentType("image/jpeg, image/jpg, image/png, image/gif, */*");
//		System.out.println("JQuery Ajax Request -> GetAllActPicServlet");
//		
//		ActPicService actPicService = new ActPicService();
//		List<ActPicVO> actPicList = actPicService.getAll();
//		ServletOutputStream sos = res.getOutputStream();
//		byte[] image = new byte[999999999];
//		image = actPicList.get(0).getAct_pic();
//		sos.write(image);
//		sos.close();				
	}

}
