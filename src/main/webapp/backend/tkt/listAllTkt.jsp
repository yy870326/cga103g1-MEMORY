<%@page import="com.tkt.model.TktVO"%>
<%@page import="com.tkt.model.TktService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tkt.model.*"%>
<%@ page import="com.tkt_img.model.*"%>
<%@ page import="java.time.LocalDate"%>

<%
TktService tktSvc = new TktService();
List<TktVO> list = tktSvc.getAll();
pageContext.setAttribute("list", list);
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
	padding: 1rem 1rem 0.5rem 2rem;
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
					
					<a href="<%=request.getContextPath()%>/backend/tkt/addTkt.jsp" class="btn btn-rounded btn-primary add-btn">
						<span class="btn-icon-start text-primary">
							 <i class="fa fa-plus color-primary"></i>
                        </span>
                        新增
                     </a>
				</div>
				<div class="input-group search-area">
					<input type="text" class="form-control" placeholder="Search here">
					<span class="input-group-text"> <i
						class="flaticon-381-search-2"></i>

					</span>
				</div>
			</div>

			<div class="col-12 table-responsive">
				<table class="table table-hover table-responsive-sm fold-table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">編號</th>
							<!-- <th scope="col"></th> -->
							<th scope="col">票券名稱</th>
							<th scope="col">票券價格</th>
							<th scope="col">地點</th>
							<th scope="col">已賣出數量</th>
							<th scope="col">剩餘數量</th>
							<th scope="col">票券種類</th>
							<th scope="col">票券狀態</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
					<%@ include file="/backend/tkt/pageIndex.file"%>
						<c:forEach var="tktVO" items="${list}"  begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td># ${tktVO.tkt_no}</td>
								<%-- <td><img src="${tktImgVO.tkt_img}"></td> --%>
								<td>${tktVO.tkt_name}</td>
								<td>NT$ ${tktVO.price}</td>
								<td>${tktVO.locate}</td>
								<td>${tktVO.sold_amount}</td>
								<td>${tktVO.original_amount - tktVO.sold_amount}</td>
								
								
								<td><c:if test="${tktVO.kind == 0 }" var="true">
										<span class="badge badge-secondary">景點門票</span>
									</c:if> <c:if test="${tktVO.kind == 1 }" var="true">
										<span class="badge dark badge-warning">主題樂園</span>
									</c:if> <c:if test="${tktVO.kind == 2 }" var="true">
										<span class="badge badge-info">博物館展覽</span>
									</c:if>
								</td>

								<td><c:if test="${tktVO.tkt_status == 0 }" var="true">
										<span class="badge badge-danger light">未上架</span>
									</c:if> <c:if test="${tktVO.tkt_status == 1 }" var="true">
										<span class="badge badge-primary light">已上架</span>
									</c:if>
								</td>

								<td>
									<!-- 之後看能不能用 boostrap modal 跳彈跳視窗出來修改資料 --> <!-- <button type="button" class="btn btn-warning"
										 data-toggle="modal" data-target="#updateCoupModal">修改</button> -->

									<form method="post"
										action="<%=request.getContextPath()%>/tkt/updateTkt.do">
										<button type="submit" class="fa fa-pencil-alt btn" value="">
											<!-- <i class="fa fa-pencil-alt"></i> -->
										</button>
										<input type="hidden" name="tkt_no" value="${tktVO.tkt_no}">
										<input type="hidden" name="action" value="getOneUpdate">
									</form>

								</td>


							</tr>
						</c:forEach>

					</tbody>
				</table>
				
				<%@ include file="/backend/tkt/pagination.file"%>
			
			<!-- ----------------------------------- -->
			<!-- test -->

	<!-- Button trigger modal -->
	<!-- <button type="button" class="btn btn-primary mb-2"
		data-bs-toggle="modal" data-bs-target="#exampleModalCenter">新增</button> -->
	<!-- Modal -->
	<%-- <div class="modal fade" id="exampleModalCenter">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">新增票券</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal">
					</button>
				</div>
				<div class="modal-body">
					<div class="form-row input-mb d-flex">
						<!-- 1 -->
						<div class="form-group col-md-5 input-mr">
							<label class="form-label" for="tkt_name">票券名稱</label> <input
								type="text" class="form-control" id="tkt_name" name="tkt_name"
								value="${tktVO.tkt_name}">
						</div>
						<div class="form-group col-md-4 input-mr">
							<label class="form-label" for="price">價格</label> <input
								type="number" class="form-control" id="price" name="price"
								value="${tktVO.price}">
						</div>
						<div class="form-group col-md-2">
							<label for="">狀態</label>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="tkt_status"
									id="statusOn" value="0" checked> <label
									class="form-check-label" for="statusOn"> 未上架 </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="tkt_status"
									id="statusOff" value="1"> <label
									class="form-check-label" for="statusOff"> 上架 </label>
							</div>
						</div>
					</div>
					<!-- 2 -->
					<div class="form-row d-flex input-mb">
						<div class="form-group col-md-3 input-mb input-mr">
							<label class="form-label" for="">原始數量</label> <input
								type="number" class="form-control" id="original_amount"
								name="original_amount" value="${tktVO.original_amount}">
						</div>



						<div class="form-group col-md-9 input-mb">
							<label class="form-label" for="locate">地點</label> <input
								type="text" class="form-control" id="locate" name="locate"
								value="${tktVO.locate}">
						</div>
					</div>

					<!-- 3 -->

					<div class="form-row d-flex input-mb">
						<div class="form-group col-md-4 input-mr">
							<label class="form-label" for="tkt_startdate">票券開始日期</label> <input
								type="date" class="form-control" id="tkt_startdate"
								name="tkt_startdate" value="${tktVO.tkt_startdate}">
						</div>
						<div class="form-group col-md-4 input-mr">
							<label class="form-label" for="tkt_enddate">票券結束日期</label> <input
								type="date" class="form-control" id="tkt_enddate"
								name="tkt_enddate" value="${tktVO.tkt_enddate}">
						</div>
						<div class="form-group col-md-3">
							<label class="form-label" for="kind">票券種類</label>
							<select
								id="inputState" class="default-select form-control wide"
								name="kind">
								<option value="0">景點門票</option>
								<option value="1">主題樂園</option>
								<option value="2">博物館展覽</option>
							</select>
						</div>
					</div>
					<!-- 4 -->
					<div class="form-group col-md-12 input-mb">
						<label class="form-label" for="address">體驗地點</label>
						<textarea class="form-control" id="address" name="address">${tktVO.address}</textarea>
					</div>
					<!-- 5 -->
					<div class="form-group col-md-12 input-mb">
						<label class="form-label" for="instruction">票券說明</label>
						<textarea class="form-control" id="instruction" name="instruction">${tktVO.instruction}</textarea>
					</div>
					<!-- 6 -->
					<div class="form-group col-md-12 input-mb">
						<label class="form-label" for="howuse">如何使用</label>
						<textarea class="form-control" id="howuse" name="howuse">${tktVO.howuse}</textarea>
					</div>
					<!-- 7 -->
					<div class="form-group col-md-12 input-mb">
						<label class="form-label" for="canxpolicy">取消政策</label>
						<textarea class="form-control" id="canxpolicy" name="canxpolicy">${tktVO.canxpolicy}</textarea>
					</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger light"
					data-bs-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary" id="tktAdd">儲存</button>
			</div>
		</div>
	</div>
	</div>	 --%>
				
				
				

			<!-- pagination -->
				<!-- <nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<li class="page-item disabled"><a class="page-link">第一頁</a></li>
						<li class="page-item"><a class="page-link" href="#">1</a></li>
						<li class="page-item"><a class="page-link" href="#">最後一頁</a>
						</li>
					</ul>
				</nav>

			</div>
		</div>
	</div> -->

	

	<%@ include file="/backend/commonJS.file"%>
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.27.2/axios.min.js"></script> -->
	
	<!-- axios Test -->
	<script>
		/* const tktAdd = document.querySelector("#tktAdd"); */
		
		<%-- const data = {
				tkt_name: ${tktVO.tkt_name},
				price: ${tktVO.price},
				tkt_status: ${tktVO.tkt_status},
				original_amount: ${tktVO.original_amount},
				locate: ${tktVO.locate},
				tkt_startdate: ${tktVO.tkt_startdate},
				tkt_enddate: ${tktVO.tkt_enddate},
				kind: ${tktVO.kind},
				address: ${tktVO.address},
				instruction: ${tktVO.instruction},
				howuse: ${tktVO.howuse},
				canxpolicy: ${tktVO.canxpolicy},
		};
		
		tktAdd.addEventListener("click", () => {
			
			fetch('<%=request.getContextPath()%>/tkt/addTkt.do', {
				method: 'post',
				headers: {
			        "Content-Type": "application/json; charset=utf-8",
			      },
			    body: JSON.stringify(data)
				
			})
			.then(function(response) {
				console.log(response);
			}).catch(function(err) {
				console.log(error);
			}) --%>

			<%-- axios.post('<%=request.getContextPath()%>/tkt/addTkt.do', {
				tkt_name: ${tktVO.tkt_name},
				price: ${tktVO.price},
				tkt_status: ${tktVO.tkt_status},
				original_amount: ${tktVO.original_amount},
				locate: ${tktVO.locate},
				tkt_startdate: ${tktVO.tkt_startdate},
				tkt_enddate: ${tktVO.tkt_enddate},
				kind: ${tktVO.kind},
				address: ${tktVO.address},
				instruction: ${tktVO.instruction},
				howuse: ${tktVO.howuse},
				canxpolicy: ${tktVO.canxpolicy},
			  })
			  .then(function (response) {
			    console.log(response);
			  })
			  .catch(function (error) {
			    console.log(error);
			  }); --%>
		});
	
	</script>

</body>
</html>