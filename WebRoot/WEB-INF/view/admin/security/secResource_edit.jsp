<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="kuakao" uri="http://www.kuakao.net/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta charset="utf-8">
    <title>资源编辑</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/include_css_and_js.action"></script>
    <script type="text/javascript">
    var checkForm = function(){
    	var urlIds = "";
    	$("input[name='resUrl']").each(function(){
        	if ($(this).attr("checked")) {
            	if(urlIds != "")urlIds += ",";
            	urlIds += $(this).val();
        	}
    	});
    	//alert("ids==="+urlIds);return false;
    	$("#urlIds").val(urlIds);
    	return true;
    };
    </script>
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
          <li><a href="${pageContext.request.contextPath}/admin/secResource/index.action?${secResourceDto.queryStr}">资源列表</a></li>
          <li class="active"><a data-toggle="tab" href="#1"><i class="icon-th-list"></i><span>编辑资源</span></a></li>
    </ul>
    <div class="tab-content">
    <div id="1" class="tab-pane active">
    <div class="box">
    <form action="${pageContext.request.contextPath}/admin/secResource/save.action" method="post"  class="form-horizontal" onsubmit="return checkForm();">
    <input type="hidden" id="queryStr" name="queryStr" value="${secResourceDto.queryStr}" />
    <input type="hidden" name="formToken" value="${secResourceDto.formToken}" />
    <input type="hidden" name="resId" value="${secResourceDto.resId}" />
    <input type="hidden" name="urlIds" id="urlIds" />
    <table class="table table-bordered table-striped">
    <tr>
    <th>资源名称：</th>
    <td><input type="text" id="resName" name="resName" value="${secResourceDto.resName}" /></td>
    </tr>
    <tr>
    <th>所属菜单：</th>
    <td>
		<select id="appMenu" name="appMenu">
			<option value="">请选择所属菜单</option>
			<kuakao:xmlConfig configName="security_menu"/>
			<c:forEach var="xmlConfig" items="${xmlConfigs}">
			<option value="${xmlConfig.key}" <c:if test="${secResourceDto.appMenu==xmlConfig.key}">selected='selected'</c:if>>${xmlConfig.value}</option>
			</c:forEach>
		</select>
      </td>
    </tr>
    <tr>
    <th>包含URL：</th>
    <td>&nbsp;
    <c:if test="${!empty menuUrls}">
    <div class="box">
    <c:forEach items="${menuUrls}" var="menuInfoMap">
    <a href="#collapse${menuInfoMap['menuId']}" data-parent="#accordion${menuInfoMap['menuId']}" data-toggle="collapse" class="accordion-toggle collapsed">${menuInfoMap['menuName']}</a>
    <c:if test="${!empty menuInfoMap['urlInfoMaps']}">
	<div class="accordion-body collapse" id="collapse${menuInfoMap['menuId']}" style="height: 0px;">
	<div class="accordion-inner">
    <c:forEach items="${menuInfoMap['urlInfoMaps']}" var="urlInfoMap">
    <input type="checkbox" name="resUrl" <c:if test="${urlInfoMap['selected']}">checked="checked"</c:if> value="${urlInfoMap['urlId']}" />&nbsp;
    <c:if test="${urlInfoMap['urlShow']==1}"><b>show</b></c:if>&nbsp;&nbsp;
    <i>${urlInfoMap['urlMethodShow']}</i>&nbsp;&nbsp;
    ${urlInfoMap['urlName']}&nbsp;&nbsp;
    ${urlInfoMap['urlPath']}
    </c:forEach>
    </div>
    </div>
    </c:if>
    </c:forEach>
    </div>
    </c:if>
    </td>
    </tr>
    <tr>
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

