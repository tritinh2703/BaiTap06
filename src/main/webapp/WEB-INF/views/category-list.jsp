<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Categories</title>
</head>
<body>
<h2>Categories</h2>
<p><a href="${pageContext.request.contextPath}/logout">Logout</a></p>

<form method="get">
    <input type="text" name="kw" placeholder="Search by name">
    <button type="submit">Search</button>
</form>
<a href="category?action=edit">Add New Category</a>
<table border="1" cellpadding="5" cellspacing="0">
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Description</th>
    <th>Action</th>
</tr>
<c:forEach var="c" items="${list}">
  <tr>
    <td>${c.id}</td>
    <td>${c.name}</td>
    <td>${c.description}</td>
    <td>
      <a href="category?action=edit&id=${c.id}">Edit</a>
      <a href="category?action=delete&id=${c.id}">Delete</a>
    </td>
  </tr>
</c:forEach>
</table>
</body>
</html>
