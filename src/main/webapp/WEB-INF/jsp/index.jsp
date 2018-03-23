<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品管理系统后台主页</title>
</head>
<body>
<h3>商品管理系统后台主页</h3>

当前用户名：${sessionScope.userName}

<hr/>
<shiro:hasPermission name="product:add">
<a href="${pageContext.request.contextPath}/product/toAdd">商品添加</a><br/>
</shiro:hasPermission>
<shiro:hasPermission name="product:update">
<a href="${pageContext.request.contextPath}/product/toUpdate">商品修改</a><br/>
</shiro:hasPermission>
<shiro:hasPermission name="product:list">
<a href="${pageContext.request.contextPath}/product/toList">商品列表</a><br/>
</shiro:hasPermission>

</body>
</html>