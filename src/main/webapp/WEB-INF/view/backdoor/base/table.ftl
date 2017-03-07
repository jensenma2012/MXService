[#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /]

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
		 [@block name="subtitle" /]<span>(共<span id="pageTotal">${page.totalCount}</span>条记录)</span>
	</div>
	<form id="listForm" action="list" method="get">
		<div class="bar">
			[@block name="addbutton" /]
			<div class="buttonWrap">
				[@block name="deletebutton" /]
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
				[@block name="extrabutton" /]
				<div class="menuWrap">
					<a href="javascript:;" id="pageSizeSelect" class="button">
						每页显示<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="pageSizeOption">
							<li><a href="javascript:;"[#if page.pageSize == 10] class="current"[/#if] val="10">10</a></li>
							<li><a href="javascript:;"[#if page.pageSize == 20] class="current"[/#if] val="20">20</a></li>
							<li><a href="javascript:;"[#if page.pageSize == 50] class="current"[/#if] val="50">50</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="menuWrap">
				<select name="fieldName" class="select">
					<option value="">--请选择--</option>
					[#list page.searchFields as field]
						<option value="${field.name()!}" [#if page.fieldName?? && page.fieldName==field.name()]selected[/#if]>${field.desc()!}</option>
					[/#list]
				</select>
				<input type="text" class="text" name="fieldValue" value="${page.fieldValue}"/>
				<input type="submit" class="button" value="查询" />
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				[@block name="columntitle" /]
			</tr>
			[#list page.results as list]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${list.id}"/>
					</td>
					[@block name="columnvalue" /]
				</tr>
			[/#list]
		</table>
		[#include "/backdoor/pagination.ftl"]
	</form>

	<!-- Scripts -->
	<script src="${base}/resources/js/jquery.min.js" type="text/javascript"></script>
	<script src="${base}/resources/js/common.js" type="text/javascript"></script>
	<script src="${base}/resources/js/list.js" type="text/javascript"></script>

	<script type="text/javascript">
		$().ready(function() {
			[@block name="extrajs" /]

			if(null != "${message}" && "${message}" != ""){
	    		$.message("warn","${message}");
	    	}
		});
	</script>
</body>

</html>