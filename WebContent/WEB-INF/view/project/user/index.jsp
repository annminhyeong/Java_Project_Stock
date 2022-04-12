<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="apple-touch-icon" sizes="76x76" href="/assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="/assets/img/favicon.png">
  <title>
    주식정보 알리미
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

<body class="index-page">
			<!-- 네이브 바 start -->
              <c:choose>
              	<c:when test="${sessionScope.SS_VERIFY == 1}">
					<nav id="navbar-main" class="navbar navbar-main navbar-expand-lg navbar-transparent navbar-light headroom">
				    <div class="container">
				      <a class="navbar-brand mr-lg-5" href="/project/user/index.do">
				        <h5 class="text-black font-weight-bold">주식 정보 알리미</h5>
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
				        <ul class="navbar-nav navbar-nav-hover align-items-lg-center">
				            
				          <li class="nav-item dropdown">
				            <a href="/project/notice/NoticeList.do" class="nav-link">
				              <span class="text-uppercase text-muted font-weight-bold">공지사항 관리</span>
				            </a>
				          </li>
				            
				          <li class="nav-item dropdown">
				            <a href="/project/user/UserList.do" class="nav-link">
				              <span class="text-uppercase text-muted font-weight-bold">사용자 관리</span>
				            </a>
				          </li>
				            
				          <li class="nav-item dropdown">
				            <a href="/project/user/mypageUpdate.do" class="nav-link">
				              <span class="text-uppercase text-muted font-weight-bold">마이페이지</span>
				            </a>
				          </li>
				            
				          <li class="nav-item dropdown">
				            <a href="/project/Logout.do" class="nav-link">
				              <span class="text-uppercase text-muted font-weight-bold">로그아웃</span>
				            </a>
				          </li>
				            
				        </ul>
				      </div>
				    </div>
				  </nav>
				  <!-- End Navbar -->
              	</c:when>
              	
              	<c:when test="${sessionScope.SS_VERIFY == 0}">
				<nav id="navbar-main" class="navbar navbar-main navbar-expand-lg navbar-transparent navbar-light headroom">
				    <div class="container">
				      <a class="navbar-brand mr-lg-5" href="/project/user/index.do">
				        <h5 class="text-black font-weight-bold">주식 정보 알리미</h5>
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
				        <ul class="navbar-nav navbar-nav-hover align-items-lg-center">
				            
				          <li class="nav-item dropdown">
				            <a href="/project/notice/NoticeList.do" class="nav-link">
				              <span class="text-uppercase text-muted font-weight-bold">공지사항</span>
				            </a>
				          </li>
				           
				           <li class="nav-item dropdown">
				            <a href="/project/crewling/stock.do" class="nav-link">
				              <span class="text-uppercase text-muted font-weight-bold">주식정보<br>&emsp;검색</span>
				            </a>
				          </li>
				           				            
				          <li class="nav-item dropdown">
				            <a href="/project/crewling/talk.do" class="nav-link">
				              <span class="text-uppercase text-muted font-weight-bold">종목 토론실<br>&emsp;&ensp;분석</span>
				            </a>
				          </li>
				            
				          <li class="nav-item dropdown">
				            <a href="/project/crewling/kosgrap.do" class="nav-link">
				              <span class="text-uppercase text-muted font-weight-bold">코스피,코스닥</span>
				            </a>
				          </li>
				          
				          <li class="nav-item dropdown">
				            <a href="/project/crewling/stop.do" class="nav-link">
				              <span class="text-uppercase text-muted font-weight-bold">기타 주식정보</span>
				            </a>
				          </li>
				            
				          <li class="nav-item dropdown">
				            <a href="/project/user/mypageUpdate.do" class="nav-link">
				              <span class="text-uppercase text-muted font-weight-bold">마이페이지</span>
				            </a>
				          </li>
				            
				          <li class="nav-item dropdown">
				            <a href="/project/Logout.do" class="nav-link">
				              <span class="text-uppercase text-muted font-weight-bold">로그아웃</span>
				            </a>
				          </li>
				            
				        </ul>
				      </div>
				    </div>
				  </nav>
				  <!-- End Navbar -->
              	</c:when>
              	<c:when test="${not empty sessionScope.SS_ID or user_id ne null }">
				<nav id="navbar-main" class="navbar navbar-main navbar-expand-lg navbar-transparent navbar-light headroom">
				    <div class="container">
				      <a class="navbar-brand mr-lg-5" href="/project/user/index.do">
				        <h5 class="text-black font-weight-bold">주식 정보 알리미</h5>
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
				        <ul class="navbar-nav navbar-nav-hover align-items-lg-center">
				            
				          <li class="nav-item dropdown">
				            <a href="/project/notice/NoticeList.do" class="nav-link">
				              <span class="text-uppercase text-muted font-weight-bold">공지사항</span>
				            </a>
				          </li>
				           
				           <li class="nav-item dropdown">
				            <a href="/project/crewling/stock.do" class="nav-link">
				              <span class="text-uppercase text-muted font-weight-bold">주식정보<br>&emsp;검색</span>
				            </a>
				          </li>
				           				            
				          <li class="nav-item dropdown">
				            <a href="/project/crewling/talk.do" class="nav-link">
				              <span class="text-uppercase text-muted font-weight-bold">종목 토론실<br>&emsp;&ensp;분석</span>
				            </a>
				          </li>
				          
				          <li class="nav-item dropdown">
				            <a href="/project/crewling/kosgrap.do" class="nav-link">
				              <span class="text-uppercase text-muted font-weight-bold">기타 주식정보</span>
				            </a>
				          </li>
				            
				          <li class="nav-item dropdown">
				            <a href="/project/crewling/kosgrap.do" class="nav-link">
				              <span class="text-uppercase text-muted font-weight-bold">코스피,코스닥</span>
				            </a>
				          </li>
				            
				          <li class="nav-item dropdown">
				            <a href="/project/Logout.do" class="nav-link">
				              <span class="text-uppercase text-muted font-weight-bold">로그아웃</span>
				            </a>
				          </li>
				            
				        </ul>
				      </div>
				    </div>
				  </nav>
				  <!-- End Navbar -->
              	</c:when>
              	
              	<c:otherwise>
					<nav id="navbar-main" class="navbar navbar-main navbar-expand-lg navbar-transparent navbar-light headroom">
				    <div class="container">
				      <a class="navbar-brand mr-lg-5" href="/project/user/index.do">
				        <h5 class="text-black font-weight-bold">주식 정보 알리미</h5>
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
				        <ul class="navbar-nav navbar-nav-hover align-items-lg-center">
				           			            
				          <li class="nav-item dropdown">
				            <a href="/project/login.do" class="nav-link">
				              <span class="text-uppercase text-muted font-weight-bold">로그인</span>
				            </a>
				          </li>
				            
				        </ul>
				      </div>
				    </div>
				  </nav>
				  <!-- End Navbar -->
              	</c:otherwise>	

              </c:choose>
<!-- 네이브 바 end -->
    <div class="section section-hero section-shaped">
      <div class="shape shape-style-1 shape-primary">
          <img src="/assets/img/pages/shares-5279686_1920.jpg" style="background-size: cover;">
      </div>
      <div class="page-header">
        <div class="container shape-container d-flex align-items-center py-lg">
          <div class="col px-0">
            <div class="row align-items-center justify-content-center">
              <div class="col-lg-6 text-center">
                <img src="/assets/img/brand/KakaoTalk_20200610_152149163.png" style="width: 400px;" class="img-fluid">
                <p class="lead text-black"><strong>주식 정보를 빠르고 쉽게 알려드립니다.</strong></p>


              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="separator separator-bottom separator-skew zindex-100">
        <svg x="0" y="0" viewBox="0 0 2560 100" preserveAspectRatio="none" version="1.1" xmlns="http://www.w3.org/2000/svg">
          <polygon class="fill-white" points="2560 0 2560 100 0 100"></polygon>
        </svg>
      </div>
    </div>
    <div class="section section-components pb-0" id="section-components">
      <div class="container">
        <div class="row justify-content-center">

        </div>
      </div>
    </div>
    <div class="section pb-0 section-components">
      <div class="container mb-5">
 
      

  <div class="section" style="background-image: url('/assets/img/ill/1.svg');">
    <div class="container py-md">
      <div class="row justify-content-between align-items-center">
        <div class="col-lg-6 mb-lg-auto">
          <div class="rounded overflow-hidden transform-perspective-left">
            <div id="carousel_example" class="carousel slide" data-ride="carousel">
              <ol class="carousel-indicators">
                <li data-target="#carousel_example" data-slide-to="0" class="active"></li>
                <li data-target="#carousel_example" data-slide-to="1"></li>
                <li data-target="#carousel_example" data-slide-to="2"></li>
              </ol>
              <div class="carousel-inner">
                <div class="carousel-item active">
                  <img class="img-fluid" src="/assets/img/theme/picture1.PNG" alt="First slide">
                </div>
                <div class="carousel-item">
                  <img class="img-fluid" src="/assets/img/theme/picture2.PNG" alt="Second slide">
                </div>
                <div class="carousel-item">
                  <img class="img-fluid" src="/assets/img/theme/picture3.PNG" alt="Third slide">
                </div>
              </div>
              <a class="carousel-control-prev" href="#carousel_example" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
              </a>
              <a class="carousel-control-next" href="#carousel_example" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
              </a>
            </div>
          </div>
        </div>
        <div class="col-lg-5 mb-5 mb-lg-0">
          <h1 class="font-weight-light font-weight-bold">기능 소개</h1>
          <p class="lead mt-4">검색한 주식의 관련 업종 현재가, 기관,외국인 순매매거래량, 
          뉴스분석,종목 토론실 분석 기능을 제공합니다.</p>

        </div>
      </div>
    </div>
  </div>
  
      </div>
      <hr>
          <footer>


      <div class="row align-items-center justify-content-md-between">
        <div class="col-md-6">
          <div class="copyright">
            &copy; 2020 <a href="" target="_blank">1920110020 안민형</a>
          </div>
        </div>
        <div class="col-md-6">

        </div>
      </div>

  </footer>
  </div>
  <!--   Core JS Files   -->
  <script src="/assets/js/core/jquery.min.js" type="text/javascript"></script>
  <script src="/assets/js/core/popper.min.js" type="text/javascript"></script>
  <script src="/assets/js/core/bootstrap.min.js" type="text/javascript"></script>
  <script src="/assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
  <!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
  <script src="/assets/js/plugins/bootstrap-switch.js"></script>
  <!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
  <script src="/assets/js/plugins/nouislider.min.js" type="text/javascript"></script>
  <script src="/assets/js/plugins/moment.min.js"></script>
  <script src="/assets/js/plugins/datetimepicker.js" type="text/javascript"></script>
  <script src="/assets/js/plugins/bootstrap-datepicker.min.js"></script>
  <!-- Control Center for Argon UI Kit: parallax effects, scripts for the example pages etc -->
  <!--  Google Maps Plugin    -->
  <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
  <script src="/assets/js/argon-design-system.min.js?v=1.2.0" type="text/javascript"></script>
  <script>
    function scrollToDownload() {

      if ($('.section-download').length != 0) {
        $("html, body").animate({
          scrollTop: $('.section-download').offset().top
        }, 1000);
      }
    }
  </script>
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