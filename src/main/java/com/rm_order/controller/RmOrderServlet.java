package com.rm_order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rm_order.model.RmOrderService;
import com.rm_order.model.RmOrderVO;

@WebServlet("/RmOrder")
public class RmOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getAllRmOrder".equals(action)) { // 切換訂單狀態list
			try {
				/*************************** 開始查詢資料 ****************************************/
				RmOrderService rmOrderSvc = new RmOrderService();
				List<RmOrderVO> orderlist = rmOrderSvc.getAllRmOrder();

				/*************************** 查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("orderlist", orderlist); // 資料庫取出的VO物件,存入req
				String url = "/frontend/room/listStoreOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				req.getRequestDispatcher("/frontend/room/listStoreOrder.jsp").forward(req, res);
			}
		}

		if ("getAllStatus".equals(action)) { // 切換訂單狀態list

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer rm_order_status = new Integer(req.getParameter("rm_order_status"));

				/*************************** 2.開始查詢資料 ****************************************/
				RmOrderService rmOrderSvc = new RmOrderService();
				List<RmOrderVO> orderlist = rmOrderSvc.getAllStatus(rm_order_status);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("rm_order_status", rm_order_status);
				req.setAttribute("orderlist", orderlist); // 資料庫取出的VO物件,存入req
				String url = "/frontend/room/listStoreOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				req.getRequestDispatcher("/frontend/room/listStoreOrder.jsp").forward(req, res);
			}
		}
	}
}