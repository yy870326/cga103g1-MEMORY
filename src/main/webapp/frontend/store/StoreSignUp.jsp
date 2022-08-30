<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="java.time.LocalDate"%>

<%

%>
<!--  
StoreVO storeVO = (StoreVO)request.getAttribute("storeVO"); 
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
		

		
	
<!-- 	內容寫在main-content這個div內    -->
<div class="main-content card card-body table-responsive" >

    <h1>廠商註冊</h1>
	<p>請填入下列資料</p>
	<form method="post" name="form1" action="<%=request.getContextPath()%>/store.do" >
		<table id="example4" class="display" style="min-width: 845px">
			 
				
				<tr>	
					<td>帳號</td>
					<td><input type="text" name="store_acc" value="${storeVO.store_acc}"></td>
				</tr>
				<tr>
					<td>密碼</td>
					<td><input type="password" name="store_pwd" value="${storeVO.store_pwd}"></td>
				</tr>	
				<tr>	
					<td>店家名稱</td>
					<td><input type="text" name="store_name" value="${storeVO.store_name}"></td>
				</tr>	
				<tr>	
					<td>統編</td>
					<td><input type="text" name="store_gui" value="${storeVO.store_gui}"></td>
				</tr>	
				<tr>	
					<td>負責人</td>
					<td><input type="text" name="store_rep" value="${storeVO.store_rep}"></td>
				</tr>	
				<tr>	
					<td>電話</td>
					<td><input type=tel name="store_tel" value="${storeVO.store_tel}"></td>
				</tr>	
				<tr>	
					<td>傳真</td>
					<td><input type="tel" name="store_fax" value="${storeVO.store_fax}"></td>
				</tr>	
				<tr>
					<td>登記地址</td>
					<td><input type="text" name="store_add" value="${storeVO.store_add}"></td>
				</tr>	
				<tr>	
					<td>聯絡人</td>
					<td><input type="text" name="store_poc" value="${storeVO.store_poc}"></td>
				</tr>	
				<tr>	
					<td>連絡電話</td>
					<td><input type="tel" name="store_con_phone" value="${storeVO.store_con_phone}"></td>
				</tr>	
				<tr>	
					<td>聯絡地址</td>
					<td><input type="tel" name="store_con_add" value="${storeVO.store_con_add}"></td>
				</tr>	
				<tr>	
					<td>信箱</td>
					<td><input type="email" name="store_email" value="${storeVO.store_email}"></td>
				</tr>	
				<tr>	
					<td>轉帳帳號</td>
					<td><input type="text" name="bank_account" value="${storeVO.bank_account}"></td>
				</tr>
				<tr>
					<td>
				 	<input type="hidden" name="action" value="insert">
				 	<input type="submit" value="加入會員">	
					</td>
				</tr>

				 <tr>
				 <td>
				 ${errorMsgs.store_acc}<br>
				 ${errorMsgs.store_pwd}<br>
				 ${errorMsgs.store_name}<br>
				 ${errorMsgs.store_gui}<br>
				 ${errorMsgs.store_rep}<br>
				 ${errorMsgs.store_tel}<br>
				 ${errorMsgs.store_fax}<br>
				 ${errorMsgs.store_add}<br>
				 ${errorMsgs.store_email}<br>
				 ${errorMsgs.bank_account}<br>
				 </td>
				 
				 </tr>
				
			 </table>
			</form> 
		</div>

		<%@ include file="/backend/commonJS.file" %> <!-- JS -->
		<script>
// 		header標題
			$("#pagename").text("MEMORY");
		</script>
	</body>
</html>