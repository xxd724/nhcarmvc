<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="js/jquery.js"></script>
<script language="javascript" src="js/nhcar.js"></script>
</head>

<body>
<c:import url="header"/>
<div id="main">
	<div id="content">
        <div id="sidebar">
            <h2>品牌：</h2>
            <ul>
                <c:forEach items="${catelist}" var="cate">
            	  <li><a href="productlist?cid=${cate.cid }">${cate.cname}</a></li>
            	</c:forEach>
                 
            </ul>
        </div>
        <form id="search" action="newlist" method="get">
        	<p><label></label><input type="text"  value="" name="key" class="skey"/><input type="submit" value="搜索" class="searchbt" /></p>
        </form>
        <div id="main-right">
        <h2>资讯列表</h2>
        <c:forEach items="${newslist}" var="news">
           <ul class="newslist">
        	<li><span>${news.ndate}</span><a href="news?nid=${news.nid}">${news.ntitle}</a></li>
            
        </ul>
        </c:forEach>
        <div class="pagenav">${pagenav}</div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
