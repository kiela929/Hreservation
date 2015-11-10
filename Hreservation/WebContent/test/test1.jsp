<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
String region  =  request.getParameter("region");
out.println("<select name='sel2'>");
if(region.equals("치과")){
 out.println("<option value=''>선택하세요</option> "); 
 out.println("<option value='seocho'>서초</option> "); 
 out.println("<option value='gangnam'>강남</option> "); 
}else{
 out.println("<option value=''>선택하세요</option> "); 
 out.println("<option value='guri'>구리</option> "); 
 out.println("<option value='hanam'>하남</option> "); 
}
out.println("</select>");
%>