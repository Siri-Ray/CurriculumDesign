<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加用户信息</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
            max-width: 90%;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }

        form {
            display: grid;
            gap: 10px;
        }

        label {
            font-weight: bold;
        }

        input[type="text"], input[type="password"], select {
            width: calc(100% - 22px); /* 减去边框的宽度 */
            padding: 8px; /* 调整输入框的内边距 */
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        button {
            width: 100%;
            padding: 8px; /* 调整按钮的内边距 */
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

        .button-group {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>添加用户信息</h1>
    <form id="addUserForm">
        <label for="username">用户名:</label>
        <input type="text" id="username" name="username" required>

        <label for="name">姓名:</label>
        <input type="text" id="name" name="name" required>

        <label for="password">密码:</label>
        <input type="password" id="password" name="password" required>

        <label for="role">用户角色:</label>
        <select id="role" name="role" required>
            <option value="supervisor">导师</option>
            <option value="secretary">学院研究生秘书</option>
            <option value="collegeLeader">学院领导</option>
            <option value="graduateAdmin">研究生院管理员</option>
            <option value="graduateLeader">研究生院领导</option>
            <option value="schoolLeader">学校领导</option>
            <option value="auditAdmin">审计管理员</option>
            <option value="systemAdmin">系统管理员</option>
        </select>

        <label for="college">学院:</label>
        <input type="text" id="college" name="college" required>

        <div class="button-group">
            <button type="button" onclick="submitForm()" style="padding: 7px;">提交</button>
            <button type="button" onclick="goBack()" style="padding: 7px;">返回</button>
        </div>
    </form>
</div>

<script>
    function submitForm() {
        var data = {
            username: document.getElementById("username").value,
            name: document.getElementById("name").value,
            password: document.getElementById("password").value,
            role: document.getElementById("role").value,
            college: document.getElementById("college").value
        };

        fetch("addUserServlet", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => {
                alert(data.message);
                if (data.success) {
                    document.getElementById("addUserForm").reset();
                }
            })
            .catch(error => {
                console.error("Error:", error);
            });
    }

    function goBack() {
        window.location.href = "userManagement.jsp";
    }
</script>
</body>
</html>
