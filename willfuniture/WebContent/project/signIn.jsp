<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>회원가입</title>
 	<!-- Favicon  -->
    <link rel="icon" href="/semi/project/img/core-img/favicon.ico">

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="/semi/project/css/core-style.css">
    <link rel="stylesheet" href="/semi/project/style.css">

<script type="text/javascript" src="<%=cp%>/project/js/util.js"></script>
<script type="text/javascript">


	function idCheck(){
	
		var f = document.myForm;

		str = f.userId.value;
		str = str.trim();

		if(str == "")
			alert("입력된 아이디가 없습니다.")
		else window.open("idCheck.do?userId="+ str,"","width=400 height=150")
	}

	function signIn(){
		
		var f = document.myForm;
		
		//아이디 검사
		str = f.userId.value;
		str = str.trim();
		
		if(!str){
			alert("아이디를 입력하세요!");
			f.userId.focus();
			return;
		}
		
		if(str!=f.userIdCk.value){
			alert("아이디 중복체크 해주세요!");
			f.idChecked.value="";
			f.userId.focus();
			return;
		}
		
		f.userId.value = str;	
		
		//비밀번호 검사		
		str = f.userPwd.value;
		str = str.trim();
		
		if(!str){
			alert("비밀번호를 입력하세요!");
			f.userPwd.focus();
			return;
		}
		
		if(!f.rePwd.value){
			alert("비밀번호 확인 해주세요!");
			f.rePwd.focus();
			return;
		}
		
		if(str!=f.rePwd.value){
			alert("입력된 비밀번호가 다릅니다. 다시 확인해주세요")
			f.rePwd.focus();
			return;
		}
		
		f.userPwd.value = str;	
		
		//이름 검사
		str = f.userName.value;
		str = str.trim();

		if(!str){
			alert("이름을 입력하세요!");
			f.userName.focus();
			return;
		}
		
		if(isValidKorean(str)!=true){	//한글인지 확인 (util.js에 있는 function)
			alert("\n이름은 한글로만 입력가능합니다.");
			f.userName.focus();
			return;
		}
		
		f.userName.value = str;	
		
		//생일 검사
		str = f.userBirth.value;
		str = str.trim();
		
		if(!str){
			alert("생년월일을 입력하세요!");
			f.userBirth.focus();
			return;
		}
	
		f.userBirth.value = str;	
		
		str = f.userTel.value;
		str = str.trim();
		
		//전화번호 검사
		if(!str){
			alert("전화번호를 입력하세요!");
			f.userTel.focus();
			return;
		}
	
		f.userTel.value = str;	
		
		
		//이메일 검사
		str = f.userEmail.value;
		str = str.trim();
		
		if(!str){
			alert("이메일을 입력하세요!");
			f.userEmail.focus();
			return;
		}
		
		if(!isValidEmail(str)){
			alert("\n정상적인 이메일을 입력하세요.");
			f.email.focus();
			return;
		}

		f.userEmail.value = str;	
		
		
		//주소 검사
		str = f.addr.value;
		str = str.trim();
		
		if(!str){
			alert("주소를 입력하세요!");
			f.addr.focus();
			return;
		}

		f.addr.value = str;	
		
		
		//상세주소 검사
		str = f.addrDetail.value;
		str = str.trim();
		
		if(!str){
			alert("상세주소를 입력하세요!");
			f.addrDetail.focus();
			return;
		}

		if(document.myForm.idChecked.value != "idChecked"){
			alert("아이디 중복 체크 해주세요!")
			return;
		}
		
		f.addrDetail.value = str;	

		f.action = "<%=cp %>/member/signIn_ok.do";	
		f.submit();
		
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
                                <h2> 회원가입 </h2>
                            </div>

                            <form action="" method="post" name="myForm">
                                <div class="row">
                                     <div class="col-9 mb-3">
                                        <input type="text" class="form-control" name="userId" placeholder="아이디">
                                   	 </div>
                                   	<div class="col-3 mb-3">
     								     <input type="button" class="btn amado-btn w-100" value=" 아이디 중복체크 " onclick="idCheck();"/>
       								</div>	
                                    <div class="col-12 mb-3">
                                        <input type="password" class="form-control" name="userPwd" placeholder="비밀번호">
                                    </div>
                               
                                    <div class="col-12 mb-3">
                                        <input type="password" class="form-control" name="rePwd" placeholder="비밀번호확인">
                                    </div>
                               
                                    <div class="col-12 mb-3">
                                        <input type="text" class="form-control" name="userName" placeholder="이름">
                                    </div>
                                    
                                    <div class="col-12 mb-3">
                                        <input type="text" class="form-control" name="userBirth" placeholder="생일 YYYYMMDD">
                                    </div>
                                    
                                    <div class="col-12 mb-3">
                                        <input type="tel" class="form-control" name="userTel" placeholder="전화번호">
                                    </div>                               
                                    
                                    <div class="col-12 mb-3">
                                        <input type="email" class="form-control" name="userEmail" placeholder="Email">
                                    </div>
                                    <div class="col-12 mb-3">
                                        <input type="text" class="form-control" name="addr" placeholder="주소">
                                    </div>
                                    <div class="col-12 mb-3">
                                        <input type="text" class="form-control" name="addrDetail" placeholder="상세주소">
                                    </div>
                                    
                                    <input type="hidden" name="idChecked"/>
                                    <input type="hidden" name="userIdCk"/>
                                    <input type="hidden" name="totPoint" value="0"/>
                                    <input type="hidden" name="accumPrice" value="0"/>
                                    </div>
                                  
							<div class="cart-btn mt-30">
                                 <input type="button" class="btn amado-btn w-100" value=" 회원가입 " onclick="signIn();"/>
                            </div>
                                    
                                
							</form>
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