<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.mem_coup.model.*"%>

<% 
CoupService coupSrv = new CoupService();
List<CoupVO> list = coupSrv.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>優惠券領取專區</title>
<%-- CSS --%>
	<%@ include file="/frontend/commonCSS.file" %>
	
	
</head>
<body>

  <%-- header --%>
  <%@ include file="/frontend/header.file" %>

      <!-- 主要內容 Start -->

      <div class="col-lg-12 mt-5 mb-5">
        <!-- 內容Start -->
        <h3 class="mt-5 mb-5 text-center">優惠券領取專區</h3>
        
        
		
        <div class="col-sm-12 col-md-12 d-flex justify-content-center">
			
			<table class="table table-hover table-responsive-sm fold-table text-center">
					<thead class="thead-dark">
						<tr>
							<th scope="col">優惠券名稱</th>
							<th scope="col">折抵金額</th>
							<th scope="col">到期日</th>
							<th scope="col"></th>
						</tr>
					</thead>
					
					<tbody>
					
					
					<c:forEach var="coupVO" items="${list}">
						<c:if test="${coupVO.status == 1}">
							<tr>
								<td>${coupVO.coup_name}</td>
								<td class="text-danger">- NT$ ${coupVO.discount}</td>
								<td>${coupVO.enddate}</td>
								<td>
									<button type="button" class="btn btn-primary">點我領取</button>
								</td>
							</tr>
							</c:if>
						</c:forEach>

					</tbody>
				</table>
			
			
      
      
      
      
      
      
      
      
      
    </div> <!-- 這兩個 div 會影響 footer 不可刪 -->
  </div> <!-- 這兩個 div  footer 不可刪 -->

  <!-- 主內容 end -->

  <%-- footer --%>
	<%@ include file="/frontend/footer.file" %>
  	
  	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file" %>

</body>
</html>