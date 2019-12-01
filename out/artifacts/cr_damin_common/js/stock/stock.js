var table1, table2, table3, table4, table5, table6, tableId, stockTable;
var currentTable;

// 新增库存信息
$("#addStock").bind(
		"click",
		function() {
			edit_refresh('新增库存', '/cr-admin/stock/toAddStock.do', '', '',
					stockTable, 'true');
		});

// 库存信息新增、编辑时，关闭刷新当前table，需要引入common.js
function edit_refresh(title, url, w, h, table, isFull) {
	layer_show_refresh(title, url, w, h, table, isFull);
}

function layer_show_refresh_click(title, url, w, h, table, isFull, callback) {
	if (title == null || title == '') {
		title = false;
	}
	;
	if (url == null || url == '') {
		url = "404.html";
	}
	;
	if (w == null || w == '') {
		w = 800;
	}
	;
	if (h == null || h == '') {
		h = ($(window).height() - 50);
	}
	;
	if (isFull == 'true') {
		isFull = true;
	} else {
		isFull = false;
	}
	var index = layer.open({
		type : 2,
		area : [ w + 'px', h + 'px' ],
		fix : false, // 不固定
		maxmin : true,
		shade : 0.4,
		title : title,
		content : url,
		end : function(index) {// 层销毁后触发的回调，刷新当前table
			callback();
		}
	});
	if (isFull) {
		layer.full(index);
	}
}

$("tbody").on(
		'click',
		'a.edit, a.delete,a.inCome, a.lovChoose',
		function() {
			var hrefId = $(this).attr("class");// href的id
			var stockId = $($(this).parents('tr').find("td")[1]).text(); // 通过dom节点
			// var data = table1.row( $(this).parents('tr') ).data();//
			// 通过官方提供的方法
			// var userId = data.userId;
			if (hrefId == "lovChoose") {
				lovChoose($(this).parents('tr'));
			} else if (hrefId == "inCome") {
				edit_refresh('进货', '/cr-admin/stock/toInComeStock.do?&stockId='
						+ stockId, '', '', stockTable, 'false');
			} else if (hrefId == "edit") {
				layer_show_refresh_click('输入秘钥',
						'/cr-admin/stock/toShowStockIncomePricePage.do', '400',
						'200', '', 'false', function() {
							var isShowIncomePrice = localStorage
									.getItem("isShowIncomePrice");
							localStorage.setItem("isShowIncomePrice", "");
							if (isShowIncomePrice
									&& isShowIncomePrice == "true") {
								edit_refresh('编辑',
										'/cr-admin/stock/toEditStock.do?&stockId='
												+ stockId, '', '', stockTable,
										'true');
							}
						});

			} else {// 删除
				stock_delete_refresh(stockId, stockTable);
			}
		});

// ---------------------------------------------------------------

// 填充数据方法(库存列表)
function datatablefunc(isLOV) {

	// 点击某一行会有选中
	$('.table-sort tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
		} else {
			$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});

	// 双击
	$('.table-sort tbody').on('dblclick', 'tr', function() {
		// 暂时无动作
	});

	var returntable = $("#stocktable")
			.DataTable(
					{
						"ajax" : {
							url : "/cr-admin/stock/queryStockList.do",
							dataSrc : "data"// 解析返回的json对象名称（不写默认接收key为data的json结构）
						},
						// 解析全部列，如果有控制须用null来接收
						"columns" : [ {
							"data" : null
						}, {
							"data" : "stockId"
						}, {
							"data" : "stockNo"
						}, {
							"data" : "stockDes"
						}, {
							"data" : "stockPriceIncome"
						}, {
							"data" : "stockPrice"
						}, {
							"data" : "stockQuantity"
						}, {
							"data" : "stockUnit"
						}, {
							"data" : "workHoursCost"
						}, {
							"data" : null
						}

						],

						// 解析返回的某些列，并对其进行显示设置
						"columnDefs" : [
								{
									// 发布状态
									targets : 1,
									render : function(data, type, row, meta) {
										return "<td class=\"stockId\">" + data
												+ "</td>";
									}
								},
								{
									// 发布状态
									targets : -1,
									render : function(data, type, row, meta) {
										var tdHtml = "<td class=\"td-status\"><a title=\"进货\" href=\"javascript:;\" class=\"inCome\"><span class=\"label label-success radius\">进货</span></a>"
												+ "&nbsp;&nbsp;<a title=\"修改\" href=\"javascript:;\" class=\"edit\"><span class=\"label label-warning radius\">修改</span></a>"
												+ "&nbsp;&nbsp;<a title=\"删除\" href=\"javascript:;\" class=\"delete\"><span class=\"label label-warning radius\">删除</span></a></td>";
										if (isLOV) {
											tdHtml = "<td class=\"td-status\"><a title=\"使用这个配件\" href=\"javascript:;\" class=\"lovChoose\"><span class=\"label label-success radius\">选择</span></a></td>";
										}
										return tdHtml;
									}
								},
								// {"visible": false, "targets": [ 1 ]},//
								// 控制列的隐藏显示
								{
									"orderable" : false,
									"targets" : [ 0, -1, -2 ]
								// 指定列不参与排序
								}

						],
						"language" : {
							"sProcessing" : "处理中...",
							"sLengthMenu" : "显示 _MENU_ 项结果",
							"sZeroRecords" : "没有匹配结果",
							"sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
							"sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
							"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
							"sInfoPostFix" : "",
							"sSearch" : "搜索:",
							"sUrl" : "",
							"sEmptyTable" : "表中数据为空",
							"sLoadingRecords" : "载入中...",
							"sInfoThousands" : ",",
							"oPaginate" : {
								"sFirst" : "首页",
								"sPrevious" : "上页",
								"sNext" : "下页",
								"sLast" : "末页"
							},
							"oAria" : {
								"sSortAscending" : ": 以升序排列此列",
								"sSortDescending" : ": 以降序排列此列"
							}
						},
						"initComplete" : function(settings, json) {// table初始化后触发
							$("table tr").find("td:eq(1),th:eq(1)").hide();// 设置第一列隐藏
						},
						"drawCallback" : function(settings) {
							$("table tr").find("td:eq(1)").hide();// 设置第一列隐藏
							$("table tr").find("td:eq(4),th:eq(4)").hide();

							if (isLOV) {
								trDbClickAction();
							}
						},
						"order" : [ [ 1, "desc" ] ],// 默认按第1列排序
						"stateSave" : false
					// 状态保存
					});
	// 添加索引列
	returntable.on('order.dt search.dt', function() {
		returntable.column(0, {
			search : 'applied',
			order : 'applied'
		}).nodes().each(function(cell, i) {
			cell.innerHTML = i + 1;
		});
	}).draw();

	stockTable = returntable;
	return returntable;
}

// 库存删除，确认刷新当前table
function stock_delete_refresh(stockId, table) {
	if (stockId == null || stockId == "") {
		layer.alert("库存id不可为空，请刷新重试！", {
			icon : 2
		});
		return false;
	}
	var confirmMsg = "确认要删除吗？";
	layer.confirm(confirmMsg, {
		icon : 3
	}, function(index) {
		$.ajax({
			type : "post",
			url : "/cr-admin/stock/delStockInfo.do",
			data : {
				stockId : stockId
			},
			success : function(data) {
				if (data) {
					var code = data.message.code;
					var msg = data.message.msg;
					if (code == "0000") {
						layer.close(index);
						layer.msg("已删除", {
							icon : 1,
							time : 1000
						}, function() {
							table.ajax.reload();
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
	});

}

// 初始化特殊密码校验界面
function initValidateOtherPasswordPage() {
	// 设置焦点
	$("#otherPassword").focus();

	// 验证form表单 Validform组件
	var form = $("#validateOtherPassword").Validform(
			{
				btnSubmit : "#submitBtn",// 设置提交按钮
				tiptype : 2,// 兄弟姐妹中找位置来填充
				postonce : false,// 不开启重复提交校验
				ajaxPost : false,// 不采用组件提供的ajax提交数据（因为组件提供的风格不一致，而且不好控制逻辑处理）
				callback : function(form) {
					var otherPassword = $("#otherPassword").val().trim();// 获取库存编号
					if (otherPassword == null || otherPassword == "") {
						layer.alert("秘钥不可为空，请填写！", {
							icon : 2
						});
						$("#otherPassword").focus();
						return false;
					}
					var flag = true;
					var msg = "";
					$.ajax({
						type : "post",
						dataType : "json",
						async : false,
						url : "/cr-admin/stock/validateIncomePricePassword.do",
						data : {
							otherPassword : otherPassword
						},
						success : function(data) {
							if (data) {
								var code = data.code;
								msg = data.msg;
								if (code == "200") {
									localStorage.setItem("isShowIncomePrice",
											"true");
									var index = parent.layer
											.getFrameIndex(window.name);// 先得到当前iframe层的索引
									parent.layer.close(index);// 执行关闭

								} else {
									flag = false;
								}
							}
						}
					});
					if (!flag) {
						layer.alert(msg, {
							icon : 2
						});
					}
					return flag;
				}
			});

}
// 初始化增加界面
function initAddPage() {
	// 设置焦点
	$("#stockNo").focus();

	// 给licensePlateNum绑定一个ajax请求校验
	$("#stockNo").bind(
			"change",
			function() {
				var stockId = $("#stockId").val().trim();
				var stockNo = $("#stockNo").val().trim();
				var ajaxurl = "/cr-admin/stock/checkStockNo.do?stockNo="
						+ stockNo + "&stockId=" + stockId;
				$(this).attr("ajaxurl", ajaxurl);
			});

	// 验证form表单 Validform组件
	var form = $("#formStock")
			.Validform(
					{
						btnSubmit : "#submitBtn",// 设置提交按钮
						tiptype : 2,// 兄弟姐妹中找位置来填充
						postonce : false,// 不开启重复提交校验
						ajaxPost : false,// 不采用组件提供的ajax提交数据（因为组件提供的风格不一致，而且不好控制逻辑处理）
						callback : function(form) {
							var stockNo = $("#stockNo").val().trim();// 获取库存编号
							var stockDes = $("#stockDes").val().trim();// 获取库存描述
							var stockPriceIncome = "";// 获取库存价格
							var stockPrice = $("#stockPrice").val().trim();// 获取库存价格
							var stockQuantity = $("#stockQuantity").val()
									.trim();// 获取库存个数
							var stockUnit = $("#stockUnit").val().trim();// 获取库存单位
							var workHoursCost = $("#workHoursCost").val()
									.trim();// 获取安装工时费
							var supplier = $("#supplier").val().trim();// 获取供应商名称
							var supplierTel = $("#supplierTel").val().trim();// 获取供应商电话
							var supplierId = $("#makeupCoSe").val();// 获取供应商id
							var stockId = $("#stockId").val().trim();// 获取id
							var inComeStockQuantity = "";// 获取进货数量
							if (pageType && pageType == "inCome") {// 进货
								inComeStockQuantity = $("#inComeStockQuantity")
										.val().trim();
								if (stockNo == null || stockNo == "") {
									layer.alert("库存编号不可为空，请检查！", {
										icon : 2
									});
									return false;
								}

								if (inComeStockQuantity == null
										|| inComeStockQuantity == "") {
									layer.alert("进货数量不可为空，请填写！", {
										icon : 2
									});
									$("#inComeStockQuantity").focus();
									return false;
								}
							} else {
								stockPriceIncome = $("#stockPriceIncome").val()
										.trim();
								if (stockNo == null || stockNo == "") {
									layer.alert("库存编号不可为空，请填写！", {
										icon : 2
									});
									$("#stockNo").focus();
									return false;
								}
								if (stockDes == null || stockDes == "") {
									layer.alert("库存描述不可为空，请填写！", {
										icon : 2
									});
									$("#stockDes").focus();
									return false;
								}
								if (stockPriceIncome == null
										|| stockPriceIncome == "") {
									layer.alert("库存进价不可为空，请填写！", {
										icon : 2
									});
									$("#stockPriceIncome").focus();
									return false;
								}
								if (stockPrice == null || stockPrice == "") {
									layer.alert("库存价格不可为空，请填写！", {
										icon : 2
									});
									$("#stockPrice").focus();
									return false;
								}
								if (stockQuantity == null
										|| stockQuantity == "") {
									layer.alert("库存个数不可为空，请填写！", {
										icon : 2
									});
									$("#stockQuantity").focus();
									return false;
								}
							}
							$
									.ajax({
										type : "post",
										url : "/cr-admin/stock/addStock.do",
										data : {
											stockNo : stockNo,
											stockDes : stockDes,
											stockPriceIncome : stockPriceIncome,
											stockPrice : stockPrice,
											stockQuantity : stockQuantity,
											supplier : supplier,
											supplierTel : supplierTel,
											supplierId : supplierId,
											workHoursCost : workHoursCost,
											stockUnit : stockUnit,
											stockId : stockId,
											inComeStockQuantity : inComeStockQuantity,
											pageType : pageType
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

}

function lovChoose(tr) {
	var stockId = $($(tr).find("td")[1]).text();
	var stockNo = $($(tr).find("td")[2]).text();
	var stockDes = $($(tr).find("td")[3]).text();
	var stockPriceIncome = $($(tr).find("td")[4]).text();
	var stockPrice = $($(tr).find("td")[5]).text();
	var stockQuantity = 1;
	var stockUnit = $($(tr).find("td")[7]).text();
	var workHoursCost = $($(tr).find("td")[8]).text();
	var lovPara = "{\"stockId\":\"" + stockId + "\",\"stockNo\":\"" + stockNo
			+ "\",\"stockDes\":\"" + stockDes + "\",\"stockQuantity\":\""
			+ stockQuantity + "\",\"stockUnit\":\"" + stockUnit
			+ "\",\"workHoursCost\":\"" + workHoursCost
			+ "\",\"stockPriceIncome\":\"" + stockPriceIncome
			+ "\",\"stockPrice\":\"" + stockPrice + "\"}";
	localStorage.setItem("lovPara", lovPara);
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

// 双击
function trDbClickAction() {
	$('.table-sort tbody').on('dblclick', 'tr', function() {
		lovChoose(this);
	});
}