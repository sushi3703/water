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
	var doRegister = function(){
		var v_email = $("#email").val();
		//非空验证
		//密码确认验证
		//邮箱验证
		$.get("${pageContext.request.contextPath}/user/validate_email.action",{"email":v_email},function(data){
			if(data == "suc"){
				$.post("${pageContext.request.contextPath}/user/do_register.action",$("#form_register").serialize(),function(data){
					if(data == "suc"){
						location.href = "${pageContext.request.contextPath}/front/login/index.action";
					}else{
						alert(data);
					}
				});
			}else{
				alert(data);
			}
		});
		
	};
</script>
</head>
<body>
<p>创建新账户</p>
<c:if test="${!empty errorMsg}">
	<font color="red">${errorMsg}</font>
</c:if>
<form id="form_register">
<input type="hidden" name="type" value="${type}" />
<input type="hidden" name="teamId" value="${teamId}" />
<p>邮箱：<input type="text" name="email" id="email" /><span>邮箱作为登录账号</span></p>
<p>密码：<input type="password" name="upwd" id="upwd" /><span>请输入密码</span></p>
<p><input type="button" onclick="doRegister()" value="注册" /></p>
</form>

</body>
</html>