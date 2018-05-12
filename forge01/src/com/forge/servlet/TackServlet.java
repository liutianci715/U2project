package com.forge.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forge.bean.Forge_Users;
import com.forge.service.Forge_Users_TracksService;
import com.forge.service.impl.Forge_Users_TracksServiceImpl;

@WebServlet("/TackServlet")
public class TackServlet extends HttpServlet {

	Forge_Users_TracksService tservices = new  Forge_Users_TracksServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String method = req.getParameter("method");
		switch(method){
			case "findTime":
				queryTime(req,resp);
				break;
		}
	
	}

	private void queryTime(HttpServletRequest req, HttpServletResponse resp) {

		String pId = req.getParameter("time");
		Forge_Users user =  (Forge_Users) req.getSession().getAttribute("user");
		int uId = user.getUserId();
		String time = tservices.findTime(uId,pId);
		// 获取输出流对象
		PrintWriter writer;
		try {
			writer = resp.getWriter();
			writer.print(time); // 返回数据给前台
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
