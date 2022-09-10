package com.rm_reserve.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rm_type.model.*;


@WebServlet("/RmRsv/RmRsv.do")
public class RmReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// 從首頁搜尋，房型列表顯示符合的結果
		if ("getEnoughType".equals(action)) {

			//處理錯誤訊息
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 ****************************************/
			String arrival = req.getParameter("arrival_date");
			if (arrival == null) {
				errorMsgs.add("請選擇住宿日期");
			} 
			String departure = req.getParameter("departure_date");
			if (departure == null) {
				errorMsgs.add("請選擇退房日期");
			} 
			
			String add = req.getParameter("add");
			String addReg = "^[(\u4e00-\u9fa5)]{2,10}$";
			if (add == null || add.trim().length() == 0) {
				errorMsgs.add("住宿地區請勿空白");
			} else if(!add.trim().matches(addReg)) { 
				errorMsgs.add("地區只能是中文 ,且長度須在2到10個字之間");
            }
			
			Integer qty = Integer.valueOf(req.getParameter("roomtotal"));
			if (qty == null) {
				errorMsgs.add("請選擇房間數量");
			} 
			Integer guest = Integer.valueOf(req.getParameter("people"));
			if (guest == null) {
				errorMsgs.add("請選擇入住人數");
			} 
			// 日期格式處理
			List<String> dateList = new LinkedList<String>();
			dateList = Arrays.asList(arrival.split("-"));
			String arrival_date = dateList.get(2)+"-"+dateList.get(1)+"-"+dateList.get(0);

			List<String> dateList1 = new LinkedList<String>();
			dateList1 = Arrays.asList(departure.split("-"));
			String departure_date = dateList1.get(2)+"-"+dateList1.get(1)+"-"+dateList1.get(0);
			
//			String rangedate = req.getParameter("rangedate");
//			//將住宿期間分成入住日跟退房日
//			List <String> dateList = new LinkedList<String>();
//			dateList =Arrays.asList(rangedate.split(" to "));
//			String arrival_date = dateList.get(0);
//			String departure_date = dateList.get(1);
			
			/*************************** 2.開始查詢資料 ****************************************/
			RmTypeService rmTypeSvc = new RmTypeService();
			// 符合條件的
			List<RmTypeVO> ableList = rmTypeSvc.getEnoughType(java.sql.Date.valueOf(arrival_date),
					java.sql.Date.valueOf(departure_date), qty, guest, add);
			
			
			// 不符合條件的
			List<RmTypeVO> notList = rmTypeSvc.getNotEnoughType(java.sql.Date.valueOf(arrival_date),
					java.sql.Date.valueOf(departure_date), qty, guest);
			
//			if(notList == null) {
//				errorMsgs.add("請確認所輸入的資料是否正確");
//			}
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/room/listAllRmType.jsp");	//查無資料返回登入
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
			/*************************** 3.查詢完成,準備轉交 ************/
			HttpSession session = req.getSession();
//			session.setAttribute("rangedate", rangedate);
			session.setAttribute("arrival_date", arrival_date);
			session.setAttribute("departure_date", departure_date);
			session.setAttribute("qty", qty);
			session.setAttribute("guest", guest); // 人數只有搜尋時會用到，選完房型改記rm_type_no
			session.setAttribute("ableList", ableList);
			session.setAttribute("notList", notList);
			String url = "/frontend/room/rmList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交前台的rmList.jsp
			successView.forward(req, res);
		}
		
		if ("getEnoughTypeByStore".equals(action)) {
			
			/*************************** 1.接收請求參數 ****************************************/
			Integer store_no = Integer.valueOf(req.getParameter("store_no"));
			String rangedate = req.getParameter("rangedate");
			//將住宿期間分成入住日跟退房日
			List <String> dateList = new LinkedList<String>();
			dateList =Arrays.asList(rangedate.split(" to "));
			String arrival_date = dateList.get(0);
			String departure_date = dateList.get(1);
			
			/*************************** 2.開始查詢資料 ****************************************/
			RmTypeService rmTypeSvc = new RmTypeService();
			// 符合條件的
			List<RmTypeVO> ableList = rmTypeSvc.getEnoughTypeByStore(java.sql.Date.valueOf(arrival_date),
					java.sql.Date.valueOf(departure_date), store_no);
			
			/*************************** 3.查詢完成,準備轉交 ************/
			HttpSession session = req.getSession();
			session.setAttribute("rangedate", rangedate);
			session.setAttribute("arrival_date", arrival_date);
			session.setAttribute("departure_date", departure_date);
			session.setAttribute("store_no", store_no);
			session.setAttribute("ableList", ableList);
			String url = "/frontend/room/rmWork.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交前台的rmList.jsp
			successView.forward(req, res);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
