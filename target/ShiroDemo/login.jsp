<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
	h3{
		text-align: center;
	}
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<h3>登录</h3>
			<form id="loginForm" action="login.do" method="post">
				<div class="form-group">
					<input type="text" class="form-control" name="uname" placeholder="请输入用户名"/>
				</div>
				<div class="form-group">
					<input type="password" class="form-control" name="upwd" placeholder="请输入密码"/>
				</div>
				<div class="form-group">
					<input type="checkbox" name="rememberMe"/> 记住我
				</div>
				<div class="form-group">
					<input id="submit" type="button" class="form-control btn btn-info" value="提交"/>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
$().ready(function(){
	$("#submit").click(function(){
		$('input[name="rememberMe"]:checked').each(function() {
			$('input[name="rememberMe"]').val("true");
		});
		var form = $("#loginForm").serialize();
		alert(form);
		$.ajax({
			type:"post",
			url:"login.do",
			async:true,
			data:form,
			dataType:"json",
			success:function(data){
				if(data.message=="登录成功！"){
					window.location.href = "index.jsp";
				}else{
					alert(data.message);
				}
			}
		});
	})
})
</script>
</body>
</html>