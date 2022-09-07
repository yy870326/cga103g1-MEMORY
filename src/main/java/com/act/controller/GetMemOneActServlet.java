package com.act.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.act.model.ActService;
import com.act.model.ActVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.util.LocalDateTimeDeserializer;
import com.util.LocalDateTimeSerializer;

@WebServlet("/getMemOneAct")
public class GetMemOneActServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json, text/html; charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        System.out.println("MemPage Fetch Request -> GetMemOneActServlet"); 
        
        HttpSession actSession = req.getSession();       
        Integer memNo = (Integer) actSession.getAttribute("memNo1");
		ActService actService = new ActService();
		
		BufferedReader br = req.getReader();
		GsonBuilder gsonBuilder = new GsonBuilder();  
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());      
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
		Gson gson = gsonBuilder.setPrettyPrinting().create();
		ActVO actfromFront = gson.fromJson(br, ActVO.class);
		ActVO actVO = 
				actService.getAll()
				.stream()
				.filter(act -> act.getAct_no() == actfromFront.getAct_no())
				.filter(act -> act.getMen_no() == memNo)
				.findFirst().get();
		String json = gson.toJson(actVO);
	    PrintWriter pw = res.getWriter();
	    pw.print(json);
  
	}

}
