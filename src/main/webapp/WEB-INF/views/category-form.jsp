<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>${cat == null ? "Add Category" : "Edit Category"}</title>
</head>
<body>
<h2>${cat == null ? "Add Category" : "Edit Category"}</h2>
<form method="post">
    <input type="hidden" name="id" value="${cat.id}">
    Name: <input type="text" name="name" value="${cat.name}"><br>
    Description: <input type="text" name="description" value="${cat.description}"><br>
    <button type="submit">Save</button>
</form>
</body>
</html>
