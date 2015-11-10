<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

    
<style>
td { color:#3f3f3f; font-family:"굴림"; font-size:10pt; line-height:170%;}
td a { color:#797979; font-family:"굴림"; font-size:10pt; line-height:170% ; text-decoration: none; }
td a:hover { color:#3366CC; font-family:"굴림"; font-size:10pt; line-height:170% ; text-decoration: underline;}
input {color:#000000; font-family:"굴림"; font-size:10pt; line-height:120%;background-color: #FFFFFF; border: 1 solid #999999}
</style>
    <title>의사등록 완료</title>
    <h2>의사등록</h2>
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
				${dto.name }
				</td>				
			</tr>
				<tr align="center">	
				<td align="center" bgcolor="#eeeee">담당과</td>
				<td>
				${dto.partnum }
				</td>				
			</tr>
				<tr align="center">	
				<td align="center" bgcolor="#eeeee">아이디</td>
				<td>
				${dto.id }
				</td>				
			</tr>
				<tr align="center">	
				<td  align="center" bgcolor="#eeeee">이름</td>
				<td>
				${dto.name }
				</td>				
			</tr>
					<tr align="center">	
				<td align="center" bgcolor="#eeeee">주민등록번호</td>
				<td>
		 ${dto.jumin1 }- ${dto.jumin2 }
				</td>				
			</tr>
				<tr align="center">	
				<td align="center" bgcolor="#eeeee">나이</td>
				<td>
				${dto.age }
				</td>				
			</tr>
					<tr align="center">	
				<td align="center" bgcolor="#eeeee">이메일</td>
				<td>
			${dto.email }
				</td>				
			</tr>
					<tr align="center">	
				<td align="center" bgcolor="#eeeee">전화번호</td>
				<td>
				${dto.phone1 }-${dto.phone2 }-${dto.phone3 }
				</td>				
			</tr>
				<tr align="center">	
				<td align="center" bgcolor="#eeeee">주소</td>
				<td>
				${dto.post1} ${dto.post2 }
				</td>				
			</tr>
				<tr align="center">	
				<td align="center" bgcolor="#eeeee">우편번호</td>
				<td>
			${dto.postnum1 }-${dto.postnum2 }
				</td>				
			</tr>
				<tr align="center">	
				<td align="center" bgcolor="#eeeee">회원등급</td>
				<td>
			 ${dto.grade }
    
				</td>				
			</tr>
			
    </form>



