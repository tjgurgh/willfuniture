<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Library Assets | Kiaalap - Kiaalap Admin Template</title>
    
	<script type = "text/javascript">
	
	function sendIt() {
		
	
		var f = document.proForm;
		
		f.action="<%=cp%>/master/editProduct_ok.do";
		f.submit();
		
	}
	
	

	</script>    
    
    
    
</head>
<body>

	<c:choose>
		<c:when test="${sessionScope.customInfo.userId !='admin'}">
			<p>페이지 접근권한이 없습니다.</p>
		</c:when>
		<c:otherwise>

	<%@include file="/project/admin/header.jsp" %>

<form action="" method="post" name="proForm" enctype="multipart/form-data">

 <div class="single-pro-review-area mt-t-30 mg-b-15">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="product-payment-inner-st">
                            <ul id="myTabedu1" class="tab-review-design">
                                <li class="active"><a href="#">상품수정</a></li>
                            </ul>
                            <div id="myTabContent" class="tab-content custom-product-edit">
                                <div class="product-tab-list tab-pane fade active in" id="description">
                                    <div class="row">
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                            <div class="review-content-section">
                                                <div id="dropzone1" class="pro-ad addcoursepro">


															<c:choose>
																<c:when test="${empty dto.productNo}">



																	<div class="row">
																		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
																			<div class="form-group">
																				<select name="categoryNo" class="form-control"
																					style="width: 80%; height: 30%; font-size: 13pt; border-color: c8c8c8;">
																					<option value="0" selected>카테고리선택</option>
																					<option value="1">Chairs</option>
																					<option value="2">Beds</option>
																					<option value="3">Accesories</option>
																					<option value="4">Furniture</option>
																					<option value="5">Home Deco</option>
																					<option value="6">Dressings</option>
																					<option value="7">Tables</option>
																				</select>
																			</div>

																			<div class="form-group res-mg-t-15"></div>
																			<div class="form-group">
																				<input name="productName" type="text"
																					class="form-control" placeholder="상품명">
																			</div>

																			<div class="form-group">
																				<input name="price" type="text" class="form-control"
																					placeholder="가격">
																			</div>

																			<div class="form-group">
																				<textarea rows="7" cols="60" placeholder="상품설명"
																					class="form-control" name="proContent"></textarea>
																			</div>
																		</div>

																		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">

																			<dt>상품조회</dt>

																			<div class="form-group">
																				<table>
																					<tr>
																						<td width="80%"><input name="productNo"
																							type="text" class="form-control"
																							placeholder="상품번호"></td>
																						<td width="20%"><input name="searchProductNo"
																							type="button" value="상품번호조회" class="form-control"></td>
																				</table>
																			</div>
																			<div class="form-group res-mg-t-15">
																				<dt>상품사진1</dt>
																				<div class="form-group res-mg-t-15">
																					<input type="hidden" class="form-control">
																				</div>
																				<input name="upload1" type="file"
																					class="form-control">
																			</div>
																			<dt>상품사진2</dt>
																			<div class="form-group">
																				<input name="upload2" type="file"
																					class="form-control">
																			</div>
																			<dt>상품사진3</dt>
																			<div class="form-group">
																				<input name="upload3" type="file"
																					class="form-control">
																			</div>
																			<dt>상품사진4</dt>
																			<div class="form-group">
																				<input name="upload4" type="file"
																					class="form-control">
																			</div>
																		</div>

																	</div>
																	
																	
																	
																</c:when>
																<c:otherwise>


																	<div class="row">
																		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
																			<div class="form-group">
																				<select name="categoryNo" class="form-control"
																					style="width: 80%; height: 30%; font-size: 13pt; border-color: c8c8c8;">
																					
																					
																					
																					<c:if test="${dto.categoryNo==1 }">
																					<option value="0" >카테고리선택</option>
																					<option value="1" selected>Chairs</option>
																					<option value="2">Beds</option>
																					<option value="3">Accesories</option>
																					<option value="4">Furniture</option>
																					<option value="5">Home Deco</option>
																					<option value="6">Dressings</option>
																					<option value="7">Tables</option>
																					</c:if>
																					
																					<c:if test="${dto.categoryNo==2 }">
																					<option value="0" >카테고리선택</option>
																					<option value="1" >Chairs</option>
																					<option value="2" selected>Beds</option>
																					<option value="3">Accesories</option>
																					<option value="4">Furniture</option>
																					<option value="5">Home Deco</option>
																					<option value="6">Dressings</option>
																					<option value="7">Tables</option>
																					</c:if>
																					
																					<c:if test="${dto.categoryNo==3 }">
																					<option value="0" >카테고리선택</option>
																					<option value="1" >Chairs</option>
																					<option value="2">Beds</option>
																					<option value="3" selected>Accesories</option>
																					<option value="4">Furniture</option>
																					<option value="5">Home Deco</option>
																					<option value="6">Dressings</option>
																					<option value="7">Tables</option>
																					</c:if>
																					
																					<c:if test="${dto.categoryNo==4 }">
																					<option value="0" >카테고리선택</option>
																					<option value="1" >Chairs</option>
																					<option value="2">Beds</option>
																					<option value="3">Accesories</option>
																					<option value="4" selected>Furniture</option>
																					<option value="5">Home Deco</option>
																					<option value="6">Dressings</option>
																					<option value="7">Tables</option>
																					</c:if>
																					
																					<c:if test="${dto.categoryNo==5 }">
																					<option value="0" >카테고리선택</option>
																					<option value="1" selected>Chairs</option>
																					<option value="2">Beds</option>
																					<option value="3">Accesories</option>
																					<option value="4">Furniture</option>
																					<option value="5" selected>Home Deco</option>
																					<option value="6">Dressings</option>
																					<option value="7">Tables</option>
																					</c:if>
																					
																					<c:if test="${dto.categoryNo==6 }">
																					<option value="0" >카테고리선택</option>
																					<option value="1" >Chairs</option>
																					<option value="2">Beds</option>
																					<option value="3">Accesories</option>
																					<option value="4">Furniture</option>
																					<option value="5">Home Deco</option>
																					<option value="6" selected>Dressings</option>
																					<option value="7">Tables</option>
																					</c:if>
																					
																					<c:if test="${dto.categoryNo==7 }">
																					<option value="0" >카테고리선택</option>
																					<option value="1" >Chairs</option>
																					<option value="2">Beds</option>
																					<option value="3">Accesories</option>
																					<option value="4">Furniture</option>
																					<option value="5">Home Deco</option>
																					<option value="6">Dressings</option>
																					<option value="7" selected>Tables</option>
																					</c:if>
																					
																					
																					
																					
																					
																				</select>
																			</div>

																			<div class="form-group res-mg-t-15"></div>
																			<div class="form-group">
																				<input name="productName" type="text"
																					class="form-control" value=${dto.productName }>
																			</div>

																			<div class="form-group">
																				<input name="price" type="text" class="form-control"
																					value=${dto.price }>
																			</div>

																			<div class="form-group">
																				<textarea rows="7" cols="60" placeholder="상품설명"
																					class="form-control" name="proContent" >${dto.proContent }</textarea>
																			</div>
																		</div>

																		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">

																			<dt>상품번호</dt>

																			<div class="form-group">
																				<table>
																					<tr>
																						<td width="80%"><input name="productNo"
																							type="text" class="form-control"
																							value=${dto.productNo }></td>
																						<!-- <td width="20%"><input name="searchProductNo"
																							type="button" value="상품번호" class="form-control"></td> -->
																				</table>
																			</div>
																			<div class="form-group res-mg-t-15">
																				<dt>상품사진1</dt>
																				<div class="form-group res-mg-t-15">
																					<input type="hidden" class="form-control">
																				</div>
																				<input name="upload1" type="file"
																					class="form-control">
																			</div>
																			<dt>상품사진2</dt>
																			<div class="form-group">
																				<input name="upload2" type="file"
																					class="form-control">
																			</div>
																			<dt>상품사진3</dt>
																			<div class="form-group">
																				<input name="upload3" type="file"
																					class="form-control">
																			</div>
																			<dt>상품사진4</dt>
																			<div class="form-group">
																				<input name="upload4" type="file"
																					class="form-control">
																			</div>
																		</div>

																	</div>
																	    		</c:otherwise>
																					</c:choose>














																	<div class="row">
																		<div class="col-lg-12">
																			<div class="button-style-three">
																				<button type="button"
																					class="btn btn-custon-rounded-three btn-success"
																					onclick="sendIt();">
																					수정완료</button>
																				<input type="hidden" name="pageNum" value="${pageNum }"	>
																					
																					
																				<button type="reset"
																					class="btn btn-custon-rounded-three btn-primary"
																					onclick="categoryNo.focus();">다시입력</button>
																				<button type="button"
																					class="btn btn-custon-rounded-three btn-warning"
																					onclick="javascript:location.href='/semi/master/productList.do'"/ >작성취소</button>
																			</div>

																		</div>
																	</div></div>
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
  </form> 



<%@include file="/project/admin/footer.jsp" %>

    		</c:otherwise>
	</c:choose>

<body>

</body>
</html>