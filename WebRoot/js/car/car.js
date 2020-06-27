var table1, table2, table3, table4, table5, table6, tableId, carTable;
var currentTable;

// 新增汽车信息
$("#addCar").bind(
		"click",
		function() {
			edit_refresh('新增汽车', '/cr-admin/car/toAddCar.do', '', '', carTable,
					'true');
		});

$("#addRepairAndItem").bind(
		"click",
		function() {
			
			var carId=$('tr.selected').find("td").eq(1).html();
			if(!carId){
				layer.tips("请从列表中选中一条记录再操作.","#addRepairAndItem",{tips: [2,"#c00"]});
//				$("#errspan").text("请先从列表中选中一条记录再操作.");
				return false;
			}
			$("#errspan").text("");
			layer_show_refresh_click('新增维修记录和项目',
					'/cr-admin/repair/toAddRepairAndItem.do?carId='+carId, '', '',
					carTable, 'true');
		});
// 汽车信息新增、编辑时，关闭刷新当前table，需要引入common.js
function edit_refresh(title, url, w, h, table, isFull) {
	layer_show_refresh(title, url, w, h, table, isFull);
}

function layer_show_refresh_click(title, url, w, h, table, isFull) {
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
		ifFull = false;
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
			table.ajax.reload();
			// 双击事件
			$('tr.selected').find("td").dblclick();
		}
	});
	layer.full(index);
}

$("tbody").on(
		'click',
		'a.edit, a.delete',
		function() {
			var hrefId = $(this).attr("class");// href的id
			var carId = $($(this).parents('tr').find("td")[1]).text(); // 通过dom节点
			// var data = table1.row( $(this).parents('tr') ).data();// 通过官方提供的方法
			// var userId = data.userId;
			if (hrefId == "edit") {
				edit_refresh('编辑', '/cr-admin/car/toEditCar.do?&carId='
						+ carId, '', '', carTable, 'true');
			} else {// 删除
				car_delete_refresh(carId, carTable);
			}
		});

// ---------------------------------------------------------------

// 填充数据方法(汽车列表)
function datatablefunc() {

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
		var carid = $(this).find("td").eq(1).html();
		var owner = $(this).find("td").eq(7).html();
		// $.session.set('carid', carid);
		var aobject = window.parent.document.getElementById("repairListId");
		var _hrefVal = $(aobject).attr("_href");
		var ahtml = $(aobject).html();
		// 修改值
		$(aobject).attr("_href", _hrefVal + "?carid=" + carid);
		$(aobject).html(owner + "-维修记录");
		window.parent.document.getElementById("repairListId").click();
		// 恢复原值
		$(aobject).attr("_href", _hrefVal);
		$(aobject).html(ahtml);
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
							"data" : "carFrameNum"
						}, {
							"data" : "ownerName"
						}, {
							"data" : "linker"
						}, {
							"data" : "linkerMobile"
						}, {
							"data" : "ownerAddress"
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
										return "<td class=\"carId\">"
												+ data + "</td>";
									}
								},

								{
									// 发布状态
									targets : -1,
									render : function(data, type, row, meta) {
										return "<td class=\"td-status\"><a title=\"修改\" href=\"javascript:;\" class=\"edit\"><span class=\"label label-success radius\">修改</span></a>" +
												"&nbsp;&nbsp;<a title=\"删除\" href=\"javascript:;\" class=\"delete\"><span class=\"label label-warning radius\">删除</span></a></td>";
									}
								},
								//{"visible": false, "targets": [ 1 ]},// 控制列的隐藏显示
								{
									"orderable" : false,
									"targets" : [ 0, -1, -2 ]// 指定列不参与排序
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
						"initComplete": function(settings, json) {// table初始化后触发
							$("table tr").find("td:eq(1),th:eq(1)").hide();// 设置第一列隐藏
						},
						"drawCallback": function(settings) {
							$("table tr").find("td:eq(1)").hide();// 设置第一列隐藏
						},
						"order" : [ [ 1, "desc" ] ],// 默认按第1列排序
						"stateSave" : false // 状态保存
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

//汽车删除，确认刷新当前table
function car_delete_refresh(carId, table) {
	if(carId == null || carId == ""){
		layer.alert("汽车id不可为空，请刷新重试！", {icon : 2});
		return false;
	}
	$.ajax({
		url : "/cr-admin/repair/queryRepairList.do",
		type : "post",
		data : {
			"carid" : carId
		},
		success : function(data) {
			var confirmMsg="确认要删除吗？";
			if(data&&data.length>0){
				confirmMsg="该车辆存在维修记录信息，该操作将一并删除车辆信息和维修记录信息，是否继续？";
			}
			layer.confirm(confirmMsg, {icon: 3}, function(index) {
				$.ajax({
					type : "post",
					url : "/cr-admin/car/delCarInfo.do",
					data : {
						carId : carId
					},
					success : function(data){
						if(data){
							var code = data.message.code;
							var msg = data.message.msg;
							if(code == "0000"){
								layer.close(index);
								layer.msg("已删除", {
									icon : 1,
									time : 1000
								}, function() {
									table.ajax.reload();
								});
							}else{
								layer.alert(msg, {icon : 2});
								return false;
							}
						}
					}
				});
			});
		}
	});
	
	
}

