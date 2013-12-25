<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="kuakao" uri="http://www.kuakao.net/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta charset="utf-8">
    <title>资源组编辑</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/include_css_and_js.action"></script>
    <script type="text/javascript">
    var checkForm = function(){
    	var resIds = "";
    	$("input[name='resGroup']").each(function(){
        	if ($(this).attr("checked")) {
            	if(resIds != "")resIds += ",";
            	resIds += $(this).val();
        	}
    	});
    	//alert("ids==="+resIds);return false;
    	$("#resIds").val(resIds);
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
          <li><a href="${pageContext.request.contextPath}/admin/secResGroup/index.action?${secResGroupDto.queryStr}">资源组列表</a></li>
          <li class="active"><a data-toggle="tab" href="#1"><i class="icon-th-list"></i><span>编辑资源组</span></a></li>
    </ul>
    <div class="tab-content">
    <div id="1" class="tab-pane active">
    <div class="box">
    <form action="${pageContext.request.contextPath}/admin/secResGroup/save.action" method="post"  class="form-horizontal" onsubmit="return checkForm();">
    <input type="hidden" id="queryStr" name="queryStr" value="${secResGroupDto.queryStr}" />
    <input type="hidden" name="formToken" value="${secResGroupDto.formToken}" />
    <input type="hidden" name="groupId" value="${secResGroupDto.groupId}" />
    <input type="hidden" name="resIds" id="resIds" />
    <table class="table table-bordered table-striped">
    <tr>
    <th>资源组名称：</th>
    <td><input type="text" id="groupName" name="groupName" value="${secResGroupDto.groupName}" /></td>
    </tr>
    <tr>
    <th>包含资源：</th>
    <td>&nbsp;
    <c:if test="${!empty menuResources}">
    <div class="box">
    <c:forEach items="${menuResources}" var="menuInfoMap">
    <a href="#collapse${menuInfoMap['menuId']}" data-parent="#accordion${menuInfoMap['menuId']}" data-toggle="collapse" class="accordion-toggle collapsed">${menuInfoMap['menuName']}</a>
    <c:if test="${!empty menuInfoMap['resourceInfoMaps']}">
	<div class="accordion-body collapse" id="collapse${menuInfoMap['menuId']}" style="height: 0px;">
	<div class="accordion-inner">
    <c:forEach items="${menuInfoMap['resourceInfoMaps']}" var="resourceInfoMap">
    <input type="checkbox" name="resGroup" <c:if test="${resourceInfoMap['selected']}">checked="checked"</c:if> value="${resourceInfoMap['resId']}" />&nbsp;
    ${resourceInfoMap['resName']}
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

