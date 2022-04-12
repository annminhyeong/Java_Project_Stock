<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="poly.util.CmmUtil" %>

<%

   String cp = request.getContextPath();

%>

<%
	String url = (String)request.getAttribute("url");	

	String SS_ID = CmmUtil.nvl((String)session.getAttribute("SS_ID"));
	String SS_VERIFY = CmmUtil.nvl((String)session.getAttribute("SS_VERIFY"));
	String SS_USER_NAME = CmmUtil.nvl((String)session.getAttribute("SS_USER_NAME"));
	String res = CmmUtil.nvl((String)request.getAttribute("res"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 되었습니다</title>
<%
	String msg ="";
	String test ="";
	
	if(res.equals("1")){
		msg = SS_ID + "님이 로그인 되었습니다.";	
		test = SS_VERIFY + "테스트";
	}else if(res.equals("0")){
		msg = "아이디 비밀번호가 일치하지 않습니다.";
	}else{
		msg = "시스템에 문제가 발생했습니다. 잠시후에 시도해주세요";
	}
%>
<script type="text/javascript">
	alert("<%=msg%>");
	location.href='<%=url%>';
</script>


</head>
<body>
<%=msg %>
<%=test%> 

</body>
</html>