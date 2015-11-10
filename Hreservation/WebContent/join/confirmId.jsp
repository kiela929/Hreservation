<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script>


function openConfirmid(userinput) {

    url = "/confirmId.do?id=" + userinput.id.value;
    window.loaction=url;
}
</script>

<script language="javascript">
  function setid()
    {		
    	opener.document.joinForm.id.value='${id}';
    	window.close();//창닫기
		}

</script>

<head>
<title>ID 중복확인</title>

<body>

<c:if test="${check eq false}">


<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td height="39" >${id}이미 사용중인 아이디입니다.</td>
  </tr>
</table>

<form name="checkForm" method="post" action="confirmId.do">
<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td align="center"> 
       다른 아이디를 선택하세요.<p>
       <input type="text" size="10" maxlength="12" name="id"> 
       
       <input type="submit" value="중복확인" maxlength="16">
				
    </td>
  </tr>
</table>
</form>
</c:if>
<c:if test="${check eq true}">

<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td align="center"> 
      <p>입력하신 ${id} 는 사용하실 수 <br/>
   		  있는 ID입니다. </p>
      <input type="button" value="닫기" onclick="setid()">
    </td>
  </tr>
</table>
</c:if>
</body>
</html>











