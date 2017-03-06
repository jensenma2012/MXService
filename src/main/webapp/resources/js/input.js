$().ready(function() {
	setTimeout(function(){
		if ($.tools != null) {
			var $tab = $("#tab");
			var $title = $("#inputForm :input[title], #inputForm label[title]");

			// Tab效果
			$tab.tabs("table.tabContent, div.tabContent", {
				tabs : "input"
			});

			// 表单提示
			$title.tooltip({
				position : "center right",
				offset : [ 0, 4 ],
				effect : "fade"
			});
		}

		// 验证消息
		if ($.validator != null) {
			$.extend($.validator.messages,
			{
				required : message("validate.required"),
				email : message("validate.email"),
				url : message("validate.url"),
				date : message("validate.date"),
				dateISO : message("validate.dateISO"),
				pointcard : message("validate.pointcard"),
				number : message("validate.number"),
				digits : message("validate.digits"),
				minlength : $.validator.format(message("validate.minlength")),
				maxlength : $.validator.format(message("validate.maxlength")),
				rangelength : $.validator.format(message("validate.rangelength")),
				min : $.validator.format(message("validate.min")),
				max : $.validator.format(message("validate.max")),
				range : $.validator.format(message("validate.range")),
				accept : message("validate.accept"),
				equalTo : message("validate.equalTo"),
				remote : message("validate.remote"),
				integer : message("validate.integer"),
				positive : message("validate.positive"),
				negative : message("validate.negative"),
				decimal : message("validate.decimal"),
				pattern : message("validate.pattern"),
				filetype : message("validate.filetype")
			});

			$.validator.setDefaults({
				errorClass : "fieldError",
				ignore : ".ignore",
				ignoreTitle : true,
				errorPlacement : function(error, element) {
					var fieldSet = element.closest("span.fieldSet");
					if (fieldSet.length > 0) {
						error.appendTo(fieldSet);
					} else {
						error.insertAfter(element);
					}
				},
				submitHandler : function(form) {
					$(form).find(":submit").prop("disabled", true);
					form.submit();
				}
			});
		}
	},1000);
});