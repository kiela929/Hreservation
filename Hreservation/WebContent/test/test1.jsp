<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
String region  =  request.getParameter("region");
out.println("<select name='sel2'>");
if(region.equals("ġ��")){
 out.println("<option value=''>�����ϼ���</option> "); 
 out.println("<option value='seocho'>����</option> "); 
 out.println("<option value='gangnam'>����</option> "); 
}else{
 out.println("<option value=''>�����ϼ���</option> "); 
 out.println("<option value='guri'>����</option> "); 
 out.println("<option value='hanam'>�ϳ�</option> "); 
}
out.println("</select>");
%>