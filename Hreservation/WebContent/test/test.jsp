<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script>
function sub(){
 var x = f.sel1.value;
 $.ajax({   
  type: "POST",  
  url: "test1.jsp",   
  data: "region="+x,   //&a=xxx ������ �ڿ� �� ���̸� ��
  success: result    //function result �� �ǹ���
   }
 );
}
function result(msg){
 //sub()�� ����Ǹ� ��� ���� ������ �ͼ� action �� �����ִ� callback �Լ�
 var sel  =  document.f.sel2;
 $("#sp1").html(msg); //innerHTML �� �̷� ������� �����
 //id �� $("#id")   name �� ��� $("name") ���� ������
}
</script>
<script src="js/jquery-1.11.2.min.js"></script>
</head>
<body>
<form name="f">
 <select name="sel1" onchange="sub();">
  <option value="">�����ϼ���</option>
  <option value="seoul">����</option>
  <option value="kyung">���</option>
 </select>
 <span id="sp1">
  <select name="sel2">
   <option value="">�����ϼ���</option>
  </select>
 </span>
</form>
</body>
</html>
