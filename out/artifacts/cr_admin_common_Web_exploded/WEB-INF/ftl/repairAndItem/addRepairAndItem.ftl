<!--新增维修记录和项目信息-->
<#import "../common.ftl" as cc>
<!DOCTYPE HTML>
<html>
<!--头部-->
<@cc.html_head title="新增维修记录和项目信息">
<script type="text/javascript" src="${base}cr-admin/js/DateUtils.js"></script>
<style type="text/css">
.table>tbody>tr>td {
	text-align: center;
}
</style>
</@cc.html_head>
<body>
	<div class="pd-20">
		<div>
			<form action="" method="post" class="form form-horizontal col-12"
				id="formCar">
				<input type="text" value="${(carInfo.carId)!''}" id="carId"
					name="carId" style="display:none" /> <label>汽车信息</label>
				<div class="row cl">
					<label class="form-label col-2">客户姓名：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text"
							value="${(carInfo.ownerName)!''}" placeholder="" id="ownerName"
							name="ownerName" disabled>
					</div>

					<div class="col-2"></div>

					<label class="form-label col-2">车型：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text"
							value="${(carInfo.carType)!''}" placeholder="" id="carType"
							name="carType" disabled>
					</div>
				</div>

				<div class="row cl">
					<label class="form-label col-2">车牌号：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text"
							value="${(carInfo.licensePlateNum)!''}" placeholder=""
							id="licensePlateNum" name="licensePlateNum" disabled>
					</div>

					<div class="col-2"></div>

					<label class="form-label col-2">联系电话：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text"
							value="${(carInfo.linkerMobile)!''}" placeholder=""
							id="ownerMobile" name="ownerMobile" disabled>
					</div>
				</div>
			</form>

			<form action="" method="post" class="form form-horizontal col-12"
				id="formRepair">
				<label>维修记录信息</label>

				<div class="row cl">
					<label class="form-label col-2">维修单号：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="${repairNum!''}"
							placeholder="" id="repairNum" name="repairNum" disabled>
					</div>
					<span class="Validform_checktip col-2"></span> <label
						class="form-label col-2">维修顾问：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="" placeholder=""
							id="serviceAdviser" name="serviceAdviser" datatype="*1-50"
							errormsg="维修顾问应在50字以内！" ignore="ignore">
					</div>
					<span class="Validform_checktip col-2"></span>
				</div>

				<div class="row cl">
					<label class="form-label col-2">维修工：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="" placeholder=""
							id="repairMan" name="repairMan" datatype="*1-50"
							errormsg="维修工应在50字以内！" ignore="ignore">
					</div>
					<span class="Validform_checktip col-2"></span> <label
						class="form-label col-2"><span class="c-red">*</span>维修时间：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text Wdate" value=""
							placeholder="" id="repairTime" name="repairTime"
							onfocus="WdatePicker({maxDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							datatype="*" nullmsg="请填写维修时间！">
					</div>
					<span class="Validform_checktip col-2"></span>
				</div>

				<div class="row cl">
					<label class="form-label col-2">应收总额：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="" placeholder=""
							id="repairShouldSum" name="repairShouldSum" disabled>
					</div>
					<span class="Validform_checktip col-2"></span> <label
						class="form-label col-2">实收金额：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="" placeholder=""
							id="repairActualSum" name="repairActualSum"
							datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/"
							errormsg="请填写数字！" ignore="ignore">
					</div>
					<span class="Validform_checktip col-2"></span>

				</div>

				<div class="row cl">
					<label class="form-label col-2">行驶里程（单位：km）：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="0" placeholder=""
							id="warrantyStartMiles" name="warrantyStartMiles"
							datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/"
							errormsg="请填写数字！" ignore="ignore">
					</div>
					<span class="Validform_checktip col-2"></span> <label
						class="form-label col-2"><span class="c-red">*</span>付款方式：</label>
					<div class="formControls col-3 skin-minimal" id="repairPaymentDiv">
						<div class="radio-box">
							<input type="radio" value="0" id="repairPayment"
								name="repairPayment" checked datatype="*" nullmsg="请选择付款方式！" />
							<label for="repairPayment">现金</label>
						</div>

						<div class="radio-box">
							<input type="radio" value="1" id="repairPayment"
								name="repairPayment" /> <label for="repairPayment">支票</label>
						</div>

						<div class="radio-box">
							<input type="radio" value="2" id="repairPayment"
								name="repairPayment" /> <label for="repairPayment">信用卡</label>
						</div>
						<div class="radio-box">
							<input type="radio" value="3" id="repairPayment"
								name="repairPayment" /> <label for="repairPayment">其他</label>
						</div>

					</div>
					<span class="Validform_checktip col-1"></span>

				</div>

				<div class="row cl">
					<label class="form-label col-4"></label>
					
					<span class="Validform_checktip col-2"></span> <label
						class="form-label col-2">付款方式备注：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="" placeholder=""
							id=otherBz name="otherBz" datatype="*1-50" ignore="ignore">
					</div>
					<span class="Validform_checktip col-2"></span>

				</div>
				
				<div class="row cl">
					<div class="col-10">
						<button class="btn btn-secondary radius" type="submit"
							id="submitBtn" style="display:none">
							<i class="Hui-iconfont">&#xe632;</i>提交
						</button>
					</div>
				</div>
			</form>
		</div>
		<div class="mb-5 col-12">
			<span class="row cl"> <a href="javascript:;"
				class="btn btn-primary radius" id="addRow"><i
					class="Hui-iconfont">&#xe600;</i> 增加一行</a> <a href="javascript:;"
				class="btn btn-primary radius" id="delRow"><i
					class="Hui-iconfont">&#xe600;</i> 删除一行</a>
				<button class="btn btn-secondary radius" type="submit"
					id="submitBtn1">
					<i class="Hui-iconfont">&#xe632;</i>提交
				</button> </span>
		</div>
		<div class="mt-10">
			<table
				class="table table-border table-bordered table-hover table-bg table-sort"
				id="repairItemTable">
				<thead>
					<tr class="text-c">
						<th width="30" id="orderNum">序号</th>
						<!-- <th width="100" id="itemNum">项目编号</th> -->
						<th width="100" id="itemDes">项目描述</th>
						<th width="90" id="replaceReason">配件编号</th>
						<th width="90" id="itemUnit">项目单位</th>
						<th width="90" id="itemQuantity">项目数量</th>
						<th width="100" id="itemPrice">项目单价</th>
						<th width="90" id="workHoursCost">工时费</th>
						<th width="90" id="itemSum">项目总额</th>
						<th width="90" id="stockId" class="hide">项目id</th>
						<th width="90" id="stockPriceIncome" class="hide">项目进价</th>
						<th width="90" id="manage">操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>

	<script type="text/javascript"
		src="${base}cr-admin/js/repairAndItem/repairAndItem.js"></script>
	<script type="text/javascript"
		src="${base}cr-admin/js/jquery.tabletojson.js"></script>
	<script type="text/javascript">
		$(function() {
			// 初始化设置焦点
			$("#serviceAdviser").focus();

			// 验证form表单 Validform组件
			var form = $("#formRepair")
					.Validform(
							{
								btnSubmit : "#submitBtn",// 设置提交按钮
								tiptype : 2,// 兄弟姐妹中找位置来填充
								postonce : false,// 不开启重复提交校验
								ajaxPost : false,// 不采用组件提供的ajax提交数据（因为组件提供的风格不一致，而且不好控制逻辑处理）
								callback : function(form) {
									var carId = $("#carId").val().trim();// 获取汽车id
									var repairNum = $("#repairNum").val()
											.trim();// 获取维修单号
									var serviceAdviser = $("#serviceAdviser")
											.val().trim();// 获取维修顾问
									var repairMan = $("#repairMan").val()
											.trim();// 获取维修工
									var repairTime = $("#repairTime").val()
											.trim();// 获取维修时间
									var warrantyStartMiles = $(
											"#warrantyStartMiles").val().trim();// 获取行驶里程数
									var repairPayment = $(
											"#repairPaymentDiv input:checked")
											.val();// 获取付款方式
									var repairActualSum = $("#repairActualSum")
											.val().trim();// 实收金额
									var otherBz = $("#otherBz").val().trim();

									if (carId == null || carId == "") {
										layer.alert("汽车id不可为空，请刷新重试！", {
											icon : 2
										});
										return false;
									}

									if (repairTime == null || repairTime == "") {
										layer.alert("维修时间不可为空，请填写！", {
											icon : 2
										});
										$("#repairTime").focus();
										return false;
									}

									// 行驶里程为空默认为0
									if (warrantyStartMiles == null
											|| warrantyStartMiles == "") {
										warrantyStartMiles = 0;
									}

									var array = new Array();
									var table = $("#repairItemTable");
									var flag = true;
									// 循环维修项目table，并将相关数据保存到json对象中
									table
											.find("tbody tr")
											.each(
													function(i, valtr) {
														// 定义项目对象
														var item = {
															stockId : "",// 库存id
															stockPriceIncome : "",// 进价
															itemNum : "",// 项目编号
															itemDes : "",// 项目描述
															replaceReason : "",// 更换原因 字段显示改为 配件编号
															itemUnit : "",// 项目单位
															itemQuantity : 0,// 项目数量
															itemPrice : 0,// 项目单价
															workHoursCost : 0,// 工时费
															itemSum : 0
														// 项目总额
														}

														$(this)
																.find("input")
																.each(
																		function(
																				j,
																				valtd) {
																			array[i] = item;
																			if ($(
																					this)
																					.attr(
																							"id") == "itemNum") {// 项目编号
																				item.itemNum = $(
																						this)
																						.val()
																						.trim();
																			} else if ($(
																					this)
																					.attr(
																							"id") == "itemDes") {// 项目描述
																				item.itemDes = $(
																						this)
																						.val()
																						.trim();
																				// 顺便取下 编号
																				item.itemNum = i + 1;
																			} else if ($(
																					this)
																					.attr(
																							"id") == "replaceReason") {// 更换原因 字段显示改为 配件编号
																				item.replaceReason = $(
																						this)
																						.val()
																						.trim();
																			} else if ($(
																					this)
																					.attr(
																							"id") == "itemUnit") {// 项目单位
																				item.itemUnit = $(
																						this)
																						.val()
																						.trim();
																			} else if ($(
																					this)
																					.attr(
																							"id") == "itemQuantity") {// 项目数量
																				item.itemQuantity = $(
																						this)
																						.val()
																						.trim();
																			} else if ($(
																					this)
																					.attr(
																							"id") == "itemPrice") {// 项目单价
																				item.itemPrice = $(
																						this)
																						.val()
																						.trim();
																			} else if ($(
																					this)
																					.attr(
																							"id") == "workHoursCost") {// 工时费
																				item.workHoursCost = $(
																						this)
																						.val()
																						.trim();
																			} else if ($(
																					this)
																					.attr(
																							"id") == "stockPriceIncome") {// 进价
																				item.stockPriceIncome = $(
																						this)
																						.val()
																						.trim();
																			} else if ($(
																					this)
																					.attr(
																							"id") == "itemSum") {// 项目总额
																				if (!$(
																						this)
																						.val()
																						.trim()) {
																					layer
																							.alert(
																									"项目总额不可为空，请填写！",
																									{
																										icon : 2
																									});
																					$(
																							this)
																							.focus();
																					flag = false;
																					return false;
																				}
																				item.itemSum = $(
																						this)
																						.val()
																						.trim();
																			}
																		});
														var stockId = $(this)
																.data("stockId");
														if (stockId) {
															item.stockId = stockId;
														}
													});

									if (!flag) {// 检验项目列表中是否含有异常数据，有则跳出
										return false;
									}

									if (array.length == 0) {
										layer.alert("项目列表不可为空，请填写！", {
											icon : 2
										});
										return false;
									}
									var conditionPara = {
										carId : carId,
										repairNum : repairNum,
										serviceAdviser : serviceAdviser,
										repairActualSum : repairActualSum,
										repairMan : repairMan,
										repairTime : repairTime,
										warrantyStartMiles : warrantyStartMiles,
										repairPayment : repairPayment,
										otherBz : otherBz,
										itemArray : array
									};
									$
											.ajax({
												type : "post",
												url : "/cr-admin/repair/addRepairAndItem.do",
												data : {
													conditionPara : JSON
															.stringify(conditionPara)
												},
												success : function(data) {
													if (data) {
														var code = data.code;
														var msg = data.msg;
														if (code == "0000") {
															layer
																	.msg(
																			"新增成功",
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
		$("#submitBtn1").click(function() {
			$("#submitBtn").click();
		});
		$('.skin-minimal input').iCheck({
			checkboxClass : 'icheckbox-blue',
			radioClass : 'iradio-blue',
			increaseArea : '20%'
		});
	</script>
</body>
</html>