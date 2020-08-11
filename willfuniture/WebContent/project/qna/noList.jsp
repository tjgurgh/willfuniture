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
<!-- Logo -->
			<br/>
            <div class="logo">
               &nbsp;&nbsp;&nbsp;&nbsp; <a href="/semi/project/main.jsp"><img src="/semi/project/img/core-img/logo.png" alt=""></a>
            </div>
            <br/>
	<div class="product-status mg-b-15">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="product-status-wrap">
						<h4>문의 게시판</h4>
						<div class="add-product">
						<button class="btn btn-primary" 
						onclick="location.href='<%=cp%>/sqna/list.do'">전체 문의</button>
						<button class="btn btn-default"
						onclick="location.href='<%=cp%>/sqna/noList.do'">문의 미확인</button>
						<button class="btn btn-danger"
						onclick="location.href='<%=cp%>/master/adminindex.do'">관리자페이지</button>
						</div>

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
										<td>${dto.qno }</td>
										<!-- <td><img src="img/product/book-1.jpg" alt="" /></td> -->
										<td>
										
										<c:if test="${dto.level > 1}">
										<c:forEach begin="1" end="${dto.level}">
										&nbsp;&nbsp;&nbsp;&nbsp; <!-- 답변글일경우 글 제목 앞에 공백을 준다. -->
										</c:forEach>
										</c:if>
										<c:if test="${!empty dto.qnaCategory }">
										<a href="${articleUrl }&qno=${dto.qno }">[${dto.qnaCategory}]${dto.subject }</a>
										</c:if>
										</td>
										<td>${dto.userId }</td>
										<td>${dto.writeDate }</td>
										<td>${dto.hitCount }</td>
										<%-- 		<td>
												<button data-toggle="tooltip" title="수정"
													class="pd-setting-ed"
													onclick="location.href='/semi/project/sqna/updated.do?qno=${dto.qno}&${params }'">
													<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
												</button>
												<button data-toggle="tooltip" title="삭제"
													class="pd-setting-ed">
													<i class="fa fa-trash-o" aria-hidden="true"></i>
												</button>
											</td> --%>
									</tr>
								</c:forEach> 
							</table>
						</div>

						<div class="custom-pagination" align="center">
							<!-- <form action="/sqna/noList.do" name="myForm">
								<div class="form-inline">
									<select id="searchKey" name="searchKey">
										<option value="">검색조건</option>
										<option value="subject">제목</option>
										<option value="qnaContent">내용</option>
										<option value="userId">작성자</option>
									</select> <input class="form-control" type="text" name="searchValue"
										placeholder="검색어를 입력하세요" />
									<button id="searchBtn" class="btn btn-primary">Search</button>
								</div>
							</form> -->
							
							<ul class="pagination">
								<!-- 
								<li class="page-item"></li>
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