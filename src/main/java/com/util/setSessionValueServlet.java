package com.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

@WebServlet("/setSessionValue")
public class setSessionValueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json, text/html; charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Integer memNo1 = 1;
        Integer memNo2 = 2;
        Integer memNo3 = 3;
        session.setAttribute("memNo1", memNo1);
        session.setAttribute("memNo2", memNo2);
        session.setAttribute("memNo3", memNo3);
        session.setAttribute("memNo1Acc", "Q123434565@gmail.com");
        session.setAttribute("memNo2Acc", "A138775451@gmail.com");
        session.setAttribute("memNo3Acc", "D129242193@gmail.com");
        String memNo1Acc = (String) session.getAttribute("memNo1Acc");
        String memNo2Acc = (String) session.getAttribute("memNo2Acc");
        String memNo3Acc = (String) session.getAttribute("memNo3Acc");
        List<String> memNoList = new ArrayList<String>();
        memNoList.add(memNo1Acc);
        memNoList.add(memNo2Acc);
        memNoList.add(memNo3Acc);
        Gson gson = new Gson();
        String actNoJson = gson.toJson(memNoList);
        res.getWriter().write(actNoJson);

	}
}
