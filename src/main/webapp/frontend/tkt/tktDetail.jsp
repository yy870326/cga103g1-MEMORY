<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coup.model.*"%>
<%@ page import="com.tkt.model.*"%>
<%@ page import="com.mem.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>票券詳情 - Memory</title>
<%-- CSS --%>
	<%@ include file="/frontend/commonCSS.file" %>
</head>
<body>

  <%-- header --%>
  <%@ include file="/frontend/header.file" %>

  
  <!-- main -->
  
  <section class="pt80 pb80 listingDetails Campaigns">
  <div class="container">
    <div class="row"> 
      
      <!-- Tab line -->
      <div class="col-lg-8 col-md-12 col-sm-12 ">
        <!-- <ul class="nav nav-tabs tab-line"> -->
          <!-- <li class="nav-item"> <a class="nav-link active" data-toggle="tab" href="#tab-de-1"> 票券內容說明 </a> </li> -->
          <!-- <li class="nav-item"> <a class="nav-link active" data-toggle="tab" href="#tab-de-2"> Availability </a> </li> -->
         <!--  <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#tab-de-3"> Amenities </a> </li>
          <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#tab-de-4"> Calendar </a> </li> -->
        <!--   <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#tab-de-5"> 照片 </a> </li>
          <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#tab-de-6"> 評價 </a> </li> -->
        <!-- </ul> -->
        
        <a href="<%=request.getContextPath()%>/frontend/tkt/tktShopList.jsp" class="btn btn-info mb-3">返回票券商城</a> 
        <div class="tab-content">
          <div class="tab-pane active" id="tab-de-1">
            <div class="text-block NopaddingDetails">
              <h3 class="mb-4">${tktVO.tkt_name}</h3>
              <p class="mb-2">詳細說明：${tktVO.instruction}</p>
              <p class="mb-2">開始日期：${tktVO.tkt_startdate}</p>
              <p class="mb-2">結束日期：${tktVO.tkt_enddate}</p>
              <p class="mb-2">地區：${tktVO.locate}</p>
              <p class="mb-2">體驗地址：${tktVO.address}</p>
              <p class="mb-2">購買須知：${tktVO.notice}</p>
              <p class="mb-2">如何使用：${tktVO.howuse}</p>
              <p class="mb-2">取消政策：${tktVO.canxpolicy}</p>
              
              <c:if test="${tktVO.kind == 0 }" var="true">
					<span class="badge badge-pill badge-primary text-white"># 景點門票</span>
			  </c:if> 
			  <c:if test="${tktVO.kind == 1 }" var="true">
					<span class="badge badge-pill badge-success text-white"># 主題樂園</span>
			  </c:if> 
			  <c:if test="${tktVO.kind == 2 }" var="true">
					<span class="badge badge-pill badge-info text-white"># 博物館展覽</span>
			  </c:if>
              
            </div>
            <!-- <div class="text-block"> 
              Listing Location
              <h5 class="mb-4">Location</h5>
              <div class="map-wrapper-300 mb-3">
                <div class="map-container fw-map">
                  <div id="map-main"> </div>
                </div>
              </div>
            </div> -->
            <div class="text-block"> 
              <!-- Gallery-->
              <h5 class="mb-4"></h5>
              <div class="row gallery ml-n1 mr-n1">
                <div class="col-lg-12 col-6 px-1 mb-2"><a href="#"><img src="https://i.imgur.com/eZXD0nx.jpg" alt="..." class="img-fluid"></a></div>
              </div>
            </div>
            <div class="text-block">
              
              
              <div class="media d-block d-sm-flex review">
                <!-- <div class="text-md-center mr-4 mr-xl-5"><img src="images/img-11.jpg" alt="Jabba Hut" class="avatar avatar-xl p-2 mb-2"></div> -->
                <div class="media-body">
                  
                  
                </div>
              </div>
            </div>
          </div>
         

        </div>
      </div>
      <!-- cart form -->
      <div class="col-lg-4 col-md-12 col-sm-12 right_Details">
        <div class="p-4 shadow ml-lg-4 rounded sticky-top" style="top: 100px;">
        
      <%--  <form id="booking-form" METHOD="post" ACTION="<%=request.getContextPath()%>/cart/addCart.do" class="form"> --%>
        <h5 class="mb-4">${tktVO.tkt_name}</h5>
          <p class="text-muted"><span class="text-primary h2">${tktVO.price}</span> / 人</p>
          <hr class="my-4">
            <div class="form-group">
            <div class="form-group mb-4">
              <label for="guests" class="form-label">票券張數</label>
              <!-- 假數量 之後會接到真的票券資料用 el 綁定數量 -->
              <input type="number" value="1" id="guests" class="form-control">
<!--               <input type="number" name="count" id="guests" class="form-control"> -->
            </div>
              <!-- 假資料 真的資料會從資料庫撈 tkt_no -->
               <input type="hidden" name="tkt_no"  value="${tktVO.tkt_no}" class="tkt_no"> 
			   <!-- <input type="hidden" name="tkt_name"  value="201 景觀台門票">
			   <input type="hidden" name="price"  value="500"> -->
			   <input type="hidden" name="count"  value="1" class="count">
              <button type="submit" class="btn btn-primary btn-block addItem">加入購物車<i class="fas fa-cart-arrow-down"></i></button>
			   <input type="hidden" name="action"	value="addItem">
			   <!-- <input type="hidden" name="count"  value="2"> -->
			   <!-- <input type="hidden" name="action"	value="addItem"> -->
            </div>
         <!--  </form> -->
          
          
         <%--  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cart/initCart.do">
			<input type="submit" class="btn btn-primary btn-block" value="測試 cookie">
          	<input type="hidden" name="action" value="init">
			<!-- <input type="hidden" name="action"	value="init"> -->
 		 </FORM> --%>
 		 
 		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cart/getCart.do">
			<input type="submit" class="btn btn-primary btn-block" value="前往購物車列表">
 			<input type="hidden" name="action"	value="getCart">
			<!-- <input type="hidden" name="action"	value="getCart"> -->
 		</FORM>
          <!-- <hr class="my-4">
          <div class="text-center">
            <p> <a href="#" class="text-secondary text-sm"> <i class="fa fa-heart"></i> Bookmark This Hotels</a></p>
          </div> -->
        </div>
      </div>
    </div>
  </div>
</section>

  
  
     </div> <!-- 這兩個 div 會影響 footer 不可刪 -->
  </div> <!-- 這兩個 div  footer 不可刪 -->

  <!-- 主內容 end -->

  <%-- footer --%>
	<%@ include file="/frontend/footer.file" %>
  	
  	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file" %>
	
	
	
	<!-- axios test -->
 <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.20.0/axios.js"></script>
 
 <!-- init 加到 footer 或 header 或 JS file-->
<%-- <script src="<%=request.getContextPath()%>/frontend/cart/init.js"></script> --%>
 
 
 <script>
 
 	const addItem = document.querySelector('.addItem');
 	const tkt_no = document.querySelector('.tkt_no');
 	const count = document.querySelector('.count');

 	
 	addItem.addEventListener('click', () => {
 		axios({
 			"method": "post", 
 			"url": "/CGA103G1/cart/addCart.do",
 			"params": {
 				"count": parseInt(count.value),
 				"tkt_no": parseInt(tkt_no.value)
 			}
 		}).then(function (response) {
            console.log(response);
        }).catch(function (error) {
            console.log(error);
        });
 	});
 	
 	// 載入商品單一頁面或商品列表頁就自動初始化
 	
 	/* function init(){ 
 		axios({
 	 		"method": "post", 
 	 		"url": "/CGA103G1/cart/initCart.do"
 		}).then(function (response) {
 	    	//console.log(response);
 		}).catch(function (error) {
 	    	console.log(error);
 		});
 	} */

 	// 當畫面載入時初始化
 	/* window.onload = init(); */
 
 </script>
  

</body>
</html>