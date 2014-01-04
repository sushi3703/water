<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="kuakao" uri="http://www.kuakao.net/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta charset="utf-8">
    <title>用户权限</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/include_css_and_js.action"></script>
    <script type="text/javascript">
     $(function(){
     //checkbox回填
     $("input[name='selRes']").each(function(){
         if ($(this).attr("chk")=="true") {
             $(this).attr("checked","checked");
         }
     })
	});
     var updateUserSecurity = function(){
    	 var ids = "";
    	$("input[name='selRes']").each(function(){
        	if ($(this).attr("checked")) {
            	if(ids != "")ids += ",";
            	ids += $(this).val();
        	}
    	});
    	
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
          <li class="active"><a data-toggle="tab" href="#1"><i class="icon-th-list"></i><span>修改用户权限</span></a></li>
    </ul>
    <c:if test="${empty menuResList}">
    <div class="alert alert-info alert-block">
		<a class="close" href="#" data-dismiss="alert">×</a>
		<h4 class="alert-heading">暂无可分配权限</h4>
		您暂时还没有可分配的权限，需要先到<a>资源中心</a>添加
	</div>
    </c:if>
    <c:if test="${!empty menuResList}">
    <c:forEach items="${menuResList}" var="menuResMap">
    <div class="box">
    	<div class="box-head"><h3>${menuResMap['menuName']}</h3></div>
    	<div class="box-content box-nomargin">
    	<table class="table table-striped table-bordered">
    	<c:forEach items="${menuResMap['resList']}" var="resMap">
		<tr>
		<td class="table-checkbox"><input type="checkbox" name="selRes" chk="${resMap['chk']}" value="${resMap['resId']}" /></td>
		<td>${resMap["resName"]}</td>
		</tr>
    	</c:forEach>
    	</table>
    	</div>
     </div>
    </c:forEach>
    <div class="form-actions">
    <input type="button" value="保存" onclick="updateUserSecurity()" class="btn btn-primary" />
    </div>
    </c:if>
    
     
    </div>
    </div>
    
    
    </div>
    </div>
    </div>
  </body>
</html>

