<!--汽车管理页面-->
<#import "../common.ftl" as cc>
<!DOCTYPE HTML>
<html>
<!--头部-->
<@cc.html_head title="供应商管理">
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
		供应商管理 <span class="c-gray en">&gt;</span>供应商列表 <a
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
				<div class="panel-header">供应商列表</div>
				<div class="panel-body">
					<div class="mb-5 col-12">
						<span class="ml-5"> <a href="javascript:;"
							class="btn btn-primary radius" id="addSupplier"><i
								class="Hui-iconfont">&#xe600;</i> 新增供应商</a> </span>
					</div>
					<table
						class="table table-border table-bordered table-hover table-bg table-sort"
						id="supplierTable">
						<thead>
							<tr class="text-c">
								<th width="25"></th>
								<th width="50" id="supplierId">id</th>
								<th width="100" id="supplierNum">供应商编号</th>
								<th width="50" id="supplierCompanyName">供应厂商名称</th>
								<th width="70" id="supplierName">联系人</th>
								<th width="90" id="supplierTel">联系方式</th>
								<th width="90" id="supplierAddr">地址</th>
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
				src="${base}cr-admin/js/supplier/supplier.js"></script>
			<script type="text/javascript" src="${base}cr-admin/js/common.js"></script>
			<script type="text/javascript">
			$(function() {
					//增加查询密码校验
					layer_show_refresh_click_pwd('输入秘钥',
							'/cr-admin/stock/toShowStockIncomePricePage.do', '400',
							'200', '', 'false', function() {
								var isShowIncomePrice = localStorage
										.getItem("isShowIncomePrice");
								localStorage.setItem("isShowIncomePrice", "");
								if (isShowIncomePrice
										&& isShowIncomePrice == "true") {
									//校验通过可加载界面
									initData();
								}
							},0);
						
				});
				
				function initData() {
					datatablefunc();
				}
			</script>
</body>
</html>