<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="kuakao" uri="http://www.kuakao.net/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta charset="utf-8">
    <title>资源管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/include_css_and_js.action"></script>
    </head>
   <body>
   <!--固定头部-->
 <script type="text/javascript" src="${pageContext.request.contextPath}/front/login/common_top.action"></script>
<!--固定头部结束-->
  <div class="container-fluid">
    <div class="row-fluid">
    <!--左侧导航开始-->
	<jsp:include page="/WEB-INF/view/common_left.jsp"></jsp:include>    
    <!--左侧导航结束-->
    <div class="content">
    <div class="row-fluid no-margin">
    <div class="span12">
    <ul class="nav nav-tabs nav-tabs-main">
          <li class="active"><a href="#"><i class="icon-th-list"></i><span>资源列表</span></a></li>
          <li class=""><a href="${pageContext.request.contextPath}/admin/secResource/edit.action?queryStr=${_page.encodeQueryStr}"><i class="icon-plus"></i><span>新增资源</span></a></li>
        </ul>
      <div class="tab-content">
       <div id="1" class="tab-pane active">
       <form class="form-horizontal well" action="${pageContext.request.contextPath}/admin/secResource/index.action" method="get">
       <table class="offset1">
       
       <tr>
       <th>菜单:</th>
       <td>
       	<select name="appMenu" id="appMenu">
       		<option value="">全部</option>
	        <kuakao:xmlConfig configName="security_menu"/>
	        <c:forEach var="xmlConfig" items="${xmlConfigs}">
	        <option value="${xmlConfig.key}" <c:if test="${_page.appMenu==xmlConfig.key}">selected='selected'</c:if>>${xmlConfig.value}</option>
	        </c:forEach>
       	</select>
       </td>
       <th class="span2">资源名称:</th>
       <td class="span3"> <input type="text" name="resName" id="resName" value="${_page.resName}"  style="width: 210px;"  class="input-medium" /></td>
       </tr>
       <tr>
       <th class="span2">资源ID:</th>
       <td class="span3"> <input type="text" name="resId" id="resId" value="${_page.resId}"  style="width: 210px;"  class="input-medium" /></td>
       <td>&nbsp;</td><td>&nbsp;</td>
       </tr>
       <tr>
       <td style="text-align:center" colspan="4"><button type="submit" class="btn btn-primary">查询</button></td>
       </tr>
       </table> 
       </form>
        <div class="box">
       <div class="box-head">
       <h5>资源列表</h5>
       </div>
       <div class="box-normargin">
       
       <table class="table table-bordered table-striped">
       <tr>
       <th>ID</th>
       <th>所属菜单</th>
       <th>资源名称</th>
       <th>包含URL</th>
       <th>操作</th>
       </tr>
       <tbody>
       <c:forEach var="secResourceEntity" items="${secResourceEntitys}">
       <tr>
       <td>${secResourceEntity.resId}</td>
       <td><kuakao:xmlConfig configName="security_menu" isMap="true" key="${secResourceEntity.appMenu}"/>&nbsp;</td>
       <td>${secResourceEntity.resName}</td>
       <td>
       &nbsp;
       <c:if test="${!empty secResourceEntity.urls}">
       <table>
       <c:forEach items="${secResourceEntity.urls}" var="url">
       <tr>
       <td>&nbsp;<c:if test="${url.urlShow==1}">show</c:if></td>
       <td>${url.urlMethod==1?"get":"post"}</td>
       <td>${url.urlName}</td>
       <td>${url.urlPath}</td>
       </tr>
       </c:forEach>
       </table>
       </c:if>
       </td>
       <td>
       	<div class="btn-group">
                <button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
                <ul class="dropdown-menu">
                  <li><a href="${pageContext.request.contextPath}/admin/secResource/edit.action?resId=${secResourceEntity.resId}&queryStr=${_page.encodeQueryStr}">编辑</a></li>
                  <li><a href="javascript:void(0)" onClick="commonDel('${pageContext.request.contextPath}/admin/secResource/destroy.action',{'resId':'${secResourceEntity.resId}'});">删除</a></li>
                </ul>
       	</div>
       </td>
       </tr>
       </c:forEach>
       </tbody>
       </table>
        <div class="pagination pagination-centered">
    <kuakao:page template="pager/adminPager2013.ftl"></kuakao:page>
        </div>
       </div>
       </div>
       </div>
       </div>  
  
    </div>
    </div>
    
    
    </div>
    </div>
    </div>
  </body>
</html>

