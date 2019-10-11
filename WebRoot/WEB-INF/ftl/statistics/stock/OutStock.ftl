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
					项目库存信息列表<a class="btn btn-success radius r mr-20"
						style="line-height:1.6em;margin-top:-3px;float:reight"
						href="javascript:location.replace(location.href);" title="刷新"><i
						class="Hui-iconfont">&#xe68f;</i> </a>
				</div>
				<div class="panel-body">

					<form action="" method="post" class="form form-horizontal col-12"
						id="formSum">
						<div style="display:none" id="startTime" name="startTime">${startTime}</div>
						<div style="display:none" id="endTime" name="endTime">${endTime}</div>
						<div style="display:none" id="dateType" name="dateType">${dateType}</div>
						<div class="row cl">
							<label class="form-label col-2" >库存编号：</label>
							<div class="formControls col-2">
								<input type="text" class="input-text" value="" placeholder=""
									id="stockNo" name="stockNo" disabled>
							</div>

							<div class="col-2"></div>

							<label class="form-label col-2">库存描述：</label>
							<div class="formControls col-2">
								<input type="text" class="input-text" value="" placeholder=""
									id="stockDes" name="stockDes" disabled>
							</div>
						</div>
						<div class="row cl">
							<label class="form-label col-2" >供应商：</label>
							<div class="formControls col-2">
								<input type="text" class="input-text" value="" placeholder=""
									id="supplier" name="supplier" disabled>
							</div>

							<div class="col-2"></div>

							<label class="form-label col-2" >库存单位：</label>
							<div class="formControls col-2">
								<input type="text" class="input-text" value="" placeholder=""
									id="stockUnit" name="stockUnit" disabled>
							</div>
						</div>
					</form>
					<div class="panel-body">
						<div class="mb-5 col-12"></div>
						<table
							class="table table-border table-bordered table-hover table-bg table-sort"
							id="OutstockTable">
							<thead>
								<tr class="text-c">
									<th width="5"></th>
									<th width="10" id="stockId" value="${stockId}">${stockId}</th>
									<th width="25" id="carId">汽车id</th>
									<th width="25" id="stockNo">库存编号</th>
									<th width="50" id="license_plate_num">车牌号</th>
									<th width="70" id="repairCount">维修次数</th>
									<th width="80" id="userdStockQuantity">总使用库存数量</th>
									<th width="70" id="stockIncomePrice">总库存成本</th>
									<th width="70" id="workHourCost">总工时费成本</th>
									<th width="50" id="workHoursCost">收入</th>
									<th width="80">操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>

				<div></div>

				<script type="text/javascript"
					src="${base}cr-admin/js/statistics/stock/outStockStatistics.js"></script>
				<script type="text/javascript" src="${base}cr-admin/js/common.js"></script>
				<script type="text/javascript">
					$(function() {
						initStatisticsData();
					});
				</script>
</body>
</html>