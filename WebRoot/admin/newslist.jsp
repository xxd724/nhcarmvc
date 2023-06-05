<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻列表</title>
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="../js/jquery.js"></script>
<script language="javascript">
	$(function(){
		$(".tbllist tr:even").addClass("even");
		$(".tbllist tr:odd").addClass("odd");
		$(".del").click(function(){
		 	return confirm("确定要删除吗？");
		});
	});
</script>
</head>

<body id="frmain">

	<div id="main">
    	<div class="sharp color1">
           <div class="content">
           <form action="newslist" method="post" enctype="multipart/form-data">
           	<div class="tbl">
           		<h1><strong>当前位置：</strong>新闻列表</h1>
               
                <div class="optlist"><a href="#"><img src="images/tb.gif" />发布新闻</a></div>
                <table  class="tbllist">
				  <tr>
				    <th scope="col">新闻ID</th>
				    <th scope="col">标题</th>
				    <th scope="col">发布日期</th>
				    <th scope="col">操作</th>
				    </tr>
				  <c:forEach items="${newslist}" var="newslist"> 
				  <tr>
				    <td>${newslist.nid}</td>
				    <td>${newslist.ntitle}</td>
				    <td>${newslist.ndate}</td>
				    <td><a href="deletenews?nid=${newslist.nid}" class="del"><img src="images/icon_delete.gif" />删除</a><a href="modifynews?nid=${newslist.nid}"><img src="images/icon_edit.gif" />修改</a><a href="#">添加flash展示</a></td>
				  </tr>
				  </c:forEach>
				</table>
				<div class="pagenav">${pagenav}</div>
             </div>
            </form>
           </div>
		</div>
	</div>
    
</body>
</html>
