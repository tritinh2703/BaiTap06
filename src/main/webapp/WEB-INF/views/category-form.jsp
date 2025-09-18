<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${cat == null ? "Add Category" : "Edit Category"}</title>
<style>
body{font-family:Arial;background:#f5f5f5;margin:0;padding:20px;}
.container{max-width:600px;margin:auto;background:#fff;padding:20px;border-radius:8px;box-shadow:0 2px 6px rgba(0,0,0,0.1);}
h2{text-align:center;color:#333;margin-top:0;}
form{display:flex;flex-direction:column;gap:12px;}
label{font-weight:bold;}
input[type="text"], textarea{
    padding:8px;border:1px solid #ccc;border-radius:4px;width:100%;
}
button{background:#4CAF50;color:#fff;border:none;padding:10px;border-radius:4px;cursor:pointer;}
button:hover{background:#45a049;}
a.back{display:inline-block;margin-top:10px;color:#2196F3;text-decoration:none;}
a.back:hover{text-decoration:underline;}
</style>
</head>
<div class="container">
<h2>${cat == null ? "Add Category" : "Edit Category"}</h2>
<form method="post">
    <input type="hidden" name="id" value="${cat.id}">
    <label>Name:</label>
    <input type="text" name="name" value="${cat.name}">
    <label>Description:</label>
    <textarea name="description">${cat.description}</textarea>
    <button type="submit">Save</button>
</form>
<p style="text-align:center">
  <a class="back" href="${pageContext.request.contextPath}/admin/category">Back</a>
</p>
</div>
</body>
</html>
