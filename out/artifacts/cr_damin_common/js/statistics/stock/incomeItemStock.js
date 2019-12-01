var stockTable;

// 弹出窗口
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
// 按钮点击事件
$("tbody").on(
		'click',
		'a.track',
		function() {
			var hrefId = $(this).attr("class");// href的id
			var stockId = $($(this).parents('tr').find("td")[1]).text(); // 通过dom节点
			var dateType = options = $("#select option:selected").val();
			var startTime = document.getElementById("startTime").value;
			var endTime = document.getElementById("endTime").value;
			// var data = table1.row( $(this).parents('tr') ).data();//
			// 通过官方提供的方法
			// var userId = data.userId;
			if (hrefId == "track") {
				layer_show_refresh_click('详细进货记录',
						'/cr-admin/stock/toIncomeItemStockList.do?stockId=' + stockId
								+ "&startTime=" + startTime + "&endTime="
								+ endTime, '', '',
						stockTable, 'false');
			}
		});

// 填充数据方法(库存列表)
function initStatisticsData() {

	// 点击某一行会有选中
	$('.table-sort tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
		} else {
			$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});

	// 双击
	$('.table-sort tbody').on(
			'dblclick',
			'tr',
			function() {
				// 暂时无动作
				var hrefId = $(this).attr("class");// href的id
				var stockId = document.getElementById("stockId1").value; // 通过dom节点
				var dateType = options = $("#select option:selected").val();
				var startTime = document.getElementById("startTime").value;
				var endTime = document.getElementById("endTime").value;
				// var data = table1.row( $(this).parents('tr') ).data();//
				// 通过官方提供的方法
				// var userId = data.userId;
				if (hrefId == "track") {
					layer_show_refresh_click('详细进货记录',
							'/cr-admin/stock/toIncomeItemStockList.do?stockId=' + stockId
							+ "&startTime=" + startTime + "&endTime="
							+ endTime, '', '', stockTable, 'false');
				}
			});

	var dateType = options = $("#select option:selected").val();
	var startTime = $("#startTime").html();
	var endTime = $("#endTime").html();
	var stockId = $("#stockId1").html(); // 通过dom节点
	var url = "/cr-admin/stock/getIncomeItemStock.do";
//	$.ajax({
//		url : url,
//		type : "post",
//		data : {
//			endTime : endTime,
//			startTime : startTime,
//			dateType : dateType
//		},
//		success : function(data) {
//			$("#stockIncomePrice").val(data.stock.stockIncomePrice);
//			$("#workHourCost").val(data.stock.workHourCost);
//			$("#itemSum").val(data.stock.itemSum);
//			$("#profit").val(data.stock.profit);
//		}
//	});
	var returntable = $("#ComingStockTable")
			.DataTable(
					{
						"destroy" : true,
						"ajax" : {
							url : url,
							"data" : {
								endTime : endTime,
								startTime : startTime,
								stockId : stockId
							},
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
							"data" : "stockName"
						}, {
							"data" : "supplier"
						}, {
							"data" : "supplierTel"
						}, {
							"data" : "stockQuantity"
						}, {
							"data" : "stockIncomePrice"
						}, {
							"data" : "incomingTimeStr"
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
							// $("table tr").find("td:eq(4),th:eq(4)").hide();

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

	if (startTime && endTime) {
		if (startTime > endTime) {
			alert("结束时间不能早于开始时间。");
			return false;
		}
	}

	return returntable;
}
