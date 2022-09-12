package com.tkt_order2.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.CartItemService;
import com.cart.model.CartItemVO;
import com.cart.model.CheckOutService;
import com.tkt_item2.model.TktItem2Service;
import com.tkt_item2.model.TktItem2VO;
import com.tkt_order2.model.TktOrderMailService;

@WebServlet(name = "addTktOrderServlet", urlPatterns = { "/tktOrder/addTktOrd.do" })
public class addTktOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain; charset = UTF-8");
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		// ------------------ getParameter ------------------
		// 接收結帳清單所有的 tkt_no
		
		String sessionId = null;
//		Integer tkt_no = 0;
		
		// mem_no
		Integer mem_no = Integer.valueOf(req.getParameter("mem_no").trim());
		System.out.println(mem_no);
		// mem_coup_no
		Integer mem_coup_no = Integer.valueOf(req.getParameter("mem_coup_no").trim());
		System.out.println(mem_coup_no);
		// original_price
		Integer original_price = Integer.valueOf(req.getParameter("original_price").trim());
		System.out.println(original_price);
		// ttl_price
		Integer ttl_price = Integer.valueOf(req.getParameter("ttl_price").trim());
		System.out.println(ttl_price);
		
		// 訂購者資料
		// tkt_order_mem_name
		String tkt_order_mem_name = req.getParameter("tkt_order_mem_name").trim();
		// tkt_order_mem_email
		String tkt_order_mem_email = req.getParameter("tkt_order_mem_email").trim();
//		TktItem2Service tktItem2Srv = new TktItem2Service();
//		List<TktItem2VO> list = tktItem2Srv.getAll();
//		
//		Enumeration<String> en = req.getParameterNames();
		
//		while (en.hasMoreElements()) {
////			String name = (String) en.nextElement();
////			String values[] = req.getParameterValues(name); 
//			String tkt_nos = (String) en.nextElement();
//			// tkt_no
//			String values[] = req.getParameterValues("tkt_no"); 
//			// mem_no
////			Integer mem_no = Integer.valueOf(req.getParameter("mem_no").trim());
////			// mem_coup_no
////			Integer mem_coup_no = Integer.valueOf(req.getParameter("mem_coup_no").trim());
////			// original_price
////			Integer original_price = Integer.valueOf(req.getParameter("original_price").trim());
////			// ttl_price
////			Integer ttl_price = Integer.valueOf(req.getParameter("ttl_price").trim());
//			
//			
//			
//			
//			if (values != null) { 
//				for (int i = 0; i < values.length; i++) {
//					sessionId = (String) req.getSession().getAttribute("sessionId"); // 取得 session 的 ID
//					
//					
////					System.out.println("-----------" +sessionId);
////					CartItemService cartItemSrv = new CartItemService();
//					CheckOutService checkOutSrv = new CheckOutService();
////					Check2Srv check2 = new Check2Srv();
//					tkt_no = Integer.valueOf(values[i]); // 將一一取到的 tkt_no 轉為數字
//					System.out.println("-----------" +tkt_no);
//					
//					// 購物車結帳要做的事
//					// 更新庫存、銷售量
//					// 更改會員優惠券使用狀態
//					// 把資料新增到訂單、訂單明細 table
//					// 把 QR Code 圖片存進訂單明細(或透過信件寄出)
//					// 做交易處理包起來，一但有一項失敗就 rollback
//					// 最後才可以把已購買的 tkt_no 從購物車清除(因為 Redis 是暫存，如果先清除購物車就會無法 rollback)
//					// 都成功後寄出信件通知
//					
////					cartItemSrv.updateTktsoldAmount(sessionId, tkt_no);
////					cartItemSrv.updateTktoriAmount(sessionId, tkt_no);
////					check2.checkOut(mem_no, mem_coup_no, original_price, ttl_price, list, sessionId);
//					checkOutSrv.checkOut(mem_no, mem_coup_no, original_price, ttl_price, list, sessionId);
////					cartItemSrv.delItem(sessionId, tkt_no); // 透過已購買的 tkt_no 清除購物車 tkt_no
//				}
//			}
//		}
		
		
		
		
		
		// ----------- 成立訂單 ----------- 
		HttpSession session = req.getSession();
		sessionId = (String) session.getAttribute("sessionId"); // 取得 session 的 ID
		CheckOutService checkOutSrv = new CheckOutService();
		
		TktItem2Service tktItem2Srv = new TktItem2Service();
		List<TktItem2VO> list = new ArrayList<TktItem2VO>();
		
		
		
		CartItemService cartItemSrv = new CartItemService();
		List<CartItemVO> cartItems = cartItemSrv.getCart(sessionId);
		
		
		for (int i = 0; i < cartItems.size(); i++) {
			CartItemVO cartItemVO = cartItems.get(i);
			TktItem2VO tktItem2VO = new TktItem2VO();
			
			Integer ori_price = cartItemVO.getCount() * cartItemVO.getPrice();
			
			tktItem2VO.setTkt_no(cartItemVO.getTkt_no());
			tktItem2VO.setAmount(cartItemVO.getCount());
			tktItem2VO.setTkt_ori_price(ori_price);
			
			list.add(tktItem2VO);
		}
		
		checkOutSrv.checkOut(mem_no, mem_coup_no, original_price, ttl_price, list, sessionId);
		
		// -------------- 生成 QR Code
		
		// 訂單成立發送 Email 通知
		
		String to = tkt_order_mem_email; // 要抓會員 email 或是在購買頁新增欄位傳參數過來
		String subject = "票券訂單成功";
		String ch_name = tkt_order_mem_name + " 用戶"; // 要抓會員 帳戶名
		String messageText = "Hello! " + ch_name + "，您已成功在美陌旅訂購票券，本次的消費金額為 NT$" + ttl_price + " 元，感謝您的購買";

		TktOrderMailService tktOrdMailSrv = new TktOrderMailService();
		tktOrdMailSrv.sendMail(to, subject, messageText);
		
		// 設置QRCode的存放目錄、檔名與圖片格式
		
		
		
		// --------------- 所有交易都成功，刪掉 redis 購物車資料
		
				// 取得購物車選購的選購商品
		List<CartItemVO> checkedList = (List<CartItemVO>)session.getAttribute("checkedList"); 
		for (CartItemVO cartItemVO : checkedList) {
			System.out.println(cartItemVO.getTkt_no());
			cartItemSrv.delItem(sessionId, cartItemVO.getTkt_no()); // 透過已購買的 tkt_no 清除購物車 tkt_no
		}
		
		// 結帳成功轉交至會員訂單管理頁面
		RequestDispatcher rd = req.getRequestDispatcher("/frontend/tkt_order/tktOrder.jsp");
		rd.forward(req, res);
	}

}
