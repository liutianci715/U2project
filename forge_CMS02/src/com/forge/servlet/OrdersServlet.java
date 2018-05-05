package com.forge.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forge.bean.Forge_Order;
import com.forge.bean.Forge_Users;
import com.forge.service.OrderService;
import com.forge.service.impl.OrderServiceImpl;

@WebServlet("/OrdersServlet")
public class OrdersServlet extends HttpServlet {

	OrderService service = new OrderServiceImpl();
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
		System.out.println(method);
		switch(method){
			case "update":
				updateOrder(req,resp);  //修改用户
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
		System.out.println("================OrdersServlet进入了deleteById======================");
		String id = req.getParameter("id");
		System.out.println("delete id=================>"+id);
		service.delete(id);
		findAlls(req,resp);
	}

	private void findById(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("================findById进入了findById======================");
		Forge_Order order = null;
		String id = req.getParameter("id");
		System.out.println("id=======================>"+id);
		order = service.findById(id);
		System.out.println(order.toString());
		req.setAttribute("order", order);
		
			try {
				req.getRequestDispatcher("production/Order_Info_table.jsp").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
	}

	private void findAlls(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("================进入了Order findAll======================");
		List<Forge_Order> orders = new ArrayList();
		orders = service.findAll();
		req.getSession().setAttribute("orderList", orders);
		try {
			resp.sendRedirect("production/tables_dynamic(3).jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateOrder(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("================进入了updateOrder======================");
		int id = Integer.valueOf(req.getParameter("id")) ;
		String userAddress = req.getParameter("userAddress");
		String createTime = req.getParameter("createTime");
		String cost = req.getParameter("cost");
		String status = req.getParameter("status");
		String type = req.getParameter("type");
		String serialNumber = req.getParameter("serialNumber");
		Forge_Order order = new Forge_Order(id,userAddress,createTime,cost,status,type,serialNumber);
		service.update(order);
		
		findAlls(req,resp);
	}

}
