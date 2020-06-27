<!--新增项目校验秘钥信息-->
<#import "../common.ftl" as cc>
<!DOCTYPE HTML>
<html>
<!--头部-->
<@cc.html_head title="校验秘钥">
	<script type="text/javascript" src="${base}cr-admin/js/DateUtils.js"></script>
</@cc.html_head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal"
			id="validateOtherPassword">
			<div class="row cl">
				<label class="form-label col-3">输入秘钥：</label>
				<div class="formControls col-4">
					<input type="password" class="input-text" value="" placeholder="" id="otherPassword" name="otherPassword"  AUTOCOMPLETE="OFF">
				</div>
				<span class="Validform_checktip col-2"></span>
				
			</div>
			
			<div class="row cl">
				<div class="col-10 col-offset-2">
					<button class="btn btn-secondary radius" type="submit" id="submitBtn">
					<i class="Hui-iconfont">&#xe632;</i>提交</button>
				</div>
			</div>
		</form>
	</div>
	</div>

	<script type="text/javascript" src="${base}cr-admin/js/stock/stock.js"></script>
	<script type="text/javascript">
		var pageType = "inCome";
		$(function() {
			initValidateOtherPasswordPage();
		});
	</script>
</body>
</html>