<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tkt.model.*"%>
<%@ page import="com.tkt_img.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
TktService tktSvc = new TktService();
List<TktVO> list = tktSvc.getAll();
pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>票券商城</title>
<%-- CSS --%>
<%@ include file="/frontend/commonCSS.file"%>

<style>
	.TravelGo-category-footer{
		padding: 15px 0px !important;
	}
	.badge {
		padding: 10px 10px 5px 10px !important;
	}

</style>

</head>
<body>

<%-- header --%>
	<%@ include file="/frontend/header.file"%>
	
	
	<!-- =======================
	Banner innerpage -->
<div class="innerpage-banner left py-7" style="background:url(https://i.imgur.com/wYzrG5n.jpg) repeat center; background-size:cover;">
  <div class="container">
    <div class="row all-text-white">
      <div class="col-md-12 align-self-center">
        <h1 class="innerpage-title text-dark">票券商城</h1>
      </div>
    </div>
  </div>
</div>
<!-- =======================
	Banner innerpage -->
	
	<section class="pt80 pb80 cruise-grid-view">
  <div class="container">
    <div class="row">
      <div class="col-lg-3 col-md-3 col-sm-12 Filter-left">
        <form method="post" action="#" autocomplete="off" >
          <div class="mb-left">
            <label for="form_dates" class="form-label">透過關鍵字查詢</label>
            <div class="form-group">
              <input class="form-control" type="text" placeholder="請搜尋關鍵字">
            </div>
          </div>
          <div class="mb-left">
            <label for="form_dates" class="form-label">透過開始日期查詢</label>
            <div class="form-group">
              <input class="form-control" type="date" placeholder="請搜尋開始日期">
            </div>
          </div>
          <div class="mb-left">
            <label for="form_dates" class="form-label">透過結束日期查詢</label>
            <div class="form-group">
              <input class="form-control" type="date" placeholder="請搜尋結束日期">
            </div>
          </div>
          
         
          <div class="mb-left">
            <label class="form-label">價格範圍</label>
            <div class="range-slider">
              <input type="range" value="300" min="1" max="1000" range="true">
              <span class="range-value">300</span> </div>
          </div>

          <div class="pb-left">
            <div id="moreFilters" class="collapse show">
              <div class="filter-block">
                <h6 class="mb-3">地點</h6>
                <div class="form-group">
                  <label for="form_neighbourhood" class="form-label">票券體驗區域</label>
                  <select id="locate" class="default-select form-control wide" name=locate>
									<option value="基隆市">基隆市</option>
									<option value="台北市">台北市</option>
									<option value="新北市">新北市</option>
									<option value="桃園市">桃園市</option>
									<option value="新竹縣">新竹縣</option>
									<option value="新竹市">新竹市</option>
									<option value="苗栗縣">苗栗縣</option>
									<option value="台中市">台中市</option>
									<option value="彰化縣">彰化縣</option>
									<option value="南投縣">南投縣</option>
									<option value="雲林縣">雲林縣</option>
									<option value="嘉義縣">嘉義縣</option>
									<option value="嘉義市">嘉義市</option>
									<option value="台南市">台南市</option>
									<option value="高雄市">高雄市</option>
									<option value="屏東縣">屏東縣</option>
									<option value="宜蘭縣">宜蘭縣</option>
									<option value="花蓮縣">花蓮縣</option>
									<option value="台東縣">台東縣</option>
									<option value="澎湖縣">澎湖縣</option>
									<option value="金門縣">金門縣</option>
									<option value="連江縣">連江縣</option>
					</select>
                </div>
                
              </div>
              
              
              <div class="filter-block">
                <h6 class="mb-3">票券種類</h6>
                <ul class="list-unstyled mb-0">
                  <li>
                    <div class="custom-control custom-checkbox">
                      <input type="checkbox" id="amenities_12" name="amenities[]" class="custom-control-input">
                      <label for="amenities_12" class="custom-control-label"> 景點門票</label>
                    </div>
                  </li>
                  <li>
                    <div class="custom-control custom-checkbox">
                      <input type="checkbox" id="amenities_13" name="amenities[]" class="custom-control-input">
                      <label for="amenities_13" class="custom-control-label">主題樂園</label>
                    </div>
                  </li>
                  <li>
                    <div class="custom-control custom-checkbox">
                      <input type="checkbox" id="amenities_14" name="amenities[]" class="custom-control-input">
                      <label for="amenities_14" class="custom-control-label">博物館展覽</label>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
            <div class="mb-left">
              <button type="submit" class="btn btn-primary btn-grad FilterBtn"> <i class="fas fa-filter mr-1"></i>查詢 </button>
            </div>
          </div>
        </form>
      </div>
      <div class="col-lg-9 col-md-9 col-sm-12">
        <div class="row">
        
        <c:forEach var="tktVO" items="${list}">
          <div class="col-lg-6 col-md-6 col-sm-12">
            <div class="listing-item ">
              <article class="TravelGo-category-listing fl-wrap">
                <div class="TravelGo-category-img"> <a href="/CGA103G1/tkt/tktDetail.do?tkt_no=${tktVO.tkt_no}"><img src="https://i.imgur.com/eZXD0nx.jpg" alt=""></a>
                  <div class="TravelGo-category-opt">
                    <div class="listing-rating card-popup-rainingvis" data-starrating2="5">
                    <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i>
                    </div>
                    <div class="rate-class-name">
                      <span>5.0</span> </div>
                  </div>
                </div>
                <div class="TravelGo-category-content fl-wrap title-sin_item">
                  <div class="TravelGo-category-content-title fl-wrap">
                    <div class="TravelGo-category-content-title-item">
                      <h3 class="title-sin_map"><a href="hotel-detailed.html">${tktVO.tkt_name}</a></h3>
                      <div class="TravelGo-category-location fl-wrap"><a href="#" class="map-item"><i class="fas fa-map-marker-alt"></i> ${tktVO.address}</a> <span>NT$ ${tktVO.price}</span> </div>
                      
                    </div>
                  </div>
                  <p>${tktVO.instruction}</p>
                  <div class="TravelGo-category-footer fl-wrap d-flex justify-content-between">
                    
                    <c:if test="${tktVO.kind == 0 }" var="true">
						<span class="badge badge-pill badge-primary text-white"># 景點門票</span>
					</c:if> 
					<c:if test="${tktVO.kind == 1 }" var="true">
						<span class="badge badge-pill badge-success text-white"># 主題樂園</span>
					</c:if> 
					<c:if test="${tktVO.kind == 2 }" var="true">
						<span class="badge badge-pill badge-info text-white"># 博物館展覽</span>
					</c:if>
					
                  <input type="hidden" name="tkt_no" value="${tktVO.tkt_no}" class="tkt_no">
                  <input type="hidden" name="count"  value="1" class="count">
                    <button  type="submit" class="TravelGo-category-price btn-grad addItem"><span>加入購物車</span><i class="fas fa-cart-arrow-down"></i></button>
                  	<!-- <span class="badge badge-pill badge-primary text-white"># 景點門票</span> -->
                  </div>
                </div>
              </article>
            </div>
          </div>
		</c:forEach>
 
  
  

        </div>
      </div>
    </div>
  </div>
</section>
	
	
	<%-- footer --%>
	<%@ include file="/frontend/footer.file"%>

	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file"%>
	
		<!-- axios test -->
 <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.20.0/axios.js"></script>
	
	
	<script>
	
	const addItem = document.querySelectorAll('.addItem');
 	const tkt_no = document.querySelectorAll('.tkt_no');
 	const count = document.querySelectorAll('.count');

 	
 	for(let i = 0; i < ${list.size()}; i++) {
 		addItem[i].addEventListener('click', () => {
 	 		axios({
 	 			"method": "post", 
 	 			"url": "/CGA103G1/cart/addCart.do",
 	 			"params": {
 	 				"count": parseInt(count[i].value),
 	 				"tkt_no": parseInt(tkt_no[i].value)
 	 			}
 	 		}).then(function (response) {
 	            //console.log(response);
 	            // 可在這加入 sweetAlert
 	        }).catch(function (error) {
 	            console.log(error);
 	        });
 	 	});
 	}
 	
 	// 載入商品單一頁面或商品列表頁就自動初始化
 	
 	function init(){ 
 		axios({
 	 		"method": "post", 
 	 		"url": "/CGA103G1/cart/initCart.do"
 		}).then(function (response) {
 	    	console.log(response);
 		}).catch(function (error) {
 	    	console.log(error);
 		});
 	}

 	// 當畫面載入時初始化
 	window.onload = init();
	
	</script>
	
</body>
</html>