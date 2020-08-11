<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!doctype html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Basic Form Element | Kiaalap - Kiaalap Admin Template</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="cssHeader.jsp"%>

<script type="text/javascript">
	function replyUpdateBtn() {
	
	var upd = confirm("수정하시겠습니까?");
		
	if(upd){
		
		var rf = document.replyForm;
		rf.action = "<%=cp%>/sreply/updated_ok.do?${params }";
		rf.submit();
		alert("수정 되었습니다.");
		
	} else {
		alert("수정이 취소 되었습니다.");
		return;
	}
	}
	
	function replyDeleteBtn(rno, qno) {
		
		var upd = confirm("삭제 하시겠습니까?");
		
		if(upd){
			
			var rub = document.getElementById("deleteForm");
			rub.action = "<%=cp%>/sreply/deleted_ok.do?rno=" + rno + "&reviewNo=" + qno +"&${params }";
			rub.submit();
			
			alert("삭제 되었습니다.");
			
		} else {
			alert("삭제가 취소 되었습니다.");
			return;
		}
	}

	function deleteBtn(){
	var del = confirm("삭제하시겠습니까?");
	
	if(del){
		
		var f = document.myForm;
		f.action = "<%=cp%>/sreview/deleted_ok.do?reviewNo=${dto.reviewNo }&${params }";
			f.submit();
			alert("삭제 되었습니다.");
		} else {
			alert("삭제가 취소 되었습니다.");
			return;
		}
	}
	
	function replySubmit(){
		
		var f2 = document.myForm2;
		
		f2.action = "/semi/sreply/created_ok.do";
		f2.submit();
	}
</script>
</head>

<body style="margin: 0 auto;">
	<br />
	<br />
	<div class="row" style="margin: auto;">
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12"
			style="margin: auto;">

			<div class="basic-login-inner">
				<h3>후기 게시판</h3>
				<p></p>
				<form action="/semi/project/sqna/updated_ok.do" method="post"
					name="myForm">

					<div class="form-group-inner">
						<label>작성자</label> <input type="text" class="form-control"
							value="${dto.userId }" disabled="disabled" />
					</div>
					<div class="form-group-inner">
						<label>주문 번호</label> <input type="text" disabled="disabled"
							class="form-control" placeholder="주문 번호" value="${dto.orderNo }" />
					</div>
					<div class="form-group-inner">
						<label>제목</label> <input type="text" disabled="disabled"
							class="form-control" placeholder="제목" value="${dto.subject }" />
					</div>
					<div class="form-group-inner">
						<label>내용</label>
						<textarea rows="10" cols="30" name="content" disabled="disabled"
							class="form-control" placeholder="내용">${dto.content }</textarea>
					</div>

					<div class="button-ap-list responsive-btn" style="float: right;">
						<div class="button-style-four btn-mg-b-10">
							<c:if
								test="${sessionScope.customInfo.userId == dto.userId && !empty dto.userId
							&& sessionScope.customInfo.userId != 'admin'}">
								<input type="button" class="btn btn-custon-four btn-primary"
									value="수정하기"
									onclick="location.href='<%=cp%>/sreview/updated.do?reviewNo=${dto.reviewNo }&${params }';">
								<input type="button" class="btn btn-custon-four btn-success"
									value="삭제하기" onclick="deleteBtn();">
							</c:if>

							<c:if
								test="${sessionScope.customInfo.userId == 'admin' && !empty dto.userId }">
								<input type="button" class="btn btn-custon-four btn-primary"
									value="수정하기"
									onclick="location.href='<%=cp%>/project/sqna/updated.do?reviewNo=${dto.reviewNo }';">
								<input type="button" class="btn btn-custon-four btn-success"
									value="삭제하기" onclick="deleteBtn();">
							</c:if>
							<input type="button" class="btn btn-custon-four btn-default"
								value="목록으로"
								onclick="location.href='/semi/sreview/list.do?${params}&userId=${sessionScope.customInfo.userId}'">
						</div>
					</div>
				</form>

				<br />
				<br />
				<form action="/semi/project/sreply/created_ok.do" method="post"
					name="myForm2">
					<input type="hidden" name="qno" value="${dto.reviewNo }"> <input
						type="hidden" name="userId"
						value="${sessionScope.customInfo.userId }">
					<div class="blog-details-area mg-b-15">
						<div class="container-fluid">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="blog-details-inner">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
												<div class="comment-head">
													<h3>댓글 작성</h3>
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
												<div class="user-comment">
													<div class="comment-details">
														<p>
														<c:if test="${sessionScope.customInfo.userId != null }">
															<textarea rows="5" cols="30" name="content"
																class="form-control" placeholder="내용"></textarea>
															<span class="mobile-sm-d-n"></span>
														</c:if>
														<c:if test="${sessionScope.customInfo.userId == null }">
															<textarea rows="5" cols="30" name="content"
																class="form-control" placeholder="로그인한 사용자만 이용 가능" disabled="disabled"></textarea>
															<span class="mobile-sm-d-n"></span>
														</c:if>
														</p>

													</div>
												</div>
												<br />
												<div class="button-ap-list responsive-btn"
													style="float: right;">
													<div class="button-style-four btn-mg-b-10">
													<c:if test="${sessionScope.customInfo.userId != null }">
														<input type="button"
															class="btn btn-custon-four btn-primary" value="등록하기"
															onclick="replySubmit();">
													</c:if>
														<!-- <input type="button" class="btn btn-custon-four btn-success"
									value="삭제하기" onclick="deleteBtn();"> -->
														<input type="button"
															class="btn btn-custon-four btn-default" value="목록으로"
															onclick="location.href='/semi/sreview/list.do?${params}&userId=${sessionScope.customInfo.userId}'">
													</div>
												</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
				<div class="blog-details-area mg-b-15">
					<div class="container-fluid">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="blog-details-inner">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="comment-head">
												<h3>댓글 목록</h3>
											</div>
										</div>
									</div>

									<c:forEach var="rdto" items="${rlists }" varStatus="st">
										<c:if test="${!st.first }">
											<br />
										</c:if>

										<form id="deleteForm" method="post">
											<div class="row">
												<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
													<div class="user-comment">
														<div class="comment-details">
															<input type="hidden" name="rno" value="${rdto.rno }">
															<input type="hidden" name="qno" value="${rdto.qno }">
															<input type="hidden" name="reviewNo"
																value="${dto.reviewNo }">
															<h4>
																작성자 : ${rdto.userId } <span class="comment-replay"
																	onclick="location.href='/semi/sreply/updated.do?rno=${rdto.rno}'">수정</span>
															</h4>
															<h4>
																내용 : ${rdto.content } <span class="comment-replay"
																	onclick="replyDeleteBtn(${rdto.rno}, ${rdto.qno });">삭제</span>
															</h4>
														</div>
													</div>
												</div>
											</div>
										</form>

									</c:forEach>
								</div>
								<div class="custom-pagination" align="center">
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
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="jsFooter.jsp"%>

</body>

</html>