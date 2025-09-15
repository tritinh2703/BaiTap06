<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head><title>Category Form</title></head>
<body>
<h2>${cat == null ? "Thêm" : "Sửa"} Category</h2>
<form method="post">
    <input type="hidden" name="id" value="${cat.id}">
    Name: <input type="text" name="name" value="${cat.name}"><br>
    Description: <input type="text" name="description" value="${cat.description}"><br>
    <button type="submit">Save</button>
</form>
</body>
</html>
