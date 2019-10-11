<!--按月记录实收应收金额页面-->
<#import "/common.ftl" as cc>
<!DOCTYPE HTML>
<html>
<!--头部-->
<@cc.html_head title="销售统计统计（按月）">
	<script type="text/javascript" src="${base}cr-admin/js/DateUtils.js"></script>
	<script type="text/javascript" src="${base}cr-admin/js/jquery-session.js"></script> 
	<style type="text/css">
		.table>tbody>tr>td{
        	text-align:center;
		}
	</style>
</@cc.html_head>

<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 查询统计 <span class="c-gray en">&gt;</span>销售金额统计 
		<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
			<i class="Hui-iconfont">&#xe68f;</i>
		</a>
	</nav>
	<div class="pd-5">
		<div class="HuiTab mt-5">
		</div>
		<div>
			<table class="table table-border table-bordered table-hover table-bg table-sort" id="salesItemtable" style="width:100%">
					<thead>
					<tr class="text-c">
						<th width="25"></th>
						<th width="25" id="id" style="display:none" value="${datatime}">${datatime}</th>
						<th width="50" id="datatime">日期</th>
						<th width="100" id="repairSum">应收总金额</th>
						<th width="100" id="repairSctualSum">实收总金额</th>
					</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
		</div>
	</div>
<script type="text/javascript" src="${base}cr-admin/js/statistics/moneyStatistics/salesMonthStatistics.js"></script>
<script type="text/javascript" src="${base}cr-admin/js/common.js"></script>
<script type="text/javascript">

</script> 
</body>
</html>