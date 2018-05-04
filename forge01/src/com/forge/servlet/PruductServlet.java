package com.forge.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forge.bean.Forge_Product;
import com.forge.service.ProductService;
import com.forge.service.impl.ProductServiceImpl;

@WebServlet("/buyServlet")
public class PruductServlet extends HttpServlet {
	
	//实例化service层对象
	ProductService service = new ProductServiceImpl();
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
		if (method == null) {
			List<Forge_Product> products = service.findAll();
			req.setAttribute("products", products);
			// 转发
			req.getRequestDispatcher("product.jsp").forward(req, resp);

		} else {
			switch (method) {
			case "add":
				addCart(req, resp);
				break;
			case "findCart":
				findCart(req, resp);
				break;
			case "del":
				delCart(req, resp);
				break;
			case "clear":
				clearCart(req, resp);
				break;
			}
		}
	}

	private void clearCart(HttpServletRequest req, HttpServletResponse resp) {
		
	}

	private void delCart(HttpServletRequest req, HttpServletResponse resp) {
		
	}

	private void findCart(HttpServletRequest req, HttpServletResponse resp) {
		
	}

	private void addCart(HttpServletRequest req, HttpServletResponse resp) {
		
	}
	
}
