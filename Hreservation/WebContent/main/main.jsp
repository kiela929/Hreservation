<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<!-- 로딩 -->
   <link rel="stylesheet" type="text/css" title="default" href="css/main.css">
<!-- end of 로딩 -->
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<link rel="stylesheet" type="text/css" href="css/style3.css" />
<link
	href='http://fonts.googleapis.com/css?family=Alegreya+SC:700,400italic'
	rel='stylesheet' type='text/css' />


<link href='js/fullcalendar.css' rel='stylesheet' />
<link href='js/fullcalendar.print.css' rel='stylesheet' media='print' />
<!-- 메뉴바 -->

<link rel="stylesheet" href="cssmenu/styles.css">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<script src="cssmenu/script.js"></script>
<!-- /// -->

<!-- 로딩 -->
  <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
  <script type="text/javascript" src="js/custom.js"></script>
<!-- end of 로딩 -->

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
<title>메인페이지</title>

<body>
	<!-- 전체를 감싸는 태그 -->
	<div id="page-wrapper">
		<!-- 헤더 -->
		<header id="main-header">
			<!-- <hgroup > -->
			<div align="center">
				<a href="javascript:location='main.do'"><img alt="logo"
					src="images/logo.PNG" width="300" height="80"></a>
			</div>
			<!-- <h2 class="master-description">K H &nbsp;a c a d e m y</h2>
			<c:if test="true">
			<div align="right">
				<a href="javascript:location='template.action'"><font color="#D4D4D4"><h3>홈으로 나가기</h3></font></a>
			<br/>
			<font color=#FA1F5B>현재 관리자로 로그인 중입니다.
			</font>
	</div>
	</c:if> -->
			<!--  </hgroup>-->
		</header>
		<!-- 네비게이션 -->
		<nav id="main-navigation">

			<div id='cssmenu' style="padding-top: 30px;">
				<ul>
					<li class='active'><a href=javascript:location='main.do'>Home</a></li>
					<c:if test="${id==null}"><!-- 로그인 안된 상태 -->
					<li><a href=javascript:location='login.do'>Login</a></li>
					<li><a href=javascript:location='login.do'>Reservation</a></li>
					<li><a href=javascript:location='login.do'>MyPage</a></li>
					</c:if>
					
					<c:if test="${id!=null}"><!-- 로그인 된 상태 -->
					<li><a href=javascript:location='logout.do'>Logout</a></li>
				
					
					<c:if test="${grade eq 3}"> <!-- 회원이면 -->
					<li><a href=javascript:location='reserve.do'>Reservation</a></li>
					<li><a href='myReserveList.do'>MyPage</a></li>
					</c:if>
					
					<c:if test="${grade eq 1 }"> <!-- 관리자이면 -->
					<li><a href=javascript:location='doctorJoin.do'>enroll</a></li>
					<li><a href=javascript:location='chartMain.do'>Chart</a></li>
					</c:if>
					
					<c:if test="${grade eq 2 }"> <!-- 의사이면 -->
					<li><a href=javascript:location='scheduleList.do'>schedule</a></li>
					<li><a href=javascript:location='chartMain.do'>Chart</a></li>
					</c:if>
					
					
					</c:if>
				</ul>
			</div>

			<div class="pull-right">
				<!-- 메뉴 오른쪽 -->
			</div>
		</nav>
		<!-- 본문 -->
		<div id="content">
			<!-- 본문 좌측 영역-내용부분 -->

			<section id="main-section">
				<div class="pull-left">
					<!-- 		 <ul class="mh-menu">
				<li><a href="#"><span>Login</span> <span>로그인</span></a><img src="images/1.jpg" alt="image01"/></li>
				<li><a href="#"><span>Reservation</span> <span>진료예약</span></a><img src="images/2.jpg" alt="image02"/></li>
				<li><a href="#"><span>Calendar</span> <span>예약현황보기</span></a><img src="images/3.jpg" alt="image03"/></li>
				<li><a href="#"><span>Contact us</span> <span>오시는 길</span></a><img src="images/4.jpg" alt="image04"/></li>
			</ul>  -->
				</div>
			</section>
			<!-- 	본문 우측 영역 -->
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
		<!-- 밑 -->
		<footer id="main-footer">
		<!-- 	</br>
			</br>
			</br>
			</br>
			<div align="center">
				서울 햇빛병원 / 서울시 영등포구 249-33 / 전화번호 : 02-2680-6523~7 / 팩스 :
				02-2680-6531<br /> 경기 햇빛병원 / 경기도 광명시 철산로 56 / 전화번호 : 02-2680-6820 /
				팩스 : 02-2680-6826</br>
				</br>
				</br>
			</div> -->
		</footer>
	</div>
</body>

