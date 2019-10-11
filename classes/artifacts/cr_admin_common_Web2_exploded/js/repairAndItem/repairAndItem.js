var table1, table2, table3, table4, table5, table6, tableId, carTable;
var editTable;

$(function() {
	var repairId = $("#repairId").val();
	editTable = $("#repairItemTable")
			.DataTable(
					{
						"ajax" : {
							url : "/cr-admin/repairItem/queryRepairItemList.do",
							data : {
								repairId : repairId
							},
							dataSrc : "data"// 解析返回的json对象名称（不写默认接收key为data的json结构）
						},
						// 解析全部列，如果有控制须用null来接收
						"columns" : [ {
							"data" : null
						// }, {
						// "data" : "itemNum"
						}, {
							"data" : "itemDes"
						}, {
							"data" : "replaceReason"
						}, {
							"data" : "itemUnit"
						}, {
							"data" : "itemQuantity"
						}, {
							"data" : "itemPrice"
						}, {
							"data" : "workHoursCost"
						}, {
							"data" : "itemSum"
						}, {
							"data" : "stockId"
						}, {
							"data" : "stockPriceIncome"
						}, {
							"data" : null
						} ],
						"columnDefs" : [
								{
									// 操作
									targets : -1,
									render : function(data, type, row, meta) {
										return "<td class=\"td-manage\"><a title=\"删除\" href=\"javascript:;\" id=\"delete\" class=\"delete\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe631;</i></a></td>";
									}
								}, {
									"orderable" : false,
									"targets" : [ 1, 2, 3, 4, 5, 6, 7 ]
								// 指定列不参与排序
								} ],
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
							initTextTable();
						},
						"pageLength" : 200,
						"dom" : "tr",// 只显示table和加载等待信息
						"order" : [ [ 0, "asc" ] ],// 默认按第二列排序
						"stateSave" : false
					// 状态保存
					});
	// 添加索引列
	editTable.on('order.dt search.dt', function() {
		editTable.column(0, {
			search : 'applied',
			order : 'applied'
		}).nodes().each(function(cell, i) {
			cell.innerHTML = i + 1;
		});
	}).draw();
});

// 初始化table后设置每个td的样式为input，因为在render中设置的当手动添加时，会有问题
function initTextTable() {
	// 循环维修项目table，并将相关数据保存到json对象中
	$("#repairItemTable")
			.find("tbody tr")
			.each(
					function(i, valtr) {
						$(this)
								.find("td")
								.each(
										function(j, valtd) {
											if (j == 1) {// 项目描述
												$(this).html(
														"<input type=\"text\" class=\"input-text\" id=\"itemDes\" value=\""
																+ $(this)
																		.text()
																+ "\">");
											} else if (j == 2) {// 更换原因 字段显示改为
												// 配件编号
												$(this)
														.html(
																"<input type=\"text\" class=\"input-text\" id=\"replaceReason\" value=\""
																		+ $(
																				this)
																				.text()
																		+ "\" maxlength=\"150\">");
											} else if (j == 3) {// 项目单位
												$(this)
														.html(
																"<input type=\"text\" class=\"input-text\" id=\"itemUnit\" value=\""
																		+ $(
																				this)
																				.text()
																		+ "\" maxlength=\"10\">");
											} else if (j == 4) {// 项目数量
												$(this)
														.html(
																"<input type=\"text\" class=\"input-text\" id=\"itemQuantity\" value=\""
																		+ $(
																				this)
																				.text()
																		+ "\" maxlength=\"10\" onKeyUp=\"amount(this)\">");
											} else if (j == 5) {// 项目单价
												$(this)
														.html(
																"<input type=\"text\" class=\"input-text\" id=\"itemPrice\" value=\""
																		+ $(
																				this)
																				.text()
																		+ "\" maxlength=\"10\" onKeyUp=\"amount(this)\">");
											} else if (j == 6) {// 工时费
												$(this)
														.html(
																"<input type=\"text\" class=\"input-text\" id=\"workHoursCost\" value=\""
																		+ $(
																				this)
																				.text()
																		+ "\" maxlength=\"10\" onKeyUp=\"amount(this)\">");
											} else if (j == 7) {// 项目总额
												$(this)
														.html(
																"<input type=\"text\" class=\"input-text\" id=\"itemSum\" value=\""
																		+ $(
																				this)
																				.text()
																		+ "\" disabled>");
											} else if (j == 8) {// 库存id
												$(this).addClass("hide");
												var stockId = $(this).html();
												// 给tr赋值
												$(this).parent().data(
														"stockId", stockId);
												$(this)
														.html(
																"<input type=\"text\" class=\"input-text\" id=\"stockId\" value=\""
																		+ $(
																				this)
																				.text()
																		+ "\" disabled>");
											} else if (j == 9) {// 进价
												$(this).addClass("hide");
												$(this)
												.html(
														"<input type=\"text\" class=\"input-text\" id=\"stockPriceIncome\" value=\""
														+ $(
																this)
																.text()
																+ "\" disabled>");
											}
										});
					});
}

// 增加一行
$("#addRow")
		.click(
				function() {
					layer_show_refresh_click(
							'选择配件',
							'/cr-admin/stock/toStockLOV.do',
							'',
							'',
							'false',
							function() {
								var lovPara = localStorage.getItem("lovPara");
								localStorage.setItem("lovPara", "{}");
								if (!lovPara) {
									lovPara = "{}";
								}
								lovPara = JSON.parse(lovPara);
								if (!lovPara || !lovPara.stockId) {
									return;
								}
								var tr = $("<tr class=\"text-c\"></tr>")
										.append(
												"<td id=\"orderNum\"></td>"
														+ "<td><input type=\"text\" class=\"input-text\" id=\"itemDes\" value=\""
														+ lovPara.stockDes
														+ "\"></td>"
														+ "<td><input type=\"text\" class=\"input-text\" id=\"replaceReason\" maxlength=\"150\" value=\""
														+ lovPara.stockNo
														+ "\"></td>"
														+ "<td><input type=\"text\" class=\"input-text\" id=\"itemUnit\" maxlength=\"10\" value=\""
														+ lovPara.stockUnit
														+ "\"></td>"
														+ "<td><input type=\"text\" class=\"input-text\" id=\"itemQuantity\" maxlength=\"10\" onKeyUp=\"amount(this)\" value=\""
														+ lovPara.stockQuantity
														+ "\"></td>"
														+ "<td><input type=\"text\" class=\"input-text\" id=\"itemPrice\" maxlength=\"10\" onKeyUp=\"amount(this)\" value=\""
														+ lovPara.stockPrice
														+ "\"></td>"
														+ "<td><input type=\"text\" class=\"input-text\" id=\"workHoursCost\" maxlength=\"10\" onKeyUp=\"amount(this)\" value=\""
														+ lovPara.workHoursCost
														+ "\"></td>"
														+ "<td><input type=\"text\" class=\"input-text\" id=\"itemSum\" disabled></td>"
														+ "<td class=\"hide\"><input type=\"text\" class=\"input-text\" id=\"stockId\" disabled></td>"
														+ "<td class=\"hide\"><input type=\"text\" class=\"input-text\" id=\"stockPriceIncome\" disabled value=\""
														+ lovPara.stockPriceIncome
														+ "\"></td>"
														+ "<td class=\"td-manage\"><a title=\"删除\" href=\"javascript:;\" id=\"delete\" class=\"delete\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe631;</i></a></td>");
								editTable.row.add(tr).draw();
								if (lovPara.stockNo) {// 选择库存
									// 编号不可改
									var tr = $('#repairItemTable tr:last');
									tr.find('td').eq(3).find("input").attr(
											"disabled", "disabled");
									//名称可改，应付假库存情况
									// tr.find('td').eq(1).find("input").attr(
									// "disabled", "disabled");
									// 记录id
									tr.data("stockId", lovPara.stockId);
									// 计算应收总额
									jisuanjine(tr.find('td').eq(1));
								}
								editTable.on('order.dt search.dt', function() {
									editTable.column(0, {
										search : 'applied',
										order : 'applied'
									}).nodes().each(function(cell, i) {
										cell.innerHTML = i + 1;
									});
								}).draw();
							});

				});

// 删除选中行
$("#repairItemTable tbody").on("click", "tr", function() {
	if ($(this).hasClass("selected")) {
		$(this).removeClass("selected");
	} else {
		editTable.$("tr.selected").removeClass("selected");
		$(this).addClass("selected");
	}
});

$("#delRow").click(function() {
	editTable.row(".selected").remove().draw(false);
	editTable.on('order.dt search.dt', function() {
		editTable.column(0, {
			search : 'applied',
			order : 'applied'
		}).nodes().each(function(cell, i) {
			cell.innerHTML = i + 1;
		});
	}).draw();
});
// 点击删除图标时
$("#repairItemTable tbody").on("click", "#delete", function() {
	editTable.row($(this).parents("tr")).remove().draw();
	editTable.on('order.dt search.dt', function() {
		editTable.column(0, {
			search : 'applied',
			order : 'applied'
		}).nodes().each(function(cell, i) {
			cell.innerHTML = i + 1;
		});
	}).draw();
});

// 计算项目总额
$("#repairItemTable tbody").on("blur",
		"#itemQuantity,#itemPrice, #workHoursCost", function() {
			jisuanjine(this);
		});

function jisuanjine(_this) {
	// $(this).parents("tr").find("#itemSum").val(0);
	var itemQuantity = $(_this).parents("tr").find("#itemQuantity").val()
			.trim() == "" ? 0 : +$(_this).parents("tr").find("#itemQuantity")
			.val().trim();
	var itemPrice = $(_this).parents("tr").find("#itemPrice").val().trim() == "" ? 0
			: +$(_this).parents("tr").find("#itemPrice").val().trim();
	var workHoursCost = $(_this).parents("tr").find("#workHoursCost").val()
			.trim() == "" ? 0 : +$(_this).parents("tr").find("#workHoursCost")
			.val().trim();
	var itemSum = (itemPrice * itemQuantity + workHoursCost).toFixed(2);

	$(_this).parents("tr").find("#itemSum").val(itemSum);
	// 计算应收总额
	itemSumOnchange();
}

/*
 * 项目总额改变时修改录入维修总额
 */
function itemSumOnchange() {
	var repairShouldSum = 0;
	$("#repairItemTable").find("tr:not(:first)").each(function(j, valtd) {
		var itemSum = $(this).find("input[id='itemSum']").val();
		repairShouldSum = Number(repairShouldSum) + Number(itemSum);
	});
	$("#repairShouldSum").val(repairShouldSum);
}

/*
 * 只可以输入金额
 */
function amount(th) {
	var regStrs = [ [ '^0(\\d+)$', '$1' ], // 禁止录入整数部分两位以上，但首位为0
	[ '[^\\d\\.]+$', '' ], // 禁止录入任何非数字和点
	[ '\\.(\\d?)\\.+', '.$1' ], // 禁止录入两个以上的点
	[ '^(\\d+\\.\\d{2}).+', '$1' ] // 禁止录入小数点后两位以上
	];
	for (i = 0; i < regStrs.length; i++) {
		var reg = new RegExp(regStrs[i][0]);
		th.value = th.value.replace(reg, regStrs[i][1]);
	}
}

/*
 * 只可以输入数字
 */
function validataNum(th) {
	var regStrs = [ [ '[^\\d]+$', '' ], // 禁止录入任何非数字
	// [ '\\-(\\d?)\\-+', '-$1' ], // 禁止录入两个以上的-
	];
	for (i = 0; i < regStrs.length; i++) {
		var reg = new RegExp(regStrs[i][0]);
		th.value = th.value.replace(reg, regStrs[i][1]);
	}
}

// 新增汽车信息
$("#addCar").bind(
		"click",
		function() {
			edit_refresh('新增汽车', '/cr-admin/car/toAddCar.do', '', '', carTable,
					'true');
		});

// 打开lov窗口
function layer_show_refresh_click(title, url, w, h, isFull, callback) {
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

// ---------------------------------------------------------------

// 填充数据方法(汽车列表)
function datatablefunc() {

	// 点击某一行会有选中
	$('.table-sort tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			// table1.$('tr.selected').removeClass('selected');
			$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});

	var returntable = $("#cartable")
			.DataTable(
					{
						"ajax" : {
							url : "/cr-admin/car/queryCarList.do",
							dataSrc : "data"// 解析返回的json对象名称（不写默认接收key为data的json结构）
						},
						// 解析全部列，如果有控制须用null来接收
						"columns" : [ {
							"data" : null
						}, {
							"data" : "carId"
						}, {
							"data" : "carType"
						}, {
							"data" : "licensePlateNum"
						}, {
							"data" : "carChassisNum"
						}, {
							"data" : "engineNum"
						}, {
							"data" : "warrantyStartMiles"
						}, {
							"data" : "ownerName"
						}, {
							"data" : "ownerSex"
						}, {
							"data" : "ownerAddress"
						}, {
							"data" : "ownerMobile"
						}, {
							"data" : "ownerFax"
						}, {
							"data" : "ownerNum"
						}, {
							"data" : null
						}

						],

						// 解析返回的某些列，并对其进行显示设置
						"columnDefs" : [

								{
									// 类型
									targets : 8,
									render : function(data, type, row, meta) {
										if (data == 1) {
											return "男";
										} else {
											return "女";
										}
									}

								},
								{
									// 发布状态
									targets : -1,
									render : function(data, type, row, meta) {
										return "<td class=\"td-status\"><span class=\"label label-success radius\">操作</span></td>";
									}
								}

						],
						"language" : {
							"aria" : {
								"sortAscending" : " - click/return to sort ascending",
								"sortDescending" : " - click/return to sort descending"
							},
							"lengthMenu" : "显示 _MENU_ 记录",
							"zeroRecords" : "对不起，查询不到任何相关数据",
							"emptyTable" : "没有相关数据",
							"loadingRecords" : "正在加载数据-请等待...",
							"info" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录。",
							"infoEmpty" : "当前显示0到0条，共0条记录",
							"infoFiltered" : "（数据库中共为 _MAX_ 条记录）",
							"processing" : "正在加载数据...",
							"search" : "模糊查询：", // 多语言配置文件，可将language的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
							"paginate" : {
								"first" : "首页",
								"previous" : " 上一页 ",
								"next" : " 下一页 ",
								"last" : " 尾页 "
							}
						},
						"order" : [ [ 1, "desc" ] ],// 默认按第二列排序
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
	carTable = returntable;
	return returntable;
}
