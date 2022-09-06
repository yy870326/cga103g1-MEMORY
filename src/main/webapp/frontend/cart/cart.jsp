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
		.btn-small {
			padding: 0px 10px;
			margin-bottom: 0px;
			line-height: 20px;
			border-radius: 100%;
			width: 25px;
			height: 25px;
			text-align: center;
		}
	</style>
	
</head>
<body>

<% 
/* CartItemVO cartItemVO = new CartItemVO();
System.out.println("cart.jsp cartItemVO:" + cartItemVO); */
List<CartItemVO> cartItems = (List<CartItemVO>)request.getAttribute("cartItems"); 
/* System.out.println("cart.jsp cartItems:" + cartItems); */
 /*  CartItemService cartItemSrv = new CartItemService();
  List<CartItemVO> list = cartItemSrv.getCart(sessionId);
  pageContext.setAttribute("list", list); */
  /* Integer cartItemSize = cartItems.size(); */
%>

  <%-- header --%>
  <%@ include file="/frontend/header.file" %>

  
  
  
  
    <!-- =======================
	Banner innerpage -->
<div class="innerpage-banner left py-7" style="background:url(https://i.imgur.com/wYzrG5n.jpg) center repeat; background-size:cover;">
  <div class="container">
    <div class="row all-text-white">
      <div class="col-md-12 align-self-center">
        <h1 class="innerpage-title text-dark">購物車</h1>
      </div>
    </div>
  </div>
</div>
  <!-- main start-->
  
  <form class="pt80 pb80" method="post" action="<%=request.getContextPath()%>/cart/cartCheckOut.do">
		<div class="container">
			<div class="row">
				<div class="col-sm-12 mb-5">
					<h3 class="text-center mb-4">購物車清單</h3>
					<div class="table-responsive-sm">
						<table class="table table-lg table-noborder table-striped">
							<thead class="all-text-white bg-grad">
								<tr>
									<!-- <th scope="col">#</th> -->
									<th scope="col"></th>
									<th scope="col">票券名稱</th>
									<th scope="col">單價</th>
									<th scope="col">數量</th>
									<th scope="col">小計</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<%-- <c:if test="${cartItems != null && (cartItemSize > 0)}"> --%>
								<c:forEach var="cartItemVO" items="${cartItems}">
								<tr class="tr">
									<%-- <th scope="row">${cartItemVO.tkt_no}</th> --%>
									<td>
										<%-- <label>${cartItemVO.tkt_no}</label> --%>
										<input type="checkbox" name="tkt_no" value="${cartItemVO.tkt_no}" class="checkbox" checked>
									</td>
									<td>${cartItemVO.tkt_name}</td>
									<td>NT$ <span class="onePrice">${cartItemVO.price}</span></td>
									<td>
										<button type="button" class="btn btn-primary btn-small minus">-</button>
										<span class="itemCount">${cartItemVO.count}</span>
										<button type="button" class="btn btn-primary btn-small plus">+</button>
									</td>
									<%-- <td><input type="number" value="${cartItemVO.count}"></td>  --%>
									<td class="itemsPrice">${cartItemVO.price * cartItemVO.count}</td>
									<td>
										<%-- <form method="post" action="<%=request.getContextPath()%>/cart/delCartItem.do">
											<input type="hidden" name="action"  value="delItem"> --%>
              								<input class="tkt_no1" type="hidden" name="tkt_no1"  value="${cartItemVO.tkt_no}">
											<!-- 刪除按鈕 -->
											<button  type="button" class="btn btn-danger delItem"><i class="fas fa-trash-alt"></i></button>
										<!-- </form> -->
										
									</td>
								</tr>
								</c:forEach>
								 <%-- </c:if> --%>
								<%-- <c:if test="${cartItems == null && (cartItemSize == 0)}">
								<tr>
									<td>尚未加入票券到購物車</td>
								</tr>
								
								</c:if> --%>
								
							</tbody>
						</table>
					</div>
					
					<hr>
					
					<!-- <div class="d-flex justify-content-end mb-3"> 
						<p>總計： <span class="totalPrice" style="color: red;"></span> 元</p>
					</div> -->
					
					<div class="d-flex justify-content-between">
						<!-- <div class="d-flex"> -->
							<a href="<%=request.getContextPath()%>/frontend/tkt/tktShopList.jsp" class="btn btn-outline-primary">繼續購物</a>  <!-- 返回票券列表要動態 -->
							<!-- 再看看要不要做清空購物車按鈕 -->
							<%-- <form method="post" action="<%=request.getContextPath()%>/cart/delCartItem.do">
								<input type="hidden" name="action"  value="delItem">
              					<input type="hidden" name="tkt_no"  value="${cartItemVO.tkt_no}">
								<button class="btn btn-outline-primary">清空購物車</button>
							</form>	 --%>			
						<!-- </div> -->
						<button type="submit" class="btn btn-primary checkOut"><i class="fas fa-arrow-circle-right"></i>前往結賬</button>
					</div>
					
				</div>
			</div>
  		</div>
  </form>
  
  
  
     </div> <!-- 這兩個 div 會影響 footer 不可刪 -->
  </div> <!-- 這兩個 div  footer 不可刪 -->

  <!-- 主內容 end -->

  <%-- footer --%>
	<%@ include file="/frontend/footer.file" %>
  	
  	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file" %>
  
  <!-- jquery cdn -->
  <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
  
  <!-- sweetalert -->
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
  <!-- axios test -->
 <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.20.0/axios.js"></script>
  
  <script>
  <!-- cart js -->
  const minusBtn = document.querySelectorAll('.minus');
  const plusBtn = document.querySelectorAll('.plus');
  const itemCount = document.querySelectorAll('.itemCount'); 
  const itemsPrice = document.querySelectorAll('.itemsPrice'); 
  const onePrice = document.querySelectorAll('.onePrice'); 
  const totalPrice = document.querySelector('.totalPrice'); 
  const tkt_no_s = document.querySelectorAll('.tkt_no1'); 
  const tr = document.querySelectorAll('.tr'); 
  const delItem = document.querySelectorAll('.delItem'); 
/*   const checkOut = document.querySelector('.checkOut');
  const checkbox = document.querySelectorAll('.checkbox'); */
 
  /* let sum = 0; */
  
 
  for(let i = 0; i < ${cartItems.size()}; i++) {
	  
	  /* checkbox */
	  /* 陣列裝被勾選的票券 */
 	  /* let checkboxvalue = [];
	  
 	 checkbox[i].addEventListener('change', () => {
	  for (i in checkbox) {
		  if(checkbox[i].checked) {
			  checkboxvalue.push(checkbox[i].value);
			  
		  }
	  } */
	  
		  /* console.log(checkboxvalue); */
		  
		  
		  /* 送出 checkbox 選購項目 */
		  /* 無法成功送出 checkbox  */
		  /* checkOut.addEventListener('click', () => {
			  axios({
	 		   		"method": "post", 
	 		   		"url": "cartCheckOut.do",
	 		   	 	"Content-Type": "application/json",
	 		   		"params": {
	 		   			"tkt_no": checkboxvalue
	 		   		}
	 		    }).then(function (response) {
	 		        console.log(response);
	 		    }).catch(function (error) {
	 		         console.log(error);
	 		    }); 
		  }); */
		  
 	 /*  }); */
	  
	  
	  
	  /* 取出該項目的 tkt_no 轉型為數字送進 servlet */
	  let tkt_no = parseInt(tkt_no_s[i].value);
	  
	  /* sweetalert btn setting*/
		 const swalWithBootstrapButtons = Swal.mixin({
	        customClass: {
	            confirmButton: 'btn btn-success ml-3',
	            cancelButton: 'btn btn-danger mr-3'
	        },
	        buttonsStyling: false
	    })
	    
	    
	   /* totalPrice 還沒完工*/
	  /* sum += parseInt(itemsPrice[i].innerText);	
	  totalPrice.innerText = sum; */
	   
	  /* delItem */
	  
	  delItem[i].addEventListener('click', () => {
		  
			  swalWithBootstrapButtons.fire({
			        title: '確定將此票券商品移除購物車嗎?',
			        text: "刪除後，此票券商品將不在購物車中",
			        icon: 'warning',
			        showCancelButton: true,
			        confirmButtonText: '是的',
			        cancelButtonText: '取消',
			        reverseButtons: true
			    }).then((result) => {
			        if (result.isConfirmed) {
			            swalWithBootstrapButtons.fire(
			                '已成功刪除此票券商品!',
			                '若想再次將商品加入購物車，請至票券商城',
			                'success'
			            )
			            
			           axios({
			   				"method": "post", 
			   				"url": "delCartItem.do",
			   				"params": {
			   					"tkt_no": tkt_no
			   				}
			   		   }).then(function(response) { 
			   		   		/* console.log(response); */
			   		   		tr[i].setAttribute('style', 'display: none');
			   		   }).catch((error) => console.log(error));

			        }

			    })
		 
	  });
	  
	 
	  /* minusBtn */
 	minusBtn[i].addEventListener('click', () => {
		if(parseInt(itemCount[i].innerText) > 1) {
 			let afterMinus = parseInt(itemCount[i].innerText) -1;
 			itemCount[i].innerText = afterMinus; 
 			/* 更新小計 */
 		 	itemsPrice[i].innerText = parseInt(itemCount[i].innerText) * parseInt(onePrice[i].innerText); 
 		 	
 				  
 			/* 更新 redis 數量 */
 			axios({
 		   		"method": "post", 
 		   		"url": "changeCount.do",
 		   		"params": {
 		   			"count": afterMinus,
 		   			"tkt_no": tkt_no
 		   		}
 		    }).then(function (response) {
 		        /* console.log(response); */
 		    }).catch(function (error) {
 		         console.log(error);
 		    }); 
 			
 			/* $.ajax({
 				url: "changeCount.do",
 				type: "post",
 				data: {
 					count: afterMinus,
 					tkt_no: tkt_no
 				}
 			}); */
 			
 		} else if (parseInt(itemCount[i].innerText) === 1) { // 當購物車商品已經只剩下 1 時，還想要繼續 -1 會詢問會員是否要刪除品項
 			
 		    swalWithBootstrapButtons.fire({
 		        title: '確定將此票券商品移除購物車嗎?',
 		        text: "刪除後，此票券商品將不在購物車中",
 		        icon: 'warning',
 		        showCancelButton: true,
 		        confirmButtonText: '是的',
 		        cancelButtonText: '取消',
 		        reverseButtons: true
 		    }).then((result) => {
 		        if (result.isConfirmed) {
 		            swalWithBootstrapButtons.fire(
 		                '已成功刪除此票券商品!',
 		                '若想再次將商品加入購物車，請至票券商城',
 		                'success'
 		            )
 		            /* 如果按確認刪除按鈕 */
 		           let afterMinus = parseInt(itemCount[i].innerText) -1;
 		            
 		           axios({
 		   				"method": "post", 
 		   				"url": "changeCount.do",
 		   				"params": {
 		   					"count": afterMinus,
 		   					"tkt_no": tkt_no
 		   				}
 		   		   }).then(function (response) {
 		   			  tr[i].setAttribute('style', 'display: none');
 		              /* console.log(response); */
 		          }).catch(function (error) {
 		              console.log(error);
 		          }); 
 		           /* $.ajax({
 		 				url: "changeCount.do",
 		 				type: "post",
 		 				data: {
 		 					count: afterMinus,
 		 					tkt_no: tkt_no
 		 				},
 		 				success: function() {
 		 					tr[i].setAttribute('style', 'display: none');
 		 				}
 		 			});  */
 		        }

 		    })
 		}
 		
		
 		
  	});
	  
	  
	  /* plusBtn */
	  
 	plusBtn[i].addEventListener('click', () => {
 		
  		let afterPlus = parseInt(itemCount[i].innerText) +1;
  		itemCount[i].innerText = afterPlus; 
  		/* 更新小計 */
  		itemsPrice[i].innerText = parseInt(itemCount[i].innerText) * parseInt(onePrice[i].innerText); 
  	
  		
  		/* 更新 redis 數量 */
  		/* $.ajax({
 			url: "changeCount.do",
 			type: "post",
 			data: {
 				count: afterPlus,
 				tkt_no: tkt_no
 			}
 		}); */
 		
 		axios({
 			"method": "post", 
 			"url": "changeCount.do",
 			"params": {
 				"count": afterPlus,
 				"tkt_no": tkt_no
 			}
 		}).then(function (response) {
            /* console.log(response); */
        }).catch(function (error) {
            console.log(error);
        });
 		
   	});
	  
	  
	  
 	 
 	 
	  
 } 
 
  
 
 	
  </script>

</body>
</html>