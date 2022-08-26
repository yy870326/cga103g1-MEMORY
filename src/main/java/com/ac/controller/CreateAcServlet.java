package com.ac.controller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import com.ac.model.AcServiceImpl;
import com.ac.model.AcVO;
import com.ac_pic.model.AcPicService;
import com.ac_pic.model.AcPicVO;

@WebServlet("/createAc")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class CreateAcServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        
        System.out.println("request 過來了");
        
		String createAc = req.getParameter("action");
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		
		Part filePart = req.getPart("upload");
		
		InputStream fileContent = filePart.getInputStream();
		byte[] image = org.apache.commons.io.IOUtils.toByteArray(fileContent);
		
		AcServiceImpl acServiceImpl2 = new AcServiceImpl();
	    List<AcVO> aclist = acServiceImpl2.getAll();
		
		
		if ("發表".equals(createAc)) { // acCreate.jsp的請求

			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String title = req.getParameter("title");
			if (title == null || title.trim().length() == 0) {
				errorMsgs.put("title","文章標題請勿空白");
//				errorMsgs.add("文章標題請勿空白");
			}
			Integer value = 0;
			String customRadio = req.getParameter("customRadio");
			System.out.println("Radio的值" + customRadio);
			if (customRadio == null || customRadio.trim().length() == 0) {
				errorMsgs.put("customRadio","請選擇文章種類");
//				errorMsgs.add("請選擇文章種類");
			}else {
				value = Integer.parseInt(customRadio);
			}

			String content = req.getParameter("content");
			if (content == null || content.trim().length() == 0) {
				errorMsgs.put("content","文章內容請勿空白");
//				errorMsgs.add("文章內容請勿空白");
			}
			
			String createTime = req.getParameter("createTime");
			System.out.println(createTime);
//			2022-8-21 23:4:30
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",Locale.TAIWAN);
			LocalDateTime time = LocalDateTime.parse(createTime, formatter);
			if (time == null) {
				errorMsgs.put("time","請填寫時間");
//				errorMsgs.add("請填寫時間");
			}
			
			if (image.length == 0) {
				errorMsgs.put("upload","請上傳一張照片");
			}

			System.out.println(errorMsgs);
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("acVo", acVo); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/ac/acCreate.jsp");
				failureView.forward(req, res);
				return;
			}
			
			
			AcVO acVo = new AcVO();
			acVo.setAc_title(title);
			acVo.setAc_type_no(value);
			acVo.setAc_content(content);
			acVo.setAc_time(time);
			acVo.setMem_no(1);
			AcPicVO acPicVO = new AcPicVO();
			acPicVO.setAc_pic_img(image);
			acPicVO.setAc_no(aclist.size() + 1);
	

			/*************************** 2.開始新增資料 ***************************************/
			AcServiceImpl acServiceImpl = new AcServiceImpl();
			Integer i = acServiceImpl.createAc(acVo);
			
			AcPicService acPicService =  new AcPicService();
			acPicService.insertImage(acPicVO);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/frontend/ac/acCardPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
	}

}
