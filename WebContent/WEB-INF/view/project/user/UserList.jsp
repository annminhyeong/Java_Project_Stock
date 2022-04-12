<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="poly.util.CmmUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="poly.dto.DocUserDTO"%>  

    
      
<%

session.setAttribute("SESSION_USER_ID","USER01");

List<DocUserDTO> rList = (List<DocUserDTO>)request.getAttribute("rList");

if(rList == null){
	rList = new ArrayList<DocUserDTO>();
}

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
 	   
    
     <script type="text/javascript">
 	 function fn_paging(curPage) {
	  location.href = "/project/notice/NoticeList.do?curPage=" + curPage;
	  }
 </script>

   <script>

   // 생략	

	$(document).on('click', '#btnSearch', function(e){

		e.preventDefault();

		var url = "/project/notice/NoticeList";
		
		location.href = url;

		console.log(url);

	});	

</script> 
 
  <script type="text/javascript">
	function doDetail(seq) {
		location.herf="/project/NoticeInfo.do?nSeq=" + seq;
	}
  </script>
   
       <style>

.list{
position:relative;
width:100%;

}
.ov{
text-overflow: ellipsis;
}
.ov2{
text-overflow: ellipsis;
}
.list dl dt {
   text-overflow: ellipsis;
   overflow:hidden;
  white-space:nowrap; 
   margin: auto;
  float:left;
}

.list dl dd {
  float:left;
}

.list .num {width:9%; }
.list .sub {width:44%; }
.list .name {width:15%; }
.list .data {width:15%; }
.list .count {width:17%; }


.list dt {
  width:11%;
  text-overflow: ellipsis;
  overflow:hidden;
  white-space:nowrap; 
  text-align:center;
  max-height:38px;
  border-bottom:2px solid black;
  padding:10px 5px;
  }

.list dd {
text-overflow: ellipsis;
  width:11%;
  max-height:38px;
  overflow:hidden;
  white-space:nowrap; 
  text-align:center;
  padding:8px 5px;
  border-bottom:1px solid #ccc;
}

  .list dd.sub {
   padding-left:5px; width:44%px;
}

#board .list-bot {
   clear: both;
   text-align:center;
   padding:10px 10px;
}

</style>
    
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
    <div class="section section-hero section-shaped">
      <div class="shape shape-style-3 shape-default">
        <span class="span-150"></span>
        <span class="span-50"></span>
        <span class="span-50"></span>
        <span class="span-75"></span>
        <span class="span-100"></span>
        <span class="span-75"></span>
        <span class="span-50"></span>
        <span class="span-100"></span>
        <span class="span-50"></span>
        <span class="span-100"></span>
      </div>
      <div class="page-header">
        <div class="container shape-container d-flex align-items-center py-lg">
          <div class="col px-0">
            <div class="row align-items-center justify-content-center">
              <div class="col-lg-6 text-center">
                <h1 class="text-white display-1">사용자 관리</h1>


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

          <div class="col-lg-10 col-10 mx-md-auto">
              <!-- 공지사항 리스트 입력-->

                <!-- search{s} -->
		<div style="margin-left: auto;width: 350px;">
		<form action="/project/noticeTest.do">
		<div class="form-group row justify-content-center">
			
			
			
		
			<div class="w100" style="padding-right:10px">
				
				
			
				<select class="form-control form-control-sm" name="searchType" id="searchType">

					<option value="notice_title">제목</option>

					<option value="notice_content">본문</option>

					<option value="notice_writer">작성자</option>

				</select>
			
			
			</div>

			<div class="w300" style="padding-right:10px">

				<input type="text" class="form-control form-control-sm" name="keyword" id="keyword">

			</div>

			<div>

				<button class="btn btn-sm btn-primary" name="btnSearch" id="btnSearch">검색</button>

			</div>

		</div>
			</form>
	</div>
			<!-- search{s} end -->
	
			<!-- 테이블 -->
          <div class="list">
      <dl>
         <dt class="num"><a class="jg">번호</a></dt>
         <dt class="sub"><a class="jg">제목</a></dt>
         <dt class="name"><a class="jg">작성자</a></dt>
         <dt class="data"><a class="jg">날짜</a></dt>
         <dt class="count"><a class="jg">조회 수</a></dt>   
   
      </dl>
      

		<%
			for(int i=0; i<rList.size(); i++){
				DocUserDTO rDTO = rList.get(i);
				
				if(rDTO == null){
					rDTO = new DocUserDTO();
				}
			
		%>
      <dl id="lists">
     
         <dd class="num"><%out.print(CmmUtil.nvl(rDTO.getUser_no()));%></dd>
         <dd class="sub"><%=CmmUtil.nvl(rDTO.getId()) %></dd>
         <dd class="name"><%=CmmUtil.nvl(rDTO.getUser_name()) %></dd>
         <dd class="data"><%=CmmUtil.nvl(rDTO.getRegdate()) %></dd>
         <dd class="count"><a href="/project/user/UserDelete.do?user_no=<%=rDTO.getUser_no() %>" >삭제하기</a></dd>

      </dl>
<%
}
%>


      </div>


      <!-- Tab panes -->
<!-- 테이블 끝 -->

		
<!-- 페이징 시작 -->
<!-- 1. eq = equal (==)

2. ne = not equal (!=)

3. empty = list,map등의 객체가 값이 있다,없다를 구분(ex)empty, !empty)
 -->
				<div style="display: inline-block;">
		            <!-- 1~5페이지 아닌 경우에 처음 찍기 -->
		            <ul class="pagination">
                    <c:if test="${pagination.curRange ne 1 }">
                        <a class="page-link" href="#" onClick="fn_paging(1)">처음</a> 
                    </c:if>
                    <!-- 1페이지가 아닌 경우에 이전 찍기 -->
                    <c:if test="${pagination.curPage ne 1}">
                        <a class="page-link" href="#" onClick="fn_paging('${pagination.prevPage }')">이전</a> 
                    </c:if>
                    
                    <!--for문 느낌 begin 부터 end 까지 -->
                    <c:forEach var="pageNum" begin="${pagination.startPage }" end="${pagination.endPage }">
                        <c:choose>
                            <c:when test="${pageNum eq  pagination.curPage}">
                                <span style="font-weight: bold;"><a  class="page-link" href="#"  onClick="fn_paging('${pageNum }')">${pageNum }</a></span> 
                            </c:when>
                            <c:otherwise>
                                <a class="page-link" href="#" onClick="fn_paging('${pageNum }')">${pageNum }</a> 
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    
                    
                    <!-- 마지막 페이지가 아닌 경우에 다음 찍기 -->
                    <c:if test="${pagination.curPage ne pagination.pageCnt && pagination.pageCnt > 0}">
                        <a class="page-link" href="#" onClick="fn_paging('${pagination.nextPage }')">다음</a> 
                    </c:if>
                    
                    <!-- 마지막 페이지 블록이 아닐때 끝 찍기 -->
                    <c:if test="${pagination.curRange ne pagination.rangeCnt && pagination.rangeCnt > 0}">
                        <a class="page-link" href="#" onClick="fn_paging('${pagination.pageCnt }')">끝</a> 
                    </c:if>
                    
                    </ul>
                </div>
 <!-- 페이징 끝 -->               



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