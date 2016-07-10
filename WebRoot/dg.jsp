<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dg.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	  <link rel="stylesheet" href="themes/icon.css" type="text/css"></link>
    <link rel="stylesheet" href="themes/default/easyui.css" type="text/css"></link>
    
  	<!-- 引入js文件，有顺序 -->
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
 	<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
 
  </head>
  
  <body>
    <table id="dg" ></table>
	
	<script>
		$("#dg").datagrid({
			url:"${pageContext.request.contextPath}/servlet/UserServlet?time="+new Date().getTime(),
			columns:[[
				{field:'id',title:'编号',width:100},
				{field:'name',title:'姓名',width:100},
				{field:'sal',title:'薪水',width:100}
			]],
			fitColumns:true,
			singleSelect:true,
			pagination : true,
			pageSize : 2,
			pageList : [2]	
		});
	
	
	</script>
    
    
  </body>
</html>
