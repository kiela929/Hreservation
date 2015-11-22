<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

    <link class="include" rel="stylesheet" type="text/css" href="../jsChart/jquery.jqplot.min.css" />
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="../jsChart/jquery.jqplot.min.js"></script>
    
    
    <script type="text/javascript" src="../plugins/jqplot.pieRenderer.min.js"></script>
<script type="text/javascript" src="../plugins/jqplot.donutRenderer.min.js"></script>
    
    <script type="text/javascript" src="../plugins/jqplot.barRenderer.min.js"></script>
<script type="text/javascript" src="../plugins/jqplot.categoryAxisRenderer.min.js"></script>
<script type="text/javascript" src="../plugins/jqplot.pointLabels.min.js"></script>
    
    
    
<script type="text/javascript" src="../jsChart/jquery.min.js"></script>
<script type="text/javascript" src="../jsChart/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="../plugins/jqplot.barRenderer.min.js"></script>
<script type="text/javascript" src="../plugins/jqplot.highlighter.min.js"></script>
<script type="text/javascript" src="../plugins/jqplot.cursor.min.js"></script>
<script type="text/javascript" src="../plugins/jqplot.pointLabels.min.js"></script>
<link rel="stylesheet" type="text/css" href="../jsChart/jquery.jqplot.min.css" />
    
    <script>
    $(document).ready(function(){
    	  var data = [
    	    ['Heavy Industry', 12],['Retail', 9], ['Light Industry', 14], 
    	    ['Out of home', 16],['Commuting', 7], ['Orientation', 9]
    	  ];
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
    
    
    
    
    $(document).ready(function(){
        var s1 = [200, 600, 700, 1000];
        var s2 = [460, -210, 690, 820];
        var s3 = [-260, -440, 320, 200];
        // Can specify a custom tick Array.
        // Ticks should match up one for each y value (category) in the series.
        var ticks = ['May', 'June', 'July', 'August'];
         
        var plot1 = $.jqplot('chart2', [s1, s2, s3], {
            // The "seriesDefaults" option is an options object that will
            // be applied to all series in the chart.
            seriesDefaults:{
                renderer:$.jqplot.BarRenderer,
                rendererOptions: {fillToZero: true}
            },
            // Custom labels for the series are specified with the "label"
            // option on the series option.  Here a series option object
            // is specified for each series.
            series:[
                {label:'Hotel'},
                {label:'Event Regristration'},
                {label:'Airfare'}
            ],
            // Show the legend and put it outside the grid, but inside the
            // plot container, shrinking the grid to accomodate the legend.
            // A value of "outside" would not shrink the grid and allow
            // the legend to overflow the container.
            legend: {
                show: true,
                placement: 'outsideGrid'
            },
            axes: {
                // Use a category axis on the x axis and use our custom ticks.
                xaxis: {
                    renderer: $.jqplot.CategoryAxisRenderer,
                    ticks: ticks
                },
                // Pad the y axis just a little so bars can get close to, but
                // not touch, the grid boundaries.  1.2 is the default padding.
                yaxis: {
                    pad: 1.05,
                    tickOptions: {formatString: '$%d'}
                }
            }
        });
    });
    
    
    
    $(document).ready(function () {
        var s1 = [[2002, 112000], [2003, 122000], [2004, 104000], [2005, 99000], [2006, 121000], 
        [2007, 148000], [2008, 114000], [2009, 133000], [2010, 161000], [2011, 173000]];
        var s2 = [[2002, 10200], [2003, 10800], [2004, 11200], [2005, 11800], [2006, 12400], 
        [2007, 12800], [2008, 13200], [2009, 12600], [2010, 13100]];
     
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
                    tickInterval: 1,
                    drawMajorGridlines: false,
                    drawMinorGridlines: true,
                    drawMajorTickMarks: false,
                    rendererOptions: {
                    tickInset: 0.5,
                    minorTicks: 1
                }
                },
                yaxis: {
                    tickOptions: {
                    	  formatString: "$%'d"
                    },
                    rendererOptions: {
                        forceTickAt0: true
                    }
                },
                y2axis: {
                    tickOptions: {
                        formatString: "$%'d"
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

<body>
<div id="chart1" style="width:300px; height:300px;"></div>

<div id="chart2" style="width:300px; height:300px;"></div>
<div id="chart3"></div>
</body>