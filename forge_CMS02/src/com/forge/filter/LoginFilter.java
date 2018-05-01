package com.forge.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forge.bean.Forge_Users;

/*@WebFilter("/*")*/
public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			System.out.println("LoginFilter====>doFilter进行处理");
			HttpServletRequest req = (HttpServletRequest)request;  //子类的方法  向下转型
			HttpServletResponse resp = (HttpServletResponse)response;  //子类的方法  向下转型
			Forge_Users user = null;
			//看用户是否登录
			user = (Forge_Users)req.getSession().getAttribute("user");
			String path = req.getRequestURI();
			System.out.println("==============================="+path);
			if(user !=null || path.equals("login") || path.contains(".js") ||
					path.contains(".css")){
				//放行
				chain.doFilter(request, response);
				
			}else{
				resp.sendRedirect("production/login.jsp");
			}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
