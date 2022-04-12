<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="oracle.net.aso.b"%>
<%@page import="poly.dto.DocNoticeDTO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	DocNoticeDTO pDTO = (DocNoticeDTO)request.getAttribute("pDTO");

	
%>

    
    
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

<body class="landing-page">
  <div class="wrapper">
    <div class="section section-hero section-shaped">
      <div class="shape shape-style-3 shape-default">
		<img src="/assets/img/pages/bulletin-board-2768204_1920.jpg" style="background-size: cover; width: 100%; height: 100%">
      </div>
      <div class="page-header">
        <div class="container shape-container d-flex align-items-center py-lg">
          <div class="col px-0">
            <div class="row align-items-center justify-content-center">
              <div class="col-lg-6 text-center">
                <h1 class="text-white display-1">상세보기</h1>


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
    <div class="section features-6">
      <div class="container">
        <div class="row align-items-center">

          <div class="col-lg-9 col-10 mx-md-auto">
              <!-- 공지사항 리스트 입력-->
                	<form action="/project/notice/NoticeUpdate.do">
		<div>	
			<input type="hidden" name="notice_no" value="<%=pDTO.getNotice_no()%>">
		</div>
		
		<div class="centered">
			<input type="text" readonly="readonly" name="notice_title" maxlength="100" style="width: 100%" value="<%=pDTO.getNotice_title()%>" class="form-control" >
		</div>
			<br>
		
		<div class="centered">
			<div readonly="readonly" name="notice_content" style="width: 100%; height: 400px" class="form-control"><%=pDTO.getNotice_content()%></div>
		</div>
			<br>
		
		<c:choose>
			<c:when test="${sessionScope.SS_VERIFY == 1}">
				<div class="text-center">
					
					<div style="display: inline-block;">	
						<input type="submit" value="수정" class="btn btn-primary">
					</div>
					
					<div style="display: inline-block;">
						<input type="button" onclick="location.href='/project/notice/NoticeDelete.do?notice_no=<%=pDTO.getNotice_no()%>'" value="삭제" class="btn btn-danger">
					</div>
			
					<div style="display: inline-block;">	
						<input type="button" onclick="location.href='/project/notice/NoticeList.do'" value="돌아가기"  class="btn btn-success">
					</div>
				</div>
			</c:when>
			<c:when test="${sessionScope.SS_VERIFY == 0}">
				<div class="text-center">
					<div style="display: inline-block;">	
						<input type="button" onclick="location.href='/project/notice/NoticeList.do'" value="돌아가기"  class="btn btn-success">
					</div>
				</div>
			</c:when>
			
			<c:when test="${not empty sessionScope.SS_ID or user_id ne null}">
				<div class="text-center">
					<div style="display: inline-block;">	
						<input type="button" onclick="location.href='/project/notice/NoticeList.do'" value="돌아가기"  class="btn btn-success">
					</div>
				</div>
			</c:when>
			
			<c:otherwise>
			
			</c:otherwise>
		</c:choose>
	</form>
                



          </div>
   
              

              
          </div>
        </div>
      </div>
    </div>

    <br /><br />
    <footer class="footer">
      <div class="container">
        <div class="row row-grid align-items-center mb-5">
          <div class="col-lg-6">
            <h3 class="text-primary font-weight-light mb-2"></h3>
            <h4 class="mb-0 font-weight-light"></h4>
          </div>
          <div class="col-lg-6 text-lg-center btn-wrapper">
            
          </div>
        </div>
        <hr>
        <div class="row align-items-center justify-content-md-between">
          <div class="col-md-6">
            <div class="copyright">
              &copy; 2020 <a href="" target="_blank">1920110020 안민형</a>
            </div>
          </div>
          <div class="col-md-6">
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