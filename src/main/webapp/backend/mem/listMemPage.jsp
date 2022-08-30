<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.rm_type.model.*"%>
<%@ page import="java.time.LocalDate"%>

<jsp:useBean id="memSvc" class="com.mem.model.MemService" />

<%
List<MemVO> memList = memSvc.getAll();
pageContext.setAttribute("memList", memList);
%>

<!DOCTYPE html>
<html>

<head>
<%@ include file="/backend/commonCSS.file"%>
<!-- CSS -->
<style>
.container {
	display: flex;
	justify-content: center;
	align-items: center;
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
	<div class="main-content card card-body table-responsive">


		<div class="container-fluid">
			<div class="row">
				<div class="col-xl-12">
					<div class="tab-content">
						<div class="tab-pane active show" id="All">
							<button type="button" class="btn btn-primary mb-xxl-0 mb-4 "
								data-bs-toggle="modal" data-bs-target="#exampleModal">發送通知</button>
							<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title" id="exampleModalLabel">通知消息</h3>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<!-- 訊息內容 -->
						<input type="text" value="請輸入內容" class="modal-body">
						<!-- 預留按鍵 -->
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary">發送</button>
						</div>
					</div>
				</div>
			</div>
						
							<div class="table-responsive">
								<table
									class="table card-table default-table display mb-4 dataTablesCard table-responsive-xl"
									id="example4">
									<thead>
										<tr>
											<th class="bg-none">	
												<div class="form-check style-1">
													<input class="form-check-input" type="checkbox" value=""
														id="checkAll">
												</div>
											</th>
											<th>會員</th>
											<th>性別</th>
											<th>電話</th>
											<th>信箱</th>
											<th>地址</th>
											<th>被檢舉記點</th>
											<th
												style="width: 64; padding-right: 30px; padding-left: 30px;">狀態</th>
											<th class="bg-none"></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="memVO" items="${memList}">
											<tr>
												<td>
													<div class="form-check style-1">
														<input class="form-check-input" type="checkbox" value="">
													</div>
												</td>
												<td>
													<div class="media-bx">
														<c:choose>
															<c:when test="${memVO.mem_pic != null}">
																<img
																	src="<%=request.getContextPath()%>/Mem?mem_no=${memVO.mem_no}&action=getOnePic">
															</c:when>
															<c:when test="${memVO.mem_pic == null}">
																<img
																	src="<%=request.getContextPath()%>/backend/assets/img/nullpic.png"
																	class="no-img">
															</c:when>
														</c:choose>
														<div>
															<span class="text-primary">#${memVO.mem_no}</span>
															<h4 class="mb-0 mt-1">
																<a class="text-black">${memVO.mem_name}</a>
															</h4>
														</div>
													</div>
												</td>
												<td>
													<div>${memVO.mem_gender}</div>
												</td>
												<td>
													<div>${memVO.mem_mobile}</div>
												</td>
												<td>
													<div>${memVO.mem_email}</div>
												</td>
												<td>
													<div>${memVO.mem_city}${memVO.mem_dist}${memVO.mem_addr}</div>
												</td>
												<td>
													<div class="container">${memVO.mem_report_count}</div>
												</td>
												<td><c:choose>
														<c:when test="${memVO.acc_status==0}">
															<i class='bx bxs-circle' style='color: #aaa'></i>未啟用</c:when>
														<c:when test="${memVO.acc_status==1}">
															<i class='bx bxs-circle' style='color: green'></i>已啟用</c:when>
														<c:when test="${memVO.acc_status==2}">
															<i class='bx bxs-circle' style='color: red'></i>停權中</c:when>
													</c:choose></td>
												<td>
													<div class="dropdown dropstart">
														<a href="javascript:void(0);" class="btn-link"
															data-bs-toggle="dropdown" aria-expanded="false"> <img
															src="<%=request.getContextPath()%>/backend/assets/img/selectStatus.png">
														</a>
														<div class="dropdown-menu">
															<FORM METHOD="post"
																ACTION="<%=request.getContextPath()%>/Mem">
																<input class="dropdown-item container" type="submit"
																	value="${memVO.acc_status==1 ? '停權' : '啟用' }">
																<input type="hidden" name="mem_no"
																	value="${memVO.mem_no}"> <input type="hidden"
																	name="action"
																	value="${memVO.acc_status==1 ? 'unupdateStatus' : 'updateStatus' }">
															</FORM>
														</div>
													</div>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<%@ include file="/backend/commonJS.file"%>
		<!-- JS -->
		<script>
			// header標題
				$("#pagename").text("MEMORY 會員管理");
		</script>
</body>
</html> --%>