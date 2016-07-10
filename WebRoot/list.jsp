<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>使用Jsp +Js + Jquery + EasyUI + Servlet + Lucene，完成分页</title>
    <!-- 引入css文件，无顺序 -->
    <link rel="stylesheet" href="themes/icon.css" type="text/css"></link>
    <link rel="stylesheet" href="themes/default/easyui.css" type="text/css"></link>
  	<!-- 引入js文件，有顺序 -->
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
  </head>
  <body>


	<!-- 输入区 -->
	<form id="myformID">
		输入关键字：<input type="text" value="" id="keywordID"/>
		<input type="button" value="站内搜索" id="findID"/>
	</form>
	<script type="text/javascript">
		//定位"站内搜索"按钮
		$("#findID").click(function(){
			//获取关键字
			var keyword = $("#keywordID").val();
			//去空格
			keyword = $.trim(keyword);
			//判断
			if(keyword.length == 0){
				//提示
				alert("请输入关键字!!!");
				//清空文本框的内容
				$("#keywordID").val("");
				//定位于输入关键字文本框
				$("#keywordID").focus();
			}else{
				//异步发送请求到服务器
				//load表示方法名
				//"keywords"表示需要发送的的参数名，后台收：request.getParameter("keywords")
				//keyword表示参数值
				$("#dg").datagrid("load",{    
				    "keywords" : keyword
				});  
			}
		});
	</script>
	
	

	<!-- 显示区 -->
	<table id="dg"></table>
	<script type="text/javascript">
		$("#dg").datagrid({
			url : "${pageContext.request.contextPath}/servlet/ArticleServlet?time="+new Date().getTime(),
			columns :  [[    
					        	{field:'id',title:'编号',width:100},    
					        	{field:'title',title:'标题',width:100},    
					        	{field:'content',title:'内容',width:100}
						]],
			fitColumns : true,
			singleSelect : true,
			pagination : true,
			pageSize : 2,
			pageList : [2]		    
		});
	</script>
	
  </body>
</html>
