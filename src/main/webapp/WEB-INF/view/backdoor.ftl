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
	<link href="${base}/resources/css/login.css" rel="stylesheet" type="text/css" />

	<link rel="shortcut icon" href="${base}/resources/img/favicons/favicon.png" />
</head>

<body>
	<div class="login">
		<form id="loginForm" action="${base}/backdoor/login" method="post">
			<input type="hidden" name="captchaId" value="${captchaId}" />
			<table>
				<tr>
					<td width="190" rowspan="2" align="center" valign="bottom">
						<img src="${base}/resources/img/logo.png" alt="后台管理系统" />
					</td>
					<th>
						用户名:
					</th>
					<td>
						<input type="text" id="username" name="username" class="text" maxlength="30" />
					</td>
				</tr>
				<tr>
					<th>
						密&nbsp;&nbsp;&nbsp;码:
					</th>
					<td>
						<input type="password" id="password" name="password" class="text" maxlength="30" autocomplete="off" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<th>验证码:</th>
					<td>
						<input type="text" id="captcha" name="captcha" class="text captcha" maxlength="4" autocomplete="off" />
						<img id="captchaImage" class="captchaImage" src="${base}/backdoor/captcha?captchaId=${captchaId}" onclick="refresh()" title="点击更换验证码" />
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<th>
						&nbsp;
					</th>
					<td>
						<input type="button" class="homeButton" value="" onclick="location.href='${base}/backdoor'" />
						<input type="submit" class="loginButton" value="登录" />
					</td>
				</tr>
			</table>
			<div class="powered">© 2017 malaoshi All right reserved</div>
		</form>
	</div>

	<!-- Scripts -->
	<script src="${base}/resources/js/jquery.min.js" type="text/javascript"></script>
	<script src="${base}/resources/js/common.js" type="text/javascript"></script>

	<script type="text/javascript">
		$().ready( function() {
			var $loginForm = $("#loginForm");
			var $username = $("#username");
			var $password = $("#password");
			var $captcha = $("#captcha");

			$loginForm.submit( function() {
				if ($username.val() == "") {
					$.message("warn", "请输入您的用户名");
					return false;
				}
				if ($password.val() == "") {
					$.message("warn", "请输入您的密码");
					return false;
				}
				if ($captcha.val() == "") {
					$.message("warn", "请输入您的验证码");
					return false;
				}
			});

			if(null != "${errmsg}" && "${errmsg}" != ""){
	    		$.message("warn","${errmsg}");
	    	}
		});

		function refresh() {
			$("#captchaImage").attr("src", "${base}/backdoor/captcha?captchaId=${captchaId}&random="+Math.random());
		}
	</script>
</body>

</html>