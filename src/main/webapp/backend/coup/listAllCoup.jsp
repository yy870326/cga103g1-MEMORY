<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="coupSvc" class="com.coup.model.CoupService" />

<%
List<CoupVO> coupList = coupSvc.getAll();
pageContext.setAttribute("coupList", coupList);
%>

<html>
<head>
<title>優惠券列表 - Memory</title>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />
	
<!-- bootstrap cdn 用了會跑版先註解 -->	
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
    integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous"> -->

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

/* coup css */

.coup-list-h1 {
	margin-right: 2rem;
}

.coup-add-btn {
	padding: 1rem 2.5rem 0.5rem 2.5rem;
}

</style>

</head>
<body>

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
					<h1 class="coup-list-h1">優惠券列表</h1>
					<a href="addCoup.jsp" class="btn btn-primary coup-add-btn">新增</a>
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
							<th scope="col">優惠券編號</th>
							<th scope="col">優惠券名稱</th>
							<th scope="col">優惠券介紹</th>
							<th scope="col">優惠券金額</th>
							<th scope="col">起始日期</th>
							<th scope="col">結束日期</th>
							<th scope="col">狀態</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="coupVO" items="${coupList}">
							<tr>
								<td>${coupVO.coup_no}</td>
								<td>${coupVO.coup_name}</td>
								<td>${coupVO.introduce}</td>
								<td>${coupVO.discount}</td>
								<td>${coupVO.startdate}</td>
								<td>${coupVO.enddate}</td>
								<td>${coupVO.status}</td>
								<td>
									<!-- 之後看能不能用 boostrap modal 跳彈跳視窗出來修改資料 -->
									<!-- <button type="button" class="btn btn-warning"
										 data-toggle="modal" data-target="#updateCoupModal">修改</button> -->

									<form method="post" action="<%=request.getContextPath()%>/coup/updateCoup.do">
										<input type="submit" class="btn btn-warning" value="修改"> 
										<input type="hidden" name="coup_no" value="${coupVO.coup_no}">
										<input type="hidden" name="action" value="coupUpdate">
									</form>
									
								</td>


							</tr>
						</c:forEach>

					</tbody>
				</table>

			</div>
		</div>
	</div>
	
	
	<!-- Update Modal -->
    <!-- <div class="modal fade" id="updateCoupModal" tabindex="-1" aria-labelledby="updateCoupModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <form action="#" method="post" class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="updateCoupModalLabel">coup_no 優惠券修改</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <label for="coupName">優惠券名稱</label>
                            <input type="text" class="form-control" id="coupName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="introduce">優惠券介紹</label>
                        <textarea class="form-control" id="introduce" rows="3"></textarea>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="startDate">開始日期</label>
                            <input type="date" class="form-control" id="startDate">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="endDate">結束日期</label>
                            <input type="date" class="form-control" id="endDate">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="discount">折扣金額</label>
                            <input type="number" class="form-control" id="discount">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="status">狀態</label>
                            <select class="custom-select custom-select-lg mb-3" id="status">
                                <option value="0" selected>未上架</option>
                                <option value="1">已上架</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">儲存</button>
                </div>
            </form>
        </div>
    </div> -->

	<%@ include file="/backend/commonJS.file"%>
	
	<!-- bootstrap cdn 用了會跑版先註解-->	
	<!-- <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script> -->
</body>
</html>