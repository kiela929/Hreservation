<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<title>의사 등록</title>
<center>
<form action="doctorJoinPro.do">
아이디 <input type="text" name="id"/> <br/>
비밀번호<input type="password" name="pw"/> <br/>
비밀번호 확인<input type="password" name="pw1"/> <br/>
이름 <input type="text" name="name"/> <br/>
주민등록번호<input type="text" name="jumin1"/> - <input type="text" name="jumin2"/> <br/>
나이 <input type="text" name="age"/> <br/>
전화번호<input type="text" name="phone1">-<input type="text" name="phone2">-<input type="text" name="phone3"><br/>
주소 <input type="text" name="address1"/> <br/>
우편번호 <input type="text" name="address2"/> <br/>
담당과 <input type="text" name="partnum"/> <br/>

<input type="submit" value="회원가입"/>

<input type="reset" value="다시가입"/>

</form>
</center>
