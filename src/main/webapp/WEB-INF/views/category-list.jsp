<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Categories</title>
<style>
body{
    font-family: Arial, sans-serif;
    background:#f5f5f5;
    margin:0;
    padding:20px;
}
.container{
    max-width:1000px;
    margin:auto;
    background:#fff;
    padding:20px;
    border-radius:8px;
    box-shadow:0 2px 6px rgba(0,0,0,0.1);
}
h2{margin-top:0;color:#333;text-align:center;}
.top-bar{display:flex;justify-content:space-between;align-items:center;margin-bottom:15px;}
button, .btn{
    background:#4CAF50;color:#fff;border:none;padding:8px 14px;
    border-radius:4px;cursor:pointer;text-decoration:none;
}
button:hover, .btn:hover{background:#45a049;}
.search-box input[type="text"]{padding:6px;width:200px;}
table{
    width:100%;border-collapse:collapse;margin-top:15px;
}
th,td{
    border:1px solid #ddd;padding:8px;text-align:left;
}
th{background:#4CAF50;color:#fff;}
.video-box video{max-width:200px;border-radius:6px;}
.action a{
    margin-right:8px;text-decoration:none;color:#2196F3;
}
.action a:hover{color:#0b7dda;}
.logout{color:#e74c3c;text-decoration:none;font-weight:bold;}
.logout:hover{text-decoration:underline;}
</style>
</head>
<body>
<div class="container">
    <div class="top-bar">
        <a class="logout" href="${pageContext.request.contextPath}/logout">Logout</a>
        <div class="search-box">
            <form method="get">
                <input type="text" name="kw" placeholder="Search by name" value="${param.kw}">
                <button type="submit">Search</button>
                <button type="button"
                        onclick="window.location='${pageContext.request.contextPath}/admin/category'">
                    Show All
                </button>
            </form>
        </div>
    </div>

    <div style="text-align:right;margin-bottom:10px;">
        <a class="btn" href="category?action=edit">Add New Category</a>
    </div>

    <table>
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
                    <strong>${c.name}</strong>
                    <div class="video-box">
                        <ul style="padding-left:15px;">
                            <c:forEach var="v" items="${c.videos}">
                                <li>
                                    ${v.title}<br/>
                                    <video controls>
                                        <source src="${pageContext.request.contextPath}/${v.url}" type="video/mp4">
                                    </video>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
                <td>${c.description}</td>
                <td class="action">
                    <a href="category?action=edit&id=${c.id}">Edit</a>
                    <a href="category?action=delete&id=${c.id}">Delete</a>
                    <a href="category/video?catId=${c.id}">Video</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
