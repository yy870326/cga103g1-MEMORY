package com.emp.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;

public class EmpServlet {
	private static final long serialVersionUID = 1L;
	       
	    public EmpServlet() {
	        super();
	    }
	
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req, res);
		}
		
		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
			
			if("getOneEmp".equals(action)) {
				Integer emp_no = new Integer(req.getParameter("emp_no"));
				EmpService ser = new EmpService();
				EmpVO empVO = ser.getOneEmp(emp_no);
				
				req.setAttribute("empVO", empVO);
				
				String url = "backend/emp/personal.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
			}
			if("logout".equals(action)) {
				HttpSession session = req.getSession();
				session.invalidate();
				//導回登入頁面-------------
				String url = "/backend/login/login.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
			
			if("login".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
					/*************************** 1.接收請求參數 **********************/
				String emp_acc = req.getParameter("emp_acc");
				String emp_pwd = req.getParameter("emp_pwd");
				/*************************** 2.開始查詢資料 *****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO1 = empSvc.login(emp_acc, emp_pwd);
				if(empVO1==null) {
					errorMsgs.add("查無資料或輸入錯誤");
				}else if(empVO1.getEmp_state()==false) {
					errorMsgs.add("您已離職無法登入。");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/後台登入/後台登入.jsp");	//查無資料返回登入
					failureView.forward(req, res);
					return;// 程式中斷
				}else {
					HttpSession session = req.getSession();
				    session.setAttribute("empVO1", empVO1);
				    try {
				    	 String location = (String) session.getAttribute("location2");
				         if (location != null) {
				           session.removeAttribute("location2");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
				           res.sendRedirect(location);            
				           return;}
				    }catch (Exception ignored) { }
				      res.sendRedirect(req.getContextPath()+"/backend/後台主頁/後台主頁.jsp");
				}
		}
			
			if("updateOne".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				Integer emp_no = new Integer(req.getParameter("emp_no"));
				
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_no);
				
				req.setAttribute("empVO", empVO);
				
				String url = "backend/emp/updateEmp.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
			}
			
			if("update".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
			try {	
				Integer emp_no = new Integer(req.getParameter("emp_no"));
				String emp_acc = req.getParameter("emp_acc").trim();
				if(emp_acc == null || emp_acc.length() < 5) {
					errorMsgs.add("帳號: 請勿空白、小於字數6");
				}
				String emp_pwd = req.getParameter("emp_pwd").trim();
				if(emp_pwd == null || emp_pwd.length() < 5) {
					errorMsgs.add("密碼: 請勿空白、小於字數6");
				}
				String emp_name = req.getParameter("emp_name").trim();
				if(emp_name == null || emp_name.length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				}
				String emp_email = req.getParameter("emp_email").trim();
				String e_emailReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
				if (emp_email == null || emp_email.trim().length() == 0) {
					errorMsgs.add("電子信箱: 請勿空白");
				} else if(!emp_email.trim().matches(e_emailReg)) {
					errorMsgs.add("電子信箱: 請依照電子郵件格式輸入");
	            }
				Boolean emp_state = new Boolean(req.getParameter("emp_state"));
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/emp/listAllEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.updateEmp(emp_acc, emp_pwd, emp_name, emp_email, emp_state, emp_no);
				
				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
	
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
			}
			
			if("insert".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				try {
					String emp_acc = req.getParameter("emp_acc").trim();
					if(emp_acc == null || emp_acc.length() < 5) {
						errorMsgs.add("帳號: 請勿空白、小於字數6");
					}
					String emp_pwd = req.getParameter("emp_pwd").trim();
					if(emp_pwd == null || emp_pwd.length() < 5) {
						errorMsgs.add("密碼: 請勿空白、小於字數6");
					}
					String emp_name = req.getParameter("emp_name").trim();
					String e_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,5}$";
					if (emp_name == null || emp_name.trim().length() == 0) {
						errorMsgs.add("員工姓名: 請勿空白");
					} else if(!emp_name.trim().matches(e_nameReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("員工姓名: 只能是中、英文字母, 且長度必需在2到5之間");
		            }
					String emp_email = req.getParameter("emp_email").trim();
					String e_emailReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
					if (emp_email == null || emp_email.trim().length() == 0) {
						errorMsgs.add("電子信箱: 請勿空白");
					} else if(!emp_email.trim().matches(e_emailReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("電子信箱: 請依照電子郵件格式輸入");
		            }
					Boolean emp_state = new Boolean(req.getParameter("emp_state"));
					
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/emp/listAllEmp.jsp");
						failureView.forward(req, res);
						return;
					}
					/***************************2.開始查詢資料*****************************************/
					EmpService empSvc = new EmpService();
					EmpVO empVO = empSvc.addEmp(emp_acc, emp_pwd, emp_name, emp_email, emp_state);
					
					req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
					String url = "/back_end/emp/listAllEmp.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);
				}
			 catch (Exception e) {
				errorMsgs.add("新增資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
}
}

