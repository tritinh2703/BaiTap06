<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head><title>Video List</title></head>
<body>
<h2>Videos</h2>
<p><a href="${pageContext.request.contextPath}/logout">Logout</a></p>
<form method="get">
  <input type="text" name="kw" placeholder="Search title">
  <button type="submit">Search</button>
</form>
<a href="video?action=edit">Add Video</a>
<table border="1">
<tr><th>ID</th><th>Title</th><th>URL</th><th>Category</th><th>Action</th></tr>
<c:forEach var="v" items="${list}">
  <tr>
    <td>${v.id}</td>
    <td>${v.title}</td>
    <td>${v.url}</td>
    <td>${v.category.name}</td>
    <td>
      <a href="video?action=edit&id=${v.id}">Edit</a>
      <a href="video?action=delete&id=${v.id}">Delete</a>
    </td>
  </tr>
</c:forEach>
</table>
</body>
</html>
