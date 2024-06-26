<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户管理系统</title>
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
            width: 90%;
            max-width: 1200px;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }

        .search-box {
            margin-bottom: 20px;
            text-align: center;
        }

        .search-box label {
            display: inline-block;
            margin-right: 10px;
        }

        .search-box select, .search-box input[type="text"] {
            padding: 10px;
            margin-right: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            vertical-align: middle;
        }

        .search-box select {
            width: auto;
        }

        .search-box input[type="text"] {
            width: 50%;
        }

        button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background-color: #007BFF;
            color: white;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        .action-buttons {
            margin-bottom: 20px;
            text-align: center;
        }

        .action-buttons button {
            margin: 5px;
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

        .button-disabled {
            background-color: #ccc;
            cursor: not-allowed;
        }
    </style>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            showAllUsers();
        });

        function searchUsers() {
            var type = document.getElementById("searchType").value;
            var keyword = document.getElementById("searchInput").value;

            fetch("searchUsersServlet", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ type: type, keyword: keyword })
            })
                .then(response => response.json())
                .then(data => {
                    displayResults(data.users);
                })
                .catch(error => {
                    console.error("Error:", error);
                });
        }

        function showAllUsers() {
            fetch("searchUsersServlet", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ type: "all", keyword: "" })
            })
                .then(response => response.json())
                .then(data => {
                    displayResults(data.users);
                })
                .catch(error => {
                    console.error("Error:", error);
                });
        }

        function displayResults(users) {
            var tbody = document.getElementById("userTable").querySelector("tbody");
            tbody.innerHTML = "";
            users.forEach((user, index) => {
                var row = document.createElement("tr");
                row.innerHTML = `
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.college}</td>
                    <td>${user.userRole}</td>
                    <td>
                        <button onclick="editUser('${user.username}', '${user.password}', '${user.userRole}', '${user.college}')">修改</button>
                    </td>
                `;
                tbody.appendChild(row);
            });
        }

        function editUser(username, password, user_role, college) {
            window.location.href = `userEdit.jsp?username=${username}&user_role=${user_role}&college=${college}`;
        }

        function addUser() {
            window.location.href = "addUser.jsp";
        }
    </script>
</head>
<body>
<div class="container">
    <h1>用户管理系统</h1>
    <div class="search-box">
        <label for="searchType">查询方式:</label>
        <select id="searchType">
            <option value="username">账号</option>
            <option value="college">学院</option>
        </select>
        <input type="text" id="searchInput" placeholder="输入查询内容">
        <button id="searchBtn" onclick="searchUsers()">查询</button>
    </div>
    <div class="action-buttons">
        <button id="showAllUsersBtn" onclick="showAllUsers()">展示所有用户</button>
        <button id="addUserBtn" onclick="addUser()">添加用户</button>
    </div>
    <table id="userTable">
        <thead>
        <tr>
            <th>账号</th>
            <th>密码</th>
            <th>学院</th>
            <th>角色</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <!-- 动态生成 -->
        </tbody>
    </table>
</div>
</body>
</html>
