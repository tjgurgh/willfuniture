<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");

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

		function login(){
			
			var f = document.myForm;
			
			if(!f.userId.value){
				
				alert("아이디를 입력하세요!");
				f.userId.focus();
				return;
				
			}

			if(!f.userPwd.value){
				
				alert("패스워드를 입력하세요!");
				f.userPwd.focus();
				return;
			}

			f.action = "/semi/member/login_ok.do";
			f.submit();
			
			
		}


</script>


</head>
<body>
  <%@include file="/project/header.jsp" %>  
        <!-- Header Area End -->

        <div class="cart-table-area section-padding-100">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12 col-lg-8 text-center">
                        <div class="checkout_details_area mt-50 clearfix">

                            <div class="cart-title">
                                <h2> 로그인 </h2>
                            </div>

  							<form action="" method="post" name=myForm>
                                <div class="row">
                                  <div class="col-12 mb-3">
                                        <input type="text" class="form-control" name="userId" placeholder="아이디">
                                   	 </div>
                     
                                     <div class="col-12 mb-3">
                                        <input type="password" class="form-control" name="userPwd" placeholder="비밀번호">
                                    </div>
                                   </div>
                                   
                                   <div align="center">
                                   <font color="red"><b>${message }</b></font>
                                   </div>

							<c:choose>
								<c:when test="${!empty message }">

									<div class="cart-btn mt-30">
										<input type="button" class="btn amado-btn w-100"
											value=" 비밀번호 찾기 "
											onclick="javascript:location.href='<%=cp%>/member/searchpw.do';" />
									</div>
								</c:when>
							</c:choose>


							<div class="cart-btn mt-30">
                                <input type="button" class="btn amado-btn w-100" value=" Login" onclick="login();"/>
                            </div>
                             <div class="cart-btn mt-30">
                                <input type="button" class="btn amado-btn w-100" value="Sign In"
                                onclick="javascript:location.href='<%=cp %>/member/signIn.do';"/>
                            </div>
                            </form>
                                   
                               	</div>
                        </div>
                    </div>
                            
                        </div>
                    </div>
                    
     
    <!-- ##### Main Content Wrapper End ##### -->

 
   <%@include file="/project/footer.jsp" %>

</body>
</html>