<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"		>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Amado - Furniture Ecommerce Template | Checkout</title>
 	<!-- Favicon  -->
    <link rel="icon" href="/semi/project/img/core-img/favicon.ico">

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="/semi/project/css/core-style.css">
    <link rel="stylesheet" href="style.css">
    
    
<script type="text/javascript">

	function sendIt(){
		
		var f = document.myForm;
		var wf = document.wonaForm;
		
		if(!f.userName.value){
			alert("이름을 입력하세요");
			f.userName.focus();
			return;
		}
		if(!f.userTel.value){
			alert("전화번호를 입력하세요");
			f.userTel.focus();
			return;
		}
		if(!f.userEmail.value){
			alert("이메일을 입력하세요");
			f.userEmail.focus();
			return;
		}
		if(!f.addr.value){
			alert("기본 주소를 입력하세요");
			f.addr.focus();
			return;
		}
		if(!f.addrDetail.value){
			alert("상세 주소를 입력하세요");
			f.addrDetail.focus();
			return;
		}
		
		
		f.total.value = wf.total.value;
		f.usePoint.value = wf.usePoint.value;
		f.bonusPoint.value = wf.bonusPoint.value;
		
		f.action = "<%=cp%>/orders/checkout_ok.do";
		f.submit();
		
	}
	
	
	function pointCK(){
		
		var wf = document.wonaForm;
		
		if(Number(wf.usePoint.value)>"${mDto.totPoint }"){
			
			alert("사용 가능한 최대 포인트는 '${mDto.totPoint }'포인트입니다.");
			wf.usePoint.value = 0;
			wf.usePoint.focus();
			return;
			
		}
		
		wf.total.value = "${cartTotal}"-wf.usePoint.value;
		wf.bonusPoint.value = Math.round((wf.total.value *0.05)/10)*10;
		
	}
	

</script>

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

      <div class="cart-table-area section-padding-100">
            <div class="container-fluid">
                <div class="row">
                
                
                
               
                    <div class="col-12 col-lg-8">
                        <div class="checkout_details_area mt-50 clearfix">

                            <div class="cart-title">
                                <h2>Checkout</h2>
                            </div>

                             <form action="" method="post" name="myForm">
                                <div class="row">
                                    <div class="col-12 mb-3">
                                        <input type="text" class="form-control" name="userName" value="${mDto.userName }"  required>
                                    </div>
                                    
                                    <div class="col-12 mb-3">
                                        <input type="text" class="form-control" name="userTel" value="${mDto.userTel }">
                                    </div>
                                    <div class="col-12 mb-3">
                                        <input type="email" class="form-control" name="userEmail" value="${mDto.userEmail }">
                                    </div>
                                   
                                    <div class="col-12 mb-3">
                                        <input type="text" class="form-control" name="addr" placeholder="기본 주소" value="">
                                    </div>
                                    <div class="col-12 mb-3">
                                        <input type="text" class="form-control" name="addrDetail" placeholder="상세 주소" value="">
                                    </div>
                                    
                                    <div class="col-12 mb-3">
                                        <textarea class="form-control w-100" name="options" cols="30" rows="10" placeholder="Leave a comment about your order"></textarea>
                                    </div>

                                    <div class="col-12 mb-3">
                                        
                                        <div class="custom-control custom-checkbox d-block">
                                            <input type="checkbox" class="custom-control-input" id="customCheck3"
                                            onclick="addr.value='${mDto.addr }'; addrDetail.value='${mDto.addrDetail }';">
                                            <label class="custom-control-label" for="customCheck3">Ship to my address</label>
                                        
                                        </div>
                                    </div>							
                                </div>
                                
                                <input type="hidden" name="total"/>
                                <input type="hidden" name="totPoint" value="${mDto.totPoint }"/>
                                <input type="hidden" name="usePoint"/>
                                <input type="hidden" name="bonusPoint"/>
                                
                                
                                
                                
                        	</form>
                            
                        </div>
                    </div>
                    <div class="col-12 col-lg-4">
                        <div class="cart-summary">
                        
                            <h5>Cart Total</h5>
                            
                            <form action="" name="wonaForm" method="post">
                            
                            <ul class="summary-table">
                                <li><span>subtotal:</span> <span>$&nbsp;${cartTotal }</span></li>
                                <li><span>my point:</span> <span>${mDto.totPoint }</span></li>
                                
                                <li><span>use point:</span>
                                <span>
                                	<input type="text" name="usePoint" value="0" style="width: 70px; text-align: right;"
                                	onkeyup="pointCK();"/>
                                </span>
                                </li>
                                
                                <li><span>total:</span>
                                <span>
                                	<input type="text" name="total" value="${cartTotal }" readonly="readonly" style="width: 70px; text-align: right;"/>
                                </span>
                                </li>
                                
                                <li><span>bonus point:</span>
                                <span>
                                	<input type="text" name="bonusPoint" value="${bonusPoint }" readonly="readonly" style="width: 70px; text-align: right;"/>
                                </span>
                                </li>
                            	
                            </ul>
                            
                           	
                            </form>
                            
                            
                            
                            
                            <div class="payment-method">
                                <!-- Cash on delivery -->
                                <div class="custom-control custom-checkbox mr-sm-2">
                                    <input type="checkbox" class="custom-control-input" id="cod" checked>
                                    <label class="custom-control-label" for="cod">Cash on Delivery</label>
                                </div>
                                <!-- Paypal -->
                                <div class="custom-control custom-checkbox mr-sm-2">
                                    <input type="checkbox" class="custom-control-input" id="paypal">
                                    <label class="custom-control-label" for="paypal">Paypal <img class="ml-15" src="/semi/project/img/core-img/paypal.png" alt=""></label>
                                </div>
                            </div>

                            <div class="cart-btn mt-100">
                            
                            	
                            	<input type="button" class="btn amado-btn w-100" value="Checkout" 
                            	onclick="sendIt();">
                            	
                            	
                            </div>
                            
                        </div>
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