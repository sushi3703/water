<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="kuakao" uri="http://www.kuakao.net/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta charset="utf-8">
    <title>url管理</title>
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
          <li class="active"><a href="#"><i class="icon-th-list"></i><span>URL列表</span></a></li>
          <li class=""><a href="${pageContext.request.contextPath}/admin/secUrl/edit.action?queryStr=${_page.encodeQueryStr}"><i class="icon-plus"></i><span>新增URL</span></a></li>
        </ul>
      <div class="tab-content">
       <div id="1" class="tab-pane active">
       <form class="form-horizontal well" action="${pageContext.request.contextPath}/admin/secUrl/index.action" method="get">
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
       <th>是否显示:</th>
       <td>
        <kuakao:xmlConfig configName="common_yes_no"/>
			<select name="urlShow">
				<option value="">全部</option>
			<c:forEach var="xmlConfig" items="${xmlConfigs}">
				<option value="${xmlConfig.key}" <c:if test="${_page.urlShow==xmlConfig.key}">selected='selected'</c:if>>${xmlConfig.value}</option>
			</c:forEach>
			</select>
       </td>
       </tr>
       <tr>
       <th class="span2">URL名称:</th>
       <td class="span3"> <input type="text" name="urlName" id="urlName" value="${_page.urlName}"  class="input-medium" style="width: 210px;" /></td>
       <th >提交方式:</th>
       <td>
        <kuakao:xmlConfig configName="security_url_method"/>
			<select name="urlMethod">
				<option value="">全部</option>
			<c:forEach var="xmlConfig" items="${xmlConfigs}">
				<option value="${xmlConfig.key}" <c:if test="${_page.urlMethod==xmlConfig.key}">selected='selected'</c:if>>${xmlConfig.value}</option>
			</c:forEach>
			</select>
       </td>
       </tr>
       <tr>
       <th class="span2">地址:</th>
       <td class="span3" colspan="3"> <input type="text" name="urlPath" id="urlPath" value="${_page.urlPath}" class="input-medium" style="width: 560px;" /></td>
       </tr>
       <tr>
       <td style="text-align:center" colspan="4"><button type="submit" class="btn btn-primary">查询</button></td>
       </tr>
       </table> 
       </form>
        <div class="box">
       <div class="box-head">
       <h5>URL列表</h5>
       </div>
       <div class="box-normargin">
       
       <table class="table table-bordered table-striped">
       <tr>
       <th>ID</th>
       <th>所属菜单</th>
       <th>URL名称</th>
       <th>提交方式</th>
       <th>地址</th>
       <th>是否显示</th>
       <th>操作</th>
       </tr>
       <tbody>
       <c:forEach var="secUrlEntity" items="${secUrlEntitys}">
       <tr>
       <td>${secUrlEntity.urlId}</td>
       <td><kuakao:xmlConfig configName="security_menu" isMap="true" key="${secUrlEntity.appMenu}"/>&nbsp;</td>
       <td>${secUrlEntity.urlName}</td>
       <td><kuakao:xmlConfig configName="security_url_method" isMap="true" key="${secUrlEntity.urlMethod}"/></td>
       <td>${secUrlEntity.urlPath}</td>
       <td><kuakao:xmlConfig configName="common_yes_no" isMap="true" key="${secUrlEntity.urlShow}"/></td>
       <td> <div class="btn-group">
                <button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
                <ul class="dropdown-menu">
                  <li><a href="${pageContext.request.contextPath}/admin/secUrl/edit.action?urlId=${secUrlEntity.urlId}&queryStr=${_page.encodeQueryStr}">编辑</a></li>
                  <li><a href="javascript:void(0)" onClick="commonDel('${pageContext.request.contextPath}/admin/secUrl/destroy.action',{'urlId':'${secUrlEntity.urlId}'});">删除</a></li>
                </ul>
              </div></td>
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

