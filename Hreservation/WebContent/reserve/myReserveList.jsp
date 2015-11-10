<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<center>
<h2>나의 예약현황</h2>
<hr width="1000" color="EEEEE"><br/><br/>
</center>
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

<body> 
 	<c:if test="${requestScope.totalCount == 0}">
 		<center>
  		예약내용이 없습니다.<br/><br/>
	    </center>
  		
  	</c:if>


 <c:if test="${requestScope.totalCount > 0}">
<form name="reserve.do" method="post">
	<table class="tablesorter" width="850" cellpadding="0" cellspacing="0" align="center">
	        <thead align="center"> 
	    <tr height="30" align="center"> 
	      <th align="center"  width="130" bgcolor="#DDE787" >예약번호</th> 
	      <th align="center"  width="150" bgcolor="#FFF685">ID</th>
	      <th align="center"  width="150"  bgcolor="#DDE787">예약날짜</th>
	      <th align="center"  width="150" bgcolor="#FFF685">예약시간</th>
	      <th align="center"  width="180"  bgcolor="#DDE787">담당의코드</th>
	      <th align="center"  width="150" bgcolor="#FFF685">담당의</th>
	      <th align="center"  width="220"  bgcolor="#DDE787">담당의연락처</th>
	      <th align="center"  width="150" bgcolor="#FFF685">진료과</th> 
	      <th align="center"  width="150"  bgcolor="#DDE787">등록날짜</th>
	      <th align="center"  width="150"></th>
	    </tr>
	       </thead> 
	           <tbody> 
	    <c:forEach var="list" items="${mylist_list}" varStatus="i">
	    	<tr height="65">
		    	<td align="center"  bgcolor="#F2F6D5" >
		    	<font size="2">${list.reservenum }</font>
			    </td>
			     	<td align="center" bgcolor="#FFFCD7">
		    	<font size="2">${list.id }</font>
			    </td>
			    
			        	<td align="center" bgcolor="#F2F6D5" >
		    	<font size="2">${list.reserve_date_string }</font>
			    </td>
			     
			     	<td align="center" bgcolor="#FFFCD7">
		    	<font size="2">${list.str_time }시</font>
			    </td>
			    
			    	<td align="center" bgcolor="#F2F6D5" >
		    	<font size="2">${list.doctornum }</font>
			    </td>
			   
			     	<td align="center" bgcolor="#FFFCD7">
		    	<font size="2">${list.doc_name }</font>
			    </td>
			   <td align="center"  bgcolor="#F2F6D5" >
		    	<font size="2">${list.phone1 }-${list.phone2 }-${list.phone3 }</font>
			    </td>
			    
			    	<td align="center" bgcolor="#FFFCD7">
		    	<font size="2">${list.part_name }</font>
			    </td>
			    
			     <td align="center" bgcolor="#F2F6D5" >
		    	<font size="2">${list.reg_date_string }</font>
			    </td>
			    <td align="center" >
		    	<input type="button" value="cancel" 
		    	onclick="window.location='reserveDel.do?reservenum=${list.reservenum}'"><br />			    		
		    	</td> 
	    	</tr>    
    </c:forEach>
    </tbody>
    
    </table>
</form>
	    <br/>
	    
	    <!-- 
	    <center>
	    	<input type="button" value="myPage" onclick="javascript:window.location='mypage.do'">    	
	    </center> -->

   	</c:if>
   	<center>
	<br/><font size="2">${pagingHtml}</font>
		<input type="hidden" name="currentPage" value="${currentPage}" >	
	<br/><br/><br/><br/>
</center>
</body>
   