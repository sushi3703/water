<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div class='navi'><ul class='main-nav'>
	
    <li id='li_menu_baseMenu0'>
    <a class='light toggle-collapsed' href='#'>
    	<div class='ico'><i class='icon-th-large icon-white'></i></div>
    	基本信息
    	<img alt='' src='http://cachecss.kuakao.com/public/ui/img/toggle-subnav-down.png' />
    </a>
    <ul id='ul_menu_baseUrl0' class='collapsed-nav closed'>
    <li id="li_url_baseUrl0"><a style='margin-left: 0px;' href='${pageContext.request.contextPath}/front/login/index.action?selMenu=baseMenu0&selUrl=baseUrl0'>首页</a></li>
    <li id="li_url_baseUrl1"><a style='margin-left: 0px;' href='${pageContext.request.contextPath}/front/team/my_team.action?selMenu=baseMenu0&selUrl=baseUrl1'>我的团队</a></li>
    <li id="li_url_baseUrl2"><a style='margin-left: 0px;' href='${pageContext.request.contextPath}/front/user/to_update_user_base.action?selMenu=baseMenu0&selUrl=baseUrl2'>修改基本信息</a></li>
    <li id="li_url_baseUrl3"><a style='margin-left: 0px;' href='${pageContext.request.contextPath}/front/user/to_update_pwd.action?selMenu=baseMenu0&selUrl=baseUrl3'>修改密码</a></li>
    <li id="li_url_baseUrl4"><a style='margin-left: 0px;' href='http://www.eakroko.de/neat/datatables.html' target="_blank">bootstrap-Neat</a></li>
    </ul>
    </li>
    
	<script type="text/javascript" src="${pageContext.request.contextPath}/front/security/security_left.action?selMenu=${param['selMenu']}&selUrl=${param['selUrl']}"></script>
	<script type="text/javascript">
	$(function(){
		//菜单选中项效果
		var selMenu = ${param['selMenu']};
		if(selMenu == ""){//默认选中菜单
			selMenu = "baseMenu0";;
		}
		var selUrl = ${param['selUrl']};
		if(selUrl == ""){//默认选中url
			selMenu = "baseUrl0";
		}
		$("#li_menu_"+selMenu).addClass("active open");
		$("#ul_menu_"+selMenu).removeClass("closed").attr("style","display: block");
		$("#li_url_"+selUrl).addClass("active");
	})
	</script>
	</ul>
	</div>