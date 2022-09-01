<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="com.mem.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員信用卡管理</title>
<%-- CSS --%>
	<%@ include file="/frontend/commonCSS.file" %>
</head>
<body>

  <%-- header --%>
  <%@ include file="/frontend/header.file" %>
  <!-- mem-sidebar -->
  <%@ include file="/frontend/memSidebar.file" %>
	
      <!-- 主要內容 Start -->

      <div class="col-lg-9">
        <!-- h5 改 h3 -->
        <!-- 這塊刪掉放內容Start -->
        <div class="col-sm-12 mt-5 mb-5">
        	<div class="d-flex justify-content-between mb-5">
        		<h3 class="mr-3">會員信用卡管理</h3>
        		<button class="btn btn-primary">新增信用卡</button>
        	</div>
	       
	        
					<div class="table-responsive-sm">
						<table class="table table-lg table-bordered table-striped text-center">
							<thead class="all-text-white bg-grad">
								<tr>
									<th scope="col">卡號後四碼</th>
									<th scope="col">信用卡自訂別名</th>
									<th scope="col">信用卡銀行</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row">0000</th>
									<td>xxx</td>
									<td>國泰世華</td>
									<td>
										<button class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
										<button class="btn btn-info"><i class="fas fa-pen"></i></button>
									</td>
								</tr>
								<tr>
									<th scope="row">1111</th>
									<td>yyy</td>
									<td>中國信託</td>
									<td>
										<button class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
										<button class="btn btn-info"><i class="fas fa-pen"></i></button>
									</td>
								</tr>
								<tr>
									<th scope="row">2222</th>
									<td>zzz</td>
									<td>玉山</td>
									<td>
										<button class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
										<button class="btn btn-info"><i class="fas fa-pen"></i></button>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
      </div>
      
      
      
      
      
      
      
      
      
      
    </div> <!-- 這兩個 div 會影響 footer 不可刪 -->
  </div> <!-- 這兩個 div  footer 不可刪 -->

  <!-- 主內容 end -->

  <%-- footer --%>
	<%@ include file="/frontend/footer.file" %>
  	
  	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file" %>

</body>
</html>