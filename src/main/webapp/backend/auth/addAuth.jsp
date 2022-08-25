<%@page import="com.auth.model.AuthVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.auth_fun.model.*"%>
<%@ page import="java.time.LocalDate"%>
<jsp:useBean id="authSvc" class="com.auth.model.AuthService"/>

<%
AuthVO authVO = (AuthVO) request.getAttribute("authVO");
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
<div class="main-content">
			<a class="btn btn-secondary light" href="<%=request.getContextPath()%>/backend/auth/listAllAuth.jsp">&lt; 回權限列表</a>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<div class="card col-xl-9">  
				<form method="post" action="<%=request.getContextPath()%>/auth/addAuth.do" name="addAuth">
					<div class="card-body d-flex justify-content-center">
						<div class="col-xl-8">
							<div class="row mb-2">
							    <label for="fun_no" class="col-sm-3 col-form-label">權限編號</label>
							    <div class="col-sm-8">
							    	<input type="text" name="fun_no" maxlength="3" class="form-control" id="fun_no" value="<%=(authVO == null) ? "" : authVO.getFun_no()%>">
							    </div>
							</div>
							<div class="row mb-2">
							    <label for="emp_no" class="col-sm-3 col-form-label">員工編號</label>
							    <div class="col-sm-8">
							    	<input type="text" name="emp_no" maxlength="3" class="form-control" id="emp_no" value="<%=(authVO == null) ? "" : authVO.getEmp_no()%>">
							    </div>	
							    	<%-- <select class="mt-2 form-select" name="emp_no">
							    		<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
										<c:forEach var="empVO" items="${empSvc.allEmp}">
											<option value="${empVO.emp_no}" ${(authVO.emp_no==empVO.emp_no)?'selected':'' }>${empVO.emp_name}</option>
										</c:forEach> 
									</select> --%>
							    </div>
							</div>
							</div>
						</div>
					</div>
					<div class="mb-3 d-flex justify-content-center align-items-center">
						<input type="hidden" name="action" value="insert">
						<input class="btn btn-primary col-lg-3" type="submit" value="新增">
                	</div>
				</form>
			</div>
		</div>


		<%@ include file="/backend/commonJS.file" %> <!-- JS -->
		<script>
// 		header標題
			$("#pagename").text("MEMORY 後台管理");
		</script>
	</body>
</html>