<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"><!--<![endif]-->

<head>
	<meta charset="utf-8" />
	<title>编辑权限</title>
	<meta name="author" content="马老师" />
	<meta name="keywords" content="马老师, malaoshi" />
	<meta name="description" content="马老师 - 个人服务站后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

	<link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />

	<link rel="shortcut icon" href="${base}/resources/img/favicons/favicon.png" />

	<style type="text/css">
	    .authorities label {
			min-width: 120px;
			_width: 120px;
			display: block;
			float: left;
			padding-right: 4px;
			_white-space: nowrap;
		}

		.authorities a{
		   color:#488BD1;
		   font-weight: bold;
		}
	</style>
</head>

<body>
	<div class="path">编辑权限</div>
	<form id="inputForm" action="update" method="post">
		<input type="hidden" name="id" value="${role.id}" />
	    <input type="hidden" name="authorities" value="ROLE_BASE" />
		<table class="input">
			<tr>
				<th>权限名:</th>
				<td>
					${role.name}
				</td>
			</tr>
			<tr>
				<th>权限描述:</th>
				<td>
					<input type="text" name="description" class="text" value="${role.description}" maxlength="255" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
	        <tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="全选此组权限">管理员</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" id="admin" name="authorities" value="ROLE_ADMIN" [#if role.authorityList?contains("ROLE_ADMIN")]checked[/#if] />管理员管理
						</label>
					</span>
				</td>
			</tr>
			<tr class="authorities">
				<th>&nbsp;</th>
				<td>
					<span class="fieldSet admin">
						<label>
							<input type="checkbox" name="authorities" value="ROLE_ADMIN_ADD" [#if role.authorityList?contains("ROLE_ADMIN_ADD")]checked[/#if] />添加管理员
						</label>
						<label>
							<input type="checkbox" name="authorities" value="ROLE_ADMIN_EDIT" [#if role.authorityList?contains("ROLE_ADMIN_EDIT")]checked[/#if] />编辑管理员
						</label>
					</span>
				</td>
			</tr>
	        <tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="全选此组权限">权限</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" id="role" name="authorities" value="ROLE_ROLE" [#if role.authorityList?contains("ROLE_ROLE")]checked[/#if] />权限管理
						</label>
					</span>
				</td>
			</tr>
			<tr class="authorities">
				<th>&nbsp;</th>
				<td>
					<span class="fieldSet role">
						<label>
							<input type="checkbox" name="authorities" value="ROLE_ROLE_ADD" [#if role.authorityList?contains("ROLE_ROLE_ADD")]checked[/#if] />添加权限
						</label>
						<label>
							<input type="checkbox" name="authorities" value="ROLE_ROLE_EDIT" [#if role.authorityList?contains("ROLE_ROLE_EDIT")]checked[/#if] />编辑权限
						</label>
					</span>
				</td>
			</tr>
	        <tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="全选此组权限">配置</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" id="config" name="authorities" value="ROLE_CONFIG" [#if role.authorityList?contains("ROLE_CONFIG")]checked[/#if] />配置项管理
						</label>
					</span>
				</td>
			</tr>
			<tr class="authorities">
				<th>&nbsp;</th>
				<td>
					<span class="fieldSet config">
						<label>
							<input type="checkbox" name="authorities" value="ROLE_CONFIG_ADD" [#if role.authorityList?contains("ROLE_CONFIG_ADD")]checked[/#if] />添加配置项
						</label>
						<label>
							<input type="checkbox" name="authorities" value="ROLE_CONFIG_EDIT" [#if role.authorityList?contains("ROLE_CONFIG_EDIT")]checked[/#if] />编辑配置项
						</label>
						<label>
							<input type="checkbox" name="authorities" value="ROLE_CONFIG_REFRESH" [#if role.authorityList?contains("ROLE_CONFIG_REFRESH")]checked[/#if] />同步配置项
						</label>
						<label>
							<input type="checkbox" name="authorities" value="ROLE_CONFIG_DELETE" [#if role.authorityList?contains("ROLE_CONFIG_DELETE")]checked[/#if] />删除配置项
						</label>
					</span>
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
			var $selectAll = $("#inputForm .selectAll");

			$selectAll.click(function() {
				var $this = $(this);
				var $parentsNode = $this.parent().parent();
				var $thisCheckbox = $this.closest("tr").find(":checkbox");
				if ($thisCheckbox.filter(":checked").length > 0) {
					$thisCheckbox.prop("checked", false);
					$parentsNode.next().closest("tr").find(":checkbox").prop("checked", false);
				} else {
					$thisCheckbox.prop("checked", true);
					$parentsNode.next().closest("tr").find(":checkbox").prop("checked", true);
				}
				return false;
			});

			$("input[name='authorities']").each(function(i){
				$(this).on("click",function(){
					var parentRoreName = this.id;
					var subRoleCount = $("."+parentRoreName).length;
					if(subRoleCount > 0){
						if(this.checked){
							$("."+parentRoreName+" input[name='authorities']").prop("checked", true);
						}else{
							$("."+parentRoreName+" input[name='authorities']").prop("checked", false);
						}
					}
				});
			});

			var $inputForm = $("#inputForm");
			$inputForm.validate({
				rules: {
					description: {
						maxlength:255
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