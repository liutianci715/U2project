package com.forge.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.forge.bean.Forge_Users;
import com.forge.service.UserService;
import com.forge.service.impl.UserServiceImpl;


@WebServlet(value="/UsersServlet")
public class UsersServlet extends HttpServlet {

	Logger logger = Logger.getLogger(UsersServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			this.doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("loginName");
		String password = req.getParameter("password");
		
		
		UserService service = new UserServiceImpl();
		
		
		Forge_Users user = null;
		
		
		user = service.login(name, password);
		
		
	//	System.out.println(name);
	//	System.out.println(password);
		if(user==null){
			logger.info("登陆失败！");
		}else if(user!=null){
			//Cookie con = new Cookie("userName",name);
			//Cookie cop = new Cookie("pwd",password);
			logger.info("登陆成功！");
		}
			
	}

	
}
