function init(Time, dateType) {
	var worldMapContainer = document.getElementById('money');
	// 用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
	var resizeWorldMapContainer = function() {
		worldMapContainer.style.width = (window.innerWidth - 100) + 'px';
		worldMapContainer.style.height = (window.innerHeight - 100) + 'px';
	};
	// 设置容器高宽
	resizeWorldMapContainer();
	// 初始化图表
	var myChart = echarts.init(worldMapContainer);
	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		title : {
			text : '销售金额统计' 
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '应收金额', '实收金额' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		dataZoom : {
			show : true,
			realtime : true,
			start : 0,
			end : 100,
			showAllSymbol : true
		},
		xAxis : {
			data : [],
			type : 'category',
			// 折线在x上是点
			boundaryGap : false,
			axisLabel : {
				// X轴刻度配置
				interval : 'auto',
				// 0：表示全部显示不间隔；auto:表示自动根据刻度个数和宽度自动设置间隔个数
				rotate : 20
			}
		},
		yAxis : {},
		series : [ {
			name : '实收金额',
			type : 'line',
			smooth : true,
			data : []
		}, {
			name : '应收金额',
			type : 'line',
			smooth : true,
			data : []
		} ]
	});

	myChart.showLoading(); // 数据加载完之前先显示一段简单的loading动画
	var datatime = []; // 类别数组（实际用来盛放X轴坐标值）
	var repairSum = []; // 应有收入（实际用来盛放Y坐标值）
	var repairSctualSum = []; // 实际收入（实际用来盛放Y坐标值）
	var startTime = document.getElementById("startTime").innerHTML;
	var endTime = document.getElementById("endTime").innerHTML;
	var dateType = document.getElementById("dateType").innerHTML;

	$.ajax({
		async : false, // 异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		url : "/cr-admin/statistic/getMonery.do", // 请求发送到TestServlet处		
		type : "post",
		data : {
			endTime : endTime,
			startTime : startTime,
//			time : Time,
			dateType : dateType
		},
		dataType : "json", // 返回数据形式为json
		success : function(date) {
			// 请求成功时执行该函数内容，result即为服务器返回的json对象
			var result = date.data;
			for ( var i = result.length - 1; i >= 0; i--) {
				repairSum.push(result[i].repairSum); // 挨个取出数量并填入数量数组
			}
			for ( var i = result.length - 1; i >= 0; i--) {
				repairSctualSum.push(result[i].repairSctualSum); // 挨个取出数量并填入数量数组
			}
			for ( var i = result.length - 1; i >= 0; i--) {
				datatime.push(result[i].datatime); // 挨个取出类别并填入类别数组
			}

			myChart.hideLoading(); // 隐藏加载动画
			myChart.setOption({ // 加载数据图表
				xAxis : {
					data : datatime
				},
				series : [ {
					// 根据名字对应到相应的系列
					type : 'line',
					datatime : '实收金额',
					data : repairSctualSum
				}, {
					// 根据名字对应到相应的系列
					type : 'line',
					datatime : '应收金额',
					data : repairSum
				} ]
			});
		},
		error : function(errorMsg) {
			// 请求失败时执行该函数
			alert("图表请求数据失败!");
			myChart.hideLoading();
		}
	});
}

function closeWin() {
	$("#win").window('close');
}
$().ready(function() {
	init();
});
var oneMonth = $("#oneMonth");
var threeMonth = $("#threeMonth");
var sixMonth = $("#sixMonth");
var oneYear = $("#oneYear");
var dateType = $("#dateType").val();
$("#oneMonth").click(function() {
	init(1, dateType);
});
$("#threeMonth").click(function() {
	init(3, dateType);
});
$("#sixMonth").click(function() {
	init(6, dateType);
});
$("#oneYear").click(function() {
	init(12, dateType);
});
function initData() {
	init(null, dateType);
}
