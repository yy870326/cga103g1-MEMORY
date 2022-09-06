package com.rm_reserve.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rm_type.model.*;
import com.store.model.*;

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
		System.out.println("Action" + action);
		// 從首頁搜尋，房型列表顯示符合的結果
		if ("getEnoughType".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/
			String rangedate = req.getParameter("rangedate");
			String add = req.getParameter("add");
			Integer qty = new Integer(req.getParameter("qty"));
			Integer guest = new Integer(req.getParameter("guest"));
			

			// 將收到的住宿期間分割成 起始日 和 結束日
			List<String> dateList = new LinkedList<String>();
			dateList = Arrays.asList(rangedate.split(" to "));
			String arrival_date = dateList.get(0);
			String departure_date = dateList.get(1);

			/*************************** 2.開始查詢資料 ****************************************/
			RmTypeService rmTypeSvc = new RmTypeService();
			// 符合條件的
			List<RmTypeVO> ableList = rmTypeSvc.getEnoughType(java.sql.Date.valueOf(arrival_date),
					java.sql.Date.valueOf(departure_date), qty, guest, add);
			
			
			// 不符合條件的
			List<RmTypeVO> notList = rmTypeSvc.getNotEnoughType(java.sql.Date.valueOf(arrival_date),
					java.sql.Date.valueOf(departure_date), qty, guest);

			/*************************** 3.查詢完成,準備轉交 ************/
			HttpSession session = req.getSession();
			session.setAttribute("rangedate", rangedate);
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
