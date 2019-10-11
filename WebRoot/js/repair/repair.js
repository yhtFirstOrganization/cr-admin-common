var repairTable;

// 修改维修记录和项目信息
function updateRepairAndItemInfo(_this, event) {
	// 获取维修id
	var li = $(_this).closest("li");
	var repairid = li.attr("repairid");
	layer_show_full('修改维修记录和项目信息',
			'/cr-admin/repair/toUpdateRepairAndItem.do?repairId=' + repairid,
			'', '');
	event.stopPropagation();
}

// 汽车信息新增、编辑时，关闭刷新当前table，需要引入common.js
function edit_refresh(title, url, w, h, table, isFull) {
	layer_show_refresh(title, url, w, h, table, isFull);
}

// 弹层全屏
function layer_show_full(title, url, w, h) {
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
	var index = layer.open({
		type : 2,
		area : [ w + 'px', h + 'px' ],
		fix : false, // 不固定
		maxmin : true,
		shade : 0.4,
		title : title,
		content : url,
	});
	layer.full(index);
}

// ---------------------------------------------------------------

// 初始化界面
function initPage(repairId) {
	var perCarId = $("#perCarId").html();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	if (startTime && endTime) {
		if (startTime > endTime) {
			alert("结束时间不能早于开始时间。");
			return false;
		}
	}
	$.ajax({
		url : "/cr-admin/repair/queryRepairList.do",
		type : "post",
		data : {
			"startTime" : startTime,
			"endTime" : endTime,
			"carid" : perCarId
		},
		success : function(data) {
			drawingListData(data);
			if (repairId) {
				var li = $("li[repairid=" + repairId + "]");
				if (!li.find("#divinli").is(':visible')) {
					li.find("h4").click();
				} else {
					datatablefunc(li.find("h4"));
				}
			}
		}
	});
}
// 导出excel
function exportRepairInfo(_this, event) {
	// 获取维修id
	var li = $(_this).closest("li");
	var repairid = li.attr("repairid");
	$.ajax({
		url : "/cr-admin/repairItem/exportToExcel.do",
		type : "post",
		data : {
			"repairId" : repairid
		},
		success : function(data) {
			var code = data.code;
			var message = data.msg;
			if (!code || code == "0") {
				alert("文件导出失败!请确认没有打开文件【" + message + "】,再重新导出。");
			} else {
				// alert("文件导出成功!请查看【" + message + "】");
				window.location.href = baseUrl + message;
			}

		}
	});
	event.stopPropagation();
}
// 描绘列表
function drawingListData(data) {
	$("#repairListUl").html("");
	var firstRepairId = "";
	if (!data || data.length <= 0) {
		$("#repairListUl").append(
				"<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;暂无维修记录！</h4>");
	}
	$
			.each(
					data,
					function(index, datainfo) {
						var repairId = datainfo.repairId;
						if (index == 0) {
							firstRepairId = repairId;
						}
						var carid = datainfo.carId;
						var repairName = datainfo.repairName;
						var repairTime = datainfo.repairTime;
						var updateTime = datainfo.updateTime;
						var warrantyStartMiles = datainfo.warrantyStartMiles;// 里程数
						var repairMan = datainfo.repairMan;
						var repairSum = datainfo.repairSum;
						var repairActualSum = datainfo.repairActualSum;
						var isEnoughDays = false;
						// 判断时间
						if (repairTime && updateTime) {
							// 应该保养时间小于当前时间 而且 更新时间小于应该保养时间则提示保养
							var curDate = new Date(); // 开始时间
							var enoughDaysStart = repairTime
									+ (24 * 3600 * 1000 * 180);// 应该保养时间（维修时间过去180天）
							if (updateTime < enoughDaysStart
									&& enoughDaysStart < curDate.getTime()) {
								isEnoughDays = true;
							}
						}
						var hstyle = "";
						if (!repairActualSum && repairActualSum <= 0) {
							hstyle = " style='background-color:crimson;'";
						}
						// 保养
						if (isEnoughDays) {
							hstyle = " style='background-color:yellow;'";
						}

						$("#repairListUl")
								.append(
										"<li class=\"item\" id=\""
												+ repairId
												+ "\" repairid=\""
												+ repairId
												+ "\" carid=\""
												+ carid
												+ "\"><h4"
												+ hstyle
												+ ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
												+ repairName
												+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;里程数："
												+ warrantyStartMiles
												+ "公里"
												+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维修工："
												+ repairMan
												+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维修应收总额："
												+ repairSum
												+ "元"
												+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;实收总额："
												+ repairActualSum
												+ "元"
												+ "<span class=\"label label-success radius\" style=\"float:right;\" onclick=\"viewPerCar(this,event);\">查车</span>"
												+ "<span style=\"float:right\">&nbsp;&nbsp;</span>"
												+ "<span class=\"label label-primary radius\" style=\"float:right;\" onclick=\"exportRepairInfo(this,event);\">导出</span>"
												+ "<span style=\"float:right\">&nbsp;&nbsp;</span>"
												+ "<span class=\"label label-warning radius\" style=\"float:right;\" onclick=\"repair_delete_refresh(this);\">删除</span>"
												+ "<span style=\"float:right\">&nbsp;&nbsp;</span>"
												+ "<span class=\"label label-success radius\" style=\"float:right;\" onclick=\"updateRepairAndItemInfo(this,event);\">修改</span><b>+</b></h4><div id=\"divinli\" class=\"info\">"
												+ getRepairItemHtml()
												+ "</div></li>");
					});

	$.Huifold("#repairListUl .item h4", "#repairListUl .item .info", "fast", 1,
			"click", callback); /*
								 * 5个参数顺序不可打乱，分别是：相应区,隐藏显示的内容,速度,类型,事件
								 * ,执行操作后的回调函数（自加）
								 */
	// 默认打开第一个
	if (firstRepairId) {
		// $("#" + firstRepairId + " h4").click();
	}
}

function callback(_this) {
	// $(_this).next().find("table").prop("id", "repairItemTableContent");
	datatablefunc(_this);
	// 清空该table id
	// $("#repairItemTableContent").prop("id", "noid");
}

/**
 * 更换原因 字段显示改为 配件编号
 * 
 * @returns {String}
 */
function getRepairItemHtml() {
	var repairItemHtml = "<div class=\"panel-body\"><div class=\"l mb-5\"></div><table class=\"table table-border table-bordered table-hover table-bg table-sort\" id=\"repairItemTableContent\"><thead><tr class=\"text-c\"><th width=\"25\"></th><th width=\"50\" id=\"itemId\">id</th><th width=\"100\" id=\"repairId\">维修工单编号</th><th width=\"100\" id=\"itemDes\">项目描述</th><th width=\"90\" id=\"itemPrice\">项目单价</th><th width=\"90\" id=\"itemQuantity\">项目数量</th><th width=\"90\" id=\"itemUnit\">项目单位</th><th width=\"90\" id=\"itemSum\">项目总额</th><th width=\"90\" id=\"replaceReason\">配件编号</th><th width=\"90\" id=\"workHoursCost\">工时费</th></tr></thead><tbody></tbody></table></div>";
	return repairItemHtml;
}

/**
 * 打开该维修记录
 * 
 * @returns {String}
 */
function open_repair_li(repairid) {
	initPage(repairid);
}

// 填充数据方法(维修列表)
function datatablefunc(_this) {
	// 获取维修id
	var li = $(_this).closest("li");
	var repairid = li.attr("repairid");
	$("li div.info").html("");
	li.find("#divinli").append(getRepairItemHtml());

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

	var returntable = $("#repairItemTableContent").DataTable({
		"ajax" : {
			url : "/cr-admin/repairItem/queryRepairItemList.do",
			data : {
				repairId : repairid
			},
			dataSrc : "data"// 解析返回的json对象名称（不写默认接收key为data的json结构）
		},
		// 解析全部列，如果有控制须用null来接收
		"columns" : [ {
			"data" : null
		}, {
			"data" : "repairItemId"
		}, {
			"data" : "repairId"
		// }, {
		// "data" : "itemNum"
		}, {
			"data" : "itemDes"
		}, {
			"data" : "itemPrice"
		}, {
			"data" : "itemQuantity"
		}, {
			"data" : "itemUnit"
		}, {
			"data" : "itemSum"
		}, {
			"data" : "replaceReason"
		}, {
			"data" : "workHoursCost"
		}

		],
		// 解析返回的某些列，并对其进行显示设置
		"columnDefs" : [
		/*
		 * { // 类型 targets : 8, render : function(data, type, row, meta) {
		 * if(data == 1){ return "男"; }else{ return "女"; } } }, { // 发布状态
		 * targets : -1, render : function(data, type, row, meta) { return "<td class=\"td-status\"><span
		 * class=\"label label-success radius\">操作</span></td>"; } }
		 */
		{
			"visible" : false,
			"targets" : [ 1, 2 ],
			"searchable" : false,
		},// 控制列的隐藏显示
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

// 跳转到对应车界面
function viewPerCar(_this, event) {
	var li = $(_this).closest("li");
	var carid = li.attr("carid");
	$
			.ajax({
				url : "/cr-admin/car/queryCarInfo.do",
				type : "post",
				data : {
					"carId" : carid
				},
				success : function(data) {
					var owner = data.ownerName;
					// 打开新界面
					// $.session.set('carid', carid);
					var aobject = window.parent.document
							.getElementById("repairListId");
					var _hrefVal = $(aobject).attr("_href");
					var ahtml = $(aobject).html();
					// 修改值
					$(aobject).attr("_href", _hrefVal + "?carid=" + carid);
					$(aobject).html(owner + "-维修记录");
					window.parent.document.getElementById("repairListId")
							.click();
					// 恢复原值
					$(aobject).attr("_href", _hrefVal);
					$(aobject).html(ahtml);
					// 阻止冒泡
					event.stopPropagation();
				}
			});

}
// 删除维修记录和维修项目，确认刷新当前table
function repair_delete_refresh(_this) {
	// 获取维修id
	var li = $(_this).closest("li");
	var repairId = li.attr("repairid");
	if (repairId == null || repairId == "") {
		layer.alert("维修记录id不可为空，请刷新重试！", {
			icon : 2
		});
		return false;
	}
	layer.confirm("确认要删除吗？", {
		icon : 3
	}, function(index) {
		$.ajax({
			type : "post",
			url : "/cr-admin/repair/delRepairInfoAndItem.do",
			data : {
				repairId : repairId
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
							li.remove();
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
