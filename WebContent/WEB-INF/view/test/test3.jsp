<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawStuff);

      function drawStuff() {
        var data = new google.visualization.arrayToDataTable([
          ['Move', '전일대비'],
          ["data", 0.55],
          ["data", -0.90],
          ["data", -0.44],
          ["data", 1.28],
          ['data', -1.18]
        ]);

        var options = {
          width: 800,
          legend: { position: 'none' },
          chart: {
            title: 'Chess opening moves',
            subtitle: 'popularity by percentage' },
          axes: {
            x: {
              0: { side: 'top', label: 'White to move'} // Top x-axis.
            }
          },
          bar: { groupWidth: "90%" }
        };

        var chart = new google.charts.Bar(document.getElementById('top_x_div'));
        // Convert the Classic options to Material options.
        chart.draw(data, google.charts.Bar.convertOptions(options));
      };
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
  <body>
    <div id="top_x_div" style="width: 800px; height: 600px;"></div>
        <div id="table_div"></div>
          <div class="list">
      <dl>
         <dt class="num"><a class="jg">번호</a></dt>
         <dt class="sub"><a class="jg">제목</a></dt>
         <dt class="name"><a class="jg">작성자</a></dt>
         <dt class="data"><a class="jg">날짜</a></dt>
         <dt class="count"><a class="jg">조회 수</a></dt>   
   
      </dl>
      
      <dl id="lists">
     
         <dd class="num">a</dd>
         <dd class="sub">a</dd>
         <dd class="name">a</dd>
         <dd class="data">a</dd>
         <dd class="count">a</dd>

      </dl>

      </div>
  </body>
</html>
