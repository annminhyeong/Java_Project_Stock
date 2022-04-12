<%@page import="poly.dto.DocUserDTO"%>
<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="poly.util.CmmUtil" %>
<%@ page import="poly.dto.DocUserDTO" %>

<%
	//controller로 부터 전달받은 데이터
	String msg = CmmUtil.nvl((String)request.getAttribute("msg"));
	String url = (String)request.getAttribute("url");
	
	//controller로 부터 받은 웹으로 부터 입력받은 데이터
	DocUserDTO pDTO = (DocUserDTO)request.getAttribute("pDTO");
	
	if(pDTO == null){
		pDTO = new DocUserDTO();
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
	alert("<%=msg%>");
	location.href = '<%=url%>';
</script>
</head>
<body>
</body>
</html>