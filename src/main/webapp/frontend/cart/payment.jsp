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
</head>
<body>

<% 
/* CartItemVO cartItemVO = new CartItemVO();
System.out.println("cart.jsp cartItemVO:" + cartItemVO); */
List<CartItemVO> cartItems = (List<CartItemVO>)request.getAttribute("cartItems"); 
System.out.println("cart.jsp cartItems:" + cartItems);
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
<div class="innerpage-banner left bg-overlay-dark-7 py-7" style="background:url(images/07.jpg) repeat; background-size:cover;">
  <div class="container">
    <div class="row all-text-white">
      <div class="col-md-12 align-self-center">
        <h1 class="innerpage-title">Cruise Booking</h1>
        <h6 class="subtitle">Fusce iaculis ex sed nulla commodo imperdiet. Proin sed rhoncus ligula.</h6>
        <nav aria-label="breadcrumb">
          <ol class="breadcrumb">
            <li class="breadcrumb-item active"><a href="index.html"><i class="ti-home"></i> Home</a></li>
            <li class="breadcrumb-item">Cruise Booking</li>
          </ol>
        </nav>
      </div>
    </div>
  </div>
</div>
  
  <!-- TABLE -->
  <section class="pt80">
		<div class="container">
			<div class="row">
				<div class="col-sm-12 mb-3">
					<h3 class="text-center mb-4">付款清單</h3>
					<div class="table-responsive-sm">
						<table class="table table-lg table-noborder table-striped">
							<thead class="all-text-white bg-grad">
								<tr>
									<th scope="col">#</th>
									<th scope="col">票券名稱</th>
									<th scope="col">單價</th>
									<th scope="col">數量</th>
									<th scope="col">小計</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<%-- <c:if test="${cartItems != null && (cartItemSize > 0)}"> --%>
								<%-- <c:forEach var="cartItemVO" items="${cartItems}"> --%>
								<tr>
									<th scope="row">1</th>
									<td>票券名稱</td>
									<td>價格</td>
									<td>3</td> 
									<td>總價</td>
									<td><button class="btn btn-danger">刪除</button></td>
								</tr>
								<%-- </c:forEach> --%>
								<%-- </c:if> --%>
								<%-- <c:if test="${cartItems == null && (cartItemSize == 0)}">
								<tr>
									<th scope="row">0</th>
									<td>0</td>
									<td>0</td>
									<td><input type="number" value="1"></td>
									<td>0</td>
								</tr>
								
								</c:if> --%>
								
							</tbody>
						</table>
					</div>
					
					<hr>
					
					<div class="d-flex justify-content-between"> 
						<div class="d-flex">
							<a href="<%=request.getContextPath()%>/frontend/cart/cart.jsp" class="btn btn-outline-primary">返回購物車</a>
						</div>
						<p>總計： <span 	style="color: red;">xxx</span> 元</p>
					</div>
					
					<%-- <div class="d-flex justify-content-between">
						<a href="<%=request.getContextPath()%>/frontend/cart/testTkt1.jsp" class="btn btn-outline-primary">繼續購物</a>  <!-- 返回票券列表要動態 -->
						<button type="submit" class="btn btn-primary"><i class="fas fa-arrow-circle-right"></i>前往結賬</button>
					</div> --%>
					
				</div>
			</div>
  		</div>
  </section>
  
  
  <!-- =======================
	Banner innerpage -->

<section class="pb80 booking-section login-area">
  <div class="container">
    <div class="row">
      <div class="col-lg-4 col-md-6 col-sm-12">
        <div class="listing-item ">
          <article class="TravelGo-category-listing fl-wrap">
            <div class="TravelGo-category-img"> <a href="hotel-detailed.html"><img src="https://i.imgur.com/eZXD0nx.jpg" alt=""></a>
              <div class="TravelGo-category-opt">
                <div class="listing-rating card-popup-rainingvis" data-starrating2="5"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></div>
                <div class="rate-class-name">
                  <div class="score"><strong>Very Good</strong>27 Reviews </div>
                  <span>5.0</span> </div>
              </div>
            </div>
            <div class="TravelGo-category-content fl-wrap title-sin_item">
              <div class="TravelGo-category-content-title fl-wrap">
                <div class="TravelGo-category-content-title-item">
                  <h3 class="title-sin_map"><a href="hotel-detailed.html">Asia & African Cruise</a></h3>
                  <div class="TravelGo-category-location fl-wrap"><a href="#" class="map-item"><i class="fas fa-map-marker-alt"></i> 27th Brooklyn New York, USA</a> <span>$ 200</span> </div>
                </div>
              </div>
              <p>Sed interdum metus at nisi tempor laoreet. Integer gravida orci a justo sodales.</p>
              <ul class="facilities-list fl-wrap">
                <li><i class="fas fa-wifi"></i><span>Free WiFi</span></li>
                <li><i class="fas fa-parking"></i><span>Parking</span></li>
                <li><i class="fas fa-smoking-ban"></i><span>Non-smoking Rooms</span></li>
                <li><i class="fas fa-utensils"></i><span> Restaurant</span></li>
              </ul>
              <div class="TravelGo-category-footer fl-wrap">
                <div class="TravelGo-category-price btn-grad"><span>Edit</span></div>
              </div>
              <div class="TravelGo-category-content-title-item others-details">
                <h3 class="title-sin_map"><a href="hotel-detailed.html">Others Details</a></h3>
              </div>
              <table class="table table-striped">
                <tbody>
                  <tr>
                    <td class="bookex">ROOM TYPE:</td>
                    <td>Delux</td>
                  </tr>
                  <tr>
                    <td class="bookex">FOOD & DINING:</td>
                    <td>$200</td>
                  </tr>
                  <tr>
                    <td class="bookex">CRUISE PRICE:</td>
                    <td>$300</td>
                  </tr>
                  <tr>
                    <td class="bookex">TAXES:</td>
                    <td>$300</td>
                  </tr>
                  <tr>
                    <td class="bookex"><strong>Total:</strong></td>
                    <td><strong>$1200</strong></td>
                  </tr>
                </tbody>
              </table>
            </div>
          </article>
        </div>
        <div class="listing-item">
          <article class="TravelGo-category-listing fl-wrap">
            <div class="TravelGo-category-content fl-wrap title-sin_item">
              <div class="TravelGo-category-content-title fl-wrap NeedHelp">
                <div class="TravelGo-category-content-title-item">
                  <h3 class="title-sin_map"><a href="hotel-detailed.html">Need Help?</a></h3>
                  <div class="TravelGo-category-location fl-wrap"></div>
                </div>
              </div>
              <div class="NeedhelpSection">
                <P>We would be more than happy to help you. Our team advisor are 24/7 at your service to help you.</P>
                <ul>
                  <li><span><i class="fas fa-phone-volume"></i></span> +921 215 2154 214</li>
                  <li><span><i class="far fa-envelope"></i></span> Info@travelGo.com</li>
                  <li><span><i class="fab fa-skype"></i> </span> TravelG@Skype</li>
                </ul>
              </div>
            </div>
          </article>
        </div>
      </div>
      <div class="col-lg-8 col-md-6 col-sm-12">
        <div class="row">
          <div class="col-lg-12 col-md-12 col-sm-12">
            <div class="login-box Booking-box">
              <div class="login-top">
                <h3>Your Personal Information</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
              </div>
              <form class="login-form" action="#">
                <div class="row">
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>First Name</label>
                    <input type="text" name="text" placeholder="First Name">
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>Last Name</label>
                    <input type="text" name="text" placeholder="Last Name">
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>Email</label>
                    <input type="text" name="email" placeholder="Enter your email here">
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 password">
                    <label>Phone Number</label>
                    <input type="text" name="text" placeholder="Enter Phone Number">
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>Country</label>
                    <select class="custom-select select-big mb-3">
                      <option selected="">All Country</option>
                      <option value="location1">USA</option>
                      <option value="location2">Canada</option>
                      <option value="location3">Singapure</option>
                      <option value="location5">Russia</option>
                      <option value="location6">Australia</option>
                      <option value="location8">China</option>
                    </select>
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>State</label>
                    <select class="custom-select select-big mb-3">
                      <option selected="">All locations</option>
                      <option value="location1">Chicago, US</option>
                      <option value="location2">New York, US</option>
                      <option value="location3">Seattle/Kirkland, US</option>
                      <option value="location4">Los Angles, US</option>
                      <option value="location5">Moscow, Russia</option>
                      <option value="location6">Sydney, Australia</option>
                      <option value="location7">Birmingham, UK</option>
                      <option value="location7">Manchester, UK</option>
                      <option value="location8">Beijing, China</option>
                    </select>
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>Zip Code</label>
                    <input type="text" name="text" placeholder="Zip Code">
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>Home Address</label>
                    <input type="text" name="text" placeholder="Home Address">
                  </div>
                  <div class="col-md-12 d-flex justify-content-between">
                    <div class="chqbox">
                      <input type="checkbox" name="rememberme" id="rmme">
                      <label for="rmme"> I want to receive TravelGo promotional offers in the future</label>
                    </div>
                  </div>
                  <div class="login-top cardInfo">
                    <h3>Your Card Information</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>Credit Card Type</label>
                    <select class="custom-select select-big mb-3">
                      <option selected="">select a card</option>
                    </select>
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>Card holder name</label>
                    <input type="text" name="text" placeholder="Card holder name">
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>Card number</label>
                    <input type="text" name="text" placeholder="Card number">
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>Card identification number</label>
                    <input type="text" name="text" placeholder="Card identification number">
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>Monthe</label>
                    <select class="custom-select select-big mb-3">
                      <option selected="">select a Monthe</option>
                      <option value="location8">January</option>
                      <option value="location8">Febuary</option>
                      <option value="location8">March</option>
                      <option value="location8">April</option>
                    </select>
                  </div>
                  <div class="col-lg-6 col-md-12 col-sm-12 email">
                    <label>Year</label>
                    <select class="custom-select select-big mb-3">
                      <option selected="">select a Year</option>
                      <option value="location8">2019</option>
                      <option value="location8">2020</option>
                      <option value="location8">2021</option>
                      <option value="location8">2022</option>
                    </select>
                  </div>
                  <div class="col-md-12 d-flex justify-content-between">
                    <div class="chqbox">
                      <input type="checkbox" name="rememberme" id="rmme">
                      <label for="rmme"> By continuing, you agree to the Terms and Conditions.</label>
                    </div>
                  </div>
                  <div class="col-md-12">
                    <button class="Confirm" type="submit" name="button">Confirm Booking</button>
                  </div>
                </div>
              </form>
            </div>
          </div>
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
  

</body>
</html>