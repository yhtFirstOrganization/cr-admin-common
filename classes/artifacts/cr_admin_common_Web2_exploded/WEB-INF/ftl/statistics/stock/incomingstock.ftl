<!--项目库存统计页面-->
<#import "../../common.ftl" as cc>

<!DOCTYPE HTML>
<html>
<!--头部-->
<@cc.html_head title="库存进货统计">
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
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		库存统计 <a class="btn btn-success radius r mr-20"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i> </a>
	</nav>
	<div class="pd-5">
		<div class="HuiTab mt-5">
			<!--选项卡tab-->

			<!--选项内容-->
			<div class="panel panel-default">
				<div class="panel-header">库存进货统计列表</div>
				<div class="panel-body">
					<div class="mb-5 col-12"></div>
					<div style="height: 50px;background-color: whitesmoke;">
						<div style="float: right;margin-left:10px;margin-top: -15px;">
							<br />
							<button type="submit" class="btn btn-success radius" id=""
								name="" onclick="initStatisticsData();">
								<i class="Hui-iconfont">&#xe665;</i> 查询
							</button>
							<button type="submit" class="btn btn-success radius" id=""
								name="" onclick="location.href='/cr-admin/stock/expStockComeList.do'">
								<i class="Hui-iconfont">&#xe665;</i> 导出
							</button>
						</div>
						<div style="float: right;margin-top: -15px;">
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
<!-- 					<form action="" method="post" class="form form-horizontal col-12" -->
<!-- 						id="formSum"> -->
<!-- 						<div class="row cl"> -->
<!-- 							<label class="form-label col-2" title="维修中使用配件的总成本">配件总成本：</label> -->
<!-- 							<div class="formControls col-2"> -->
<!-- 								<input type="text" class="input-text" value="" placeholder="" -->
<!-- 									id="stockIncomePrice" name="stockIncomePrice" disabled> -->
<!-- 							</div> -->

<!-- 							<div class="col-2"></div> -->

<!-- 							<label class="form-label col-2" title="维修中使用配件的工时费之和">总工时费：</label> -->
<!-- 							<div class="formControls col-2"> -->
<!-- 								<input type="text" class="input-text" value="" placeholder="" -->
<!-- 									id="workHourCost" name="workHourCost" disabled> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="row cl"> -->
<!-- 							<label class="form-label col-2" title="实收收入">应收入总额：</label> -->
<!-- 							<div class="formControls col-2"> -->
<!-- 								<input type="text" class="input-text" value="" placeholder="" -->
<!-- 									id="itemSum" name="itemSum" disabled> -->
<!-- 							</div> -->

<!-- 							<div class="col-2"></div> -->

<!-- 							<label class="form-label col-2" title="实收收入-配件总成本">应得净盈利：</label> -->
<!-- 							<div class="formControls col-2"> -->
<!-- 								<input type="text" class="input-text" value="" placeholder="" -->
<!-- 									id="profit" name="profit" disabled> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</form> -->
					<table
						class="table table-border table-bordered table-hover table-bg table-sort"
						id="outStockListTable">
						<thead>
							<tr class="text-c">
								<th width="25"></th>
								<th width="50" id="stockId">id</th>
								<th width="100" id="stockNo">库存编号</th>
								<th width="50" id="stockDes">库存描述</th>
								<th width="90" id="stockQuantity">进货总数量</th>
								<th width="70" id="stockCount">进货次数</th>
								<th width="90" id="stockQuantity">进货成本</th>
								<th width="90" id="supplier" style="display:none">供应商</th>
								<th width="120">操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>

			<div></div>

			<script type="text/javascript"
				src="${base}cr-admin/js/statistics/stock/incomingStock.js"></script>
			<script type="text/javascript" src="${base}cr-admin/js/common.js"></script>
			<script type="text/javascript">
			$(function() {
				//增加查询密码校验
				layer_show_refresh_click('输入秘钥',
						'/cr-admin/stock/toShowStockIncomePricePage.do', '400',
						'200', '', 'false', function() {
							var isShowIncomePrice = localStorage
									.getItem("isShowIncomePrice");
							localStorage.setItem("isShowIncomePrice", "");
							if (isShowIncomePrice
									&& isShowIncomePrice == "true") {
								//校验通过可加载界面
								initStatisticsData();
							}
						},0);
					
				//initStatisticsData();
			});
			</script>
</body>
</html>