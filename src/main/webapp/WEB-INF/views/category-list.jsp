<%@ taglib uri="jakarta.tags.core" prefix="c"%>
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
    <th>Name & Videos</th>
    <th>Description</th>
    <th>Action</th>
</tr>
<c:forEach var="c" items="${list}">
  <tr>
    <td>${c.id}</td>
    <td>
       <b>${c.name}</b>
       <ul>
         <c:forEach var="v" items="${c.videos}">
            <li>
              ${v.title}
              <br/>
              <video width="240" controls>
                 <source src="${pageContext.request.contextPath}/${v.url}" type="video/mp4">
              </video>
            </li>
         </c:forEach>
       </ul>
    </td>
    <td>${c.description}</td>
    <td>
      <a href="category?action=edit&id=${c.id}">Edit</a>
      <a href="category?action=delete&id=${c.id}">Delete</a>
      <a href="../admin/video?action=edit&catId=${c.id}">Add Video</a>
    </td>
  </tr>
</c:forEach>
</table>
</body>
</html>
