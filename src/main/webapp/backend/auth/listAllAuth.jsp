<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.auth.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="authFunSvc" class="com.auth_fun.model.AuthFunService"/>

<%
// 計算各狀態有幾筆資料
AuthService authSvc = new AuthService(); 
pageContext.setAttribute("authSvc", authSvc);

//第一次進來執行if裡面，list是getAll
// 不是第一次進來(點擊狀態分類從controller過來的)，table中就用forward過來的list
/* if (request.getAttribute("list") == null) {	 */
	if (request.getAttribute("list") == null) {
	List<AuthVO> list = authSvc.getAllAuth();
	pageContext.setAttribute("list",list);	
	}
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
				<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<div class="d-flex mb-4 justify-content-start align-items-center flex-wrap add">
				<a href='<%=request.getContextPath()%>/backend/auth/addAuth.jsp'>
					<button type="button" class="btn btn-rounded btn-primary"><span
						class="btn-icon-start text-primary"><i class='bx bx-plus' style='color:#00ADB5' ></i>
					</span>新增</button>				
				</a>
				<div class="card-tabs mt-3 mt-sm-0">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item">
							<a class="nav-link" href="<%=request.getContextPath()%>/auth/addAuth.do?action=getAllAuth">所有權限列表 (${authSvc.getAllAuth().size()})</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="table-responsive">
				<table id="authTable" class="display" style="min-width: 845px;">
					<thead>
						<tr>
							<th>權限編號</th>
							<th>員工編號</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="authVO" items="${list}">
						<tr class="view">
							<td>${authVO.fun_no}</td>
							<%-- <td>${roomTypeSvc.getOneRoomType(roomVO.type_no).type_name}</td> --%>
							<td>${authVO.emp_no}</td>
							<%-- <td>${authVO.name_title}</td> --%>
							<td>
<%-- 								<form method="post" action="<%=request.getContextPath()%>/room/Room"> --%>
<%-- 									<input type="hidden" name="rm_no"  value="${roomVO.rm_no}"> --%>
<!-- 			     					<input type="hidden" name="action"	value="getOneForUpdate"> -->
<!-- 									<button type="submit" class="btn btn-secondary btn-sm"><i class='bx bxs-pencil'></i>修改</button> -->
<!-- 			     				</form> -->
			     				<a class="btn btn-secondary btn-sm" href="<%=request.getContextPath()%>/auth/updateAuth.do?emp_no=${authVO.emp_no}&action=getOneForUpdate">修改</a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>


		<%@ include file="/backend/commonJS.file" %> <!-- JS -->
		<script>
// 		header標題
			$("#pagename").text("MEMORY 後台管理");
		</script>
	</body>
</html>