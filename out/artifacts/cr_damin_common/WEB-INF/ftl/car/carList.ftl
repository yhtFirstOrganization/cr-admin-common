<!--汽车管理页面-->
<#import "../common.ftl" as cc>
<!DOCTYPE HTML>
<html>
<!--头部-->
<@cc.html_head title="汽车管理">
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
		汽车管理 <span class="c-gray en">&gt;</span>汽车列表 <a
			class="btn btn-success radius r mr-20"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i>
		</a>
	</nav>
	<div class="pd-5">
		<div class="HuiTab mt-5">
			<!--选项卡tab-->

			<!--选项内容-->
			<div class="panel panel-default">
				<div class="panel-header">汽车信息列表</div>
				<div class="panel-body">
				<div class="mb-5 col-12">
					<span class="ml-5">
						<a href="javascript:;" class="btn btn-primary radius" id="addCar"><i class="Hui-iconfont">&#xe600;</i> 新增汽车</a>
					</span> 
					<span class="ml-5">
						<a href="javascript:;" class="btn btn-primary radius" id="addRepairAndItem"><i class="Hui-iconfont">&#xe600;</i> 新增维修记录和项目</a>
						<span id="errspan" style="color:red;"></span>
					</span> 
				</div>
				<table class="table table-border table-bordered table-hover table-bg table-sort" id="cartable">
					<thead>
						<tr class="text-c">
							<th width="25"></th>
							<th width="50" id="carId">id</th>
							<th width="100" id="carType">车型</th>
							<th width="50" id="licensePlateNum">车牌号</th>
							<th width="70" id="carFrameNum">车架号</th>
							<th width="90" id="ownerName">客户姓名</th>
							<th width="90" id="linker">联系人</th>
							<th width="90" id="linkerMobile">联系电话</th>
							<th width="200" id="ownerAddress">车主详细位置</th>
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

<script type="text/javascript" src="${base}cr-admin/js/car/car.js"></script>
<script type="text/javascript" src="${base}cr-admin/js/common.js"></script>
<script type="text/javascript">
	$(function(){
		initData();
	});
	function initData(){
		datatablefunc();
	}

</script> 
</body>
</html>