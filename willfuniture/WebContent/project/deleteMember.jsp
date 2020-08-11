<%@page import="com.member.MemberDTO"%>
<%@page import="com.member.MemberDAO"%>
<%@page import="com.util.DBCPConn"%>
<%@page import="java.sql.Connection"%>
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
    
<title>Amado - Furniture Ecommerce Template | Checkout</title>
 	<!-- Favicon  -->
    <link rel="icon" href="/semi/project/img/core-img/favicon.ico">

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="/semi/project/css/core-style.css">
    <link rel="stylesheet" href="/semi/project/style.css">

<script type="text/javascript">

function deleteMember(){		
	
	var f = document.myForm;
	
	if(!f.userPwd.value){
		
		alert("비밀번호를 입력하세요!");
		f.userId.focus();
		return;
		
	}

	if(!f.userPwd.value){
		
		alert("확인 비밀번호를 입력하세요!");
		f.userPwd.focus();
		return;
	}
	
	if(f.userPwd.value != f.rePwd.value){
		alert("입력된 비밀번호가 다릅니다. 다시 확인해주세요")
			f.rePwd.focus();
			return;
	}
	
	
	   var delConfirm = confirm('정말 탈퇴하시겠습니까?');
	   if (delConfirm) {
	      alert('탈퇴처리가 완료되었습니다.\n 그동안 감사했습니다.');
	      f.action = "<%=cp%>/member/delete_ok.do";
	      f.submit();
	   }
	   else {
	      alert('탈퇴가 취소되었습니다.');
	   }
	
	

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
                    <div class="col-12 col-lg-8 text-center">
                        <div class="checkout_details_area mt-50 clearfix">

                            <div class="cart-title">
                                <h2> 회원탈퇴 </h2>
                            </div>
                            
                            <div align="center">
                            1) 회원의 탈퇴요청의 경우에는 당사가 회원탈퇴 처리의 완료 통보하는 시점에서 탈퇴가 완료 됩니다. <br/>
								다만, 당사는 완료 통보일로부터 30일의 유예 기간 동안 기 적립된 포인트 정보를 소멸시키지 아니하고 저장하였다가,<br/>
								회원이 별도의 절차를 거쳐 재가입 요청을 하고 회사가 이를 허락하여 유예 기간 내 재가입할 경우,<br/>
								모든 포인트를 재사용토록 하며, 재가입이 없이 유예기간이 지나면, 모든 포인트가 즉시 소멸됩니다.<br/><br/>
							
							2) 회원자격상실의 경우 통보일에 회원 자격상실이 확정됩니다.<br/>
								단 사망으로 인한 자격상실의 경우에는 당사의 통보여부와 상관없이 회원 사망일에 자격상실이 확정되며, <br/>
								당해 회원에게 제공된 서비스와 관련된 권리나 의무 및 포인트는 당해 회원의 상속인에게 상속되지 않습니다.<br/><br/><br/>														
							</div>

  							<form action="" method="post" name="myForm">
                                <div class="row">
                                  <div class="col-12 mb-3">
                                        <input type="password" class="form-control" name="userPwd" placeholder="비밀번호"
                                        value = "${dto.userPwd}">
                                   	 </div>
                     
                                     <div class="col-12 mb-3">
                                        <input type="password" class="form-control" name="rePwd" placeholder="비밀번호 확인">
                                    </div>
                                   </div>
                                   
									 <input type="hidden" name="userId" value="${dto.userId }"/>
									
                                   <div class="cart-btn mt-30">
                                <input type="button" class="btn amado-btn w-100" value=" 탈&nbsp;&nbsp;&nbsp;퇴"
                                onclick="deleteMember();"/>
                            </div>
                             <div class="cart-btn mt-30">
                                <input type="button" class="btn amado-btn w-100" value=" 취&nbsp;&nbsp;&nbsp;소 "
                                onclick="javascript:location.href='<%=cp %>/project/index.jsp';"/>
                            </div>
                                     </form>
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