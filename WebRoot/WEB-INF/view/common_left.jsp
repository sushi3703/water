<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div class='navi'><ul class='main-nav'>
    <li class='open'>
    <a class='light toggle-collapsed' href='#'>
    	<div class='ico'><i class='icon-th-large icon-white'></i></div>
    	基本信息
    	<img alt='' src='http://cachecss.kuakao.com/public/ui/img/toggle-subnav-down.png' />
    </a>
    <ul class='collapsed-nav closed' style='display: block'>
    <li><a style='margin-left: 0px;' href='${pageContext.request.contextPath}/front/team/my_team.action'>我的团队</a></li>
    <li><a style='margin-left: 0px;' href='${pageContext.request.contextPath}/front/user/to_update_user_base.action'>修改基本信息</a></li>
    <li><a style='margin-left: 0px;' href='${pageContext.request.contextPath}/front/user/to_update_pwd.action'>修改密码</a></li>
    <li><a style='margin-left: 0px;' href='http://www.eakroko.de/neat/datatables.html' target="_blank">bootstrap-Neat</a></li>
    </ul>
    </li>
	<script type="text/javascript" src="${pageContext.request.contextPath}/front/security/security_left.action"></script>
	</ul>
	</div>