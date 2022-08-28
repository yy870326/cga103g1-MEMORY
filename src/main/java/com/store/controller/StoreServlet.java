package com.store.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/store.do")
public class StoreServlet extends HttpServlet {
	



	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOneStore".equals(action)) { 
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			//================== 接收請求參數===========================
			String str = req.getParameter("store_no");
			if(str == null || (str.trim()).length() == 0 ) {
				errorMsgs.put("store_no","請輸入帳號");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/store/storeSelectPage.jsp");
				failureView.forward(req, res);
				return;
			}
			Integer store_no = null;
			try {
				store_no = Integer.valueOf(str);
			}catch(Exception e) {
				errorMsgs.put("store_no","帳號格式不正確");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/store/StoreSelectPage.jsp");
				failureView.forward(req, res);
				return;
			}
			
			
			//======================存取資料============================
			StoreService storeSvc = new StoreService();
			StoreVO storevo = storeSvc.getOneStore(store_no);
			
			if(storevo == null) {
				errorMsgs.put("store_no","查無此帳號");;
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/store/storeSelectPage.jsp");
				failureView.forward(req, res);
				return;
			}
			
			//======================轉交資料=============================
			req.setAttribute("storeVO", storevo);
			String url = "/backend/store/storeListOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("updateStore".equals(action)) { 
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			//================== 接收請求參數===========================
			Integer store_no = Integer.valueOf(req.getParameter("store_no"));
			
			
			
			//======================存取資料============================
			StoreService storeSvc = new StoreService();
			StoreVO storevo = storeSvc.getOneStore(store_no);
			
			
			
			//======================轉交資料=============================
			req.setAttribute("storeVO", storevo);
			String url = "/backend/store/storeUpdate.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("update".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//========================接收參數==============================
				
			Integer store_no = Integer.valueOf(req.getParameter("store_no").trim());
			String store_acc = req.getParameter("store_acc");
			String store_pwd = req.getParameter("store_pwd");
			Integer acc_status = Integer.valueOf(req.getParameter("acc_status").trim());
			String store_name = req.getParameter("store_name");
			String store_gui =req.getParameter("store_gui");
			String store_rep = req.getParameter("store_rep");
			String store_tel = req.getParameter("store_tel");
			String store_fax = req.getParameter("store_fax");
			String store_add = req.getParameter("store_add");
			String store_poc = req.getParameter("store_poc");
			String store_con_phone = req.getParameter("store_con_phone");
			String store_con_add = req.getParameter("store_con_add");
			String store_email = req.getParameter("store_email");
			
			java.sql.Date store_reg_date = null;
			try {
			store_reg_date = java.sql.Date.valueOf(req.getParameter("store_reg_date"));
			}catch(IllegalArgumentException e) {
				store_reg_date = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.put("store_reg_date","請輸入註冊日期");
			}
			String bank_account = req.getParameter("bank_account");
			Integer store_tkt_rating_score = Integer.valueOf(req.getParameter("store_tkt_rating_score").trim());
			Integer store_tkt_rating_count = Integer.valueOf(req.getParameter("store_tkt_rating_count").trim());
			Integer store_tkt_rating = Integer.valueOf(req.getParameter("store_tkt_rating").trim());
			Integer store_rm_rating_score = Integer.valueOf(req.getParameter("store_rm_rating_score").trim());
			Integer store_rm_rating_count = Integer.valueOf(req.getParameter("store_rm_rating_count").trim());
			Integer store_act_rating_score = Integer.valueOf(req.getParameter("store_act_rating_score").trim());
			Integer store_act_rating_count = Integer.valueOf(req.getParameter("store_act_rating_count").trim());
			Integer store_report_count = Integer.valueOf(req.getParameter("store_report_count").trim());
		
			StoreVO storeVO = new StoreVO();
		}

	
	}
}