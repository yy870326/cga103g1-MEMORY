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
<title>票券1 - Memory</title>
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
        <ul class="nav nav-tabs tab-line">
          <li class="nav-item"> <a class="nav-link active" data-toggle="tab" href="#tab-de-1"> 票券內容說明 </a> </li>
          <!-- <li class="nav-item"> <a class="nav-link active" data-toggle="tab" href="#tab-de-2"> Availability </a> </li> -->
         <!--  <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#tab-de-3"> Amenities </a> </li>
          <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#tab-de-4"> Calendar </a> </li> -->
          <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#tab-de-5"> 照片 </a> </li>
          <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#tab-de-6"> 評價 </a> </li>
        </ul>
        <div class="tab-content">
          <div class="tab-pane active" id="tab-de-1">
            <div class="text-block NopaddingDetails">
              <h5 class="mb-4">101 景觀台門票</h5>
              <p class="text-muted font-weight-light">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
              <p class="text-muted font-weight-light">Also in the apartment:</p>
              <ul class="text-muted font-weight-light roomlist">
                <li>Lorem ipsum dolor sit amet, consectetur</li>
                <li>dolore eu fugiat nulla pariatur</li>
                <li>sed do eiusmod tempor incididunt</li>
              </ul>
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
                <div class="col-lg-4 col-6 px-1 mb-2"><a href="#"><img src="https://i.imgur.com/eZXD0nx.jpg" alt="..." class="img-fluid"></a></div>
                <div class="col-lg-4 col-6 px-1 mb-2"><a href="#"><img src="https://i.imgur.com/1ZxerUF.jpg" alt="..." class="img-fluid"></a></div>
                <div class="col-lg-4 col-6 px-1 mb-2"><a href="#"><img src="https://i.imgur.com/DlbRgQe.jpg" alt="..." class="img-fluid"></a></div>
              </div>
            </div>
            <!-- Amenities-->
            <!-- <div class="text-block">
              <h5 class="mb-4">Amenities</h5>
              <div class="row">
                <div class="col-md-6">
                  <ul class="list-unstyled text-muted">
                    <li class="mb-2"><i class="fa fa-wifi text-secondary w-1rem mr-3 text-center"></i> <span class="text-sm">Wifi</span></li>
                    <li class="mb-2"><i class="fa fa-tv text-secondary w-1rem mr-3 text-center"></i> <span class="text-sm">Cable TV</span></li>
                    <li class="mb-2"><i class="fa fa-snowflake text-secondary w-1rem mr-3 text-center"></i> <span class="text-sm">Air conditioning</span></li>
                    <li class="mb-2"><i class="fa fa-thermometer-three-quarters text-secondary w-1rem mr-3 text-center"></i> <span class="text-sm">Heating</span></li>
                  </ul>
                </div>
                <div class="col-md-6">
                  <ul class="list-unstyled text-muted">
                    <li class="mb-2"><i class="fa fa-bath text-secondary w-1rem mr-3 text-center"></i><span class="text-sm">Toiletteries</span></li>
                    <li class="mb-2"><i class="fa fa-utensils text-secondary w-1rem mr-3 text-center"></i><span class="text-sm">Equipped Kitchen</span></li>
                    <li class="mb-2"><i class="fa fa-laptop text-secondary w-1rem mr-3 text-center"></i><span class="text-sm">Desk for work</span></li>
                    <li class="mb-2"><i class="fa fa-tshirt text-secondary w-1rem mr-3 text-center"></i><span class="text-sm">Washing machine</span></li>
                  </ul>
                </div>
              </div>
            </div> -->
            <div class="text-block">
              <h5 class="mb-4">評價 </h5>
              <div class="media d-block d-sm-flex review">
                <!-- <div class="text-md-center mr-4 mr-xl-5"><img src="images/img-22.jpg" alt="Padmé Amidala" class="avatar avatar-xl p-2 mb-2"></div> -->
                <div class="media-body">
                  <h6 class="mt-2 mb-1">Deho Smith</h6>
                  <div class="mb-2"><i class="fa fa-xs fa-star text-primary"></i><i class="fa fa-xs fa-star text-primary"></i><i class="fa fa-xs fa-star text-primary"></i><i class="fa fa-xs fa-star text-primary"></i><i class="fa fa-xs fa-star text-primary"></i> </div>
                  <p class="text-muted text-sm">ut labore et dolore magna aliquao laboris nisi ut aliquip ex ea commodo consequat. </p>
                </div>
              </div>
              <div class="media d-block d-sm-flex review">
                <!-- <div class="text-md-center mr-4 mr-xl-5"><img src="images/img-11.jpg" alt="Jabba Hut" class="avatar avatar-xl p-2 mb-2"></div> -->
                <div class="media-body">
                  <h6 class="mt-2 mb-1">S. M Smithrs</h6>
                  <div class="mb-2"><i class="fa fa-xs fa-star text-primary"></i><i class="fa fa-xs fa-star text-primary"></i><i class="fa fa-xs fa-star text-primary"></i><i class="fa fa-xs fa-star text-primary"></i><i class="fa fa-xs fa-star text-primary"></i> </div>
                  <p class="text-muted text-sm">ut labore etexercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </p>
                </div>
              </div>
              <!-- <div class="rebiew_section">
                <div id="leaveReview" class="mt-4 collapse show" style="">
                  <h5 class="mb-4">Leave a review</h5>
                  <form id="contact-form" method="get" action="#" class="form">
                    <div class="row">
                      <div class="col-sm-6">
                        <div class="form-group">
                          <input type="text" name="name" id="name" placeholder="Enter your name" required class="form-control">
                        </div>
                      </div>
                      <div class="col-sm-6">
                        <div class="form-group">
                          <select name="rating" id="rating" class="custom-select focus-shadow-0">
                            <option value="5">★★★★★ (5/5)</option>
                            <option value="4">★★★★☆ (4/5)</option>
                            <option value="3">★★★☆☆ (3/5)</option>
                            <option value="2">★★☆☆☆ (2/5)</option>
                            <option value="1">★☆☆☆☆ (1/5)</option>
                          </select>
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <input type="email" name="email" id="email" placeholder="Enter your  email" required class="form-control">
                    </div>
                    <div class="form-group">
                      <textarea rows="4" name="review" id="review" placeholder="Enter your review" required class="form-control"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Post review</button>
                  </form>
                </div>
              </div> -->
            </div>
          </div>
         
            

          <div class="tab-pane" id="tab-de-5">
            <div class="text-block NopaddingDetails"> 
              <!-- Gallery-->
              <h5 class="mb-4">照片</h5>
              <div class="row gallery ml-n1 mr-n1">
                <div class="col-lg-4 col-6 px-1 mb-2"><a href="#"><img src="https://i.imgur.com/eZXD0nx.jpg" alt="..." class="img-fluid"></a></div>
                <div class="col-lg-4 col-6 px-1 mb-2"><a href="#"><img src="https://i.imgur.com/1ZxerUF.jpg" alt="..." class="img-fluid"></a></div>
                <div class="col-lg-4 col-6 px-1 mb-2"><a href="#"><img src="https://i.imgur.com/DlbRgQe.jpg" alt="..." class="img-fluid"></a></div>
              </div>
            </div>
          </div>
          <div class="tab-pane" id="tab-de-6">
            <div class="text-block NopaddingDetails">
              <h5 class="mb-4">評價</h5>
              <div class="media d-block d-sm-flex review">
                <!-- <div class="text-md-center mr-4 mr-xl-5"><img src="images/img-22.jpg" alt="Padmé Amidala" class="avatar avatar-xl p-2 mb-2"></div> -->
                <div class="media-body">
                  <h6 class="mt-2 mb-1">Deho Smith</h6>
                  <div class="mb-2"><i class="fa fa-xs fa-star text-primary"></i><i class="fa fa-xs fa-star text-primary"></i><i class="fa fa-xs fa-star text-primary"></i><i class="fa fa-xs fa-star text-primary"></i><i class="fa fa-xs fa-star text-primary"></i> </div>
                  <p class="text-muted text-sm">ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatiuat. </p>
                </div>
              </div>
              <div class="media d-block d-sm-flex review">
                <!-- <div class="text-md-center mr-4 mr-xl-5"><img src="images/img-11.jpg" alt="Jabba Hut" class="avatar avatar-xl p-2 mb-2"></div> -->
                <div class="media-body">
                  <h6 class="mt-2 mb-1">S. M Smithrs</h6>
                  <div class="mb-2"><i class="fa fa-xs fa-star text-primary"></i><i class="fa fa-xs fa-star text-primary"></i><i class="fa fa-xs fa-star text-primary"></i><i class="fa fa-xs fa-star text-primary"></i><i class="fa fa-xs fa-star text-primary"></i> </div>
                  <p class="text-muted text-sm">ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrdo consequat. </p>
                </div>
              </div>
              <div class="rebiew_section">
                <div id="leaveReview" class="mt-4 collapse show" style="">
                  <h5 class="mb-4">Leave a review</h5>
                  <form id="contact-form" method="get" action="#" class="form">
                    <div class="row">
                      <div class="col-sm-6">
                        <div class="form-group">
                          <input type="text" name="name" id="name" placeholder="Enter your name" required class="form-control">
                        </div>
                      </div>
                      <div class="col-sm-6">
                        <div class="form-group">
                          <select name="rating" id="rating" class="custom-select focus-shadow-0">
                            <option value="5">★★★★★ (5/5)</option>
                            <option value="4">★★★★☆ (4/5)</option>
                            <option value="3">★★★☆☆ (3/5)</option>
                            <option value="2">★★☆☆☆ (2/5)</option>
                            <option value="1">★☆☆☆☆ (1/5)</option>
                          </select>
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <input type="email" name="email" id="email" placeholder="Enter your  email" required class="form-control">
                    </div>
                    <div class="form-group">
                      <textarea rows="4" name="review" id="review" placeholder="Enter your review" required class="form-control"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Post review</button>
                  </form>
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
        <h5 class="mb-4">101 景觀台門票</h5>
          <p class="text-muted"><span class="text-primary h2">500</span> / 人</p>
          <hr class="my-4">
            <div class="form-group">
            <div class="form-group mb-4">
              <label for="guests" class="form-label">票券張數</label>
              <!-- 假數量 之後會接到真的票券資料用 el 綁定數量 -->
              <input type="number" value="1" id="guests" class="form-control">
<!--               <input type="number" name="count" id="guests" class="form-control"> -->
            </div>
              <!-- 假資料 真的資料會從資料庫撈 tkt_no -->
               <input type="hidden" name="tkt_no"  value="3" class="tkt_no"> 
			   <!-- <input type="hidden" name="tkt_name"  value="201 景觀台門票">
			   <input type="hidden" name="price"  value="500"> -->
			   <input type="hidden" name="count"  value="1" class="count">
              <button type="submit" class="btn btn-primary btn-block addItem">加入購物車<i class="fas fa-cart-arrow-down"></i></button>
			   <input type="hidden" name="action"	value="addItem">
			   <!-- <input type="hidden" name="count"  value="2"> -->
			   <!-- <input type="hidden" name="action"	value="addItem"> -->
            </div>
         <!--  </form> -->
          
          
          <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cart/initCart.do">
			<input type="submit" class="btn btn-primary btn-block" value="測試 cookie">
          	<input type="hidden" name="action" value="init">
			<!-- <input type="hidden" name="action"	value="init"> -->
 		 </FORM>
 		 
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
<!-- <section class="Categories pt80 pb60 hotelsamilar">
  <div class="container">
    <div class="row mb-5">
      <div class="col-md-8">
        <p class="subtitle text-secondary nopadding">Similar Hotels</p>
        <h1 class="paddtop1 font-weight lspace-sm">You may also like </h1>
      </div>
      <div class="col-md-4 d-lg-flex align-items-center justify-content-end"><a href="" class="blist text-sm ml-2"> See all Hotels<i class="fas fa-angle-double-right ml-2"></i></a></div>
    </div>
    <div class="row">
      <div class="swiper-container guides-slider-popular"> 
        Additional required wrapper
        <div class="swiper-wrapper"> 
          Slides
          
            <div class="col-md-4 col-sm-6 col-xs-12">
            <div class="listroBox">
              <figure> <a href="hotel-detailed.html" class="wishlist_bt"></a> <a href="hotel-detailed.html"><img src="images/hotels/room2.jpg" class="img-fluid" alt="" >
                <div class="read_more"><span>Read more</span></div>
                </a> </figure>
              <div class="listroBoxmain">
                <h3><a href="hotel-detailed.html">Modern, Well-Appointed Room</a></h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do</p>
                <a class="address" href="">Get directions</a> </div>
              <ul>
                <li>
                  <p class="card-text text-muted"><span class="h4 text-primary">$80</span> / night</p>
                </li>
                <li>
                  <div class="R_retings">
                    <div class="list-rat-ch list-room-rati"> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> </div>
                  </div>
                </li>
              </ul>
            </div>
          </div>        
		 
		 
          <div class="col-md-4 col-sm-6 col-xs-12">
            <div class="listroBox">
              <figure> <a href="hotel-detailed.html" class="wishlist_bt"></a> <a href="hotel-detailed.html"><img src="images/hotels/room3.jpg" class="img-fluid" alt="" >
                <div class="read_more"><span>Read more</span></div>
                </a> </figure>
              <div class="listroBoxmain">
                <h3><a href="hotel-detailed.html">Modern, Well-Appointed Room</a></h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do</p>
                <a class="address" href="">Get directions</a> </div>
              <ul>
                <li>
                  <p class="card-text text-muted"><span class="h4 text-primary">$80</span> / night</p>
                </li>
                <li>
                  <div class="R_retings">
                    <div class="list-rat-ch list-room-rati"> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> </div>
                  </div>
                </li>
              </ul>
            </div>
          </div> 



          <div class="col-md-4 col-sm-6 col-xs-12">
            <div class="listroBox">
              <figure> <a href="hotel-detailed.html" class="wishlist_bt"></a> <a href="hotel-detailed.html"><img src="images/hotels/room4.jpg" class="img-fluid" alt="" >
                <div class="read_more"><span>Read more</span></div>
                </a> </figure>
              <div class="listroBoxmain">
                <h3><a href="hotel-detailed.html">Modern, Well-Appointed Room</a></h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do</p>
                <a class="address" href="">Get directions</a> </div>
              <ul>
                <li>
                  <p class="card-text text-muted"><span class="h4 text-primary">$80</span> / night</p>
                </li>
                <li>
                  <div class="R_retings">
                    <div class="list-rat-ch list-room-rati"> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> </div>
                  </div>
                </li>
              </ul>
            </div>
          </div> 



          <div class="col-md-4 col-sm-6 col-xs-12">
            <div class="listroBox">
              <figure> <a href="hotel-detailed.html" class="wishlist_bt"></a> <a href="hotel-detailed.html"><img src="images/hotels/room5.jpg" class="img-fluid" alt="" >
                <div class="read_more"><span>Read more</span></div>
                </a> </figure>
              <div class="listroBoxmain">
                <h3><a href="hotel-detailed.html">Modern, Well-Appointed Room</a></h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do</p>
                <a class="address" href="">Get directions</a> </div>
              <ul>
                <li>
                  <p class="card-text text-muted"><span class="h4 text-primary">$80</span> / night</p>
                </li>
                <li>
                  <div class="R_retings">
                    <div class="list-rat-ch list-room-rati"> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> </div>
                  </div>
                </li>
              </ul>
            </div>
          </div> 



          <div class="col-md-4 col-sm-6 col-xs-12">
            <div class="listroBox">
              <figure> <a href="hotel-detailed.html" class="wishlist_bt"></a> <a href="hotel-detailed.html"><img src="images/hotels/room6.jpg" class="img-fluid" alt="" >
                <div class="read_more"><span>Read more</span></div>
                </a> </figure>
              <div class="listroBoxmain">
                <h3><a href="hotel-detailed.html">Modern, Well-Appointed Room</a></h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do</p>
                <a class="address" href="">Get directions</a> </div>
              <ul>
                <li>
                  <p class="card-text text-muted"><span class="h4 text-primary">$80</span> / night</p>
                </li>
                <li>
                  <div class="R_retings">
                    <div class="list-rat-ch list-room-rati"> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i> </div>
                  </div>
                </li>
              </ul>
            </div>
          </div> 
		  
		 
		 
        </div>
        <div class="swiper-pagination d-md-none"> </div>
      </div>
    </div>
  </div>
</section> -->
  
  
  
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
 	
 	function init(){ 
 		axios({
 	 		"method": "post", 
 	 		"url": "/CGA103G1/cart/initCart.do"
 		}).then(function (response) {
 	    	//console.log(response);
 		}).catch(function (error) {
 	    	console.log(error);
 		});
 	}

 	// 當畫面載入時初始化
 	window.onload = init();
 
 </script>
  

</body>
</html>