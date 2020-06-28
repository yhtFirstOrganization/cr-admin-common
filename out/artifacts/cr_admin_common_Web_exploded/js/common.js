/**
 * 打开一个layer窗口，并在关闭的时候刷新当前table（dataTables）
 * 
 * @param title
 * @param url
 * @param w
 * @param h
 * @param table
 * @param isFull
 */
function layer_show_refresh(title, url, w, h, table, isFull) {
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
			table.ajax.reload();
		}
	});
	if(isFull){
		layer.full(index);
	}
}