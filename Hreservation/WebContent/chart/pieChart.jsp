<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    <head>
    <link class="include" rel="stylesheet" type="text/css" href="jsChart/jquery.jqplot.min.css" />
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="jsChart/jquery.jqplot.min.js"></script>
    
    
    <script type="text/javascript" src="plugins/jqplot.pieRenderer.min.js"></script>
<script type="text/javascript" src="plugins/jqplot.donutRenderer.min.js"></script>
    
    
       <script>
    $(document).ready(function(){
    	  var data = ${pie_list} ;
    	  var plot1 = jQuery.jqplot ('chart1', [data], 
    	    { 
    	      seriesDefaults: {
    	        // Make this a pie chart.
    	        renderer: jQuery.jqplot.PieRenderer, 
    	        rendererOptions: {
    	          // Put data labels on the pie slices.
    	          // By default, labels show the percentage of the slice.
    	          showDataLabels: true
    	        }
    	      }, 
    	      legend: { show:true, location: 'e' }
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
    
    <div id="chart1" style="width:600px; height:600px;"></div>
    </center>
    </body>