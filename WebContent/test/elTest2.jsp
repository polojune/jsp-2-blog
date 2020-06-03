<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
  // String username = (String) request.getAttribute("username");
  // String password = (String) request.getAttribute("password");

%>

<html>
<head>
<meta charset="UTF-8">
<title>elTest2.jsp</title>
</head>
<body>
<h1>elTest2.jsp 파일</h1>
<hr/>
유저네임 : ${username} <br/>
패스워드 : ${sessionScope.password}<br/>
</body>
</html>