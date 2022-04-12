<%@page import="poly.dto.DocUserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String id = (String)session.getAttribute("SS_ID");
	String email = (String)session.getAttribute("SS_EMAIL");
	String user_name = (String)session.getAttribute("SS_USER_NAME");
	String user_no = (String)session.getAttribute("SS_USER_NO");
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
    <script>
    
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
  <script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>    
  <script type="text/javascript">
//이름 형식 체크
function test_name() {
   var name = f.user_name.value;
   var CheckForm = /^[a-zA-Z]{2,10}|[a-zA-Z]{2,10}|[가-힣]{2,4}$/;

   if (!CheckForm.test(name)) {
      $("#name-type").show();

   } else {
      $("#name-type").hide();
   }
}

</script>
<script type="text/javascript">
$(function checkPwd(){
	$("#alert-success").hide();
	$("#alert-danger").hide();
	$("input").keyup(function(){
		var pwd1=$("#pwd1").val();
		var pwd2=$("#pwd2").val();
        var CheckForm1 = /^[a-z0-9]{8,16}$/;
        
        if(!CheckForm1.test(pwd1)){
        	$("#signupbtn").prop("disabled", true);
        	if(pwd1 !=""){
            $("#pwdtypefail").show(); 
        	}
         }else{
		
		if(pwd1 != "" || pwd2 != ""){
			if(pwd1 == pwd2){ 
			$("#pwdtypefail").hide();
			$("#alert-success").show();
			$("#alert-danger").hide(); $("#submit").removeAttr("disabled");
			$("#signupbtn").prop("disabled", false);
			pwdCheck = 1;
			
			}else{
				$("#pwdtypefail").hide();
				$("#pwdtype").hide();
				$("#alert-success").hide();
				$("#alert-danger").show();
				$("#submit").attr("disabled", "disabled");
				$("#signupbtn").prop("disabled", true);
			} 
		} 
	}
	}); 
}); 
</script>
<script type="text/javascript">
   
    	//회원가입 정보 유효성 채크
    	function doRegUserCheck(f) {
			
    		if(f.id.value==""){
    			alert("아이디를 입력하세요");
    			f.id.focus();
    			return false;
    		}
    		
    		if(f.user_name.value==""){
    			alert("이름를 입력하세요");
    			f.user_name.focus();
    			return false;
    		}
    		
    		if(f.password.value==""){
    			alert("비밀번호를 입력하세요");
    			f.password.focus();
    			return false;
    		}
    		
    		if(f.password2.value==""){
    			alert("비밀번호 확인를 입력하세요");
    			f.password2.focus();
    			return false;
    		}
    		
    		if(f.email.value==""){
    			alert("이메일를 입력하세요");
    			f.email.focus();
    			return false;
    		}
    		
		}  
</script>
</head>

<body class="profile-page">
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
      <img class="bg-image" src="/assets/img/pages/stock-market-2616931_1280.jpg" style="width: 100%;">
      <!-- SVG separator -->
      <div class="separator separator-bottom separator-skew">
        <svg x="0" y="0" viewBox="0 0 2560 100" preserveAspectRatio="none" version="1.1" xmlns="http://www.w3.org/2000/svg">
          <polygon class="fill-secondary" points="2560 0 2560 100 0 100"></polygon>
        </svg>
      </div>
    </section>
    <section class="section bg-secondary">
      <div class="container">
        <div class="card card-profile shadow mt--300">
          <div class="px-4">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">

              </div>
 

            </div>

            <div class="text-center mt-5">

              <h3>아이디:<%=id %></h3>
              <div class="h6 font-weight-300">이메일:<%=email%></div>
              				
              
              
                              <div class="row justify-content-center">
                <div class="col-lg-9">
                    <img src="/assets/img/theme/financial-equalization-1015266_1280.jpg" width="100%">
             
                </div>
              </div>
              <div class="h6 mt-4">
         <form action="/project/mypageUpdateProc.do" name="f" method="post" onsubmit="return doRegUserCheck(this);">                
                  <div class="form-group">
                    <div class="input-group input-group-alternative mb-3">
                      <div class="input-group-prepend">
                        <span class="input-group-text">이름:</span>
                      </div>
                      <input type="text" placeholder="<%=user_name %>" class="form-control" name="user_name" placeholder="이름를 넣으세요" oninput="test_name()"/>
                      <div class="alert alert-danger" id="name-type" style="width: 1000px; display: none;">이름 형식이 아닙니다.</div>
                    </div>
                  </div>

                  <div class="form-group">
                    <div class="input-group input-group-alternative mb-3">
                      <div class="input-group-prepend">
                        <span class="input-group-text">비밀번호:</span>
                      </div>
                      <input type="password" placeholder="" class="form-control" name="password" id="pwd1" />
                      <div class="alert alert-danger" id="pwdtypefail" style="width: 1000px; display: none;">형식이 올바르지 않습니다.(영문소문자, 숫자 8~16글자)</div>
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <div class="input-group input-group-alternative mb-3">
                      <div class="input-group-prepend">
                        <span class="input-group-text">비밀번호 확인:</span>
                      </div>
                      <input type="password" placeholder="" class="form-control" name="password2" id="pwd2" oninput="checkPwd()" />
                      <div class="alert alert-success" id="alert-success" style="width: 1000px;">비밀번호 사용가능.</div> 
                      <div class="alert alert-danger" id="alert-danger" style="width: 1000px;">비밀번호가 일치하지 않습니다.</div>
                    </div>
                                        <button class="btn btn-primary" id="updatebtn">수정하기</button>
					<a href="/project/user/mypageDelete.do" target="_self" class="btn btn-danger">탈퇴하기</a>
                  </div>

           </form>

						
                  </div>
               </div>

            </div>
          </div>
        </div>
      </div>
    </section>
    <footer class="footer">
      <div class="container">
        <div class="row row-grid align-items-center mb-8">
          <div class="col-lg-6">
            <h3 class="text-primary font-weight-light mb-2">주식정보를 알려드립니다.</h3>

          </div>

        </div>
        <hr>
        <div class="row align-items-center justify-content-md-between">
          <div class="col-md-6">
            <div class="copyright">
              &copy; 2020 <a href="" target="_blank">1920110020 안민형</a>
            </div>
          </div>

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