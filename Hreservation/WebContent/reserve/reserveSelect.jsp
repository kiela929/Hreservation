<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ���� DB�� �ִ� ������ doctorInPart �� ����Ʈ�� ������ ����. �̰� 
���� ���� �����ϴ� select ���� ����ȿ� �ѷ��ֱ�  -->


<select name="doctornum">
 <option value="">�����</option>
  <c:forEach var="doctor" items="${doctorInPart}" varStatus="i">
  <option value=${doctor.doctornum }>${doctor.name }</option>
  </c:forEach>
</select>
