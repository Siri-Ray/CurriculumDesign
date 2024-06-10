<%--
  Created by IntelliJ IDEA.
  User: 86133
  Date: 2024/6/7
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>审查日志</title>
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

        input[type="text"] {
            width: 60%;
            padding: 10px;
            margin-right: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
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
    <h1>审查日志</h1>
    <div class="search-box">
        <select id="searchType">
            <option value="username">用户名</option>
            <option value="operation">操作类型</option>
        </select>
        <input type="text" id="searchInput" placeholder="输入关键词">
        <button onclick="searchLogs()">查询</button>
        <button onclick="showAllLogs()">展示所有日志记录</button>
    </div>
    <div id="logs" class="logs"></div>
</div>

<script>
    function searchLogs() {
        var type = document.getElementById("searchType").value;
        var keyword = document.getElementById("searchInput").value;
        fetch("searchLogsServlet?type=" + type + "&keyword=" + keyword)
            .then(response => response.json())
            .then(data => {
                displayLogs(data);
            })
            .catch(error => {
                console.error("Error:", error);
            });
    }

    function showAllLogs() {
        fetch("showAllLogsServlet")
            .then(response => response.json())
            .then(data => {
                displayLogs(data);
            })
            .catch(error => {
                console.error("Error:", error);
            });
    }

    function displayLogs(data) {
        var logsDiv = document.getElementById("logs");
        logsDiv.innerHTML = `
            <table>
                <thead>
                    <tr>
                        <th>用户名</th>
                        <th>操作</th>
                        <th>详情</th>
                        <th>时间</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        `;

        var tbody = logsDiv.querySelector("tbody");
        data.forEach((log, index) => {
            var row = document.createElement("tr");
            row.innerHTML = `
                <td>${log.username}</td>
                <td>${log.operation}</td>
                <td>${log.details}</td>
                <td>${log.timestamp}</td>
            `;
            tbody.appendChild(row);
        });
    }
</script>
</body>
</html>

