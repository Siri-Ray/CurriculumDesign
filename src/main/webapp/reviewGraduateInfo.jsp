<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>审核研究生信息</title>
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

    button {
      padding: 6px 12px;
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

    .button-disabled {
      background-color: #ccc;
      cursor: not-allowed;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>审核研究生信息</h1>
  <table id="reviewTable">
    <thead>
    <tr>
      <th>序号</th>
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
    <!-- 动态生成 -->
    </tbody>
  </table>
</div>

<script>
  document.addEventListener("DOMContentLoaded", function() {
    fetch("getPendingReviewsServlet")
            .then(response => response.json())
            .then(data => {
              displayReviews(data.items);
            })
            .catch(error => {
              console.error("Error:", error);
            });
  });

  function displayReviews(items) {
    var tbody = document.getElementById("reviewTable").querySelector("tbody");
    tbody.innerHTML = "";
    items.forEach((item, index) => {
      var row = document.createElement("tr");
      row.innerHTML = `
                <td>${item.reviewId}</td>
                <td>${item.name}</td>
                <td>${item.gender}</td>
                <td>${item.idCard}</td>
                <td>${item.college}</td>
                <td>${item.major}</td>
                <td>${item.degreeType}</td>
                <td>${item.tutor}</td>
                <td>
                    <button onclick="approveReview(${item.reviewId})">通过</button>
                    <button onclick="rejectReview(${item.reviewId})">拒绝</button>
                </td>
            `;
      tbody.appendChild(row);
    });
  }

  function approveReview(reviewId) {
    updateReviewStatus(reviewId, "approve");
  }

  function rejectReview(reviewId) {
    updateReviewStatus(reviewId, "reject");
  }

  function updateReviewStatus(reviewId, status) {
    fetch("reviewGraduateServlet", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ reviewId: reviewId, status: status })
    })
            .then(response => response.json())
            .then(data => {
              if (data.success) {
                var buttons = document.querySelectorAll(`button[onclick*='${reviewId}']`);
                buttons.forEach(button => {
                  button.classList.add("button-disabled");
                  button.disabled = true;
                });
              }
              alert(data.message);
            })
            .catch(error => {
              console.error("Error:", error);
            });
  }
</script>
</body>
</html>
