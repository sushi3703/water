<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="kuakao" uri="http://www.kuakao.net/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta charset="utf-8">
    <title>url增加</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/include_css_and_js.action"></script>
    <script type="text/javascript">
    
    </script>
  </head>
  <body>
  <!--固定头部-->
 <script type="text/javascript" src="${pageContext.request.contextPath}/front/login/common_top.action"></script>
<!--固定头部结束-->
  <div class="container-fluid">
    <div class="row-fluid">
    <!--左侧导航开始-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/admin/security/security_left.action"></script>    
    <!--左侧导航结束-->
    <div class="content">
    <div class="row-fluid no-margin">
    <div class="span12">
    <ul class="nav nav-tabs nav-tabs-main">
          <li><a href="${pageContext.request.contextPath}/admin/secUrl/index.action?${secUrlDto.queryStr}">URL列表</a></li>
          <li class="active"><a data-toggle="tab" href="#1"><i class="icon-th-list"></i><span>编辑URL</span></a></li>
    </ul>
    <div class="tab-content">
    <div id="1" class="tab-pane active">
    <div class="box">
    <form action="${pageContext.request.contextPath}/admin/secUrl/save.action" method="post"  class="form-horizontal">
    <input type="hidden" id="queryStr" name="queryStr" value="${secUrlDto.queryStr}" />
    <input type="hidden" name="urlId" value="${secUrlDto.urlId}" />
    <table class="table table-bordered table-striped">
    <tr>
    <th>URL地址：</th>
    <td><input type="text" id="urlPath" name="urlPath" value="${secUrlDto.urlPath}" style="width: 500px;" /></td>
    </tr>
    <tr>
    <th>URL名称：</th>
    <td><input type="text" id="urlName" name="urlName" value="${secUrlDto.urlName}" /></td>
    </tr>
    <tr>
    <th>所属菜单：</th>
    <td>
		<select id="appMenu" name="appMenu">
			<option value="">请选择所属菜单</option>
			<kuakao:xmlConfig configName="security_menu"/>
			<c:forEach var="xmlConfig" items="${xmlConfigs}">
			<option value="${xmlConfig.key}" <c:if test="${secUrlDto.appMenu==xmlConfig.key}">selected='selected'</c:if>>${xmlConfig.value}</option>
			</c:forEach>
		</select>
      </td>
    </tr>
    <tr>
    <th>访问方式：</th>
    <td>
    <kuakao:xmlConfig configName="security_url_method"/>
		<select name="urlMethod">
		<c:forEach var="xmlConfig" items="${xmlConfigs}">
		<option value="${xmlConfig.key}" <c:if test="${secUrlDto.urlMethod==xmlConfig.key}">selected='selected'</c:if>>${xmlConfig.value}</option>
		</c:forEach>
		</select>
    </td>
    </tr>
    <tr>
    <th>是否显示:</th>
    <td>
		<select name="urlShow">
		<option value="2" <c:if test="${secUrlDto.urlShow==2}">selected='selected'</c:if>>否</option>
		<option value="1" <c:if test="${secUrlDto.urlShow==1}">selected='selected'</c:if>>是</option>
		</select>
    </td>
    </tr>
    <tr>
    <td colspan="2" style="text-align:center">
    <button type="submit" class="btn btn-primary">保存</button>
     <button type="submit" class="btn " onclick="history.back(-1);" >返回</button>
    </td>
    </tr>
    </table>
     </form>
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

