package com.tkt.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "addTktServlet", urlPatterns = { "/tkt/addTkt.do" })
public class addTktServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		// ------------------------- 輸入格式錯誤處理 ----------------

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		
		// tkt_no
		Integer tkt_no;
		
		// tkt_name
		String tkt_name;
		
		// original_amount
		Integer original_amount;
		
		// price
		Integer price;
		
		// tkt_startdate
		Date tkt_startdate;
		
		// tkt_enddate
		Date tkt_enddate;
		
		// locate
		String locate;
		
		// instruction
		String instruction;
		
		// address
		String address;
		
		// notice
		String notice;
		
		// howuse
		String howuse;
		
		// canxpolicy
		String canxpolicy;
		
		// tkt_status
		Integer tkt_status;
		
		// sold_amount
		Integer sold_amount;
		
		// kind
		Integer kind;
	}

}
