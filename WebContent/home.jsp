
<%@page import="com.cos.blog.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="include/nav.jsp"%>


<div class="container">

<c:forEach var="board" items="${boards}"> 

	<div class="card m-2" style="width: 100%">
		<!-- 고정 픽셀보다는 화면 퍼센트로 지정 -->
		<!-- <img class="card-img-top" src="img_avatar1.png" alt="Card image">  -->
		<div class="card-body">
            <h4 class="card-title">${board.title}</h4>
            <p class="card-text">${board.content}</p>
			<a href="#" class="btn btn-primary">상세 보기</a>
		</div>
	</div>
</c:forEach>


</div>


<%@ include file="include/footer.jsp"%>