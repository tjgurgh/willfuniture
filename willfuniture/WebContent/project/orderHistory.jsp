<%@page import="java.sql.Connection"%>
<%@page import="com.util.DBCPConn"%>
<%@page import="com.member.MemberDTO"%>
<%@page import="com.member.MemberDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
	String userId = request.getParameter("userId");
	Connection conn = DBCPConn.getConnection();
	
	MemberDAO dao = new MemberDAO(conn);
	MemberDTO dto = dao.getReadData(userId);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"		>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>주문내역 조회</title>
 	<!-- Favicon  -->
    <link rel="icon" href="/semi/project/img/core-img/favicon.ico">

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="/semi/project/css/core-style-order.css">
    <link rel="stylesheet" href="/semi/project/style.css">
<!-- ###############################################################밑에 스크립트 추가가능################################################################################### -->
	 <script type="text/javascript">
	 			function sendIt() {
	 				
	 				var f = document.myForm;
            		f.action = "<%=cp%>/return_ok.do";
            		f.submit();
            		
            	}
            </script>
</head>

<body>
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

        <div class="cart-table-area section-padding-100">
            <div class="container-fluid">
            	<div class="row">
                    <div class="col-12 col-lg-12">
                        <div class="checkout_details_area mt-50 clearfix">

                            <div class="cart-title">
                                <h2> 주문내역조회 </h2>
                    		</div>
							<c:if test="${empty lists }">
								<h5>주문 내역이 없습니다.....</h5>
							</c:if>
							<c:if test="${!empty lists }">
							<table class="table table-responsive">
                                <thead>
                                    <tr>
                                        <th width="13%">주문번호</th>
                                        <th width="13%">상품이름</th>
                                        <th width="11%">가격</th>
                                        <th width="6%">수량</th>
                                        <th width="12.5%">옵션</th>
                                        <th width="12.5%">주소</th>
                                        <th width="20%">상세주소</th>
                                        <th width="12%">진행상태</th>
                                    </tr>
                                </thead>
                                <tbody>
                                
                                
                                	<c:forEach var="dto" items="${lists }">
                                	
                                	 <tr>
                                        
                                        <td width="13%">
                                            <h5>${dto.orderNo }</h5>
                                        </td>
                                        <td width="13%">
                                            <h5>${dto.productName }</h5>
                                        </td>
                                        <td width="11%">
                                            <h5>${dto.price }</h5>
                                        </td>
                                        <td width="6%">
                                            <h5>${dto.orderQuantity }</h5>
                                        </td>
                                        <td width="12.5%">
                                            <h5>${dto.options }</h5>
                                        </td>
                                        <td width="12.5%">
                                            <h5>${dto.addr }</h5>
                                        </td>
                                        <td width="20%">
                                            <h5>${dto.addrDetail }</h5>
                                        </td>
                                        <td width="12%">
                                            <h5>${dto.progress }</h5>
                                        </td>
                                        
                                    </tr>
                                	
                                	</c:forEach>
                                
                                </tbody>
                            </table>
					</c:if>
                    	</div>
                	</div>
            	</div>
			</div>
			
			<div class="cart-btn mt-30">
            	<input type="button" class="btn amado-btn w-100" value="마이페이지" onclick="javascript:location.href='<%=cp %>/member/mypage.do';"/>
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
    <script src="js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="js/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Plugins js -->
    <script src="js/plugins.js"></script>
    <!-- Active js -->
    <script src="js/active.js"></script>

</body>

</html>