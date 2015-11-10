<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<title>로그인</title>
<body>
<center>
<div style="padding: 50 50 50 50;">
<form action="loginPro.do">

<table>
<tr align="center" >
<td align="center">아이디</td>
<td><input type="text" name="id"/></td></tr>

<tr>
<td>비밀번호</td>
<td><input type="password" name="pw"></td></tr>

<tr>
<td colspan="2" align="center"><input type="submit" value="로그인"/>

<input type="button" value="회원가입" onclick="javascript:window.location='join.do'"/></td></tr>


</table>
</form>
</div>
</center>

</body>