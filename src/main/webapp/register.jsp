<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			<h3>注册</h3>
			<form id="registerForm" action="register.do" method="post">
				<div class="form-group">
					<input type="text" class="form-control" name="uname" placeholder="请输入用户名"/>
				</div>
				<div class="form-group">
					<input type="password" class="form-control" name="upwd" placeholder="请输入密码"/>
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
		var form = $("#registerForm").serialize();
		$.ajax({
			type:"post",
			url:"register.do",
			async:true,
			data:form,
			dataType:"json",
			success:function(data){
				if(data.message=="注册成功！"){
					window.location.href = "login.jsp";
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