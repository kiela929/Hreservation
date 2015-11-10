<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
<script type="text/javascript" src="jsChart/jquery.min.js"></script>
<script type="text/javascript" src="jsChart/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="jsChart/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="plugins/jqplot.barRenderer.min.js"></script>
<script type="text/javascript" src="plugins/jqplot.pieRenderer.min.js"></script>
<script type="text/javascript" src="plugins/jqplot.categoryAxisRenderer.min.js"></script>
<script type="text/javascript" src="plugins/jqplot.pointLabels.min.js"></script>
<link rel="stylesheet" type="text/css" href="jsChart/jquery.jqplot.min.css" />


<script>$(document).ready(function(){
        $.jqplot.config.enablePlugins = true;
        var s1 = ${result};
        var ticks = ${part_name};
         
        plot1 = $.jqplot('chart1', [s1], {
            // Only animate if we're not using excanvas (not in IE 7 or IE 8)..
            animate: !$.jqplot.use_excanvas,
            seriesDefaults:{
                renderer:$.jqplot.BarRenderer,
                pointLabels: { show: true }
            },
            axes: {
                xaxis: {
                    renderer: $.jqplot.CategoryAxisRenderer,
                    ticks: ticks
                   
                },
            yaxis: {
                tickOptions: {
                    formatString: "%'d 건"
                }
            }
            },
            highlighter: { show: false }
        });
     
        $('#chart1').bind('jqplotDataClick', 
            function (ev, seriesIndex, pointIndex, data) {
                $('#info1').html('series: '+seriesIndex+', point: '+pointIndex+', data: '+data);
            }
        );
    });
</script>
</head>
<body>


<center>  
<input type="button" value="이번달 예약수 - 원형" onclick="javascript:window.location='pieChart.do'"/>
<input type="button" value="이번달 예약수 - 막대형" onclick="javascript:window.location='barChart.do'"/>
<input type="button" value="누적 예약수" onclick="javascript:window.location='barMonthChart.do'"/>
<br/><br/>

<div id="chart1"></div></center>
  
</body>
</html>