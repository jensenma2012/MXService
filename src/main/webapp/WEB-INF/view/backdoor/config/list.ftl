<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"><!--<![endif]-->

<head>
	<meta charset="utf-8" />
	<title>配置项列表</title>
	<meta name="author" content="马老师" />
	<meta name="keywords" content="马老师, malaoshi" />
	<meta name="description" content="马老师 - 个人服务站后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

	<link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />

	<link rel="shortcut icon" href="${base}/resources/img/favicons/favicon.png" />
</head>

<body>
	<div class="path">
		 配置列表 <span>(共<span id="pageTotal">${page.totalCount}</span>条记录)</span>
	</div>
	<form id="listForm" action="list" method="get">
		<div class="bar">
			<@security.authorize access="hasRole('ROLE_CONFIG_ADD')">
				<a href="add" class="iconButton">
					<span class="addIcon">&nbsp;</span>添加
				</a>
			</@security.authorize>
			<div class="buttonWrap">
				<@security.authorize access="hasRole('ROLE_CONFIG_DELETE')">
					<a href="javascript:;" id="deleteButton" class="iconButton disabled">
						<span class="deleteIcon">&nbsp;</span>删除
					</a>
				</@security.authorize>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="pageSizeSelect" class="button">
						每页显示<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="pageSizeOption">
							<li><a href="javascript:;"<#if page.pageSize == 10> class="current"</#if> val="10">10</a></li>
							<li><a href="javascript:;"<#if page.pageSize == 20> class="current"</#if> val="20">20</a></li>
							<li><a href="javascript:;"<#if page.pageSize == 100> class="current"</#if> val="50">50</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="menuWrap">
				<span class="arrow">配置key：</span>
				<input type="text" class="text" name="keyword" value="${page.keyword}"/>
				<input type="submit" class="button" value="查询" />
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<span>配置key</span>
				</th>
				<th>
					<span>配置value</span>
				</th>
				<th>
					<span>配置描述</span>
				</th>
				<th>
					<span>创建时间</span>
				</th>
				<@security.authorize access="hasRole('ROLE_CONFIG_EDIT')">
					<th>	
						<span>操作</span>
					</th>
				</@security.authorize>
			</tr>
			<#list page.result as list>
			<tr>
				<td>
					<input type="checkbox" name="ids" value="${list.id}"/>
				</td>
				<td>
					${list.key}
				</td>
				<td>
					${list.value}
				</td>
				<td>
					${list.description}
				</td>
				<td>
					${(list.createDate?string("yyyy-MM-dd HH:mm:ss"))!"-"}
				</td>
				<@security.authorize access="hasRole('ROLE_CONFIG_EDIT')">
					<td>
						<a href="edit?id=${list.id}">[编辑]</a>
					</td>
				</@security.authorize>
			</tr>
			</#list>
		</table>
		<#include "/backdoor/pagination.ftl">
	</form>

	<!-- Scripts -->
	<script src="${base}/resources/js/jquery.min.js" type="text/javascript"></script>
	<script src="${base}/resources/js/common.js" type="text/javascript"></script>
	<script src="${base}/resources/js/list.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		$().ready(function() {
			if(null != "${message}" && "${message}" != ""){
	    		$.message("warn","${message}");
	    	}
		});
	</script>
</body>

</html>