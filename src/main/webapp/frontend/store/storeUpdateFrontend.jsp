<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="java.time.LocalDate"%>

<%
StoreVO storeVO = (StoreVO)request.getAttribute("storeVO"); 

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
<div class="main-content card card-body table-responsive" >
	<form method="post" name="form1" action="<%=request.getContextPath()%>/store.do" >
		<table id="example4" class="display" style="min-width: 845px">
			<thead> 
				<tr>
					<th>廠商會員編號</th>
					<th>帳號</th>
					<th>密碼</th>
					<th>帳號狀態</th>
					<th>店家名稱</th>
					<th>統編</th>
					<th>負責人</th>
					<th>電話</th>
					<th>傳真</th>
					<th>登記地址</th>
					<th>聯絡人</th>
					<th>連絡電話</th>
					<th>聯絡地址</th>
					<th>信箱</th>
					<th>加入時間</th>
					<th>轉帳帳號</th>
					<th>票券總分數</th>
					<th>票券總評價數</th>
					<th>票券平均評價數</th>
					<th>訂房總分數</th>
					<th>訂房總評價數</th>
					<th>活動總分數</th>
					<th>活動總評價數</th>
					<th>被檢舉計點</th>
					<th>修改資料</th>
				</tr>
			 </thead>
			 <tbody>	

			 	
			 	 <tr class="view">
					<td>${storeVO.store_no}</td>
					<td><input type="text" name="store_acc" value="${storeVO.store_acc}"></td>
					<td><input type="text" name="store_pwd" value="${storeVO.store_pwd}"></td>
					<td>	
						<select  name="acc_status" size="1">
    							<option value="0">帳號未啟用</option>
							    <option value="1">帳號已啟用</option>
							    <option value="2">帳號停權</option>
  						 </select>
					</td>
					<td><input type="text" name="store_name" value="${storeVO.store_name}"></td>
					<td><input type="text" name="store_gui" value="${storeVO.store_gui}"></td>
					<td><input type="text" name="store_rep" value="${storeVO.store_rep}"></td>
					<td><input type="text" name="store_tel" value="${storeVO.store_tel}"></td>
					<td><input type="text" name="store_fax" value="${storeVO.store_fax}"></td>
					<td><input type="text" name="store_add" value="${storeVO.store_add}"></td>
					<td><input type="text" name="store_poc" value="${storeVO.store_poc}"></td>
					<td><input type="text" name="store_con_phone" value="${storeVO.store_con_phone}"></td>
					<td><input type="text" name="store_con_add" value="${storeVO.store_con_add}"></td>
					<td><input type="text" name="store_email" value="${storeVO.store_email}"></td>
					<td><input type="date" name="store_reg_date" value="${storeVO.store_reg_date}"></td>
					<td><input type="text" name="bank_account" value="${storeVO.bank_account}"></td>
					<td><input type="text" name="store_tkt_rating_score" value="${storeVO.store_tkt_rating_score}"></td>
					<td><input type="text" name="store_tkt_rating_count" value="${storeVO.store_tkt_rating_count}"></td>
					<td><input type="text" name="store_tkt_rating" value="${storeVO.store_tkt_rating}"></td>
					<td><input type="text" name="store_rm_rating_score" value="${storeVO.store_rm_rating_score}"></td>
					<td><input type="text" name="store_rm_rating_count" value="${storeVO.store_rm_rating_count}"></td>
					<td><input type="text" name="store_act_rating_score" value="${storeVO.store_act_rating_score}"></td>
					<td><input type="text" name="store_act_rating_count" value="${storeVO.store_act_rating_count}"></td>
					<td><input type="text" name="store_report_count" value="${storeVO.store_report_count}"></td> 
				 	<td>
				 	<input type="hidden" name="action" value="update">
					<input type="hidden" name="store_no" value="${storeVO.store_no}">
					<input type="submit" value="送出修改">	
					
					
					
					
					</td>		
				 </tr>
				 <tr>
				 <td>
				 ${errorMsgs.store_acc}
				 ${errorMsgs.store_pwd}
				 ${errorMsgs.store_name}
				 ${errorMsgs.store_gui}
				 ${errorMsgs.store_rep}
				 ${errorMsgs.store_tel}
				 ${errorMsgs.store_add}
				 ${errorMsgs.store_email}
				 ${errorMsgs.store_reg_date}
				 ${errorMsgs.bank_account}
				 </td>
				 
				 </tr>
				</tbody>
			 </table>
			</form> 
		</div>

		<%@ include file="/backend/commonJS.file" %> <!-- JS -->
		<script>
// 		header標題
			$("#pagename").text("MEMORY 後台管理");
		</script>
	</body>
</html>