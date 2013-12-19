<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/tool/jquery/jquery.min.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
<p>创建新账户</p>
<c:if test="${!empty errorMsg}">
	<font color="red">${errorMsg}</font>
</c:if>
<form action="${pageContext.request.contextPath}/login/do_register.action" method="post">
<p>邮箱：<input type="text" name="email" /><span>邮箱作为登录账号，也可用于找回密码等</span></p>
<p>密码：<input type="password" name="upwd" /><span>请输入密码</span></p>
<p><input type="submit" value="注册" /></p>
</form>

</body>
</html>