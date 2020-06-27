<!--维修记录页面-->
<#import "../common.ftl" as cc>
<#import "form.ftl" as form>
<!DOCTYPE HTML>
<html>
<!--头部-->
<@cc.html_head title="维修记录">
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
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 汽车管理 <span class="c-gray en">&gt;</span>维修记录 
		<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
			<i class="Hui-iconfont">&#xe68f;</i>
		</a>
	</nav>
	<div class="pd-5">
		<div class="HuiTab mt-5">
			<!--汽车信息-->
			<@form.html_carInfo>
			</@form.html_carInfo>
		</div>
		<div style="height:60px;">
			<div style="float:right;margin-top:25px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="submit" class="btn btn-success radius" id="" name="" onclick="initData();"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
			</div>
				
			<div style="float:right;">
				<br/>
				<label class="form-label col-2">开始时间：</label>
				<div class="formControls col-4">
					<input type="text" class="input-text Wdate" value="" placeholder="" id="startTime" name="startTime"
						onfocus="WdatePicker({maxDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*"/>
				</div>
			
				<label class="form-label col-2">终止时间：</label>
				<div class="formControls col-4">
					<input type="text" class="input-text Wdate" value="" placeholder="" id="endTime" name="endTime"
						onfocus="WdatePicker({maxDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*"/>
				</div>
                <input type="text" name="repairIds" id="repairIds" class="form-control" style="display:none;" value=${repairIds!""}>
			</div>

		</div>
		<div>
			<ul id="repairListUl" class="Huifold">
			
			</ul>
		</div>
	</div>
<script type="text/javascript" src="${base}cr-admin/js/repair/repair.js"></script>
<script type="text/javascript" src="${base}cr-admin/js/common.js"></script>
<script type="text/javascript">
	var baseUrl = "${base}";
	$(function(){
        if(!$("#perCarId").html()){
			//自定义转化函数
            Date.prototype.Format = function (fmt) {
                let o = {
                    "M+": this.getMonth() + 1, //月份
                    "d+": this.getDate(), //日
                    "h+": this.getHours(), //小时
                    "m+": this.getMinutes(), //分
                    "s+": this.getSeconds(), //秒
                    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                    "S": this.getMilliseconds() //毫秒
                };
                if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
                for (let k in o)
                    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                return fmt;
            };

            var now = new Date();
            now.setMonth(now.getMonth() - 1);
            var recentMonth = now.Format("yyyy-MM-dd 00:00:00");
            $("#startTime").val(recentMonth);
        }
        initData();
	});
	function initData(){
		initPage();
	}
</script> 
</body>
</html>