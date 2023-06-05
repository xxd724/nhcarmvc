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
        <div class="homeplist" id="main-right">
        <h2>汽车列表</h2>
        <div class="ordernav">
        	<label>排序：</label><a href="#" class="cur">新车</a><a href="#">价格</a><a href="#">热评</a><a href="#">热销</a>
        </div>
          <ul>
          <c:forEach items="${productlist}" var="product">
              <li><a href="product?pid=${product.pid }"><img src="uploads/${product.ppic}" /></a>
                <h3><a href="product?pid=${product.pid }&pimd=${product.pid}">${product.pname}</a></h3>
                <p><a href="#">立即抢购</a>RMB:${product.pprice}</p>
              </li>
          </c:forEach>                 
        </ul>
        <div class="pagenav">${pagenav}</div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
<div id="cslist">
	<ul>
    	<li class="qq"><a href="#"><i>qq</i></a></li>
        <li class="tel"><a href="#"><i>tel</i></a></li>
        <li class="weixin"><a href="#"><i>weixin</i></a></li>
        <li class="top"><a href="#"><i>top</i></a></li>
    </ul>
</div>
</body>
</html>