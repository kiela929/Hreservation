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
    	window.close();//â�ݱ�
		}

</script>

<head>
<title>ID �ߺ�Ȯ��</title>

<body>

<c:if test="${check eq false}">


<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td height="39" >${id}�̹� ������� ���̵��Դϴ�.</td>
  </tr>
</table>

<form name="checkForm" method="post" action="confirmId.do">
<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td align="center"> 
       �ٸ� ���̵� �����ϼ���.<p>
       <input type="text" size="10" maxlength="12" name="id"> 
       
       <input type="submit" value="�ߺ�Ȯ��" maxlength="16">
				
    </td>
  </tr>
</table>
</form>
</c:if>
<c:if test="${check eq true}">

<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td align="center"> 
      <p>�Է��Ͻ� ${id} �� ����Ͻ� �� <br/>
   		  �ִ� ID�Դϴ�. </p>
      <input type="button" value="�ݱ�" onclick="setid()">
    </td>
  </tr>
</table>
</c:if>
</body>
</html>











