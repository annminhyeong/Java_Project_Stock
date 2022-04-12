<%@page import="poly.dto.CrewlingDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<CrewlingDTO> pList = (List)request.getAttribute("pList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%for(int i=0; i<pList.size(); i++){ %>
				<span><%=pList.get(i).getTalk_title() %></span>
				<span><%=pList.get(i).getTalk_date() %></span>
				<span><%=pList.get(i).getTalk_id() %></span>
				<br>
	<%} %>
</body>
</html>