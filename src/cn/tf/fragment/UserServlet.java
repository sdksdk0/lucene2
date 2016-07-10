package cn.tf.fragment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import cn.tf.bean2json.User;

public class UserServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//收集DataGrid向服务器发送的参数  --page(当前页号)
		String page=request.getParameter("page");
		System.out.println("page="+page);
		
		//收集DataGrid想服务器发送的参数  --rows（当前需要显示的记录数)
		String rows=request.getParameter("rows");
		System.out.println("rows="+rows);
		
		
		List<User>  userList=new ArrayList<User>();
		userList.add(new User(1,"朱大大",10000));
		userList.add(new User(2,"李水",9000));
		userList.add(new User(3,"王明",7000));
		userList.add(new User(4,"刘天一",5000));
		userList.add(new User(5,"水水",6000));
		userList.add(new User(6,"何丽",7000));
		userList.add(new User(7,"汪花",2000));
		
		Map<String,Object> map=new LinkedHashMap<String, Object>();
		map.put("total", userList.size());
		map.put("rows", userList);
		
		JSONArray  jsonArray=JSONArray.fromObject(map);
		String jsonJAVA=jsonArray.toString();
		
		jsonJAVA=jsonJAVA.substring(1,jsonJAVA.length()-1);
		
		
		//以流的方式将json文本输出到DateGrid组件中
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw=response.getWriter();
		pw.write(jsonJAVA);
		pw.flush();
		pw.close();
		
	
	}

}
