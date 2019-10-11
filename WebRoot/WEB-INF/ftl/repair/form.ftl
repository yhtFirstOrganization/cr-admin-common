
<!--汽车信息-->
<#macro html_carInfo>
<#if carInfo??>
	<div class="panel panel-default">
		<div class="panel-header">汽车信息</div>
		<div id="perCarId" style="display:none;">${carInfo.carId!""}</div>
		<div class="panel-body">
			<form class="form-horizontal">
				<div class="row cl">
					<label class="form-label col-2">客户姓名：</label>
					<span class="form-label col-2">${carInfo.ownerName!""}</span>
					<span class="Validform_checktip col-1"></span>
					
					<label class="form-label col-2 Validform_label">车牌号：</label>
					<span class="form-label col-2">${carInfo.licensePlateNum!""}</span>
					<span class="Validform_checktip col-1"></span>
				</div>
				
				<div class="row cl">
					<label class="form-label col-2">联系人：</label>
					<span class="form-label col-2">${carInfo.linker!""}</span>
					<span class="Validform_checktip col-1"></span>
					
					<label class="form-label col-2 Validform_label">车型：</label>
					<span class="form-label col-2">${carInfo.carType!""}</span>
					<span class="Validform_checktip col-1"></span>
				</div>

				<div class="row cl">
					<label class="form-label col-2">联系电话：</label>
					<span class="form-label col-2">${carInfo.linkerMobile!""}</span>
					<span class="Validform_checktip col-1"></span>
		
					<label class="form-label col-2">车架号：</label>
					<span class="form-label col-2">${carInfo.carFrameNum!""}</span>
					<span class="Validform_checktip col-1"></span>
				</div>
				
				<div class="row cl">
					<label class="form-label col-2">车主详细位置：</label>
					<span class="form-label col-3">${carInfo.ownerAddress!""}</span>
					<span class="Validform_checktip col-1"></span>
				</div>
			</form>
		</div>
	</div>
<#else>
<h3>&nbsp;&nbsp;&nbsp;&nbsp;暂未选定车辆信息</h3>
</#if>
</#macro>

<!--维修记录-->
<#macro html_repairInfo>
 			<div class="row cl">
				<label class="form-label col-2">维修工单名称：</label>
				<span  class="form-label col-2">${repairInfo.repairName!""}</span>
				<span class="Validform_checktip col-1"></span>
				
				<label class="form-label col-2 ValidformLabel">维修顾问：</label>
				<span  class="form-label col-2">${repairInfo.serviceAdviser!""}</span>
				<span class="Validform_checktip col-1"></span>
			</div>
			
			<div class="row cl">
				<label class="form-label col-2">维修工：</label>
				<span  class="form-label col-2">${repairInfo.repairMan!""}</span>
				<span class="Validform_checktip col-1"></span>
				
				<label class="form-label col-2">维修时间：</label>
				<span  class="form-label col-2">${repairInfo.repairTime?string('yyyy-MM-dd HH:mm:ss')}</span>
				<span class="Validform_checktip col-1"></span>
			</div>
			
			<div class="row cl">
				
				<label class="form-label col-2">付款方式：</label>
				<span  class="form-label col-2">
				<#if repairInfo.repairPayment=="0">现金<#elseif repairInfo.repairPayment=="1" >支票<#elseif  repairInfo.repairPayment=="2">信用卡</#if>
				</span>
				<span class="Validform_checktip col-1"></span>

				<label class="form-label col-2">维修总额：</label>
				<span  class="form-label col-2">${repairInfo.repairSum!""}</span>
				<span class="Validform_checktip col-1"></span>
				
			</div>
			
	</div>
</#macro>