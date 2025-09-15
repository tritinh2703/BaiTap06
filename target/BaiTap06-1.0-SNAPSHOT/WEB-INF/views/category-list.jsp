<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head><title>Category List</title></head>
<body>
<h2>Categories</h2>
<p><a href="${pageContext.request.contextPath}/logout">Logout</a></p>

<form method="get">
    <input type="text" name="kw" placeholder="Tìm kiếm">
    <button type="submit">Search</button>
</form>
<a href="category?action=edit">Thêm mới</a>
<table border="1">
<tr><th>ID</th><th>Name</th><th>Action</th></tr>
<c:forEach var="c" items="${list}">
  <tr>
    <td>${c.id}</td>
    <td>${c.name}</td>
    <td>
      <a href="category?action=edit&id=${c.id}">Sửa</a>
      <a href="category?action=delete&id=${c.id}">Xóa</a>
    </td>
  </tr>
</c:forEach>
</table>
</body>
</html>
