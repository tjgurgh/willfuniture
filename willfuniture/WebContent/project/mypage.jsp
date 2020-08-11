<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="/semi/project/css/style-mypage.css">
</head>
<body>

	<c:choose>
		<c:when test="${empty sessionScope.customInfo.userId}">
			<p>로그인하여 주십시오.</p>
		</c:when>
		<c:otherwise>
		
<%@include file="/project/header.jsp" %> 



	<div id="detailview" style="height=300px; width=1200px;">
	<br/><br/><br/>
		<ul>
			<li class="dv_top">
				<div class="dv_top_navi">
					<img src="/semi/project/img/20160406_09.png"> 마이페이지<br/><br/>
				</div>
				<div class="cart">
					<ul>

						<li class="cart_title">
							<p style=" font-size:20px;">회원님의 쇼핑내역과 기타 자세한 정보등을 확인하실 수 있습니다.</p>
						</li><br/><br/>
						<li class="cart_navi">
							<table class="mptop_table" >
								<colgroup>
									<col width="168px;">
									<col width="234px;">
									<col width="234px;">
									<col width="234px;">
									<col width="">
								</colgroup>
								<tbody>
									<tr >
										<td rowspan="2" border="1"><img src=/semi/project/img/20200525_175300.png></td>
										<td colspan="4" align="center">방문해 주셔서 감사합니다. <font color="blue"><b>${sessionScope.customInfo.userName }</b></font> 님은 [<strong>${grade }</strong>]
											이십니다.
										</td>
									</tr>
									
									<tr>
									
										<td align="center" style="font-size:15px;"><b>가용적립금</b>
											<p align="center" style="font-size:18px;">${dto.totPoint }<span>원</span>
											
										</td>
										
										<td align="center" style="font-size:15px;"><b>총 주문 건</b>
											<p align="center" style="font-size:18px;">${orderCount }<span>건</span></p>
										</td>
									</tr>
								</tbody>
								
							</table>
							<br/><br/>
						</li>
						<li>
							<table class="mpbody_table">
								<colgroup>
									<col width="333px;">
									<col width="50px;">
									<col width="334px;">
									<col width="50px;">
									<col width="333px;">
								</colgroup>
								<tbody>
								
									<tr>
										<td><a href="<%=cp %>/orders/orderHistory.do"><img
												src="/semi/project/img/20160411_04.png"></a></td>
										<td></td>
										<td><a href="/semi/member/memberUpdate.do"><img
												src="/semi/project/img/20160411_05.png"></a></td>
										<td>	<c:choose>
													<c:when test="${sessionScope.customInfo.userId!='admin' }">
														<td><a href='<%=cp%>/sqna/myList.do?userId=${sessionScope.customInfo.userId}'><img src="/semi/project/img/20160411_07.png"></a></td>
													</c:when>
													<c:otherwise>
														<td><a href="<%=cp%>/sqna/list.do" ><img src="/semi/project/img/20160411_07.png"></a></td>
													</c:otherwise>
												</c:choose>
										</td>		
										

										
											
										<td></td>
									</tr>
									<tr>
										<td colspan="5" style="height: 30px; padding: 0;"></td>
									</tr>
									
								</tbody>
							</table>
						</li>
					</ul>
				</div>
			</li>
			
		</ul>
	</div>

    <%@include file="/project/footer.jsp" %>
    
        		</c:otherwise>
	</c:choose>
</body>
</html>