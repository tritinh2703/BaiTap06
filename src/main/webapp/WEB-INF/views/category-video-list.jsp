<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Videos of Category: ${category.name}</title>
<style>
body{font-family:Arial;background:#f5f5f5;margin:0;padding:20px;}
.container{max-width:900px;margin:auto;background:#fff;padding:20px;border-radius:8px;box-shadow:0 2px 6px rgba(0,0,0,0.1);}
h2{text-align:center;color:#333;margin-top:0;}
a.back{color:#2196F3;text-decoration:none;font-weight:bold;}
a.back:hover{text-decoration:underline;}
a.btn{
    background:#4CAF50;color:#fff;border:none;padding:8px 14px;
    border-radius:4px;cursor:pointer;text-decoration:none;
}
a.btn:hover{background:#45a049;}
table{width:100%;border-collapse:collapse;margin-top:20px;}
th,td{border:1px solid #ddd;padding:8px;text-align:left;}
th{background:#4CAF50;color:#fff;}
a.action{color:#2196F3;text-decoration:none;margin-right:8px;}
a.action:hover{color:#0b7dda;}
video{max-width:200px;border-radius:6px;}
</style>
</head>
<body>
<div class="container">
    <h2>Videos of Category: ${category.name}</h2>

    <p>
      <a class="back" href="${pageContext.request.contextPath}/admin/category">Back to Categories</a>
    </p>

    <p style="text-align:right">
      <a class="btn" href="${pageContext.request.contextPath}/admin/category/video?catId=${category.id}&action=edit">
        Add New Video
      </a>
    </p>

    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Video</th>
            <th>Action</th>
        </tr>
        <c:forEach var="v" items="${list}">
            <tr>
                <td>${v.id}</td>
                <td>${v.title}</td>
                <td>
                    <video controls>
                        <source src="${pageContext.request.contextPath}/${v.url}" type="video/mp4">
                        Your browser does not support video playback.
                    </video>
                </td>
                <td>
                    <a class="action"
                       href="${pageContext.request.contextPath}/admin/category/video?catId=${category.id}&action=edit&id=${v.id}">
                       Edit
                    </a>
                    <a class="action"
                       href="${pageContext.request.contextPath}/admin/category/video?catId=${category.id}&action=delete&id=${v.id}">
                       Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
