<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"><!--<![endif]-->

<head>
	<meta charset="utf-8" />
	<title>
		[@block name="title" /]
	</title>
	<meta name="author" content="马老师" />
	<meta name="keywords" content="马老师, malaoshi" />
	<meta name="description" content="马老师 - 个人服务站后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

	<link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />

	<link rel="shortcut icon" href="${base}/resources/img/favicons/favicon.png" />

	[@block name="extracss" /]
</head>

<body>
	<div class="path">
		[@block name="subtitle" /]
	</div>
	<form id="inputForm" action="[@block name="action" /]" method="post" [@block name="enctype" /]>
		<table class="input">
			[@block name="form" /]
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
			[@block name="extrajs" /]

			var $inputForm = $("#inputForm");
			$inputForm.validate({
				rules: {
					[@block name="rules" /]
				},
				messages: {
					[@block name="messages" /]
				}
			});

			if(null != "${message}" && "${message}" != ""){
	    		$.message("warn","${message}");
	    	}
		});
	</script>
</body>

</html>