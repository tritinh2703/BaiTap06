<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users</title>
<style>
body{font-family:Arial;background:#f5f5f5;margin:0;padding:20px;}
.container{max-width:800px;margin:auto;background:#fff;padding:20px;border-radius:8px;box-shadow:0 2px 6px rgba(0,0,0,0.1);}
h2{text-align:center;color:#333;}
.logout{color:#e74c3c;text-decoration:none;font-weight:bold;}
.logout:hover{text-decoration:underline;}
form{margin-bottom:15px;text-align:right;}
input[type="text"]{padding:6px;}
button,.btn{background:#4CAF50;color:white;border:none;padding:8px 14px;border-radius:4px;cursor:pointer;text-decoration:none;}
button:hover,.btn:hover{background:#45a049;}
table{width:100%;border-collapse:collapse;}
th,td{border:1px solid #ddd;padding:8px;}
th{background:#4CAF50;color:#fff;}
a.action{color:#2196F3;text-decoration:none;margin-right:8px;}
a.action:hover{color:#0b7dda;}
</style>
</head>
<body>
<div class="container">
    <div style="display:flex;justify-content:space-between;align-items:center;">
        <a class="logout" href="${pageContext.request.contextPath}/logout">Logout</a>
        <form method="get">
            <input type="text" name="kw" placeholder="Search by username" value="${param.kw}">
            <button type="submit">Search</button>
            <button type="button"
                    onclick="window.location='${pageContext.request.contextPath}/admin/user'">Show All</button>
        </form>
    </div>

    <div style="text-align:right;margin-bottom:10px;">
        <a class="btn" href="user?action=edit">Add New User</a>
    </div>

    <table>
        <tr><th>ID</th><th>Username</th><th>Role</th><th>Action</th></tr>
        <c:forEach var="u" items="${list}">
            <tr>
                <td>${u.id}</td>
                <td>${u.username}</td>
                <td>${u.role}</td>
                <td>
                    <a class="action" href="user?action=edit&id=${u.id}">Edit</a>
                    <a class="action" href="user?action=delete&id=${u.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
