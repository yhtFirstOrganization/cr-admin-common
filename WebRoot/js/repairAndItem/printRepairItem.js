var table1, table2, table3, table4, table5, table6, tableId, carTable;
var editTable;

$(function() {
	var repairId = $("#repairId").val();
	editTable = $("#repairItem")
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
							"data" : "itemQuantity"
						}, {
							"data" : "itemPrice"
						}, {
							"data" : "workHoursCost"
						}, {
							"data" : "itemSum"
						} ],
						"columnDefs" : [
								{
									"orderable" : false,
									"targets" : [ 1, 2, 3, 4, 5 ]
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
                            $("#print").click();
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


$("#print").click(function () {
    bdhtml = window.document.body.innerHTML;
    sprnstr = "<!--startprint-->";
    eprnstr = "<!--endprint-->";
    prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);
    prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));
    window.document.body.innerHTML = prnhtml;
    window.print();
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
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

