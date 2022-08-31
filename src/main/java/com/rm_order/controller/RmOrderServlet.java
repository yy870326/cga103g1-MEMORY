package com.rm_order.controller;

import java.io.IOException;
import java.util.ArrayList;
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

		if ("getAllRmOrder".equals(action)) { // 取得該廠商所有訂單
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer store_no = new Integer(req.getParameter("store_no"));
				/*************************** 開始查詢資料 ****************************************/
				RmOrderService rmOrderSvc = new RmOrderService();
				List<RmOrderVO> orderlist = rmOrderSvc.getAllByStore(store_no);

				/*************************** 查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("orderlist", orderlist);
				String url = "/frontend/room/listStoreOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				req.getRequestDispatcher("/frontend/room/listStoreOrder.jsp").forward(req, res);
			}
		}

		if ("getStoreStatus".equals(action)) { // 取得該廠商訂單狀態

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer store_no = new Integer(req.getParameter("store_no"));
				Integer rm_order_status = new Integer(req.getParameter("rm_order_status"));

				/*************************** 2.開始查詢資料 ****************************************/
				RmOrderService rmOrderSvc = new RmOrderService();
				List<RmOrderVO> orderlist = rmOrderSvc.getStoreStatus(store_no, rm_order_status);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

				req.setAttribute("orderlist", orderlist);
				String url = "/frontend/room/listStoreOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				req.getRequestDispatcher("/frontend/room/listStoreOrder.jsp").forward(req, res);
			}
		}
		if ("getOne".equals(action)) {

			try {
				
				/*************************** 1.接收請求參數 ****************************************/
				Integer rm_order_no = new Integer(req.getParameter("rm_order_no"));
				Integer store_no = new Integer(req.getParameter("store_no"));
				I_RmOrderDAO irm = new RmOrderDAO();
				Integer exp = irm.getOne(rm_order_no).getStore_no();
				
				System.out.println(exp);
				System.out.println(store_no);
				
				if (!exp.equals(store_no)) {
					RequestDispatcher failview = req.getRequestDispatcher("/frontend/room/listStoreOrder.jsp");
					failview.forward(req, res);
					return;
				}
				
				/*************************** 2.開始查詢資料 ****************************************/
				RmOrderService rmOrderSvc = new RmOrderService();
				List<RmOrderVO> orderlist = new ArrayList<RmOrderVO>();
				orderlist.add(rmOrderSvc.getOne(rm_order_no));
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

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