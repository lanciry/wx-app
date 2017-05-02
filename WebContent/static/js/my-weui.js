var weui = {
	alert : function(title,callback) {
		title = title ? title :"提示信息";
		var alertHtml = '<div class="weui_dialog_alert">';
		alertHtml += '<div class="weui_mask"></div>';
		alertHtml += '<div class="weui_dialog">';
		alertHtml += ' <div class="weui_dialog_hd" style="padding:1.2em 1em .5em;"><strong class="weui_dialog_title">'+ title + '</strong></div>';
		alertHtml += ' <div class="weui_dialog_ft" style="margin-top:15px;">';
		alertHtml += ' <a href="javascript:;"class="weui_btn_dialog primary">确定</a>';
		alertHtml += ' </div>';
		alertHtml += ' </div>';
		alertHtml += '</div>';
		if ($(".weui_dialog_alert").length == 0) {
			$("body").append($(alertHtml));
		}
		var weui_alert = $(".weui_dialog_alert");
		weui_alert.find(".weui_dialog_title").text(title ?title:"确认提示");
		weui_alert.show();
		weui_alert.find('.weui_btn_dialog').one('click', function() {
			weui_alert.remove();
			if (callback) {
				callback();
			}
		});
	},
	confirm : function(title, callback) {
		if ($(".weui_dialog_confirm").length == 0) {
			var confirmHtml = '<div class="weui_dialog_confirm">'
				+ '<div class="weui_mask"></div>'
				+ '<div class="weui_dialog">'
				+ '<div class="weui_dialog_hd"><strong class="weui_dialog_title"></strong></div>'
				+ '<div class="weui_dialog_ft">'
				+ '<a href="javascript:;"class="weui_btn_dialog default">取消</a>'
				+ '<a href="javascript:;"class="weui_btn_dialog primary">确定</a>'
				+ '</div>' + '</div>' + '</div>';
			$("body").append($(confirmHtml));
		}
		var weui_confirm = $(".weui_dialog_confirm");
		weui_confirm.show();
		weui_confirm.find(".weui_dialog_title").text(title ?title:"确认提示");
		weui_confirm.find('.primary').one('click', function() {
			weui_confirm.remove();
			if (callback) {
				callback(true);
			}
		});
		weui_confirm.find('.default').one('click', function() {
			weui_confirm.remove();
			if (callback) {
				callback(false);
			}
		});
	},
	toast : function (msg,callback){
		if ($("#toast").length == 0) {
			var toastHtml = '<div id="toast">'
				+ '<div class="weui_mask_transparent"></div>'
				+ '<div class="weui_toast">'
				+ '<i class="weui_icon_toast"></i>'
				+ '<p class="weui_toast_content"></p>'
				+ '</div>'
				+ '</div>';
			$("body").append($(toastHtml));
		}
		var weui_toast = $("#toast");
		weui_toast.show();     
		weui_toast.find(".weui_toast_content").text(msg ?msg:"已完成");        
		setTimeout(function () {
			weui_toast.remove();
			if (callback) {
				callback();
			}
        }, 2000);           
	},
	prompt : function(title,callback) {
		title = title ? title :"提示信息";
		var alertHtml = '<div class="weui_dialog_alert">';
		alertHtml += '<div class="weui_mask"></div>';
		alertHtml += '<div class="weui_dialog">';
		alertHtml += ' <div class="weui_dialog_hd"><strong class="weui_dialog_title">'+ title + '</strong></div>';
		alertHtml += '<div class="weui_dialog_bd">';
		alertHtml += '<textarea class="weui_textarea" maxlength="200" id="reason" placeholder="'+title+'" rows="3"></textarea>';
		alertHtml += '</div>';
		alertHtml += ' <div class="weui_dialog_ft">';
		alertHtml += ' <a href="javascript:;"class="weui_btn_dialog primary">确定</a>';
		alertHtml += ' </div>';
		alertHtml += ' </div>';
		alertHtml += '</div>';
		if ($(".weui_dialog_alert").length == 0) {
			$("body").append($(alertHtml));
		}
		var weui_alert = $(".weui_dialog_alert");
		weui_alert.find(".weui_dialog_title").text(title ?title:"确认提示");
		weui_alert.show();
		weui_alert.find('.weui_btn_dialog').one('click', function() {
			if (callback) {
				var reason = $('#reason').val();
				weui_alert.remove();
				callback(reason);
			}
		});
	}
};
