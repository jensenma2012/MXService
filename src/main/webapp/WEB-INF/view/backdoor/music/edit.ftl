<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"><!--<![endif]-->

<head>
	<meta charset="utf-8" />
	<title>编辑歌曲</title>
	<meta name="author" content="马老师" />
	<meta name="keywords" content="马老师, malaoshi" />
	<meta name="description" content="马老师 - 个人服务站后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

	<link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />

	<link rel="shortcut icon" href="${base}/resources/img/favicons/favicon.png" />
</head>

<body>
	<div class="path">编辑歌曲</div>
	<form id="inputForm" action="update" method="post">
		<table class="input">
			<tr>
				<th><span class="requiredField">*</span>歌曲标题:</th>
				<td>
					<input type="text" name="title" class="text" value="${music.title}" maxlength="64" />
				</td>
			</tr>
			<tr>
				<th><span class="requiredField">*</span>歌曲描述:</th>
				<td>
					<input type="text" name="description" class="text" value="${music.description}" maxlength="64" />
				</td>
			</tr>
			<tr>
				<th>歌曲:</th>
				<td>
					<audio controls src="${musicUrl}${music.filename}">您的浏览器不支持audio标签</audio>
				</td>
			</tr>
			<tr>
				<th><span class="requiredField">*</span>专辑:</th>
				<td>
					<select name="album.id">
						<option value="">--请选择--</option>
						[#list albums as album]
							<option value="${album.id!}" [#if music.album.id?? && music.album.id==album.id]selected[/#if]>${album.title!}-${album.artist.name}</option>
						[/#list]
					</select>
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
					title: {
						required:true,
						maxlength:64
					},
					description: {
						required:true,
						maxlength:64
					},
					"album.id": {
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