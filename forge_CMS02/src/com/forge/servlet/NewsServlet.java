package com.forge.servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.forge.bean.Forge_News;
import com.forge.dao.NewsDao;
import com.forge.dao.impl.NewsDaoImpl;
@WebServlet("/NewsServlet/*")
public class NewsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//设置post乱码
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String method = req.getParameter("method");
		System.out.println(method);
		switch (method) {
		case "addNews":
			addNews(req, resp); // 新增的方法
			System.out.println("aaa");
			break;
		case "getAllNews":
			findAllNews(req, resp); // 查询的方法
			break;
		case "findById":
			findById(req, resp); // 查询的方法
			break;
		case "delNews":
			delNews(req, resp); // 删除的方法
			break;
		case "updateNews":
			updateNews(req, resp); // 修改方法
			break;

		default:
			break;
		}
	}

	private void updateNews(HttpServletRequest req, HttpServletResponse resp) {
		
	}

	private void delNews(HttpServletRequest req, HttpServletResponse resp) {
		
	}

	private void findById(HttpServletRequest req, HttpServletResponse resp) {
		
	}

	private void findAllNews(HttpServletRequest req, HttpServletResponse resp) {
		
	}

	/**
	 *  新增的方法   包含文件上传
	 *  
	 *  
	 *  01.引入需要的jar包
	 *  02.在form表中中更改enctype
	 *  03.ServletFileUpload.isMultipartContent(request)  来判断我们的请求是不是文件上传请求
	 *  04.获取请求中所有的表单元素
	 *     List<FileItem>  list=ServletFileUpload.parseRequest(request)
	 *     每一个表单元素就对应一个FileItem
	 *  05.FileItem.isFormField()
	 *     true===>普通的表单元素
	 *             getFiledName()===>获取name属性值
	 *             getString(String s)===》获取value的值  s===>编码格式
	 *     flase==>文件上传元素   
	 *            getName===>获取上传文件的名称
	 *            getContentType()===》获取上传文件的类型      mime-type
	 *            
	 *            
	 */
	private void addNews(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("add方法");
		
		Forge_News news = new Forge_News();
		NewsDao dao= new NewsDaoImpl();
		//创建factory对象 可以设置缓冲区大小 以及存放位置
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		//判断是否是文件上传类型
		boolean flag = upload.isMultipartContent(req);
		System.out.println(flag);
		if(flag){//form表单是文件上传类型
			try {
				//获取所有的表单元素
				List <FileItem> items = upload.parseRequest(req);
				Iterator<FileItem> its = items.iterator();
				while(its.hasNext()){
					FileItem item = its.next();
					//判断表单元素是什么类型
					if(item.isFormField()){//证明是普通元素
						//title content createtime
						String fieldName = item.getFieldName();
						System.out.println(fieldName);
						switch(fieldName){
							case "title":
								try {
									news.setTitle(item.getString("UTF-8"));
									System.out.println(news.getTitle());
								} catch (UnsupportedEncodingException e) {
									e.printStackTrace();
								}
								break;
							case "createTime":
								try {
									news.setCreateTime(new SimpleDateFormat("dd/MM/yy")
											.parse(item.getString("UTF-8")));
								} catch (UnsupportedEncodingException e) {
									e.printStackTrace();
								} catch (ParseException e) {
									e.printStackTrace();
								}
								break;
							case "content":
								try {
									news.setContent(item.getString("UTF-8"));
								} catch (UnsupportedEncodingException e) {
									e.printStackTrace();
								}
									break;
						}
						
						req.getSession().setAttribute("news",news);
					} else {//证明是文件的元素
						String uploadPath = req.getSession().getServletContext().getRealPath("upload");
						File file = new File(uploadPath);
						if (!file.exists()) {
							file.mkdirs();
						}
						String fileName = item.getName();
						if (!"".equals(fileName) && null != fileName) {
							File saveFile = new File(uploadPath, fileName);
							item.write(saveFile);
							//news = (Forge_News)req.getSession().getAttribute("news");
							news.setImg(uploadPath + "\\" + fileName);
							System.out.println(news.getImg());
							
						}
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			dao.add(news);
		}
	}

}
