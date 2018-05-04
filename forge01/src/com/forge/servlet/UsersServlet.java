package com.forge.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
import com.forge.util.Md5Encrypt;


@WebServlet(value="/UsersServlet")
public class UsersServlet extends HttpServlet {

	Logger logger = Logger.getLogger(UsersServlet.class);
	UserService service = new UserServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String method = req.getParameter("method");
		System.out.println(method);
		switch(method){
			case "login":
				login(req,resp);
				break;
			case "findAll" :
				findAlls(req,resp);
				break;
			case "add" :
				addUser(req,resp);
				break;
			case "name": //判断用户名是否已被占用
				validateName(req,resp);
				break;
			
		}
		
			
	}

	private void validateName(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("================进入了validateName======================");
		String name = req.getParameter("LoginName");
		System.out.println("validateName==============="+name);
		Forge_Users user =null;
		user=service.findByName(name);
		
		boolean flag = false;
		if(user!=null){
			flag = true;  //证明数据库中已存在
		}
		try {
			PrintWriter writer = resp.getWriter();
			writer.print(flag);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addUser(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("================进入了addUser======================");
	//	String userName = req.getParameter("userName");
		String LoginName = req.getParameter("LoginName");
		System.out.println(LoginName);
		String address = req.getParameter("address");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		Forge_Users user = new Forge_Users(LoginName,pwd,address,phone,email);
		System.out.println("=============="+user.getLoginName());
		service.add(user);
		System.out.println("addUser===========>"+user.toString());
	}

	private void findAlls(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("================进入了findAll======================");
		List<Forge_Users> users = new ArrayList();
		users = service.findAll();
		req.getSession().setAttribute("userList", users);
		try {
			resp.sendRedirect("production/tables_dynamic(1).jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void login(HttpServletRequest req,HttpServletResponse resp) {
		String name = req.getParameter("loginName");
		String password = req.getParameter("password");
	//	String uppwd = Md5Encrypt.MD5(password);  //密码加密
	
		
		
		
		
		Forge_Users user = null;
		
		
		user = service.login(name, password);
		String remeber = req.getParameter("remember");
		System.out.println("rember=================>"+remeber);
		if(remeber!=null&&remeber.equals("on")){
			Cookie nameCokie = new Cookie("nameCokie", remeber);
			resp.addCookie(nameCokie);
		}		
	//	System.out.println(name);
	//	System.out.println(password);
		if(user==null){
			logger.info("登陆失败！");
			try {
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(user!=null){
			//Cookie con = new Cookie("userName",name);
			//Cookie cop = new Cookie("pwd",password);
			logger.info("登陆成功！");
		

			try {
				resp.sendRedirect("index.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
}
