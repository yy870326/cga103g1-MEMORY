<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.auth_fun.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="authFunSvc" class="com.auth_fun.model.AuthFunService" />

<%
// 計算各狀態有幾筆資料
// pageContext.setAttribute("authFunSvc", authFunSvc);
/* AuthFunService authFunSvc = new AuthFunService(); */
List<AuthFunVO> list = authFunSvc.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>

<head>
<title>所有權限列表 - Memory</title>
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
		<div class="container-fluid">
			<div class="col-12 d-flex justify-content-between mb-5">
				<div class="d-flex">
					<h1 class="coup-list-h1 list-h1">權限列表</h1>
					<a
						href="<%=request.getContextPath()%>/backend/authfun/addAuthFun.jsp"
						class="btn btn-primary add-btn">新增</a>
				</div>
				<div class="input-group search-area">
					<input type="text" class="form-control" placeholder="Search here">
					<span class="input-group-text"> <a href="javascript:void(0)">
							<i class="flaticon-381-search-2"></i>
					</a>
					</span>
				</div>
			</div>
			<%-- <h1 class="coup-lis list-h1">權限列表</h1>
					<a href="<%=request.getContextPath()%>/backend/authfun/addAuthFun.jsp" class="btn btn-primary add-btn">新增</a>
				</div>
			<table id="example4" class="display" style="min-width: 845px"> --%>
			<div class="col-12">
				<table class="table fold-table">
					<thead class="thead-dark">
						<tr>
							<th>權限編號</th>
							<th>權限名稱</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="authFunVO" items="${list}">
							<tr class="view">
								<td>${authFunVO.fun_no}</td>
								<td>${authFunVO.fun_name}<%-- -[${authFunVO.empVO.emp_name}] --%></td>
						
						<td>
						
								<%-- <form method="post"
									action="<%=request.getContextPath()%>/authfun/updateAuthFun.do">
									<input type="submit" class="btn btn-warning" value="修改">
									<input type="hidden" name="authFun_no" value="${authFunVO.fun_no}">
									<input type="hidden" name="action" value="getOneUpdate">
								</form> --%>
						</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<%@ include file="/backend/commonJS.file"%>
			<!-- JS -->
			<script>
				// 		header標題
				$("#pagename").text("MEMORY 後台管理");
			</script>
</body>
</html>