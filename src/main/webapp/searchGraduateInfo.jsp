<%--
  Created by IntelliJ IDEA.
  User: 86133
  Date: 2024/6/6
  Time: 10:49
  To change this template use File | Settings | File Templates.
  searchInfo.jsp实现查询信息界面,包括

  研究生：查看，修改部分

  导师：查询查看所有指导
  学院研究生秘书：查看查询本学院所有，导入，修改基础信息
  学院领导：查看查询本学院所有，修改基础信息
  研究生院管理员：审核，管理全校
  研究生院领导：查询、查看全校
  学校领导：查询、查看全校

  审计管理员：查看系统的日志信息

  系统管理员：设置**用户**的角色和权限，设置研究生院管理员和审计管理员

  姓名/学院/导师

  1.单输入框查询和按钮(可查询学号/姓名/学院),在本页展现学生信息(以记录的方式展示,一页有10条记录,可以翻页),可以实现模糊查询,
  2.查询所有(只有按钮),在本页列出可查询的学生列表,点击查询跳转到graduateInfo.jsp展现学生信息
  3.管理所有按钮,与2.相同,多出的是修改功能。
  4.查询,修改本学院,在本页列出本学院的学生列表
  5.添加功能(有添加按钮),点击按钮后,跳转到addGraduate.jsp页面,仅仅秘书有这个功能
  由于不同角色对应的功能不同,所以把1.单输入框查询和按钮为独立的,查询所有按钮和管理所有按钮和查看查询本学院所有按钮都是放置一起的
  我希望通过login.jsp的登录后,从Servlet获得不同的data.code,来隐藏改角色没有功能,仅仅保留其有的功能
--%>
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
                    document.getElementById("manageAllBtn").style.display = "none";
                    document.getElementById("addGraduateBtn").style.display = "none";
                    break;
                case "学院研究生秘书":
                    document.getElementById("viewAllBtn").style.display = "none";
                    break;
                case "学院领导":
                    document.getElementById("viewAllBtn").style.display = "none";
                    document.getElementById("addGraduateBtn").style.display = "none";
                    break;
                case "研究生院管理员":
                    document.getElementById("viewDeptBtn").style.display = "none";
                    document.getElementById("addGraduateBtn").style.display = "none";
                    break;
                case "研究生院领导":
                    document.getElementById("viewDeptBtn").style.display = "none";
                    document.getElementById("manageAllBtn").style.display = "none";
                    document.getElementById("addGraduateBtn").style.display = "none";
                    break;
                case "学校领导":
                    document.getElementById("viewDeptBtn").style.display = "none";
                    document.getElementById("manageAllBtn").style.display = "none";
                    document.getElementById("addGraduateBtn").style.display = "none";
                    break;
                case "审计管理员":
                    document.getElementById("searchInput").style.display = "none";
                    document.getElementById("searchBtn").style.display = "none";
                    document.getElementById("viewAllBtn").style.display = "none";
                    document.getElementById("manageAllBtn").style.display = "none";
                    document.getElementById("viewDeptBtn").style.display = "none";
                    document.getElementById("addGraduateBtn").style.display = "none";
                    break;
                case "系统管理员":
                    document.getElementById("viewAllBtn").style.display = "none";
                    document.getElementById("manageAllBtn").style.display = "none";
                    document.getElementById("viewDeptBtn").style.display = "none";
                    document.getElementById("addGraduateBtn").style.display = "none";
                    break;
            }
        });

        function search() {
            var queryType = document.getElementById("queryType").value;
            var queryValue = document.getElementById("searchInput").value;
            // 发起搜索请求，返回结果并在页面展示
            fetch("searchGraduateServlet", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ queryType: queryType, queryValue: queryValue })
            })
                .then(response => response.json())
                .then(data => {
                    displayResults(data);
                })
                .catch(error => {
                    console.error("Error:", error);
                });
        }

        function displayResults(data) {
            var resultDiv = document.getElementById("results");
            resultDiv.innerHTML = `
                <table>
                    <thead>
                        <tr>
                            <th>学号</th>
                            <th>姓名</th>
                            <th>学院</th>
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
                row.innerHTML = `
                    <td>${record.studentId}</td>
                    <td>${record.name}</td>
                    <td>${record.college}</td>
                `;
                tbody.appendChild(row);
            });

            var pagination = resultDiv.querySelector(".pagination");
            pagination.innerHTML = createPagination(data.length, 10);
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

        function queryAll() {
            // 发起查询所有请求
            fetch("queryAllServlet")
                .then(response => response.json())
                .then(data => {
                    displayResults(data);
                })
                .catch(error => {
                    console.error("Error:", error);
                });
        }

        function manageAll() {
            // 发起管理所有请求
            fetch("manageAllServlet")
                .then(response => response.json())
                .then(data => {
                    displayResults(data);
                })
                .catch(error => {
                    console.error("Error:", error);
                });
        }

        function queryDept() {
            // 发起查询本学院请求
            fetch("queryDeptServlet")
                .then(response => response.json())
                .then(data => {
                    displayResults(data);
                })
                .catch(error => {
                    console.error("Error:", error);
                });
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
            <option value="tutor">导师</option>
        </select>
        <input type="text" id="searchInput" placeholder="输入查询内容">
        <button id="searchBtn" onclick="search()">查询</button>
    </div>
    <div class="action-buttons">
        <button id="viewAllBtn" onclick="queryAll()">查询所有</button>
        <button id="manageAllBtn" onclick="manageAll()">管理所有</button>
        <button id="viewDeptBtn" onclick="queryDept()">查询本学院</button>
        <button id="addGraduateBtn" onclick="addGraduate()">添加</button>
    </div>
    <div id="results" class="results"></div>
</div>
</body>
</html>
