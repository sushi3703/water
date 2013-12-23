<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>修改密码</title>
<link href="${pageContext.request.contextPath}/tool/bootstrap/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/tool/bootstrap/signin.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="container">

      <form class="form-signin" action="${pageContext.request.contextPath}/front/user/do_update_pwd.action" method="post">
        <h2 class="form-signin-heading">修改密码</h2>
        <c:if test="${!empty errorMsg}">
          <font color="red">${errorMsg}</font>
        </c:if>
        <c:if test="${!empty showMsg}">
          <font color="green">${showMsg}</font>
        </c:if>
        <input type="text" readonly="readonly" class="form-control" placeholder="${userLoginBaseInfo.uname}" >
        <input name="oldPwd" type="password" class="form-control" placeholder="原密码">
        <input name="newPwd" type="password" class="form-control" placeholder="新密码">
        <button class="btn btn-lg btn-primary btn-block" type="submit">修改</button>
        <br/>
        <a href="${pageContext.request.contextPath}/front/login/index.action">返回首页</a>
      </form>
	
    </div> <!-- /container -->

</body>
</html>
