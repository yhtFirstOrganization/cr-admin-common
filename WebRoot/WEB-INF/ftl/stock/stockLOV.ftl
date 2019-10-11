<!--项目库存管理页面-->
<#import "../common.ftl" as cc>
<!DOCTYPE HTML>
<html>
<!--头部-->
<@cc.html_head title="项目库存管理">
	<script type="text/javascript" src="${base}cr-admin/js/DateUtils.js"></script>
	<script type="text/javascript" src="${base}cr-admin/js/jquery-session.js"></script> 
	<style type="text/css">
		.table>tbody>tr>td{
        	text-align:center;
		}
	</style>
</@cc.html_head>

<body>
<div class="pd-5">
	<div class="HuiTab mt-5">
		<!--选项卡tab-->
		
		<!--选项内容-->
		<div class="panel panel-default">
			<div class="panel-body">
				<table class="table table-border table-bordered table-hover table-bg table-sort" id="stocktable">
					<thead>
						<tr class="text-c">
							<th width="25"></th>
							<th width="50" id="stockId">id</th>
							<th width="100" id="stockNo">项目编号</th>
							<th width="50" id="stockDes">项目描述</th>
							<th width="70" id="stockPriceIncome">项目进价</th>
							<th width="70" id="stockPrice">项目单价</th>
							<th width="90" id="stockQuantity">项目数量</th>
							<th width="90" id="stockUnit">项目单位</th>
							<th width="90" id="workHoursCost">安装工时费</th>
							<th width="120">操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>

	<div>
</div>

<script type="text/javascript" src="${base}cr-admin/js/stock/stock.js"></script>
<script type="text/javascript" src="${base}cr-admin/js/common.js"></script>
<script type="text/javascript">
	$(function(){
		initData();
	});
	function initData(){
		var isLOV = true;
		datatablefunc(isLOV);
	}

</script> 
</body>
</html>