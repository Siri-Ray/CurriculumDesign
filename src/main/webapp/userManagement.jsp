<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            height: 100vh;
            margin: 0;
            padding-top: 20px;
        }

        .container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 1000px;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        .search-box {
            margin-bottom: 20px;
            text-align: center;
        }

        input[type="text"], select {
            width: 200px;
            padding: 10px;
            margin-right: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background-color: #007BFF;
            color: white;
            cursor: pointer;
            font-size: 14px;
        }

        button:hover {
            background-color: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>用户管理系统</h1>
    <div class="search-box">
        <select id="searchType">
            <option value="username">账号</option>
            <option value="name">姓名</option>
            <option value="college">学院</option>
        </select>
        <input type="text" id="searchInput" placeholder="Enter keyword">
        <button onclick="searchUsers()">查询</button>
        <button onclick="showAllUsers()">展示所有用户</button>
    </div>
    <div id="users" class="users"></div>
    <div id="pagination" class="pagination"></div>
</div>

<script>
    let currentPage = 1;
    const itemsPerPage = 10;

    function searchUsers() {
        var type = document.getElementById("searchType").value;
        var keyword = document.getElementById("searchInput").value;
        fetchUsers("searchUsersServlet", type, keyword, 1);
    }

    function showAllUsers() {
        fetchUsers("showAllUsersServlet", "", "", 1);
    }

    function fetchUsers(url, type, keyword, page) {
        var params = new URLSearchParams({ type, keyword, page });
        fetch(`${url}?${params.toString()}`)
            .then(response => response.json())
            .then(data => {
                displayUsers(data.users);
                setupPagination(data.total, page);
            })
            .catch(error => {
                console.error("Error:", error);
            });
    }

    function displayUsers(data) {
        var usersDiv = document.getElementById("users");
        usersDiv.innerHTML = `
            <table>
                <thead>
                    <tr>
                        <th>账号</th>
                        <th>密码</th>
                        <th>姓名</th>
                        <th>学院</th>
                        <th>角色</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        `;

        var tbody = usersDiv.querySelector("tbody");
        data.forEach((user) => {
            var row = document.createElement("tr");
            row.innerHTML = `
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.name}</td>
                <td>${user.college}</td>
                <td>${user.role}</td>
                <td><button onclick="editUser('${user.username}', '${user.password}', '${user.role}', '${user.college}')">修改</button></td>
            `;
            tbody.appendChild(row);
        });
    }

    function setupPagination(totalItems, currentPage) {
        var paginationDiv = document.getElementById("pagination");
        var totalPages = Math.ceil(totalItems / itemsPerPage);
        paginationDiv.innerHTML = '';

        for (let i = 1; i <= totalPages; i++) {
            var button = document.createElement("button");
            button.textContent = i;
            button.onclick = () => fetchUsers(currentUrl, currentType, currentKeyword, i);
            if (i === currentPage) {
                button.disabled = true;
            }
            paginationDiv.appendChild(button);
        }
    }

    function editUser(username, password, role, college) {
        window.location.href = `editUser.jsp?username=${username}&password=${password}&role=${role}&college=${college}`;
    }
</script>
</body>
</html>
