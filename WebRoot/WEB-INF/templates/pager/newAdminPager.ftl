<#setting number_format="0"> 
<#macro pager_url_link linkurl listpn pn>
	<#if listpn == pn>
		<span class="current">${listpn}</span>
		<#else>
		<a href="${linkurl?replace('#pageNo',listpn)}">${listpn}</a>
	</#if>
</#macro>
<#if _pager??>
 共${_pager.pageCount}条纪录，当前第${_pager.pageNo}/${_pager.pageSize}页，每页${_pager.perPage}条纪录&nbsp; &nbsp; 
 <#if _pager.pageNo gt 1>
	<a href="${_pagerUrl?replace('#pageNo',1)}">首页</a>
	<a href="${_pagerUrl?replace('#pageNo',_pager.pageNo-1)}">上一页</a>
<#else>
	<a href="javascript:void(0)">首页</a>
	<a href="javascript:void(0)">上一页</a>
</#if>
<#if _pager.pageNo lt _pager.pageSize>
	<a href="${_pagerUrl?replace('#pageNo',_pager.pageNo+1)}">下一页</a>
	<a href="${_pagerUrl?replace('#pageNo',_pager.pageSize)}">末页</a>
<#else>
	<a href="javascript:void(0)">下一页</a>
	<a href="javascript:void(0)">末页</a>
</#if>  
  跳转：<input id="turnPageNo" name="turnPageNo" type="text" class="ym" value="${_pager.pageNo}" /> 页
<input type="button" onclick="javascript:if(document.getElementById('turnPageNo').value==''){document.getElementById('turnPageNo').focus();alert('请输入要跳转的页数!');return;}if(document.getElementById('turnPageNo').value > 0 && document.getElementById('turnPageNo').value <= ${_pager.pageSize}) {location.href='${_pagerUrl}'.replace(/#pageNo/g,document.getElementById('turnPageNo').value);} else {document.getElementById('turnPageNo').value='${_pager.pageNo}';alert('请输入正确的页数!');}" class="go" value="GO" />
</#if>
