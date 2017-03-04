<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"><!--<![endif]-->

<head>
	<meta charset="utf-8" />
	<title>马老师个人服务站</title>
	<meta name="author" content="马老师" />
	<meta name="keywords" content="马老师, malaoshi" />
	<meta name="description" content="马老师 - 个人服务站" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

	<!-- Font Awesome -->
	<link type="text/css" media="all" href="${base}/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
	<!-- Bootstrap -->
	<link type="text/css" media="all" href="${base}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
	<!-- Animate CSS -->
	<link type="text/css" media="all" href="${base}/resources/css/animate.css" rel="stylesheet" />
	<!-- Template CSS -->
	<link rel="stylesheet" type="text/css" href="${base}/resources/css/component.css" />
	<link type="text/css" media="all" href="${base}/resources/css/style.css" rel="stylesheet" />
	<link href="${base}/resources/css/color.css" rel="stylesheet" type="text/css" data-color="color-blue" media="all" id="stylesheet-new">
	<!-- Responsive CSS -->
	<link type="text/css" media="all" href="${base}/resources/css/responsive.css" rel="stylesheet" />
	<!-- Favicons -->
	<link rel="apple-touch-icon" sizes="144x144" href="${base}/resources/img/favicons/apple-touch-icon-144x144.png" />
	<link rel="apple-touch-icon" sizes="114x114" href="${base}/resources/img/favicons/apple-touch-icon-114x114.png" />
	<link rel="apple-touch-icon" sizes="72x72" href="${base}/resources/img/favicons/apple-touch-icon-72x72.png" />
	<link rel="apple-touch-icon" href="${base}/resources/img/favicons/apple-touch-icon.png" />
	<link rel="shortcut icon" href="${base}/resources/img/favicons/favicon.png" />
	<!-- Google Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Raleway:400,100,100italic,200,200italic,300,300italic,400italic,500,500italic,600,600italic,700,700italic,800,800italic,900,900italic' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=The+Girl+Next+Door:400,100,100italic,200,200italic,300,300italic,400italic,500,500italic,600,600italic,700,700italic,800,800italic,900,900italic' rel='stylesheet' type='text/css'>
</head>

<body>
	<!-- Load page -->
	<div class="animationload">
		<div class="loader"></div>
	</div>
	<!-- End load page -->

	<!-- Wrapper -->
	<div id="wraper">
		<!-- Start Modal section -->
		<div class="modal fade active" id="qrcode" tabindex="-1" role="dialog" aria-labelledby="qrcodeLabel" aria-hidden="true">
			<div class="modal-dialog" style="height:360px; height:calc(100% - 60px);">
				<div class="modal-content" style="height:360px; top:calc(50% - 300px);">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="qrcodeLabel">公众号二维码</h4>
					</div>
					<div class="modal-body" style="text-align: center;">
						<img src="${base}/resources/img/qrcode.jpg" alt="马老师">
					</div>
				</div>
			</div>
		</div>
		<!-- End Modal section -->
		
		<!-- Start Home-header section -->
		[@block name="content" /]
		<!-- End Home-header section -->

		<!-- Start Footer section -->
		<footer class="footer">
			<!-- start container -->
			<div class="container">
				<!-- start row -->
				<div class="row">
					<!-- start phone number -->
					<div class="col-xs-12 col-sm-6 col-lg-3">
						<a href="#" onclick="return false" class="hover-animate"> <span class="mx-icons hover-animate"><i class="fa fa-phone"></i></span>
							电话：有事请发邮件
						</a>
					</div>
					<!-- end phone number -->
					<!-- start email -->
					<div class="col-xs-12 col-sm-6 col-lg-3">
						<a href="#" onclick="return false" class="hover-animate"> <span class="mx-icons hover-animate"><i class="fa fa-paper-plane"></i></span>
							邮箱：xiaoma2011@gmail.com
						</a>
					</div>
					<!-- end email -->
					<!-- start address -->
					<div class="col-xs-12 col-sm-6 col-lg-3">
						<a href="#" onclick="return false" class="hover-animate"> <span class="mx-icons hover-animate"><i class="fa fa-map-marker"></i></span>
							坐标：湖北武汉
						</a>
					</div>
					<!-- end address -->
					<!-- start Copyright -->
					<div class="col-xs-12 col-sm-6 col-lg-3 text-right">
						<span class="copyright">© 2017 malaoshi All right reserved</span>
					</div>
					<!-- end Copyright -->
				</div>
				<!-- end row -->
			</div>
			<!-- end container -->
		</footer>
		<!-- End Footer section -->
	</div>
	<!-- End Wrapper -->

	<!-- Scroll to Top -->
	<a href="#" class="btn hover-animate scrollToTop"><i class="fa fa-angle-up"></i></a>
	<!-- End Scroll to Top -->

	<!-- Scripts -->
	<script src="${base}/resources/js/jquery.min.js" type="text/javascript"></script>
	<script src="${base}/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${base}/resources/js/jquery.appear.js" type="text/javascript"></script>
	<script src="${base}/resources/js/jquery.mixitup.min.js" type="text/javascript"></script>
	<script src="${base}/resources/js/jquery.flexslider-min.js" type="text/javascript"></script>
	<script src="${base}/resources/js/jquery.inview.min.js" type="text/javascript"></script>
	<script src="${base}/resources/js/jquery.knob.min.js" type="text/javascript"></script>
	<script src="${base}/resources/js/jquery.cookie.js" type="text/javascript"></script>
	<script src="${base}/resources/js/jquery.ratyli.js" type="text/javascript"></script>
	<script src="${base}/resources/js/TweenMax.min.js" type="text/javascript"></script>
	<script src="${base}/resources/js/owl.carousel.min.js" type="text/javascript"></script>
	<script src="${base}/resources/js/modernizr.custom.js" type="text/javascript"></script>
	<script src="${base}/resources/js/menu.js" type="text/javascript"></script>
	<script src="${base}/resources/js/scripts.js" type="text/javascript"></script>
</body>

</html>