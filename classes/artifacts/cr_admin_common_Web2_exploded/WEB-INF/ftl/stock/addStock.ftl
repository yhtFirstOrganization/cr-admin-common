<!--新增项目库存信息-->
<#import "../common.ftl" as cc>
<!DOCTYPE HTML>
<html>
<!--头部-->
<@cc.html_head title="新增项目库存信息">
	<script type="text/javascript" src="${base}cr-admin/js/DateUtils.js"></script>
</@cc.html_head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal"
			id="formStock">
			<input type="text" class="input-text" value="${stockInfo.stockId!""}" style="display:none;" id="stockId">
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>项目编号：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="${stockInfo.stockNo!""}" placeholder="" id="stockNo" name="stockNo"
						datatype="*1-20" errormsg="项目编号应在20字以内！" nullmsg="请填写项目编号！">
				</div>
				<span class="Validform_checktip col-2"></span>
				
				<label class="form-label col-2"><span class="c-red">*</span>项目描述：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="${stockInfo.stockDes!""}" placeholder="" id="stockDes" name="stockDes"
						datatype="*1-50" errormsg="项目描述应在50字以内！" nullmsg="请填写项目描述！">
				</div>
				<span class="Validform_checktip col-2"></span>
				
			</div>
			
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>项目进价：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="${stockInfo.stockPriceIncome!""}" placeholder="" id="stockPriceIncome" name="stockPriceIncome"
					datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" errormsg="项目进价应为金额数字！" nullmsg="请填写项目进价！">
				</div>
				<span class="Validform_checktip col-2"></span>
				
				<label class="form-label col-2"><span class="c-red">*</span>项目价格：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="${stockInfo.stockPrice!""}" placeholder="" id="stockPrice" name="stockPrice"
					datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" errormsg="项目价格应为金额数字！" nullmsg="请填写项目价格！">
				</div>
				<span class="Validform_checktip col-2"></span>
			</div>
			
			<div class="row cl">
			
				<label class="form-label col-2 "><span class="c-red">*</span>项目个数：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="${stockInfo.stockQuantity!""}" placeholder="" id="stockQuantity" name="stockQuantity"
						datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" errormsg="项目个数应为数字！" nullmsg="请填写项目个数！">
				</div>
				<span class="Validform_checktip col-2"></span>
				
				<label class="form-label col-2 "><span class="c-red">*</span>项目单位：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="${stockInfo.stockUnit!""}" placeholder="" id="stockUnit" name="stockUnit"
						datatype="*1-20" errormsg="请填写正确的项目单位！" nullmsg="请填写项目单位！">
				</div>
				<span class="Validform_checktip col-2"></span>
			</div>
			<div class="row cl">
				<label class="form-label col-2 ">供应商名称：</label>
				<div class="formControls col-2">
					<span> <select name="makeupCoSe" id="makeupCoSe"
						onchange="change()" onclick="clickSelect()"
						style="position: absolute;border:1pt solid #e2dfdf;width: 94.5%;height: 100%;clip: rect(-1px 190px 190px 113px);">
							<#list supplierList as supplier>
							<option value='${supplier.perId }'>${supplier.perCompanyName
								}</option> </#list>
							<OPTION VALUE="" SELECTED></OPTION>
					</select> </span> <span> <input type="text" class="input-text" id="supplier"
						oninput="setinput(this);" name="supplier" placeholder="请选择或输入"
						value="${stockInfo.supplier!""}" datatype="/^\s*$/|*1-20" errormsg="请填写正确的供应商名称！"> </span>
				</div>
				<span class="Validform_checktip col-2"></span> <label
					class="form-label col-2">供应商电话：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text"
						value="${stockInfo.supplierTel!" "}" placeholder=""
						id="supplierTel" name="supplierTel" datatype="/^\s*$/|*1-20" errormsg="请填写正确的供应商电话！">
				</div>
				<span class="Validform_checktip col-2"></span>
			</div>
			<div class="row cl">
				<label class="form-label col-2">安装工时费：</label>
				<div class="formControls col-2">
					<input type="text" class="input-text" value="${stockInfo.workHoursCost!""}" placeholder="" id="workHoursCost" name="workHoursCost"
						datatype="/^\s*$/|/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" errormsg="安装工时费应为金额数字！">
				</div>
				<span class="Validform_checktip col-2"></span>
			</div>
			<div class="row cl" style="display:none;">
				<label class="form-label col-2 "><span class="c-red">*</span>是否启用：</label>
				<div class="formControls col-2 skin-minimal" id="isdeleteDiv">
					<div class="radio-box">
						<input type="radio" value="0" id="isdelete" name="isdelete" checked datatype="*" nullmsg="请选择启用类型！"/>
						<label for="isdelete">启用</label>
					</div>
					
					<div class="radio-box">
						<input type="radio" value="1" id="isdelete" name="isdelete"/>
						<label for="isdelete">停用</label>
					</div>
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
		pageType = "addOrEdit";
		$(function() {
			initAddPage();
		});
		$('.skin-minimal input').iCheck({
			checkboxClass: 'icheckbox-blue',
			radioClass: 'iradio-blue',
			increaseArea: '20%'
		});
		
		function clickSelect() {
			var supplierId = $("#makeupCoSe").val();
			var supplierName = $("#makeupCoSe  option:selected").text();
			if (supplierId == null || supplierId == "" || supplierId == "-1") {
				return;
			}
		}
		function change() {
			var supplierId = $("#makeupCoSe").val();
			var supplierName = $("#makeupCoSe  option:selected").text();
			$("#supplier").val(supplierName);
			if (supplierId == null || supplierId == "" || supplierId == "-1") {
				$("#supplierTel").val("");
				return;
			}
			$.ajax({
				type : "GET",
				url : '/cr-admin/supplier/getSupplierTel.do',
				data : {
					perId : supplierId
				},
				async : false,
				success : function(data) {
					$("#supplierTel").empty();
					$("#supplierTel").val(data.supplierTel);
				}

			});
		}

		//select支持模糊查询

		var List = [];
		$(function() {
			var makeupCoSe = document.getElementById("makeupCoSe").options;
			for ( var i = 0; i < makeupCoSe.length; i++) {
				List[i] = makeupCoSe[i].value + "|" + makeupCoSe[i].text;
			}

		});
		function setinput(this_) {
			var Html = "";
			var supplier = document.getElementById("supplier");

			var makeupCoSe = document.getElementById("makeupCoSe").options;
			if (!(supplier.value.length < 1)) {

				makeupCoSe.length = 0;
				for ( var i = 0; i < List.length; i++) {
					if (List[i].split("|")[1].indexOf(supplier.value) > -1) {
						makeupCoSe.add(new Option(List[i].split("|")[1],
								List[i].split("|")[0]));
					}
				}
				if (makeupCoSe.length < 1) {
					$("#supplierTel").val("");
				}
			}else{
				var makeupCoSe = document.getElementById("makeupCoSe").options;
				makeupCoSe.length = 0;
				for ( var i = 0; i < List.length; i++) {
					makeupCoSe.add(new Option(List[i].split("|")[1], List[i]
							.split("|")[0]));
				}
			}
		}
	</script>
	<!--引入省市区js-->
	<@cc.getChildNode_js></@cc.getChildNode_js>
</body>
</html>