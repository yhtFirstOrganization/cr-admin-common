var Operator = (function() {

	return {

		init : function() {
			var datatime = document.getElementById("id").innerHTML;
			var returntable = $("#salesItemtable")
					.DataTable(
							{
								"ajax" : {
									url : "/cr-admin/statistic/getDateMoneyByMonth.do?month="
											+ datatime,
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
									$("table tr").find("td:eq(1),th:eq(1)")
											.hide();// 设置第一列隐藏
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
	};
})();
$().ready(function() {
	Operator.init();
});

// 填充数据方法(汽车列表)
