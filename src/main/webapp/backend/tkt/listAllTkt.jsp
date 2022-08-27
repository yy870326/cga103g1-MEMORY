<%@page import="com.tkt.model.TktVO"%>
<%@page import="com.tkt.model.TktService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tkt.model.*"%>
<%@ page import="java.time.LocalDate"%>

<%
TktService tktSvc = new TktService();
List<TktVO> tktList = tktSvc.getAll();
pageContext.setAttribute("tktList", tktList);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>票券列表 - Memory</title>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />
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


/* tkt css */

.list-h1 {
	margin-right: 2rem;
}

.add-btn {
	padding: 1rem 2.5rem 0.5rem 2.5rem;
}

</style>

</head>
<body>
	<!-- loading -->
	<%@ include file="/backend/loading.file"%>
	<!-- Header -->
	<%@ include file="/backend/header.file"%>
	<!-- sidebar -->
	<%@ include file="/backend/sidebar.file"%>

	<!-- main -->
	
	<div class="content-body">
		<div class="container-fluid">
			<div class="col-12 d-flex justify-content-between mb-5">
				<div class="d-flex">
					<h1 class="coup-list-h1 list-h1">票券列表</h1>
					<a href="<%=request.getContextPath()%>/backend/tkt/addTkt.jsp" class="btn btn-primary add-btn">新增</a>
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
							<th scope="col">票券編號</th>
							<th scope="col">票券名稱</th>
							<th scope="col">票券價格</th>
							<th scope="col">地點</th>
							<th scope="col">已賣出數量</th>
							<th scope="col">票券狀態</th>
							<th scope="col">票券種類</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="tktVO" items="${tktList}">
							<tr>
								<td>${tktVO.tkt_no}</td>
								<td>${tktVO.tkt_name}</td>
								<td>${tktVO.price}</td>
								<td>${tktVO.locate}</td>
								<td>${tktVO.sold_amount}</td>
								<td>
									<c:if test="${tktVO.tkt_status == 0 }" var="true">
										未上架
									</c:if>
									<c:if test="${tktVO.tkt_status == 1 }" var="true">
										已上架
									</c:if>
								</td>
								<td>
									<c:if test="${tktVO.kind == 0 }" var="true">
										景點門票
									</c:if>
									<c:if test="${tktVO.kind == 1 }" var="true">
										主題樂園
									</c:if>
									<c:if test="${tktVO.kind == 2 }" var="true">
										博物館展覽
									</c:if>
								</td>
								
								
								<td>
									<!-- 之後看能不能用 boostrap modal 跳彈跳視窗出來修改資料 -->
									<!-- <button type="button" class="btn btn-warning"
										 data-toggle="modal" data-target="#updateCoupModal">修改</button> -->

									<form method="post" action="<%=request.getContextPath()%>/tkt/updateTkt.do">
										<input type="submit" class="btn btn-warning" value="修改"> 
										<input type="hidden" name="tkt_no" value="${tktVO.tkt_no}">
										<input type="hidden" name="action" value="getOneUpdate">
									</form>
									
								</td>


							</tr>
						</c:forEach>

					</tbody>
				</table>
				
				<nav aria-label="Page navigation example">
 		 		<ul class="pagination justify-content-center">
    				<li class="page-item disabled">
      					<a class="page-link">第一頁</a>
    				</li>
    				<li class="page-item"><a class="page-link" href="#">1</a></li>
    				<li class="page-item">
      					<a class="page-link" href="#">最後一頁</a>
    				</li>
  				</ul>
				</nav>

			</div>
		</div>
	</div>
	
	<%@ include file="/backend/commonJS.file"%>

</body>
</html>