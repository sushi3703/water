<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="kuakao" uri="http://www.kuakao.net/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta charset="utf-8">
    <title>用户管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/include_css_and_js.action"></script>
    <script type="text/javascript">
    var resetPwd = function(userId,email){
    	$.post("${pageContext.request.contextPath}/admin/user/reset_pwd.action",{"userId":userId,"email":email},function(data){
    		alert(data);
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
          <li class="active"><a href="#"><i class="icon-th-list"></i><span>用户列表</span></a></li>
          <li class=""><a href="${pageContext.request.contextPath}/admin/user/edit.action?queryStr=${_page.encodeQueryStr}"><i class="icon-plus"></i><span>新增用户</span></a></li>
        </ul>
      <div class="tab-content">
       <div id="1" class="tab-pane active">
       <form class="form-horizontal well" action="${pageContext.request.contextPath}/admin/user/index.action" method="get">
       <table class="offset1">
       
       <tr>
       <th>用户名:</th>
       <td class="span3"> <input type="text" name="uname" value="${_page.uname}"  class="input-medium" style="width: 210px;" /></td>
       <th>部门 :</th>
       <td class="span3"> <input type="text" name="department" value="${_page.department}"  class="input-medium" style="width: 210px;" /></td>
       </tr>
       <tr>
       <td style="text-align:center" colspan="4"><button type="submit" class="btn btn-primary">查询</button></td>
       </tr>
       </table> 
       </form>
        <div class="box">
       <div class="box-head">
       <h5>用户列表</h5>
       </div>
       <div class="box-normargin">
       
       <table class="table table-bordered table-striped">
       <tr>
       <th>用户名</th>
       <th>部门</th>
       <th>职务</th>
       <th>Email</th>
       <th>QQ</th>
       <th>手机</th>
       <th>操作</th>
       </tr>
       <tbody>
       <c:forEach var="userEntity" items="${userEntitys}">
       <tr>
       <td>${userEntity.uname}</td>
       <td>${userEntity.department}</td>
       <td>${userEntity.jobTitle}</td>
       <td>${userEntity.email}</td>
       <td>${userEntity.qq}</td>
       <td>${userEntity.mobile}</td>
       <td> <div class="btn-group">
                <button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
                <ul class="dropdown-menu">
                  <li><a href="${pageContext.request.contextPath}/admin/user/edit.action?userId=${userEntity.userId}&queryStr=${_page.encodeQueryStr}">编辑</a></li>
                  <li><a href="">设置权限</a></li>
                  <li><a href="javascript:resetPwd('${userEntity.userId}','${userEntity.email}');">重置密码</a></li>
                  <li><a href="javascript:void(0)" onClick="commonDel('${pageContext.request.contextPath}/admin/user/destroy.action',{'userId':'${userEntity.userId}'});">删除</a></li>
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

