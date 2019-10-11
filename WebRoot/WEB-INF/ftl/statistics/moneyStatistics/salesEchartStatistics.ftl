<!--维修金额统计页面-->
<#import "/common.ftl" as cc>
<!DOCTYPE HTML>
<html>
<!--头部-->
<@cc.html_head title="维修金额统计">
	<script type="text/javascript" src="${base}cr-admin/js/DateUtils.js"></script>
	<script type="text/javascript" src="${base}cr-admin/js/jquery-session.js"></script> 
	<style type="text/css">
		.table>tbody>tr>td{
        	text-align:center;
		}
		.echartMoney{
			height:80%;
			width:200px;
		}
		.chooseButton{
		width: 23%;
    	min-height: 31px;
    	font-size: 14px;
    	text-align: center;
    	}
	</style>
</@cc.html_head>

<body>

<div class="pd-5">
	<div class="HuiTab mt-5">
		<div class="panel panel-default">
			<div class="panel-body">
				<div style="float: right;display:none">
					<button class="chooseButton" type="submit" id="oneMonth">最近一个月</button>
					<button class="chooseButton" id="threeMonth">最近三个月</button>
					<button class="chooseButton" id="sixMonth">最近半年</button>
					<button class="chooseButton" id="oneYear">最近一年</button>
				</div>
				<div style="display:none" id="startTime" name="startTime">${startTime}</div>
				<div style="display:none" id="endTime" name="endTime">${endTime}</div>
				<div style="display:none" id="dateType" name="dateType">${dateType}</div>
				<div class="echartMoney" id="money" >
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="${base}cr-admin/js/echarts.js"></script>
<script type="text/javascript" src="${base}cr-admin/js/statistics/moneyStatistics/salesEchartStatistics.js"></script>
<script type="text/javascript" src="${base}cr-admin/js/common.js"></script>
</body>
</html>