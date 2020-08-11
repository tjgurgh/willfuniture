<%@page import="com.member.MemberDTO"%>
<%@page import="com.util.DBCPConn"%>
<%@page import="com.member.MemberDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
	String userId = request.getParameter("userId");
	Connection conn = DBCPConn.getConnection();
	
	MemberDAO dao = new MemberDAO(conn);
	MemberDTO dto = dao.getReadData(userId);
		
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>

	function idCheckClose(userId){
		
		opener.myForm.userId.value = userId;
		window.close();
		opener.myForm.idChecked.value = "idChecked";
		opener.myForm.userIdCk.value = userId;
		opener.myForm.userPwd.focus();
		
	}
	
	function idReCheck(userId){
		
		opener.myForm.userId.value = "";
		window.close();
		opener.myForm.userId.focus();
		
	}
	
	
</script>

</head>
<body>
<form action="idCheck.jsp" method="post">

<c:choose>
	<c:when test="${!empty message }">
	<br/>
	<div align="center">
    	<font color="red"><b>${message }</b></font><br/>
		<input type="button" value="다시입력" onclick="idReCheck();"/><br/>
	</div>
	</c:when>
	<c:otherwise>
	<div align="center">
	<br/><br/>
		<font color="blue"><b>${userId } (은)는 사용가능한 아이디입니다.</b></font>
	<input type="button" value="현재 아이디 사용" onclick="idCheckClose('<%=userId %>')">
	</div>
	</c:otherwise>
</c:choose>		

</form>

</body>
</html>