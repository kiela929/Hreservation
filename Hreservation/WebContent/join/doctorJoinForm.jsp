<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>ȸ��������</title>
<script>
function checkIt() {
    var userinput = eval("document.joinForm");
    if(!userinput.id.value) {
        alert("ID�� �Է��ϼ���");
        return false;
    }
    //�ߺ�Ȯ���� ������ �ʾ��� ��� ���â
    if(!userinput.confirm_id.value) {
        alert("ID �ߺ�Ȯ���� ���ּ���");
        return false;
    }
    
    if(!userinput.pw.value ) {
        alert("��й�ȣ�� �Է��ϼ���");
        return false;
    }
    
    if(userinput.pw.value != userinput.pw1.value)
    {
        alert("��й�ȣ�� �����ϰ� �Է��ϼ���");
        return false;
    }
   
    if(!userinput.name.value) {
        alert("����� �̸��� �Է��ϼ���");
        return false;
    }
    
    if(!userinput.postnum1.value  || !userinput.postnum2.value  || !userinput.post1.value || !userinput.post2.value)
    {
        alert("�ּҸ� �Է��ϼ���");
        return false;
    }
    
    if(!userinput.phone2.value || !userinput.phone3.value)
    {
        alert("�ڵ�����ȣ�� �Է��ϼ���");
        return false;
    }
    
    if(!userinput.email.value )
    {
        alert("�̸����� �Է��ϼ���");
        return false;
    }
    
  
    
}



// ���̵� �ߺ� ���θ� �Ǵ�
function openConfirmid(userinput) {
    // ���̵� �Է��ߴ��� �˻�
    if (userinput.id.value == "") {
        alert("���̵� �Է��ϼ���");
        return;
    }
    // url�� ����� �Է� id�� �����մϴ�.
    url = "confirmId.do?id=" + userinput.id.value;
                       //get������� �ٶ� �Ķ���� �� = ?
    // ���ο� �����츦 ���ϴ�.
    open(url, "confirm", 
    "toolbar=no, location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300, height=200");
}
</script>
<style>
td { color:#3f3f3f; font-family:"����"; font-size:10pt; line-height:170%;}
td a { color:#797979; font-family:"����"; font-size:10pt; line-height:170% ; text-decoration: none; }
td a:hover { color:#3366CC; font-family:"����"; font-size:10pt; line-height:170% ; text-decoration: underline;}
input {color:#000000; font-family:"����"; font-size:10pt; line-height:120%;background-color: #FFFFFF; border: 1 solid #999999}
</style>

	<form action="doctorJoinPro.do" name="joinForm" >
		
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
				<input type="text" name="id" />
				<input type="button" name="confirm_id" OnClick="openConfirmid(this.form)" value="�ߺ�Ȯ��" maxlength="16">
					
				</td>				
			</tr>
			
			<tr>	
				<td bgcolor="#eeeee">��й�ȣ</td> 
				<td>
					<input type="password" name="pw"  /><br/>
				</td>
			</tr>
			<tr>
				<td bgcolor="#eeeee">��й�ȣȮ��</td>
				<td>
					<input type="password" name="pw1" /><br/>
				</td>
			</tr>
			
			<tr>	
				<td bgcolor="#eeeee">�̸�</td>
				<td>
					<input type="text" name="name"  />
				</td>
			</tr>
			<tr>	
				<td bgcolor="#eeeee">�ֹε�Ϲ�ȣ</td> <td><input type="text" name="jumin1"  size="10"/> - 
				<input type="text" name="jumin2"  size="10"/> 
						  		</td>
			</tr>
			
					<tr>	
				<td bgcolor="#eeeee">����</td> 
				<td> <input type="text" name="age"/>
								
						  		</td>
			</tr>
			
	
			<tr>	
				<td bgcolor="#eeeee">��ȭ��ȣ</td> <td><select name="phone1">
									   <option value="010">010</option>
									   <option value="011">011</option>
									   <option value="016">016</option>
									   <option value="017">017</option>
									   <option value="019">019</option>
								  </select>
								 -<input type="text" name="phone2" size="4"   >
								  -<input type="text" name="phone3" size="4"   >	
							  </td>
			</tr>
			<tr>	
				<td bgcolor="#eeeee">�̸���</td> <td><input type="text" name="email" size="25" />											  				  
								 
							  </td>
			</tr>
			
			
		
			<tr>	
				<td bgcolor="#eeeee">�ּ�</td>	<td><input type="text" id="sample6_postcode1" size="5" name="postnum1"   /> -
				<input type="text" id="sample6_postcode2" size="5" name="postnum2"  />
				<input type="button" onclick="sample6_execDaumPostcode()" value="�����ȣ ã��"/><br/>
				<input type="text" id="sample6_address" placeholder="�ּ�" name="post1" />
				<input type="text" id="sample6_address2" placeholder="���ּ�" name="post2" />
				
				
				<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
				<script>
				    function sample6_execDaumPostcode() {
				        new daum.Postcode({
				            oncomplete: function(data) {
				                // �˾����� �˻���� �׸��� Ŭ�������� ������ �ڵ带 �ۼ��ϴ� �κ�.
				
				                // �� �ּ��� ���� ��Ģ�� ���� �ּҸ� �����Ѵ�.
				                // �������� ������ ���� ���� ��쿣 ����('')���� �����Ƿ�, �̸� �����Ͽ� �б� �Ѵ�.
				                var fullAddr = ''; // ���� �ּ� ����
				                var extraAddr = ''; // ������ �ּ� ����
				
				                // ����ڰ� ������ �ּ� Ÿ�Կ� ���� �ش� �ּ� ���� �����´�.
				                if (data.userSelectedType === 'R') { // ����ڰ� ���θ� �ּҸ� �������� ���
				                    fullAddr = data.roadAddress;
				
				                } else { // ����ڰ� ���� �ּҸ� �������� ���(J)
				                    fullAddr = data.jibunAddress;
				                }
				
				                // ����ڰ� ������ �ּҰ� ���θ� Ÿ���϶� �����Ѵ�.
				                if(data.userSelectedType === 'R'){
				                    //���������� ���� ��� �߰��Ѵ�.
				                    if(data.bname !== ''){
				                        extraAddr += data.bname;
				                    }
				                    // �ǹ����� ���� ��� �߰��Ѵ�.
				                    if(data.buildingName !== ''){
				                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				                    }
				                    // �������ּ��� ������ ���� ���ʿ� ��ȣ�� �߰��Ͽ� ���� �ּҸ� �����.
				                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
				                }
				
				                // �����ȣ�� �ּ� ������ �ش� �ʵ忡 �ִ´�.
				                document.getElementById("sample6_postcode1").value = data.postcode1;
				                document.getElementById("sample6_postcode2").value = data.postcode2;
				                document.getElementById("sample6_address").value = fullAddr;
				
				                // Ŀ���� ���ּ� �ʵ�� �̵��Ѵ�.
				                document.getElementById("sample6_address2").focus();
				            }
				        }).open();
				    }
				</script>
				
							</td>
			 	
			</tr>
			<tr>
			<td bgcolor="#eeeee">����</td> 
			<td>
			<select name="partnum">
	 <option value=1>�Ȱ�</option>
	  <option value=2>ġ��</option>
	   <option value=3>�Ҿư�</option>
	    <option value=4>�����ܰ�</option>
			</select>
			
								 
			</tr>
			<tr bgcolor="#C3C3C3">
      			<td height="1" colspan="2"></td>
    	  	</tr>
			
			<tr>	
				<td colspan="2" align="right">
					<input type="submit" value="�����ϱ�"/>
					<input type="reset" value="�ٽ��Է�" />
				<input type="button" value="���"  onClick="javascript:history.go(-1);"/>	</td>
			</tr>
		</table>			
	</form>	
	</html>
	
		
			