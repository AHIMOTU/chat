<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<link rel="stylesheet" href="regist.css">
</head>
<body>
	<%
		String str=(String)request.getAttribute("errorMsg");
	%>
	<div class="container">
		<div class="content">
		<form action="UserServlet" method="post">
			<input type="text" hidden name="step" value="regist">
			<table>
				<tr>
					<td colspan="2" align="center" style="font-size: x-large">注册信息</td>
				</tr>
				<tr>
					<td style="font-size: x-large">用户名</td>
					<td><input class="username" type="text" name="username"/>
						<div class="errorname add"></div>
						<%if(str!=null){ %>
						<div class="errorname add"><%=str %></div>
						<%} %>
					</td>
				</tr>
				<tr>
					<td style="font-size: x-large">密码</td>
					<td><input class="psw" type="password" name="psw"/>
					<div class="errorPsw add"></div></td>
					
				</tr>
				<tr>
					<td style="font-size: x-large">确认密码</td>
					<td><input class="cPsw" type="password" name="cpsw"/>
					<div class="errorPsw add"></div></td></td>
					
				</tr>
				<tr>
					<td style="font-size: x-large">邮箱</td>
					<td><input class="mail" type="email" name="mail"/>
					<div class="errorMail add"></div></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button class="btn" type="submit">提交</button></td>
				</tr>
			</table>
			</form>
			<div id="skip-register"><a href="login.jsp">注册成功？返回登录</a></div>
		</div>
		</div>
		
		<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
		<script type="text/javascript">
			$(function(){
				var is=true;
				$(".btn").click(function(){
					console.log(is);
					var $username=$('.username').val(),
					$psw=$('.psw').val(),
					$cpsw=$('.cPsw').val(),
					$mail=$('.mail').val();
					
					if($username==''){
						$('.errorname').html('用户名不能为空');
						is=false;
					}else{
						$('.errorname').html('');
						is=true;
					}
					
					if($psw==''){
						$('.errorPsw').html('密码不能为空');
						is=false;
					}else{
						$('.errorPsw').html('');
						is=true;
					}	
					if($psw!=$cpsw){
						$('.errorPsw').html('密码不一致');
						is=false;
					}else if($psw!=''){
						$('.errorPsw').html('');
						is=true;
					}
					
					if($mail==''){
						$('.errorMail').html('邮箱不能为空');
						is=false;
					}else{
						$('.errorMail').html('');
						is=true;
					}
					
					if(!is){
						return false;
					}
				});
			})	
		</script>
</body>
</html>