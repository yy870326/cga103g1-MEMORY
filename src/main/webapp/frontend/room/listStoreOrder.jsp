<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rm_order.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="rmOderSvc" class="com.rm_order.model.RmOrderService" />

<%
// 計算各狀態有幾筆資料
// pageContext.setAttribute("rmOderSvc", rmOderSvc);
if (request.getAttribute("orderlist") == null) {
List<RmOrderVO> orderlist = rmOderSvc.getAllRmOrder();
pageContext.setAttribute("orderlist", orderlist);
}
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />
<%@ include file="/backend/commonCSS.file"%>
<!-- 基本CSS檔案 -->
<style>
table.fold-table tbody tr.view {
	cursor: pointer;
}

table.fold-table tbody tr.view:hover {
	box-shadow: 0 0.125rem 1rem rgb(0 0 0/ 19%);
}

table.fold-table tbody tr.view.open {
	background: #8FC2C2;
}

table.fold-table tbody tr.view.open td {
	color: white;
}

table.fold-table tbody tr.fold {
	display: none;
}

table.fold-table tbody tr.fold.open {
	display: table-row;
}

table {
	width: 90%;
}

table.fold-table>thead>tr>th {
	align: center;
	font-size: 1.125rem;
	text-transform: capitalize;
	font-weight: 600;
	padding: 1.25rem 0.9375rem;
}

thead {
	background: #F7F6F2;
}

td, div {
	font-size: 1rem;
	letter-spacing: 0.5px;
}

div.order-data>div {
	font-size: 1.1rem;
	padding: 1%;
}

table tbody tr td:nth-child(1), table tbody tr td:nth-child(2) {
	padding-left: 45px;
}

table.table-striped {
	width: 90%;
	margin: auto;
	table-layout: fixed;
}

table.table-striped tbody tr:last-child {
	min-width: 400px;
}

h4 {
	color: #996A4D;
}

table.table-striped i {
	font-size: 12px;
}

table.table-striped th, table.table-striped td {
	padding: 0.6rem 0.3rem;
}

table.table-striped tfoot td {
	font-weight: 600;
	font-size: 1.1rem;
}

.search-area {
	max-width: 20rem;
	margin-left: auto;
	margin-right: 3%;
}

.custom-dropdown {
	margin: 0 1%;
}

i.bxs-chevron-down {
	font-size: 18px;
	padding-left: 7px;
}

table i {
	padding: 5px;
}

.btn-sm, .btn-group-sm>.btn {
	font-size: 1rem !important;
	padding: 0.625rem 1.6rem;
}

.dropdown-menu {
	z-index: 3;
	min-width: 9rem;
	padding: .5rem 0;
	text-align: center;
	border-radius: .5rem;
}
</style>
</head>

<body>
	
	<!-- sidebar -->

	<div class="main-content">
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div class="d-flex mb-4 align-items-center flex-wrap">
			<div class="dropdown custom-dropdown">
				<button type="button" class="btn btn-sm btn-secondary"
					data-bs-toggle="dropdown">
					<c:choose>
						<c:when test="${store_no==0}">依房型篩選<i
								class='bx bxs-chevron-down'></i>
						</c:when>
						<c:when test="${store_no!=0}">依房型篩選<i
								class='bx bxs-chevron-down'></i>
						</c:when>
					</c:choose>
				</button>
				<div class="dropdown-menu dropdown-menu-end">
					<c:forEach var="RmTypeVO" items="${RmTypeSvc.allRmType}">
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/RmOrder?rm_type_no=${RmTypeVO.rm_type_no}&action=getAllByType">${RmTypeVO.rm_name}</a>
					</c:forEach>
				</div>
			</div>
			<div class="card-tabs mt-3 mt-sm-0">
				<ul class="nav nav-tabs" role="tablist">
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/RmOrder?action=getAllRmOrder">所有訂單
							(${rmOderSvc.getAllRmOrder().size()})</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/RmOrder?rm_order_status=1&action=getAllStatus">入住中
							(${rmOderSvc.getAllStatus(1).size()})</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/RmOrder?rm_order_status=2&action=getAllStatus">正常
							(${rmOderSvc.getAllStatus(2).size()})</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/RmOrder?rm_order_status=3&action=getAllStatus">已實現
							(${rmOderSvc.getAllStatus(3).size()})</a></li>
				</ul>
			</div>
			<div class="input-group search-area">
				<input type="text" class="form-control" placeholder="Search here">
				<span class="input-group-text"><a href="javascript:void(0)">
						<i class="flaticon-381-search-2"></i>
				</a></span>
			</div>
		</div>
		<div></div>
		<table class="table fold-table">
			<thead>
				<tr>
					<th>訂單編號</th>
					<th>會員編號</th>
					<th>廠商編號</th>
					<th>入住日期</th>
					<th>訂單總金額</th>
					<th>訂單狀態</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="rmOrderVO" items="${orderlist}">
					<tr class="view">
						<td>${rmOrderVO.rm_order_no}</td>
						<td>${rmOrderVO.mem_no}</td>
						<td>${rmOrderVO.store_no}</td>
						<td>${rmOrderVO.order_date}</td>
						<td>${rmOrderVO.rm_charge}</td>
						<td><c:choose>
								<c:when test="${rmOrderVO.rm_order_status==1}">
									<i class='bx bxs-circle' style='color: red'></i>入住中</c:when>
								<c:when test="${rmOrderVO.rm_order_status==2}">
									<i class='bx bxs-circle' style='color: green'></i>正常</c:when>
								<c:when test="${rmOrderVO.rm_order_status==3}">
									<i class='bx bxs-circle' style='color: #aaa'></i>已實現</c:when>
							</c:choose></td>
					</tr>
					<tr class="fold">
						<td colspan="8">
							<div class="row d-flex justify-content-around my-2">
								<div class="col-4 order-data">
									<h4>
										<i class='bx bxs-user-voice'></i> 訂購人資料
									</h4>
									<div>付款人：</div>
									<div>評價：${rmOrderVO.rm_review}</div>
									<div>Email:</div>
								</div>
								<div class="col-4 order-data">
									<h4>
										<i class='bx bx-credit-card'></i> 付款資料
									</h4>
									<%-- 									<div>訂單成立日期： <fmt:formatDate value="${RmOrderVO.ord_date}" pattern="yyyy-MM-dd HH:mm:ss" /></div> --%>
									<div>訂單成立日期：${rmOrderVO.order_date}</div>
									<div>信用卡號碼：</div>
									<div>備註：</div>
								</div>
							</div>
							<table class="table table-striped">
								<thead>
									<tr>
										<th>訂單編號</th>
										<th>明細編號</th>
										<th>住房日期</th>
										<th>退房日期</th>
										<th>房型</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="detailVO"
										items="${detailSvc.getAllByOrdno(orderVO.ord_no)}">
										<tr>
											<td>${detailVO.ord_no}</td>
											<td>${detailVO.detail_no}</td>
											<td>${detailVO.checkin_date}</td>
											<td>${detailVO.checkout_date}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@ include file="/backend/commonJS.file"%>
	<!-- 放置基本JS檔案 -->
	<script>
		$(document).ready(
				function() {
					$("#pagename").text("訂單管理");
					$(".fold-table tr.view").on(
							"click",
							function() {
								$(this).toggleClass("open").next(".fold")
										.toggleClass("open");
							});
					$("li.nav-item:eq(${rm_order_status})").children()
							.addClass("nav-link active");
				});
	</script>
</body>
</html>