<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
String cp = request.getContextPath();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Amado - Furniture Ecommerce Template | Shop</title>
<!-- Favicon  -->
<link rel="icon" href="/semi/project/img/core-img/favicon.ico">

<!-- Core Style CSS -->
<link rel="stylesheet" href="/semi/project/css/core-style.css">
<link rel="stylesheet" href="style.css">
</head>
<body>
    <!-- Search Wrapper Area Start -->
    <div class="search-wrapper section-padding-100">
        <div class="search-close">
            <i class="fa fa-close" aria-hidden="true"></i>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="search-content">
                        <form action="#" method="get">
                            <input type="search" name="search" id="search" placeholder="Type your keyword...">
                            <button type="submit"><img src="/semi/project/img/core-img/search.png" alt=""></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Search Wrapper Area End -->

    <!-- ##### Main Content Wrapper Start ##### -->
    <div class="main-content-wrapper d-flex clearfix">

        <!-- Mobile Nav (max width 767px)-->
        <div class="mobile-nav">
            <!-- Navbar Brand -->
            <div class="amado-navbar-brand">
                <a href="/semi/project/main.jsp"><img src="/semi/project/img/core-img/logo.png" alt=""></a>
            </div>
            <!-- Navbar Toggler -->
            <div class="amado-navbar-toggler">
                <span></span><span></span><span></span>
            </div>
        </div>

        <!-- Header Area Start -->
        <header class="header-area clearfix">
            <!-- Close Icon -->
            <div class="nav-close">
                <i class="fa fa-close" aria-hidden="true"></i>
            </div>
            <!-- Logo -->
            <div class="logo">
                <a href="/semi/project/main.jsp"><img src="/semi/project/img/core-img/logo.png" alt=""></a>
            </div>
            <!-- Amado Nav -->
            <nav class="amado-nav">
                <ul>
                    <li><a href="/semi/project/main.jsp">Home</a></li>
                    <li><a href="/semi/page/shop.do" >Shop</a></li>
               </ul>
            </nav>
                        <!-- Button Group -->
            <c:choose>            
            <c:when test="${empty sessionScope.customInfo.userId }">
            
            <div class="amado-btn-group mt-30 mb-100">
                <a href="/semi/member/login.do" class="btn amado-btn mb-15">로그인</a>
                <a href="/semi/member/signIn.do" class="btn amado-btn mb-15">회원가입</a>
                <a href="/semi/sreview/list.do" class="btn amado-btn mb-15">상품리뷰</a>

            </div>
            </c:when>
			<c:otherwise>
			<nav class="amado-nav">
            <ul>
           		 <li><a href="/semi/cart/list.do">Cart</a></li>
            </ul>
            </nav>
			<font color="blue"><b>${sessionScope.customInfo.userName }</b></font>님 안녕하세요.<br/><br/>
			    <a href="/semi/member/logout.do" class="btn amado-btn mb-15">로그아웃</a>
                <a href="/semi/member/mypage.do" class="btn amado-btn mb-15">마이페이지</a>
                <a href="/semi/sreview/list.do" class="btn amado-btn mb-15">상품리뷰</a>
                
         			 
					
					
			</c:otherwise>
			</c:choose>
            
            
            
            
            
            
            
            
            
            
            
            <!-- Cart Menu -->
            <div class="cart-fav-search mb-100">
            <c:if test="${!empty sessionScope.customInfo.userId }">
                <a href="/semi/cart/list.do" class="cart-nav"><img src="/semi/project/img/core-img/cart.png" alt=""> Cart <span>(${sessionScope.customInfo.cartCount })</span></a>
                <a href="#" class="fav-nav"><img src="/semi/project/img/core-img/favorites.png" alt=""> Favourite</a>
                <a href="#" class="search-nav"><img src="/semi/project/img/core-img/search.png" alt=""> Search</a>
           </c:if>
            <c:if test="${empty sessionScope.customInfo.userId }">
                <a href="/semi/cart/list.do" class="cart-nav"><img src="/semi/project/img/core-img/cart.png" alt=""> Cart <span></span></a>
                <a href="#" class="fav-nav"><img src="/semi/project/img/core-img/favorites.png" alt=""> Favourite</a>
                <a href="#" class="search-nav"><img src="/semi/project/img/core-img/search.png" alt=""> Search</a>
           </c:if>
            </div>
            <!-- Social Button -->
            <div class="social-info d-flex justify-content-between">
                <a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
            </div>
        </header>
        <!-- Header Area End -->

        <div class="shop_sidebar_area">

            <!-- ##### Single Widget ##### -->
            <div class="widget catagory mb-50">
                <!-- Widget Title -->
                <h6 class="widget-title mb-30">Catagories</h6>

                <!--  Catagories  -->
                <div class="catagories-menu">
                    <ul>
                    
                    <c:choose>
                    	<c:when test="${categoryNo eq 1 }">
                            <li class="active"><a href="<%=cp%>/page/shop.do?categoryNo=1">Chairs</a></li>                    	                    	
                    	</c:when>
                    	<c:otherwise>
                    	    <li><a href="<%=cp%>/page/shop.do?categoryNo=1">Chairs</a></li>      	
                    	</c:otherwise>
                    </c:choose>
                    
                    
                    <c:choose>
                    	<c:when test="${categoryNo eq 2 }">
                        <li class="active"><a href="<%=cp%>/page/shop.do?categoryNo=2">Beds</a></li>
                    	</c:when>
                    	<c:otherwise>
                        <li><a href="<%=cp%>/page/shop.do?categoryNo=2">Beds</a></li>
                    	</c:otherwise>
                    </c:choose>
                    
                    <c:choose>
                    	<c:when test="${categoryNo eq 3 }">
                        <li class="active"><a href="<%=cp%>/page/shop.do?categoryNo=3">Accesories</a></li>
                    	</c:when>
                    	<c:otherwise>
                        <li><a href="<%=cp%>/page/shop.do?categoryNo=3">Accesories</a></li>
                    	</c:otherwise>
                    </c:choose>
                    
                    <c:choose>
                    	<c:when test="${categoryNo eq 4 }">
                        <li class="active"><a href="<%=cp%>/page/shop.do?categoryNo=4">Furniture</a></li>
                    	</c:when>
                    	<c:otherwise>
                        <li><a href="<%=cp%>/page/shop.do?categoryNo=4">Furniture</a></li>
                    	</c:otherwise>
                    </c:choose>
                    
                    <c:choose>
                    	<c:when test="${categoryNo eq 5 }">
                        <li class="active"><a href="<%=cp%>/page/shop.do?categoryNo=5">Home Deco</a></li>
                    	</c:when>
                    	<c:otherwise>
                        <li><a href="<%=cp%>/page/shop.do?categoryNo=5">Home Deco</a></li>
                    	</c:otherwise>
                    </c:choose>
                    
                    <c:choose>
                    	<c:when test="${categoryNo eq 6 }">
                        <li class="active"><a href="<%=cp%>/page/shop.do?categoryNo=6">Dressings</a></li>
                    	</c:when>
                    	<c:otherwise>
                        <li><a href="<%=cp%>/page/shop.do?categoryNo=6">Dressings</a></li>
                    	</c:otherwise>
                    </c:choose>
                    
                    <c:choose>
                    	<c:when test="${categoryNo eq 7 }">
                        <li class="active"><a href="<%=cp%>/page/shop.do?categoryNo=7">Tables</a></li>
                    	</c:when>
                    	<c:otherwise>
                        <li><a href="<%=cp%>/page/shop.do?categoryNo=7">Tables</a></li>
                    	</c:otherwise>
                    </c:choose>

                        
                        
                        
                    </ul>
                </div>
            </div>

           
        </div>

        <div class="amado_product_area section-padding-100">
            <div class="container-fluid">

                <div class="row">
                    <div class="col-12">
                        <div class="product-topbar d-xl-flex align-items-end justify-content-between">
                            <!-- Total Products -->
                            <div class="total-products">
                                <p>Showing ${start }-${end } 0f ${dataCount }</p>
							</div>
                            <!-- Sorting -->
                            <div class="product-sorting d-flex"></div>
                        </div>
                    </div>
                </div>




              <div class="row">




					<!-- Single Product Area -->

					<c:forEach var="i" begin="0" end="${pLists.size()-1 }" step="1">

						<div class="col-12 col-sm-6 col-md-12 col-xl-6">
							<div class="single-product-wrapper">
								<!-- Product Image -->
								<div class="product-img">
								
								<c:if test="${status eq 1 }">
								<a href="/semi/product/article.do?productNo=${pLists.get(i).productNo }">
									<img src="${imagePath }/${firstFileName[i].replaceAll('jpg-1', 'jpg')}" alt=""></a>
									
									<!-- Hover Thumb -->
									<a href="/semi/product/article.do?productNo=${pLists.get(i).productNo }"> 
									<img class="hover-img" src="${imagePath }/${secondFileName[i].replaceAll('jpg-2', 'jpg') }" alt="">
									</a>
									<!-- <img class="hover-img" src="/semi/project/img/product-img/product6.jpg" alt=""> -->
								</c:if>

								</div>

								<!-- Product Description -->
								<div
									class="product-description d-flex align-items-center justify-content-between">
									<!-- Product Meta Data -->
									<div class="product-meta-data">
										<div class="line"></div>
										<p class="product-price">$&nbsp;${pLists.get(i).price }</p>
										<a href="product-details.jsp">
											<h6>${pLists.get(i).productName }</h6>
										</a>
									</div>
									<!-- Ratings & Cart -->
									<div class="ratings-cart text-right">
										<div class="ratings">
											<i class="fa fa-star" aria-hidden="true"></i> <i
												class="fa fa-star" aria-hidden="true"></i> <i
												class="fa fa-star" aria-hidden="true"></i> <i
												class="fa fa-star" aria-hidden="true"></i> <i
												class="fa fa-star" aria-hidden="true"></i>
										</div>
										<div class="cart">
											<a href="#" data-toggle="tooltip"
												data-placement="left" title="Add to Cart"><img
												src="/semi/project/img/core-img/cart.png" alt=""></a>
										</div>
									</div>
								</div>
							</div>
						</div>

					</c:forEach>



				</div>
					<div class="row">
						<div class="col-12">
							<!-- Pagination -->
							<nav aria-label="navigation">
							<ul class="pagination justify-content-end mt-50">

								<c:forEach var="indexlist"  begin="1" end="${totalPage}" step="1" varStatus="status">							
									<c:choose>									
										<c:when  test="${currentPage == status.index}">
											<li class="page-item active"><a class="page-link" >${status.index}</a></li>		
										</c:when>
										<c:otherwise>					

											<li class="page-item"><a class="page-link"  href="<%=cp%>/page/shop.do?pageNum=${status.index}&categoryNo=${categoryNo }">${status.index}</a></li>
											
										</c:otherwise>								
									</c:choose>									
								</c:forEach>
								

							</ul>
							</nav>
						</div>
					</div>
		</div>
  </div>
    </div>
 <!-- ##### Main Content Wrapper End ##### -->

 <!-- ##### Footer Area Start ##### -->
    <footer class="footer_area clearfix">
        <div class="container">
            <div class="row align-items-center">
                <!-- Single Widget Area -->
                <div class="col-12 col-lg-4">
                    <div class="single_widget_area">
                        <!-- Logo -->
                        <div class="footer-logo mr-50">
                            <a href="/semi/project/main.jsp"><img src="/semi/project/img/core-img/logo2.png" alt=""></a>
                        </div>
                        <!-- Copywrite Text -->
                        <p class="copywrite"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
                    </div>
                </div>
                <!-- Single Widget Area -->
                <div class="col-12 col-lg-8">
                    <div class="single_widget_area">
                        <!-- Footer Menu -->
                        <div class="footer_menu">
                            <nav class="navbar navbar-expand-lg justify-content-end">
                                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#footerNavContent" aria-controls="footerNavContent" aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-bars"></i></button>
                                <div class="collapse navbar-collapse" id="footerNavContent">
                                    <ul class="navbar-nav ml-auto">
                                        <li class="nav-item">
                                            <a class="nav-link" href="/semi/project/main.jsp">Home</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="/semi/page/shop.do">Shop</a>
                                        </li>
                                        
                                        <li class="nav-item">
                                            <a class="nav-link" href="/semi/cart/list.do">Cart</a>
                                        </li>
                                       S
									<c:choose>
										<c:when test="${sessionScope.customInfo.userId !='admin'}">
											
										</c:when>
										<c:otherwise>
										<nav class="navbar navbar-expand-lg justify-content-end">
                               				 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#footerNavContent" aria-controls="footerNavContent" aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-bars"></i></button>
                               					 <div class="collapse navbar-collapse" id="footerNavContent">
                                  					  <ul class="navbar-nav ml-auto">
											<li class="nav-item">
                                            <a class="nav-link" href="/semi/master/adminindex.do"><b>관리자페이지</b></a>
                                       		 </li>
                                       	</ul>
                                       	</div>
                                       	</nav>
                                       		 
										</c:otherwise>
									</c:choose>
                                   </ul>
                                </div>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- ##### Footer Area End ##### -->

    <!-- ##### jQuery (Necessary for All JavaScript Plugins) ##### -->
    <script src="<%=cp%>/project/js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="<%=cp%>/project/js/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="<%=cp%>/project/js/bootstrap.min.js"></script>
    <!-- Plugins js -->
    <script src="<%=cp%>/project/js/plugins.js"></script>
    <!-- Active js -->
    <script src="<%=cp%>/project/js/active.js"></script>

</body>

</html>