package com.forge.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forge.bean.Forge_Users;
import com.forge.service.UserService;
import com.forge.service.impl.UserServiceImpl;
import com.forge.util.PageInfo;
import com.google.gson.Gson;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("进入了TestServlet");
		req.setCharacterEncoding("utf-8");
		UserService service = new UserServiceImpl();
		PageInfo<Forge_Users> users = service.findAlls(Integer.valueOf(req.getParameter("pageNum")), 3);
		users.setTotal(service.getTotalCount());
		System.out.println("pageNum=====>" + req.getParameter("pageNum"));
		users.setPageNum(Integer.valueOf(req.getParameter("pageNum")));
		System.out.println("总记录数===》" + users.getTotal());
		Gson gson = new Gson();
		String json = gson.toJson(users);
		System.out.println(json);
		//获取输出流对象
		PrintWriter writer = resp.getWriter();
		writer.print(json);  //返回数据给前台
		writer.close();
		
	}

}
