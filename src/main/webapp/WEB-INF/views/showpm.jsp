<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/myscript.js" />"></script>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<sec:authentication var="user" property="principal" />
<title>Private Messages</title>

</head>
<body>
	<div id="userinfo">
		<p align="right">
			Logged in as ${user.login}, click <a href="${pageContext.request.contextPath}/logout">here</a> to
			log out
		</p>
		<p align="right">
			Back to the <a href="${pageContext.request.contextPath}/board">Board</a>
		</p>
	</div>
	<h3 align="center">New private messages</h3>

  <c:forEach items="${messages}" var="message">
    <div class="message">
      <table class="board">
        <tr>
          <td style="width: 15%"><p align="center">
              <fmt:formatDate type="both" value="${message.date}"></fmt:formatDate>
            </p></td>
          <td style="width: 20%"><p align="center">
              <c:choose>
                <c:when test="${message.author.login eq user.login}">
                  <strong>You</strong>
                </c:when>
                <c:otherwise>
                  <a href="${pageContext.request.contextPath}/board/pmto/${message.author.login}"> <strong><c:out
                        value="${message.author.login}"></c:out></strong></a>
                </c:otherwise>
              </c:choose>
            </p></td>
          <td style="width: 65%"><p class="mbody" style="padding: 10px;">
              <c:out value="${message.body}"></c:out>
            </p></td>
        </tr>
      </table>
    </div>
  </c:forEach>

</body>
</html>