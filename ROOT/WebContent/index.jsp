<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Look At !</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css"/>
    <link rel="stylesheet" href="/css/mainpage/mainpage.css" type="text/css">
    <!-- Font -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@500&family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>
<body>
 <%@ include file="/view/header.jsp" %>
<div name="wrap" id="wrap" class="wrap">
<%

	response.sendRedirect("/index.do");

%>

    <!-- 인기 전시회 포스터  -->
    <!-- Swiper -->
    <div class="swiper mySwiper">
        <div class="swiper-wrapper">
        <c:forEach var="item" items="${exhibitionPage.content}">
            <div class="swiper-slide">
   			    <a href="/exhibition/read.do?no=${item.exhibition_no}">
                <img src="<%=request.getContextPath() %>/view/image/${item.thumbnail}"/>
                </a>
            </div>
         </c:forEach>
        </div>
        <div class="swiper-pagination"></div>
        <div class="autoplay-progress">
            <svg viewBox="0 0 48 48">
                <%-- <circle cx="24" cy="24" r="20"></circle> --%>
            </svg>
            <span></span>
        </div>
    </div>

        <!-- 전시회 게시글 등록순 ASC -->
    	<div class="exhibitionContainer">
			<c:forEach var="item" items="${exhibitionPage.content}">
				<a href="/exhibition/read.do?no=${item.exhibition_no}">
					<div class="item" width="1280px">
						<img src="<%=request.getContextPath() %>/view/image/${item.thumbnail}" style="width: 300px; height:418px;"/>
					</div>
				</a>
			</c:forEach>
		</div>
    </div>

    <!-- 이 자리에는 사이트 공통 푸터가 들어가면 될 것같다. -->
    <div name="footer" id="footer">
        <h2>footer</h2>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
<script src="/js/mainpage/swiper.js"></script>

</body>
</html>