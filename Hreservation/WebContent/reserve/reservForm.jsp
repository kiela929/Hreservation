<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<title>예약하기</title>

<head>
<script src="js/jquery-1.11.2.min.js"></script>
 <script>
function sub(){
 var x = f.part_name.value;
 $.ajax({   
  type: "POST",  
  url: "reserveSelect.do",   
  data: "region="+x,   //&a=xxx 식으로 뒤에 더 붙이면 됨
  success: result    //function result 를 의미함
   }
 );
}
function result(msg){
 //sub()가 실행되면 결과 값을 가지고 와서 action 을 취해주는 callback 함수
 var sel  =  document.f.doctornum;
 $("#sp1").html(msg); //innerHTML 을 이런 방식으로 사용함
 //id 는 $("#id")   name 의 경우 $("name") 으로 접근함
}
</script>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>


<script>
$(function() {
    $( "#testDatepicker" ).datepicker({
    });
});</script>

<style>
td { color:#3f3f3f; font-family:"굴림"; font-size:10pt; line-height:170%;}
td a { color:#797979; font-family:"굴림"; font-size:10pt; line-height:170% ; text-decoration: none; }
td a:hover { color:#3366CC; font-family:"굴림"; font-size:10pt; line-height:170% ; text-decoration: underline;}
input {color:#000000; font-family:"굴림"; font-size:10pt; line-height:120%;background-color: #FFFFFF; border: 1 solid #999999}
</style>

</head>
<form name="f" action="reservePro.do">
<!-- 로그인정보가져오기, 보여주기. 
사용자가 입력해야 하는 부분은 예약 시간, 예약 과, 담당의 

세션정보 가져오기 아이디-->

	<table border=0 width="600" align="center" height="350">	
		<tr>
  			<td align="center" colspan="2"><h2></h2>
  			</td>
  		</tr>	
			<tr bgcolor="#C3C3C3">
      			<td height="1" colspan="2"></td>
    	  	</tr>
			
			<tr>	
				<td bgcolor="#eeeee">아이디</td>
				<td>
				 ${dto.id } <input type="hidden" name="id" value="${dto.id }"> 
				</td>				
			</tr>
			
			<tr>	
				<td bgcolor="#eeeee">진료과</td>
				<td>
				 <select name="part_name" onchange="sub();">
			    <option value="">진료과</option>
				  <c:forEach var="partDTO" items="${partDTO}" varStatus="i">
				  <option value=${partDTO.partnum }>${partDTO.part_name }</option>
				  </c:forEach>
				</select>
				</td>				
			</tr>
				<tr>	
				<td bgcolor="#eeeee">담당의</td>
				<td>
				<span id="sp1">
<select name="doctornum" >
    <option value="">담당의</option>
 
</select>
</span></td>				
			</tr>
			
			
			<tr>	
				<td bgcolor="#eeeee">날짜</td>
				<td>
			<input type="text" id="testDatepicker" name="reserve_date"><br/>
				</td>				
			</tr>
			
			<tr>	
				<td bgcolor="#eeeee">시간</td>
				<td>
			<select name="str_time">
<c:forEach var="time" begin="9" end="11" step="1">
<option value=${time }> ${time }시</option>
</c:forEach>
<c:forEach var="time" begin="13" end="17" step="1">
<option value=${time }> ${time }시</option>
</c:forEach>
</select>	</td>				
			</tr>
			<tr bgcolor="#C3C3C3">
      			<td height="1" colspan="2"></td>
    	  	</tr>
			
			<tr>	
				<td colspan="2" align="right">
				
<input type="submit" value="예약하기">
<input type="button" value="취소"  onClick="javascript:history.go(-1);"/>

</td>
			</tr>
			</table>

    
    </form>