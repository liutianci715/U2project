package com.forge.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.spy.memcached.MemcachedClient;

import com.forge.bean.Cart;
import com.forge.bean.CartItem;
import com.forge.bean.Forge_Cart;
import com.forge.bean.Forge_Product;
import com.forge.bean.Forge_Users;
import com.forge.service.Forge_CartService;
import com.forge.service.ProductService;
import com.forge.service.impl.Forge_CartServiceImpl;
import com.forge.service.impl.ProductServiceImpl;
import com.forge.util.MemcachedUtil;
import com.google.gson.Gson;


@WebServlet("/buyServlet")
public class PruductServlet extends HttpServlet {
	
	//ʵ��service�����
	ProductService service = new ProductServiceImpl();
	Forge_CartService fcService = new Forge_CartServiceImpl();
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
			// ת��
			req.getRequestDispatcher("my-all.jsp").forward(req, resp);

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
			case "toPay":
				toPay(req, resp);
				break;
			}
		}
	}

	private void toPay(HttpServletRequest req, HttpServletResponse resp) {
		
	}

	private void clearCart(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().removeAttribute("cart");
		try {
			resp.sendRedirect("my-car.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void delCart(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("方法进来的delCart");
		String id = req.getParameter("id");
		System.out.println("id================="+id);
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		System.out.println("方法进来的delCart:"+cart);
		service.del(id, cart);
		try {
			resp.sendRedirect("my-car.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void findCart(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("欢迎来到findCart方法");
		//先判断用户是否登录，如果没有登录进入购物车先登录，登录则进入查询页面
		Forge_Users user=(Forge_Users) req.getSession().getAttribute("user");
		if(null==user){//用户等于空
			System.out.println("++++++++++++findCart:user不为空=================");
			//用户没有登录查询cookie里购物车
			Cookie[] cookies = req.getCookies();
			Cookie cookie=null;
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("cart")){
					//找到的赋给cookie
					cookie=cookies[i];
				}
			}
			if(null==cookie){//cookie等于空
				System.out.println("进入了cookie等于空");
				try {
					resp.sendRedirect("my-car.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{//cookie不等于空
				System.out.println("进入了cookie不等于空");
				String json=cookie.getValue();
				Gson gson=new Gson();
				Cart cart=gson.fromJson(json, Cart.class);
				req.getSession().setAttribute("cart", cart);
				try {
					resp.sendRedirect("my-car.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
				/*req.setAttribute("cart", cart);
				try {
					req.getRequestDispatcher("my-car.jsp").forward(req, resp);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		}else{//用户不为空，用户已经登录  先从缓存中取购物车，如果缓存中没有，去数据库取
			System.out.println("++++++++++++findCart:user不为空=================");
			MemcachedClient client = MemcachedUtil.getInstance();
			Cart cart = (Cart) client.get("cart");
			
			if(cart!=null){//如果缓存中有购物车
				System.out.println("++++++++++++findCart:Memcachedcart不为空=================");
				req.getSession().setAttribute("cart", cart);
			}else{//如果缓存中没有购物车  去数据库取
				//从数据库中取出购物车
				System.out.println("++++++++++++findCart:Memcachedcart为空=================");
				 Cart userCart = getUserCart(user.getUserId());
				req.getSession().setAttribute("cart", cart);

			}
			try {
				resp.sendRedirect("my-car.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}
	
		//获取用户的购物车
		private Cart getUserCart(int userId) {
			System.out.println("==========进入了getUserCart==============");

			Cart cart = new Cart();
			List<Forge_Cart> item = fcService.findByUserId(userId);
			for(int i = 0;i<item.size();i++){
				//获取商品的id
				String productId = item.get(i).getProductId();
				//根据商品id获取商品
				Forge_Product product = service.findById(productId);
				//获取商品数量
				String num =item.get(i).getProductNum();
				//获取商品小计
				double price = item.get(i).getPrice();
				//创建购物项将上商品加入购物项中
				 CartItem cartItem = new CartItem();
				 cartItem.setNum(Integer.valueOf(num));
				 cartItem.setPrice(price);
				 cartItem.setProduct(product);
				
				cart.getMap().put(productId, cartItem);
			}
			return cart;
		}

	private void addCart(HttpServletRequest req, HttpServletResponse resp) {
		
		System.out.println("===========================进入了addCart=========================");
		String num = req.getParameter("num");  //获取商品数量
		System.out.println("============"+num);
		String productId = req.getParameter("id"); //获取商品id
		System.out.println("iddenfyu"+productId);
		//Cart cart = (Cart) req.getSession().getAttribute("cart");
		Gson gson = new Gson();
		
		Cart cart = null;
		
		//判断用户是否登录
		Forge_Users user = (Forge_Users) req.getSession().getAttribute("user");
		if(user==null){
			System.out.println("==========进入了user等于空==============");
			
			String json = null;
			Cookie cookie = null;
			//从cookie中查找购物车
			Cookie  [] cookies = req.getCookies();
			for(int i = 0;i<cookies.length;i++){
				if(cookies[i].getName().equals("cart")){
					cookie = cookies[i];
					json = cookie.getValue();
				}
			}
			//判断json是否为空进而判断Cookie中是否有购物车
			
			if(json==null){
				System.out.println("==========进入了json等于空==============");
				cart = new Cart();
				service.addCart(productId, cart,Integer.valueOf(num));
				System.out.println("qian"+cart.getPrice());
				json = gson.toJson(cart);
				System.out.println("*******************:"+json);
				cookie = new Cookie("cart",json);
				cookie.setMaxAge(24*60*60);
				resp.addCookie(cookie);
			}else{
				System.out.println("==========进入了json不等于空==============");
				cart = gson.fromJson(json, Cart.class);
				service.addCart(productId, cart, Integer.valueOf(num));
				String json1 = gson.toJson(cart);
				cookie.setValue(json1);
			}
			 
			try {
				resp.sendRedirect("page.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{ //用户不为空即用户已经登录了
			System.out.println("==========进入了user不等于空==============");
			  //从数据库取出用户的购物车
			  try {
					resp.sendRedirect("page.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			  
		}
		
		
	}

	


	
}
