<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="kuakao" uri="http://www.kuakao.net/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta charset="utf-8">
    <title>资源组管理</title>
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
          <li class="active"><a href="#"><i class="icon-th-list"></i><span>资源组列表</span></a></li>
          <li class=""><a href="${pageContext.request.contextPath}/admin/secResGroup/edit.action?queryStr=${_page.encodeQueryStr}"><i class="icon-plus"></i><span>新增资源组</span></a></li>
        </ul>
      <div class="tab-content">
       <div id="1" class="tab-pane active">
       <form class="form-horizontal well" action="${pageContext.request.contextPath}/admin/secResGroup/index.action" method="get">
       <table class="offset1">
       
       <tr>
       <th class="span2">组名称:</th>
       <td class="span3"> <input type="text" name="groupName" id="groupName" value="${_page.groupName}"  class="input-medium" style="width: 300px" /></td>
       </tr>
       <tr>
       <td style="text-align:center"><button type="submit" class="btn btn-primary">查询</button></td>
       </tr>
       </table> 
       </form>
        <div class="box">
       <div class="box-head">
       <h5>资源组列表</h5>
       </div>
       <div class="box-normargin">
       
       <table class="table table-bordered table-striped">
       <tr>
       <th>ID</th>
       <th>组名称</th>
       <th>包含资源</th>
       <th>操作</th>
       </tr>
       <tbody>
       <c:forEach var="secResGroupEntity" items="${secResGroupEntitys}">
       <tr>
       <td>${secResGroupEntity.groupId}</td>
       <td>${secResGroupEntity.groupName}</td>
       <td>
       &nbsp;
       <c:if test="${!empty secResGroupEntity.ress}">
       <c:forEach items="${secResGroupEntity.ress}" var="res" varStatus="ckey">
       	<c:if test="${ckey.index%2==0}">${res.resName}</c:if>
       	<c:if test="${ckey.index%2==1}"><i>${res.resName}</i></c:if>
       </c:forEach>
       </c:if>
       </td>
       <td>
       	<div class="btn-group">
                <button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
                <ul class="dropdown-menu">
                  <li><a href="${pageContext.request.contextPath}/admin/secResGroup/edit.action?groupId=${secResGroupEntity.groupId}&queryStr=${_page.encodeQueryStr}">编辑</a></li>
                  <li><a href="javascript:void(0)" onClick="commonDel('${pageContext.request.contextPath}/admin/secResGroup/destroy.action',{'groupId':'${secResGroupEntity.groupId}'});">删除</a></li>
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

