//----------------------------------初始化页面-------------------------------------------
$().ready(function() {
	//增加查询密码校验
	layer_show_refresh_click('输入秘钥',
			'/cr-admin/stock/toShowStockIncomePricePage.do', '400',
			'200', '', 'false', function() {
				var isShowIncomePrice = localStorage
						.getItem("isShowIncomePrice");
				localStorage.setItem("isShowIncomePrice", "");
				if (isShowIncomePrice
						&& isShowIncomePrice == "true") {
					//校验通过可加载界面
					datatablefunc();
					initSum();
				}
			},0);
	
	// 点击某一行会有选中
	$('.table-sort tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			// $(this).removeClass('selected');
		} else {
			// table1.$('tr.selected').removeClass('selected');
			$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});

	// 双击
	$('.table-sort tbody').on('dblclick', 'tr', function() {
		var datatime = $(this).find("td").eq(2).html();
		getDateMoneyByMonth(datatime, event);
	});
//	datatablefunc();
//	initSum();
});
function datatablefunc(dateType) {

	var dateType = options = $("#select option:selected").val();
	var startTime = document.getElementById("startTime").value;
	var endTime = document.getElementById("endTime").value;
	var url = "/cr-admin/statistic/getMonery.do";
	var returntable = $("#salestable").DataTable({
		"destroy" : true,
		"ajax" : {
			"data" : {
				endTime : endTime,
				startTime : startTime,
				dateType : dateType
			},
			url : url,
			dataSrc : "data"// 解析返回的json对象名称（不写默认接收key为data的json结构）
		},
		// 解析全部列，如果有控制须用null来接收
		"columns" : [ {
			"data" : null
		}, {
			"data" : null
		}, {
			"data" : "datatime"
		}, {
			"data" : "repairSum"
		}, {
			"data" : "repairSctualSum"
		} ],

		// 解析返回的某些列，并对其进行显示设置
		"columnDefs" : [

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

	carTable = returntable;
	return returntable;
}

// 初始化界面
function initSum(dateType) {
	var dateType = options = $("#select option:selected").val();
	var startTime = document.getElementById("startTime").value;
	var endTime = document.getElementById("endTime").value;
	var url = "/cr-admin/statistic/getMonery.do";

	if (startTime && endTime) {
		if (startTime > endTime) {
			alert("结束时间不能早于开始时间。");
			return false;
		}
	}
	$.ajax({
		url : url,
		type : "post",
		data : {
			endTime : endTime,
			startTime : startTime,
			dateType : dateType
		},
		success : function(data) {
//			$("#incomingHide").val(data.incoming);
			$("#incoming").val("--");
			$("#incoming").val(data.incoming);
			$("#outsum").val(data.outsum);
			$("#repairShouldSum").val(data.repairSum);
			$("#repairActualSum").val(data.repairSctualSum);
		}
	});
	$("#inComingPrice").unbind("click");
	$("#inComingPrice").bind(
			"click",
			function() {
				return;
				layer_show_refresh_click('输入秘钥',
						'/cr-admin/stock/toShowStockIncomePricePage.do', '400',
						'200', '', 'false', function() {
							var isShowIncomePrice = localStorage
									.getItem("isShowIncomePrice");
							localStorage.setItem("isShowIncomePrice", "");
							if (isShowIncomePrice
									&& isShowIncomePrice == "true") {
								var incoming = $("#incomingHide").val();
								$("#incoming").val(incoming);
								window.setTimeout(function() {
									$("#incoming").val("--");
								}, 5000);
							}
						});
			});

}

function layer_show_refresh_click(title, url, w, h, table, isFull, callback,isClose) {
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
		closeBtn:isClose,
		end : function(index) {// 层销毁后触发的回调，刷新当前table
			callback();
		}
	});
	if (isFull) {
		layer.full(index);
	}
}

// ----------------------------------调转到图形化界面--------------------------------------------------------------

function toEchart() {
	var dateType = options = $("#select option:selected").val();
	var startTime = document.getElementById("startTime").value;
	var endTime = document.getElementById("endTime").value;
	// 获取查询方式
//	layer_show_full("销售金额统计图形化展示",
//			'/cr-admin/statistic/toSalesEchart.do?dateType=' + dateType
//					+ '&endTime=' + endTime + '&startTime=' + startTime, '90%',
//			'90%');
	layer_show_full("销售金额统计图形化展示",
			'/cr-admin/statistic/toSalesEchart.do?dateType=' + dateType
			+ '&endTime=' + endTime + '&startTime=' + startTime, '90%',
			window.innerHeight - 100 + "px");
	event.stopPropagation();
}

function closeWin() {
	$("#win").window('close');
}

// ----------------------------查询按钮-------------------------------
function initData() {
	var dateType = options = $("#select option:selected").val();
	datatablefunc(dateType);
	initSum(dateType);
}
// ----------------------------下拉框onclick事件----------------------
function gradeChange() {
	initData();
}

// ----------------------------双击一条信息弹出
// ---------------------------------------------------
function getDateMoneyByMonth(_this, event) {
	// 获取维修id
	var li = $(_this).closest("li");
	var datatime = _this;
	layer_show_full(datatime + '月详细记录',
			'/cr-admin/statistic/toSalesMonth.do?datatime=' + datatime, '', '');
	event.stopPropagation();
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
		w = "800px";
	}
	;
	if (h == null || h == '') {
		h = ($(window).height() - 50) + "px";
	}
	;
	var index = layer.open({
		type : 2,
		area : [ w, h ],
		fix : false, // 不固定
		maxmin : true,
		shade : 0.4,
		title : title,
		content : url,
	});
}
