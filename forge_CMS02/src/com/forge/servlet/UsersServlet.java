package com.forge.servlet;

import java.io.IOException;
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


@WebServlet(value="/UsersServlet")
public class UsersServlet extends HttpServlet {

	Logger logger = Logger.getLogger(UsersServlet.class);
	UserService service = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			this.doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String method = req.getParameter("method");
		System.out.println(method);
		switch(method){
			case "login"://用户登录
				login(req, resp);
				break;
			case "update":
				updateUser(req,resp);  //修改用户
				break;
			case "findAll" :
				findAlls(req,resp);
				break;
			case "findById" :
				findById(req,resp);
				break;
			case "delete":
				deleteById(req,resp);
				break;
			
		}
		
		
			
	}

	private void deleteById(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("================进入了deleteById======================");
		String id = req.getParameter("id");
		System.out.println("delete id=================>"+id);
		service.delete(id);
		try {
			resp.sendRedirect("production/tables_dynamic(1).jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void findById(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("================进入了findById======================");
		Forge_Users user = null;
		String id = req.getParameter("id");
		System.out.println("id=======================>"+id);
		user = service.findById(id);
		System.out.println(user.toString());
		req.setAttribute("user", user);
		
			try {
				req.getRequestDispatcher("production/User_Info_table.jsp").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
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
	
	private void updateUser(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("================进入了updateUser======================");
		int id = Integer.valueOf(req.getParameter("id")) ;
		String loginName = req.getParameter("loginName");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		Forge_Users user = new Forge_Users(id,loginName,phone,email,address);
		service.update(user);
		try {
			resp.sendRedirect("production/tables_dynamic(1).jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 管理员登录
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void login(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String name = req.getParameter("loginName");
		String password = req.getParameter("password");
		Forge_Users user = null;
		user = service.login(name, password);
		System.out.println(user);
		System.out.println(name);
		System.out.println(password);
		if(user==null){
			logger.info("登陆失败！");
			resp.sendRedirect("production/login.jsp");
		}else if(user!=null){
			System.out.println(user.getLoginName());
			System.out.println(user.getPassword());
			if(user.getLoginName().equals("111")&& user.getPassword().equals("123")){
				logger.info("登陆成功！");
				req.getSession().setAttribute("admin", user);
				resp.sendRedirect("production/index.jsp");
			}else{
				resp.sendRedirect("production/login.jsp");
			}
			
		}
	}

	
}
