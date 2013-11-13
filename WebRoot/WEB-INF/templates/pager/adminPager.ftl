<#setting number_format="0"> 
<#macro pager_url_link linkurl listpn pn>
	<#if listpn == pn>
		<span class="current">${listpn}</span>
		<#else>
		<a href="${linkurl?replace('#pageNo',listpn)}">${listpn}</a>
	</#if>
</#macro>
<#if _pager??>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="http://img.kuakao.net/images/back/tab_20.gif" width="15" height="29" /></td>
        <td background="http://img.kuakao.net/images/back/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="25%" height="29" nowrap="nowrap"><span class="STYLE1">共${_pager.pageCount}条纪录，当前第${_pager.pageNo}/${_pager.pageSize}页，每页${_pager.perPage}条纪录</span></td>
            <td width="75%" valign="top" class="STYLE1"><div align="right">
              <table width="352" height="20" border="0" cellpadding="0" cellspacing="0">
                <tr>
                <#if _pager.pageNo gt 1>
					<td width="62" height="22" valign="middle"><div align="right"><a href="${_pagerUrl?replace('#pageNo',1)}"><img src="http://img.kuakao.net/images/back/first.gif" width="37" height="15" /></a></div></td>
					<td width="50" height="22" valign="middle"><div align="right"><a href="${_pagerUrl?replace('#pageNo',_pager.pageNo-1)}"><img src="http://img.kuakao.net/images/back/back.gif" width="43" height="15" /></a></div></td>
				<#else>
					<td width="62" height="22" valign="middle"><div align="right"><img src="http://img.kuakao.net/images/back/first.gif" width="37" height="15" /></div></td>
					<td width="50" height="22" valign="middle"><div align="right"><img src="http://img.kuakao.net/images/back/back.gif" width="43" height="15" /></div></td>
				</#if>
                
                
                <#if _pager.pageNo lt _pager.pageSize>
					<td width="54" height="22" valign="middle"><div align="right"><a href="${_pagerUrl?replace('#pageNo',_pager.pageNo+1)}"><img src="http://img.kuakao.net/images/back/next.gif" width="43" height="15" /></a></div></td>
					<td width="49" height="22" valign="middle"><div align="right"><a href="${_pagerUrl?replace('#pageNo',_pager.pageSize)}"><img src="http://img.kuakao.net/images/back/last.gif" width="37" height="15" /></a></div></td>
				<#else>
					<td width="54" height="22" valign="middle"><div align="right"><img src="http://img.kuakao.net/images/back/next.gif" width="43" height="15" /></div></td>
					<td width="49" height="22" valign="middle"><div align="right"><img src="http://img.kuakao.net/images/back/last.gif" width="37" height="15" /></div></td>
				</#if>  
                  
                  <td width="59" height="22" valign="middle"><div align="right">转到第</div></td>
                  <td width="25" height="22" valign="middle"><span class="STYLE7">
                    <input id="turnPageNo" name="turnPageNo" type="text" value="${_pager.pageNo}" class="STYLE1" style="height:18px; width:25px;" size="5" />
                  </span></td>
                  <td width="23" height="22" valign="middle">页</td>
                  <td width="30" height="22" valign="middle"><img src="http://img.kuakao.net/images/back/go.gif" onclick="javascript:if(document.getElementById('turnPageNo').value==''){document.getElementById('turnPageNo').focus();alert('请输入要跳转的页数!');return;}if(document.getElementById('turnPageNo').value > 0 && document.getElementById('turnPageNo').value <= ${_pager.pageSize}) {location.href='${_pagerUrl}'.replace(/#pageNo/g,document.getElementById('turnPageNo').value);} else {document.getElementById('turnPageNo').value='${_pager.pageNo}';alert('请输入正确的页数!');}" width="37" height="15" /></td>
                </tr>
              </table>
            </div></td>
          </tr>
        </table></td>
        <td width="14"><img src="http://img.kuakao.net/images/back/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table>
</#if>