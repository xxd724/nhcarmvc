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
<script language="javascript">
function k(i){
	return document.getElementById(i);
}
function show(i){
	k(i).style.display="inline";
}
function close(i){
	k(i).style.display="none";
}
function checkpic(){
	k("checkno_id").src="checkno.jsp?t="+Math.random();
}
function checkdata(){
	var mUserName=k("mUserName").value;
	var mTitle=k("mTitle").value;
	var mContent=k("mContent").value;
	var checkno=k("checkno").value;
	if(mUserName==""){
		show("ckmUserName");
		return false;
	}else{
		close("ckmUserName");
	}
	if(mTitle==""){
		show("ckmTitle");
		return false;
	}else{
		close("ckmTitle");
	}
	if(mContent==""){
		show("ckmContent");
		return false;
	}else{
		close("ckmContent");
	}
	if(checkno==""){
		show("ckcheckno");
		return false;
	}else{
		close("ckcheckno");
	}
	return true;
}
</script>
</head>

<body>
<c:import url="header"/>
<div id="main">
	<div id="content">
        <div id="sidebar">
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
                 
            </ul>
        </div>
        <div id="main-right">
        <h2>在线留言</h2>
          
        <div id="form">
      	<form action="message" method="post" onsubmit="return checkdata()">
        	<p><label>*昵称：</label><input type="text"  id="mUserName" class="txt" name="mname"/><span class="errorinfo" id="ckmUserName">不能为空</span></p>
            <p><label>*主题：</label><input type="text" class="ltxt" id="mTitle" name="mtitle"/><span class="errorinfo" id="ckmTitle">不能为空</span></p>
            <p><label>*内容：</label><textarea class="mltxt" id="mContent" name="mcontent"></textarea><span class="errorinfo" id="ckmContent">不能为空</span></p>
            <p><label class="lblcheckno">*验证码：</label><input type="text"  id="checkno" class="txt" name="mcheckno"/><img src="checkno.jsp" onclick="this.src='checkno.jsp?t='+Math.random()"/><span class="errorinfo" id="ckcheckno">不能为空</span></p>
            <p><input type="submit" class="bt" value="提交" /></p>
            
        </form>
        
      </div>
      <div id="messagelist">
      <ul>
      <c:forEach items="${messagelist }" var="message">
        <li>
        	<h3>网友：${message.mname }</h3>
            <h4><span>${message.mdate }</span>${message.mtitle }</h4>
        	<div class="messagecontent">
            	<p>${message.mcontent}</p>
            </div>
        </li>
        </c:forEach>
      </ul>
       <div class="pagenav">${pagenav}</div>
      </div>
        
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
