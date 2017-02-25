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

	<link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/resources/css/error.css" rel="stylesheet" type="text/css"/>

	<link rel="shortcut icon" href="${base}/resources/img/favicons/favicon.png" />
</head>

<body>
	<div class="wrap">
		<div class="error">
			<dl>
				<dt>对不起，您的操作出现错误！</dt>
				<#if content??>
					<dd>${content}</dd>
				</#if>
				<#if constraintViolations?has_content>
					<#list constraintViolations as constraintViolation>
						<dd><${constraintViolation.propertyPath}> ${constraintViolation.message}</dd>
					</#list>
				</#if>
				<dd>
					<a href="javascript:;" onclick="window.history.back(); return false;">返回上一页</a>
				</dd>
			</dl>
		</div>
	</div>
</body>

</html>