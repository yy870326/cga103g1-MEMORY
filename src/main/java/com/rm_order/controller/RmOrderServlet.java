package com.rm_order.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rm_order.model.*;

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

		if ("getAllRmOrder".equals(action)) {
			try {
				/*************************** 開始查詢資料 ****************************************/
				RmOrderService rmOrderSvc = new RmOrderService();
				List<RmOrderVO> orderlist = rmOrderSvc.getAllRmOrder();

				/*************************** 查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("orderlist", orderlist);
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
				req.setAttribute("orderlist", orderlist);
				String url = "/frontend/room/listStoreOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				req.getRequestDispatcher("/frontend/room/listStoreOrder.jsp").forward(req, res);
			}
		}
		if ("cancel".equals(action)) { // 取消訂單

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer rm_order_no = new Integer(req.getParameter("rm_order_no"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				RmOrderVO rmOrderVO = new RmOrderVO();
				RmOrderService rmOrderSvc = new RmOrderService();
				rmOrderVO = rmOrderSvc.cancel(rm_order_no);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("rmOrderVO", rmOrderVO);
				String url = "/frontend/room/listStoreOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				req.getRequestDispatcher("/frontend/room/listStoreOrder.jsp").forward(req, res);
			}
		}
	}
}