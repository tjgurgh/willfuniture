<%@page import="java.sql.Connection"%>
<%@page import="com.util.DBCPConn"%>
<%@page import="com.member.MemberDTO"%>
<%@page import="com.member.MemberDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	
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
<title>회원정보 수정</title>
 	<!-- Favicon  -->
    <link rel="icon" href="/semi/project/img/core-img/favicon.ico">

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="/semi/project/css/core-style.css">
    <link rel="stylesheet" href="/semi/project/style.css">
<!-- ###############################################################밑에 스크립트 추가가능################################################################################### -->
<script type="text/javascript">
	 function sendIt() {
			
			var f = document.myForm;
			
			str = f.userPwd.value;
			str = str.trim();
			
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
/* 	
 		
 		
 		//비밀번호 검사		
 		str = f.userPwd.value;
 		str = str.trim();
 		
 		if(!str){
 			alert("비밀번호를 입력하세요!");
 			f.userPwd.focus();
 			return;
 		}
 		
 		f.userPwd.value = str;
 		
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

 		f.addrDetail.value = str;	
*/
 		
 		f.action = "/semi/member/update_ok.do";
		f.submit();

	}
</script>
</head>
<body>
<%@include file="/project/header.jsp" %> 


        <div class="cart-table-area section-padding-100">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12 col-lg-8">
                        <div class="checkout_details_area mt-50 clearfix">

                            <div class="cart-title">
                                <h2> 회원정보 수정 </h2>
                            </div>

                            <form action="" method="post" name="myForm">
                                <div class="row">
                                     <div class="col-12 mb-3">
                                     <b>아이디 : </b>
                                        <input type="hidden" class="form-control" name="userId" value="${dto.userId }">
                                    ${dto.userId }
                                   	 </div>
                               		
                                    <div class="col-12 mb-3">
                                    <b>이름 : </b>
                                        <input type="hidden" class="form-control" name="userName" value="${dto.userName}">
                                        ${dto.userName }
                                    </div>
                                    <div class="col-12 mb-3">
                                    <b>생일 : </b>
                                        <input type="hidden" class="form-control" name="userBirth" value="${dto.userBirth }">
                                        ${dto.userBirth }
                                    </div>
                                    <div class="col-12 mb-3">
                               		 <b>비밀번호 수정<br></b> 
                                        <input type="password" class="form-control" name="userPwd" value="${dto.userPwd}">
                                   	</div>
                                     <div class="col-12 mb-3">
                               		 <b>비밀번호 수정 확인<br></b> 
                                        <input type="password" class="form-control" name="rePwd">
                                   	</div>
                                    <div class="col-12 mb-3">
                                     <b>전화번호<br></b> 
                                        <input type="text" class="form-control" name="userTel" value="${dto.userTel }">
                                    </div>                               
                                    
                                    <div class="col-12 mb-3">
                                    E-mail<br>
                                        <input type="text" class="form-control" name="userEmail" value="${dto.userEmail }">
                                    </div>
                                    <div class="col-12 mb-3">
                                     <b>주소<br></b> 
                                        <input type="text" class="form-control" name="addr" value="${dto.addr }">
                                    </div>
                                    <div class="col-12 mb-3">
                                     <b>상세주소<br></b> 
                                        <input type="text" class="form-control" name="addrDetail" value="${dto.addrDetail }">
                                    </div>
                                </div>
                               
                                
                            </form>
                          
                            <div class=row>
                            <div class="col-6 mb-3">
                                <input type="submit" class="btn amado-btn w-100" value="수정완료" onclick="sendIt();">
						 
						 	</div>
						 	<div class="col-6 mb-3">
                           
                            	<input type="button" class="btn amado-btn w-100" value="수정취소"
                            	onclick="javascript:location.href='<%=cp%>/member/mypage.do'">
                            </div>
                            
                        <div class="col-12 mb-3">
                            	<input type="button" class="btn amado-btn w-100" value="회원탈퇴"
                            	onclick="javascript:location.href='<%=cp%>/member/deleteMember.do'">
                            </div>
                            </div>
                        </div>
                    </div>
               

                  
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Main Content Wrapper End ##### -->

 
    <!-- ##### Footer Area Start ##### -->
    <%@include file="/project/footer.jsp" %>
</body>
</html>