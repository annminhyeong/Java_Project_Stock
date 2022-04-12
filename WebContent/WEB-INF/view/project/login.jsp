<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!--
=========================================================
* Argon Design System - v1.2.0
=========================================================

* Product Page: https://www.creative-tim.com/product/argon-design-system
* Copyright 2020 Creative Tim (http://www.creative-tim.com)

Coded by www.creative-tim.com

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. -->
<!DOCTYPE html>
<html lang="en">

<head>
  <script type="text/javascript">
	function doLoginUserCheck(f) {
		
		if(f.id.value==""){
			alert("아이디를 입력하세요.");
			f.id.focus();
			return false;
		}
		
		if(f.password.value==""){
			alert("비밀번호를 입력하세요.");
			f.password.focus();
			return false;
		}
	}
</script>	

  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="apple-touch-icon" sizes="76x76" href="/assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="/assets/img/favicon.png">
  <title>
주식 정보 알리미
  </title>
  <!--     Fonts and icons     -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
  <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
  <!-- Nucleo Icons -->
  <link href="/assets/css/nucleo-icons.css" rel="stylesheet" />
  <link href="/assets/css/nucleo-svg.css" rel="stylesheet" />
  <!-- Font Awesome Icons -->
  <link href="/assets/css/font-awesome.css" rel="stylesheet" />
  <link href="/assets/css/nucleo-svg.css" rel="stylesheet" />
  <!-- CSS Files -->
  <link href="/assets/css/argon-design-system.css?v=1.2.0" rel="stylesheet" />
</head>

<body class="login-page">
					<nav id="navbar-main" class="navbar navbar-main navbar-expand-lg navbar-transparent navbar-light headroom">
				    <div class="container">
				      <a class="navbar-brand mr-lg-5" href="/project/user/index.do">
				        <h5 class="text-white font-weight-bold">주식 정보 알리미</h5>
				      </a>
				      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar_global" aria-controls="navbar_global" aria-expanded="false" aria-label="Toggle navigation">
				        <span class="navbar-toggler-icon"></span>
				      </button>
				      <div class="navbar-collapse collapse" id="navbar_global">
				        <div class="navbar-collapse-header">
				          <div class="row">
				            <div class="col-6 collapse-brand">
				              <a href="/project/user/index.do">
				        <h5 class="text-black font-weight-bold">주식 정보 알리미</h5>
				              </a>
				            </div>
				            <div class="col-6 collapse-close">
				              <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbar_global" aria-controls="navbar_global" aria-expanded="false" aria-label="Toggle navigation">
				                <span></span>
				                <span></span>
				              </button>
				            </div>
				          </div>
				        </div>
				      </div>
				    </div>
				  </nav>
  <section class="section section-shaped section-lg">
    <div class="shape shape-style-1 bg-gradient-default">
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
    </div>
    <div class="container pt-lg-7">
      <div class="row justify-content-center">
        <div class="col-lg-5">
          <div class="card bg-secondary shadow border-0">
            <div class="card-header bg-white pb-1">
              <div class="text-muted text-center mb-3">
                    <h2 class="mb-0">로그인</h2>
                </div>
            </div>
            <div class="card-body px-lg-5 py-lg-5">

              <form role="form" action="/project/getUserLoginCheck.do" method="post" onsubmit="return doLoginUserCheck(this);" name="f">
                <div class="form-group mb-3">
                  <div class="input-group input-group-alternative">
                    <div class="input-group-prepend">
                      <span class="input-group-text">아이디:</span>
                    </div>
                    <input class="form-control" placeholder="id" type="text" name="id">
                  </div>
                </div>
                <div class="form-group focused">
                  <div class="input-group input-group-alternative">
                    <div class="input-group-prepend">
                      <span class="input-group-text">비밀번호:</span>
                    </div>
                    <input class="form-control" placeholder="Password" type="password" name="password">
                  </div>
                </div>

                <div class="text-center">
					<input type="submit" class="btn btn-primary my-4" value="login">
                  <a href="https://kauth.kakao.com/oauth/authorize?client_id=4c88ff72991392bcbdd37ad7a1777f42&redirect_uri=http://3.34.88.97:8080/KakaoLogin.do&response_type=code">
                    <img src="/assets/img/brand/kakao_login_btn_simple_medium.png">
                      </a>
                </div>
              </form>
                <div class="text-center">
                    <span>
                        <a href="/project/searchPwd.do" class="text-uppercase text-muted font-weight-bold"><small>비밀번호 찾기</small></a>&nbsp;&nbsp;
                    </span>
                    <span>
                        <a href="/project/searchId.do" class="text-uppercase text-muted font-weight-bold"><small>아이디 찾기</small></a>&nbsp;&nbsp;
                    </span>
                    <span>
                        <a href="/project/signup.do" class="text-uppercase text-muted font-weight-bold"><small>회원가입</small></a>
                    </span>
                </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <footer class="footer">
    <div class="container">
      <div class="row row-grid align-items-center mb-9">
        <div class="col-lg-6">
          <h3 class="text-primary font-weight-light mb-">환영합니다.</h3>
          <h4 class="mb-0 font-weight-light">주식정보 사이트입니다.</h4>
        </div>

      </div>
      <hr>
      <div class="row align-items-center justify-content-md-between">
        <div class="col-md-8">
          <div class="copyright">
            &copy; 2020 <a href="" target="_blank">1920110020 안민형</a>
          </div>
        </div>

      </div>
    </div>
  </footer>
  <!--   Core JS Files   -->
  <script src="/assets/js/core/jquery.min.js" type="text/javascript"></script>
  <script src="/assets/js/core/popper.min.js" type="text/javascript"></script>
  <script src="/assets/js/core/bootstrap.min.js" type="text/javascript"></script>
  <script src="/assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
  <!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
  <script src="../assets/js/plugins/bootstrap-switch.js"></script>
  <!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
  <script src="/assets/js/plugins/nouislider.min.js" type="text/javascript"></script>
  <script src="/assets/js/plugins/moment.min.js"></script>
  <script src="/assets/js/plugins/datetimepicker.js" type="text/javascript"></script>
  <script src="/assets/js/plugins/bootstrap-datepicker.min.js"></script>
  <!-- Control Center for Argon UI Kit: parallax effects, scripts for the example pages etc -->
  <!--  Google Maps Plugin    -->
  <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
  <script src="/assets/js/argon-design-system.min.js?v=1.2.0" type="text/javascript"></script>
  <script src="https://cdn.trackjs.com/agent/v3/latest/t.js"></script>
  <script>
    window.TrackJS &&
      TrackJS.install({
        token: "ee6fab19c5a04ac1a32a645abde4613a",
        application: "argon-design-system-pro"
      });
  </script>
</body>

</html>