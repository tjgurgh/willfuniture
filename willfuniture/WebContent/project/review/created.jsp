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
	function send() {
		var f = document.myForm;

		str = f.subject.value;
		if (!str) {
			alert("제목을 입력해주세요.");
			f.subject.focus();
			return;
		}
		f.subject.value = str;
	
		str = f.content.value;
		if (!str) {
			alert("내용을 입력해주세요.");
			f.content.focus();
			return;
		}

		f.content.value = str;

		f.action = "/semi/sreview/created_ok.do";
		f.submit();
	}
</script>

</head>

<body>
	<br />
	<br />
	<div class="row" style="margin: auto;">
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">


			<div class="basic-login-inner">
				<h3>후기 게시판</h3>
				<p></p>
				<form action="/semi/sreview/created_ok.do" method="post"
					name="myForm">
					<div class="form-group-inner">
						<label>작성자</label> <input type="text" name="userId"
							class="form-control" value="${sessionScope.customInfo.userId }" disabled="disabled"/> 
							<!-- 
				<input type="hidden" value="${dto.productNo }" name="productNo">
				<input type="hidden" value="${dto.orderNo }" name="orderNo">
					-->
				<input type="hidden" value="${sessionScope.customInfo.userId }" name="userId">

					</div>
					<div class="form-group-inner">
						<label>주문 번호</label> <select name="orderNo">
							<c:forEach var="odto" items="${olists }" varStatus="status">
								<option value="${odto.orderNo }">${odto.orderNo }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group-inner">
						<label>제목</label> <input type="text" name="subject"
							class="form-control" placeholder="제목" />
					</div>
					<div class="form-group-inner">
						<label>내용</label>
						<textarea rows="10" cols="30" name="content"
							class="form-control" placeholder="내용"></textarea>
					</div>

					<div class="button-ap-list responsive-btn" style="float: right;">
						<div class="button-style-four btn-mg-b-10">
							<input type="button" class="btn btn-custon-four btn-primary"
								value="등록하기" onclick="send();"> <input type="reset"
								class="btn btn-custon-four btn-success" value="다시입력"> <input
								type="button" class="btn btn-custon-four btn-default"
								value="목록으로"
								onclick="location.href='/semi/sreview/list.do'">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<%@include file="jsFooter.jsp"%>
</body>

</html>