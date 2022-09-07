package com.act_participant.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.act.model.ActService;
import com.act_participant.model.ActParticipantService;
import com.google.gson.Gson;
import com.mem.model.MemService;

@WebServlet("/joinAct")
public class JoinActServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json, text/html; charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
		System.out.println("fetch Request -> JoinActServlet");
		HttpSession session = req.getSession();
		Integer actNo =  (Integer) session.getAttribute("actNo");
		String memLoginAcc = (String) session.getAttribute("memNo1Acc");
		MemService memService = new MemService();
		String memAcc = memService.getOneMem(1).getMem_email();
		Integer memNo = (Integer) session.getAttribute("memNo2");
		Gson gson = new Gson();
		ActService actService = new ActService();
		if ((memAcc).equals(memLoginAcc)) {
			ActParticipantService actParticipantService = new ActParticipantService();
		    LocalDateTime currentTime = LocalDateTime.now();
			actParticipantService.addActParticipant(actNo, memNo, currentTime);
			Integer actMaxCount =
					actService.getAll().stream().filter(act -> act.getAct_no() == actNo).findFirst().get().getAct_max_count();
			Integer actCurrentCount =
					actService.getAll().stream().filter(act -> act.getAct_no() == actNo).findFirst().get().getAct_current_count();
			String resInfo ="";
			if(actMaxCount > actCurrentCount) {				
				actService.updateActPeopleAmount(actNo, memNo);
				resInfo = gson.toJson("加入成功");
			}else {
				resInfo =  gson.toJson("已超過活動最大限制人數，無法加入");
			}
			res.getWriter().write(resInfo);
		}else {
			String resInfo = gson.toJson("加入失敗");
			res.getWriter().write(resInfo);	
		}
	}

}
