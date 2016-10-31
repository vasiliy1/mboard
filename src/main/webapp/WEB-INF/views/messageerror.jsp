<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/myscript.js" />"></script>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<title>Error page</title>
</head>
<body>
	<h2>An error occured</h2>
	
	<br>
  <c:forEach items="${errors}" var="error" >
    <p align="center" style="color: red">${error}</p>  
  </c:forEach>
  <p><a href="${pageContext.request.contextPath}/board">Back to Message Board</a></p>

</body>
</html>