<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${video == null || video.id == 0 ? "Add" : "Edit"} Video for Category: ${category.name}</title>
<style>
body{font-family:Arial;background:#f5f5f5;margin:0;padding:20px;}
.container{max-width:600px;margin:auto;background:#fff;padding:20px;border-radius:8px;box-shadow:0 2px 6px rgba(0,0,0,0.1);}
h2{text-align:center;color:#333;margin-top:0;}
form{display:flex;flex-direction:column;gap:15px;}
label{font-weight:bold;}
input[type="text"],input[type="file"]{padding:8px;border:1px solid #ccc;border-radius:4px;width:100%;}
button{background:#4CAF50;color:#fff;border:none;padding:10px;border-radius:4px;cursor:pointer;}
button:hover{background:#45a049;}
a.back{display:inline-block;margin-top:10px;color:#2196F3;text-decoration:none;}
a.back:hover{text-decoration:underline;}
.error{color:#e74c3c;}
</style>
</head>
<body>
<div class="container">
    <h2>${video == null || video.id == 0 ? "Add" : "Edit"} Video for Category: ${category.name}</h2>

    <p>
        <a class="back" href="${pageContext.request.contextPath}/admin/category/video?catId=${category.id}">
           Back to Videos
        </a>
    </p>

    <c:if test="${not empty error}">
      <p class="error">${error}</p>
    </c:if>

    <form method="post" enctype="multipart/form-data">
        <!-- id rỗng khi thêm mới để servlet nhận diện đúng -->
        <input type="hidden" name="id" value="${video.id > 0 ? video.id : ''}">
        <!-- QUAN TRỌNG: dùng categoryId (không phải catId) -->
        <input type="hidden" name="categoryId" value="${category.id}">

        <label>Title:</label>
        <input type="text" name="title" value="${video.title}">

        <label>File:</label>
        <input type="file" name="file">

        <button type="submit">Save</button>
    </form>
</div>
</body>
</html>
