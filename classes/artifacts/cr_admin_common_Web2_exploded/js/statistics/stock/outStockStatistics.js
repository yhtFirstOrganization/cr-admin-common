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
			var carId = $($(this).parents('tr').find("td")[2]).text(); // 通过dom节点
			// $.session.set('carid', carid);
			var aobject = window.parent.parent.document
					.getElementById("repairListId");
			var _hrefVal = $(aobject).attr("_href");
			var ahtml = $(aobject).html();
			// 修改值
			$(aobject).attr(
					"_href",
					_hrefVal + "?carid=" + carId + "&startTime=" + startTime
							+ "&emdTime=" + endTime);
			$(aobject).html("维修记录");
			window.parent.parent.document.getElementById("repairListId")
					.click();
			// 恢复原值
			$(aobject).attr("_href", _hrefVal);
			$(aobject).html(ahtml);
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
	var stockId = document.getElementById("stockId").innerHTML;
	var dateType = options = $("#select option:selected").val();
	var startTime = document.getElementById("startTime").innerHTML;
	var endTime = document.getElementById("endTime").innerHTML;
	// 双击
	$('.table-sort tbody').on(
			'dblclick',
			'tr',
			function() {
				var carid = $(this).find("td").eq(2).html();
				// $.session.set('carid', carid);
				var aobject = window.parent.parent.document
						.getElementById("repairListId");
				var _hrefVal = $(aobject).attr("_href");
				var ahtml = $(aobject).html();
				// 修改值
				$(aobject).attr(
						"_href",
						_hrefVal + "?carid=" + carid + "&startTime="
								+ startTime + "&emdTime=" + endTime);
				$(aobject).html("维修记录");
				window.parent.parent.document.getElementById("repairListId")
						.click();
				// 恢复原值
				$(aobject).attr("_href", _hrefVal);
				$(aobject).html(ahtml);
			});

	var url = "/cr-admin/stock/toOutStockItemList.do?stockId=" + stockId
			+ "&startTime=" + startTime + "&endTime=" + endTime + "&dateType="
			+ dateType;

	var returntable = $("#OutstockTable")
			.DataTable(
					{
						"ajax" : {
							url : url,
							dataSrc : "data"// 解析返回的json对象名称（不写默认接收key为data的json结构）
						},
						// 解析全部列，如果有控制须用null来接收
						"columns" : [ {
							"data" : null
						}, {
							"data" : "stockId"
						}, {
							"data" : "carId"
						}, {
							"data" : "stockNo"
						}, {
							"data" : "license_plate_num"
						}, {
							"data" : "repairCount"
						}, {
							"data" : "userdStockQuantity"
						}, {
							"data" : "cost"
						}, {
							"data" : "workHourCost"
						}, {
							"data" : "itemSum"
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
										var tdHtml = "<td class=\"td-status\"><a title=\"查看详细维修记录\" href=\"javascript:;\" class=\"track\"><span class=\"label label-success radius\">查看详细维修记录</span></a>"
												+ "</td>";
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
							$("table tr").find("td:eq(2),th:eq(2)").hide();// 设置第一列隐藏
						},
						"drawCallback" : function(settings) {
							$("table tr").find("td:eq(1)").hide();// 设置第一列隐藏
							$("table tr").find("td:eq(2)").hide();// 设置第一列隐藏
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

	$.ajax({
		url : url,
		type : "post",
		success : function(data) {
			$("#stockNo").val(data.stock.stockNo);
			$("#stockDes").val(data.stock.stockDes);
			$("#supplier").val(data.stock.supplier);
			$("#stockUnit").val(data.stock.stockUnit);
		}
	});
	return returntable;
}
