<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head><meta charset="UTF-8"><title>Video Form</title></head>
<body>
<h2>${video == null ? "Add Video" : "Edit Video"}</h2>
<form method="post">
  <input type="hidden" name="id" value="${video.id}">
  Title: <input type="text" name="title" value="${video.title}"><br>
  URL: <input type="text" name="url" value="${video.url}"><br>
  Category:
  <select name="categoryId">
    <c:forEach var="c" items="${categories}">
      <option value="${c.id}" ${video.category.id==c.id?"selected":""}>${c.name}</option>
    </c:forEach>
  </select><br>
  <button type="submit">Save</button>
</form>
</body>
</html>
