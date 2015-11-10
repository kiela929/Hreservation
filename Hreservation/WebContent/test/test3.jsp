<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<head>
<script type="text/javascript" src="js/jquery-1.11.3.js"></script> 
<script type="text/javascript" src="js/jquery.tablesorter.js"></script> 
<link rel="stylesheet" href="css/themes.blue.css" type="text/css">
<link rel="stylesheet" href="css/themes.green.css" type="text/css">
<script>
$(document).ready(function() { 
    $("table").tablesorter(); 
    $("#ajax-append").click(function() { 
         $.get("assets/ajax-content.html", function(html) { 
             // append the "ajax'd" data to the table body 
             $("table tbody").append(html); 
            // let the plugin know that we made a update 
            $("table").trigger("update"); 
            // set sorting column and direction, this will sort on the first and third column 
            var sorting = [[2,1],[0,0]]; 
            // sort on the first column 
            $("table").trigger("sorton",[sorting]); 
        }); 
        return false; 
    }); 
});          

</script>
</head>

<table cellspacing="1" class="tablesorter">             
    <thead>> 
        <tr> 
            <th>first name</th> 
            <th>last name</th> 
            <th>age</th> 
            <th>total</th> 
            <th>discount</th> 
            <th>date</th> 
        </tr> 
    </thead> 
    <tbody> 
        <tr> 
            <td>peter</td> 
            <td>parker</td> 
            <td>28</td> 
            <td>$9.99</td> 
            <td>20%</td> 
            <td>jul 6, 2006 8:14 am</td> 
        </tr> 
        <tr> 
            <td>john</td> 
            <td>hood</td> 
            <td>33</td> 
            <td>$19.99</td> 
            <td>25%</td> 
            <td>dec 10, 2002 5:14 am</td> 
        </tr> 
        <tr> 
            <td>clark</td> 
            <td>kent</td> 
            <td>18</td> 
            <td>$15.89</td> 
            <td>44%</td> 
            <td>jan 12, 2003 11:14 am</td> 
        </tr> 
        <tr> 
            <td>bruce</td> 
            <td>almighty</td> 
            <td>45</td> 
            <td>$153.19</td> 
            <td>44%</td> 
            <td>jan 18, 2001 9:12 am</td> 
        </tr> 
        <tr> 
            <td>bruce</td> 
            <td>evans</td> 
            <td>22</td> 
            <td>$13.19</td> 
            <td>11%</td> 
            <td>jan 18, 2007 9:12 am</td> 
        </tr> 
    </tbody> 
</table><a href="#" id="ajax-append">append new table data</a><br><br>