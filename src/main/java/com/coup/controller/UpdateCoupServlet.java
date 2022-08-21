package com.coup.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coup.model.CoupService;
import com.coup.model.CoupVO;

@WebServlet(name = "UpdateCoupServlet", urlPatterns = { "/coup/updateCoup.do" })
public class UpdateCoupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		if ("coupUpdate".equals(action)) {

			// ------------------------- 輸入格式錯誤處理 -----------------

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// coup_no
			Integer coup_no = Integer.valueOf(req.getParameter("coup_no"));

			// coup_name
			String coup_name = req.getParameter("coup_name");
			if (coup_name == null || coup_name.length() == 0) {
				errorMsgs.add("請填入優惠券名稱");
			}

			// introduce
			String introduce = req.getParameter("introduce");
			if (introduce == null || introduce.length() == 0) {
				errorMsgs.add("請填入優惠券介紹");
			}

			// discount
			Integer discount = Integer.valueOf(req.getParameter("discount"));
			if (discount == null) {
				errorMsgs.add("請填入優惠券折扣金額");
			}

			// startdate
			Date startdate = null;

			try {
				startdate = Date.valueOf(req.getParameter("startdate"));
			} catch (IllegalArgumentException e) {
				startdate = new Date(System.currentTimeMillis());
				errorMsgs.add("請填入優惠券起始日期");
			}

			// enddate
			Date enddate = null;

			try {
				enddate = Date.valueOf(req.getParameter("enddate"));
			} catch (IllegalArgumentException e) {
				enddate = new Date(System.currentTimeMillis());
				errorMsgs.add("請填入優惠券起始日期");
			}

			// status
			Integer status = Integer.valueOf(req.getParameter("status"));
			if (status == null) {
				errorMsgs.add("請選擇優惠券狀態");
			}

			CoupVO coupVO = new CoupVO();
			coupVO.setCoup_no(coup_no);
			coupVO.setCoup_name(coup_name);
			coupVO.setIntroduce(introduce);
			coupVO.setDiscount(discount);
			coupVO.setStartdate(startdate);
			coupVO.setEnddate(enddate);
			coupVO.setStatus(status);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("coupVO", coupVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/coup/updateCoup.jsp");
				failureView.forward(req, res);
				return;
			}

			// ----------------------- 修改資料 ---------------------
			CoupService coupSrv = new CoupService();
			coupVO = coupSrv.updateCoup(coup_no, coup_name, introduce, discount, startdate, enddate, status);

			// --------------------- 修改完成,準備轉交 ---------------
			req.setAttribute("coupVO", coupVO);
			RequestDispatcher successView = req.getRequestDispatcher("/backend/coup/listAllCoup.jsp");
			successView.forward(req, res);

		}

	}

}
