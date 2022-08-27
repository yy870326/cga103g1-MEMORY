package com.ac.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ac.model.AcServiceImpl;
import com.ac.model.AcVO;
import com.ac_pic.model.AcPicService;
import com.ac_pic.model.AcPicVO;



@WebServlet("/getOneAc")
public class GetOneAcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");	
		String action = request.getParameter("action");
		
		AcServiceImpl acServiceImpl = new AcServiceImpl();
		AcVO acVO = new AcVO();
		AcPicService acPicService = new AcPicService();
		List<AcPicVO> acPicVO = new LinkedList<AcPicVO>();
		
		if("acInner".equals(action)) {
			try {
				Integer acNo = new Integer(request.getParameter("acNo"));
				acVO = acServiceImpl.getOneAcById(acNo);
				acPicVO = acPicService.getOneByAcNo(acNo);
				byte[] acImage = acPicVO.stream().findFirst().get().getAc_pic_img();
				request.setAttribute("acVO", acVO);
				request.setAttribute("acImage", acImage);
				request.getRequestDispatcher("/frontend/ac/acDetailPage.jsp")
				.forward(request, response);
				return;
			}catch(Exception ex) {
				request.getRequestDispatcher("/frontend/ac/acCardPage.jsp")
				.forward(request, response);
				return;
			}
		}
	}


}
