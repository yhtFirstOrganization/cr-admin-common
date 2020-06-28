<!--新增汽车信息-->
<#import "../common.ftl" as cc>
<!DOCTYPE HTML>
<html>
<!--头部-->
<@cc.html_head title="新增汽车信息">
	<script type="text/javascript" src="${base}cr-admin/js/DateUtils.js"></script>
</@cc.html_head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal"
			id="formCar">
			
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>客户名称：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="" placeholder="" id="ownerName" name="ownerName"
						datatype="*1-50" errormsg="客户名称应在50字以内！" nullmsg="请填写客户名称！">
				</div>
				<span class="Validform_checktip col-2"></span>
				
				<label class="form-label col-2"><span class="c-red">*</span>车牌号：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="" placeholder="" id="licensePlateNum" name="licensePlateNum"
						datatype="*1-50" errormsg="车牌号应在50字以内！" nullmsg="请填写车牌号！">
				</div>
				<span class="Validform_checktip col-2"></span>
				
			</div>
			
			<div class="row cl">
				<label class="form-label col-2 "><span class="c-red">*</span>联系人：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="" placeholder="" id="linker" name="linker"
						datatype="*1-50" errormsg="联系人应在50字以内！" nullmsg="请填写联系人！">
				</div>
				<span class="Validform_checktip col-2"></span>
				
				<label class="form-label col-2"><span class="c-red">*</span>车型：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="" placeholder="" id="carType" name="carType"
					datatype="*1-50" errormsg="车型应在50字以内！" nullmsg="请填写车型！">
				</div>
				<span class="Validform_checktip col-2"></span>
			</div>
			
			<div class="row cl">
				<label class="form-label col-2 "><span class="c-red">*</span>联系电话：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="" placeholder="" id="linkerMobile" name="linkerMobile"
						datatype="*1-20" errormsg="请填写正确的联系电话！" nullmsg="请填写联系电话！">
				</div>
				<span class="Validform_checktip col-2"></span>
				
				
				<label class="form-label col-2"><span class="c-red">*</span>车架号：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="" placeholder="" id="carFrameNum" name="carFrameNum"
						datatype="*1-100" errormsg="车架号应在100字以内！" nullmsg="请填写车架号！">
				</div>
				<span class="Validform_checktip col-2"></span>
			</div>
			
			<div class="row cl">
				<label class="form-label col-2 ">车主详细位置：</label>
				<div class="formControls col-1">
					<span class="select-box">
						<select class="select" id="s_province" name="s_province">
							<option value="" selected>省</option>
			            	<#list provs as prov>
			            		<option value="${prov.regionId}">${prov.name}</option>
			            	</#list>
						</select>
					</span>
				</div>
				<div class="formControls col-1">
					<span class="select-box">
						<select class="select" id="s_city" name="s_city">
							<option value="" selected>市</option>
						</select>
					</span>
				</div>
				<div class="formControls col-1">
					<span class="select-box">
						<select class="select" id="s_county" name="s_county" dataType="*" nullmsg="请选择地市！" ignore="ignore">
							<option value="" selected>区/县</option>
						</select>
					</span>
				</div>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="" placeholder="" id="ownerAddress" name="ownerAddress"
						datatype="*1-50" errormsg="详细住址应在50字以内！" ignore="ignore">
				</div>
			</div>
			
			<div class="row cl" style="display:none;">
				<label class="form-label col-2 "><span class="c-red">*</span>是否启用：</label>
				<div class="formControls col-2 skin-minimal" id="isdeleteDiv">
					<div class="radio-box">
						<input type="radio" value="0" id="isdelete" name="isdelete" checked datatype="*" nullmsg="请选择启用类型！"/>
						<label for="isdelete">启用</label>
					</div>
					
					<div class="radio-box">
						<input type="radio" value="1" id="isdelete" name="isdelete"/>
						<label for="isdelete">停用</label>
					</div>
				</div>
				<span class="Validform_checktip col-2"></span>
			</div>
			
			<div class="row cl">
				<div class="col-10 col-offset-2">
					<button class="btn btn-secondary radius" type="submit" id="submitBtn">
					<i class="Hui-iconfont">&#xe632;</i>提交</button>
				</div>
			</div>
		</form>
	</div>
	</div>

	<script type="text/javascript" src="${base}cr-admin/js/car/car.js"></script>
	<script type="text/javascript">
		$(function() {
			// 设置焦点
			$("#ownerName").focus();
			
			// 给licensePlateNum绑定一个ajax请求校验
			$("#licensePlateNum").bind("change", function(){
				var licensePlateNum = $("#licensePlateNum").val().trim();
				var ajaxurl="/cr-admin/car/checkLicensePlateNum.do?licensePlateNum=" + $("#licensePlateNum").val().trim();
				$(this).attr("ajaxurl", ajaxurl);
			});
			
			// 验证form表单 Validform组件
			var form = $("#formCar").Validform({
				btnSubmit : "#submitBtn",// 设置提交按钮
				tiptype : 2,// 兄弟姐妹中找位置来填充
				postonce : false,// 不开启重复提交校验
				ajaxPost : false,// 不采用组件提供的ajax提交数据（因为组件提供的风格不一致，而且不好控制逻辑处理）
				callback : function(form) {
					var carType = $("#carType").val().trim();// 获取车型
					var licensePlateNum = $("#licensePlateNum").val().trim();// 获取车牌号
					var carFrameNum = $("#carFrameNum").val().trim();// 获取车架号
					var ownerName = $("#ownerName").val().trim();// 获取客户名称
					var linker = $("#linker").val().trim();// 获取联系人
					var linkerMobile = $("#linkerMobile").val().trim();// 获取联系电话
					var s_county = $("#s_county").val().trim();// 区
					// 获取车主详细位置
					var ownerAddress = $('#s_province option:selected').text()
					+ $('#s_city option:selected').text()
					+ $('#s_county option:selected').text() + $("#ownerAddress").val();
					var isdelete = $("#isdeleteDiv input:checked").val();// 获取启用类型
					
					if(carType == null || carType == ""){
						layer.alert("车型不可为空，请填写！", {icon : 2});
						$("#carType").focus();
						return false;
					}
					if(licensePlateNum == null || licensePlateNum == ""){
						layer.alert("车牌号不可为空，请填写！", {icon : 2});
						$("#licensePlateNum").focus();
						return false;
					}
					if(carFrameNum == null || carFrameNum == ""){
						layer.alert("车架号不可为空，请填写！", {icon : 2});
						$("#carFrameNum").focus();
						return false;
					}
					if(ownerName == null || ownerName == ""){
						layer.alert("客户名称不可为空，请填写！", {icon : 2});
						$("#ownerName").focus();
						return false;
					}
					if(linker == null || linker == ""){
						layer.alert("联系人不可为空，请填写！", {icon : 2});
						$("#linker").focus();
						return false;
					}
					if(linkerMobile == null || linkerMobile == ""){
						layer.alert("车主联系电话不可为空，请填写！", {icon : 2});
						$("#linkerMobile").focus();
						return false;
					}
					if(isdelete == null || isdelete == ""){
						layer.alert("启用类型不可为空，请填写！", {icon : 2});
						$("#isdelete").focus();
						return false;
					}
					$.ajax({
						type : "post",
						url : "/cr-admin/car/addCar.do",
						data : {
							carType : carType,
							licensePlateNum : licensePlateNum,
							carFrameNum : carFrameNum,
							ownerName : ownerName,
							ownerCity : s_county,
							ownerAddress : ownerAddress,
							linker : linker,
							linkerMobile : linkerMobile,
							isdelete :isdelete
						},
						success : function(data){
							if(data){
								var code = data.code;
								var msg = data.msg;
								if(code == "0000"){
									layer.msg("新增成功", {icon: 1,  time: 1000},function(){
										var index = parent.layer.getFrameIndex(window.name);// 先得到当前iframe层的索引
										parent.layer.close(index);// 执行关闭
										return false;
									});
								}else{
									layer.alert(msg, {icon : 2});
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
			checkboxClass: 'icheckbox-blue',
			radioClass: 'iradio-blue',
			increaseArea: '20%'
		});
	</script>
	<!--引入省市区js-->
	<@cc.getChildNode_js></@cc.getChildNode_js>
</body>
</html>