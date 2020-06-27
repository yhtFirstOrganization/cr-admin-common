<!--头部宏-->
<#macro html_head title="${user.userName}汽车服务有限公司" description="${user.userName}汽车服务维修后台" keywords="${user.userName}汽车服务维修后台,${user.userName}汽车维修,${user.userName},汽车维修" author="${user.userName}汽车服务">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,member-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<LINK rel="Bookmark" href="/favicon.ico" >
<LINK rel="Shortcut Icon" href="${base}cr-admin/images/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${base}cr-admin/lib/html5.js"></script>
<script type="text/javascript" src="${base}cr-admin/lib/respond.min.js"></script>
<script type="text/javascript" src="${base}cr-admin/lib/PIE_IE678.js"></script>
<![endif]-->
<link href="${base}cr-admin/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${base}cr-admin/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${base}cr-admin/skin/default/skin.css" rel="stylesheet" type="text/css" id="skin" />
<link href="${base}cr-admin/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${base}cr-admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="${base}cr-admin/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${base}cr-admin/js/jquery-1.11.1.js"></script> 
<script type="text/javascript" src="${base}cr-admin/lib/Validform/5.3.2/Validform.min.js"></script> 
<script type="text/javascript" src="${base}cr-admin/lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="${base}cr-admin/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="${base}cr-admin/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="${base}cr-admin/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${base}cr-admin/js/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="${base}cr-admin/js/H-ui.js"></script> 
<script type="text/javascript" src="${base}cr-admin/js/H-ui.admin.js"></script> 
<script type="text/javascript" src="${base}cr-admin/js/bootstrap.js"></script> 
<#nested>
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>${title}</title>
<meta name="keywords" content="${keywords}">
<meta name="description" content="${description}">
<meta name="author" content="${author}">
</head>
</#macro>

<!--省市区级联-->
<#macro getChildNode_js>
 	<script>
		$(function(){
 	 		$("#s_province").change(function(){
 	 			var prov_id=$("#s_province").val();
 	 			if(prov_id==null||prov_id==""||prov_id=="-1"){
 	 				return;
 	 			}
 	 			$.ajax({
					type:"POST",
					url : '/cr-admin/region/getCitys.do',
    				data :{
    					fatherid:prov_id
    				},
    				async: false,
    				success : function(data){
    					$("#s_city").empty();
    					$("#s_city").append('<option value="">市</option>');
    					for(var i =0 ;i<data.length;i++){
    						var html ='<option value='+data[i].regionId+'>'+data[i].name+'</option>';
    						$("#s_city").append(html);
    					}
    				}
				
				});	
 	 		});
 	 		$("#s_city").change(function(){
 	 			var city_id=$("#s_city").val();
 	 			if(city_id==null||city_id==""||city_id=="-1"){
 	 				return;
 	 			}
 	 			$.ajax({
					type:"POST",
					url : '/cr-admin/region/getCitys.do',
    				data :{
    					fatherid:city_id
    				},
    				async: false,
    				success : function(data){
    					$("#s_county").empty();
    					$("#s_county").append('<option value="">区/县</option>');
    					for(var i =0 ;i<data.length;i++){
    						var html ='<option value='+data[i].regionId+'>'+data[i].name+'</option>';
    						$("#s_county").append(html);
    					}
    				}
				
				});	
 	 		});
	 	});
	</script>
</#macro>
