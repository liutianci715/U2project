package com.forge.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.RepaintManager;

import com.forge.bean.Forge_Product;
import com.forge.bean.Forge_Product_Category;
import com.forge.bean.Forge_Users;
import com.forge.bean.Forge_Users_Tracks;
import com.forge.service.Forge_Users_TracksService;
import com.forge.service.ProductService;
import com.forge.service.Product_CategoryService;
import com.forge.service.impl.Forge_Users_TracksServiceImpl;
import com.forge.service.impl.ProductServiceImpl;
import com.forge.service.impl.Product_CategoryServiceImpl;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {

	Product_CategoryService service = new	Product_CategoryServiceImpl();
	//浏览记录相关实例
	Forge_Users_TracksService tservices = new  Forge_Users_TracksServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		String type = req.getParameter("method");
		switch (type) {
		case "findAll":
			findType1(req, resp, type); //��ѯһ���˵�
			break;
		case "findByt3":  //根据三级菜单的id获取下级商品
			findByt3(req, resp);  //��ѯ�����˵�
			break;
		case "page":  //根据三级菜单的id获取下级商品
			pageInfo(req, resp);  //��ѯ�����˵�
			break;
		case "track":   //查询商品浏览记录
			queryTrack(req, resp);  
			break;
		case "books":
			findBooks(req,resp);
			break;
		}
		
	}
	
	
	
	/**
	 * 模糊查询
	 */
	private void findBooks(HttpServletRequest req, HttpServletResponse resp) {
		//获取搜索框输入的内容
        String name=req.getParameter("name");
        /*try {
			name=new String(name.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}*/
        //向server层调用相应的业务
        ProductService booksServer=new ProductServiceImpl();
        List<String> res=booksServer.findBooksAjax(name);
        String str="";
        for (int i = 0; i < res.size(); i++) {
        	if(i>0){
                str+=","+res.get(i);
            }else{
                str+=res.get(i);
            }
		}
        System.out.println("333"+str);
        //返回结果
        try {
			resp.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	
	//查询商品浏览记录
	private void queryTrack(HttpServletRequest req, HttpServletResponse resp) {
		//获取用户ID
		String userId = req.getParameter("userId");
		List<Forge_Product> products = tservices.findAll(userId);
		req.getSession().setAttribute("userTrack", products);
		try {
			resp.sendRedirect("my-track.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void pageInfo(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("========================进入了pageInfo=================");

		String id = req.getParameter("id");  //获取商品的id
//		System.out.println("===============id:"+id);
//		List<Forge_Product> products = (List<Forge_Product>) req.getSession().getAttribute("products");
//		Forge_Product product = null;
//		for (int i = 0; i < products.size(); i++) {
//			if(id == products.get(i).getId()){
//				product = products.get(i);
//				System.out.println("========================="+products.get(i).getFileName());
//			}	
//		}
//		req.getSession().setAttribute("product", product);
//		try {
		req.getSession().setAttribute("pageid", id);
		//将商品添加到用户的浏览记录中
		addTrack(id,req,resp);
		
			try {
				//进入商品展示页面
				resp.sendRedirect("page.jsp"); 
			} catch (IOException e) {
				e.printStackTrace();
			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}

	//添加用户的浏览记录 
	private void addTrack(String id, HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("========================进入了addTrack=================");

		//获取用户  如果用户登陆了加入浏览记录  如果用户没登录不记录(参考 淘宝网)
		Forge_Users user = (Forge_Users) req.getSession().getAttribute("user");
		if(user!= null){
			//从数据库取出用户的浏览记录
			int userId =user.getUserId();
		    List<Forge_Product> tracks = tservices.findAll(userId);
		    System.out.println(tracks);
		    int count =0;
		    if(!tracks.isEmpty()){
		    	for(int i = 0;i<tracks.size();i++){                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
					if(id.equals(tracks.get(i).getId())||id==tracks.get(i).getId()){
						System.out.println("88888888888888888888浏览记录id相同88888888888888");
						//String productId = tracks.get(i).getId();
						count=count+1;
						
						
					}
					
		    	}
					if(count>0){
						tservices.update(userId,id);
					}else{
						//添加浏览记录 	
						tservices.addTrack(user.getUserId(),id);
						
					}
					System.out.println("bbbbbbbbbbbbbbbbbbbbbb");
				
		    }else{
		    	tservices.addTrack(user.getUserId(),id);
		    }
			
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		}
	}

	private void findByt3(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("========================进入了findByt3=================");
			String id = req.getParameter("id");
			List<Forge_Product> products = service.findByt3(id);
			for(int i = 0;i<products.size();i++){
				System.out.println(products.get(i).getFileName());
			}
		req.getSession().setAttribute("products", products);
			req.setAttribute("id", id);
		
			try {
				req.getRequestDispatcher("my-all.jsp").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
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
