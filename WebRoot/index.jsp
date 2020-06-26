<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>汽车维修管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- CSS -->
<link rel="stylesheet" href="<%=path%>/css/themes/login.css">
<!-- Javascript -->
<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.js"></script>
</head>

<body>
	<div class="container">
		<div class="content">
			<div class="denglu">
				<div class="shurukuang">
					<div width="320" border="0" cellspacing="0" cellpadding="0"
						style="width: 200px; margin-left: 70px;">
						<div height="28" style="margin-top: 210px;">
							用户名：<input name="loginName" type="text" class="shuru01"
								id="loginName" value=""></input>
						</div>
						<div height="28">
							密码：<input type="password" name="password" type="text"
								class="shuru02" id="password" value=""></input>
						</div>
						<div height="28" align="left" style="margin-top: 20px;">
							<button class="queding"  id="login">登录</button>
						</div>

						<div style="padding-right:8px;height:40;margin-top: 60px;"
							align="right">版本号：V1.0.01</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="<%=path%>/js/index/login.js"></script>
	<script type="text/javascript">
		var path ='<%=path%>';
	</script>
</body>
</html>
