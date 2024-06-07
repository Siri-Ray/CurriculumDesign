<%--
  Created by IntelliJ IDEA.
  User: 86133
  Date: 2024/6/6
  Time: 11:49
  To change this template use File | Settings | File Templates.
  addGraduate.jsp 实现添加研究生信息的界面
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加研究生信息</title>
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

        input[type="text"] {
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
    </style>
</head>
<body>
<div class="container">
    <h1>添加研究生信息</h1>
    <form action="addGraduateServlet" method="post">
        <div class="form-group">
            <label for="studentId">学号:</label>
            <input type="text" id="studentId" name="studentId" required>
        </div>
        <div class="form-group">
            <label for="name">姓名:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="college">学院:</label>
            <input type="text" id="college" name="college" required>
        </div>
        <div class="form-actions">
            <button type="submit">提交</button>
            <button type="reset" class="reset">重置</button>
        </div>
    </form>
</div>
</body>
</html>
