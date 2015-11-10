<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<!-- �ε� -->
   <link rel="stylesheet" type="text/css" title="default" href="css/main.css">
<!-- end of �ε� -->
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<link rel="stylesheet" type="text/css" href="css/style3.css" />
<link
	href='http://fonts.googleapis.com/css?family=Alegreya+SC:700,400italic'
	rel='stylesheet' type='text/css' />


<link href='js/fullcalendar.css' rel='stylesheet' />
<link href='js/fullcalendar.print.css' rel='stylesheet' media='print' />
<!-- �޴��� -->

<link rel="stylesheet" href="cssmenu/styles.css">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<script src="cssmenu/script.js"></script>
<!-- /// -->

<!-- �ε� -->
  <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
  <script type="text/javascript" src="js/custom.js"></script>
<!-- end of �ε� -->

<script src='js/moment.min.js'></script>
<script src='js/jquery.min.js'></script>
<script src='js/fullcalendar.min.js'></script>
<script>
	$(document).ready(function() {
		var x = ${eventString};
		$('#calendar').fullCalendar({
			lang: 'ko',
			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'month,basicWeek,basicDay'
			},
			defaultDate : '2015-05-19',
			editable : true,
			eventLimit : true, // allow "more" link when too many events
			events : x,
			  eventDrop: function(event, delta, revertFunc) {

			        alert(event.title + " was dropped on " + event.start.format());

			        if (!confirm("Are you sure about this change?")) {
			            revertFunc();
			        }

			    }
			
			
			
			
		});

	});
</script>

<style>
.pull-left {
	float: left;
}

.pull-right {
	float: right;
}
</style>
<style>
#content {
	overflow: hidden;
}

#main-aside {
	width: auto;
	height: 800px;
}
</style>
<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 900px;
	margin: 45 auto;
}
</style>


</head>
<title>����������</title>

<body>
	<!-- ��ü�� ���δ� �±� -->
	<div id="page-wrapper">
		<!-- ��� -->
		<header id="main-header">
			<!-- <hgroup > -->
			<div align="center">
				<a href="javascript:location='main.do'"><img alt="logo"
					src="images/logo.PNG" width="300" height="80"></a>
			</div>
			<!-- <h2 class="master-description">K H &nbsp;a c a d e m y</h2>
			<c:if test="true">
			<div align="right">
				<a href="javascript:location='template.action'"><font color="#D4D4D4"><h3>Ȩ���� ������</h3></font></a>
			<br/>
			<font color=#FA1F5B>���� �����ڷ� �α��� ���Դϴ�.
			</font>
	</div>
	</c:if> -->
			<!--  </hgroup>-->
		</header>
		<!-- �׺���̼� -->
		<nav id="main-navigation">

			<div id='cssmenu' style="padding-top: 30px;">
				<ul>
					<li class='active'><a href=javascript:location='main.do'>Home</a></li>
					<c:if test="${id==null}"><!-- �α��� �ȵ� ���� -->
					<li><a href=javascript:location='login.do'>Login</a></li>
					<li><a href=javascript:location='login.do'>Reservation</a></li>
					<li><a href=javascript:location='login.do'>MyPage</a></li>
					</c:if>
					
					<c:if test="${id!=null}"><!-- �α��� �� ���� -->
					<li><a href=javascript:location='logout.do'>Logout</a></li>
				
					
					<c:if test="${grade eq 3}"> <!-- ȸ���̸� -->
					<li><a href=javascript:location='reserve.do'>Reservation</a></li>
					<li><a href='myReserveList.do'>MyPage</a></li>
					</c:if>
					
					<c:if test="${grade eq 1 }"> <!-- �������̸� -->
					<li><a href=javascript:location='doctorJoin.do'>enroll</a></li>
					<li><a href=javascript:location='chartMain.do'>Chart</a></li>
					</c:if>
					
					<c:if test="${grade eq 2 }"> <!-- �ǻ��̸� -->
					<li><a href=javascript:location='scheduleList.do'>schedule</a></li>
					<li><a href=javascript:location='chartMain.do'>Chart</a></li>
					</c:if>
					
					
					</c:if>
				</ul>
			</div>

			<div class="pull-right">
				<!-- �޴� ������ -->
			</div>
		</nav>
		<!-- ���� -->
		<div id="content">
			<!-- ���� ���� ����-����κ� -->

			<section id="main-section">
				<div class="pull-left">
					<!-- 		 <ul class="mh-menu">
				<li><a href="#"><span>Login</span> <span>�α���</span></a><img src="images/1.jpg" alt="image01"/></li>
				<li><a href="#"><span>Reservation</span> <span>���Ό��</span></a><img src="images/2.jpg" alt="image02"/></li>
				<li><a href="#"><span>Calendar</span> <span>������Ȳ����</span></a><img src="images/3.jpg" alt="image03"/></li>
				<li><a href="#"><span>Contact us</span> <span>���ô� ��</span></a><img src="images/4.jpg" alt="image04"/></li>
			</ul>  -->
				</div>
			</section>
			<!-- 	���� ���� ���� -->
			<aside id="main-aside" style="padding: 30 30 30 30">
				<c:choose>
					<c:when test="${BODY_PATH==null }">
						<div id='calendar'></div>
					</c:when>

					<c:otherwise>
						<jsp:include page="${BODY_PATH }" />
					</c:otherwise>
				</c:choose>

			</aside>
		</div>
		<!-- �� -->
		<footer id="main-footer">
		<!-- 	</br>
			</br>
			</br>
			</br>
			<div align="center">
				���� �޺����� / ����� �������� 249-33 / ��ȭ��ȣ : 02-2680-6523~7 / �ѽ� :
				02-2680-6531<br /> ��� �޺����� / ��⵵ ����� ö��� 56 / ��ȭ��ȣ : 02-2680-6820 /
				�ѽ� : 02-2680-6826</br>
				</br>
				</br>
			</div> -->
		</footer>
	</div>
</body>

