<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="coupSvc" class="com.coup.model.CoupService" />

 <%
System.out.println("add -s"); // 印出來看
CoupVO coupVO = (CoupVO) request.getAttribute("coupVO"); // 
System.out.println(coupVO); // 印出來看
System.out.println("add -e"); // 印出來看
%> 

<html>
<head>
<title>新增優惠券 - Memory</title>

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

.input-mr {
	margin-right: 1rem;
}

.input-mb {
	margin-bottom: 1rem;
}

.error-list-mb{
	margin-bottom: 0.6rem;
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
				<h1 class="coup-list-h1">新增優惠券</h1>
			</div>
			
			<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red" class="error-list-mb">${message}</li>
					</c:forEach>
				</ul>
		</c:if>


			<div class="col-12">
				<form action="<%=request.getContextPath()%>/coup/addCoup.do" method="post" class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title">新增優惠券</h3>
					</div>
					<div class="modal-body">
						<div class="form-row input-mb d-flex">
							<div class="form-group col-md-5 input-mr">
								<label for="coupName">優惠券名稱</label> 
								<input type="text" class="form-control" id="coupName" name="coup_name" value="${coupVO.coup_name}">
							</div>
							<div class="form-group col-md-4 input-mr">
								<label for="discount">折扣金額</label> 
								<input type="number" class="form-control" id="discount" name="discount" value="${coupVO.discount}">
							</div>
							<div class="form-group col-md-2">
								<label for="status">狀態</label> 
								<input type="number" class="form-control" id="status" name="status" value="${coupVO.status}">
								
								<!-- <p>狀態</p>
								<select class="custom-select custom-select-lg mb-3" id="status">
									<option value="0" selected>未上架</option>
									<option value="1">已上架</option>
								</select> -->
								
							<%-- <td>
								<select size="1" name="OrderState" required>
									<option value="0" <c:if test="${coupVO.status == 0}"><c:out value="selected"></c:out></c:if>>未上架</option>
									<option value="1" <c:if test="${coupVO.status == 1}"><c:out value="selected"></c:out></c:if>>已上架</option>
								</select>
							</td> --%>
								
							</div>
						</div>
						<div class="form-group col-md-12 input-mb">
							<label for="introduce">優惠券介紹</label>
							<textarea class="form-control" id="introduce" name="introduce" rows="3">${coupVO.introduce}</textarea>
						</div>
						<div class="form-row d-flex input-mb">
							<div class="form-group col-md-5 input-mr">
								<label for="startDate">開始日期</label> 
								<input type="date" class="form-control" id="startDate" name="startdate" value="${coupVO.startdate}">
							</div>
							<div class="form-group col-md-5">
								<label for="endDate">結束日期</label> 
								<input type="date" class="form-control" id="endDate" name="enddate" value="${coupVO.enddate}">
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<!-- hidden input -->
						<input type="hidden" name="action" value="coupAdd">
						
						<a href="<%=request.getContextPath()%>/backend/coup/listAllCoup.jsp" class="btn btn-secondary" data-dismiss="modal">取消</a>
						<input type="submit" class="btn btn-primary" value="儲存">
					</div>
				</form>

			</div>
		</div>
	</div>

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