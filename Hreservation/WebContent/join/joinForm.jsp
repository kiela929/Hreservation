<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>회원가입폼</title>
			
				<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
				<script>
				    function sample6_execDaumPostcode() {
				        new daum.Postcode({
				            oncomplete: function(data) {
				                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				
				                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
				                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				                var fullAddr = ''; // 최종 주소 변수
				                var extraAddr = ''; // 조합형 주소 변수
				
				                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				                    fullAddr = data.roadAddress;
				
				                } else { // 사용자가 지번 주소를 선택했을 경우(J)
				                    fullAddr = data.jibunAddress;
				                }
				
				                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
				                if(data.userSelectedType === 'R'){
				                    //법정동명이 있을 경우 추가한다.
				                    if(data.bname !== ''){
				                        extraAddr += data.bname;
				                    }
				                    // 건물명이 있을 경우 추가한다.
				                    if(data.buildingName !== ''){
				                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				                    }
				                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
				                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
				                }
				
				                // 우편번호와 주소 정보를 해당 필드에 넣는다.
				                document.getElementById("sample6_postcode1").value = data.postcode1;
				                document.getElementById("sample6_postcode2").value = data.postcode2;
				                document.getElementById("sample6_address").value = fullAddr;
				
				                // 커서를 상세주소 필드로 이동한다.
				                document.getElementById("sample6_address2").focus();
				            }
				        }).open();
				    }
				</script>
<script>
function checkIt() {
    var userinput = eval("document.joinForm");
    if(!userinput.id.value) {
        alert("ID를 입력하세요");
        return false;
    }
    //중복확인을 누르지 않았을 경우 경고창
    if(!userinput.confirm_id.value) {
        alert("ID 중복확인을 해주세요");
        return false;
    }
    
    if(!userinput.pw.value ) {
        alert("비밀번호를 입력하세요");
        return false;
    }
    
    if(userinput.pw.value != userinput.pw1.value)
    {
        alert("비밀번호를 동일하게 입력하세요");
        return false;
    }
   
    if(!userinput.name.value) {
        alert("사용자 이름을 입력하세요");
        return false;
    }
    
    if(!userinput.postnum1.value  || !userinput.postnum2.value  || !userinput.post1.value || !userinput.post2.value)
    {
        alert("주소를 입력하세요");
        return false;
    }
    
    if(!userinput.phone2.value || !userinput.phone3.value)
    {
        alert("핸드폰번호를 입력하세요");
        return false;
    }
    
    if(!userinput.email.value )
    {
        alert("이메일을 입력하세요");
        return false;
    }
    
  
    
}



// 아이디 중복 여부를 판단
function openConfirmid(userinput) {
    // 아이디를 입력했는지 검사
    if (userinput.id.value == "") {
        alert("아이디를 입력하세요");
        return;
    }
    // url과 사용자 입력 id를 조합합니다.
    url = "confirmId.do?id=" + userinput.id.value;
                       //get방식으로 줄때 파라미터 값 = ?
    // 새로운 윈도우를 엽니다.
    open(url, "confirm", 
    "toolbar=no, location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300, height=200");
}
</script>
<style>
td { color:#3f3f3f; font-family:"굴림"; font-size:10pt; line-height:170%;}
td a { color:#797979; font-family:"굴림"; font-size:10pt; line-height:170% ; text-decoration: none; }
td a:hover { color:#3366CC; font-family:"굴림"; font-size:10pt; line-height:170% ; text-decoration: underline;}
input {color:#000000; font-family:"굴림"; font-size:10pt; line-height:120%;background-color: #FFFFFF; border: 1 solid #999999}
</style>

	<form action="joinPro.do" name="joinForm" >
		
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
				<input type="text" name="id" id="id"/>
					<input type="button" name="confirm_id" OnClick="openConfirmid(this.form)" value="중복확인" maxlength="16">
				
				</td>				
			</tr>
			
			<tr>	
				<td bgcolor="#eeeee">비밀번호</td> 
				<td>
					<input type="password" name="pw"  /><br/>
				</td>
			</tr>
			<tr>
				<td bgcolor="#eeeee">비밀번호확인</td>
				<td>
					<input type="password" name="pw1" /><br/>
				</td>
			</tr>
			
			<tr>	
				<td bgcolor="#eeeee">이름</td>
				<td>
					<input type="text" name="name"  />
				</td>
			</tr>
			<tr>	
				<td bgcolor="#eeeee">주민등록번호</td> <td><input type="text" name="jumin1"  size="10"/> - 
				<input type="text" name="jumin2"  size="10"/> 
						  		</td>
			</tr>
			
					<tr>	
				<td bgcolor="#eeeee">나이</td> 
				<td> <input type="text" name="age"/>
								
						  		</td>
			</tr>
			
	
			<tr>	
				<td bgcolor="#eeeee">전화번호</td> <td><select name="phone1">
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
				<td bgcolor="#eeeee">이메일</td> <td><input type="text" name="email" size="25" />											  				  
								 
							  </td>
			</tr>
			
			
		
			<tr>	
				<td bgcolor="#eeeee">주소</td>	<td><input type="text" id="sample6_postcode1" size="5" name="postnum1"   /> -
				<input type="text" id="sample6_postcode2" size="5" name="postnum2"  />
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"/><br/>
				<input type="text" id="sample6_address" placeholder="주소" name="post1" />
				<input type="text" id="sample6_address2" placeholder="상세주소" name="post2" />
				
	
				
							</td>
			 	
			</tr>
			<tr bgcolor="#C3C3C3">
      			<td height="1" colspan="2"></td>
    	  	</tr>
			
			<tr>	
				<td colspan="2" align="right">
					<input type="submit" value="가입하기"/>
					<input type="button" value="다시입력" onclick="sa();"/>
					<input type="button" value="가입안함" onclick="javascript:window.location='template.action'"/>
				</td>
			</tr>
		</table>			
	</form>	
	</html>