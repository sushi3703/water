<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>请登录</title>
<link href="${pageContext.request.contextPath}/tool/bootstrap/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/tool/bootstrap/signin.css" rel="stylesheet" type="text/css" />
<body>
<div class="container">

      <form class="form-signin" action="${pageContext.request.contextPath}/login/do_login.action" method="post">
      <input type="hidden" name="redirectTo" value="${redirectTo}" />
        <h2 class="form-signin-heading">请登录</h2>
        <c:if test="${!empty errorMsg}">
          <font color="red">${errorMsg}</font>
        </c:if>
        <input name="uname" type="text" class="form-control" placeholder="账号" autofocus>
        <input name="upwd" type="password" class="form-control" placeholder="密码">
        <label class="checkbox">
          <a href="javascript:alert('请联系管理员，重置密码');"> 忘记密码？</a>
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
  <p><br/>
  	<a href="${pageContext.request.contextPath}/login/snsLogin.action?redirectTo=${redirectTo}">
  		<img src="${pageContext.request.contextPath}/img/login_qq.png" />
  	</a>
  </p>
      </form>

</div> <!-- /container -->
</body>
</html>
