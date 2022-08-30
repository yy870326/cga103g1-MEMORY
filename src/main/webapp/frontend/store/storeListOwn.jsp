<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="java.time.LocalDate"%>


<%
StoreVO storeVO = (StoreVO)request.getAttribute("storeVO");
String sacc = storeVO.getStore_acc();
StoreService storeSvc = new StoreService();
StoreVO storeVO1 = storeSvc.getOneStoreByAcc(sacc);

pageContext.setAttribute("storeVO1",storeVO1);
%>
<!--
-->
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
				<tr>
					<td>廠商會員編號</td>
					<td>${storeVO1.store_no}</td>
				</tr>
				<tr>	
					<td>帳號</td>
					<td>${storeVO1.store_acc}</td>
				</tr>	
				<tr>
					<td>密碼</td>
					<td>${storeVO1.store_pwd}</td>
				</tr>
				<tr>	
					<td>帳號狀態</td>
					<td><c:choose>
						<c:when test="${storeVO1.acc_status==0}">									
								    <i class='bx bxs-circle' style='color: red'></i>帳號未啟用</c:when>
						<c:when test="${storeVO1.acc_status==1}">
									<i class='bx bxs-circle' style='color: green'></i>帳號已啟用</c:when>
						<c:when test="${storeVO1.acc_status==2}">
									<i class='bx bxs-circle' style='color: #aaa'></i>帳號停權</c:when>
					</c:choose></td>
				</tr>
				<tr>	
					<td>店家名稱</td>
					<td>${storeVO1.store_name}</td>
				</tr>
				<tr>	
					<td>統編</td>
					<td>${storeVO1.store_gui}</td>
				</tr>
				<tr>	
					<td>負責人</td>
					<td>${storeVO1.store_rep}</td>
				</tr>
				<tr>	
					<td>電話</td>
					<td>${storeVO1.store_tel}</td>
				</tr>
				<tr>
					<td>傳真</td>
					<td>${storeVO1.store_fax}</td>
				</tr>
				<tr>
					<td>登記地址</td>
					<td>${storeVO1.store_add}</td>
				</tr>
				<tr>
					<td>聯絡人</td>
					<td>${storeVO1.store_poc}</td>
				</tr>
				<tr>	
					<td>連絡電話</td>
					<td>${storeVO1.store_con_phone}</td>
				</tr>
				<tr>	
					<td>聯絡地址</td>
					<td>${storeVO1.store_con_add}</td>
				</tr>
				<tr>	
					<td>信箱</td>
					<td>${storeVO1.store_email}</td>
				</tr>
				<tr>	
					<td>加入時間</td>
					<td>${storeVO1.store_reg_date}</td>
				</tr>
				<tr>	
					<td>轉帳帳號</td>
					<td>${storeVO1.bank_account}</td>
				</tr>
				<tr>	
					<td>票券總分數</td>
					<td>${storeVO1.store_tkt_rating_score}</td>
				</tr>
				<tr>	
					<td>票券總評價數</td>
					<td>${storeVO1.store_tkt_rating_count}</td>
				</tr>
				<tr>	
					<td>票券平均評價數</td>
					<td>${storeVO1.store_tkt_rating}</td>
				</tr>
				<tr>	
					<td>訂房總分數</td>
					<td>${storeVO1.store_rm_rating_score}</td>
				</tr>
				<tr>	
					<td>訂房總評價數</td>
					<td>${storeVO1.store_rm_rating_count}</td>
				</tr>
				<tr>	
					<td>活動總分數</td>
					<td>${storeVO1.store_act_rating_score}</td>
				</tr>
				<tr>	
					<td>活動總評價數</td>
					<td>${storeVO1.store_act_rating_count}</td>
				</tr>
				<tr>	
					<td>被檢舉計點</td>
					<td>${storeVO1.store_report_count}</td>
				</tr>
				<tr>	
					<td>修改資料</td>
					<td>
						<form method="post" action="<%=request.getContextPath()%>/store.do">
							<input type="submit" value="修改">
		  					<input type="hidden" name="store_no" value="${storeVO1.store_no}">
							<input type="hidden" name="action" value="updateStore">
						</form>
					</td> 
				</tr>
			
			 	
			 	 <tr class="view">
				
				 </tr>
			 </table>
		</div>

		<%@ include file="/backend/commonJS.file" %> <!-- JS -->
		<script>
// 		header標題
			$("#pagename").text("MEMORY");
		</script>
	</body>
</html>