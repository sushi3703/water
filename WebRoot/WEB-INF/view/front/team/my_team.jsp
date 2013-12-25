<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="kuakao" uri="http://www.kuakao.net/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta charset="utf-8">
    <title>团队信息</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/include_css_and_js.action"></script>
    <script type="text/javascript">
    var teamBaseInfoFormSubmit = function(){
    	var teamName = $.trim($("#teamName").val());
    		if(teamName == ""){
    			alert("请输入团队名称");
    			return;
    		}
    		$("#form_team_base_info").submit();
    }
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
          <li class="active"><a data-toggle="tab" href="#1"><i class="icon-th-list"></i><span>我的团队</span></a></li>
    </ul>
    
    <div class="box">
    <div class="box-head">
	<h3>基本信息</h3>
	</div>
	<div class="box-content">
	<c:if test="${!empty showMsg}">
		<div class="alert alert-info alert-block">
		<a class="close" href="#" data-dismiss="alert">×</a>
		<h4 class="alert-heading">${showMsg}</h4>
		</div>
	</c:if>
	<form action="${pageContext.request.contextPath}/front/team/create_team.action" method="post" id="form_team_base_info" class="form-horizontal">
	<div class="control-group">
		<label class="control-label" for="teamName">团队名称：</label>
		<div class="controls">
		<input type="text" id="teamName" name="teamName" class="input-square" <c:if test="${empty userLoginBaseInfo || userLoginBaseInfo.type !=1}"> style="border:0px" readonly="readonly"</c:if> value="${teamEntity.teamName}" />
		<c:if test="${!empty userLoginBaseInfo && userLoginBaseInfo.type == 1}">
    	&nbsp;&nbsp;
    	<input type="button" id="btn_team_info" value="修改" onclick="teamBaseInfoFormSubmit()" class="btn btn-primary" />
    	</c:if>
		</div>
	</div>
	</form>
	</div>
    </div>
     
    <div class="box">
    <div class="box-head">
	<h3>团队成员</h3>
	</div>
	<div class="box-content">
	
	assssssss
	<br/>
	vvvvvvvv
	</div>
    </div>
    
    </div>
    </div>
    
    
    </div>
    </div>
    </div>
  </body>
</html>

