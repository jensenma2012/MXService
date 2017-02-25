$().ready( function() {
	var $listForm = $("#listForm");
	var $pageTotal = $("#pageTotal");
	var $deleteButton = $("#deleteButton");
	var $refreshButton = $("#refreshButton");
	var $pageSizeSelect = $("#pageSizeSelect");
	var $pageSizeOption = $("#pageSizeOption a");
	var $listTable = $("#listTable");
	var $selectAll = $("#selectAll");
	var $ids = $("#listTable input[name='ids']");
	var $contentRow = $("#listTable tr:gt(0)");
	var $pageSize = $("#pageSize");
	var $pageNumber = $("#pageNumber");

	// 删除
	$deleteButton.click( function() {
		var $this = $(this);
		if ($this.hasClass("disabled")) {
			return false;
		}
		var $checkedIds = $("#listTable input[name='ids']:enabled:checked");
		$.dialog({
			type: "warn",
			content: message("dialog.deleteConfirm"),
			ok: message("dialog.ok"),
			cancel: message("dialog.cancel"),
			onOk: function() {
				$.ajax({
					url: "delete",
					type: "POST",
					data: $checkedIds.serialize(),
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						if (message.type == "success") {
							$pageTotal.text(parseInt($pageTotal.text()) - $checkedIds.length);
							$checkedIds.closest("tr").remove();
							setTimeout(function() {
								location.reload(true);
							}, 3000);
						}
						$deleteButton.addClass("disabled");
						$selectAll.prop("checked", false);
						$checkedIds.prop("checked", false);
					},
					error:function(data) {
						error(data);
					}
				});
			}
		});
	});

	// 刷新
	$refreshButton.click( function() {
		location.reload(true);
		return false;
	});

	// 每页记录数选项
	$pageSizeSelect.mouseover( function() {
		var $this = $(this);
		var offset = $this.offset();
		var $menuWrap = $this.closest("div.menuWrap");
		var $popupMenu = $menuWrap.children("div.popupMenu");
		$popupMenu.css({left: offset.left, top: offset.top + $this.height() + 2}).show();
		$menuWrap.mouseleave(function() {
			$popupMenu.hide();
		});
	});

	// 每页记录数
	$pageSizeOption.click( function() {
		var $this = $(this);
		$pageSize.val($this.attr("val"));
		$pageNumber.val("1");
		$listForm.submit();
		return false;
	});

	// 全选
	$selectAll.click( function() {
		var $this = $(this);
		var $enabledIds = $("#listTable input[name='ids']:enabled");
		if ($this.prop("checked")) {
			$enabledIds.prop("checked", true);
			if ($enabledIds.filter(":checked").length > 0) {
				$deleteButton.removeClass("disabled");
				$contentRow.addClass("selected");
			} else {
				$deleteButton.addClass("disabled");
			}
		} else {
			$enabledIds.prop("checked", false);
			$deleteButton.addClass("disabled");
			$contentRow.removeClass("selected");
		}
	});

	// 选择
	$ids.click( function() {
		var $this = $(this);
		if ($this.prop("checked")) {
			$this.closest("tr").addClass("selected");
			$deleteButton.removeClass("disabled");
		} else {
			$this.closest("tr").removeClass("selected");
			if ($("#listTable input[name='ids']:enabled:checked").length > 0) {
				$deleteButton.removeClass("disabled");
			} else {
				$deleteButton.addClass("disabled");
			}
		}
		$selectAll.attr("checked",$ids.length == $("input[name='ids']:checked").length ? true : false);
		$("#selectall").attr("checked",$ids.length == $("input[name='ids']:checked").length ? true : false);
	});

	// 页码输入
	$pageNumber.keypress(function(event) {
		var key = event.keyCode ? event.keyCode : event.which;
		if ((key == 13 && $(this).val().length > 0) || (key >= 48 && key <= 57)) {
			return true;
		} else {
			return false;
		}
	});

	// 表单提交
	$listForm.submit(function() {
		if (!/^\d*[1-9]\d*$/.test($pageNumber.val())) {
			$pageNumber.val("1");
		}
	});

	// 页码跳转
	$.pageSkip = function(pageNumber) {
		$pageNumber.val(pageNumber);
		$listForm.submit();
		return false;
	};

	// 列表查询
	if (location.search != "") {
		addCookie("listQuery", location.search);
	} else {
		removeCookie("listQuery");
	}

});

function error(data){  
    var result = data.responseText; 
    if(result !=null && result !=''){//后台异常时，并在后台捕获  
        var url = getRootPath()+"/backdoor/error";//获取工程路径  
        location.href = url;  
        //$(document.body).html(result);   
    }else{//后台异常，且没有被捕获  
        //clickautohide(5);  
    }  
}

function getRootPath(){  
    //获取当前网址，如： http://localhost:8099/UniqueduHome/view/error/notAuthorize.jsp  
    var curWwwPath=window.document.location.href;  
    //获取主机地址之后的目录，如： UniqueduHome/view/error/notAuthorize.jsp  
    var pathName=window.document.location.pathname;  
    var pos=curWwwPath.indexOf(pathName);  
    //获取主机地址，如： http://localhost:8099  
    var localhostPaht=curWwwPath.substring(0,pos);  
    //获取带"/"的项目名，如：/UniqueduHome  
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);  
    return(localhostPaht+projectName);  
} 