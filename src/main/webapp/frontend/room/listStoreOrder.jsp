<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.rm_type.model.*"%>
<%@ page import="com.rm_order.model.*"%>
<%@ page import="com.rm_order_list.model.*"%>
<%@ page import="java.time.LocalDate"%>

<jsp:useBean id="memSvc" class="com.mem.model.MemService" />
<jsp:useBean id="storeSvc" class="com.store.model.StoreService" />
<jsp:useBean id="rmTypeSvc" class="com.rm_type.model.RmTypeService" />
<jsp:useBean id="rmOrderSvc" class="com.rm_order.model.RmOrderService" />
<jsp:useBean id="rmOrderListSvc"
	class="com.rm_order_list.model.RmOrderListService" />

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
<!-- 基本CSS檔案 -->
<%@ include file="/backend/commonCSS.file"%>

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

div.main-content {
	margin-top: 30px;
	margin-left: 0px;
	margin-bottom: 300px;
}

.mt-3, .my-3 {
	margin-top: 0rem !important;
}
</style>


<!-- CSS -->
<%@ include file="/frontend/commonCSS.file"%>
</head>

<body>
	<%@ include file="/frontend/header.file"%>

	<div class="main-content">
		<div class="d-flex mb-4 align-items-center flex-wrap">
			<div class="card-tabs mt-3 mt-sm-0">
				<ul class="nav nav-tabs" role="tablist">
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/RmOrder?store_no=${store_no}&action=getAllRmOrder">所有訂單
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/RmOrder?store_no=${store_no}&rm_order_status=0&action=getStoreStatus">入住中
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/RmOrder?store_no=${store_no}&rm_order_status=1&action=getStoreStatus">正常
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/RmOrder?store_no=${store_no}&rm_order_status=2&action=getStoreStatus">已實現
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/RmOrder?store_no=${store_no}&rm_order_status=2&action=getStoreStatus">已取消
					</a></li>
					<li class="nav-item">
						<!-- Search -->
						<form METHOD="post" ACTION="<%=request.getContextPath()%>/RmOrder">
							<div class="input-group search-area" style="margin-left: 850px;">
								<input class="form-control" type="text" name="rm_order_no"
									placeholder="請輸入訂單編號" /> <input type="submit"
									class="btn btn-grad border-radius-left-0 mb-0" value="Search">
								<input type="hidden" name="store_no" value="${store_no}">
								<input type="hidden" name="action" value="getOne">
							</div>
						</form>
					</li>
				</ul>
			</div>
		</div>

		<table class="table fold-table">
			<thead>
				<tr>
					<th>訂單編號</th>
					<th>會員編號</th>
					<th>訂單日期</th>
					<th>入住日期</th>
					<th>退房日期</th>	
					<th>訂單狀態</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="rmOrderVO" items="${orderlist}">
					<tr class="view">
						<td>${rmOrderVO.rm_order_no}</td>
						<td>${rmOrderVO.mem_no}</td>
						<td>${rmOrderVO.order_date}</td>
						<td>${rmOrderVO.start_date}</td>
						<td>${rmOrderVO.end_date}</td>	
						<td><c:choose>
								<c:when test="${rmOrderVO.rm_order_status==0}">
									<i class='bx bxs-circle' style='color: red'></i>入住中</c:when>
								<c:when test="${rmOrderVO.rm_order_status==1}">
									<i class='bx bxs-circle' style='color: green'></i>正常</c:when>
								<c:when
									test="${rmOrderVO.rm_order_status==2 && rmOrderVO.rm_charge!=0}">
									<i class='bx bxs-circle' style='color: #aaa'></i>已實現</c:when>
								<c:when
									test="${rmOrderVO.rm_order_status==2 && rmOrderVO.rm_charge==0}">
									<i class='bx bxs-circle' style='color: yellow'></i>已取消</c:when>
							</c:choose></td>
					</tr>
					<tr class="fold">
						<td colspan="8">

							<div class="row d-flex justify-content-around my-2">
								<div class="col-4 order-data">
									<h4>
										<i class='bx bxs-user-voice'></i> 訂購人資料
									</h4>
									<div>付款人：${memSvc.getOne(rmOrderVO.mem_no).mem_name}</div>
									<div>電話：${memSvc.getOne(rmOrderVO.mem_no).mem_mobile}</div>
									<div>Email:${memSvc.getOne(rmOrderVO.mem_no).mem_email}</div>
								</div>
								<div class="col-4 order-data">
									<h4>
										<i class='bx bx-credit-card'></i> 付款資料
									</h4>
									<div>訂單成立日期： ${rmOrderVO.order_date}</div>
									<div>信用卡號碼：${memSvc.getOne(rmOrderVO.mem_no).mem_card}</div>
								</div>
							</div>
							<table class="table table-striped">
								<thead>
									<tr>
										<th>房型</th>
										<th>單價</th>
										<th>間數</th>
										<th>實際住房日期</th>
										<th>實際退房日期</th>
										<th>入住人姓名</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="rmOrderListVO"
										items="${rmOrderListSvc.getAllByRmOrderNo(rmOrderVO.rm_order_no)}">
										<tr>
											<td><div>${rmTypeSvc.getOneRm(rmOrderListVO.rm_type_no).rm_name}</div></td>
											<td><div>${rmOrderListVO.rm_price}</div></td>
											<td><div>${rmOrderListVO.rm_amount}</div></td>
											<td><div>${rmOrderListVO.arrival_date}</div></td>
											<td><div>${rmOrderListVO.departure_date}</div></td>
											<td><div>${rmOrderListVO.rm_check_in}</div></td>
											<td><div></div></td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td>
										</td>
										<td>總金額 <fmt:formatNumber value="${rmOrderVO.rm_charge}"
												pattern="$###,###,###" /> <c:if
												test="${rmOrderVO.rm_order_status==2 && rmOrderVO.rm_charge==0}">
												<div>(已扣掉取消後退款金額)</div>
											</c:if>
										</td>
										<td><div>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/RmOrder">
													<input
														type="${rmOrderVO.rm_order_status==2 && rmOrderVO.rm_charge==0 ? 'hidden' : 'submit' }"
														class="btn btn-primary" value="取消訂單"> <input
														type="hidden" name="rm_order_no"
														value="${rmOrderVO.rm_order_no}"> <input
														type="hidden" name="action" value="cancel">
												</FORM>
											</div></td>
									</tr>
								</tfoot>
							</table>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<%@ include file="/frontend/footer.file"%>
	<%@ include file="/frontend/commonJS.file"%>
	<!-- 放置基本JS檔案 -->
	<%@ include file="/backend/commonJS.file"%>
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
					$("li.nav-item:eq(${ord_state+1})").children().addClass(
							"nav-link active");
				});
	</script>
</body>
</html>