<%@page import="java.util.List"%>
<%@page import="poly.dto.CrewlingDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<%
	List<String> same_company_List = (List<String>)request.getAttribute("same_company_List");
	List<String> same_present_price_List = (List<String>)request.getAttribute("same_present_price_List");
	List<String> same_price_change_List = (List<String>)request.getAttribute("same_price_change_List");
	List<CrewlingDTO> stock_deal_List = (List<CrewlingDTO>)request.getAttribute("stock_deal_List");
%>
<%
	List<CrewlingDTO>pList = (List)request.getAttribute("pList");
	List<CrewlingDTO>mList = (List)request.getAttribute("mList");
	String stock_name =(String)request.getAttribute("stock_name");
	String currentstock =(String)request.getAttribute("currentstock");
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
    
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jqcloud/1.0.4/jqcloud.css" />
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
    <script src="/assets/js/core/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jqcloud/1.0.4/jqcloud-1.0.4.js"></script>
    <script type="text/javascript">
    google.charts.load("current", {packages:["corechart"]});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
     <%if(same_company_List.size()!=0){%>	
      var data = google.visualization.arrayToDataTable([
        ["관련 회사", "현재가", { role: "style" } ],
        ["<%=same_company_List.get(0)%>", <%=same_present_price_List.get(0)%>, "#FFE08C"],
        ["<%=same_company_List.get(1)%>", <%=same_present_price_List.get(1)%>, "#B2CCFF"],
        ["<%=same_company_List.get(2)%>", <%=same_present_price_List.get(2)%>, "#FAED7D"],
        ["<%=same_company_List.get(3)%>", <%=same_present_price_List.get(3)%>, "#00D8FF"],
        ["<%=same_company_List.get(4)%>", <%=same_present_price_List.get(4)%>, "color: #e5e4e2"]
      ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        title: "<%=same_company_List.get(0)%> 관련 업종별 현재가",
        bar: {groupWidth: "95%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.BarChart(document.getElementById("barchart_values"));
      chart.draw(view, options);
      <%}%>
  }
  </script>
  
      <script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawStuff);

      function drawStuff() {
    	<%if(same_company_List.size()!=0){%>	
        var data = new google.visualization.arrayToDataTable([
          ['관련 회사', '등락률'],
          ["<%=same_company_List.get(0)%>", <%=same_price_change_List.get(0)%>],
          ["<%=same_company_List.get(1)%>", <%=same_price_change_List.get(1)%>],
          ["<%=same_company_List.get(2)%>", <%=same_price_change_List.get(2)%>],
          ["<%=same_company_List.get(3)%>", <%=same_price_change_List.get(3)%>],
          ['<%=same_company_List.get(4)%>', <%=same_price_change_List.get(4)%>]
        ]);

        var options = {
          legend: { position: 'none' },
          chart: {
            title: '현재시각 관련 주식 등락폭',
            subtitle: '정규장' },
          axes: {
            x: {
              0: { side: 'top', label: '<%=stock_name%> 관련 업종별 등락률'} // Top x-axis.
            }
          },
          bar: { groupWidth: "90%" }
        };

        var chart = new google.charts.Bar(document.getElementById('top_x_div'));
        // Convert the Classic options to Material options.
        chart.draw(data, google.charts.Bar.convertOptions(options));
        <%}%>
      };
    </script>
    
        <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Year', '기관 순매매량', '외국인 순매매량'],
        <%for(int i=(stock_deal_List.size()-1); i>=0; i--){%>
          ['<%=stock_deal_List.get(i).getStock_deal_date()%>',<%=stock_deal_List.get(i).getStock_deal_institution()%>  ,      <%=stock_deal_List.get(i).getStock_deal_foreign()%>],
   		<%}%>
        ]);

        var options = {
          title: '<%=stock_name%> 관련 기관,외국인 순매매 거래량',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
    </script>
    
        <script type="text/javascript">
      /*!
       * Create an array of word objects, each representing a word in the cloud
       */
       var words = [
    	   <%for(int i=0; i<pList.size(); i++){%>
    	   {text: "<%=pList.get(i).getWordname()%>", weight: <%=pList.get(i).getWordcount()%>},
			<%}%>
    	 ];



      $(function() {
        // When DOM is ready, select the container element and call the jQCloud method, passing the array of words as the first argument.    
        $("#demo").jQCloud(words, {
        	  height: 700
        	});
      });
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
      <img class="bg-image" src="/assets/img/pages/stock-1863880_1280.jpg" style="width: 100%;">
      <!-- SVG separator -->
    </section>


    </div>
    <div class="section section-components pb-0" id="section-components">
      <div class="container">
      	<h1 class="display-4" style="text-align: center;"><%=stock_name%></h1>
      	<br>
      	<h3 class="display-4" style="text-align: center;">현재가:<%=currentstock%></h3>
      	<%if(same_company_List.size()!=0){%>		
        <!-- 관련 현재가 -->
            <div id="barchart_values" style="width: 100%; height: 500px;"></div>    
        <!-- 현재가 -->

        <!-- 등락률 -->
        <div id="top_x_div" style="width: 100%; height: 500px;"></div>
        <!-- 등락률 -->
        <%}%>

        <!-- 기관,외국인 순매매거래량 -->
        <div id="curve_chart" style="width: 100%; height: 500px"></div>      
        <!-- 기관,외국인 순매매거래량 -->
        <br>
		<br>
		<br>	
		<br>		
        <h1 class="display-4" style="text-align: center;"><%=stock_name%> 뉴스 분석결과:</h1>
        <div id="demo" style="width: 100%"></div>
      	
      	<div class="col-lg-9 col-10 mx-md-auto">
			<div class="row justify-content-center">
		
		<h1 class="display-4" style="text-align: center;"><%=stock_name%> 관련 뉴스:</h1>	
    	 <!-- 주식 뉴스 크롤링 -->    
        <%for(int i=0; i<mList.size(); i++){
			CrewlingDTO rDTO = mList.get(i);
			
			if(rDTO == null){
				rDTO = new CrewlingDTO();
			}
    	   %>  
  		            <!--1 start-->
            <div class="col-lg-12 mb-4">
              <div class="card shadow">
                  <div class="card-body">
                      <p class="display-4">
                          <a href="https://finance.naver.com<%=rDTO.getNews_link()%>"><%=rDTO.getNews_title()%></a>
                      </p>
                      <p class="lead mb-5">
                          	<%=rDTO.getNews_writer()%>
                      </p>
                      <p class="description text-right col-lg-12">
                          <%=rDTO.getNews_time()%>
                      </p>
                      

                  </div>
              </div>
            </div>
            <!--1 end--> 
  		
  		
  		<%} %>   
  		
  			</div>
		</div>
  		
  		
  		
  		
  		
  		              
      </div>
        </div>

    <br /><br />
    

    <div class="container">
        
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