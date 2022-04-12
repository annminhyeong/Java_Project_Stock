<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="poly.dto.CrewlingDTO"%>
<%
	List<CrewlingDTO> oList = (List<CrewlingDTO>)request.getAttribute("oList");
	List<CrewlingDTO> wList = (List<CrewlingDTO>)request.getAttribute("wList");
	List<CrewlingDTO> tList = (List<CrewlingDTO>)request.getAttribute("tList");
%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  
<!-- Styles -->
<style>
#chartdiv {
  width: 70%;
  height: 500px;
}
</style>

<!-- Resources -->
<script src="https://cdn.amcharts.com/lib/4/core.js"></script>
<script src="https://cdn.amcharts.com/lib/4/charts.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/animated.js"></script>

<!-- Chart code -->
<script>
am4core.ready(function() {

// Themes begin
am4core.useTheme(am4themes_animated);
// Themes end

// Create chart instance
var chart = am4core.create("chartdiv", am4charts.XYChart);
chart.scrollbarX = new am4core.Scrollbar();

// Add data
chart.data = [{
  "country": "<%=oList.get(0).getOil_name()%>",
  "visits": <%=oList.get(0).getOil_present_price()%>
}, {
  "country": "<%=oList.get(1).getOil_name()%>",
  "visits": <%=oList.get(1).getOil_present_price()%>
}, {
  "country": "<%=oList.get(2).getOil_name()%>",
  "visits": <%=oList.get(2).getOil_present_price()%>
}, {
  "country": "<%=oList.get(3).getOil_name()%>",
  "visits": <%=oList.get(3).getOil_present_price()%>
}, {
  "country": "<%=oList.get(4).getOil_name()%>",
  "visits": <%=oList.get(4).getOil_present_price()%>
}, {
  "country": "<%=oList.get(5).getOil_name()%>",
  "visits": <%=oList.get(5).getOil_present_price()%>
}];

// Create axes
var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "country";
categoryAxis.renderer.grid.template.location = 0;
categoryAxis.renderer.minGridDistance = 30;
categoryAxis.renderer.labels.template.horizontalCenter = "middle";
categoryAxis.renderer.labels.template.verticalCenter = "middle";
categoryAxis.tooltip.disabled = true;
categoryAxis.renderer.minHeight = 110;

var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
valueAxis.renderer.minWidth = 50;

// Create series
var series = chart.series.push(new am4charts.ColumnSeries());
series.sequencedInterpolation = true;
series.dataFields.valueY = "visits";
series.dataFields.categoryX = "country";
series.tooltipText = "[{categoryX}: bold]{valueY}[/]";
series.columns.template.strokeWidth = 0;

series.tooltip.pointerOrientation = "vertical";

series.columns.template.column.cornerRadiusTopLeft = 10;
series.columns.template.column.cornerRadiusTopRight = 10;
series.columns.template.column.fillOpacity = 0.8;

// on hover, make corner radiuses bigger
var hoverState = series.columns.template.column.states.create("hover");
hoverState.properties.cornerRadiusTopLeft = 0;
hoverState.properties.cornerRadiusTopRight = 0;
hoverState.properties.fillOpacity = 1;

series.columns.template.adapter.add("fill", function(fill, target) {
  return chart.colors.getIndex(target.dataItem.index);
});

// Cursor
chart.cursor = new am4charts.XYCursor();

}); // end am4core.ready()
</script>

    <script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['지수명', '종가', '고가', '저가'],
	      <%for(int i=0; i<wList.size(); i++){ 
				CrewlingDTO pDTO = wList.get(i);
				
				if(pDTO == null){
					pDTO = new CrewlingDTO();
				}
	      %>
          ['<%=pDTO.getWorld_index()%>', <%=pDTO.getWorld_final()%>, <%=pDTO.getWorld_high()%>, <%=pDTO.getWorld_low()%>],
		<%}%>
        ]);

        var options = {
                chart: {
                  title: '해외 주요 지수'
                },
                bars: 'vertical',
                vAxis: {format: 'decimal'},
                height: 700,
                colors: ['#1b9e77', '#d95f02', '#7570b3']
              };

        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
    </script>
    
        <script type="text/javascript">
      google.charts.load('current', {'packages':['table']});
      google.charts.setOnLoadCallback(drawTable);

      function drawTable() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', '테마명');
        data.addColumn('string', '전일대비');
        data.addColumn('string', '등락율(3일 평균)');
        data.addColumn('string', '주도주1');
        data.addColumn('string', '주도주2');
        data.addRows([
          
  	      <%for(int i=0; i<10; i++){ 
				CrewlingDTO pDTO = tList.get(i);
				
				if(pDTO == null){
					pDTO = new CrewlingDTO();
				}
	      %>
          ['<%=pDTO.getTheme_num()%>',  '<%=pDTO.getTheme_name()%>', '<%=pDTO.getTheme_date()%>', '<%=pDTO.getTheme_ju1()%>', '<%=pDTO.getTheme_ju2()%>'],
		<%}%>
        ]);

        var table = new google.visualization.Table(document.getElementById('table_div'));

        table.draw(data, {showRowNumber: true, width: '60%', height: '100%'});
      }
    </script>
</head>
<body>
<div id="chartdiv"></div>
<div id="columnchart_material" style="width: 70%; height: 700px;"></div>
<div id="table_div"></div>
</body>
</html>