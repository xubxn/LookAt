<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cPath" value="<%=request.getContextPath()%>"></c:set>  
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<style>
.footer {
    position: relatives;  
    bottom: 0;
    left: 60%;
    transform: translateX(10%);
    margin: 50px;
}
</style>
<!-- Bootstrap 4 CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="../../js/jquery-3.7.0.min.js"></script>
</head>
<body>
	<div class="container">
		</div>
	<footer class="footer">
		<img src="${cPath}/view/image/footerinfo.png" style="width:1300px;"/>
	</footer>
		<!-- Bootstrap 4 JS -->
<%@ include file="bootstrap4js.jsp" %> 
</body>
</html>