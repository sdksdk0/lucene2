package cn.tf.fy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import cn.tf.fy.entity.PageBean;
import cn.tf.fy.service.ArticleService;

public class ArticleServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
		//获取关键字
		String keywords=request.getParameter("keywords");
		if(keywords==null || keywords.trim().length()==0){
			keywords="是";
		}
		//获取当前页号
		String temp=request.getParameter("page");
		if(temp==null || temp.trim().length()==0){
			temp="1";
		}
		//调用业务层
		ArticleService articleService=new ArticleService();
		PageBean page=articleService.show(keywords,Integer.parseInt(temp));
		
		//构造map对象
		Map<String,Object> map=new LinkedHashMap<String, Object>();
		map.put("total", page.getAllPageNO());
		map.put("rows", page.getArticleList());
		
		//将map转成json
		JSONArray  jsonArray=JSONArray.fromObject(map);
		String jsonJAVA=jsonArray.toString();
		//去掉两边的[]符号
		jsonJAVA=jsonJAVA.substring(1,jsonJAVA.length()-1);
		
		//以流的方式将json文本输出到DateGrid组件中
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw=response.getWriter();
		pw.write(jsonJAVA);
		pw.flush();
		pw.close();
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

}
