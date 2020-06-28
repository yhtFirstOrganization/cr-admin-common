<!--修改密码信息-->
<#import "../common.ftl" as cc>
<!DOCTYPE HTML>
<html>
<!--头部-->
<@cc.html_head title="修改密码">
	<script type="text/javascript" src="${base}cr-admin/js/DateUtils.js"></script>
</@cc.html_head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal"
			id="formCar">
	
			<div class="row cl">
				<label class="form-label col-4"><span class="c-red">*</span>原登录密码：</label>
				<div class="formControls col-4">
					<input type="password" class="input-text" value="" placeholder=""
						id="oldPassword" name="oldPassword" nullmsg="请填写原登录密码！">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-4"><span class="c-red">*</span>原进价密码：</label>
				<div class="formControls col-4">
					<input type="password" class="input-text" value="" placeholder=""
						id="oldOtherPassword" name="oldPassword" nullmsg="请填写原进价密码！">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-4">新登录密码：</label>
				<div class="formControls col-4">
					<input type="password" class="input-text" value="" placeholder=""
						id="newPassword" name="newPassword" nullmsg="请填写新登录密码！">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-4">新进价密码：</label>
				<div class="formControls col-4">
					<input type="password" class="input-text" value="" placeholder=""
						id="newOtherPassword" name="newOtherPassword" nullmsg="请填写新进价密码！">
				</div>
			</div>
			
			<div class="row cl">
				<div class="col-10 col-offset-2">
					<button class="btn btn-secondary radius" type="submit" id="submitBtn">
					<i class="Hui-iconfont">&#xe632;</i>提交</button>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		$(function() {
			// 设置焦点
			$("#oldPassword").focus();

			// 验证form表单 Validform组件
			var form = $("#formCar")
					.Validform(
							{
								btnSubmit : "#submitBtn",// 设置提交按钮
								tiptype : 2,// 兄弟姐妹中找位置来填充
								postonce : false,// 不开启重复提交校验
								ajaxPost : false,// 不采用组件提供的ajax提交数据（因为组件提供的风格不一致，而且不好控制逻辑处理）
								callback : function(form) {
									var oldPassword = $("#oldPassword").val().trim();// 获取原密码
									var oldOtherPassword = $("#oldOtherPassword").val().trim();// 获取原进价密码
									var newPassword = $("#newPassword").val().trim();// 获取新密码
									var newOtherPassword = $("#newOtherPassword").val().trim();// 获取新进价密码
									if (oldPassword == null || oldPassword == "") {
										layer.alert("原登录密码不可为空，请填写！", {
											icon : 2
										});
										$("#oldPassword").focus();
										return false;
									}
									if (oldOtherPassword == null || oldOtherPassword == "") {
										layer.alert("原进价密码不可为空，请填写！", {
											icon : 2
										});
										$("#oldOtherPassword").focus();
										return false;
									}
									if ((newPassword == null || newPassword == "") && (newOtherPassword == null || newOtherPassword == "")) {
										layer.alert("请填写要修改的密码！", {
											icon : 2
										});
										return false;
									}
									
									$.ajax({
												type : "post",
												url : "/cr-admin/index/editPassword.do",
												dataType: "json",
												data : {
													newPassword : newPassword,
													newOtherPassword : newOtherPassword,
													password : oldPassword,
													otherPassword : oldOtherPassword
												},
												success : function(data) {
													if (data) {
														var code = data.code;
														var msg = data.msg;
														if (code == "200") {
															layer.msg("修改成功",
																			{
																				icon : 1,
																				time : 1000
																			},
																			function() {
																				var index = parent.layer
																						.getFrameIndex(window.name);// 先得到当前iframe层的索引
																				parent.layer
																						.close(index);// 执行关闭
																				return false;
																			});
														} else {
															layer.alert(msg, {
																icon : 2
															});
															return false;
														}
													}
												}
											});
									return false;
								}
							});
		});
		$('.skin-minimal input').iCheck({
			checkboxClass : 'icheckbox-blue',
			radioClass : 'iradio-blue',
			increaseArea : '20%'
		});
	</script>
</body>
</html>