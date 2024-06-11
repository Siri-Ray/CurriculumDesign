<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加研究生信息</title>
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

        input[type="text"], select {
            width: calc(100% - 22px); /* 减去边框的宽度 */
            padding: 8px; /* 调整输入框的内边距 */
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        button {
            width: 100%;
            padding: 10px;
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
            gap: 10px;
            justify-content: space-between;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>添加研究生信息</h1>
    <form id="addGraduateForm">
        <label for="name">姓名:</label>
        <input type="text" id="name" name="name" required>

        <label for="gender">性别:</label>
        <select id="gender" name="gender" required>
            <option value="男">男</option>
            <option value="女">女</option>
        </select>

        <label for="idCard">身份证号:</label>
        <input type="text" id="idCard" name="idCard" required>

        <label for="college">学院:</label>
        <input type="text" id="college" name="college" required>

        <label for="major">专业:</label>
        <input type="text" id="major" name="major" required>

        <label for="degreeType">学位类型:</label>
        <input type="text" id="degreeType" name="degreeType" required>

        <label for="tutor">导师:</label>
        <input type="text" id="tutor" name="tutor" required>

        <button type="button" onclick="submitForm()">提交</button>
    </form>
</div>

<script>
    function submitForm() {
        var formData = {
            name: document.getElementById("name").value,
            gender: document.getElementById("gender").value,
            idCard: document.getElementById("idCard").value,
            college: document.getElementById("college").value,
            major: document.getElementById("major").value,
            degreeType: document.getElementById("degreeType").value,
            tutor: document.getElementById("tutor").value
        };

        fetch("addGraduateServlet", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(formData)
        })
            .then(response => response.json())
            .then(data => {
                alert(data.message);
                if (data.success) {
                    document.getElementById("addGraduateForm").reset();
                }
            })
            .catch(error => {
                console.error("Error:", error);
            });
    }
</script>
</body>
</html>
