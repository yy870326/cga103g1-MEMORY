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
List<RmOrderVO> orderlist = rmOderSvc.getAllRmOrder();
pageContext.setAttribute("orderlist", orderlist);
%>
<!DOCTYPE html>
<html>

	<head>
	<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />
		<%@ include file="/backend/commonCSS.file" %> <!-- CSS -->
	</head>

	<body>
		<%@ include file="/backend/loading.file" %> <!-- loading -->
		<%@ include file="/backend/header.file" %> <!-- Header -->
		<%@ include file="/backend/sidebar.file" %> <!-- sidebar -->

		

<!-- 	內容寫在main-content這個div內    -->
<div class="main-content card card-body table-responsive">
			<table id="example4" class="display" style="min-width: 845px">
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
				</c:forEach>
			</tbody>
			</table>
		</div>

		<%@ include file="/backend/commonJS.file" %> <!-- JS -->
		<script>
// 		header標題
			$("#pagename").text("MEMORY 後台管理");
		</script>
	</body>
</html>