package com.rm_type.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rm_type.model.RmTypeService;
import com.rm_type.model.RmTypeVO;

public class RmTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");

		if ("insertRm".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer store_no = new Integer(req.getParameter("store_no"));
				String rm_name = null;
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				if (rm_name == null || rm_name.trim().length() == 0) {
					errorMsgs.add("房型名稱 請勿空白");
				} else if (!rm_name.trim().matches(nameReg)) {
					errorMsgs.add("房型名稱 只能是中、英文字母、數字和_ , 且長度必需在1到10之間");
				}
				Integer rm_total = null;
				try {
					rm_total = new Integer(req.getParameter("rm_total").trim());
				} catch (NumberFormatException e) {
					rm_total = 5;
					errorMsgs.add("房型數量請填數字");
				}

				Integer rm_people = null;
				try {
					rm_people = new Integer(req.getParameter("rm_people").trim());
				} catch (NumberFormatException e) {
					rm_people = 2;
					errorMsgs.add("可入住人數請填數字");
				}

				Integer rm_price = null;
				try {
					rm_price = new Integer(req.getParameter("rm_price").trim());
				} catch (NumberFormatException e) {
					rm_price = 2000;
					errorMsgs.add("金額請填數字");
				}

				Integer rm_area = null;
				try {
					rm_area = new Integer(req.getParameter("rm_area").trim());
				} catch (NumberFormatException e) {
					rm_area = 10;
					errorMsgs.add("房型坪數請填數字");
				}

				String rm_intro = req.getParameter("rm_intro");
				if (rm_intro == null || rm_intro.trim().length() == 0) {
					errorMsgs.add("房型資訊 請勿空白");
				}
				

				RmTypeVO rmTypeVO = new RmTypeVO();
				rmTypeVO.setStore_no(store_no);
				rmTypeVO.setRm_name(rm_name);
				rmTypeVO.setRm_total(rm_total);
				rmTypeVO.setRm_people(rm_people);
				rmTypeVO.setRm_price(rm_price);
				rmTypeVO.setRm_area(rm_area);
				rmTypeVO.setRm_intro(rm_intro);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rmTypeVO", rmTypeVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/XXX/XXX.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				RmTypeService rmTypeSvc = new RmTypeService();
				rmTypeVO = rmTypeSvc.insertRm(store_no, rm_name, rm_total, rm_people, rm_price, rm_area, rm_intro);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/frontend/room/listAllRmType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交XXX.jsp頁面
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/room/addRmType.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer rm_type_no = Integer.valueOf(req.getParameter("rm_type_no"));
				
				/***************************2.開始查詢資料****************************************/
				RmTypeService rmtypeSvc = new RmTypeService();
				RmTypeVO rmTypeVO = rmtypeSvc.getOneRm(rm_type_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("rmTypeVO", rmTypeVO);         // 資料庫取出的empVO物件,存入req
				String url = "/room/update_rm_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_rm_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("updateRm".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer rm_type_no = new Integer(req.getParameter("rm_type_no").trim());
				String rm_name = null;
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				if (rm_name == null || rm_name.trim().length() == 0) {
					errorMsgs.add("房型名稱 請勿空白");
				} else if (!rm_name.trim().matches(nameReg)) {
					errorMsgs.add("房型名稱 只能是中、英文字母、數字和_ , 且長度必需在1到10之間");
				}
				Integer rm_total = null;
				try {
					rm_total = new Integer(req.getParameter("rm_total").trim());
				} catch (NumberFormatException e) {
					rm_total = 5;
					errorMsgs.add("房型數量請填數字");
				}

				Integer rm_people = null;
				try {
					rm_people = new Integer(req.getParameter("rm_people").trim());
				} catch (NumberFormatException e) {
					rm_people = 2;
					errorMsgs.add("可入住人數請填數字");
				}

				Integer rm_price = null;
				try {
					rm_price = new Integer(req.getParameter("rm_price").trim());
				} catch (NumberFormatException e) {
					rm_price = 2000;
					errorMsgs.add("金額請填數字");
				}

				Integer rm_area = null;
				try {
					rm_area = new Integer(req.getParameter("rm_area").trim());
				} catch (NumberFormatException e) {
					rm_area = 10;
					errorMsgs.add("房型坪數請填數字");
				}

				String rm_intro = req.getParameter("rm_intro");
				if (rm_intro == null || rm_intro.trim().length() == 0) {
					errorMsgs.add("房型資訊 請勿空白");
				}
				
				Boolean rm_update = new Boolean(req.getParameter("rm_update"));

				RmTypeVO rmTypeVO = new RmTypeVO();
				rmTypeVO.setRm_name(rm_name);
				rmTypeVO.setRm_total(rm_total);
				rmTypeVO.setRm_people(rm_people);
				rmTypeVO.setRm_price(rm_price);
				rmTypeVO.setRm_area(rm_area);
				rmTypeVO.setRm_intro(rm_intro);
				rmTypeVO.setRm_update(rm_update);
				rmTypeVO.setRm_type_no(rm_type_no);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rmTypeVO", rmTypeVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/room/updateRmType.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 ***************************************/
				RmTypeService rmTypeSvc = new RmTypeService();
				rmTypeVO = rmTypeSvc.updateRm(rm_name, rm_total, rm_people, rm_price, rm_area, rm_intro, rm_update, rm_type_no);

				/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
				String url = "/frontend/room/listAllRmType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAll頁面
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/room/addRmType.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOneType".equals(action)) { // 來自 .jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("rm_type_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入房型編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/room/XXX.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer rm_type_no = null;
				try {
					rm_type_no = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("房型編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/room/XXX.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RmTypeService rmTypeSvc = new RmTypeService();
				RmTypeVO rmTypeVO = rmTypeSvc.getOneRm(rm_type_no);
				if (rmTypeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/room/XXX.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("rmTypeVO", rmTypeVO); // 資料庫取出的rmTypeVO物件,存入req
				String url = "/frontend/room/XXX.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 XXX.jsp
				successView.forward(req, res);
		}
	}
}
