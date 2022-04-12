<%@page import="poly.dto.CrewlingDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<%
	List<CrewlingDTO>pList = (List)request.getAttribute("pList");
	List<CrewlingDTO>sList = (List)request.getAttribute("sList");
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
    
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['회사명', 'percent'],
   	   <%for(int i=0; i<pList.size(); i++){%>
          ['<%=pList.get(i).getCompany_name()%>',     <%=pList.get(i).getSearchrate()%>],
       <%}%>
        ]);

        var options = {
          title: '검색 비율',
          pieHole: 0.4,
        };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
        chart.draw(data, options);
      }
    </script>
    
    
	 <script type="text/javascript">
	function searchchk(f) {
		
		if(f.stock_name.value==""){
			alert("검색어를 입력하세요");
			f.stock_name.focus();
			return false;
		}
	}
	</script>	
    
    
</head>

<body class="landing-page">
			<!-- 네이브 바 start -->
              <c:choose>
              	<c:when test="${sessionScope.SS_VERIFY == 1}">
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
				        <ul class="navbar-nav navbar-nav-hover align-items-lg-center">
				            
				          <li class="nav-item dropdown">
				            <a href="/project/notice/NoticeList.do" class="nav-link">
				              <span class="nav-link-inner--text">공지사항 관리</span>
				            </a>
				          </li>
				            
				          <li class="nav-item dropdown">
				            <a href="/project/user/UserList.do" class="nav-link">
				              <span class="nav-link-inner--text">사용자 관리</span>
				            </a>
				          </li>
				            
				          <li class="nav-item dropdown">
				            <a href="/project/user/mypageUpdate.do" class="nav-link">
				              <span class="nav-link-inner--text">마이페이지</span>
				            </a>
				          </li>
				            
				          <li class="nav-item dropdown">
				            <a href="/project/Logout.do" class="nav-link">
				              <span class="nav-link-inner--text">로그아웃</span>
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
				        <ul class="navbar-nav navbar-nav-hover align-items-lg-center">
				            
				          <li class="nav-item dropdown">
				            <a href="/project/notice/NoticeList.do" class="nav-link">
				              <span class="nav-link-inner--text">공지사항</span>
				            </a>
				          </li>
				           
				           <li class="nav-item dropdown">
				            <a href="/project/crewling/stock.do" class="nav-link">
				              <span class="nav-link-inner--text">주식정보<br>&emsp;검색</span>
				            </a>
				          </li>
				           				            
				          <li class="nav-item dropdown">
				            <a href="/project/crewling/talk.do" class="nav-link">
				              <span class="nav-link-inner--text">종목 토론실<br>&emsp;&ensp;분석</span>
				            </a>
				          </li>
				            
				          <li class="nav-item dropdown">
				            <a href="/project/crewling/kosgrap.do" class="nav-link">
				              <span class="nav-link-inner--text">코스피,코스닥</span>
				            </a>
				          </li>
				          
				          <li class="nav-item dropdown">
				            <a href="/project/crewling/stop.do" class="nav-link">
				              <span class="nav-link-inner--text">기타 주식정보</span>
				            </a>
				          </li>
				            
				          <li class="nav-item dropdown">
				            <a href="/project/user/mypageUpdate.do" class="nav-link">
				              <span class="nav-link-inner--text">마이페이지</span>
				            </a>
				          </li>
				            
				          <li class="nav-item dropdown">
				            <a href="/project/Logout.do" class="nav-link">
				              <span class="nav-link-inner--text">로그아웃</span>
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
				        <ul class="navbar-nav navbar-nav-hover align-items-lg-center">
				            
				          <li class="nav-item dropdown">
				            <a href="/project/notice/NoticeList.do" class="nav-link">
				              <span class="nav-link-inner--text">공지사항</span>
				            </a>
				          </li>
				           
				           <li class="nav-item dropdown">
				            <a href="/project/crewling/stock.do" class="nav-link">
				              <span class="nav-link-inner--text">주식정보<br>&emsp;검색</span>
				            </a>
				          </li>
				           				            
				          <li class="nav-item dropdown">
				            <a href="/project/crewling/talk.do" class="nav-link">
				              <span class="nav-link-inner--text">종목 토론실<br>&emsp;&ensp;분석</span>
				            </a>
				          </li>
				            
				          <li class="nav-item dropdown">
				            <a href="/project/crewling/kosgrap.do" class="nav-link">
				              <span class="nav-link-inner--text">코스피,코스닥</span>
				            </a>
				          </li>
				          
				          <li class="nav-item dropdown">
				            <a href="/project/crewling/stop.do" class="nav-link">
				              <span class="nav-link-inner--text">기타 주식정보</span>
				            </a>
				          </li>
				            
				          <li class="nav-item dropdown">
				            <a href="/project/Logout.do" class="nav-link">
				              <span class="nav-link-inner--text">로그아웃</span>
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
				        <ul class="navbar-nav navbar-nav-hover align-items-lg-center">
				           			            
				          <li class="nav-item dropdown">
				            <a href="/project/login.do" class="nav-link">
				              <span class="nav-link-inner--text">로그인</span>
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
  <div class="wrapper">
    <section class="section-profile-cover section-shaped my-0">
      <!-- Circles background -->
	  <div class="shape shape-style-3 shape-default">
			      <img class="bg-image" src="/assets/img/pages/stock-1863880_1280.jpg" style="width: 100%; height: 100%">
      </div>

        <div class="container shape-container d-flex align-items-center py-lg">
          <div class="col px-0">
            <div class="row align-items-center justify-content-center">
              <div class="col-lg-6 text-center">
                <h1 class="text-white display-1">주식 정보 검색</h1>


              </div>
            </div>
          </div>
        </div>

      
      
      <!-- SVG separator -->
    </section>

      <div class="container">
				<br>
						<!-- 최근조회 기능 -->
			<div style="display: inline-block;"><strong>최근 조회:</strong></div>
		<%for(int j=0; j<sList.size(); j++){%>

			<div style="display:inline-block;"><%=sList.get(j).getStock_name()%><%if(j!= sList.size() -1){ %>,<%} %></div>

		<%} %>
		<!-- 최근조회 기능 -->
                <div class="form-group">
                    <form action="/StockSearch.do" name="f" onsubmit="return searchchk(this);">
                    
                     	<input type="text" style="height: 50px; width: 90%; float: left; border-radius: 5px; border: 1px solid #dcdcdc;" name="stock_name"  placeholder="검색할 종목을 넣으세요.">     
                    	<input type="submit" class="btn btn-primary" style="height: 50px; width: 100px" value="검색">

                    </form>
              </div>
   			   <div id="donutchart" style="width: 100%; height: 500px;"></div>


		</div>
		

	</div>



    



 

  <!--   Core JS Files   -->

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