<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

    
<style>
td { color:#3f3f3f; font-family:"����"; font-size:10pt; line-height:170%;}
td a { color:#797979; font-family:"����"; font-size:10pt; line-height:170% ; text-decoration: none; }
td a:hover { color:#3366CC; font-family:"����"; font-size:10pt; line-height:170% ; text-decoration: underline;}
input {color:#000000; font-family:"����"; font-size:10pt; line-height:120%;background-color: #FFFFFF; border: 1 solid #999999}
</style>
    <title>�ǻ��� �Ϸ�</title>
    <h2>�ǻ���</h2>
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
				<td align="center" bgcolor="#eeeee">�̸�</td>
				<td>
				${dto.name }
				</td>				
			</tr>
				<tr align="center">	
				<td align="center" bgcolor="#eeeee">����</td>
				<td>
				${dto.partnum }
				</td>				
			</tr>
				<tr align="center">	
				<td align="center" bgcolor="#eeeee">���̵�</td>
				<td>
				${dto.id }
				</td>				
			</tr>
				<tr align="center">	
				<td  align="center" bgcolor="#eeeee">�̸�</td>
				<td>
				${dto.name }
				</td>				
			</tr>
					<tr align="center">	
				<td align="center" bgcolor="#eeeee">�ֹε�Ϲ�ȣ</td>
				<td>
		 ${dto.jumin1 }- ${dto.jumin2 }
				</td>				
			</tr>
				<tr align="center">	
				<td align="center" bgcolor="#eeeee">����</td>
				<td>
				${dto.age }
				</td>				
			</tr>
					<tr align="center">	
				<td align="center" bgcolor="#eeeee">�̸���</td>
				<td>
			${dto.email }
				</td>				
			</tr>
					<tr align="center">	
				<td align="center" bgcolor="#eeeee">��ȭ��ȣ</td>
				<td>
				${dto.phone1 }-${dto.phone2 }-${dto.phone3 }
				</td>				
			</tr>
				<tr align="center">	
				<td align="center" bgcolor="#eeeee">�ּ�</td>
				<td>
				${dto.post1} ${dto.post2 }
				</td>				
			</tr>
				<tr align="center">	
				<td align="center" bgcolor="#eeeee">�����ȣ</td>
				<td>
			${dto.postnum1 }-${dto.postnum2 }
				</td>				
			</tr>
				<tr align="center">	
				<td align="center" bgcolor="#eeeee">ȸ�����</td>
				<td>
			 ${dto.grade }
    
				</td>				
			</tr>
			
    </form>



