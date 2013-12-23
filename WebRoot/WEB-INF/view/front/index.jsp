<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta charset="utf-8" />
    <title>首页</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/include_css_and_js.action" ></script>
    </head>
   <body>
   <!--固定头部-->
 <script type="text/javascript" src="${pageContext.request.contextPath}/front/login/common_top.action" ></script>
<!--固定头部结束-->
  <div class="container-fluid">
    <div class="row-fluid">
    <!--左侧导航开始-->
    <div class='navi'><ul class='main-nav'>
    <li>
    <a class='light toggle-collapsed' href='#'>
    	<div class='ico'><i class='icon-th-large icon-white'></i></div>
    	基本信息
    	<img alt='' src='http://cachecss.kuakao.com/public/ui/img/toggle-subnav-down.png' />
    </a>
    <ul class='collapsed-nav closed' style='display: none'>
    <li><a style='margin-left: 0px;' href='${pageContext.request.contextPath}/front/user/to_update_user_base.action'>修改基本信息</a></li>
    <li><a style='margin-left: 0px;' href='${pageContext.request.contextPath}/front/user/to_update_pwd.action'>修改密码</a></li>
    </ul>
    </li>
	<script type="text/javascript" src="${pageContext.request.contextPath}/admin/security/security_left.action"></script>
	</ul></div>
    <!--左侧导航结束-->
    <div class="content">
    <div class="row-fluid">
    <div class="span12">
     <ul class="breadcrumb">
    <li>首页欢迎</li>
    <li>提示信息</li>
    </ul>
    </div>
    </div>
    </div>
    </div>
    </div>
  </body>
</html>
