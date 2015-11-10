<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

       <form>
		<table border=0 width="600" align="center" height="350" >	
		<tr align="center">
  			<td align="center" colspan="2"><h2></h2>
  			</td>
  		</tr>	
			<tr align="center" bgcolor="#C3C3C3">
      			<td height="1" colspan="2"></td>
    	  	</tr>
			<tr align="center">	
				<td align="center" bgcolor="#eeeee">이름</td>
				<td>
			${memberDTO.name}
				</td>				
			</tr>
				
				<tr align="center">	
				<td  align="center" bgcolor="#eeeee">생년월일</td>
				<td>
${memberDTO.jumin1 }
				</td>				
			</tr>
					<tr align="center">	
				<td align="center" bgcolor="#eeeee">예약일</td>
				<td>
	${ReserveDTO.reserve_date_string }
				</td>				
			</tr>
				<tr align="center">	
				<td align="center" bgcolor="#eeeee">시간</td>
				<td>
				${ReserveDTO.str_time}
				</td>				
			</tr>
					<tr align="center">	
				<td align="center" bgcolor="#eeeee">진료과</td>
				<td>
				${partDTO.part_name }
				</td>				
			</tr>
					<tr align="center">	
				<td align="center" bgcolor="#eeeee">담당의</td>
				<td>
				${doctorDTO.id }
				</td>				
			</tr>
				
			


</form>


