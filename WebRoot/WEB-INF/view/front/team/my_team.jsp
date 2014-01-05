<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="kuakao" uri="http://www.kuakao.net/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="net.water.tool.file.ConfigUtil" %>
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
    };
    var showInvite = function(){
    	if(${empty teamEntity}){
    		alert("请先创建团队");
    	}else{
			$("#div_msg_invite_register").show();    		
    	}
    };
    var resetPwd = function(userId,email){
    	$.post("${pageContext.request.contextPath}/front/user/reset_pwd.action",{"userId":userId,"email":email},function(data){
    		alert(data);
    	});
    };
    $(function(){
    	$("#div_msg_invite_register").hide();
    })
    </script>
  </head>
  <body>
  <c:set var="loginUserIsAdmin" value="${!empty userLoginBaseInfo && userLoginBaseInfo.type == 1}"></c:set>
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
		<div class="alert alert-block alert-success">
		<a class="close" href="#" data-dismiss="alert">×</a>
		<h4 class="alert-heading">${showMsg}</h4>
		</div>
	</c:if>
	<form action="${pageContext.request.contextPath}/front/team/create_team.action" method="post" id="form_team_base_info" class="form-horizontal">
	<div class="control-group">
		<label class="control-label" for="teamName">团队名称：</label>
		<div class="controls">
		<input type="text" id="teamName" name="teamName" class="input-square" <c:if test="${!loginUserIsAdmin}"> style="border:0px" readonly="readonly"</c:if> value="${teamEntity.teamName}" />
		<c:if test="${loginUserIsAdmin}">
    	&nbsp;&nbsp;
    	<input type="button" id="btn_team_info" value="${empty teamEntity ? '创建团队' : '修改'}" onclick="teamBaseInfoFormSubmit()" class="btn btn-primary" />
    	</c:if>
		</div>
	</div>
	</form>
	</div>
    </div>
    
    <div class="box">
    <div class="box-head">
	<h3>团队成员</h3>
	<c:if test="${loginUserIsAdmin}">
	&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="邀请成员" id="btn_show_invite" onclick="showInvite()" class="btn btn-success" />
	</c:if>
	</div>
	<div id="div_msg_invite_register" class="alert alert-block alert-success">
		<a class="close" href="#" onclick="$('#div_msg_invite_register').hide();">×</a>
		<h4 class="alert-heading">复制以下链接给好友，即可邀请其加入</h4>
		<%=ConfigUtil.getValue("project_domain") %>/user/to_register.action?inviteId=${teamEntity.teamId}
	</div>
	<div class="box-content box-nomargin">
	<table class="table table-striped table-bordered">
       <thead>
       <tr>
       <th>用户类型</th>
       <th>用户名</th>
       <th>职务</th>
       <th>部门</th>
       <th>Email</th>
       <th>QQ</th>
       <th>手机</th>
       <c:if test="${loginUserIsAdmin}">
       <th>管理</th>
       </c:if>
       </tr>
       </thead>
       <tbody>
       <c:forEach var="userEntity" items="${users}">
       <tr>
       <td>${userEntity.type==1?"管理员":"成员"}</td>
       <td>${userEntity.uname}</td>
       <td>${userEntity.jobTitle}</td>
       <td>${userEntity.department}</td>
       <td>${userEntity.email}</td>
       <td>${userEntity.qq}</td>
       <td>${userEntity.mobile}</td>
       <c:if test="${loginUserIsAdmin}">
       <td>
       <c:if test="${userEntity.type != 1}">
       	<a href="${pageContext.request.contextPath}/front/security/to_update_security.action?userId=${userEntity.userId}">修改权限</a>&nbsp;
       	<a href="javascript:resetPwd('${userEntity.userId}','${userEntity.email}');">重置密码</a>
       	</c:if>
       </td>
       </c:if>
       </tr>
       </c:forEach>
       </tbody>
    </table>
	</div>
    </div>
    
    </div>
    </div>
    
    
    </div>
    </div>
    </div>
  </body>
</html>

