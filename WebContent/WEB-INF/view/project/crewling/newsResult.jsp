<%@page import="poly.dto.CrewlingDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<CrewlingDTO>pList = (List)request.getAttribute("pList");
	List<CrewlingDTO>nList = (List)request.getAttribute("nList");
%>
<!DOCTYPE html>
<html>
  <head>
    <title>jQCloud Example</title>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jqcloud/1.0.4/jqcloud.css" />
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jqcloud/1.0.4/jqcloud-1.0.4.js"></script>
    
    
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
        	  width: 1000,
        	  height: 1000
        	});
      });
    </script>
  </head>
  <body>
  
  
    <!-- You should explicitly specify the dimensions of the container element -->
        <div id="demo"></div>
        
    <!-- 주식 뉴스 크롤링 -->    
        <%for(int i=0; i<nList.size(); i++){
			CrewlingDTO rDTO = nList.get(i);
			
			if(rDTO == null){
				rDTO = new CrewlingDTO();
			}
    	   %>  
  		<span><a href="https://finance.naver.com<%=rDTO.getNews_link()%>"><%=rDTO.getNews_title()%></a></span>
  		<span><%=rDTO.getNews_writer()%></span>
  		<span><%=rDTO.getNews_time()%></span>
  		<br>
  		
  		<%} %> 
  </body>
</html>