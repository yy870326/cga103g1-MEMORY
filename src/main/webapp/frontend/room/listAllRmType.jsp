<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rm_type.model.*"%>
<%@ page import="com.rm_pic.model.*"%>

<jsp:useBean id="rmTypeSvc" scope="page" class="com.rm_type.model.RmTypeService" />
<jsp:useBean id="rmPicSvc" scope="page" class="com.rm_pic.model.RmPicService" />
<%-- <jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" /> --%>

<% 

//沒有搜尋過進來就顯示全部allList，有沒有搜尋過用rangedate判斷
	if (session.getAttribute("rangedate") == null) {
		List<RmTypeVO> List = rmTypeSvc.getAllRm();
		pageContext.setAttribute("List", List);
	}
		
// 日曆的日期，字串分割成start_date、end_date
	if (session.getAttribute("rangedate") != null) {
		String rangedate = (String) session.getAttribute("rangedate");
		List<String> dateList = new LinkedList<String>();
		dateList = Arrays.asList(rangedate.split(" to "));
		String start_date = dateList.get(0);
		String end_date = dateList.get(1);
		pageContext.setAttribute("start_date", start_date);
		pageContext.setAttribute("end_date", end_date);
	}
%>	
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <title>尋找下榻民宿</title>

		<%-- CSS --%>
		<%@ include file="/frontend/commonCSS.file" %>

</head>

<body>
	<!-- header -->
  <%@ include file="/frontend/header.file" %>
  <!-- room-sidebar -->
  
	<%@ include file="/frontend/roomSidebar.file" %>
	
	 <section class="pt80 pb80 cruise-grid-view">
     <div class="container">
      <div class="row">
        <div class="col-lg-4 col-md-4 col-sm-12">
          <div class="Filter-left">
<!--             <form action="#" autocomplete="off"> -->
<!--               <div class="mb-left"> -->
<!--                 <label for="form_dates" class="form-label">選擇日期</label> -->
<!--                 <div class="form-group"> -->
<!--                   <input type="text" id="rangeDate" name="rangedate" placeholder="請選擇入住期間" class="form-control" data-input> -->
<!--                    <span class="input-group-addon"></span> -->
                </div>
              </div>
              <div class="mb-left">
<!--                 <label for="form_guests" class="form-label">入住人數</label> -->
<!--                 <select class="custom-select select-big "> -->
<!--                   <option selected="">入住人數</option> -->
<!--                   <option value="guests_0">1位 </option> -->
<!--                   <option value="guests_1">2位 </option> -->
<!--                   <option value="guests_2">3位 </option> -->
<!--                   <option value="guests_3">4位 </option> -->
<!--                 </select> -->
              </div>
              <div class="mb-left">
<!--                 <label for="form_type" class="form-label">預訂房間數量</label> -->
<!--                 <select class="custom-select select-big "> -->
<!--                   <option value="type_0"> 1間 </option> -->
<!--                   <option value="type_1"> 2間 </option> -->
<!--                   <option value="type_2"> 3間 </option> -->
<!--                   <option value="type_2"> 4間 </option> -->
<!--                 </select> -->
              </div>
              <div class="mb-left">
	
        <div class="col-lg-12 col-md-8 col-sm-12">
          <div class="resultBar barSpaceAdjust">
            <h2>We found <span>7</span> Results for you</h2>
            <ul class="list-inline">
              <li class="active"><a href="cruise-grid-view.html"><i class="fa fa-th" aria-hidden="true"></i></a></li>
              <li><a href="hotel-list-view.html"><i class="fa fa-th-list" aria-hidden="true"></i></a></li>
            </ul>
          </div>
          <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12">
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <div class="row listroBox">
                    <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12 Nopadding">
                      <figure> <a href=s class="wishlist_bt"></a> <a href="hotel-detailed.html"><img
                            src="images/hotels/room6.jpg" class="img-fluid" alt="">
                          <div class="read_more"><span>Read more</span></div>
                        </a> </figure>
                    </div>
                    <div class="col-lg-7 col-md-7 col-sm-6 col-xs-12 Nopadding">
                      <div class="listroBoxmain">
                        <h3><a href="hotel-detailed.html">Blue Hill Restaurant</a></h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do</p>
                        <a class="address" href="">Get directions</a>
                      </div>
                      <ul>
                        <li><span class="Ropen">Now Open</span></li>
                        <li>
                          <div class="R_retings"><span>Blue Hill<em>122 Reviews</em></span><strong>8.2</strong></div>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
            </div>





            <div class="col-lg-12 col-md-12 col-sm-12">
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <div class="row listroBox">
                    <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12 Nopadding">
                      <figure> <a href="hotel-detailed.html" class="wishlist_bt"></a> <a href="hotel-detailed.html"><img
                            src="images/hotels/room1.jpg" class="img-fluid" alt="">
                          <div class="read_more"><span>Read more</span></div>
                        </a> </figure>
                    </div>
                    <div class="col-lg-7 col-md-7 col-sm-6 col-xs-12 Nopadding">
                      <div class="listroBoxmain">
                        <h3><a href="hotel-detailed.html">Blue Hill Restaurant</a></h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do</p>
                        <a class="address" href="">Get directions</a>
                      </div>
                      <ul>
                        <li><span class="Ropen">Now Open</span></li>
                        <li>
                          <div class="R_retings"><span>Blue Hill<em>122 Reviews</em></span><strong>8.2</strong></div>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
            </div>


            <div class="col-lg-12 col-md-12 col-sm-12">
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <div class="row listroBox">
                    <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12 Nopadding">
                      <figure> <a href="hotel-detailed.html" class="wishlist_bt"></a> <a href="hotel-detailed.html"><img
                            src="images/hotels/room2.jpg" class="img-fluid" alt="">
                          <div class="read_more"><span>Read more</span></div>
                        </a> </figure>
                    </div>
                    <div class="col-lg-7 col-md-7 col-sm-6 col-xs-12 Nopadding">
                      <div class="listroBoxmain">
                        <h3><a href="hotel-detailed.html">Blue Hill Restaurant</a></h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do</p>
                        <a class="address" href="">Get directions</a>
                      </div>
                      <ul>
                        <li><span class="Ropen">Now Open</span></li>
                        <li>
                          <div class="R_retings"><span>Blue Hill<em>122 Reviews</em></span><strong>8.2</strong></div>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
            </div>


            <div class="col-lg-12 col-md-12 col-sm-12">
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <div class="row listroBox">
                    <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12 Nopadding">
                      <figure> <a href="hotel-detailed.html" class="wishlist_bt"></a> <a href="hotel-detailed.html"><img
                            src="images/hotels/room3.jpg" class="img-fluid" alt="">
                          <div class="read_more"><span>Read more</span></div>
                        </a> </figure>
                    </div>
                    <div class="col-lg-7 col-md-7 col-sm-6 col-xs-12 Nopadding">
                      <div class="listroBoxmain">
                        <h3><a href="hotel-detailed.html">Blue Hill Restaurant</a></h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do</p>
                        <a class="address" href="">Get directions</a>
                      </div>
                      <ul>
                        <li><span class="Ropen">Now Open</span></li>
                        <li>
                          <div class="R_retings"><span>Blue Hill<em>122 Reviews</em></span><strong>8.2</strong></div>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="col-lg-12 col-md-12 col-sm-12">
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <div class="row listroBox">
                    <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12 Nopadding">
                      <figure> <a href="hotel-detailed.html" class="wishlist_bt"></a> <a href="hotel-detailed.html"><img
                            src="images/hotels/room4.jpg" class="img-fluid" alt="">
                          <div class="read_more"><span>Read more</span></div>
                        </a> </figure>
                    </div>
                    <div class="col-lg-7 col-md-7 col-sm-6 col-xs-12 Nopadding">
                      <div class="listroBoxmain">
                        <h3><a href="hotel-detailed.html">Blue Hill Restaurant</a></h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do</p>
                        <a class="address" href="">Get directions</a>
                      </div>
                      <ul>
                        <li><span class="Ropen">Now Open</span></li>
                        <li>
                          <div class="R_retings"><span>Blue Hill<em>122 Reviews</em></span><strong>8.2</strong></div>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
            </div>




            <div class="col-lg-12 col-md-12 col-sm-12">
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <div class="row listroBox">
                    <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12 Nopadding">
                      <figure> <a href="hotel-detailed.html" class="wishlist_bt"></a> <a href="hotel-detailed.html"><img
                            src="images/hotels/room5.jpg" class="img-fluid" alt="">
                          <div class="read_more"><span>Read more</span></div>
                        </a> </figure>
                    </div>
                    <div class="col-lg-7 col-md-7 col-sm-6 col-xs-12 Nopadding">
                      <div class="listroBoxmain">
                        <h3><a href="hotel-detailed.html">Blue Hill Restaurant</a></h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do</p>
                        <a class="address" href="">Get directions</a>
                      </div>
                      <ul>
                        <li><span class="Ropen">Now Open</span></li>
                        <li>
                          <div class="R_retings"><span>Blue Hill<em>122 Reviews</em></span><strong>8.2</strong></div>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
            </div>



            <div class="col-lg-12 col-md-12 col-sm-12">
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <div class="row listroBox">
                    <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12 Nopadding">
                      <figure> <a href="hotel-detailed.html" class="wishlist_bt"></a> <a href="hotel-detailed.html"><img
                            src="images/hotels/room7.jpg" class="img-fluid" alt="">
                          <div class="read_more"><span>Read more</span></div>
                        </a> </figure>
                    </div>
                    <div class="col-lg-7 col-md-7 col-sm-6 col-xs-12 Nopadding">
                      <div class="listroBoxmain">
                        <h3><a href="hotel-detailed.html">Blue Hill Restaurant</a></h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do</p>
                        <a class="address" href="">Get directions</a>
                      </div>
                      <ul>
                        <li><span class="Ropen">Now Open</span></li>
                        <li>
                          <div class="R_retings"><span>Blue Hill<em>122 Reviews</em></span><strong>8.2</strong></div>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
            </div>


          </div>



          <div class="paginationCommon blogPagination categoryPagination">
            <nav aria-label="Page navigation">
              <ul class="pagination">
                <li> <a href="#" aria-label="Previous"> <span aria-hidden="true"><i class="fa fa-angle-left"
                        aria-hidden="true"></i></span> </a> </li>
                <li class="active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li> <a href="#" aria-label="Next"> <span aria-hidden="true"><i class="fa fa-angle-right"
                        aria-hidden="true"></i></span> </a> </li>
              </ul>
            </nav>
          </div>



        </div>
      </div>
    </div>
  </section>
    <!-- Search -->

  	<!--  footer  -->
	<%@ include file="/frontend/footer.file" %>
  	<!--  footer  -->

	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<%@ include file="/frontend/commonJS.file" %>
</body>
</html>