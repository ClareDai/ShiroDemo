<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
		<a class="btn btn-danger" href="logout">退出登录</a><br>
	<div class="panel panel-default">
	  <div class="panel-heading">
	  	<h3 class="panel-title">shiro在JSP页面中的用法</h3>
	  </div>
	  <div class="panel-body">
	  	<shiro:authenticated>用户已经登录显示此内容</shiro:authenticated><br>      
		<shiro:hasRole name="manager">manager角色登录显示此内容</shiro:hasRole><br>      
		<shiro:hasRole name="admin">admin角色登录显示此内容</shiro:hasRole><br>      
		<shiro:hasRole name="normal">normal角色登录显示此内容</shiro:hasRole><br>            
		<shiro:hasAnyRoles name="manager,admin">** manager or admin 角色用户登录显示此内容**</shiro:hasAnyRoles><br>      
		<shiro:principal/>显示当前登录用户名 <br>    
		<shiro:hasPermission name="add">add权限用户显示此内容</shiro:hasPermission><br>      
		<shiro:hasPermission name="query">query权限用户显示此内容</shiro:hasPermission><br>      
		<shiro:lacksPermission name="del"> 不具有user:del权限的用户显示此内容 </shiro:lacksPermission><br>
	  </div>
	</div>
	<div class="panel panel-default">
	  <div class="panel-heading">
	    <h3 class="panel-title">shiro在controller中的用法</h3>
	  </div>
	  <div class="panel-body">
	    <button id="query" type="button" class="btn btn-primary">查看操作</button>
		<button id="add" type="button" class="btn btn-primary">添加操作</button>
		<button id="update" type="button" class="btn btn-primary">修改操作</button>
		<button id="del" type="button" class="btn btn-primary">删除操作</button>
		<button id="admin" type="button" class="btn btn-warning">admin角色操作</button>
		<button id="manager" type="button" class="btn btn-warning">manager角色操作</button>
		<button id="normal" type="button" class="btn btn-warning">normal角色操作</button>
	  </div>
	</div>
</div>
<script type="text/javascript">
$().ready(function(){
	$("#query").click(function(){
		window.location.href="query.do";
	});
	$("#add").click(function(){
		window.location.href="add.do";
	});
	$("#update").click(function(){
		window.location.href="update.do";
	});
	$("#del").click(function(){
		window.location.href="del.do";
	});
	$("#admin").click(function(){
		window.location.href="roleAdmin.do";
	});
	$("#manager").click(function(){
		window.location.href="roleManager.do";
	});
	$("#normal").click(function(){
		window.location.href="roleNormal.do";
	});
});
</script>
</body>
</html>