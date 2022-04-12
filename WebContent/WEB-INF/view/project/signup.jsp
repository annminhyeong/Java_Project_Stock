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
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="../assets/img/favicon.png">
  <title>
주식 정보 알리미
  </title>
  <!--     Fonts and icons     -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
  <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
  <!-- Nucleo Icons -->
  <link href="../assets/css/nucleo-icons.css" rel="stylesheet" />
  <link href="../assets/css/nucleo-svg.css" rel="stylesheet" />
  <!-- Font Awesome Icons -->
  <link href="../assets/css/font-awesome.css" rel="stylesheet" />
  <link href="../assets/css/nucleo-svg.css" rel="stylesheet" />
  <!-- CSS Files -->
  <link href="../assets/css/argon-design-system.css?v=1.2.0" rel="stylesheet" />
    
    <script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>    
  <script type="text/javascript">
	function doLoginUserCheck(f) {
		
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
    	
<script>
 
//     아이디와 비밀번호가 맞지 않을 경우 가입버튼 비활성화를 위한 변수설정
    
    	function checkEmail(){

		$("#email-success").hide();
		$("#email-danger").hide();
		var email = $('input[name=email]').val();
	
        var CheckForm = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
        
        if(!CheckForm.test(email)){
        	$("#updatebtn").prop("disabled", true);
			$("#email-type").show();

         }else{
		$.ajax({
			
			data:{
				email : email,
			},
			
			url:"/project/checkEmail.do",
			success: function(data) {
				console.log(data);

				if(data>0){
					$("#email-type").hide();
					$("#email-success").hide();
					$("#email-danger").show();
					$("#updatebtn").prop("disabled", true);
				}
				 
				else {
					$("#email-type").hide();
					$("#email-success").show();
					$("#email-danger").hide();
					$("#updatebtn").prop("disabled", true);
				}

			}
			
		});
         }
	
	}
    
    
    
    
    
    
    
    
    
    
    var idCheck = 0;
    var pwdCheck = 0;
    //아이디 체크하여 가입버튼 비활성화, 중복확인.
    function checkId() {
        var inputed = $('#id').val();
        var inputed1 = $('#pwd1').val();
        var CheckForm = /^[a-z0-9]{5,16}$/;

        
        if(!CheckForm.test(inputed)){
        	$("#signupbtn").prop("disabled", true);
            $("#idtype").show();
            $("#idSuccess").hide();
			$("#idExist").hide();
			$("#idBlank").hide();
         }

        else{
        	 
        

        $.ajax({
            data : {
                id : inputed
            },
            url : "/project/checkId.do",
            success : function(data) {
                if(inputed=="" && data=='0') {
                    $("#idtype").hide();
                	$("#idSuccess").hide();
					$("#idExist").hide();
					$("#idBlank").show();
                	$("#signupbtn").prop("disabled", true);
                    idCheck = 0;
                } else if (data == '0') {
                    
                	
                	
                	$("#idtype").hide();
                	$("#idSuccess").show();
					$("#idExist").hide();
					$("#idBlank").hide();
                    idCheck = 1;
                    if(idCheck==1 && pwdCheck == 1) {
                       
                    	$("#signupbtn").prop("disabled", false);
                        signupCheck();
                    } 
                } else if (data == '1') {
                	$("#idtype").hide();
                	$("#idSuccess").hide();
 					$("#idExist").show();
 					$("#idBlank").hide();
                    $("#signupbtn").prop("disabled", true);
                    idCheck = 0;
                } 
            }
        });
        
         }
    }

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
</head>

<body class="register-page">
  <div class="wrapper">
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
              <div class="card-header bg-white pb-4">
              
                  <div class="text-muted text-center mb-3">
                    <h2 class="mb-0">회원가입</h2>
                  </div>
              </div>
              <div class="card-body px-lg-5 py-lg-5">

                <form role="form" name="f" action="/project/insertUserinfo.do">
                  
                  <div class="form-group">
                    <div class="input-group input-group-alternative mb-3">
                      <div class="input-group-prepend">
                        <span class="input-group-text">아이디:</span>
                      </div>
                      <input type="text" placeholder="" class="form-control" name="id" required  id="id" oninput="checkId()"/>
                      <div class="alert alert-danger" id="idtype" style="width: 1000px; display: none;">형식이 올바르지 않습니다.(영문소문자, 숫자 5~16글자)</div>
              	      <div class="alert alert-success" id="idSuccess" style="width: 1000px; display: none;">아이디 사용가능</div> 
              	      <div class="alert alert-danger" id="idExist" style="width: 1000px; display: none;">아이디가 중복됩니다</div>
              	      <div class="alert alert-danger" id="idBlank" style="width: 1000px; display: none;">아이디를 입력해주세요</div>
                    </div>
                      
                  </div>    
                    
                  <div class="form-group">
                    <div class="input-group input-group-alternative mb-3">
                      <div class="input-group-prepend">
                        <span class="input-group-text">이름:</span>
                      </div>
                      <input type="text" placeholder="" class="form-control" name="user_name" placeholder="이름를 넣으세요" oninput="test_name()"/>
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
                  </div>
                    
                  <div class="form-group">
                    <div class="input-group input-group-alternative mb-3">
                      <div class="input-group-prepend">
                        <span class="input-group-text">이메일:</span>
                      </div>
                      <input type="text" placeholder="" class="form-control" name="email" oninput="checkEmail()" />
                      <div class="alert alert-success" id="email-success" style="width: 1000px; display: none;">이메일 사용가능.</div> 
                      <div class="alert alert-danger" id="email-danger" style="width: 1000px; display: none;">이메일이 중복됩니다.</div>
			          <div class="alert alert-danger" id="email-type" style="width: 1000px; display: none;">이메일이 형식이 아닙니다.</div>
                        
                    </div>
                  </div>
                    

                  <div class="text-center">
                    <input type="submit" class="btn btn-primary mt-4" value="Create account" id="signupbtn">
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <footer class="footer">
      <div class="container">
        <div class="row row-grid align-items-center mb-5">
          <div class="col-lg-6">
            <h3 class="text-primary font-weight-light mb-2">환영합니다!</h3>
            <h4 class="mb-0 font-weight-light">주식정보를 알려드립니다.</h4>
          </div>

        </div>
        <hr>
        <div class="row align-items-center justify-content-md-between">
          <div class="col-md-6">
            <div class="copyright">
              1920110020 안민형
            </div>
          </div>

        </div>
      </div>
    </footer>
  </div>
  <!--   Core JS Files   -->
  <script src="../assets/js/core/jquery.min.js" type="text/javascript"></script>
  <script src="../assets/js/core/popper.min.js" type="text/javascript"></script>
  <script src="../assets/js/core/bootstrap.min.js" type="text/javascript"></script>
  <script src="../assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
  <!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
  <script src="../assets/js/plugins/bootstrap-switch.js"></script>
  <!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
  <script src="../assets/js/plugins/nouislider.min.js" type="text/javascript"></script>
  <script src="../assets/js/plugins/moment.min.js"></script>
  <script src="../assets/js/plugins/datetimepicker.js" type="text/javascript"></script>
  <script src="../assets/js/plugins/bootstrap-datepicker.min.js"></script>
  <!-- Control Center for Argon UI Kit: parallax effects, scripts for the example pages etc -->
  <!--  Google Maps Plugin    -->
  <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
  <script src="../assets/js/argon-design-system.min.js?v=1.2.0" type="text/javascript"></script>
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