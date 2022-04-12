<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Year', '코스피', '코스닥'],
          ['2020.06.03',  2140.31,      743.54],
          ['2020.06.02',  2087.19,      743.58],
          ['2020.06.01',  2065.08,       735.72],
          ['2020.05.29',  2029.60,      713.68],
          ['2020.05.28',  2028.54,       708.75],
          ['2020.05.27',  2031.20,       724.59],
          ['2020.05.26',  2029.78,       729.11],
        ]);

        var options = {
          title: '코스피 코스닥 시세',
          hAxis: {title: '일별시세',  titleTextStyle: {color: '#333'}},
          vAxis: {minValue: 0}
        };

        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
  <script type="text/javascript">
    google.charts.load("current", {packages:["corechart"]});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
      var data = google.visualization.arrayToDataTable([
        ["관련 회사", "Density", { role: "style" } ],
        ["회사1", 8.94, "#FFE08C"],
        ["회사2", 10.49, "#B2CCFF"],
        ["회사3", 19.30, "#FAED7D"],
        ["회사4", 19.30, "#00D8FF"],
        ["회사5", 21.45, "color: #e5e4e2"]
      ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        title: "Density of Precious Metals, in g/cm^3",
        bar: {groupWidth: "95%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.BarChart(document.getElementById("barchart_values"));
      chart.draw(view, options);
  }
  </script>
  </head>
  <body>
    <div id="chart_div" style="width: 100%; height: 500px;"></div>
<div id="barchart_values" style="width: 900px; height: 300px;"></div>
  </body>
</html>