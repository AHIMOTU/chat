<%@page import="entity.User"%>
<%@page import="javax.jws.soap.SOAPBinding.Use"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="main.css">
<link rel="stylesheet" href="icon.css">
</head>
<body onload="haoyou()">
<% User lu=(User)session.getAttribute("loginUser"); 
	if(lu==null){
		response.sendRedirect("login.jsp");
		return;
	}
%>
<div id="Wrap">
	<div id="top">
		<div class="head-img">
			<img alt="" src="">
			<div class="head-img-1">
				<ul>
					<li><a href="edit.jsp">设置</a></li>
					<li><a href="UserServlet?step=loginOut">退出</a></li>
				</ul>
			</div>
			<span style="position:absolute;right:-70px;top:20px;">欢迎<%=lu.getUsername() %></span>
		</div>
		
		<div class="effect">
			<ul>
				<li><a href="#">聊天</a></li>
				<li><a href="#">好友</a></li>
				<li><a href="#">朋友圈</a></li>
			</ul>
		</div>
	</div>
	<div id="bottom">
		<div class="left">
			<div id="chat" class="list">chat</div>
			<div id="friend" class="list active">
				<div id="w-search">
						<input class="search" type="text" placeholder="输入用户名..." name="searchFriend" onkeyup="search(this)">
				</div>
				<div id="search-i"><i class="iconfont">&#xe602;</i></div>
				<div class="allFriend">
					<ul></ul>
				</div>
				<div class="friend-list">
					<ul></ul>
				</div>
				<div>
					<table>
						
					</table>
				</div>
			</div>
			<div id="party" class="list">此功能将在以后开放！</div>
		</div>
		<div class="right">
			<div class="up">
				<div class="receiver" style="height:30px;width:100%;line-height:30px;text-align:center;background:#D0D0D0;"></div>
				<div id="chat-bg" style="width:100%;height:350px;overflow:auto;">
					 <table cellspacing="20"></table>
				</div>
			</div>
			<div class="down">
				<form>
					<textarea id="textarea" class="textarea" placeholder="输入聊天内容..."></textarea>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
$('#textarea').keyup(function(event){
	if(event.keyCode==13){
		var $val=$(this).val();
		var $rece=$('.receiver').text();
		$(this).val('');
		$.ajax({
			url:"MessageSevrvlet",
			type:"post",
			data:{
				"receiver":$rece,
				"content":$val
			}, 
			success:function(data){
				
			}
		})
	}
})
$('#search-i').click(function(){
	$('.friend-list').hide();
	$('.search').val('');
})
$('#friend input').keyup(function(){
	$('.friend-list').show();
	if($(this).val()==''){
		$('.friend-list').hide();
	}
})
$('#friend input').click(function(){
	$('.friend-list').show();
})
function search(se){
	var s=$(se).val();
	if(s!=''){
		$.ajax({
			url:"FriendServlet",
			type:"post",
			data:{
				"searchFriend":s,
				"step":"search"
			},
			success:function(data){
				var jsonData=$.parseJSON(data);
				$('.friend-list ul').html("");
				for(var i=0;i<jsonData.length;i++){
					var sea=$('<li><div id="head"><i class="iconfont">&#xe61c;</i></div><div id="con"><div>'+jsonData[i].username+'</div><div class="mail">'+jsonData[i].mail+'</div></div><div id="more2"><i onclick="add(this);" class="iconfont">&#xe74a;</i></div></li>')
					$('.friend-list ul').append(sea);
				}
			}
		})
	}
}


	function add(btn){
		$('.allFriend ul').html('');
		var friendName=$(btn).parent().prev().children().eq(0).text();
		
		alert("确认添加？");
		$.ajax({
			url:"FriendServlet?friendname="+friendName,
			type:"get",
			success:function(data){
				var jsonData2=$.parseJSON(data);
					for(var i=0;i<jsonData2.length;i++){
							var add=$('<li><div id="head"><i class="iconfont">&#xe61c;</i></div><div id="con"><div>'+jsonData2[i].username+'</div><div class="mail">'+jsonData2[i].mail+'</div></div><div id="more"><i onclick="sendMessage(this);" class="iconfont">&#xe6d3;</i></div></li>')
							$('.allFriend ul').append(add);
					}
			}
		})
	}
	
	
	
	
	
	function sendMessage(s){
		$('.up table').html("");
		var rece=$(s).parent().prev().children().eq(0).text();
		$('.receiver').html(rece);
		window.setInterval(function(){
			$.ajax({
				url:"MessageSevrvlet?receiver="+rece,
				type:"get",
				success:function(data){
					
					var $data=$.parseJSON(data);
					
					var k;
					$('.up table').html("");
					for(var key in $data){
						if($data[key].sender==rece){
							k=$('<tr><td style="background:#ccc;border-radius:20px;opacity:0.8;"><div>发送者：'+$data[key].sender+'</div><div style="font-weight:bold;">'+$data[key].content+'</div><div>接受者：'+$data[key].receiver+'</div></td><td></td></tr>');
						}else if(("欢迎"+$data[key].sender)==$('.head-img span').text()){
							k=$('<tr><td></td><td style="background:#ccc;border-radius:20px;opacity:0.8;"><div>发送者：'+$data[key].sender+'</div><div style="font-weight:bold;">'+$data[key].content+'</div><div>接受者：'+$data[key].receiver+'</div></td></tr>');
						}
						$('.up table').append(k);
					}
				}
			});
		},2000)
	}
	
	function haoyou(){
		$.ajax({
			url:"FriendServlet",
			type:"post",
			data:{
				"step":"friend"
			},
			success:function(data){
				var jsonData2=$.parseJSON(data);
				for(var i=0;i<jsonData2.length;i++){
						var add=$('<li><div id="head"><i class="iconfont">&#xe61c;</i></div><div id="con"><div>'+jsonData2[i].username+'</div><div class="mail">'+jsonData2[i].mail+'</div></div><div id="more"><i onclick="sendMessage(this);" class="iconfont">&#xe6d3;</i></div></li>')
						$('.allFriend ul').append(add);
				}
			}
		})
	}
	
	
	
	
	
	$('.head-img').hover(function(){
		$('.head-img-1').show();
	},function(){
		$('.head-img-1').hide();
	});
	$('#top .effect a').each(function(index,value){
		$(this).click(function(){
			$('#bottom .left .list').removeClass('active');
			$('#bottom .left .list').eq(index).addClass('active');
		});
	});

</script>
</html>



















