package com.mem.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.apache.naming.java.javaURLContextFactory;

import com.mem.model.*;
import com.mem.model.MemDAO;
import com.mem.model.MemVO;

@MultipartConfig
public class MemServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("mem_no","請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer mem_no = null;
				try {
					mem_no = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("mem_no","員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemService MemSvc = new MemService();
				MemVO memVO = MemSvc.getOneMem(mem_no);
				if (memVO == null) {
					errorMsgs.put("mem_no","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
				String url = "/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer mem_no = Integer.valueOf(req.getParameter("mem_no"));
				
				/***************************2.開始查詢資料****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memVO", memVO);         // 資料庫取出的empVO物件,存入req
				String url = "/mem/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer mem_no = Integer.valueOf(req.getParameter("mem_no").trim());
				
				 String  mem_acc = req.getParameter("mem_acc");           
				 String  mem_pwd  = req.getParameter("mem_pwd");         
				 Integer acc_status  =Integer.valueOf(req.getParameter("acc_status"));
				 String  mem_name    =req.getParameter("mem_name"); 
				 String  mem_gender  =req.getParameter("mem_gender"); 
				 String  mem_email   =req.getParameter("mem_email"); 
				 String  mem_mobile  =req.getParameter("mem_mobile");
				 String  mem_city    =req.getParameter("mem_city");
				 String  mem_dist=req.getParameter("mem_dist"); 
				 String mem_addr =req.getParameter("mem_addr");
				 java.sql.Date    mem_reg_date=null;
				 try {
					 mem_reg_date = java.sql.Date.valueOf(req.getParameter("mem_reg_date").trim());
					} catch (IllegalArgumentException e) {
						mem_reg_date=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
				 
				 //存入圖片
//				 Part part = req.getPart("mem_pic");
//			      InputStream in = part.getInputStream();
//			      ByteArrayOutputStream out = new ByteArrayOutputStream();
//			      byte[] buff = new byte[1024];
//			      int len;
//			    //若in.read(buff)能讀到資料，把資料長度設給len，讓out寫出資料(從0寫到len的長度)
//			      while((len = in.read(buff)) != -1) {
//			        out.write(buff, 0, len);
//			      }
//			      //out寫出完成後，把資料轉存為byte[]
//			      byte[] mem_pic = out.toByteArray();
		byte[] mem_pic =req.getPart("mem_pic").getInputStream().readAllBytes();
			     Integer mem_report_count = Integer.valueOf(req.getParameter("mem_report_count"));
			     String mem_card = req.getParameter("mem_card");
			      
			      
				  
				
				 
				 try {
					 mem_reg_date = java.sql.Date.valueOf(req.getParameter("mem_reg_date").trim());
					} catch (IllegalArgumentException e) {
						mem_reg_date=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}

				  MemVO memVO = new MemVO();
				  	 memVO.setMem_no(mem_no);
					 memVO.setMem_acc(mem_acc);
					 memVO.setMem_pwd(mem_pwd);
					 memVO.setAcc_status(acc_status);
					 memVO.setMem_name(mem_name);
					 memVO.setMem_gender(mem_gender);
					 memVO.setMem_email(mem_email);
					 memVO.setMem_mobile(mem_mobile);
					 memVO.setMem_reg_date(mem_reg_date);
					 memVO.setMem_pic(mem_pic);
					 memVO.setMem_report_count(mem_report_count);
					 memVO.setMem_card(mem_card);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/update_mem_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(mem_acc, mem_pwd, acc_status, mem_name, mem_gender, mem_email, mem_mobile, mem_city, mem_dist, mem_addr, mem_reg_date, mem_pic, mem_report_count, mem_card,mem_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		 String  mem_acc = req.getParameter("mem_acc");           
		 String  mem_pwd  = req.getParameter("mem_pwd");         
		 Integer acc_status  =Integer.valueOf(req.getParameter("acc_status"));
		 String  mem_name    =req.getParameter("mem_name"); 
		 String  mem_gender  =req.getParameter("mem_gender"); 
		 String  mem_email   =req.getParameter("mem_email"); 
		 String  mem_mobile  =req.getParameter("mem_mobile");
		 String  mem_city    =req.getParameter("mem_city");
		 String  mem_dist=req.getParameter("mem_dist"); 
		 String mem_addr =req.getParameter("mem_addr");
		 java.sql.Date    mem_reg_date=null;
		 try {
			 mem_reg_date = java.sql.Date.valueOf(req.getParameter("mem_reg_date").trim());
			} catch (IllegalArgumentException e) {
				mem_reg_date=new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
		 
		 //存入圖片
//		 Part part = req.getPart("mem_pic");
//	      InputStream in = part.getInputStream();
//	      ByteArrayOutputStream out = new ByteArrayOutputStream();
//	      byte[] buff = new byte[1024];
//	      int len;
//	    //若in.read(buff)能讀到資料，把資料長度設給len，讓out寫出資料(從0寫到len的長度)
//	      while((len = in.read(buff)) != -1) {
//	        out.write(buff, 0, len);
//	      }
//	      //out寫出完成後，把資料轉存為byte[]
//	      byte[] mem_pic = out.toByteArray();
byte[] mem_pic =req.getPart("mem_pic").getInputStream().readAllBytes();
	     Integer mem_report_count = Integer.valueOf(req.getParameter("mem_report_count"));
	     String mem_card = req.getParameter("mem_card");
	      
	      
		  
		
		 
		 try {
			 mem_reg_date = java.sql.Date.valueOf(req.getParameter("mem_reg_date").trim());
			} catch (IllegalArgumentException e) {
				mem_reg_date=new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
		   
		  MemVO memVO = new MemVO();
		 memVO.setMem_acc(mem_acc);
		 memVO.setMem_pwd(mem_pwd);
		 memVO.setAcc_status(acc_status);
		 memVO.setMem_name(mem_name);
		 memVO.setMem_gender(mem_gender);
		 memVO.setMem_email(mem_email);
		 memVO.setMem_mobile(mem_mobile);
		 memVO.setMem_reg_date(mem_reg_date);
		 memVO.setMem_pic(mem_pic);
		 memVO.setMem_report_count(mem_report_count);
		 memVO.setMem_card(mem_card);
		 
			


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/mem/addMem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.addMem(mem_acc, mem_pwd, acc_status, mem_name, mem_gender, mem_email, mem_mobile, mem_city, mem_dist, mem_addr, mem_reg_date, mem_pic, mem_report_count, mem_card);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer mem_no = Integer.valueOf(req.getParameter("mem_no"));
				
				/***************************2.開始刪除資料***************************************/
				MemService memSvc = new MemService();
				memSvc.deleteMem(mem_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	
		
	}
	
}