<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>修改基本信息</title>
</head>
<body>
<p>修改基本信息</p>
<c:if test="${!empty errorMsg}">
   <font color="red">${errorMsg}</font>
</c:if>
<form action="${pageContext.request.contextPath}/front/user/do_update_user_base.action" method="post">
<input type="hidden" name="userId" value="${userBaseInfo.userId}" />
<p>邮箱：<input type="text" name="" value="${userBaseInfo.email}" disabled="disabled" /></p>
<p>用户名：<input type="text" name="uname" value="${userBaseInfo.uname}" /></p>
<p>部门：<input type="text" name="department" value="${userBaseInfo.department}" /></p>
<p>头衔：<input type="text" name="jobTitle" value="${userBaseInfo.jobTitle}" /></p>
<p>联系电话：<input type="text" name="mobile" value="${userBaseInfo.mobile}" /></p>
<p>联系QQ：<input type="text" name="qq" value="${userBaseInfo.qq}" /></p>
<input type="submit" value="保存" />
</form>
<p><a href="${pageContext.request.contextPath}/front/login/index.action">返回首页</a></p>
</body>
</html>
