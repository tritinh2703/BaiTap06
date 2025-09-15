<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head><meta charset="UTF-8"><title>User Form</title></head>
<body>
<h2>${u == null ? "Add User" : "Edit User"}</h2>
<form method="post">
  <input type="hidden" name="id" value="${u.id}">
  Username: <input type="text" name="username" value="${u.username}"><br>
  Password: <input type="password" name="password" value="${u.password}"><br>
  Role:
  <select name="role">
    <option value="admin" ${u.role=="admin"?"selected":""}>Admin</option>
    <option value="user"  ${u.role=="user"?"selected":""}>User</option>
  </select><br>
  <button type="submit">Save</button>
</form>
</body>
</html>
