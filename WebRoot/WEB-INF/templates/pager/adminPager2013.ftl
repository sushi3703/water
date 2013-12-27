<#if _pager??>
<script type="text/javascript">
	var gotoPage = function(){
		if(document.getElementById('turnPageNo').value==''){
			document.getElementById('turnPageNo').focus();
			alert('请输入要跳转的页数!');
			return;
		}
		if(document.getElementById('turnPageNo').value > 0 && document.getElementById('turnPageNo').value <= ${_pager.pageSize}) {
			location.href='${_pagerUrl}'.replace(/#pageNo/g,document.getElementById('turnPageNo').value);
		} else {
			document.getElementById('turnPageNo').value='${_pager.pageNo}';
			alert('请输入正确的页数!');
		}
	}
</script>
  <ul>
  	<li><span style="margin-right:5px;">共${_pager.pageCount}条纪录，当前第${_pager.pageNo}/${_pager.pageSize}页，每页${_pager.perPage}条纪录</span></li>
  	<#-- 前一页begin -->
  	<#if _pager.pageNo gt 1>
  		<li><a href="${_pagerUrl?replace('#pageNo',_pager.pageNo-1)}"><span>Prev</span></a></li>
  	<#else>
    	<li class="disabled"><span>Prev</span></li>
    </#if>
    <#-- 前一页end -->
    <#-- 固定前三页begin -->
    <#if _pager.pageNo == 1>
  		<li class="active"><span>1</span></li>
  	<#else>
    	<li><a href="${_pagerUrl?replace('#pageNo',1)}">1</a></li>
    </#if>
    
    <#if _pager.pageSize gt 1>
    	<#if _pager.pageNo == 2>
  			<li class="active"><span>2</span></li>
  		<#else>
    		<li><a href="${_pagerUrl?replace('#pageNo',2)}">2</a></li>
    	</#if>
    </#if>
    
    <#if _pager.pageSize gt 2>
    	<#if _pager.pageNo == 3>
  			<li class="active"><span>3</span></li>
  		<#else>
    		<li><a href="${_pagerUrl?replace('#pageNo',3)}">3</a></li>
    	</#if>
    </#if>
    <#-- 固定前三页end -->
    
    <#if _pager.pageNo gt 6>
    	<li><span>..</span></li>
    </#if>
    
    <#-- 当前页面的前两页begin -->
    <#if _pager.pageSize - _pager.pageNo gt 0 && _pager.pageNo gt 5>
    	<li><a href="${_pagerUrl?replace('#pageNo',_pager.pageNo-2)}">${_pager.pageNo-2}</a></li>
    </#if>
    <#if _pager.pageSize - _pager.pageNo gt 1 && _pager.pageNo gt 4>
    	<li><a href="${_pagerUrl?replace('#pageNo',_pager.pageNo-1)}">${_pager.pageNo-1}</a></li>
    </#if>
    <#-- 当前页面的前两页end -->
    
    <#if _pager.pageNo gt 3 && _pager.pageSize-_pager.pageNo gt 2>
    	<li class="active"><span>${_pager.pageNo}</span></li>
    </#if>
    
    <#-- 当前页面的后两页begin -->
    <#if _pager.pageNo gt 2 && _pager.pageSize - _pager.pageNo gt 3>
    	<li><a href="${_pagerUrl?replace('#pageNo',_pager.pageNo+1)}">${_pager.pageNo + 1}</a></li>
    </#if>
    <#if _pager.pageNo gt 1 && _pager.pageSize - _pager.pageNo gt 4>
    	<li><a href="${_pagerUrl?replace('#pageNo',_pager.pageNo+2)}">${_pager.pageNo + 2}</a></li>
    </#if>
    <#-- 当前页面的后两页end -->
    	
    <#if _pager.pageSize - _pager.pageNo gt 5>
    	<li><span>..</span></li>
    </#if>
    
    <#-- 固定后三页begin -->
    <#if _pager.pageSize gt 5>
    	<#if _pager.pageSize - _pager.pageNo == 2>
    		<li class="active"><span>${_pager.pageSize-2}</span></li>
  		<#else>
    		<li><a href="${_pagerUrl?replace('#pageNo',_pager.pageSize-2)}">${_pager.pageSize-2}</a></li>
    	</#if>
    </#if>
    
    <#if _pager.pageSize gt 4>
    	<#if _pager.pageSize - _pager.pageNo == 1>
    		<li class="active"><span>${_pager.pageSize-1}</span></li>
  		<#else>
    		<li><a href="${_pagerUrl?replace('#pageNo',_pager.pageSize-1)}">${_pager.pageSize-1}</a></li>
    	</#if>
    </#if>
    
    <#if _pager.pageSize gt 3>
    	<#if _pager.pageSize == _pager.pageNo>
    		<li class="active"><span>${_pager.pageSize}</span></li>
  		<#else>
    		<li><a href="${_pagerUrl?replace('#pageNo',_pager.pageSize)}">${_pager.pageSize}</a></li>
    	</#if>
    </#if>
    <#-- 固定后三页end -->
    
    <#-- 后一页begin -->
    <#if _pager.pageNo lt _pager.pageSize>
    	<li><a href="${_pagerUrl?replace('#pageNo',_pager.pageNo+1)}"><span>Next</span></a></li>
    <#else>
    	<li class="disabled"><span>Next</span></li>
    </#if>
    <#-- 后一页end -->
    
    <#-- 直接跳转begin -->
    <li>
    	<input id="turnPageNo" name="turnPageNo" type="text" value="${_pager.pageNo}" style="width:30px;height:30px;float:left;margin-left:5px;" />
    	<a href="javascript:gotoPage();">GO</a>
    </li>
    <#-- 直接跳转end -->
  </ul>
</#if>