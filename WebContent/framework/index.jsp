<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>XXXX管理平台</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/easyui/css/default.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/easyui/js/themes/gray/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/easyui/js/themes/icon.css" />
    <script type="text/javascript" src="<%=basePath%>js/easyui/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/easyui/js/jQuery.easyui.js"></script>
	<script type="text/javascript" src='<%=basePath%>js/easyui/js/outlook2.js'> </script>
	<style type="text/css">
		.comeback{display:block; float:left; margin-left:10px;}
	</style>
    <script type="text/javascript">
	 var _menus = {"menus":[
						{"menuid":"1","icon":"icon-sys","menuname":"系统管理",
							"menus":[{"menuname":"菜单管理","icon":"icon-nav","url":"http://www.16sucai.com"},
									{"menuname":"添加用户","icon":"icon-add","url":"demo.html"},
									{"menuname":"用户管理","icon":"icon-users","url":"demo2.html"},
									{"menuname":"角色管理","icon":"icon-role","url":"demo2.html"},
									{"menuname":"权限设置","icon":"icon-set","url":"demo.html"},
									{"menuname":"系统日志","icon":"icon-log","url":"demo.html"}
								]
						},{"menuid":"8","icon":"icon-sys","menuname":"员工管理",
							"menus":[{"menuname":"员工列表","icon":"icon-nav","url":"demo.html"},
									{"menuname":"视频监控","icon":"icon-nav","url":"demo1.html"}
								]
						},{"menuid":"56","icon":"icon-sys","menuname":"部门管理",
							"menus":[{"menuname":"添加部门","icon":"icon-nav","url":"demo1.html"},
									{"menuname":"部门列表","icon":"icon-nav","url":"demo2.html"}
								]
						},{"menuid":"28","icon":"icon-sys","menuname":"财务管理",
							"menus":[{"menuname":"收支分类","icon":"icon-nav","url":"demo.html"},
									{"menuname":"报表统计","icon":"icon-nav","url":"demo1.html"},
									{"menuname":"添加支出","icon":"icon-nav","url":"demo.html"}
								]
						},{"menuid":"39","icon":"icon-sys","menuname":"商城管理",
							"menus":[{"menuname":"商品分","icon":"icon-nav","url":"/shop/productcatagory.aspx"},
									{"menuname":"商品列表","icon":"icon-nav","url":"/shop/product.aspx"},
									{"menuname":"商品订单","icon":"icon-nav","url":"/shop/orders.aspx"}
								]
						}
				]};
       
        $(function() {        

            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
                    if (r) {
                        $.messager.alert("操作提示","已经退出了","info");
                    }
                });
            })
			
			//默认3秒后删除“欢迎使用”的tab标签
			var t=setTimeout("$('#tabs').tabs('close','欢迎使用')",3000);
        });
    </script>
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
	<noscript> <!--元素用来定义在脚本未被执行时的替代内容-->
		<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
			<img src="<%=basePath%>images/easyui/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	
	<!--顶部-->
    <div region="north" split="true" border="false" style="overflow: hidden; height: 100px; background-color:#000000;line-height: 20px;color: #fff; font-family: Verdana,微软雅黑,黑体;">
        <div style="width:250px; float:right;" class="head">
			<a class="comeback">欢迎admin回来</a>
			<a href="#" id="editpass" class="comeback">修改密码</a> 
			<a href="#" id="loginOut" class="comeback">安全退出</a>
		</div>
        <span style="padding-left:10px; font-size: 16px;"><img src="<%=basePath%>images/easyui/images/blocks.gif" width="20" height="20" align="absmiddle" />一看就特别牛B的公司</span>
    </div>
	
	<!--底部-->
    <div region="south" split="true" style="height: 30px; background:#D2E0F2; ">
        <div class="footer">By 行走的火焰 Email:lxq@59ibox.cn</div>
    </div>
	
	<!--菜单项-->
    <div region="west" split="true" title="导航菜单" style="width:180px;" id="west">
		<div class="easyui-accordion" fit="true" border="false">
				
		</div>
    </div>
	
	<!--主页面-->
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" style="padding:20px;overflow:hidden;" id="home">

			<h1>Welcome to jQuery UI!</h1>

			</div>
		</div>
    </div>
    
    <!--关闭标签页的功能-->
    <div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>

</body>
</html>