<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="login.css">
<link rel="stylesheet" href="icon.css">
<title>欢迎登录</title>
</head>
<body onload="init()">
<% String rePswMsg =(String) request.getAttribute("pswError");
   String reUserMsg = (String) request.getAttribute("userMsg");
%>
	<canvas id="cvs"></canvas>
	<div id="login">
		<form action="UserServlet" method="post">
			<input type="text" hidden name="step" value="login">
			<table align="center">
				<tr><td colspan="2" align="center"><h1>登录</h1></td></tr>
				<tr>
					<td><i class="iconfont">&#xe61c;</i></td>
					<td><input type="text" name="username" class="username">
					<div class="add errorName"></div>
					<% if(rePswMsg!=null){ %>
						<div class="add"><%=rePswMsg %></div>
					<%} %></td>
					
				</tr>
				<tr>
					<td ><i class="iconfont">&#xe64c;</i></td>
					<td><input type="password" name="password" class="psw">
					<div class="add errorPsw"></div>
					<% if(reUserMsg!=null){ %>
						<div class="add"><%=reUserMsg %></div>
					<%} %></td>
					
				</tr>
				<tr><td colspan="2" align="center"><button type="submit" class="btn">登录</button></td></tr>
			</table>
		</form>
		<div id="skip-register"><a href="regist.jsp">未注册？点击注册</a></div>
	</div>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="login.js"></script>
<script>
	$(function(){
		var is=true;
		$('.btn').click(function(){
			var $userName=$('.username').val();
			var $password=$('.psw').val();
			if($userName==''){
				$('.errorName').html('用户名不能为空');
				is=false;
			}else{
				$('.errorName').html('');
				is=true;
			}
			if($password==''){
				$('.errorPsw').html('密码不能为空');
				is=false;
			}else{
				$('.errorPsw').html('');
				is=true;
			}
			
			if(!is){
				return false;
			}
		});
	}); 
</script>
</body>
</html>