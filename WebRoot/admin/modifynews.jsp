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
<link href="css/admin.css" rel="stylesheet" type="text/css" />
</head>

<body id="frmain">
	<div id="main">
    	<div class="sharp color1">
           <div class="content">
           <form action="modifynews" method="post">
           	<div class="tbl">
           		<h1><strong>当前位置：</strong>发布新闻</h1>
               
                <div class="optlist"><a href="#"><img src="images/tb.gif" />新闻列表</a></div>
                <table border="0" cellspacing="0" class="tbledit">
  					<input type="hidden" name="nid" id=""value="" />
                  <tr>
                    <th width="180" align="right" scope="row">*标题：</th>
                    <td align="left">
                      <input type="text" name="ntitle" id="" class="longtxt" value="${modify.ntitle }"  /><span class="errorinfo" id="ckntitle">请输入标题</span>
                    </td>
                  </tr>
                  <tr>
                    <th width="180" align="right" scope="row">*来源：</th>
                    <td align="left">
                      <input type="text" name="nfrom" id="" class="longtxt" value="${modify.nfrom }"  /><span class="errorinfo" id="ckntitle">请输入标题</span>
                    </td>
                  </tr>
             	<tr>
                     <th width="180" align="right" scope="row">*内容：</th>
                    <td align="left">
                      <textarea  name="ncontent" id="editor1" class="editcontent">${modify.ncontent }</textarea>
                 
                      </td>
                   </tr>
                  <tr>
                    <th scope="row">&nbsp;</th>
                    <td align="left"> 
                      <input name="" type="submit" value="修改"  class="bt"/></td>
                  </tr>
                </table>
            </div>
            </form>
           </div>
		</div>
	</div>
    
</body>
</html>
