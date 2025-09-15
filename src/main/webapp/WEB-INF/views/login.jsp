<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head><title>Login</title></head>
<body>
<h2>Login</h2>
<c:if test="${not empty error}">
  <p style="color:red">${error}</p>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/login">
    Username: <input type="text" name="username"><br>
    Password: <input type="password" name="password"><br>
    <button type="submit">Login</button>
</form>
</body>
</html>
