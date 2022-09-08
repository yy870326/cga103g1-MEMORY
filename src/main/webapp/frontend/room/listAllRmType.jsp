<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rm_type.model.*"%>
<%@ page import="com.rm_pic.model.*"%>

<jsp:useBean id="rmTypeSvc" scope="page" class="com.rm_type.model.RmTypeService" />
<jsp:useBean id="rmPicSvc" scope="page" class="com.rm_pic.model.RmPicService" />
<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<%
//如果session中沒有間數跟人數
if (session.getAttribute("people") == null) {
	pageContext.setAttribute("people", 2);
}
if (session.getAttribute("roomtotal") == null) {
	pageContext.setAttribute("roomtotal", 1);
}

// 日曆的日期，字串分割成start_date、end_date
if (session.getAttribute("rangedate") != null) {
	String rangedate = (String) session.getAttribute("rangedate");
	List<String> dateList = new LinkedList<String>();
	dateList = Arrays.asList(rangedate.split(" to "));
	String start_date = dateList.get(0);
	String end_date = dateList.get(1);
	pageContext.setAttribute("start_date", start_date);
	pageContext.setAttribute("end_date", end_date);
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>尋找下榻民宿</title>
<%@ include file="/frontend/commonCSS.file"%>
<style>
.banner-form {
	position: relative;
	z-index: 1;
	margin-top: -100px;
	background-color: #f7f6f2;
	padding: 25px 40px 22px;
	border-radius: 15px;
	width: 100%;
	margin-right: auto;
	box-shadow: 5px 5px 5px 0px rgb(102 102 102/ 20%); .
	testimonials-content >span { font-size : 20px;
	letter-spacing: 1px;
	margin-left: 25px;
	color: #996A4D;
}

p.act_instruction, p.type_info {
	overflow: hidden;
	display: -webkit-box;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
}

p.type_info {
	-webkit-line-clamp: 5;
}

.banner-form .form-group label>i {
	position: relative;
	top: 0;
	left: 0;
}

.banner-form .btn-primary {
	padding: 12px;
}

.main-nav {
	background-color: rgba(255, 255, 255, 0.8);
}

.no-js .owl-carousel, .owl-carousel.owl-loaded {
	z-index: 0;
}

.banner-form .form-group .form-control:focus {
	z-index: 2;
}

.align-items-center {
	-ms-flex-align: center !important;
	align-items: center !important;
}

.row {
	display: -ms-flexbox;
	display: flex;
	-ms-flex-wrap: wrap;
	flex-wrap: wrap;
	margin-right: -15px;
	margin-left: -15px;
}

.btn-primary1 {
	background-color: #5bc9e2;
	padding: 0 20px;
	border-radius: 2px 2px 2px 2px;
	-moz-border-radius: 2px 2px 2px 2px;
	-webkit-border-radius: 12px;
	border: none;
	display: inline-block;
	line-height: 42px;
}

.btn-primary1 {
	color: #fff;
	background-color: #5bc9e2;
	border-color: #ffcd22;
	margin: 20px 20px 10px 60px;
}

.owl-carousel, .owl-carousel .owl-item {
	-webkit-tap-highlight-color: transparent;
	position: relative
}

.owl-carousel {
	display: none;
	width: 100%;
	z-index: 1
}

.owl-carousel .owl-stage {
	position: relative;
	-ms-touch-action: pan-Y;
	touch-action: manipulation;
	-moz-backface-visibility: hidden
}

.owl-carousel .owl-stage:after {
	content: ".";
	display: block;
	clear: both;
	visibility: hidden;
	line-height: 0;
	height: 0
}

.owl-carousel .owl-stage-outer {
	position: relative;
	overflow: hidden;
	-webkit-transform: translate3d(0, 0, 0)
}

.owl-carousel .owl-item, .owl-carousel .owl-wrapper {
	-webkit-backface-visibility: hidden;
	-moz-backface-visibility: hidden;
	-ms-backface-visibility: hidden;
	-webkit-transform: translate3d(0, 0, 0);
	-moz-transform: translate3d(0, 0, 0);
	-ms-transform: translate3d(0, 0, 0)
}

.owl-carousel .owl-item {
	min-height: 1px;
	float: left;
	-webkit-backface-visibility: hidden;
	-webkit-touch-callout: none
}

.owl-carousel .owl-item img {
	display: block;
	width: 100%
}

.owl-carousel .owl-dots.disabled, .owl-carousel .owl-nav.disabled {
	display: none
}

.no-js .owl-carousel, .owl-carousel.owl-loaded {
	display: block
}

.owl-carousel .owl-dot, .owl-carousel .owl-nav .owl-next, .owl-carousel .owl-nav .owl-prev
	{
	cursor: pointer;
	-webkit-user-select: none;
	-khtml-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none
}

.owl-carousel .owl-nav button.owl-next, .owl-carousel .owl-nav button.owl-prev,
	.owl-carousel button.owl-dot {
	background: 0 0;
	color: inherit;
	border: none;
	padding: 0 !important;
	font: inherit
}

.owl-carousel.owl-loading {
	opacity: 0;
	display: block
}

.owl-carousel.owl-hidden {
	opacity: 0
}

.owl-carousel.owl-refresh .owl-item {
	visibility: hidden
}

.owl-carousel.owl-drag .owl-item {
	-ms-touch-action: pan-y;
	touch-action: pan-y;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none
}

.owl-carousel.owl-grab {
	cursor: move;
	cursor: grab
}

.owl-carousel.owl-rtl {
	direction: rtl
}

.owl-carousel.owl-rtl .owl-item {
	float: right
}

.owl-carousel .animated {
	animation-duration: 1s;
	animation-fill-mode: both
}

.owl-carousel .owl-animated-in {
	z-index: 0
}

.owl-carousel .owl-animated-out {
	z-index: 1
}

.owl-carousel .fadeOut {
	animation-name: fadeOut
}

@
keyframes fadeOut { 0%{
	opacity: 1
}

100
%
{
opacity
:
0
}
}
.owl-height {
	transition: height .5s ease-in-out
}

.owl-carousel .owl-item .owl-lazy {
	opacity: 0;
	transition: opacity .4s ease
}

.owl-carousel .owl-item .owl-lazy:not([src]), .owl-carousel .owl-item .owl-lazy[src^=""]
	{
	max-height: 0
}

.owl-carousel .owl-item img.owl-lazy {
	transform-style: preserve-3d
}

.owl-carousel .owl-video-wrapper {
	position: relative;
	height: 100%;
	background: #000
}

.owl-carousel .owl-video-play-icon {
	position: absolute;
	height: 80px;
	width: 80px;
	left: 50%;
	top: 50%;
	margin-left: -40px;
	margin-top: -40px;
	background: url(owl.video.play.png) no-repeat;
	cursor: pointer;
	z-index: 1;
	-webkit-backface-visibility: hidden;
	transition: transform .1s ease
}

.owl-carousel .owl-video-play-icon:hover {
	-ms-transform: scale(1.3, 1.3);
	transform: scale(1.3, 1.3)
}

.owl-carousel .owl-video-playing .owl-video-play-icon, .owl-carousel .owl-video-playing .owl-video-tn
	{
	display: none
}

.owl-carousel .owl-video-tn {
	opacity: 0;
	height: 100%;
	background-position: center center;
	background-repeat: no-repeat;
	background-size: contain;
	transition: opacity .4s ease
}

.owl-carousel .owl-video-frame {
	position: relative;
	z-index: 1;
	height: 100%;
	width: 100%
}
</style>
</head>
<body>
	<!-- header -->
	<%@ include file="/frontend/header.file"%>
	<%@ include file="/frontend/roomSidebar.file"%>


	<div class="banner-area">
		<div class="container">
			<div class="banner-content">
				<!-- 				<h1>Feliz 提供您愉快的住宿體驗</h1> -->
				<h1>開始您的旅程</h1>
			</div>
		</div>
	</div>

	<div class="banner-form-area">
		<div class="container">
			<div class="banner-form">
				<form method="post"
					action="<%=request.getContextPath()%>/RmRsv/RmRsv.do"
					id="getEnoughType">
					<div class="row align-items-center d-flex justify-content-between">
						<div class="col-lg-4 col-md-4">
							<div class="form-group">
								<label><i class='bx bx-calendar'></i> 入住期間</label>
								<div class="input-group">
									<input type="text" id="rangeDate" name="rangedate"
										placeholder="請選擇入住期間" class="form-control" data-input>
									<span class="input-group-addon"></span>
								</div>
<!-- 								<i class='bx bxs-chevron-down'></i> -->
							</div>
						</div>

						<!--  <i class='bx bxs-chevron-down'></i>	 -->
						<div class="col-lg-2 col-md-2">
							<div class="form-group">
								<label><i class='bx bx-user'></i> 入住人數</label> <select
									id="people" name="people" class="form-control" required>
									<option value="1" ${people == 1 ? 'selected' : '' }>1人</option>
									<option value="2" ${people == 2 ? 'selected' : '' }>2人</option>
									<option value="3" ${people == 3 ? 'selected' : '' }>3人</option>
									<option value="4" ${people == 4 ? 'selected' : '' }>4人</option>
								</select>
							</div>
						</div>
						<div class="mb-left">
							<div class="form-group">
								<label><i class='bx bx-home-alt'></i> 間數</label> <select
									id="roomtotal" name="roomtotal" class="form-control" required>
									<option value="1" ${roomtotal == 1 ? 'selected' : '' }>1間</option>
									<option value="2" ${roomtotal == 2 ? 'selected' : '' }>2間</option>
									<option value="3" ${roomtotal == 3 ? 'selected' : '' }>3間</option>
									<option value="4" ${roomtotal == 4 ? 'selected' : '' }>4間</option>
									<option value="5" ${roomtotal == 5 ? 'selected' : '' }>5間</option>
									<option value="6" ${roomtotal == 6 ? 'selected' : '' }>6間</option>
								</select>
							</div>
						</div>
						<div class="col-lg-2 col-md-2">
							<div class="form-group">
								<label><i class='bx bxs-location-plus'></i>地區</label> <select
									id="store_add" name="store_add" class="form-control" required>
									<option value="1" ${store_add == 1 ? 'selected' : '' }>台北</option>
									<option value="2" ${store_add == 2 ? 'selected' : '' }>台中</option>
									<option value="3" ${store_add == 3 ? 'selected' : '' }>台南</option>
									<option value="4" ${store_add == 4 ? 'selected' : '' }>花蓮</option>
									<option value="5" ${store_add == 5 ? 'selected' : '' }>台東</option>
								</select>
							</div>
						</div>
						<div class="col-lg-3 col-md-3 d-flex justify-content-end pt-70">
							<input type="hidden" name="action" value="getEnoughType">
							<button type="button" class="btn btn-primary col-lg-8"
								onclick="check();">查看可訂民宿</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="testimonials-area-one pt-100 pb-70 room-area-bg">
		<div class="container">
			<div class="section-title text-center">
				<h2 class="area-title">民宿介紹</h2>
				<hr>
				<!-- 					<h3 class="area-subtitle">恬靜舒適的居住空間房與細緻用心的服務，提供您卓越的住宿體驗。</h3> -->
			</div>
			<div class="testimonials-slider-two owl-carousel owl-theme pt-45">
				<c:forEach var="rmTypeVO" items="${rmTypeSvc.getAllRm()}">
					<div class="testimonials-style">
						<div class="row align-items-center">
							<div class="col-lg-7">
								<div class="testimonials-img">
									<c:choose>
										<c:when
											test="${rmPicSvc.getAllByType(rmTypeVO.rm_type_no).size() > 0}">
											<img
												src="<%=request.getContextPath()%>/rmPic/rmPic.do?rm_type_no=${rmTypeVO.rm_type_no}&action=showFirstImages">
										</c:when>
										<c:otherwise>
											<img
												src="<%=request.getContextPath()%>/frontend/assets/images/hotels/noimages.png"
												class="no-img">
										</c:otherwise>
									</c:choose>
								</div>
							</div>

							<div class="col-lg-5">
								<div class="testimonials-content">
									<h2>${rmTypeVO.rm_name}</h2>
									<span>NT<fmt:formatNumber value="${rmTypeVO.rm_price}"
											pattern="$ ###,###" /></span> <span><i class='bx bxs-user'></i>
										${rmTypeVO.rm_people}人</span>
									<p class="rm_intro">${rmTypeVO.rm_intro}</p>
									<div class="d-flex justify-content-center">
										<a class="btn btn-primary1"
											href="<%=request.getContextPath()%>/rmtype/rmtype.do=${rmTypeVO.rm_type_no}&action=getOneForShow">瞭解更多</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!--  footer  -->
	<%@ include file="/frontend/footer.file" %>
	<%@ include file="/frontend/commonJS.file" %>
	<link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css" />
	<script
		src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.5/dist/sweetalert2.all.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
	rel='stylesheet'>
	<script>
		$("#rangeDate").flatpickr({
			mode : 'range',
			dateFormat : "Y-m-d",
			defaultDate : [ "${start_date}", "${end_date}" ],
			minDate : "today",
			maxDate : new Date().fp_incr(90),
			disable : [],
		});
		function check() {
			let duringStay = document.getElementById('rangeDate');

			if (duringStay.value.length != 24) {
				duringStay.focus();
				rangeDateIsNull();
				return false;
			} else {
				document.getElementById('getEnoughType').submit();
			}
		}
		function rangeDateIsNull() {
			swal.fire({
				icon : 'error',
				title : '請選擇 入住日 和 退房日',
				showConfirmButton : false,
				timer : 1000
			})
		}
	</script>
</body>
</html>