<%@page import="com.ac.model.AcVO"%>
<%@page import="com.ac.model.AcServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.ac.model.*"%>

<%

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章詳細頁</title>

 	<script src="assets/js/jquery.min.js" type="text/javascript"></script>

	<%-- CSS --%>
	<%@ include file="/frontend/commonCSS.file" %>

</head>
<body>
	<%-- header --%>
	<%@ include file="/frontend/header.file" %>
	
	
	
	<section class="Blog-list pt80 pb80 blog-single-section">
  <div class="container">
    <div class="row">
	      <div class="col-lg-8 col-md-12 col-xs-12">
	        <div class="blog-content">
	          <div class="post format-standard-image">
	            <div class="entry-media"> <img src="<%=request.getContextPath()%>/getOneAcImage?action=acImgList&acNo=${acVO.ac_no}" alt="圖片"></div>
	            <ul class="entry-meta">
	              <li><i class="far fa-clock"></i>${acVO.ac_time}</li>
  	              <li id="acType" value="${acVO.ac_type_no}"><i class="fas fa-comments"></i></li>              
	            </ul>
	            <h2>${acVO.ac_title}</h2>
	            <div id="content">
		            <p>${acVO.ac_content}</p>
	            </div>
	          </div>
	         
	          
	          <div class="author-box">
	            <div class="author-avatar"> <a href="#" target="_blank"><img src="images/author.jpg" alt="作者"></a> </div>
	            <div class="author-content"> <a href="#" class="author-name">作者姓名(會員姓名)</a></div>
	          </div>
	          <!-- end author-box -->
	          
	          <div class="comments-area">
	            <div class="comments-section">
	              <h3 class="comments-title">留言區</h3>
	              <ol class="comments">
	                <li class="comment even thread-even depth-1" id="comment-1">
	                  <ul class="children">
	                    <li class="comment">
	                      <div>
	                        <div class="comment-main-area">
	                          <div class="comment-wrapper">
	                            <div class="comments-meta">
									<div class="comment-image"><img src="images/img-22.jpg" alt="回覆會員照片"></div>
	                             	<h4>留言者姓名<span class="comments-date">留言時間</span></h4>
	                            </div>
	                            <div class="comment-area">
	                              <p>留言段落留言段落留言段落留言段落留言段落留言段落</p>
	                            </div>
	                          </div>
	                        </div>
	                      </div>
	                    </li>
	                  </ul>
	                </li>        
	              </ol>
	            </div>
	            <!-- end comments-section -->
	            
	            <div class="comment-respond">
	              <h3 class="comment-reply-title">留言回覆</h3>
	              <form method="post" id="commentform" class="comment-form">
	                <div class="form-textarea">
	                  <textarea id="comment" placeholder="寫下你的評論"></textarea>
	                </div>
	                <div class="form-submit">
	                  <input id="submit" value="Post" type="submit">
	                </div>
	              </form>
	            </div>
	          </div>
	          <!-- end comments-area --> 
	        </div>
	      </div>
	      
	      <div class="col-lg-4 col-md-12 col-xs-12">
	        <div class="blog-sidebar">
	          <div class="widget">
	            <h3>修改文章內容</h3>
	            <form method="get" >
	              <div>
	              		<a href="<%=request.getContextPath()%>/updateAc?action=alterAc&acNo=${acVO.ac_no}"
	              		class="btn btn-success" >
	              		修改文章
	              		 </a>
	              </div>
	            </form>
	          </div>
	      </div>
	   	</div>
 	</div>
  </div>
</section>
	
	<script type="text/javascript">
		let typeArr = ["競技", "運動", "遊記", "行程", "交通", "住宿", "景點", "購物", "飲食"]
		let acType = document.getElementById("acType")
	
		for (let i = 0; i < typeArr.length; i++) {
		    if(acType.value-1 == i){
		        acType.innerHTML = typeArr[i];
		        break;
		    }else{
		        acType.innerHTML = "無此種類";
		    }
		}
						
	</script>
	
	
	
	
	
	
	<%-- footer --%>
	<%@ include file="/frontend/footer.file" %>
  	
  	<%-- commonJS --%>
	<%@ include file="/frontend/commonJS.file" %>
	
</body>
</html>