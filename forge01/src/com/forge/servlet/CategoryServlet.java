package com.forge.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forge.bean.Forge_Product_Category;
import com.forge.service.Product_CategoryService;
import com.forge.service.impl.Product_CategoryServiceImpl;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {

	Product_CategoryService service = new	Product_CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String type = req.getParameter("method");
		List<Forge_Product_Category> cate1 = service.findByType(type);
		req.getSession().setAttribute("cate1", cate1);
		resp.sendRedirect("index.jsp");
		PrintWriter writer;
		try {
			writer = resp.getWriter();
			writer.print("true");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
