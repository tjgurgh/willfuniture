<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%@include file="cssHeader.jsp"%>

</head>
<body>
	<br />
	<div class="logo">
		&nbsp;&nbsp;&nbsp;&nbsp; <a href="/semi/project/main.jsp"><img
			src="/semi/project/img/core-img/logo.png" alt=""></a>
	</div>
	<br />

	<div class="product-status mg-b-15">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="product-status-wrap">
						<h4>후기 게시판</h4>
						<div class="add-product" align="right">  
						
						 <c:choose>            
            			<c:when test="${!empty sessionScope.customInfo.userId }">
         			
						<button class="btn btn-primary" 
						onclick="location.href='<%=cp%>/sreview/created.do?userId=${sessionScope.customInfo.userId }'">후기 작성</button>
						<button class="btn btn-success" 
						onclick="location.href='<%=cp%>/sreview/list.do'">후기 목록</button>
						</div>


            			 </c:when>
						<c:otherwise>  						
						<button class="btn btn-success" 
						onclick="location.href='<%=cp%>/sreview/list.do'">후기 목록</button>
						</div>						
						</c:otherwise>
						</c:choose>						
						
  
						<br/>
						<div class="asset-inner">		
							<table border="1">
								<tr>
									<th width="100">번호</th>
									<!-- <th>이미지</th> -->
									<th>제목</th>
									<th width="200">작성자</th>
									<th width="200">작성일</th>
									<th width="100">조회수</th>
									<!-- <th>설정</th> -->
								</tr>
								<c:forEach var="dto" items="${lists }">
									<tr>
										<td>${dto.reviewNo }</td>
										<td>
									<a href="${articleUrl }&reviewNo=${dto.reviewNo }&userId=${sessionScope.custnomInfo.userId}">${dto.subject }&nbsp;&nbsp;[${dto.recnt }]</a>
										</td>
										<td>${dto.userId }</td>
										<td>${dto.writeDate }</td>
										<td>${dto.hitCount }</td>
									</tr>
								</c:forEach>
							</table>
						</div>

						<div class="custom-pagination" align="center">
							<form action="<%=cp%>/sreview/list.do" name="myForm">
								<div class="form-inline">
									<select id="searchKey" name="searchKey">
										<option value="">검색조건</option>
										<option value="subject">제목</option>
										<option value="content">내용</option>
										<option value="userId">작성자</option>
									</select> <input class="form-control" type="text" name="searchValue"
										placeholder="검색어를 입력하세요" />
									<button id="searchBtn" class="btn btn-primary">Search</button>
								</div>
							</form>
							<ul class="pagination">
								<!-- 
								<li class="page-item"><a class="page-link" href="#">Previous</a></li>
								<li class="page-item"><a class="page-link" href="#">1</a></li>
								<li class="page-item"><a class="page-link" href="#">2</a></li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item"><a class="page-link" href="#">Next</a></li> -->
								<c:if test="${dataCount != 0 }">
				
				<li class="page-item">${pageIndexList }</li>
			</c:if>
								<c:if test="${dataCount == 0 }">
				등록된 게시물이 없습니다.
			</c:if>
							</ul>
						</div>
						<br />

					</div>
				</div>
			</div>
		</div>
	</div>



	<%@include file="jsFooter.jsp"%>
</body>
</html>