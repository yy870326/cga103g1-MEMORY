<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.auth_fun.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="authFunSvc" class="com.auth_fun.model.AuthFunService"/>

<%
// 計算各狀態有幾筆資料
// pageContext.setAttribute("authFunSvc", authFunSvc);
/* AuthFunService authFunSvc = new AuthFunService(); */
List<AuthFunVO> list = authFunSvc.getAll();
pageContext.setAttribute("list",list);
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
					<th>權限編號</th>
					<th>權限名稱</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="authFunVO" items="${list}">
					<tr class="view">
						<td>${authFunVO.fun_no}</td>
						<td>${authFunVO.fun_name}</td>
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