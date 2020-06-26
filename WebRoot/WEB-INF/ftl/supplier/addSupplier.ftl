<!--新增汽车信息-->
<#import "../common.ftl" as cc>
<!DOCTYPE HTML>
<html>
<!--头部-->
<@cc.html_head title="新增供应商信息">
<script type="text/javascript" src="${base}cr-admin/js/DateUtils.js"></script>
</@cc.html_head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal"
			id="formSupplier">
			<input type="text" class="input-text" style="display:none"
				value="${supplierInfo.perId!" "}"id="supplierId" name="supplierId">
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>供应商编号：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" readonly="readonly"
						value="${supplierInfo.perNum!" "}" placeholder="" id="supplierNum"
						name="supplierNum">
				</div>
				<span class="Validform_checktip col-2"></span> <label
					class="form-label col-2">供应厂商：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text"
						value="${supplierInfo.perCompanyName!" "}" placeholder=""
						id="supplierCompany" name="supplierCompany">
				</div>
				<span class="Validform_checktip col-2"></span>


			</div>

			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>供应人名称：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text"
						value="${supplierInfo.perName!" "}" placeholder=""
						id="supplierName" name="supplierName" datatype="*1-50"
						errormsg="联系人应在50字以内！" nullmsg="请填写联系人！">
				</div>
				<span class="Validform_checktip col-2"></span> <label
					class="form-label col-2 "><span class="c-red">*</span>联系电话：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text"
						value="${supplierInfo.perTel!" "}" placeholder="" id="supplierTel"
						name="supplierTel" datatype="*1-50" errormsg="联系电话应在50字以内！"
						nullmsg="请填写联系电话！">
				</div>
				<span class="Validform_checktip col-2"></span>
			</div>
			<div class="row cl">

				<label class="form-label col-2">联系邮箱：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text"
						value="${supplierInfo.perEmail!" "}" placeholder=""
						id="supplierEmail" name="supplierEmail">
				</div>
				<span class="Validform_checktip col-2"></span> <label
					class="form-label col-2">其他联系方式：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text"
						value="${supplierInfo.elseTel!" "}" placeholder="" id="elseTel"
						name="elseTel">
				</div>
				<span class="Validform_checktip col-2"></span>
			</div>
			<div class="row cl">

				<label class="form-label col-2 ">联系地址：</label>
				<div class="formControls col-1">
					<span class="select-box"> <select class="select"
						id="s_province" name="s_province">
							<option value="" selected>省</option> <#list provs as prov>
							<option value="${prov.regionId}">${prov.name}</option> </#list>
					</select> </span>
				</div>
				<div class="formControls col-1">
					<span class="select-box"> <select class="select" id="s_city"
						name="s_city">
							<option value="" selected>市</option>
					</select> </span>
				</div>
				<div class="formControls col-1">
					<span class="select-box"> <select class="select"
						id="s_county" name="s_county" dataType="*" nullmsg="请选择地市！"
						ignore="ignore">
							<option value="" selected>区/县</option>
					</select> </span>
				</div>
				<div class="formControls col-5">
					<input type="text" class="input-text"
						value="${supplierInfo.perAddress!" "}" placeholder=""
						id="ownerAddress" name="ownerAddress" datatype="*1-50"
						errormsg="详细住址应在50字以内！" ignore="ignore">
				</div>
			</div>

			<div class="row cl">

				<label class="form-label col-2 ">备注：</label>
				<div class="formControls col-8">
					<textarea class="input-text" style="height: auto;"
						value="${supplierInfo.bz!" "}" placeholder="" id="bz" name="bz"></textarea>
				</div>
				<span class="Validform_checktip col-2"></span>
			</div>
			<div class="row cl" style="display:none;">
				<label class="form-label col-2 "><span class="c-red">*</span>是否启用：</label>
				<div class="formControls col-2 skin-minimal" id="isdeleteDiv">
					<div class="radio-box">
						<input type="radio" value="0" id="isdelete" name="isdelete"
							checked datatype="*" nullmsg="请选择启用类型！" /> <label for="isdelete">启用</label>
					</div>

					<div class="radio-box">
						<input type="radio" value="1" id="isdelete" name="isdelete" /> <label
							for="isdelete">停用</label>
					</div>
				</div>
				<span class="Validform_checktip col-2"></span>
			</div>

			<div class="row cl">
				<div class="col-10 col-offset-2">
					<button class="btn btn-secondary radius" type="submit"
						id="submitBtn">
						<i class="Hui-iconfont">&#xe632;</i>提交
					</button>
				</div>
			</div>
		</form>
	</div>
	</div>
	<!--引入省市区js-->
	<@cc.getChildNode_js></@cc.getChildNode_js>
	<script type="text/javascript"
		src="${base}cr-admin/js/supplier/supplier.js"></script>
	<script type="text/javascript">
		$(function() {
			// 设置省市区级联
			if ("${gid}") {
				$("#s_province option[value=${gid}]").attr("selected", true);
				$("#s_province").change();
				if ("${pid}") {
					$("#s_city option[value=${pid}]").attr("selected", true);
					$("#s_city").change();
					if ("${qxid}") {
						$("#s_county option[value=${qxid}]").attr("selected",
								true);

					}
				}
			}

			// 设置焦点
			$("#supplierNum").focus();

			// 给supplierCompany绑定一个ajax请求校验
			$("#supplierCompany")
					.bind(
							"change",
							function() {
								var supplierCompany = $("#supplierCompany")
										.val().trim();
								var ajaxurl = "/cr-admin/car/checksupplierCompany.do?supplierCompany="
										+ $("#supplierCompany").val().trim();
								$(this).attr("ajaxurl", ajaxurl);
							});

			// 验证form表单 Validform组件
			var form = $("#formSupplier")
					.Validform(
							{
								btnSubmit : "#submitBtn",// 设置提交按钮
								tiptype : 2,// 兄弟姐妹中找位置来填充
								postonce : false,// 不开启重复提交校验
								ajaxPost : false,// 不采用组件提供的ajax提交数据（因为组件提供的风格不一致，而且不好控制逻辑处理）
								callback : function(form) {
									var supplierCompany = $("#supplierCompany")
											.val().trim();// 获取厂商名称
									var supplierName = $("#supplierName").val()
											.trim();// 获取联系人名
									var supplierNum = $("#supplierNum").val()
											.trim();// 获取客户名称
									var supplierTel = $("#supplierTel").val()
											.trim();// 获取联系电话
									var supplierId = $("#supplierId").val()
											.trim();// 获取供应商id
									var supplierEmail = $("#supplierEmail")
											.val().trim();// 获取联系邮箱
									var elseTel = $("#elseTel").val().trim();// 其他联系方式
									var bz = $("#bz").val().trim();// 其他联系方式
									var s_county = $("#s_county").val().trim();// 区
									// 获取车主详细位置
									var ownerAddress = $(
											'#s_province option:selected')
											.text()
											+ $('#s_city option:selected')
													.text()
											+ $('#s_county option:selected')
													.text()
											+ $("#ownerAddress").val();
									var isdelete = $(
											"#isdeleteDiv input:checked").val();// 获取启用类型

									if (supplierName == null
											|| supplierName == "") {
										layer.alert("客户名称不可为空，请填写！", {
											icon : 2
										});
										$("#supplierName").focus();
										return false;
									}
									if (supplierTel == null
											|| supplierTel == "") {
										layer.alert("联系方式不可为空，请填写！", {
											icon : 2
										});
										$("#supplierTel").focus();
										return false;
									}
									if (isdelete == null || isdelete == "") {
										layer.alert("启用类型不可为空，请填写！", {
											icon : 2
										});
										$("#isdelete").focus();
										return false;
									}
									$
											.ajax({
												type : "post",
												url : "/cr-admin/supplier/addSupplier.do",
												data : {
													perId : supplierId,
													perCompanyName : supplierCompany,
													perNum : supplierNum,
													perName : supplierName,
													perTel : supplierTel,
													perCity : s_county,
													perAddress : ownerAddress,
													perEmail : supplierEmail,
													elseTel : elseTel,
													isdelete : isdelete,
													bz : bz
												},
												success : function(data) {
													if (data) {
														var code = data.code;
														var msg = data.msg;
														if (code == "0000") {
															layer
																	.msg(
																			msg,
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