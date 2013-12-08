<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录-QQ登录</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/tool/jquery/jquery.min.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
<c:if test="${!empty errorMsg}">
	<font color="red">${errorMsg}</font>
</c:if>
<form action="${pageContext.request.contextPath}/login/bind_sns_account.action" method="post" id="bindForm">
<input type="hidden" name="qqOpenId" value="${qqOpenId}" />
<input type="hidden" name="qqAccessToken" value="${qqAccessToken}" />
<p>用户名：<input type="text" name="uname" /></p>
<p>密码：<input type="password" name="upwd" /></p>
<p><input type="submit" value="绑定账号" /></p>
</form>
<br/><br/>

</body>
</html>