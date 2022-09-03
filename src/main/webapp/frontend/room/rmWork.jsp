<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rm_type.model.*"%>
<%@ page import="com.rm_order.model.*"%>
<%@ page import="com.rm_order_list.model.*"%>
<%@ page import="java.time.LocalDate"%>

<jsp:useBean id="rmTypeSvc" class="com.rm_type.model.RmTypeService" />
<jsp:useBean id="rmOrderSvc" class="com.rm_order.model.RmOrderService" />
<jsp:useBean id="rmOrderListSvc"
	class="com.rm_order_list.model.RmOrderListService" />
<jsp:useBean id="memSvc" class="com.mem.model.MemService" />

<%
if (request.getAttribute("orderlist") == null) {
	Integer store_no = 1;
	List<RmOrderVO> orderlist = rmOrderSvc.getAllByStore(store_no);
	pageContext.setAttribute("orderlist", orderlist);
}
pageContext.setAttribute("store_no", 1);
%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />



<%@ include file="/frontend/commonCSS.file"%>
<!-- 基本JS檔案 -->
<script
	src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<style>
.bxs {
	font-size: 50px;
	color: #aaa;
}

.modal-content {
	background: #f5f3ee;
}

h3.card-title {
	color: #996A4D;
}

.to-jump {
	padding: 15px 30px;
	background: #D1E6E6;
}

.to-jump:last-child {
	padding: 10px 30px 5px 30px;
	cursor: default;
}

.to-jump:last-child h4 {
	margin-bottom: 0px;
}

.to-jump h3 {
	font-size: 30px;
	font-weight: 600;
	color: #996A4D;
	padding-top: 10px;
}

.to-jump p {
	font-size: 14px;
	color: #996A4D;
	padding: 0;
	margin: 0;
}

div.main-content {
	margin-top: 50px;
}

div.main-content>h3 {
	/* 			margin: 40px 10px 0px 10px; */
	font-size: 26px;
}

a {
	cursor: pointer;
}

table.dataTable tbody td {
	padding: 5px;
	font-size: 20px;
	font-weight: 500;
	border-bottom: 0;
	letter-spacing: 0.5px;
}

h3.checkInTitle {
	color: #fff;
	background: #a2bfb9;
	padding: 10px;
	font-weight: 600;
	position: relative;
	bottom: -55px;
}

table#checkInTable tr.odd {
	background: #edf2f1 !important;
}

h3.checkOutTitle {
	color: #fff;
	background: #C7B8A1;
	padding: 10px;
	font-weight: 600;
	position: relative;
	bottom: -55px;
}

table#checkOutTable tr.odd {
	background: #f5f5f0 !important;
}

h3.stayTitle {
	color: #fff;
	background: #c0c4d3;
	padding: 10px;
	font-weight: 600;
	position: relative;
	bottom: -55px;
}

table#stayTable tr.odd {
	background: #f5f6f8 !important;
}

td.sorting_1 {
	border-bottom: #ddd !important;
	padding-left: 35px !important;
}

.dataTables_filter>label {
	font-size: 20px;
	color: #fff;
}

.hidden {
	display: none;
}

.card {
	margin-bottom: 0;
}
</style>


</head>
<body>
	<%@ include file="/frontend/header.file"%>
	<!-- sidebar -->

	<div class="main-content">
		<div class="row d-flex justify-content-around">
			<div class="col-xl-2 card to-jump">
				<a href="#checkInTable">
					<div class="text-center row">
						<div class="col">
							<h4>今日待入住訂單</h4>
							<h3>${rmOrderListSvc.getCheckInByStore(store_no).size()}</h3>
						</div>

						<div
							class="col-4 bxs d-flex justify-content-center align-items-center">
							<i class='bx bx-log-in'></i>
						</div>

					</div>
				</a>
			</div>

			<div class="col-xl-2 card to-jump">
				<a href="#checkOutTable">
					<div class="text-center row">

						<div class="col">
							<h4>今日待退房</h4>
							<h3>${rmOrderListSvc.getCheckOutByStore(store_no).size()}</h3>
						</div>

						<div
							class="col-4 bxs d-flex justify-content-center align-items-center">
							<i class='bx bx-log-out'></i>
						</div>

					</div>
				</a>
			</div>

			<div class="col-xl-2 card to-jump">
				<a href="#stayTable">
					<div class="text-center row">
						<div class="col">
							<h4>入住中房間</h4>
							<h3>${rmOrderListSvc.getStayByStore(store_no).size()}</h3>
						</div>

						<div
							class="col-4 bxs d-flex justify-content-center align-items-center">
							<i class='bx bx-home-smile'></i>
						</div>

					</div>
				</a>
			</div>

			<div class="col-xl-2 card to-jump">
				<div class="text-center row">

					<div class="col">
						<h4>房間使用率</h4>
						<p>預計入住房間 / 可使用房間</p>
						<h3></h3>
					</div>

				</div>
			</div>
		</div>

		<h3 class="checkInTitle text-center">
			<i class='bx bx-log-in-circle'></i> 今日待入住
			<h3>
				<div class="main-content">
					<div class="align-items-center flex-wrap">
						<div class="card-tabs mt-3 mt-sm-0">
							<table class="table fold-table">
								<thead>
									<tr>
										<th>訂單編號</th>
										<th>房型名稱</th>
										<th>會員資料</th>
										<th class="hidden">會員帳號</th>
										<th>會員電話</th>
										<th>入住日</th>
										<th>退房日</th>
										<th>入住人姓名</th>
										<th></th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="checkInVO"
										items="${rmOrderListSvc.getCheckInByStore(store_no)}">
										<tr>
											<td>${checkInVO.rm_order_no}</td>
											<td>${rmTypeSvc.getOneRm(checkInVO.rm_type_no).rm_name}</td>
											<td>${memSvc.getOne(rmOrderSvc.getOne(checkInVO.rm_order_no).mem_no).mem_name}</td>
											<td class="hidden">${rmOrderSvc.getOne(checkInVO.rm_order_no).mem_no}</td>
											<td>${memSvc.getOne(rmOrderSvc.getOne(checkInVO.rm_order_no).mem_no).mem_mobile}</td>
											<td>${checkInVO.arrival_date}</td>
											<td>${checkInVO.departure_date}</td>
											<td>${checkInVO.rm_check_in}</td>
											<td><div>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/RmOrder">
														<input type="submit" class="btn btn-primary" value="訂單資訊">
														<input type="hidden" name="rm_order_no"
															value="${checkInVO.rm_order_no}"> <input
															type="hidden" name="store_no" value="${store_no}">
														<input type="hidden" name="action" value="getOne">
													</FORM>
												</div></td>
											<td><div>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/RmOrder">
														<input type="submit" class="btn btn-primary"
															value="CHECK IN"> <input type="hidden"
															name="rm_order_no" value="${checkInVO.rm_order_no}">
														<input type="hidden" name="action" value="">
													</FORM>
												</div></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>


						</div>
					</div>
				</div>
				<h3 class="checkOutTitle text-center">
					<i class='bx bx-log-in-circle'></i> 今日待退房
					<h3>
						<div class="main-content">
							<div class="align-items-center flex-wrap">
								<div class="card-tabs mt-3 mt-sm-0">
									<table id="checkOutTable" class="display table fold-table">
										<thead>
											<tr>
												<th>訂單編號</th>
												<th>房型名稱</th>
												<th>會員資料</th>
												<th class="hidden">會員帳號</th>
												<th>會員電話</th>
												<th>入住日</th>
												<th>退房日</th>
												<th>入住人姓名</th>
												<th></th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="checkOutVO"
												items="${rmOrderListSvc.getCheckOutByStore(store_no)}">
												<tr>
													<td>${checkOutVO.rm_order_no}</td>
													<td>${rmTypeSvc.getOneRm(checkOutVO.rm_type_no).rm_name}</td>
													<td>${memSvc.getOne(rmOrderSvc.getOne(checkOutVO.rm_order_no).mem_no).mem_name}</td>
													<td class="hidden">${rmOrderSvc.getOne(checkOutVO.rm_order_no).mem_no}</td>
													<td>${memSvc.getOne(rmOrderSvc.getOne(checkOutVO.rm_order_no).mem_no).mem_mobile}</td>
													<td>${checkOutVO.arrival_date}</td>
													<td>${checkOutVO.departure_date}</td>
													<td>${checkOutVO.rm_check_in}</td>
													<td><div>
															<FORM METHOD="post"
																ACTION="<%=request.getContextPath()%>/RmOrder">
																<input type="submit" class="btn btn-primary"
																	value="訂單資訊"> <input type="hidden"
																	name="rm_order_no" value="${checkOutVO.rm_order_no}">
																<input type="hidden" name="store_no" value="${store_no}">
																<input type="hidden" name="action" value="getOne">
															</FORM>
														</div></td>
													<td><div>
															<FORM METHOD="post"
																ACTION="<%=request.getContextPath()%>/RmOrder">
																<input type="submit" class="btn btn-primary"
																	value="CHECK OUT"> <input type="hidden"
																	name="rm_order_no" value="${checkOutVO.rm_order_no}">
																<input type="hidden" name="action" value="">
															</FORM>
														</div></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>

								</div>
							</div>
						</div>
						<h3 class="stayTitle text-center">
							<i class='bx bx-log-in-circle'></i> 入住中房間
							<h3>
								<div class="main-content">
									<div class="align-items-center flex-wrap">
										<div class="card-tabs mt-3 mt-sm-0">
											<table id="stayTable" class="display table fold-table">
												<thead>
													<tr>
														<th>訂單編號</th>
														<th>房型名稱</th>
														<th>會員資料</th>
														<th class="hidden">會員帳號</th>
														<th>會員電話</th>
														<th>入住日</th>
														<th>退房日</th>
														<th>入住人姓名</th>
														<th></th>
														<th></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="stayVO"
														items="${rmOrderListSvc.getStayByStore(store_no)}">
														<tr>
															<td>${stayVO.rm_order_no}</td>
															<td>${rmTypeSvc.getOneRm(stayVO.rm_type_no).rm_name}</td>
															<td>${memSvc.getOne(rmOrderSvc.getOne(stayVO.rm_order_no).mem_no).mem_name}</td>
															<td class="hidden">${rmOrderSvc.getOne(stayVO.rm_order_no).mem_no}</td>
															<td>${memSvc.getOne(rmOrderSvc.getOne(stayVO.rm_order_no).mem_no).mem_mobile}</td>
															<td>${stayVO.arrival_date}</td>
															<td>${stayVO.departure_date}</td>
															<td>${stayVO.rm_check_in}</td>
															<td><div>
																	<FORM METHOD="post"
																		ACTION="<%=request.getContextPath()%>/RmOrder">
																		<input type="submit" class="btn btn-primary"
																			value="訂單資訊"> <input type="hidden"
																			name="rm_order_no" value="${stayVO.rm_order_no}">
																		<input type="hidden" name="store_no"
																			value="${store_no}"> <input type="hidden"
																			name="action" value="getOne">
																	</FORM>
																</div></td>
															<td><div>
																	<FORM METHOD="post"
																		ACTION="<%=request.getContextPath()%>/RmOrder">
																		<input type="submit" class="btn btn-primary"
																			value="CHECK OUT"> <input type="hidden"
																			name="rm_order_no" value="${stayVO.rm_order_no}">
																		<input type="hidden" name="action" value="">
																	</FORM>
																</div></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>

										</div>
									</div>
								</div>

								<%@ include file="/frontend/footer.file"%>
								<%@ include file="/backend/commonJS.file"%>
								<!-- 放置基本JS檔案 -->
								<script>
									$(document)
											.ready(
													function() {

														$(".fold-table tr.view")
																.on(
																		"click",
																		function() {
																			$(
																					this)
																					.toggleClass(
																							"open")
																					.next(
																							".fold")
																					.toggleClass(
																							"open");
																		});
													});
								</script>
</body>
</html>