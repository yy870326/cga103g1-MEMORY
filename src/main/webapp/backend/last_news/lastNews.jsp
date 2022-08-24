<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.last_news.model.*"%>
<!DOCTYPE html>
<html>

<%
LastNewsService lnSvc = new LastNewsService();
List<LastNewsVO> lastNews = lnSvc.getLastNewsAll();
pageContext.setAttribute("lastNews", lastNews);
%>
<head>
<%@ include file="/backend/commonCSS.file"%>
</head>

<body>
	<%@ include file="/backend/loading.file"%>
	<!-- loading -->
	<%@ include file="/backend/header.file"%>
	<!-- Header -->
	<%@ include file="/backend/sidebar.file"%>
	<!-- sidebar -->

	<div class="main-content">


		<hr>
		<br> <br>

		<h3>
			最新消息管理 <a
				href="<%=request.getContextPath()%>/backend/last_news/addNews.jsp">新增</a>
		</h3>
		<table class="table" id="example4">
			<thead>
				<tr>
					<th>#</th>
					<th>文字</th>
					<th>圖片</th>
					<th>時間</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="lastNewsVO" items="${lastNews}">
					<tr>
						<td>${lastNewsVO.news_no}</td>
						<td>${lastNewsVO.news}</td>
						<td><img
							src="<%=request.getContextPath()%>/lastNews?news_no=${lastNewsVO.news_no}&action=getOnePic"
							width="150" height="150"></td>
						<td>${lastNewsVO.news_time}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>


	<%@ include file="/backend/commonJS.file"%>
	<!-- 基本JS檔案 -->
	<script>
		$(document).ready(
				$("#pagename").text("最新消息管理");
				function() {
					$(document).ready(function() {
						   $('#example4').DataTable();
				});
	</script>
</body>
</html>