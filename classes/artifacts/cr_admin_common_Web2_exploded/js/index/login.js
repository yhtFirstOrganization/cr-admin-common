$(function() {

	$(".queding").click(function() {

		if (checkInput()) {
			$.ajax({
				url : path + "/index/login.do",
				type : "post",
				dataType : "json",
				data : {
					"loginName" : $("#loginName").val(),
					"password" : $("#password").val()
				},
				success : function(data) {
					if (data) {
						if (data.code == "200") {
							window.location.href = path + "/index/toIndex.do";
						} else {
							alert(data.msg);
						}
					}
				}
			});
		}
	});

	function checkInput() {

		// 判断用户名
		if ($("input[name=loginName]").val() == null
				|| $("input[name=loginName]").val() == "") {
			alert("用户名不能为空");
			$("input[name=loginName]").focus();
			return false;
		}

		// 判断密码
		if ($("input[name=password]").val() == null
				|| $("input[name=password]").val() == "") {
			alert("密码不能为空");
			$("input[name=password]").focus();
			return false;
		}

		return true;
	}

});

function toEditPasswordPage() {
	layer_show_refresh_click('修改密码', '/cr-admin/index/toPassword.do', '400',
			'400', '', 'false', function() {
				
			});
}

function layer_show_refresh_click(title, url, w, h, table, isFull, callback) {
	if (title == null || title == '') {
		title = false;
	}
	;
	if (url == null || url == '') {
		url = "404.html";
	}
	;
	if (w == null || w == '') {
		w = 800;
	}
	;
	if (h == null || h == '') {
		h = ($(window).height() - 50);
	}
	;
	if (isFull == 'true') {
		isFull = true;
	} else {
		isFull = false;
	}
	var index = layer.open({
		type : 2,
		area : [ w + 'px', h + 'px' ],
		fix : false, // 不固定
		maxmin : true,
		shade : 0.4,
		title : title,
		content : url,
		end : function(index) {// 层销毁后触发的回调，刷新当前table
			callback();
		}
	});
	if (isFull) {
		layer.full(index);
	}
}
