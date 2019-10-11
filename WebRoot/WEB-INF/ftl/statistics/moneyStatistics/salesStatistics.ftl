<!--维修金额统计页面-->
<#import "/common.ftl" as cc>
<!DOCTYPE HTML>
<html>
<!--头部-->
<@cc.html_head title="维修金额统计">
<script type="text/javascript" src="${base}cr-admin/js/DateUtils.js"></script>
<script type="text/javascript"
	src="${base}cr-admin/js/jquery-session.js"></script>
<style type="text/css">
.table>tbody>tr>td {
	text-align: center;
}

.echartMoney {
	height: 80%;
	width: 200px;
}

.chooseButton {
	width: 23%;
	min-height: 31px;
	font-size: 14px;
	text-align: center;
}
</style>
</@cc.html_head>

<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		查询统计 <span class="c-gray en">&gt;</span>销售金额统计 <a
			class="btn btn-success radius r mr-20"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	<div class="pd-5">
		<div class="HuiTab mt-5">
			<!--选项卡tab-->

			<!--选项内容-->
			<div class="panel panel-default">
				<div class="panel-header" style="height:30px">
					维修金额统计列表
					<div style="float:right;">
						<button type="button" class="btn btn-success radius" id="" name=""
							onclick="toEchart();">图像化列表展示</button>
					</div>
				</div>
				<div class="panel-body">
					<div style="height: 78px;background-color: whitesmoke;">
						<div style="float: left;width: 50%;text-align: right;">
							<br /> <label class="form-label col-2" style="width: 70%;">选择查询方式：
								<select id="select" onchange="gradeChange()"
								style="width: 100px;height: 30px;font-size: inherit;">
									<option value="date">按日期查询</option>
									<option value="month" selected="selected">按月份查询</option>
									<option value="year">按年份查询</option>
							</select> </label>
						</div>
						<div style="float: right;margin-left:10px">
							<br />
							<button type="submit" class="btn btn-success radius" id=""
								name="" onclick="initData();">
								<i class="Hui-iconfont">&#xe665;</i> 查询
							</button>
						</div>
						<div style="float: right;">
							<br /> <label class="form-label col-2">开始时间：</label>
							<div class="formControls col-4">
								<input type="text" class="input-text Wdate" value=""
									placeholder="" id="startTime" name="startTime"
									onfocus="WdatePicker({maxDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									datatype="*" />
							</div>

							<label class="form-label col-2">终止时间：</label>
							<div class="formControls col-4">
								<input type="text" class="input-text Wdate" value=""
									placeholder="" id="endTime" name="endTime"
									onfocus="WdatePicker({maxDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									datatype="*" />
							</div>
						</div>
						<div style="clear:both"></div>
					</div>
					<form action="" method="post" class="form form-horizontal col-12"
						id="formSum">
						<div class="row cl">
							<label class="form-label col-2" title="维修中使用配件的总成本">配件总成本：</label>
							<div class="formControls col-2" id="inComingPrice">
								<input type="text" class="input-text hide" value="" placeholder=""
									id="incomingHide" name="incoming">							
								<input type="text" class="input-text" value="" placeholder=""
									id="incoming" name="incoming" disabled>
							</div>

							<div class="col-2"></div>

							<label class="form-label col-2" title="维修中使用配件的销售总定价">配件总定价：</label>
							<div class="formControls col-2">
								<input type="text" class="input-text" value="" placeholder=""
									id="outsum" name="outsum" disabled>
							</div>
						</div>
						<div class="row cl">
							<label class="form-label col-2" title="维修中使用配件的销售总定价+配件安装工时费">维修应收总额：</label>
							<div class="formControls col-2">
								<input type="text" class="input-text" value="" placeholder=""
									id="repairShouldSum" name="repairShouldSum" disabled>
							</div>

							<div class="col-2"></div>

							<label class="form-label col-2" title="维修单实际收取费用总额">维修实收总额：</label>
							<div class="formControls col-2">
								<input type="text" class="input-text" value="" placeholder=""
									id="repairActualSum" name="repairActualSum" disabled>
							</div>
						</div>
					</form>
					<div style="margin-top: 10px;">
						<table
							class="table table-border table-bordered table-hover table-bg table-sort"
							id="salestable">
							<thead>
								<tr class="text-c">
									<th width="25"></th>
									<th width="25" id="id"></th>
									<th width="50" id="datatime">时间</th>
									<th width="100" id="repairSum">应收总金额</th>
									<th width="100" id="repairSctualSum">实收总金额</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						<div></div>
					</div>
				</div>
			</div>

			<script type="text/javascript" src="${base}cr-admin/js/common.js"></script>
			<script type="text/javascript"
				src="${base}cr-admin/js/statistics/moneyStatistics/salesStatistics.js"></script>
</body>
</html>