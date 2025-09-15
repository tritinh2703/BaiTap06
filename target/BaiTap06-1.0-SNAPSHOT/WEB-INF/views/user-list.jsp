<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head><title>User List</title></head>
<body>
<h2>Users</h2>
<p><a href="${pageContext.request.contextPath}/logout">Logout</a></p>

<form method="get">
  <input type="text" name="kw" placeholder="Search username">
  <button type="submit">Search</button>
</form>
<a href="user?action=edit">Add User</a>
<table border="1">
<tr><th>ID</th><th>Username</th><th>Role</th><th>Action</th></tr>
<c:forEach var="u" items="${list}">
  <tr>
    <td>${u.id}</td>
    <td>${u.username}</td>
    <td>${u.role}</td>
    <td>
      <a href="user?action=edit&id=${u.id}">Edit</a>
      <a href="user?action=delete&id=${u.id}">Delete</a>
    </td>
  </tr>
</c:forEach>
</table>
</body>
</html>
