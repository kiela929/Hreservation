<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 현재 DB에 있는 내용을 doctorInPart 에 리스트로 가지고 있음. 이걸 
현재 폼에 존재하는 select 안의 내용안에 뿌려주기  -->


<select name="doctornum">
 <option value="">담당의</option>
  <c:forEach var="doctor" items="${doctorInPart}" varStatus="i">
  <option value=${doctor.doctornum }>${doctor.name }</option>
  </c:forEach>
</select>
