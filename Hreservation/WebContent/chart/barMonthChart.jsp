<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="jsChart/jquery.min.js"></script>
<script type="text/javascript" src="jsChart/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="plugins/jqplot.barRenderer.min.js"></script>
<script type="text/javascript" src="plugins/jqplot.highlighter.min.js"></script>
<script type="text/javascript" src="plugins/jqplot.cursor.min.js"></script>
<script type="text/javascript" src="plugins/jqplot.pointLabels.min.js"></script>
<script type="text/javascript" src="plugins/jqplot.categoryAxisRenderer.min.js"></script>
<link rel="stylesheet" type="text/css" href="jsChart/jquery.jqplot.min.css" />
    
    
    <script>
    $(document).ready(function () {
        var s1 = ${total_list2};
        var s2 = ${total_list2};
        var ticks = ${total_list};
        plot1 = $.jqplot("chart3", [s2, s1], {
            // Turns on animatino for all series in this plot.
            animate: true,
            // Will animate plot on calls to plot1.replot({resetAxes:true})
            animateReplot: true,
            cursor: {
                show: true,
                zoom: true,
                looseZoom: true,
                showTooltip: false
            },
            series:[
                {
                    pointLabels: {
                        show: true
                    },
                    renderer: $.jqplot.BarRenderer,
                    showHighlight: false,
                    yaxis: 'y2axis',
                    rendererOptions: {
                        // Speed up the animation a little bit.
                        // This is a number of milliseconds.  
                        // Default for bar series is 3000.  
                        animation: {
                            speed: 2500
                        },
                        barWidth: 15,
                        barPadding: -15,
                        barMargin: 0,
                        highlightMouseOver: false
                    }
                }, 
                {
                    rendererOptions: {
                        // speed up the animation a little bit.
                        // This is a number of milliseconds.
                        // Default for a line series is 2500.
                        animation: {
                            speed: 2000
                        }
                    }
                }
            ],
            axesDefaults: {
                pad: 0
            },
            axes: {
                // These options will set up the x axis like a category axis.
                xaxis: {
                	  renderer: $.jqplot.CategoryAxisRenderer,
                      ticks: ticks
                },
                yaxis: {
                    tickOptions: {
                        formatString: "%'d 건"
                    },
                    rendererOptions: {
                        forceTickAt0: true
                    }
                },
                y2axis: {
                    tickOptions: {
                        formatString: "%'d 건"
                    },
                    rendererOptions: {
                        // align the ticks on the y2 axis with the y axis.
                        alignTicks: true,
                        forceTickAt0: true
                    }
                }
            },
            highlighter: {
                show: true, 
                showLabel: true, 
                tooltipAxes: 'y',
                sizeAdjust: 7.5 , tooltipLocation : 'ne'
            }
        });
       
    });
    </script>
</head>
<body>

<center>  
<input type="button" value="이번달 예약수 - 원형" onclick="javascript:window.location='pieChart.do'"/>
<input type="button" value="이번달 예약수 - 막대형" onclick="javascript:window.location='barChart.do'"/>
<input type="button" value="누적 예약수" onclick="javascript:window.location='barMonthChart.do'"/>
<br/><br/>

<div id="chart3"></div></center>
</body>
</html>