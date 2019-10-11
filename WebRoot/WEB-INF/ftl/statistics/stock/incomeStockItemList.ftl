<!--项目库存统计页面-->
<#import "../../common.ftl" as cc>
<!DOCTYPE HTML>
<html>
<!--头部-->
<@cc.html_head title="库存统计">
<script type="text/javascript" src="${base}cr-admin/js/DateUtils.js"></script>
<script type="text/javascript"
	src="${base}cr-admin/js/jquery-session.js"></script>
<style type="text/css">
.table>tbody>tr>td {
	text-align: center;
}
</style>
</@cc.html_head>

<body>
	<div class="pd-5">
		<div class="HuiTab mt-5">
			<!--选项卡tab-->

			<!--选项内容-->
			<div class="panel panel-default">
				<div class="panel-header">
					项目进货详细信息列表<a class="btn btn-success radius r mr-20"
						style="line-height:1.6em;margin-top:-3px;float:reight"
						href="javascript:location.replace(location.href);" title="刷新"><i
						class="Hui-iconfont">&#xe68f;</i> </a><a class="btn btn-success radius r mr-20"
						style="line-height:1.6em;margin-top:-3px;float:reight"
						href="/cr-admin/stock/expStockComeItemList.do" title="刷新">
								<i class="Hui-iconfont">&#xe665;</i> 导出
							</a>
				</div>
				<div class="panel-body">

					<form action="" method="post" class="form form-horizontal col-12"
						id="formSum">
						<div style="display:none" id="stockId1" name="stockId1">${stockId}</div>
						<div style="display:none" id="startTime" name="startTime">${startTime}</div>
						<div style="display:none" id="endTime" name="endTime">${endTime}</div>
						
					</form>
					<div class="panel-body">
						<div class="mb-5 col-12"></div>
						<table
							class="table table-border table-bordered table-hover table-bg table-sort"
							id="ComingStockTable">
							<thead>
								<tr class="text-c">
									<th width="5"></th>
									<th width="10" id="stockId" value="${stockId}">${stockId}</th>
									<th width="25" id="stockNo">库存编号</th>
									<th width="50" id="stockName">库存描述</th>
									<th width="70" id="supplier">供应商</th>
									<th width="70" id="supplierTel">供应商联系方式</th>
									<th width="80" id="stockQuantity">进货数量</th>
									<th width="70" id="stockIncomePrice">进货单价</th>
									<th width="70" id="incomingTimes">进货时间</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>

				<div></div>

				<script type="text/javascript"
					src="${base}cr-admin/js/statistics/stock/incomeItemStock.js"></script>
				<script type="text/javascript" src="${base}cr-admin/js/common.js"></script>
				<script type="text/javascript">
					$(function() {
						initStatisticsData();
					});
				</script>
</body>
</html>