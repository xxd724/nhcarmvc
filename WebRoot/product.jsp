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
        <div id="main-right">
        <h2>汽车详情</h2>
           <div class="ppic">
           	<img src="uploads/${product.ppic }" class="pmimg"/>
            <ul>
            	<c:forEach items="${productppic}" var="product">
            		<li><img src="uploads/${product.ppic }"/></li>
            	</c:forEach>
            </ul>
           </div>
        <div class="pinfo">
        	<h1>${product.pname }</h1>
            <h4><label>南汽价：</label>￥${product.pprice }</h4>
            <h5>品牌：<a href="productlist?cid=${product.cid}">${product.cname}</a></h5>
            <p><a href="#" class="cartbt">加入购物车</a></p>
        </div>       
        <div class="pcontent">
            <h2 class="ptitle">商品介绍</h2>            
            <div class="pintro">
            ${product.pcontent}
            </div>       
        <h2 class="ptitle">评论</h2>
				
			
      <div id="messagelist">
      <ul>
      	 <c:forEach items="${pmessagelist}" var="pmessage">
      	 	<li>
      	 		<h4><span>${pmessage.pmdate}</span>网友:${pmessage.pmname}</h4>
      	 		<div class="messagecontent">
      	 			<p>${pmessage.pmcontent}</p>
      	 		</div>
      	 	</li>      	 
      	 </c:forEach>       
      </ul>
       <div class="pagenav">${pagenav}</div>
       <div id="form">
      
        
      
      	<form action="messagep" method="post">
        	<p><label>*昵称：</label><input type="text"  id="mUserName" name="pmname" class="txt"/><span class="errorinfo" id="ckmUserName">不能为空</span></p>
            <p><label>*产品ID：</label><input type="text"  id="mUserName" name="ppid" value="${product.pid }" class="txt"/><span class="errorinfo" id="ckmUserName">不能为空</span></p>           
            <p><label>*内容：</label><textarea class="mltxt" id="mContent" name="pmcontent"></textarea><span class="errorinfo" id="ckmContent">不能为空</span></p>
            <p><label class="lblcheckno">*验证码：</label><input type="text"  id="checkno" name="pmcheckno" class="txt"/><img src="checkno.jsp" onclick="this.src='checkno.jsp?t='+Math.random()"/><span class="errorinfo" id="ckcheckno">不能为空</span></p>
            <p><input type="submit" class="bt" value="提交" /></p>
            
        </form>
        </div>
      </div>

        
        </div>
        </div>
    </div>
</div>
<div id="footer">
	<ul id="footer-nav">
    	<li><a href="#">免责条款</a></li>
        <li><a href="#">隐私保护</a></li>
        <li><a href="#">咨询热点</a></li>
        <li><a href="#">联系我们</a></li>
        <li><a href="#">公司简介</a></li>
        <li class="last"><a href="#">配送方式</a></li>
    </ul>
    <ul id="footer-intro">
    	<li class="item1">正品保障</li>
        <li class="item2">30天退换货</li>
        <li class="item3">货到付款</li>
        <li class="item4">实物拍摄</li>
        <li class="item5">实时发货</li>
        <li class="item6">会员积分</li>
    </ul>
    <div id="footer-content">
        <p>&copy;2002-2015 南华汽车网（nhcar.com）版权所有，并保留所有权利。ICP备案证书号：粤ICP888888号</p>
        <p>常年法律顾问：南华律师事务所。技术支持：广州锐发计算机科技有限公司</p>
        <p>广州市南华工商学院信息管理专业</p>
    </div>
</div>
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