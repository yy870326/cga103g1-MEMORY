package com.auth_fun.controller;

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

import com.auth_fun.model.AuthFunService;
import com.auth_fun.model.AuthFunVO;
import com.coup.model.CoupService;
import com.coup.model.CoupVO;

@WebServlet(name = "UpdateAuthServlet", urlPatterns = { "/authfun/updateAuthFun.do" })
public class UpdateAuthFunServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		///////////////////////////// getOneUpdate ////////////////////////

		if ("getOneUpdate".equals(action)) {

			// ------------- getParameter -------------
			Integer fun_no = Integer.valueOf(req.getParameter("fun_no"));

			// -------------- getOne -------------
			AuthFunService authFunSrv = new AuthFunService();
			AuthFunVO authFunVO = authFunSrv.getOneAuthFun(fun_no);

			// ------------- forward -------------
			req.setAttribute("authFunVO", authFunVO);
			RequestDispatcher successView = req.getRequestDispatcher("/backend/authfun/updateAuthFun.jsp");
			successView.forward(req, res);
		}

		///////////////////////////// update ////////////////////////

		if ("update".equals(action)) {
			// ------------------------- 輸入格式錯誤處理 -----------------

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// fun_no
			Integer fun_no = Integer.valueOf(req.getParameter("fun_no"));

			// fun_name
			String fun_name = req.getParameter("fun_name");

			if (fun_name == null || fun_name.trim().length() == 0) {
				errorMsgs.add("請填入權限名稱");
			}

			AuthFunVO authFunVO = new AuthFunVO();

			authFunVO.setFun_name(fun_name);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("authFunVO", authFunVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/authfun/updateAuthFun.jsp");
				failureView.forward(req, res);
				return; // 中斷
			}

			// ----------------------- 修改資料 ---------------------
			AuthFunService authFunSrv = new AuthFunService();
			authFunVO = authFunSrv.addAuthFun(null, fun_name);

			// --------------------- 修改完成,準備轉交 ---------------
			req.setAttribute("authFunVO", authFunVO);
			RequestDispatcher successView = req.getRequestDispatcher("/backend/authfun/listAllAuthFun.jsp");
			successView.forward(req, res);
//					}

		}

	}
}