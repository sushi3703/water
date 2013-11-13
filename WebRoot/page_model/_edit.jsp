<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>添加/修改</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css" />
	<script language="javascript" src="${pageContext.request.contextPath}/tool/jquery/jquery.min.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
</head>
<body>
 <div class="mainCon">
  <div class="mCon">
  <div class="mCon_tab">
   <ul>
   	<li><span class="mCon_1cuta">添加 / 修改一个</span></li>
   	<li><span class="mCon_1cuta"><a href="javascript:history.back(-1);">返回</a></span></li>
   </ul>
  </div>
  <form action="${pageContext.request.contextPath}/admin/securityModule/do_save.action" method="post" onsubmit="return beforeSubmit();">
   <input type="hidden" name="id" value="${empty baseinfo ? 0 : baseinfo.id}" />
   <div class="inke_list">
   	<div class="left">
   	<div class="mCon_1t mCon_2t"><strong>aa：</strong></div>
    <div class="mCon_con mCon_con2 su_edit_input_width">
		<input class="mCon_int" type="text" name="aa" value="${baseinfo.aa}" />
	</div>
   	</div>
   	<div class="left">
   	<div class="mCon_1t mCon_2t"><strong>cc：</strong></div>
    <div class="mCon_con mCon_con2 su_edit_input_width">
		<input class="mCon_int" type="text" name="cc" value="${baseinfo.cc}" />
	</div>
   	</div>
   </div>
   
   <div class="inke_list">
   	<div class="left">
   	<div class="mCon_1t mCon_2t"><strong>a2：</strong></div>
    <div class="mCon_con mCon_con2 su_edit_input_width">
		<select name="" class="mCon_int">
			<option value="">aaaaaaaaaa</option>
			<option value="">cccccccccccc</option>
		</select>
	</div>
   	</div>
   	<div class="left">
   	<div class="mCon_1t mCon_2t"><strong>c2：</strong></div>
    <div class="mCon_con mCon_con2 su_edit_input_width">
		<input type="text" name="dateStr" id="tf" readonly="readonly" class="mCon_int" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/tool/jquery/date/date_input.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/tool/jquery/date/jquery.date_input.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#tf").date_input();
		})
	</script>
	</div>
   	</div>
   </div>
   
   <div class="inke_list">
   	<div class="left">
   	<div class="mCon_1t mCon_2t"><strong>a3：</strong></div>
    <div class="mCon_con mCon_con2 su_edit_input_width">
		<fieldset title="ccccccc" class="mCon_int" >
		<input type="radio" name="a3" value="1" />11
		<input type="radio" name="a3" value="2" />22
		<input type="radio" name="a3" value="3" />33
		</fieldset>
	</div>
   	</div>
   	<div class="left">
   	<div class="mCon_1t mCon_2t"><strong>c3：</strong></div>
    <div class="mCon_con mCon_con2 su_edit_input_width">
		<input class="mCon_int" type="text" name="cc" value="${baseinfo.cc}" />
	</div>
   	</div>
   </div>
   
   <div class="inke_list">
    <div class="mCon_1t mCon_2t"><strong>描述：</strong></div>
    <div class="mCon_con mCon_con2">
    	<textarea rows="6" cols="80" name="moduledesc" value="${baseinfo.moduledesc}"></textarea>
    </div>
   </div>

  <div class="scxz">
      <input class="fx1" type="submit" value="保 存" />
      <input class="fx1" type="button" onclick="history.back(-1);" value="返 回" />
  </div>
  </form>
  <div class="clear"></div>
  </div>
  <div class="clear"></div>
 </div>
 <script type="text/javascript">
//表单验证
 var beforeSubmit = function(){
 	//alert("aa");return false;
 	return true;
 }
 </script>
</body>
</html>


