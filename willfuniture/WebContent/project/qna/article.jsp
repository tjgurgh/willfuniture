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

	function deleteBtn(){
	var del = confirm("삭제하시겠습니까?");
	
	if(del){
		
		var f = document.myForm;
		f.action = "<%=cp%>/sqna/deleted_ok.do?qno=${dto.qno }&userId=${sessionScope.customInfo.userId}&${params }";
			f.submit();
			alert("삭제되었습니다.");
		} else {
			alert("삭제가 취소 되었습니다.");
			return;
		}
	}
</script>
</head>

<body style="margin: 0 auto;">
<!-- Logo -->
			<br/>
            <div class="logo">
               &nbsp;&nbsp;&nbsp;&nbsp; <a href="/semi/project/main.jsp"><img src="/semi/project/img/core-img/logo.png" alt=""></a>
            </div>
            <br/>
	<br />
	<br />
	<div class="row" style="margin: auto;">
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12"
			style="margin: auto;">


			<div class="basic-login-inner">
				<h3>문의 게시판</h3>
				<p></p>
				<form action="/semi/sqna/updated_ok.do" method="post"
					name="myForm">

					<div class="form-group-inner">
						<label>작성자</label> <input type="text" class="form-control"
							value="${dto.userId }" disabled="disabled" />
					</div>
					<c:if test="${!empty dto.qnaCategory }">
						<div class="form-group-inner">
							<label>문의 종류</label>
							<div class="form-select-list">
								<select class="form-control custom-select-value"
									name="qnaCategory" disabled="disabled">
									<option value=${dto.qnaCategory }>${dto.qnaCategory}</option>
								</select>
							</div>
						</div>
					</c:if>
					<div class="form-group-inner">
						<label>제목</label> <input type="text" name="subject"
							disabled="disabled" class="form-control" placeholder="제목"
							value="${dto.subject }" />
					</div>
					<div class="form-group-inner">
						<label>내용</label>
						<textarea rows="10" cols="30" name="qnaContent"
							disabled="disabled" class="form-control" placeholder="내용">${dto.qnaContent }</textarea>
					</div>

					<div class="button-ap-list responsive-btn" style="float: right;">
						<div class="button-style-four btn-mg-b-10">
							<c:if
								test="${sessionScope.customInfo.userId == dto.userId && !empty dto.userId
							&& sessionScope.customInfo.userId != 'admin'}">
								<input type="button" class="btn btn-custon-four btn-primary"
									value="수정하기"
									onclick="location.href='<%=cp%>/sqna/updated.do?qno=${dto.qno }&${params }';">
								<input type="button" class="btn btn-custon-four btn-success"
									value="삭제하기" onclick="deleteBtn();">
							</c:if>

							<c:if
								test="${sessionScope.customInfo.userId == 'admin' && !empty dto.userId }">
								<input type="button" class="btn btn-custon-four btn-primary"
									value="수정하기"
									onclick="location.href='<%=cp%>/sqna/updated.do?qno=${dto.qno }&${params }';">
								<input type="button" class="btn btn-custon-four btn-success"
									value="삭제하기" onclick="deleteBtn();">
							</c:if>
							<%-- <input type="button" class="btn btn-custon-four btn-default"
								value="목록으로"
								onclick="location.href='/semi/project/sqna/list.do?${params}'"> --%>
							<c:if
								test="${sessionScope.customInfo.userId == 'admin' && !empty dto.userId }">
							<input type="button" class="btn btn-custon-four btn-default"
								value="목록으로"
								onclick="location.href='/semi/sqna/list.do?${params}'">
							<input type="button" class="btn btn-custon-four btn-default"
								value="문의 미확인"
								onclick="location.href='/semi/sqna/noList.do?${params}'">
							</c:if>
							
							<c:if
								test="${sessionScope.customInfo.userId != 'admin' && !empty dto.userId }">
							<input type="button" class="btn btn-custon-four btn-default"
								value="목록으로"
								onclick="location.href='/semi/sqna/myList.do?${params}&userId=${sessionScope.customInfo.userId }'">
							</c:if>
							<c:if
								test="${sessionScope.customInfo.userId == 'admin'}">
								<input type="button" class="btn btn-custon-four btn-default"
									value="답글쓰기"
									onclick="location.href='/semi/sqna/reply.do?qno=${dto.qno}&${params }'">
							</c:if>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<%@include file="jsFooter.jsp"%>
</body>

</html>