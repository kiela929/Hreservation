<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<title>�����ϱ�</title>

<head>
<script src="js/jquery-1.11.2.min.js"></script>
 <script>
function sub(){
 var x = f.part_name.value;
 $.ajax({   
  type: "POST",  
  url: "reserveSelect.do",   
  data: "region="+x,   //&a=xxx ������ �ڿ� �� ���̸� ��
  success: result    //function result �� �ǹ���
   }
 );
}
function result(msg){
 //sub()�� ����Ǹ� ��� ���� ������ �ͼ� action �� �����ִ� callback �Լ�
 var sel  =  document.f.doctornum;
 $("#sp1").html(msg); //innerHTML �� �̷� ������� �����
 //id �� $("#id")   name �� ��� $("name") ���� ������
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
td { color:#3f3f3f; font-family:"����"; font-size:10pt; line-height:170%;}
td a { color:#797979; font-family:"����"; font-size:10pt; line-height:170% ; text-decoration: none; }
td a:hover { color:#3366CC; font-family:"����"; font-size:10pt; line-height:170% ; text-decoration: underline;}
input {color:#000000; font-family:"����"; font-size:10pt; line-height:120%;background-color: #FFFFFF; border: 1 solid #999999}
</style>

</head>
<form name="f" action="reservePro.do">
<!-- �α���������������, �����ֱ�. 
����ڰ� �Է��ؾ� �ϴ� �κ��� ���� �ð�, ���� ��, ����� 

�������� �������� ���̵�-->

	<table border=0 width="600" align="center" height="350">	
		<tr>
  			<td align="center" colspan="2"><h2></h2>
  			</td>
  		</tr>	
			<tr bgcolor="#C3C3C3">
      			<td height="1" colspan="2"></td>
    	  	</tr>
			
			<tr>	
				<td bgcolor="#eeeee">���̵�</td>
				<td>
				 ${dto.id } <input type="hidden" name="id" value="${dto.id }"> 
				</td>				
			</tr>
			
			<tr>	
				<td bgcolor="#eeeee">�����</td>
				<td>
				 <select name="part_name" onchange="sub();">
			    <option value="">�����</option>
				  <c:forEach var="partDTO" items="${partDTO}" varStatus="i">
				  <option value=${partDTO.partnum }>${partDTO.part_name }</option>
				  </c:forEach>
				</select>
				</td>				
			</tr>
				<tr>	
				<td bgcolor="#eeeee">�����</td>
				<td>
				<span id="sp1">
<select name="doctornum" >
    <option value="">�����</option>
 
</select>
</span></td>				
			</tr>
			
			
			<tr>	
				<td bgcolor="#eeeee">��¥</td>
				<td>
			<input type="text" id="testDatepicker" name="reserve_date"><br/>
				</td>				
			</tr>
			
			<tr>	
				<td bgcolor="#eeeee">�ð�</td>
				<td>
			<select name="str_time">
<c:forEach var="time" begin="9" end="11" step="1">
<option value=${time }> ${time }��</option>
</c:forEach>
<c:forEach var="time" begin="13" end="17" step="1">
<option value=${time }> ${time }��</option>
</c:forEach>
</select>	</td>				
			</tr>
			<tr bgcolor="#C3C3C3">
      			<td height="1" colspan="2"></td>
    	  	</tr>
			
			<tr>	
				<td colspan="2" align="right">
				
<input type="submit" value="�����ϱ�">
<input type="button" value="���"  onClick="javascript:history.go(-1);"/>

</td>
			</tr>
			</table>

    
    </form>