package com.store.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/store.do")
public class storeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getAllStore".equals(action)) { // 切換訂單狀態list
			try {
				/*************************** 開始查詢資料 ****************************************/
				StoreService storesvc = new StoreService();
				List<StoreVO> storelist = storesvc.getAllStore();

				/*************************** 查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("storelist", storelist); // 資料庫取出的VO物件,存入req
				String url = "/backend/store/storeListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				req.getRequestDispatcher("/backend/store/storeListAll.jsp").forward(req, res);
			}
		}

	
	}
}