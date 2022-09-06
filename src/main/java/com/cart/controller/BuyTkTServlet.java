package com.cart.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.model.CartItemService;
import com.cart.model.CartItemVO;

@WebServlet(name = "BuyTkTServlet", urlPatterns = { "/cart/buyTkt.do" })
public class BuyTkTServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain; charset = UTF-8");
		
		// ------------------ getParameter ------------------
		// 接收結帳清單所有的 tkt_no
		
		String sessionId = null;
		
		Enumeration<String> en = req.getParameterNames();
		while (en.hasMoreElements()) {
//			String name = (String) en.nextElement();
//			String values[] = req.getParameterValues(name); 
			String tkt_nos = (String) en.nextElement();
			String values[] = req.getParameterValues("tkt_no"); 
			
			Integer tkt_no = 0;
			if (values != null) { 
				for (int i = 0; i < values.length; i++) {
					sessionId = (String) req.getSession().getAttribute("sessionId"); // 取得 session 的 ID
//					System.out.println("-----------" +sessionId);
					CartItemService cartItemSrv = new CartItemService();
					tkt_no = Integer.valueOf(values[i]);
//					System.out.println("-----------" +tkt_no);
					cartItemSrv.updateTktsoldAmount(sessionId, tkt_no);
//					cartItemSrv.updateTktoriAmount(sessionId, tkt_no);
					cartItemSrv.delItem(sessionId, tkt_no); // 透過已購買的 tkt_no 清除購物車 tkt_no
				}
			}
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/frontend/cart/testOrderList.jsp");
		rd.forward(req, res);

	}

}
