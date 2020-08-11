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
    
   
    
</head>
<body>

	<c:choose>
		<c:when test="${sessionScope.customInfo.userId !='admin'}">
			<p>페이지 접근권한이 없습니다.</p>
		</c:when>
		<c:otherwise>

	<%@include file="/project/admin/header.jsp" %>

	<form action="" ></form>

       <div class="product-status mg-b-15">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="product-status-wrap">
                            <h4>상품리스트</h4>
                            <div class="add-product">
                                <a href="master/addProduct.do">상품등록</a>
                            </div>
                            <div >
                                <table width="840">
                                    <tr>
                                        <td width ="120" align="center">카테고리</td>
                                        <td width ="120" align="center">상품번호</td>
                                        <td width ="120" align="center">상품사진</td>
                                        <td width ="120" align="center">상품명</td>
                                        <td width ="120" align="center">가&nbsp;&nbsp;격</td>
                                        <td width ="120" align="center">판매현황</td>
                                        <td width ="120" align="center">수정및삭제</td>
                                    </tr>
                              </table>

							<c:if test="${maxProNum==0 }">
								<table align="center">
									<tr>
										<td>"등록된 상품이 없습니다."</td>
									</tr>
								</table>
							</c:if>
							
							
							<table align="left">
									<c:forEach var="dto" items="${lists}" varStatus="status">

									<tr>
									<c:if test="${dto.categoryNo==1}" >
									<td width ="120" align="center">Chairs</td>
									</c:if>
									<c:if test="${dto.categoryNo==2}" >
									<td width ="120" align="center">Beds</td>
									</c:if>
									<c:if test="${dto.categoryNo==3}" >
									<td width ="120" align="center">Accesories</td>
									</c:if>
									<c:if test="${dto.categoryNo==4}" >
									<td width ="120" align="center">Furniture</td>
									</c:if>
									<c:if test="${dto.categoryNo==5}" >
									<td width ="120" align="center">Home Deco</td>
									</c:if>
									<c:if test="${dto.categoryNo==6}" >
									<td width ="120" align="center">Dressings</td>
									</c:if>
									<c:if test="${dto.categoryNo==7}" >
									<td width ="120" align="center">Tables</td>
									</c:if>
									
									<td width ="120" align="center">${dto.productNo }</td>
									<td width ="120" align="center"><a href="${imagePath}/${dto.saveFileName.replaceAll('11-1.jpg', '.jpg')}">
									<img src = "${imagePath}/${dto.saveFileName.replaceAll('11-1.jpg', '.jpg')}" width="500" height="500"/>
									</a></td>
									<td width ="120" align="center">${dto.productName}</td>
									<td width ="120" align="center">${dto.price}</td>
									
									<c:if test="${dto.status==1}" >
									<td width ="120" align="center"><button class="pd-setting">판매중</button></td>
									</c:if>
									
									<c:if test="${dto.status==2}" >
									<td width ="120" align="center"><button class="pd-setting">단종</button></td>
									</c:if>
									
									<c:if test="${dto.status==3}" >
									<td width ="120" align="center"><button class="pd-setting">입고중</button></td>
									</c:if>
									
									<td width ="120" align="center">
										<button data-toggle="tooltip" title="Edit" 
											class="pd-setting-ed" onclick="javascript:location.href='/semi/master/editProduct.do?pageNum=${pageNum }&productNo=${dto.productNo }';"/>
											<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
										</button>
										<button data-toggle="tooltip" title="Trash"
											class="pd-setting-ed" onclick="javascript:location.href='/semi/master/delProduct.do?pageNum=${pageNum }&productNo=${dto.productNo }';"/>
											<i class="fa fa-trash-o" aria-hidden="true"></i>
										</button>
									</td>
									</tr>
								</c:forEach>		
								
							</table>
                          </div>
                            <div class="custom-pagination">
								<ul class="pagination">
									<li class="page-item"><td>${pageIndexList}</td></a></li>
								</ul>
							</div>


								

							</div>
                    </div>
                </div>
            </div>
        </div>

<%@include file="/project/admin/footer.jsp" %>

    		</c:otherwise>
	</c:choose>

</body>
</html>