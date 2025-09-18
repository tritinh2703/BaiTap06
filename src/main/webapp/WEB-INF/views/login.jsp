<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
body{font-family:Arial;background:#f5f5f5;margin:0;padding:20px;}
.container{max-width:400px;margin:auto;background:#fff;padding:30px;border-radius:8px;box-shadow:0 2px 6px rgba(0,0,0,0.1);}
h2{text-align:center;color:#333;margin-top:0;}
form{display:flex;flex-direction:column;gap:15px;}
input[type="text"],input[type="password"]{
    padding:10px;border:1px solid #ccc;border-radius:4px;
}
button{
    background:#4CAF50;color:#fff;border:none;padding:10px;border-radius:4px;cursor:pointer;
}
button:hover{background:#45a049;}
.error{color:red;text-align:center;}
</style>
</head>
<body>
<div class="container">
  <h2>Login</h2>
  <c:if test="${not empty error}">
    <p class="error">${error}</p>
  </c:if>
  <form method="post" action="${pageContext.request.contextPath}/login">
    <input type="text" name="username" placeholder="Username">
    <input type="password" name="password" placeholder="Password">
    <button type="submit">Login</button>
  </form>
</div>
</body>
</html>
