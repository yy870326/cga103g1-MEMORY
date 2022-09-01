package com.cart.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CartCheckOutServlet", urlPatterns = { "/cart/cartCheckOut.do" })
public class CartCheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain; charset = UTF-8");
		
		// -------------- getParameter --------------
		//	無法獲得前端送來的 tkt_no 陣列	
		
		Enumeration<String> paramNames = req.getParameterNames();
		
		while(paramNames.hasMoreElements()) {
			String tkt_no = (String) paramNames.nextElement();
			String[] tkt_nos = req.getParameterValues(tkt_no);
			
			System.out.println(tkt_nos);
		}
		
		
		// -------------- 永續層 --------------
		
	}

}
