<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<style type="text/css">
	#wrap{
		width:400px;
		height:400px;
		margin:200px auto;
		background:#ccc;
	}
	#wrap table td{
		height:100px;
	}
	#wrap table input{
		height:40px;
		width:250px;
	}
</style>
</head>
<body>
<% String error=(String)request.getAttribute("error"); %>
	<div id="wrap">
		<form action="UserServlet" method="post">
			<input type="text" hidden value="edit" name="step">
			
			<table>
				<tr>
					<td>输入旧密码：</td>
					<td><input type="password" name="oldPsw"></td>
				</tr>
				<tr>
					<td>输入新密码：</td>
					<td><input type="password" name="editPsw"></td>
				</tr>
				<tr>
					<td>确认新密码：</td>
					<td><input type="password"></td>
				</tr>
				<% if(error!=null){ %>
				<tr><td style="color:red;"><%=error %></td></tr>
				<%} %>
				<tr><td colspan="2" align="center"><button type="submit">保存</button></td></tr>
			</table>
			
		</form>
	</div>
</body>
</html>