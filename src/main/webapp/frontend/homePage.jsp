<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <title>美陌旅</title>

		<%-- CSS --%>
		<%@ include file="/frontend/commonCSS.file" %>

</head>

<body>
	<!-- header -->
	<%@ include file="/frontend/header.file" %>

  	<!-- Main Banner -->
	<%@ include file="/frontend/banner.file" %> 
    <!-- Main banner -->

	
    <section class="mt-lg-n9 mt-sm-0 pb-0 z-index-9 booking-search">
      <div class="container">
        <div class="row shadow border-radius-3">
          <div class="col-md-12 np">
            <div class="feature-box h-100">
              <div class="tab_container">
                <input id="tab1" type="radio" name="tabs" checked />
                <label for="tab1"
                  ><i class="fas fa-utensils"></i><span>訂房</span></label
                >
                <input id="tab2" type="radio" name="tabs" />
                <label for="tab2"
                  ><i class="fas fa-helicopter"></i><span>票券</span></label
                >
                <input id="tab3" type="radio" name="tabs" />
                <label for="tab3"
                  ><i class="fas fa-car-side"></i><span>揪團</span></label
                >
                <input id="tab4" type="radio" name="tabs" />

                <section id="content1" class="tab-content">
                  <div class="row">
                    <div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 padding8">
                      <div class="form-group">
                        <span class="fas fa-map-marker-alt"></span>
                        <input
                          class="form-control"
                          type="text"
                          placeholder="City, Point of Interest or U.S. Zip Code"
                        />
                      </div>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12 padding8">
                      <div class="form-group">
                        <span class="far fa-calendar-alt"></span>
                        <input
                          class="form-control"
                          type="text"
                          id="datepicker"
                          autocomplete="off"
                          placeholder="Check-in"
                        />
                      </div>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12 padding8">
                      <div class="form-group">
                        <span class="far fa-calendar-alt"></span>
                        <input
                          class="form-control"
                          type="text"
                          id="datepicker-out"
                          autocomplete="off"
                          placeholder="Check-out"
                        />
                      </div>
                    </div>
                    <div class="col-lg-1 col-md-6 col-sm-6 col-xs-12 padding8">
                      <div class="form-group">
                        <select class="custom-select select-big">
                          <option selected="">Rooms</option>
                          <option value="location1">01</option>
                          <option value="location2">02</option>
                          <option value="location3">03</option>
                          <option value="location4">04</option>
                          <option value="location5">05</option>
                        </select>
                      </div>
                    </div>
                    <div class="col-lg-1 col-md-6 col-sm-6 col-xs-12 padding8">
                      <div class="form-group">
                        <select class="custom-select select-big">
                          <option selected="">Guests</option>
                          <option value="location1">01</option>
                          <option value="location2">02</option>
                          <option value="location3">03</option>
                          <option value="location4">04</option>
                          <option value="location5">05</option>
                        </select>
                      </div>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12 padding8">
                      <div class="form-group">
                        <button
                          class="btn btn-primary btn-lg btn-grad"
                          type="submit"
                        >
                          Search
                        </button>
                      </div>
                    </div>
                  </div>
                </section>
                <section id="content2" class="tab-content">
                  <div class="row">
                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 padding8">
                      <div class="form-group">
                        <span class="fas fa-map-marker-alt"></span>
                        <input
                          class="form-control"
                          type="text"
                          placeholder="From : City, Airport, U.S. Zip"
                        />
                      </div>
                    </div>
                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 padding8">
                      <div class="form-group">
                        <span class="fas fa-map-marker-alt"></span>
                        <input
                          class="form-control"
                          type="text"
                          placeholder="To : City, Airport, U.S. Zip"
                        />
                      </div>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12 padding8">
                      <div class="form-group">
                        <span class="far fa-calendar-alt"></span>
                        <input
                          class="form-control"
                          type="text"
                          id="datepicker-1"
                          autocomplete="off"
                          placeholder="Departing"
                        />
                      </div>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12 padding8">
                      <div class="form-group">
                        <span class="far fa-calendar-alt"></span>
                        <input
                          class="form-control"
                          type="text"
                          id="datepicker-2"
                          autocomplete="off"
                          placeholder="Returning"
                        />
                      </div>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12 padding8">
                      <div class="form-group">
                        <button
                          class="btn btn-primary btn-lg btn-grad"
                          type="submit"
                        >
                          Search
                        </button>
                      </div>
                    </div>
                  </div>
                </section>
                <section id="content3" class="tab-content">
                  <div class="row">
                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 padding8">
                      <div class="form-group">
                        <span class="fas fa-map-marker-alt"></span>
                        <input
                          class="form-control"
                          type="text"
                          placeholder="Pick-up Location"
                        />
                      </div>
                    </div>
                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 padding8">
                      <div class="form-group">
                        <span class="fas fa-map-marker-alt"></span>
                        <input
                          class="form-control"
                          type="text"
                          placeholder="Drop-off Location"
                        />
                      </div>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12 padding8">
                      <div class="form-group">
                        <span class="far fa-calendar-alt"></span>
                        <input
                          class="form-control"
                          type="text"
                          id="datepicker-3"
                          autocomplete="off"
                          placeholder="Pick-up Date"
                        />
                      </div>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12 padding8">
                      <div class="form-group">
                        <span class="far fa-calendar-alt"></span>
                        <input
                          class="form-control"
                          type="text"
                          id="datepicker-4"
                          autocomplete="off"
                          placeholder="Drop-ff Date"
                        />
                      </div>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12 padding8">
                      <div class="form-group">
                        <button
                          class="btn btn-primary btn-lg btn-grad"
                          type="submit"
                        >
                          Search
                        </button>
                      </div>
                    </div>
                  </div>
                </section>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  
<!-- 額外Section 區域  -->
 


<!-- 額外Section 區域  end-->


    <!-- Search -->
    <section class="bg-light pattern-overlay-1-dark">
      <div class="container">
        <div class="col-md-12 col-lg-9 mx-auto p-4 p-sm-5">
          <div class="text-center px-0 px-sm-5">
            <p class="mb-3 lead">想尋找其他東西嗎？</p>
            <form>
              <div class="input-group">
                <input
                  class="form-control border-radius-right-0 border-right-0"
                  type="text"
                  name="search"
                  placeholder="What are you looking for?"
                />
                <button
                  type="button"
                  class="btn btn-grad border-radius-left-0 mb-0"
                >
                  Search
                </button>
              </div>
            </form>
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