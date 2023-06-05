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
<title>南华汽车网</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="js/jquery.js"></script>
<script language="javascript" src="js/nhcar.js"></script>
<script language="javascript" src="js/banner.js"></script>
</head>

<body>
<c:import url="header"/>
<div id="banner">
    <div id="banner-content">
        <ul class="bannerlist">
            <li ><a href="#"><img src="images/banner1.jpg" /></a></li>
            <li><a href="#"><img src="images/banner2.jpg" /></a></li>
            <li><a href="#"><img src="images/banner3.jpg" /></a></li> 
        </ul>
        <a href="#" id="leftarrow">left</a>
        <a href="#" id="rightarrow">right</a>
    </div>
 </div>
<div id="main">
	<div id="content">
        <div id="subcategory">
            <h2>品牌：</h2>
            <ul>
                  <li><a href="#">大众</a></li>
                  <li><a href="#">丰田</a></li>
                  <li><a href="#">吉利</a></li>
                  <li><a href="#">海马</a></li>
                  <li><a href="#">奥迪</a></li>
                  <li><a href="#">劳斯莱斯</a></li>
                  <li><a href="#">雷克萨斯</a></li>
                  <li><a href="#">本田</a></li>
                  <li><a href="#">英菲尼迪</a></li>
                  <li><a href="#">凯迪拉克</a></li>
                  <li><a href="#">奇瑞</a></li>
                  <li><a href="#">大众</a></li>
                  <li><a href="#">丰田</a></li>
                  <li><a href="#">吉利</a></li>
                  <li><a href="#">海马</a></li>
                  <li><a href="#">奥迪</a></li>
                  <li><a href="#">劳斯莱斯</a></li>
                  <li><a href="#">雷克萨斯</a></li>
                  <li><a href="#">本田</a></li>
                  <li><a href="#">英菲尼迪</a></li>
                  <li><a href="#">凯迪拉克</a></li>
                  <li><a href="#">奇瑞</a></li>
                  <li><a href="#">大众</a></li>
                  <li><a href="#">丰田</a></li>
                  <li><a href="#">吉利</a></li>
                  <li><a href="#">海马</a></li>
                  <li><a href="#">奥迪</a></li>
                  <li><a href="#">劳斯莱斯</a></li>
                  <li><a href="#">雷克萨斯</a></li>
                  <li><a href="#">本田</a></li>
                  <li><a href="#">英菲尼迪</a></li>
                  <li><a href="#">凯迪拉克</a></li>
                  <li><a href="#">奇瑞</a></li>
            </ul>
            <div class="clear"></div>
            <a href="#" class="more">more</a>
        </div>
        
        <div class="homeplist" id="hotplist">
        <h2><a href="#">more</a>热卖车型</h2>
          <ul>
          <c:forEach items="${hotProductList}" var="product">
              <li><a href="product?pid=${product.pid }"><img src="uploads/${product.ppic}" /></a>
                <h3><a href="product?pid=${product.pid }">${product.pname}</a></h3>
                <p><a href="addcart?pid=${product.pid}">立即抢购</a>RMB:${product.pprice}</p>
              </li>
          </c:forEach>
          </ul>
        </div>
        <div class="homeplist" id="newplist">
        <h2><a href="#">more</a>新车上市</h2>
        <ul>
          <c:forEach items="${newProductList}" var="product">
              <li><a href="product?pid=${product.pid }"><img src="uploads/${product.ppic}" /></a>
                <h3><a href="product?pid=${product.pid }">${product.pname}</a></h3>
                <p><a href="addcart?pid=${product.pid}">立即抢购</a>RMB:${product.pprice}</p>
              </li>
          </c:forEach>
        </ul>
        </div>
        <div class="homeplist" id="cheapplist">
        <h2><a href="#">more</a>促销车型</h2>
        <ul>
          <c:forEach items="${cheapProductList}" var="product">
              <li><a href="product?pid=${product.pid }"><img src="uploads/${product.ppic}" /></a>
                <h3><a href="product?pid=${product.pid }">${product.pname}</a></h3>
                <p><a href="addcart?pid=${product.pid}">立即抢购</a>RMB:${product.pprice}</p>
              </li>
          </c:forEach>
        </ul>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
