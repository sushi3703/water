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
	var bindAccount = function(){
		$.post("${pageContext.request.contextPath}/login/validate_user_pwd.action",
			{"uname":$("#username").val(),"upwd":$("#password").val()},
			function(data){
				var dataJson = $.parseJSON(data);
			if(!!dataJson['suc']) {
				//alert("suc");
				$("#userId").val(dataJson['userId']);
				$("#bindForm").submit();
			}else{
				alert(data);
			}
		});
	}
</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/login/bind_sns_account.action" method="post" id="bindForm">
<input type="hidden" name="qqOpenId" value="${openID}" />
<input type="hidden" name="qqAccessToken" value="${accessToken}" />
<input type="hidden" name="userId" id="userId" />
</form>
<p>用户名：<input type="text" name="username" id="username" /></p>
<p>密码：<input type="password" name="password" id="password" /></p>
<p><input type="button" value="绑定账号" onclick="bindAccount()" /></p>
<br/><br/>

</body>
</html>