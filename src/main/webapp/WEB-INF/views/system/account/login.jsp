<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  <form method="POST" action="/system/account/loginProcess" autocomplete="off">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div>
      <label for="memberId">사용자 ID</label>
      <input type="text" class="form-control"
             name="memberId" id="memberId" value="">
    </div>
    <div>
      <label for="memberPassword">비밀번호</label>
      <input type="password" class="form-control"
             name="memberPassword" id="memberPassword" value="">
    </div>
    <div class="buttons-bottom">
      <button type="submit" class="btn btn-100 btn-primary">로그인</button>
    </div>
  </form>
</div>
</body>
</html>