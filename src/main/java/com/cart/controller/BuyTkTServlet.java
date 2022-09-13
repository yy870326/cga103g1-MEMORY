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
					
					// 購物車結帳要做的事
					// 更新庫存、銷售量
					// 更改會員優惠券使用狀態
					// 把資料新增到訂單、訂單明細 table
					// 把 QR Code 圖片存進訂單明細(或透過信件寄出)
					// 做交易處理包起來，一但有一項失敗就 rollback
					// 最後才可以把已購買的 tkt_no 從購物車清除(因為 Redis 是暫存，如果先清除購物車就會無法 rollback)
					// 都成功後寄出信件通知
					
					cartItemSrv.updateTktsoldAmount(sessionId, tkt_no);
//					cartItemSrv.updateTktoriAmount(sessionId, tkt_no);
					cartItemSrv.delItem(sessionId, tkt_no); // 透過已購買的 tkt_no 清除購物車 tkt_no
				}
			}
		}
		
		// 結帳成功轉交至會員訂單管理頁面
		RequestDispatcher rd = req.getRequestDispatcher("/frontend/cart/testOrderList.jsp");
		rd.forward(req, res);

	}

}
