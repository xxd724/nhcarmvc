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
<title>注册--南华汽车网</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="js/jquery.js"></script>
<script language="javascript" src="js/nhcar.js"></script>
<script language="javascript" src="js/register.js"></script>
</head>

<body>
<c:import url="header"/>
<div id="main">
	<div id="content" class="register">
        <div id="loginform">
        <div id="form">
        <h2><a href="login">立即登录</a>注册</h2>
      	<form action="adduser" method="post">
        	<p><input type="text" name="uname" class="txtinfo uname"/>
            <span class="tipinfo">输入用户名</span>
            <span class="errorinfo">不能为空！</span>
            </p>
            <p><input type="password" name="upwd"  class="txtinfo upwd"/>
            <span class="tipinfo">输入密码</span>
            <span class="errorinfo">不能为空！</span>
            </p>
            <p><input type="password" name="urepwd"  class="txtinfo upwd"/>
            <span class="tipinfo">重新输入密码</span>
            <span class="errorinfo">两次密码不一致！</span>
            </p>
            <p><input type="text" name="uemail"  class="txtinfo uemail"/>
            <span class="tipinfo">输入Email地址</span>
            <span class="errorinfo">不能为空或格式不对！</span>
            </p>
            <p><input type="submit" class="bt" value="注册" /></p>
        </form>
        
      </div>
        
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
