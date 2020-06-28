
<!--后台主页-->
<#import "../common.ftl" as cc>
<!DOCTYPE HTML>
<html>
<!--头部-->
<@cc.html_head title="${user.userName}汽车服务有限公司-admin"> </@cc.html_head>

<body>
	<header class="Hui-header cl">
		<a class="Hui-logo l" title="H-ui.admin v2.3" href="/">${user.userName}汽车服务有限公司</a>
		<span class="Hui-subtitle l">V0.1</span>
		<nav class="mainnav cl" id="Hui-nav">
			<ul>
				<li class="dropDown dropDown_click"><a href="javascript:;"
					class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i
						class="Hui-iconfont">&#xe6d5;</i> </a>
					<ul class="dropDown-menu radius box-shadow">
						<li><a href="javascript:;" id="addCar"><i
								class="Hui-iconfont">&#xe616;</i> 汽车</a></li>
					</ul>
				</li>
			</ul>
		</nav>
		<ul class="Hui-userbar">
			<li>${user.userName}</li>
			<li class="dropDown dropDown_hover"><a href="javascript:;"
				class="dropDown_A">${user.loginName} <i class="Hui-iconfont">&#xe6d5;</i>
			</a>
				<ul class="dropDown-menu radius box-shadow">
					<li><a href="javascript:logout();">退出</a></li>
					<li><a onclick="toEditPasswordPage();">修改密码</a>
					</li>
				</ul>
			</li>
			<li id="Hui-skin" class="dropDown right dropDown_hover"><a
				href="javascript:;" title="换肤"><i class="Hui-iconfont"
					style="font-size:18px">&#xe62a;</i> </a>
				<ul class="dropDown-menu radius box-shadow">
					<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a>
					</li>
					<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
					<li><a href="javascript:;" data-val="green" title="绿色">绿色</a>
					</li>
					<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
					<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a>
					</li>
					<li><a href="javascript:;" data-val="orange" title="绿色">橙色</a>
					</li>
				</ul>
			</li>
		</ul>
		<a aria-hidden="false" class="Hui-nav-toggle" href="#"></a>
	</header>

	<aside class="Hui-aside">
		<input runat="server" id="divScrollValue" type="hidden" value="" />
		<div class="menu_dropdown bk_2">
			<dl id="menu-user">


				<dt>
					<a _href="/cr-admin/car/toCarList.do" href="javascript:;"
						id="carListId">汽车列表</a>
				</dt>
				<dd></dd>
				<dt>
					<a _href="/cr-admin/repair/toRepairList.do" href="javascript:;"
						id="repairListId" name="repairListId">维修记录列表</a>
				</dt>
				<dd></dd>
				<dt>
					<a _href="/cr-admin/stock/toStockList.do" href="javascript:;"
						id="stockListId" name="stockListId">库存管理</a>
				</dt>
				<dd></dd>
				<dt>
					<a _href="/cr-admin/supplier/toSupplierList.do" href="javascript:;"
						id="supplierListId" name="supplierListId">供应商管理</a>
				</dt>
				<dd></dd>

				<dt>查询统计</dt>
				<dd>
					<ul>
						<li><a _href="/cr-admin/statistic/toSalesMoneyList.do"
							id="salesMoneyListId" href="javascript:;">维修金额统计</a></li>
						<li><a _href="/cr-admin/stock/toStockStatisticList.do"
							id="stockStatisticListId" href="javascript:;">库存销量统计</a></li>
						<li><a _href="/cr-admin/stock/toComingStock.do"
							id="comingStockListId" href="javascript:;">库存进货统计</a></li>
						<li><a _href="/cr-admin/carUseStock/toCarUseStock.do"
							id="carUseStockListId" href="javascript:;">维修情况统计</a></li>
					</ul>
				</dd>
			</dl>

		</div>
	</aside>
	<div class="dislpayArrow">
		<a class="pngfix" href="javascript:void(0);"
			onClick="displaynavbar(this)"></a>
	</div>
	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active"><span title="欢迎页" data-href="welcome.html">欢迎页</span><em></em>
					</li>
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group">
				<a id="js-tabNav-prev" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i> </a><a
					id="js-tabNav-next" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i> </a>
			</div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display:none" class="loading"></div>
				<div class="responsive">
					<div class="row cl">
						<div class="col-2"></div>
						<div class="col-8">
							<h1 class="text-c" style="margin-top:100px;">
								欢迎使用${user.userName}汽车服务有限公司后台管理平台！</h1>
						</div>
						<div class="col-2"></div>
					</div>
				</div>
				<iframe scrolling="yes" frameborder="0" src="" id="frame"></iframe>

			</div>
		</div>
	</section>

	<script type="text/javascript">
	// 新增汽车
	$("#addCar").bind("click", function(){
		var index = layer.open({
			type: 2,
			maxmin : true,
			title: "新增汽车",
			content: '/cr-admin/car/toAddCar.do'
		});
		layer.full(index);
	});
	
	// 新增客服信息
	$("#addCustomer").bind("click", function(){
		layer_show('新增客服', '/cr-admin/customer/toAddCustomer.do', '', '510');
	});
	
	// 新增友情链接
	$("#addLinks").bind("click", function(){
		layer_show('新增友情链接', '/cr-admin/links/toAddLinks.do', '', '510');
	});
	
</script>
<script src="${base}/cr-admin/js/index/login.js"></script>
	<script type="text/javascript">
var _hmt = _hmt || [];
(function() {
	var hm = document.createElement("script");
	hm.src = "//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
	var s = document.getElementsByTagName("script")[0]; 
	s.parentNode.insertBefore(hm, s)})();
	var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
	document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E"));
	function logout(){
		$.ajax({
			type: "POST",
			dataType:"json",
			url : '/cr-admin/index/logout.do',
			success : function(data){
				var code = data.code;
				var msg = data.msg;
				if(code=="200"){
					window.location="/cr-admin";
				}
			}
			
		});	
	}
	function password(){
		alert(1);
		$.ajax({
			type: "POST",
			dataType:"json",
			url : '/cr-admin/index/toPassword.do',
			success : function(data){
				var code = data.code;
				var msg = data.msg;
				if(code=="200"){
					window.location="/cr-admin/toPassword.do";
				}
			}
			
		});	
	}
	
	
$(function(){
		window.parent.document.getElementById("carListId").click();
	});
	
</script>
</body>
</html>
