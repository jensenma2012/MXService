[@override name="content"]
	<section class="home-header border-bottom padding-block">
		<!-- start container -->
		<div class="container">
			<!-- start row -->
			<div class="row">
				<div class="col-xs-12 col-sm-5 col-lg-5 text-center">
					<!-- Your foto --> 
					<div class="full-length-photo">
						<img src="${base}/resources/img/mx.png" alt="马老师">
					</div>
					<!-- end your foto -->
				</div>
				<div class="col-xs-12 col-sm-7 col-lg-7">
					<!-- Menu -->
					<div class="small-menu">
						<div class="icons-menu">
							<button class="icons-menu-toggle-button" style="transform: matrix(1, 0, 0, 1, 0, 0);">
								<i class="icons-menu-icon fa fa-bars"></i>
							</button>
							<ul class="icons-menu-items">
								<li class="icons-menu-item"><a href="profile.html" class="icons-menu-button" style="z-index: 2; transform: matrix(0.95, 0, 0, 0.95, 0, 0);">
										<i class="icons-menu-icon fa fa-user" style="transform: matrix(0, 0, 0, 0, 0, 0);"></i>
								</a></li>
								<li class="icons-menu-item"><a href="resume.html" class="icons-menu-button" style="z-index: 1; transform: matrix(0.95, 0, 0, 0.95, 0, 0);">
										<i class="icons-menu-icon fa fa-file-text" style="transform: matrix(0, 0, 0, 0, 0, 0);"></i>
								</a></li>
								<li class="icons-menu-item"><a href="portfolio.html" class="icons-menu-button" style="z-index: 1; transform: matrix(0.95, 0, 0, 0.95, 0, 0);">
										<i class="icons-menu-icon fa fa-suitcase" style="transform: matrix(0, 0, 0, 0, 0, 0);"></i>
								</a></li>
								<li class="icons-menu-item"><a href="blog.html" class="icons-menu-button" style="z-index: 2; transform: matrix(0.95, 0, 0, 0.95, 0, 0);">
										<i class="icons-menu-icon fa fa-pencil" style="transform: matrix(0, 0, 0, 0, 0, 0);"></i>
								</a></li>
								<li class="icons-menu-item"><a href="contact.html" class="icons-menu-button" style="z-index: 2; transform: matrix(0.95, 0, 0, 0.95, 0, 0);">
										<i class="icons-menu-icon fa fa-envelope" style="transform: matrix(0, 0, 0, 0, 0, 0);"></i>
								</a></li>
							</ul>
						</div>

						<!-- Open Menu -->
						<div class="arrow">
							<div class="open-menu">open menu</div>
						</div>
						<!-- End Open Menu -->
					</div>
					<!-- End Menu -->

					<h1 class="title">马老师</h1>
					<h3 class="sub-title">一个闲的蛋疼的程序猿，仅此而已</h3>
					<!-- social icon -->
					<div class="social">
						<ul class="animated" data-animation="fadeIn" data-animation-delay="600">
							<!-- social icons -->
							<li><a class="mx-icons hover-animate" data-toggle="modal" data-target="#qrcode"><i class="fa fa-wechat"></i></a></li>
							<li><a class="mx-icons hover-animate" href="http://www.weibo.com/jensenma"><i class="fa fa-weibo"></i></a></li>
						</ul>
					</div>
					<!-- end social icon -->
				</div>
			</div>
			<!-- end row -->
		</div>
		<!-- end container -->
	</section>
[/@override]

[@extends name="base.ftl" /]