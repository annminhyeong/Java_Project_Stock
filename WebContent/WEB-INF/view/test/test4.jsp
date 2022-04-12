<%@page import="poly.dto.CrewlingDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<CrewlingDTO> stock_deal_List = (List<CrewlingDTO>)request.getAttribute("stock_deal_List");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Year', '기관 순매매량', '외국인 순매매량'],
        <%for(int i=0; i<stock_deal_List.size(); i++){%>
          ['<%=stock_deal_List.get(i).getStock_deal_date()%>',<%=stock_deal_List.get(i).getStock_deal_institution()%>  ,      <%=stock_deal_List.get(i).getStock_deal_foreign()%>],
   		<%}%>
        ]);

        var options = {
          title: '순매매 거래량',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
    </script>
</head>
<body>
    <div id="curve_chart" style="width: 900px; height: 500px"></div>
</body>
</html>