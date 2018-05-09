package com.forge.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import org.apache.log4j.Logger;

import com.forge.bean.Cart;
import com.forge.bean.CartItem;
import com.forge.bean.Forge_Cart;
import com.forge.bean.Forge_Product;
import com.forge.bean.Forge_Users;
import com.forge.service.Forge_CartService;
import com.forge.service.ProductService;
import com.forge.service.UserService;
import com.forge.service.impl.Forge_CartServiceImpl;
import com.forge.service.impl.ProductServiceImpl;
import com.forge.service.impl.UserServiceImpl;
import com.forge.util.Md5Encrypt;
import com.forge.util.MemcachedUtil;
import com.google.gson.Gson;


@WebServlet(value="/UsersServlet")
public class UsersServlet extends HttpServlet {

	Logger logger = Logger.getLogger(UsersServlet.class);
	UserService service = new UserServiceImpl();
	ProductService pservice = new ProductServiceImpl();
	Forge_CartService fcService = new Forge_CartServiceImpl();
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
			case "exit": //用户退出登录
				exit(req,resp); 
				break;
		}
		
			
	}

	//用户退出登录，将缓存中的购物车存入数据库中
	private void exit(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("================进入了exit======================");
		String userId = req.getParameter("userId");
		//取出缓存中的购物车
		MemcachedClient client = MemcachedUtil.getInstance();
		Cart mCart = (Cart) client.get("cart");
		
		System.out.println(mCart.getMap().size()+"================>3333");
		if(mCart.getMap().size()>0){
			//删除数据库中全部数据
			fcService.delDbData();
			//将缓存中的购物车存入数据库
			Map<String,CartItem> mMap = mCart.getMap();  //取出缓存中的购物车中的购物项 
			Iterator mit = mMap.entrySet().iterator();
			while(mit.hasNext()){
				Map.Entry mentry = (Map.Entry) mit.next();
				CartItem mitem = (CartItem) mentry.getValue();//获取缓存中的的购物项
				fcService.addProduct(userId, mitem);
			}
		}
		
				//清除session中的用户
				//req.getSession().removeAttribute("user");
				req.getSession().invalidate();
				
				//清除缓存中的用户
				client.delete("cart");
				
				/*Cart cart = new Cart();
				client.set("cart", 100, cart);*/
				//清除cookie中的购物车
				 Cookie []cookies =req.getCookies();
				    for(int i=0;i<cookies.length;i++){
				    	if(cookies[i].getName().equals("cart")){
				    		Cookie newCookie = new Cookie("cart",null);
				    		newCookie.setMaxAge(0);
				    		resp.addCookie(newCookie);
				    	}
				    }
				    
				    try {
						resp.sendRedirect("index.jsp");
					} catch (IOException e) {
						e.printStackTrace();
					}
		
		
		
		
		
		
		
		
		
		
//		Map<String,CartItem> mMap = mCart.getMap();  //取出缓存中的购物车中的购物项 
/*		//取出数据库中用户的购物车
		Cart uCart = getUserCart(Integer.valueOf(userId));
		if(uCart==null){
			uCart = new Cart();
		}
		Map<String,CartItem> uMap = uCart.getMap();  //取出数据库中的购物车中的购物项 
		Iterator mit = mMap.entrySet().iterator();
		Iterator uit = uMap.entrySet().iterator();
		if(mit.hasNext()){ //缓存中购物车不为空
			System.out.println("================进入了exit====缓存中购物车不为空==================");
			while(mit.hasNext()){  //循环遍历缓存中购物项
				Map.Entry mentry = (Map.Entry) mit.next();
				String mkey = mentry.getKey().toString();
				CartItem mitem = (CartItem) mentry.getValue();//获取缓存中的的购物项
				if(uit.hasNext()){  //如果用户的购物车不为空
					System.out.println("================进入了exit====用户的购物车不为空==================");
					while(uit.hasNext()){//循环遍历用户的购物项
						Map.Entry uentry = (Map.Entry) uit.next();
						String ukey = uentry.getKey().toString();
						if(!ukey.equals(mkey)){ //如果两者的key不相同，则说明商品不同直接插入数据库
							System.out.println("================进入了exit====两者的key不相同==================");
							fcService.addProduct(userId, mitem);
						}else{ //如果两者的ke不相同，两者商品数量和钱进行合并
							System.out.println("================进入了exit====两者的key相同==================");
							CartItem uitem = (CartItem) uentry.getValue();  //获取用户的购物项
							uitem.setNum(uitem.getNum());    //合并两者数量
							uitem.setPrice(uitem.getPrice()); //合并两者的钱
							fcService.updateProduct(userId,uitem);
						}
					}
				}else{ //如果用户的购物车为空则直接将缓存中的数据插入数据库
					System.out.println("================进入了exit===用户的购物车为空==================");
					fcService.addProduct(userId, mitem);
				}	
			}
		}
	*/
		
		
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
			System.out.println("user=============>"+user.getUserId());
			req.getSession().setAttribute("user", user);  //登录成功后将用户保存在session作用域中
			logger.info("登陆成功！");
			
			//合并购物车
			Cart cart = mergeCart1(user,req,resp);
			System.out.println("+++++++++y要存入缓存中的cart:"+cart);
			if(cart!=null){
				//将购物车存在缓存中
				System.out.println("进入if+++++++++y要存入缓存中的cart:");
				MemcachedClient client = MemcachedUtil.getInstance();
				client.add("cart",1000000,cart);
				//client.set("cart",1000000,cart);
				System.out.println("client.get('cart):"+client.get("cart"));
			}
			
			try {
					resp.sendRedirect("index.jsp");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

	private Cart mergeCart1(Forge_Users user,HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("==========进入了mergeCart1==============");
			//从数据库中取出购物车
		  Cart userCart = getUserCart(user.getUserId());
		  //从cookie中取出购物车
		  Cart cookieCart = getCookieCart(req,resp);
		  //两个购物车进行合并
		  Cart mergeCart = mergeCart(userCart,cookieCart);
		  if(mergeCart==null){
			  mergeCart = new Cart();
		  }
		return mergeCart;
	}

	private Cart mergeCart(Cart userCart, Cart cookieCart) {
		System.out.println("==========进入了mergeCart==============");

		Cart cart = null;
		//判断两个购物车是否为空
		
		if(userCart!=null&&cookieCart==null){
			return userCart;
		}
		if(userCart==null&&cookieCart!=null){
			return cookieCart;
		}
		if(userCart!=null&&cookieCart!=null){
			//两者都不为空把cookieCart加入到userCart
			userCart.setCount(userCart.getCount()+cookieCart.getCount());
			userCart.setPrice(userCart.getPrice()+cookieCart.getPrice());
			//获取cookieCart的map
			Map<String,CartItem> map = cookieCart.getMap();
			//遍历map并加入userCart
			Iterator it = map.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry entry = (Map.Entry) it.next();
				String key = entry.getKey().toString();
				CartItem item = (CartItem) entry.getValue();
				userCart.getMap().put(key, item);
				userCart.setCount(userCart.getCount());
			}
			return userCart;
		}
		
		return cart;
	}

	//从cookie中取出购物车
	private Cart getCookieCart(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("==========进入了getCookieCart==============");
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
		Gson gson = new Gson();
		Cart cart = gson.fromJson(json, Cart.class);
		System.out.println("==========进入了getCookieCart=============="+cart);
		return cart;
	}

	//获取用户的购物车
	private Cart getUserCart(int userId) {
		System.out.println("==========进入了getUserCart==============");

		Cart cart =null;
		double price = 0;  //商品总价
		List<Forge_Cart> item = fcService.findByUserId(userId);
		if(item.size()>0){
			cart = new Cart();
			for(int i = 0;i<item.size();i++){
				//获取商品的id
				String productId = item.get(i).getProductId();
				//根据商品id获取商品
				Forge_Product product = pservice.findById(productId);
				//获取商品数量
				String num =item.get(i).getProductNum();
				//获取商品小计
				double price1 = item.get(i).getPrice();
				//创建购物项将上商品加入购物项中
				 CartItem cartItem = new CartItem();
				 cartItem.setNum(Integer.valueOf(num));
				 cartItem.setPrice(price1);
				 cartItem.setProduct(product);
				
				cart.getMap().put(productId, cartItem);
				price+=item.get(i).getPrice();  //循环获取总价
			}
			cart.setCount(cart.getCount()+item.size());
			cart.setPrice(cart.getPrice()+price);
		}			
		System.out.println("==========进入了getUserCart=============="+cart);
		return cart;
	}
	
}
