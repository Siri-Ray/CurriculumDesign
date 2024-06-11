<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>研究生学籍管理系统 - 登录</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('background.jpg'); /* 背景图片路径 */
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            width: 400px;
            text-align: center;
        }
        .login-container h2 {
            margin-bottom: 20px;
        }
        .login-container img {
            width: 50px;
            height: 50px;
            vertical-align: middle;
            margin-right: 10px;
        }
        .login-container input,
        .login-container select {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 10px 20px 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .login-container button {
            width: calc(100% - 20px);
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin: 10px;
        }
        .login-container button:hover {
            background-color: #0056b3;
        }
        .login-title {
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 30px;
        }
    </style>
    <script>
        function login() {
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            var userType = document.getElementById("userType").value;

            var data = {
                username: username,
                password: password
            };

            var url;
            if (userType === "graduate") {
                url = "graduateLoginServlet";
            } else {
                url = "otherRolesLoginServlet";
            }

            fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
                .then(response => response.json())
                .then(data => {
                    switch(data.code) {
                        case 0:
                            alert("登录成功");
                            switch(userType) {
                                case "graduate":
                                    window.location.href = "graduateInfo.jsp?username=" + username;
                                    break;
                                case "auditAdmin":
                                    window.location.href = "auditLogs.jsp?username=" + username;
                                    break;
                                case "systemAdmin":
                                    window.location.href = "userManagement.jsp?username=" + username;
                                    break;
                                default:
                                    window.location.href = "searchGraduateInfo.jsp?username=" + username;
                                    break;
                            }
                            break;
                        case 1:
                            alert("错误：无效的用户名或密码，登录失败次数：" + data.failedAttempts);
                            break;
                        case 2:
                            alert("错误：账户已锁定");
                            break;
                        default:
                            alert("未知错误");
                            window.location.href = "unknownError.jsp";
                            break;
                    }
                })
                .catch(error => {
                    console.error("Error:", error);
                });
        }
    </script>
</head>
<body>
<div class="login-container">
    <div class="login-title">
        <img src="logo.jpeg" alt="University Logo"> <!-- 替换为你的logo图片路径 -->
        <h2>研究生管理系统</h2>
    </div>
    <input type="text" id="username" placeholder="账号" required>
    <input type="password" id="password" placeholder="密码" required>
    <select id="userType">
        <option value="graduate">研究生</option>
        <option value="tutor">导师</option>
        <option value="secretary">学院研究生秘书</option>
        <option value="collegeLeader">学院领导</option>
        <option value="gradSchoolAdmin">研究生院管理员</option>
        <option value="gradSchoolLeader">研究生院领导</option>
        <option value="schoolLeader">学校领导</option>
        <option value="auditAdmin">审计管理员</option>
        <option value="systemAdmin">系统管理员</option>
    </select>
    <button onclick="login()">登录</button>
</div>
</body>
</html>
