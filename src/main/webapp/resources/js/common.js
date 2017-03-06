var messages = {
	"admin.message.success" : "操作成功",
	"admin.message.error" : "操作错误",
	"dialog.ok" : "确&nbsp;&nbsp;定",
	"dialog.cancel" : "取&nbsp;&nbsp;消",
	"dialog.deleteConfirm" : "您确定要删除吗？",
	"dialog.refreshConfirm" : "您确定要刷新服务器配置吗？",
	"admin.dialog.clearConfirm" : "您确定要清空吗？",
	"admin.dialog.resetConfirm" : "您确定要重置吗？",
	"admin.dialog.logIgnore" : "您确定要忽略此功能的记录日志吗?",
	"admin.browser.title" : "选择文件",
	"admin.browser.upload" : "本地上传",
	"admin.browser.parent" : "上级目录",
	"admin.browser.orderType" : "排序方式",
	"admin.browser.name" : "名称",
	"admin.browser.size" : "大小",
	"admin.browser.type" : "类型",
	"admin.browser.select" : "选择文件",
	"admin.upload.sizeInvalid" : "上传文件大小超出限制",
	"admin.upload.typeInvalid" : "上传文件格式不正确",
	"admin.upload.invalid" : "上传文件格式或大小不正确",
	"validate.required" : "必填",
	"validate.email" : "E-mail格式错误",
	"validate.url" : "网址格式错误",
	"validate.date" : "日期格式错误",
	"validate.dateISO" : "日期格式错误",
	"validate.pointcard" : "信用卡格式错误",
	"validate.number" : "只允许输入数字",
	"validate.digits" : "只允许输入零或正整数",
	"validate.minlength" : "长度不允许小于{0}",
	"validate.maxlength" : "长度不允许大于{0}",
	"validate.rangelength" : "长度必须在{0}-{1}之间",
	"validate.min" : "不允许小于{0}",
	"validate.max" : "不允许大于{0}",
	"validate.range" : "必须在{0}-{1}之间",
	"validate.accept" : "输入后缀错误",
	"validate.equalTo" : "两次输入不一致",
	"validate.remote" : "输入错误",
	"validate.integer" : "只允许输入整数",
	"validate.positive" : "只允许输入正数",
	"validate.negative" : "只允许输入负数",
	"validate.decimal" : "数值超出了允许范围",
	"validate.pattern" : "格式错误",
	"validate.filetype" : "文件格式错误"
};
var str = "~`@!#$%^&*()_+-={}|[]\:;'<>?/.,";
//限制输入特殊字符.只有在IE和chrome有效.
function limitStr() {
	var realKey = String.fromCharCode(event.keyCode);
	if (str.indexOf(realKey) >= 0) {
		event.returnValue = false;
	} else {
		event.returnValue = true;
	}
}

// 添加Cookie
function addCookie(name, value, options) {
	if (arguments.length > 1 && name != null) {
		if (options == null) {
			options = {};
		}
		if (value == null) {
			options.expires = -1;
		}
		if (typeof options.expires == "number") {
			var time = options.expires;
			var expires = options.expires = new Date();
			expires.setTime(expires.getTime() + time * 1000);
		}
		document.cookie = encodeURIComponent(String(name))
				+ "="
				+ encodeURIComponent(String(value))
				+ (options.expires ? "; expires="
						+ options.expires.toUTCString() : "")
				+ (options.path ? "; path=" + options.path : "")
				+ (options.domain ? "; domain=" + options.domain : ""),
				(options.secure ? "; secure" : "");
	}
}

// 获取Cookie
function getCookie(name) {
	if (name != null) {
		var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name))
				+ "=([^;]*)").exec(document.cookie);
		return value ? decodeURIComponent(value[1]) : null;
	}
}

// 移除Cookie
function removeCookie(name, options) {
	addCookie(name, null, options);
}

// 货币格式化
function currency(value, showSign, showUnit) {
	if (value != null) {
		var price;
		if (setting.priceRoundType == "roundHalfUp") {
			price = (Math.round(value * Math.pow(10, setting.priceScale)) / Math
					.pow(10, setting.priceScale)).toFixed(setting.priceScale);
		} else if (setting.priceRoundType == "roundUp") {
			price = (Math.ceil(value * Math.pow(10, setting.priceScale)) / Math
					.pow(10, setting.priceScale)).toFixed(setting.priceScale);
		} else {
			price = (Math.floor(value * Math.pow(10, setting.priceScale)) / Math
					.pow(10, setting.priceScale)).toFixed(setting.priceScale);
		}
		if (showSign) {
			price = setting.currencySign + price;
		}
		if (showUnit) {
			price += setting.currencyUnit;
		}
		return price;
	}
}

// 多语言
function message(code) {
	if (code != null) {
		var content = messages[code] != null ? messages[code] : code;
		if (arguments.length == 1) {
			return content;
		} else {
			if ($.isArray(arguments[1])) {
				$.each(arguments[1], function(i, n) {
					content = content.replace(
							new RegExp("\\{" + i + "\\}", "g"), n);
				});
				return content;
			} else {
				$.each(Array.prototype.slice.apply(arguments).slice(1),
						function(i, n) {
							content = content.replace(new RegExp("\\{" + i
									+ "\\}", "g"), n);
						});
				return content;
			}
		}
	}
}

(function($) {
	var zIndex = 100;

	// 消息框
	var $message;
	var messageTimer;
	$.message = function() {
		var message = {};
		if ($.isPlainObject(arguments[0])) {
			message = arguments[0];
		} else if (typeof arguments[0] === "string"
				&& typeof arguments[1] === "string") {
			message.type = arguments[0];
			message.content = arguments[1];
		} else {
			return false;
		}

		if (message.type == null || message.content == null) {
			return false;
		}

		if ($message == null) {
			$message = $('<div class="xxMessage"><div class="messageContent message'
					+ message.type + 'Icon"><\/div><\/div>');
			if (!window.XMLHttpRequest) {
				$message.append('<iframe class="messageIframe"><\/iframe>');
			}
			$message.appendTo("body");
		}

		$message.children("div").removeClass(
				"messagewarnIcon messageerrorIcon messagesuccessIcon")
				.addClass("message" + message.type + "Icon").html(
						message.content);
		$message.css({
			"margin-left" : -parseInt($message.outerWidth() / 2),
			"z-index" : zIndex++
		}).show();

		clearTimeout(messageTimer);
		messageTimer = setTimeout(function() {
			$message.hide();
		}, 3000);
		return $message;
	};

	// 对话框
	$.dialog = function(options) {
		var settings = {
			width : 320,
			height : "auto",
			modal : true,
			ok : message("admin.dialog.ok"),
			cancel : message("admin.dialog.cancel"),
			onShow : null,
			onClose : null,
			onOk : null,
			onCancel : null
		};
		$.extend(settings, options);

		if (settings.content == null) {
			return false;
		}

		var $dialog = $('<div class="xxDialog"><\/div>');
		var $dialogTitle;
		var $dialogClose = $('<div class="dialogClose"><\/div>').appendTo(
				$dialog);
		var $dialogContent;
		var $dialogBottom;
		var $dialogOk;
		var $dialogCancel;
		var $dialogOverlay;
		if (settings.title != null) {
			$dialogTitle = $('<div class="dialogTitle"><\/div>').appendTo(
					$dialog);
		}
		if (settings.type != null) {
			$dialogContent = $(
					'<div class="dialogContent dialog' + settings.type
							+ 'Icon"><\/div>').appendTo($dialog);
		} else {
			$dialogContent = $('<div class="dialogContent"><\/div>').appendTo(
					$dialog);
		}
		if (settings.ok != null || settings.cancel != null) {
			$dialogBottom = $('<div class="dialogBottom"><\/div>').appendTo(
					$dialog);
		}
		if (settings.ok != null) {
			$dialogOk = $(
					'<input type="button" class="button" value="' + settings.ok
							+ '" \/>').appendTo($dialogBottom);
		}
		if (settings.cancel != null) {
			$dialogCancel = $(
					'<input type="button" class="button" value="'
							+ settings.cancel + '" \/>')
					.appendTo($dialogBottom);
		}
		if (!window.XMLHttpRequest) {
			$dialog.append('<iframe class="dialogIframe"><\/iframe>');
		}
		$dialog.appendTo("body");
		if (settings.modal) {
			$dialogOverlay = $('<div class="dialogOverlay"><\/div>')
					.insertAfter($dialog);
		}

		var dialogX;
		var dialogY;
		if (settings.title != null) {
			$dialogTitle.text(settings.title);
		}
		$dialogContent.html(settings.content);
		$dialog.css({
			"width" : settings.width,
			"height" : settings.height,
			"margin-left" : -parseInt(settings.width / 2),
			"z-index" : zIndex++
		});
		dialogShow();

		if ($dialogTitle != null) {
			$dialogTitle.mousedown(function(event) {
				$dialog.css({
					"z-index" : zIndex++
				});
				var offset = $(this).offset();
				if (!window.XMLHttpRequest) {
					dialogX = event.clientX - offset.left;
					dialogY = event.clientY - offset.top;
				} else {
					dialogX = event.pageX - offset.left;
					dialogY = event.pageY - offset.top;
				}
				$("body").bind("mousemove", function(event) {
					$dialog.css({
						"top" : event.clientY - dialogY,
						"left" : event.clientX - dialogX,
						"margin" : 0
					});
				});
				return false;
			}).mouseup(function() {
				$("body").unbind("mousemove");
				return false;
			});
		}

		if ($dialogClose != null) {
			$dialogClose.click(function() {
				dialogClose();
				return false;
			});
		}

		if ($dialogOk != null) {
			$dialogOk.click(function() {
				if (settings.onOk && typeof settings.onOk == "function") {
					if (settings.onOk($dialog) != false) {
						dialogClose();
					}
				} else {
					dialogClose();
				}
				return false;
			});
		}

		if ($dialogCancel != null) {
			$dialogCancel
					.click(function() {
						if (settings.onCancel
								&& typeof settings.onCancel == "function") {
							if (settings.onCancel($dialog) != false) {
								dialogClose();
							}
						} else {
							dialogClose();
						}
						return false;
					});
		}

		function dialogShow() {
			if (settings.onShow && typeof settings.onShow == "function") {
				if (settings.onShow($dialog) != false) {
					$dialog.show();
					$dialogOverlay.show();
				}
			} else {
				$dialog.show();
				$dialogOverlay.show();
			}
		}
		function dialogClose() {
			if (settings.onClose && typeof settings.onClose == "function") {
				if (settings.onClose($dialog) != false) {
					$dialogOverlay.remove();
					$dialog.remove();
				}
			} else {
				$dialogOverlay.remove();
				$dialog.remove();
			}
		}
		return $dialog;
	};

	// 令牌
	$(document).ajaxSend(
		function(event, request, settings) {
			if (!settings.crossDomain && settings.type != null
					&& settings.type.toLowerCase() == "post") {
				var token = getCookie("token");
				if (token != null) {
					request.setRequestHeader("token", token);
				}
			}
		});

	$(document).ajaxComplete(function(event, request, settings) {
		var loginStatus = request.getResponseHeader("loginStatus");
		var tokenStatus = request.getResponseHeader("tokenStatus");

		if (loginStatus == "accessDenied") {
			$.message("warn", "登录超时，请重新登录");
			setTimeout(function() {
				location.reload(true);
			}, 2000);
		} else if (loginStatus == "unauthorized") {
			$.message("warn", "对不起，您无此操作权限！");
		} else if (tokenStatus == "accessDenied") {
			var token = getCookie("token");
			if (token != null) {
				$.extend(settings, {
					global : false,
					headers : {
						token : token
					}
				});
				$.ajax(settings);
			}
		}
	});

})(jQuery);

// 令牌
$().ready(function() {
	$("form").submit(
		function() {
			var $this = $(this);
			if ($this.attr("method") != null
					&& $this.attr("method")
							.toLowerCase() == "post"
					&& $this.find(
							"input[name='token']")
							.length == 0) {
				var token = getCookie("token");
				if (token != null) {
					$this.append('<input type="hidden" name="token" value="'
									+ token
									+ '" \/>');
				}
			}
	});
});

Date.prototype.Format = function(fmt) { //author: meizz
	var o = {
		"M+" : this.getMonth() + 1, //月份
		"d+" : this.getDate(), //日
		"h+" : this.getHours(), //小时
		"m+" : this.getMinutes(), //分
		"s+" : this.getSeconds(), //秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), //季度
		"S" : this.getMilliseconds()
	//毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};

/**
 * 动态调整div高度
 * 以隐藏或展示某些查询条件
 * @param height
 */
function showMore(height) {
	var orgHeight = $(".bar").height();
	$(".bar").css({
		"height" : orgHeight + height
	});
	$(".Js_more").removeClass("more").attr({
		"hidden" : true
	});
	$(".Js_less").addClass("less").attr({
		"hidden" : false
	});
}

/**
 * 动态调整div高度
 * 以隐藏或展示某些查询条件
 * @param height
 */
function showLess(height) {
	var orgHeight = $(".bar").height();
	$(".bar").css({
		"height" : orgHeight - height
	});
	$(".Js_more").addClass("more").attr({
		"hidden" : false
	});
	$(".Js_less").removeClass("less").attr({
		"hidden" : true
	});
}