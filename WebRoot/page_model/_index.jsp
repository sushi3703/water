<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kuakao" uri="http://www.kuakao.net/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/tool/jquery/date/date_input.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/tool/superTables/superTables.css" />
    
	<script language="javascript" src="${pageContext.request.contextPath}/tool/jquery/jquery.min.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/tool/jquery/date/jquery.date_input.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/tool/superTables/superTables.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#st1").date_input();
			$("#et1").date_input();
		})
	</script>
</head>
<body>
<div class="mainCon">
  <div class="mCon">
    <div class="main_32">
      <div class="main_adr">
      当前位置：<span> 管理 </span>&nbsp; &gt; &nbsp;<span class="orange"> 管理</span>
      </div>
    </div>
    <div class="mCon_list">
	  <form action="${pageContext.request.contextPath}/admin/securityModule/index.action" method="get">
      <div class="inke_cler">
      	<div class="left">
          <div class="mCon_1t mCon_2t">ID：</div>
          <div class="mCon_con mCon_con2 inke_con2">
			<input class="mCon_int" type="text" name="s1" value="${_page.s1}" />
		  </div>
        </div>
        <div class="left">
          <div class="mCon_1t mCon_2t">名称：</div>
          <div class="mCon_con mCon_con2 inke_con2">
			<input class="mCon_int" type="text" name="s2" value="${_page.s2}" />
		  </div>
        </div>
        <div class="clear"></div>
      </div>
      
      <div class="inke_cler">
      	<div class="left">
          <div class="mCon_1t mCon_2t">时间从：</div>
          <div class="mCon_con mCon_con2 inke_con2">
			<input type="text" name="st1" id="st1" value="${_page.st1}" readonly="readonly" class="mCon_int Wdate" />
		  </div>
        </div>
        <div class="left">
          <div class="mCon_1t mCon_2t">时间到：</div>
          <div class="mCon_con mCon_con2 inke_con2">
			<input type="text" name="et1" id="et1" value="${_page.et1}" readonly="readonly" class="mCon_int Wdate" />
		  </div>
        </div>
        <div class="clear"></div>
      </div>
      
      <div class="inke_cler">
      	<div class="left">
          <div class="mCon_1t mCon_2t">出入库：</div>
          <div class="mCon_con mCon_con2 inke_con2">
			<select name="s3" class="mCon_int">
				<option value="">请选择</option>
				<option value="1">入库</option>
				<option value="2">出库</option>
			</select>
		  </div>
        </div>

        <div class="clear"></div>
      </div>
      
      <div class="clear_con"></div>
      <div class="mCon_con mCon_con2">
        <input type="submit" value="查 询" name="" class="mCon_btn2 qued">
      </div>
      </form>
      <div class="clear"></div>
    </div>
    
    <div class="clear"></div>
    <div class="mCon_tab">
      <ul>
        <li><span class="mCon_1cuta">列表</span></li>
		<li><a class="mCon_1cuta" href="${pageContext.request.contextPath}/admin/securityModule/to_add.action">添加</a></li>
      </ul>
    </div>
    <div class="main_list fakeContainer">
      <table id="show_table" border="1" cellspacing="0" cellpadding="0">
        <tr class="main_title main_title2">
          <td>操作</td>
          <td>ID</td>
          <td>名称</td>

          <td>模块描述</td>
          <td>包含功能点</td>

        </tr>
        <c:if test="${empty list}">
        <tr><td colspan="5" align="center">暂无数据！</td></tr>
        </c:if>
        <c:if test="${!empty list}">
        <c:forEach items="${list}" var="one">
        
        <tr class="main_td">
          <td>
          	<a class="main_fun" title="编辑" href="${pageContext.request.contextPath}/admin/securityModule/to_edit.action?id=${one.id}"><img class="admin_sq" src="${pageContext.request.contextPath}/images/b_edit.png" /></a>
          	<a class="main_fun" title="删除" href="javascript:commonDel('${pageContext.request.contextPath}/admin/securityModule/do_remove.action',{'id':'${one.id}'});"><img class="admin_sq" src="${pageContext.request.contextPath}/images/b_drop.png" /></a>
          </td>
        
          <td>${one.id}</td>
          <td>${one.modulename}</td>
          <td>${one.moduledesc}</td>
          <td>${one.urlsStr}</td>

        </tr>
        </c:forEach>
        </c:if>
        
      </table>
    </div>
    <div class="scxz">
      	<div class="page">
			<kuakao:page template="pager/newAdminPager.ftl"></kuakao:page>
		</div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript">
	$(function() {
		new superTable("show_table", {
			cssSkin : "sSky",
			fixedCols : 3
		});
})
</script>
</body>
</html>