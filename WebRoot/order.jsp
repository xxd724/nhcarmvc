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
<title>购物车--南华汽车网</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="js/jquery.js"></script>
<script language="javascript" src="js/nhcar.js"></script>
</head>

<body>
<c:import url="header"/>
<div id="main">
	<div id="content">
    <h2 class="title">客户信息</h2>
    <div id="form">
      	<form action="order" method="post">
        	<p><label>*姓名：</label><input type="text" name="oname" id="mUserName" class="txt"/><span class="errorinfo" id="ckmUserName">不能为空</span></p>
            <p><label>*电话：</label><input type="text"  name="otel" class="txt"/><span class="errorinfo" id="ckmUserName">不能为空</span></p>
            <p><label>*地址：</label><input type="text" name="oaddress" class="ltxt" id="mTitle"/><span class="errorinfo" id="ckmTitle">不能为空</span></p>
            <p><input type="submit" class="bt" value="确定提交" /></p>
            
        </form>
        
      </div>
    <h2 class="title">购物车明细</h2>
        	<div id="cart">
				<form>
			  <table border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <th scope="col">汽车图片</th>
                    <th scope="col">名称</th>
                    <th scope="col">数量</th>
                    <th scope="col">单价</th>
                    <th scope="col">小结</th>
                    <th scope="col">操作</th>
                  </tr>
                  <c:forEach items="${cartlist}" var="cart">
                  <tr>
                    <td><a href="#"><img src="uploads/${cart.ppic }"/></a></td>
                    <td><a href="#">${cart.pname}</a> </td>
                    <td><input name="nums" type="text" value="${cart.cnum}" class="stxt" /></td>
                    <input name="cids" type="hidden" value="${cart.cid}" class="stxt" />
                    <td>￥${cart.pprice}</td>
                    <td>￥${cart.pprice*cart.cnum}</td>
                    <td><a href="deletecart?cid=${cart.cid}">删除</a></td>
                  </tr>
                 </c:forEach>
              </table>
				<div class="cartinfo">
					<p class="total">总金额：￥${total}</p>
				</div>
				</form>
			</div>
        
      </div>
        
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
