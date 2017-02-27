<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

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
	<link href="${base}/resources/css/main.css" rel="stylesheet" type="text/css"/>

	<link rel="shortcut icon" href="${base}/resources/img/favicons/favicon.png" />

	<style type="text/css">
	    html, body {
	        width: 100%;
	        height: 100%;
	        overflow: hidden;
	    }
	    strong {
	    	color: #ff6600;
	    }
	</style>
</head>

<body>
	<table class="main">
	    <tr>
	        <th class="logo">
		        <a href="main">
		        	<img src="${base}/resources/img/logo.png" alt="管理平台" style="width: 120px;" />
		        </a>
	        </th>
	        <th>
	            <div id="nav" class="nav">
	                <ul>
		                <@security.authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ROLE','ROLE_CONFIG')">
							<li><a href="#system">系统</a></li>
						</@security.authorize>
	                </ul>
	            </div>
	            <div class="link"></div>
	            <div class="link">
	                <strong><@security.authentication property="principal.username" /></strong> 您好!
	                <a href="admin/modify" target="iframe">[密码重置]</a>
	                <a href="logout" target="_top">[注销]</a>
	            </div>
	        </th>
	    </tr>
	    <tr>
	        <td id="menu" class="menu">
	        	<dl id="system">
					<@security.authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ROLE')">
						<dt>系统管理</dt>
				    </@security.authorize>
					<@security.authorize access="hasRole('ROLE_ADMIN')">
						<dd>
							<a href="admin/list" target="iframe">管理员管理</a>
					    </dd>
				    </@security.authorize>
					<@security.authorize access="hasRole('ROLE_ROLE')">
						<dd>
							<a href="role/list" target="iframe">权限管理</a>
					    </dd>
				    </@security.authorize>
					<@security.authorize access="hasAnyRole('ROLE_CONFIG')">
						<dt>配置项管理</dt>
				    </@security.authorize>
					<@security.authorize access="hasRole('ROLE_CONFIG')">
						<dd>
							<a href="config/list" target="iframe">配置项管理</a>
					    </dd>
				    </@security.authorize>
				</dl>
	        </td>
	        <td style="height: 95%;">
	            <iframe id="iframe" name="iframe" style="height: 95%;" src="" frameborder="0"></iframe>
	        </td>
	    </tr>
	</table>

	<!-- Scripts -->
	<script src="${base}/resources/js/jquery.min.js" type="text/javascript"></script>
	<script src="${base}/resources/js/common.js" type="text/javascript"></script>

	<script type="text/javascript">
		if (self != top) {
		    top.location = self.location;
		}
		
		$().ready(function () {
		    var $nav = $("#nav a");
		    var $menu = $("#menu dl");
		    var $menuItem = $("#menu a");
		
		    navTool(($("#nav ul li a")[0]));
		    showView(($("#nav ul li a")[0]));
		
		    $nav.click(function () {
		        navTool(this);
		    });
		
		    $menuItem.click(function () {
		        var $this = $(this);
		        $menuItem.removeClass("current");
		        $this.addClass("current");
		    });
		
		    $("#nav ul li a").each(function (i) {
		        $(this).on("click", function () {
		            showView(this);
		        });
		    });
		
		    function navTool(node) {
		        var $this = $(node);
		        $nav.removeClass("current");
		        $menuItem.removeClass("current");
		        $this.addClass("current");
		        var $currentMenu = $($this.attr("href"));
		        $menu.hide();
		        $currentMenu.show();
		        return false;
		    }
		});
		
		function showView(node) {
			if (node == undefined) {
				return;
			}
			var hrefVal = node.href;
		    //获得#位置
		    var temp = hrefVal.lastIndexOf("#");
		    var nodeId = hrefVal.substring(temp, hrefVal.length);
		    var $aNode = $(nodeId).children("dd:first").children();
		    $aNode.addClass("current");
		    var hrefAddr = $aNode.attr("href");
		    open(hrefAddr, "iframe");
		}
	</script>
</body>

</html>