<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>欢迎光临_宜春院</title>
	<script src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script>
		$(function(){
			$("#submitBtn").click(function(){
				$.ajax({
					url:"<%=basePath%>framework/user/add.action", 
					type:"post",
					data:{
						"userName":$("#userName").val(),
						"age":$("#age").val()	
					},
					dataType:"json",
					success:function(data){
						console.log(data);
					},
					error:function(e){
						console.error(e);
					}
				});
			});	
			
			$("#deleteBtn").click(function(){
				$.ajax({
					url:"<%=basePath%>framework/user/delet.action", 
					type:"post",
					data:{
						"id":"402880ea5b0db477015b0db52ac30000"
					},
					dataType:"json",
					success:function(data){
						console.log(data);
					},
					error:function(e){
						console.error(e);
					}
				});
			});
			
		});
		
		function ajax() {
	          //先声明一个异步请求对象
	          var xmlHttpReg = null;
	          if (window.ActiveXObject) {//如果是IE
	              xmlHttpReg = new ActiveXObject("Microsoft.XMLHTTP");
	          } else if (window.XMLHttpRequest) {
	              xmlHttpReg = new XMLHttpRequest(); //实例化一个xmlHttpReg
	          }

	          //如果实例化成功,就调用open()方法,就开始准备向服务器发送请求
	          if (xmlHttpReg != null) {
	              xmlHttpReg.open("get", "<%=basePath%>framework/user/add.action?userName=\"asdasd\"&age=\"1111\"", true);
	              xmlHttpReg.send(null);
	              xmlHttpReg.onreadystatechange = doResult; //设置回调函数
	          }

	          //回调函数
	          //一旦readyState的值改变,将会调用这个函数,readyState=4表示完成相应

	          //设定函数doResult()
	          function doResult() {
	              if (xmlHttpReg.readyState == 4) {//4代表执行完成
	                  if (xmlHttpReg.status == 200) {//200代表执行成功
	                      //将xmlHttpReg.responseText的值赋给ID为resText的元素
	                      document.getElementById("resText").innerHTML = xmlHttpReg.responseText;
	                  }
	              }
	          }
	        

	      }
	</script>
</head>
<body>
	<input type="text" id="userName"/>
	<input type="text" id="age"/>
	<button id="submitBtn">提交</button>
	<button id="deleteBtn">删除</button>
	<input type="button" value="get请求http协议获取返回的数值"  onclick="ajax();"/>
	<div id="resText"></div>
	<h1>请登录。。。</h1>
</body>
</html>