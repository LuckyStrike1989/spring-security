<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" type="text/css" href="/css/default.css">
</head>
<body>
<div class="login_form">
  <h1 class="form_title">로그인</h1>
  <c:if test="${not empty error}">
    <div class="error-message">
      로그인이 실패 했습니다.
    </div>
  </c:if>
  <form method="POST" action="/system/account/loginProcess" autocomplete="off">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div>
      <label for="memberId">사용자 ID</label>
      <input type="text" class="form-control"
             name="memberId" id="memberId" value="${memberId}">
      <c:forEach var="error" items="${errors}">
        <c:if test="${error.key == 'memberId'}">
          <div class="invalid-feedback">${error.value}</div>
        </c:if>
      </c:forEach>
    </div>
    <div>
      <label for="memberPassword">비밀번호</label>
      <input type="password" class="form-control"
             name="memberPassword" id="memberPassword" value="">
      <c:forEach var="error" items="${errors}">
        <c:if test="${error.key == 'memberPassword'}">
          <div class="invalid-feedback">${error.value}</div>
        </c:if>
      </c:forEach>
    </div>
    <div class="buttons-bottom">
      <button type="submit" class="btn btn-100 btn-primary">로그인</button>
    </div>
  </form>
</div>
</body>
</html>