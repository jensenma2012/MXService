<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"><!--<![endif]-->

<head>
	<meta charset="utf-8" />
	<title>修改配置项</title>
	<meta name="author" content="马老师" />
	<meta name="keywords" content="马老师, malaoshi" />
	<meta name="description" content="马老师 - 个人服务站后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

	<link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />

	<link rel="shortcut icon" href="${base}/resources/img/favicons/favicon.png" />
</head>

<body>
	<div class="path">修改配置项</div>
	<form id="inputForm" action="update" method="post">
		<input type="hidden" name="id" value="${config.id}" />
		<table class="input">
			<tr>
				<th><span class="requiredField">*</span>配置key:</th>
				<td>
					<input type="text" name="key" class="text" value="${config.key}" maxlength="64" />
				</td>
			</tr>
			<tr>
				<th><span class="requiredField">*</span>配置value:</th>
				<td>
					<textarea name="value" class="textarea" maxlength="255">${config.value}</textarea>
				</td>
			</tr>
			<tr>
				<th>配置描述:</th>
				<td>
					<textarea name="description" class="textarea" maxlength="255">${config.description}</textarea>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确定" />
					<input type="button" class="button" value="返回" onclick="location.href='list'" />
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
				rules: {
					key: {
						required:true
					},
					value: {
						required:true
					}
				},
				messages: {
				}
			});

			if(null != "${message}" && "${message}" != ""){
	    		$.message("warn","${message}");
	    	}
		});
	</script>
</body>

</html>