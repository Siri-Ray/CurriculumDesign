<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>修改密码</title>
  <link rel="stylesheet" type="text/css" href="styles.css">
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
      padding: 30px;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      width: 90%;
      max-width: 500px;
    }

    h1 {
      color: #333;
      text-align: center;
      margin-bottom: 20px;
    }

    .form-group {
      margin-bottom: 15px;
    }

    label {
      display: block;
      margin-bottom: 5px;
      color: #555;
    }

    input[type="text"], input[type="password"] {
      width: calc(100% - 22px);
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }

    .form-actions {
      display: flex;
      justify-content: space-between;
      margin-top: 20px;
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

    button.reset {
      background-color: #6c757d;
    }

    button.reset:hover {
      background-color: #5a6268;
    }

    .error {
      color: red;
      margin-top: 10px;
    }
  </style>
  <script>
    function validatePassword() {
      var password = document.getElementById("newPassword").value;
      var confirmPassword = document.getElementById("confirmPassword").value;
      var errorElement = document.getElementById("error");

      var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

      if (!passwordRegex.test(password)) {
        errorElement.textContent = "密码必须包含至少8个字符，包括数字、大小字母和特殊字符。";
        return false;
      }

      if (password !== confirmPassword) {
        errorElement.textContent = "密码和确认密码不匹配。";
        return false;
      }

      errorElement.textContent = "";
      return true;
    }

    function handleSubmit(event) {
      if (!validatePassword()) {
        event.preventDefault();
        return;
      }

      var studentId = document.getElementById("studentId").value;
      var password = document.getElementById("newPassword").value;
      var confirmPassword = document.getElementById("confirmPassword").value;

      var data = {
        studentId: studentId,
        password: password,
        confirmPassword: confirmPassword
      };

      fetch('changePasswordServlet', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      })
              .then(response => response.json())
              .then(data => {
                if (data.success) {
                  alert('密码修改成功');
                  window.location.href = 'login.jsp'; // 成功后重定向到登录页面
                } else {
                  document.getElementById("error").textContent = data.message;
                }
              })
              .catch(error => {
                console.error('Error:', error);
                alert('修改密码失败');
              });

      event.preventDefault();
    }

    document.addEventListener("DOMContentLoaded", function() {
      var form = document.getElementById("changePasswordForm");
      form.addEventListener("submit", handleSubmit);
    });
  </script>
</head>
<body>
<div class="container">
  <h1>修改密码</h1>
  <form id="changePasswordForm">
    <div class="form-group">
      <label for="studentId">学号:</label>
      <input type="text" id="studentId" name="studentId" value="<%=request.getParameter("studentId")%>" readonly>
    </div>
    <div class="form-group">
      <label for="newPassword">新密码:</label>
      <input type="password" id="newPassword" name="newPassword" required>
    </div>
    <div class="form-group">
      <label for="confirmPassword">确认密码:</label>
      <input type="password" id="confirmPassword" name="confirmPassword" required>
    </div>
    <div id="error" class="error"></div>
    <div class="form-actions">
      <button type="submit">提交</button>
      <button type="reset" class="reset">重置</button>
    </div>
  </form>
</div>
</body>
</html>
