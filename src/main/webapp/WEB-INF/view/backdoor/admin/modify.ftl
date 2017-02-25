<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"><!--<![endif]-->

<head>
	<meta charset="utf-8" />
	<title>马老师个人服务站后台管理</title>
	<meta name="author" content="马老师" />
	<meta name="keywords" content="马老师, malaoshi" />
	<meta name="description" content="马老师 - 个人服务站后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

	<link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />

	<link rel="shortcut icon" href="${base}/resources/img/favicons/favicon.png" />
</head>

<body>
	<div class="path">账号设置</div>
	<form id="inputForm" action="save" method="post">
		<table class="input">
			<tr>
				<th>账号名称:</th>
				<td>
					${username}
				</td>
			</tr>
			<tr>
				<th><span class="requiredField">*</span>原密码:</th>
				<td>
					<input type="password" name="password" class="text" maxlength="32" />
				</td>
			</tr>
			<tr>
				<th><span class="requiredField">*</span>新密码:</th>
				<td>
					<input type="password" name="newPassword" id="newPassword" class="text" maxlength="32" />
				</td>
			</tr>
			<tr>
				<th><span class="requiredField">*</span>确认新密码:</th>
				<td>
					<input type="password" name="rePassword" class="text" maxlength="32" />
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确定" />
					<input type="button" class="button" value="返回" onclick="window.history.back(); return false;" />
				</td>
			</tr>
		</table>
	</form>

	<!-- Scripts -->
	<script src="${base}/resources/js/jquery.min.js" type="text/javascript"></script>
	<script src="${base}/resources/js/common.js" type="text/javascript"></script>
	<script src="${base}/resources/js/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${base}/resources/js/input.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		$().ready(function() {
			var $inputForm = $("#inputForm");
			$inputForm.validate({
				rules : {
					password : {
						required : true,
						pattern : /^[^\s&\"<>]+$/,
						minlength : 4,
						maxlength : 32
					},
					newPassword : {
						required : true,
						pattern : /^[^\s&\"<>]+$/,
						minlength : 4,
						maxlength : 32
					},
					rePassword : {
						required : true,
						equalTo : "#newPassword"
					}
				},
				messages : {
					newPassword : {
						pattern : "包含非法字符！"
					}
				}
			});

			if(null != "${message}" && "${message}" != ""){
	    		$.message("warn","${message}");
	    	}
		});
	</script>
</body>

</html>