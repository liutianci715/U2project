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
		switch (type) {
		case "findAll":
			findType1(req, resp, type); //��ѯһ���˵�
			break;
		case "2":
			findType2(req, resp, type);  //��ѯ�����˵�
			break;
		default:
			break;
		}
		
	}

	private void findType2(HttpServletRequest req, HttpServletResponse resp,
			String type) {
		//String parentId = req.getParameter("parentId");
		List<Forge_Product_Category> list = service.findAll();
		for(int i = 0; i<list.size();i++){
			int parentId = list.get(i).getId();
			List<Forge_Product_Category> list2 = service.findType2(parentId);
			switch(parentId){
			case 548 :
				req.getSession().setAttribute("type21", list2);
			break;
			case 628 :
				req.getSession().setAttribute("type22", list2);
			break;
			case 660 :
				req.getSession().setAttribute("type23", list2);
			break;
			case 670 :
				req.getSession().setAttribute("type24", list2);
			break;
			case 676 :
				req.getSession().setAttribute("type25", list2);
			break;
			case 681 :
				req.getSession().setAttribute("type26", list2);
			break;
		}
		}
		
		
		
		PrintWriter writer;
		try {
			writer = resp.getWriter();
			writer.print("true");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void findType1(HttpServletRequest req, HttpServletResponse resp,
			String type) throws IOException {
		List<Forge_Product_Category> cate1 = service.findAll();
		req.getSession().setAttribute("cate1", cate1);
		findType2(req,resp,"2");
		findType3(req,resp);
		//resp.sendRedirect("index.jsp");
		PrintWriter writer;
		try {
			writer = resp.getWriter();
			writer.print("true");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void findType3(HttpServletRequest req, HttpServletResponse resp){
		List<Forge_Product_Category> list3 = service.findType3();
		req.getSession().setAttribute("type3", list3);
	}

}
