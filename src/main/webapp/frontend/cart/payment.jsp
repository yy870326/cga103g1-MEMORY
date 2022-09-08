<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="com.cart.model.*"%>
<%@ page import="com.tkt.model.*"%>
<%@ page import="com.mem.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購物車 - Memory</title>
<%-- CSS --%>
	<%@ include file="/frontend/commonCSS.file" %>
	
	<style>
	
		.mt-60 {
		  margin-top: 60px;
		}
		.mb-20 {
		  margin-bottom: 20px;
		}
	
	</style>
</head>
<body>

<% 
/* CartItemVO cartItemVO = new CartItemVO();
System.out.println("cart.jsp cartItemVO:" + cartItemVO); */
List<CartItemVO> checkedList = (List<CartItemVO>)request.getAttribute("checkedList"); 
/* System.out.println("cart.jsp cartItems:" + cartItems); */
 /*  CartItemService cartItemSrv = new CartItemService();
  List<CartItemVO> list = cartItemSrv.getCart(sessionId);
  pageContext.setAttribute("list", list); */
  /* Integer cartItemSize = cartItems.size(); */
%>

  <%-- header --%>
  <%@ include file="/frontend/header.file" %>

  
  
  <!-- main start-->
  <!-- =======================
	Banner innerpage -->
<div class="innerpage-banner left py-7" style="background:url(https://i.imgur.com/wYzrG5n.jpg) repeat center; background-size:cover;">
  <div class="container">
    <div class="row all-text-white">
      <div class="col-md-12 align-self-center">
        <h1 class="innerpage-title text-dark">結帳</h1>
      </div>
    </div>
  </div>
</div>
  
  <!-- TABLE -->
  <form class="pt80" method="post" action="<%=request.getContextPath()%>/cart/buyTkt.do">
		
  
  
  
  <!-- =======================
	Banner innerpage -->

<section class="pb80 booking-section login-area">
  <div class="container">
    <div class="row">
      
      <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="row">
          <div class="col-lg-12 col-md-12 col-sm-12">
            <div class="login-box Booking-box">
            

             
 
                <div class="row">
                
                
                <table>
            

			
				<div class="col-sm-12 mb-3">
					<h3 class="text-center mb-4">結帳清單</h3>
					<div class="table-responsive-sm">
						<table class="table table-lg table-noborder table-striped text-center">
							<thead class="all-text-white bg-grad">
								<tr>
									<!-- <th scope="col">#</th> -->
									<th scope="col">票券名稱</th>
									<th scope="col">單價</th>
									<th scope="col">數量</th>
									<th scope="col">小計</th>
								</tr>
							</thead>
							<tbody>
								 <c:forEach var="cartItemVO" items="${checkedList}">
								<tr>
									<!-- <th scope="row">1</th> -->
									<td scope="row">${cartItemVO.tkt_name}</td>
									<td>NT$ <span class="onePrice">${cartItemVO.price}</span></td>
									<td>${cartItemVO.count}</td> 
									<td>NT$ <span class="itemPrice">${cartItemVO.price * cartItemVO.count}</span></td>
								</tr>
								
									<input type="hidden" name="tkt_no"  value="${cartItemVO.tkt_no}">
								 </c:forEach> 

								
							</tbody>
						</table>
					
					
					<hr>
					
					
					
					<%-- <div class="d-flex justify-content-between">
						<a href="<%=request.getContextPath()%>/frontend/cart/testTkt1.jsp" class="btn btn-outline-primary">繼續購物</a>  <!-- 返回票券列表要動態 -->
						<button type="submit" class="btn btn-primary"><i class="fas fa-arrow-circle-right"></i>前往結賬</button>
					</div> --%>
					
				</div>
			
  	         
            
            </table>
                
                <h2 class="mt-60">訂購者資訊</h2>
                <p class="text-danger mb-20">＊為必填資訊</p>
                
                  <div class="d-flex justify-content-between mb-20">
                  	<div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>會員編號</label>
                    <p>0001</p>
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label><span class="text-danger">*</span>姓名</label>
                    <input type="text" name="text" placeholder="自動帶入會員名稱" class="form-control">
                  </div>
                  
                  </div>
                  <div class="d-flex justify-content-between mb-20">
                  
                  	<div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label><span class="text-danger">*</span>Email</label>
                    <input type="text" name="email" placeholder="自動帶入會員 email " class="form-control">
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 password">
                    <label><span class="text-danger">*</span>聯絡電話</label>
                    <input type="text" name="text" placeholder="自動帶入會員電話號碼" class="form-control">
                  </div>
                  </div>
                  
                  
               
                  
                  <div class="divider divider-dotted"></div>
                  
                  <div class="login-top cardInfo col-lg-12 col-md-12 col-sm-12">
                    <h3>信用卡付款資訊</h3>
                    <p>可前往會員中心修改信用卡資料或新增信用卡資訊</p>
                  </div>
                  
                  <div class="d-flex justify-content-between mb-20">
                  
                  	<div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>選擇已儲存信用卡(後四碼)</label>
                    <select class="custom-select select-big mb-3">
                      <option selected>**** **** **** 0208（預設）</option>
                      <option>**** **** **** 1345</option>
                      <option>**** **** **** 9763</option>
                    </select>
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>信用卡持有者姓名</label>
                    <input type="text" name="text" placeholder="自動帶入" class="form-control">
                  </div>
                  
                  </div>
                  
                   <div class="divider divider-dotted"></div>
                   
                   <div class="login-top cardInfo col-lg-12 col-md-12 col-sm-12">
                    <h3>使用優惠券</h3>
                    <p>可選擇已擁有的優惠券</p>
                  </div>
                  
                <div class="d-flex justify-content-between mb-20">
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>優惠券選單</label>
                    <select class="custom-select select-big mb-3">
                      <option selected>--- 請選擇 ---</option>
                      <option>歡慶旅遊季優惠券</option>
                      <option>限時票券優惠</option>
                      <option>22中秋節優惠</option>
                    </select>
                  </div>
                  
                  	<div class="d-flex flex-column align-items-end mb-3"> 
							<p>商品總金額： <span class="totalPrice" style="color: red;"></span> 元</p>
							<p>折扣金額： - <span class="totalPrice" style="color: red;"></span> 元</p>
							<p>總付款金額： <span class="totalPrice" style="color: red;"></span> 元</p>
						</div>
                 
               </div>
                  
                  
                  <!-- <div class="divider divider-dotted"></div> -->
                  
                  <!-- <div class="login-top cardInfo col-lg-12 col-md-12 col-sm-12">
                    <h3>新增新用卡資料</h3>
                    <p>可前往會員中心修改信用卡資料</p>
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>信用卡卡號</label>
                    <input type="text" name="text" placeholder="Card number">
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>信用卡安全碼</label>
                    <input type="text" name="text" placeholder="Card identification number">
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>到期月份</label>
                    <select class="custom-select select-big mb-3">
                      <option selected="">select a Monthe</option>
                      <option value="location8">January</option>
                      <option value="location8">Febuary</option>
                      <option value="location8">March</option>
                      <option value="location8">April</option>
                    </select>
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>到期年份</label>
                    <select class="custom-select select-big mb-3">
                      <option selected="">select a Year</option>
                      <option value="location8">2019</option>
                      <option value="location8">2020</option>
                      <option value="location8">2021</option>
                      <option value="location8">2022</option>
                    </select>
                  </div> -->
                  <div class="col-md-12">
						
                    	<input type="hidden" name="mem_no" value="">
                    	
						<div class="d-flex justify-content-between">
							<a href="<%=request.getContextPath()%>/cart/getCart.do" class="btn btn-outline-primary">返回購物車</a>
							<button type="submit" class="btn btn-info ml-3">確認付款</button>
						</div>
					
                  </div>
                </div>
              
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
  </form>
     </div> <!-- 這兩個 div 會影響 footer 不可刪 -->
  </div> <!-- 這兩個 div  footer 不可刪 -->

  <!-- 主內容 end -->

  <%-- footer --%>
	<%@ include file="/frontend/footer.file" %>
  	
  	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file" %>
  
<script>
	const totalPrice = document.querySelector('.totalPrice'); 
	const itemPrice = document.querySelectorAll('.itemPrice'); 
	
	/* totalPrice */
	let sum = 0;
	
	for(let i = 0; i < ${checkedList.size()}; i++) {
	  sum += parseInt(itemPrice[i].innerText);
	  totalPrice.innerText = sum;
	}
</script>

</body>
</html>