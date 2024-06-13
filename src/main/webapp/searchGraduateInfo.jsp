<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>研究生信息查询</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
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
            max-width: 800px;
        }

        h1 {
            color: #333;
            text-align: center;
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

        .pagination {
            display: flex;
            justify-content: center;
        }

        .pagination button {
            margin: 0 5px;
        }
    </style>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var dataCode = "<%=request.getAttribute("dataCode")%>";

            // 根据data.code显示或隐藏功能
            switch(dataCode) {
                case "导师":
                    document.getElementById("viewAllBtn").style.display = "none";
                    document.getElementById("addGraduateBtn").style.display = "none";
                    break;
                case "学院研究生秘书":
                    break;
                case "学院领导":
                    document.getElementById("addGraduateBtn").style.display = "none";
                    break;
                case "研究生院管理员":
                case "研究生院领导":
                case "学校领导":
                    document.getElementById("addGraduateBtn").style.display = "none";
                    break;
                default:
                    document.getElementById("viewAllBtn").style.display = "none";
                    document.getElementById("addGraduateBtn").style.display = "none";
                    break;
            }
        });

        function search() {
            var queryType = document.getElementById("queryType").value;
            var queryValue = document.getElementById("searchInput").value;
            var dataCode = "<%=request.getAttribute("dataCode")%>";

            // 发起搜索请求，返回结果并在页面展示
            fetch("searchGraduateServlet", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ queryType: queryType, queryValue: queryValue, dataCode: dataCode })
            })
                .then(response => response.json())
                .then(data => {
                    displayResults(data, dataCode);
                })
                .catch(error => {
                    console.error("Error:", error);
                });
        }

        function queryAll() {
            var dataCode = "<%=request.getAttribute("dataCode")%>";

            // 发起查询所有请求
            fetch("searchGraduateServlet", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ queryType: "all", queryValue: "", dataCode: dataCode })
            })
                .then(response => response.json())
                .then(data => {
                    displayResults(data, dataCode);
                })
                .catch(error => {
                    console.error("Error:", error);
                });
        }

        function displayResults(data, dataCode) {
            var resultDiv = document.getElementById("results");
            resultDiv.innerHTML = `
                <table>
                    <thead>
                        <tr>
                            <th>学号</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>身份证号</th>
                            <th>学院</th>
                            <th>专业</th>
                            <th>学位类型</th>
                            <th>导师</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <div class="pagination"></div>
            `;

            var tbody = resultDiv.querySelector("tbody");
            data.forEach((record) => {
                var row = document.createElement("tr");
                var buttonsHtml = `<button onclick="viewGraduate('${record.studentId}')">查询</button>`;
                if (canManage(record, dataCode)) {
                    buttonsHtml += `<button onclick="editGraduate('${record.studentId}')">修改</button>`;
                }
                row.innerHTML = `
                    <td>${record.studentId}</td>
                    <td>${record.name}</td>
                    <td>${record.gender}</td>
                    <td>${record.idCard}</td>
                    <td>${record.college}</td>
                    <td>${record.major}</td>
                    <td>${record.degreeType}</td>
                    <td>${record.tutor}</td>
                    <td>${buttonsHtml}</td>
                `;
                tbody.appendChild(row);
            });

            var pagination = resultDiv.querySelector(".pagination");
            pagination.innerHTML = createPagination(data.length, 10);
        }

        function canManage(record, dataCode) {
            // 根据用户角色和学生记录判断是否可以管理
            switch (dataCode) {
                case "学院研究生秘书":
                case "学院领导":
                case "研究生院管理员":
                    return true;
                case "研究生院领导":
                case "学校领导":
                    return false;
                default:
                    return false;
            }
        }

        function createPagination(totalItems, itemsPerPage) {
            var totalPages = Math.ceil(totalItems / itemsPerPage);
            totalPages = Math.min(totalPages, 10); // 最多10页
            var paginationHtml = '';
            for (var i = 1; i <= totalPages; i++) {
                paginationHtml += `<button onclick="changePage(${i})">${i}</button>`;
            }
            return paginationHtml;
        }

        function changePage(pageNumber) {
            // 实现分页逻辑，这里只是一个示例
            console.log("Change to page: " + pageNumber);
        }

        function viewGraduate(studentId) {
            window.location.href = `graduateInfo.jsp?studentId=${studentId}`;
        }

        function editGraduate(studentId) {
            window.location.href = `graduateInfo.jsp?studentId=${studentId}`;
        }

        function addGraduate() {
            window.location.href = "addGraduate.jsp";
        }
    </script>
</head>
<body>
<div class="container">
    <h1>研究生管理系统</h1>
    <div class="search-box">
        <label for="queryType">查询方式:</label>
        <select id="queryType">
            <option value="studentId">学号</option>
            <option value="name">姓名</option>
            <option value="college">学院</option>
        </select>
        <input type="text" id="searchInput" placeholder="输入查询内容">
        <button id="searchBtn" onclick="search()">查询</button>
    </div>
    <div class="action-buttons">
        <button id="viewAllBtn" onclick="queryAll()">查询所有</button>
        <button id="addGraduateBtn" onclick="addGraduate()">添加</button>
    </div>
    <div id="results" class="results"></div>
</div>
</body>
</html>
