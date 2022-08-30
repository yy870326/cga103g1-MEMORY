﻿<%@page import="com.auth_fun.model.AuthFunService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.auth.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="authFunSvc" class="com.auth_fun.model.AuthFunService" />

<%
// 計算各狀態有幾筆資料
AuthService authSvc = new AuthService();
AuthFunService authFunSve = new AuthFunService();
pageContext.setAttribute("authSvc", authSvc);
pageContext.setAttribute("authFunSvc", authFunSvc);

//第一次進來執行if裡面，list是getAll
// 不是第一次進來(點擊狀態分類從controller過來的)，table中就用forward過來的list
/* if (request.getAttribute("list") == null) {	 */
if (request.getAttribute("list") == null) {
	List<AuthVO> list = authSvc.getAllAuth();
	pageContext.setAttribute("list", list);
}
%>
<!DOCTYPE html>
<html>

<head>
<title>員工權限列表 - Memory</title>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />
<%@ include file="/backend/commonCSS.file"%>
<!-- CSS -->

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

/*css */
.list-h1 {
	margin-right: 2rem;
}

.add-btn {
	padding: 1rem 2.5rem 0.5rem 2.5rem;
}
</style>

</head>

<body>
	<%@ include file="/backend/loading.file"%>
	<!-- loading -->
	<%@ include file="/backend/header.file"%>
	<!-- Header -->
	<%@ include file="/backend/sidebar.file"%>
	<!-- sidebar -->



	<!-- 	內容寫在main-content這個div內    -->

	<div class="content-body">
		<table id="example4" class="display" style="min-width: 845px"></table>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div class="container-fluid">
			<div class="col-12 d-flex justify-content-between mb-5">
				<div class="d-flex">
					<h1 class="coup-list-h1 list-h1">權限列表</h1>
					<a href="<%=request.getContextPath()%>/backend/auth/addAuth.jsp"
						class="btn btn-primary add-btn">新增</a>


					<%-- <div class="card-tabs mt-3 mt-sm-0">
				<ul class="nav nav-tabs" role="tablist">
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/auth/addAuth.do?action=getAll">所有權限列表(${authSvc.getAllAuth().size()})</a>
					</li>
				</ul>
			</div> --%>
				</div>
				<div class="input-group search-area">
					<input type="text" class="form-control" placeholder="Search here">
					<span class="input-group-text"> <a href="javascript:void(0)">
							<i class="flaticon-381-search-2"></i>
					</a>
					</span>
				</div>
			</div>
			<div class="col-12">
				<table class="table fold-table">
					<thead class="thead-dark">
						<tr>
							<th>權限編號</th>
							<th>員工編號</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="authVO" items="${list}">
							<tr>
								<td>${authVO.fun_no}-[${authVO.authFunVO.fun_name}]</td>
								<td>${authVO.emp_no}-[${authVO.empVO.emp_name}]</td>
								<td>
									<%-- <form method="post" action="<%=request.getContextPath()%>/room/Room"> --%>
									<%-- <input type="hidden" name="rm_no"  value="${roomVO.rm_no}"> --%>
									<!-- <input type="hidden" name="action"	value="getOneForUpdate"> -->
									<!-- <button type="submit" class="btn btn-secondary btn-sm"><i class='bx bxs-pencil'></i>修改</button> -->
									<!-- </form> --> <!-- 								<a class="btn btn-secondary btn-sm" -->
									<%-- 								href="<%=request.getContextPath()%>/auth/updateAuth.do?emp_no=${authVO.emp_no}&action=getOneForUpdate">修改</a> --%>
									<a class="btn btn-secondary btn-sm"
									href="<%=request.getContextPath()%>/backend/auth/updateAuth.jsp?emp_no=${authVO.emp_no}&fun_no=${authVO.fun_no}">修改</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>


		<%@ include file="/backend/commonJS.file"%>
		<!-- JS -->
		<script>
			// 		header標題
			$("#pagename").text("MEMORY 後台管理");
		</script>
</body>
</html>