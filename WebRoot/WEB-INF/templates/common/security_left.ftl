<#assign kuakao=JspTaglibs["/WEB-INF/kuakao-core.tld"] />
document.write("<div class='navi'>");
document.write("<ul class='main-nav'>");
<#if menuMap?exists>
	<#list menuMap?keys as menuKey>
	
		<#if currentMenuId?exists && currentMenuId == menuKey>
document.write("		<li class='open'>");
		<#else>
document.write("		<li>		");
		</#if>
		
document.write("		<a class='light toggle-collapsed' href='#'><div class='ico'><i class='icon-th-large icon-white'></i></div>");
document.write("		<@kuakao.xmlConfig configName='app_menu_${appId}' key='${menuKey}' />");
document.write("		<img alt='' src='http://cachecss.kuakao.com/public/ui/img/toggle-subnav-down.png'>");
document.write("		</a>");
		
		<#if currentMenuId?exists && currentMenuId == menuKey>
document.write("		<ul class='collapsed-nav closed' style='display: block'>");
		<#else>
document.write("		<ul class='collapsed-nav closed' style='display: none'>");
		</#if>
		<#if menuMap[menuKey]?exists>
		<#list menuMap[menuKey] as url>
document.write("			<li>");
document.write("				<a href='<@kuakao.urlconfig keyName="security.${appId}"/>${url.urlPath}?currentAppId=${appId}&menuId=${url.urlMenu}&urlId=${url.urlId}&${url.urlParam}' style='margin-left: 0px;'>");
		<#if currentUrlId?exists && currentUrlId == url["urlId"]>
document.write("				<b>${url.urlName}</b>");
		<#else>
document.write("				${url.urlName}");		
		</#if>
document.write("				</a>");
document.write("			</li>");
		</#list>
		</#if>
document.write("		</ul>");

document.write("		</li>");
	</#list>
</#if>
document.write("</ul>");
document.write("</div>");