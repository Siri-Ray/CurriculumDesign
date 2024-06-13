<%--
  Created by IntelliJ IDEA.
  User: 86133
  Date: 2024/6/6
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>学籍信息</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .tab {
            overflow: hidden;
            border-bottom: 1px solid #ccc;
            background-color: #f1f1f1;
        }
        .tab button {
            background-color: inherit;
            border: none;
            outline: none;
            cursor: pointer;
            padding: 14px 16px;
            transition: 0.3s;
        }
        .tab button:hover {
            background-color: #ddd;
        }
        .tab button.active {
            background-color: #ccc;
        }
        .tabcontent {
            display: none;
            padding: 20px;
            border: 1px solid #ccc;
            border-top: none;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
        .collapsible {
            background-color: #f1f1f1;
            color: #444;
            cursor: pointer;
            padding: 10px;
            width: 100%;
            border: none;
            text-align: left;
            outline: none;
            font-size: 15px;
        }
        .active, .collapsible:hover {
            background-color: #ccc;
        }
        .content {
            padding: 0 18px;
            display: none;
            overflow: hidden;
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>

<h1>学籍信息</h1>

<div class="tab">
    <button class="tablinks" onclick="openTab(event, 'BasicInfo')">基础信息</button>
    <button class="tablinks" onclick="openTab(event, 'AdmissionInfo')">入学信息</button>
    <button class="tablinks" onclick="openTab(event, 'ExtendedInfo')">扩展信息</button>
</div>

<div id="BasicInfo" class="tabcontent">
    <button class="collapsible">基础信息表</button>
    <div class="content">
        <table>
            <!-- 基础信息表内容 -->
            <tr>
                <th>学号</th>
                <td contenteditable="false" id="studentId">XXXXXXXX</td>
                <th>姓名</th>
                <td contenteditable="false" id="name">XXX</td>
            </tr>
            <tr>
                <th>性别</th>
                <td contenteditable="false" id="gender">男</td>
                <th>身份证号</th>
                <td contenteditable="false" id="idNumber">XXXXXXXXXXXX</td>
            </tr>
            <tr>
                <th>学院</th>
                <td contenteditable="false" id="college">计算机科学与技术学院</td>
                <th>专业</th>
                <td contenteditable="false" id="major">软件工程</td>
            </tr>
            <tr>
                <th>学位类型</th>
                <td contenteditable="false" id="degreeType">专业学位</td>
                <th>导师</th>
                <td contenteditable="false" id="mentor">导师2</td>
            </tr>
        </table>
        <button onclick="editInfo()">修改</button>
        <button onclick="cancelEdit()">取消</button>
        <button onclick="submitInfo()">提交</button>
    </div>

    <button class="collapsible">在校信息表</button>
    <div class="content">
        <table>
            <!-- 在校信息表内容 -->
            <tr>
                <th>年级</th>
                <td>2023级</td>
                <th>入学季节</th>
                <td>秋季</td>
            </tr>
            <tr>
                <th>培养层次</th>
                <td>硕士研究生</td>
                <th>学生类别</th>
                <td>全日制专业学位硕士</td>
            </tr>
            <tr>
                <th>学院</th>
                <td>计算机科学与技术学院</td>
                <th>管理单位</th>
                <td>计算机科学与技术学院</td>
            </tr>
            <tr>
                <th>专业</th>
                <td>085405软件工程</td>
                <th>学制</th>
                <td>3年制</td>
            </tr>
            <tr>
                <th>入学方式</th>
                <td>全国统考</td>
                <th>校区</th>
                <td>屏峰校区</td>
            </tr>
            <tr>
                <th>入学年月</th>
                <td>2023-09-01</td>
                <th>班级</th>
                <td>电子信息2</td>
            </tr>
            <tr>
                <th>招生专业</th>
                <td>085400电子信息</td>
                <th>门类/专业学位类别</th>
                <td>电子信息</td>
            </tr>
            <tr>
                <th>学位类型</th>
                <td>专业学位</td>
                <th>专业方向</th>
                <td>软件工程(085405)</td>
            </tr>
            <tr>
                <th>培养方式</th>
                <td>非定向</td>
                <th>学习方式</th>
                <td>全日制</td>
            </tr>
            <tr>
                <th>进修性质</th>
                <td>无</td>
                <th>专项计划</th>
                <td>无专项计划</td>
            </tr>
            <tr>
                <th>预计毕业时间</th>
                <td>2026-06-30</td>
                <th>联合培养单位名称</th>
                <td>无</td>
            </tr>
            <tr>
                <th>是否跨学科</th>
                <td>否</td>
                <th>是否留学生</th>
                <td>否</td>
            </tr>
            <tr>
                <th>实际毕业时间</th>
                <td>未毕业</td>
                <th>学籍状态</th>
                <td>正常</td>
            </tr>
            <tr>
                <th>学籍异动状态</th>
                <td>无</td>
                <th>导师</th>
                <td>导师2</td>
            </tr>
            <tr>
                <th>联合培养导师</th>
                <td>无</td>
                <th>在校标识</th>
                <td>在校</td>
            </tr>
            <tr>
                <th>研究方向</th>
                <td>软件工程(085405)</td>
                <th>注册状态</th>
                <td>已注册</td>
            </tr>
            <tr>
                <th>缴费情况</th>
                <td>正常</td>
                <th>学位</th>
                <td>无</td>
            </tr>
            <tr>
                <th>是否接受学历教育</th>
                <td>是</td>
                <th>学位情况</th>
                <td>未获</td>
            </tr>
            <tr>
                <th>一级学科/专业学位类别</th>
                <td>电子信息</td>
                <th>是否学科交叉培养</th>
                <td>否</td>
            </tr>
            <tr>
                <th>交叉培养学科</th>
                <td>无</td>
                <th>学位授予时间</th>
                <td>未授予</td>
            </tr>
        </table>
    </div>
</div>

<div id="AdmissionInfo" class="tabcontent">
    <button class="collapsible">入学信息</button>
    <div class="content">
        <table>
            <!-- 入学信息内容 -->
            <tr>
                <th>招生年度</th>
                <td>2023级</td>
                <th>考生编号</th>
                <td>1033</td>
            </tr>
            <tr>
                <th>报考类别</th>
                <td>全国统考</td>
                <th>考生来源</th>
                <td>应届本科毕业生</td>
            </tr>
            <tr>
                <th>录取类别</th>
                <td>国家计划内非定向</td>
                <th>考试方式</th>
                <td>全国统考</td>
            </tr>
            <tr>
                <th>是否保返</th>
                <td>否</td>
                <th>保留入学资格年限</th>
                <td>无</td>
            </tr>
            <tr>
                <th>定向委培单位所在地</th>
                <td>无</td>
                <th>定向委墙单位</th>
                <td>无</td>
            </tr>
            <tr>
                <th>推免单位</th>
                <td>无</td>
            </tr>
        </table>
    </div>
    <button class="collapsible">入学前学历信息</button>
    <div class="content">
        <table>
            <!-- 入学前学历信息 -->
            <tr>
                <th>最后毕业学校</th>
                <td>浙江工业大学</td>
                <th>最后毕业学校名称</th>
                <td>浙江工业大学</td>
            </tr>
            <tr>
                <th>最后毕业专业</th>
                <td>软件工程</td>
                <th>最后毕业专业名称</th>
                <td>软件工程</td>
            </tr>
            <tr>
                <th>最后学位单位</th>
                <td>浙江工业大学</td>
                <th>最后学位单位名称</th>
                <td>浙江工业大学</td>
            </tr>
            <tr>
                <th>最后学位专业</th>
                <td>软件工程</td>
                <th>最后学位专业名称</th>
                <td>软件工程</td>
            </tr>
            <tr>
                <th>最后毕业证书编号</th>
                <td>XXXXXXXXXXXX</td>
                <th>最后学历</th>
                <td>大学本科毕业</td>
            </tr>
            <tr>
                <th>最后学位</th>
                <td>无学位</td>
                <th>最后毕业时间</th>
                <td>2023-06-30</td>
            </tr>
            <tr>
                <th>最后学位时间</th>
                <td>无</td>
                <th>最后学历学习形式</th>
                <td>普通全日制</td>
            </tr>
        </table>
    </div>
</div>

<div id="ExtendedInfo" class="tabcontent">
    <button class="collapsible">个人联系信息</button>
    <div class="content">
        <table>
            <!-- 个人联系信息 -->
            <tr>
                <th>微信号</th>
                <td>XXXXX</td>
                <th>QQ</th>
                <td>XXXXX</td>
            </tr>
            <tr>
                <th>联系电话</th>
                <td>12345678901</td>
                <th>通讯地址</th>
                <td>浙江省杭州市西湖区留下街道留和路28号浙江工业大学屏峰校区</td>
            </tr>
            <tr>
                <th>邮政编码</th>
                <td>XXXXX</td>
                <th>现在学习或工作单位</th>
                <td>浙江工业大学</td>
            </tr>
            <tr>
                <th>单位联系电话</th>
                <td>12345678901</td>
                <th>单位邮编</th>
                <td>XXXXX</td>
            </tr>
            <tr>
                <th>紧急联系人</th>
                <td>XXX</td>
                <th>紧急电话</th>
                <td>12345678901</td>
            </tr>
            <tr>
                <th>紧急联系人邮政编码</th>
                <td>XXXXX</td>
                <th>紧急联系人住址</th>
                <td>浙江省杭州市西湖区</td>
            </tr>
            <tr>
                <th>宿舍地址</th>
                <td>浙江工业大学宿舍区</td>
                <th>宿舍电话</th>
                <td>12345678901</td>
            </tr>
        </table>
    </div>

    <!-- 工作经历 -->
    <button class="collapsible">工作经历</button>
    <div class="content">
        <table id="workExperienceTable">
            <thead>
            <tr>
                <th onclick="sortTable(0, 'workExperienceTable')">起始日期</th>
                <th onclick="sortTable(1, 'workExperienceTable')">终止日期</th>
                <th>所在单位</th>
                <th>从事工作</th>
                <th>担任职务</th>
                <th>证明人</th>
                <th>备注</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>无</td>
                <td>无</td>
                <td>无</td>
                <td>无</td>
                <td>无</td>
                <td>无</td>
                <td>无</td>
            </tr>
            <!-- Additional records can be added here -->
            </tbody>
        </table>
        <div id="workExperiencePagination"></div>
    </div>

    <!-- 教育经历 -->
    <button class="collapsible">教育经历</button>
    <div class="content">
        <table id="educationExperienceTable">
            <thead>
            <tr>
                <th onclick="sortTable(0, 'educationExperienceTable')">学校名称</th>
                <th onclick="sortTable(1, 'educationExperienceTable')">起始日期</th>
                <th onclick="sortTable(2, 'educationExperienceTable')">结束日期</th>
                <th>专业名称</th>
                <th>专业描述</th>
                <th>学历</th>
                <th>学位类型</th>
                <th>毕业学校地点</th>
                <th>修学年限</th>
                <th>是否毕业</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>浙江工业大学</td>
                <td>2019-09-01</td>
                <td>2023-06-18</td>
                <td>软件工程</td>
                <td>软件开发与设计</td>
                <td>大学本科毕业</td>
                <td>学士</td>
                <td>浙江省杭州市</td>
                <td>4年</td>
                <td>是</td>
            </tr>
            <!-- Additional records can be added here -->
            </tbody>
        </table>
        <div id="educationExperiencePagination"></div>
    </div>
</div>


<script>
    function sortTable(n, tableId) {
        var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
        table = document.getElementById(tableId);
        switching = true;
        dir = "asc";
        while (switching) {
            switching = false;
            rows = table.rows;
            for (i = 1; i < (rows.length - 1); i++) {
                shouldSwitch = false;
                x = rows[i].getElementsByTagName("TD")[n];
                y = rows[i + 1].getElementsByTagName("TD")[n];
                if (dir == "asc") {
                    if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                        shouldSwitch = true;
                        break;
                    }
                } else if (dir == "desc") {
                    if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                        shouldSwitch = true;
                        break;
                    }
                }
            }
            if (shouldSwitch) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
                switchcount++;
            } else {
                if (switchcount == 0 && dir == "asc") {
                    dir = "desc";
                    switching = true;
                }
            }
        }
    }

    function paginateTable(tableId, paginationId, rowsPerPage) {
        var table, rows, pagination, numPages, pageIndex, i;
        table = document.getElementById(tableId);
        rows = table.rows;
        pagination = document.getElementById(paginationId);
        numPages = Math.ceil((rows.length - 1) / rowsPerPage);

        for (i = 0; i < numPages; i++) {
            var pageLink = document.createElement("a");
            pageLink.href = "#";
            pageLink.innerHTML = i + 1;
            pageLink.setAttribute("data-page", i);
            pageLink.onclick = function() {
                showPage(this.getAttribute("data-page"), tableId, rowsPerPage);
            };
            pagination.appendChild(pageLink);
        }

        showPage(0, tableId, rowsPerPage);
    }

    function showPage(pageIndex, tableId, rowsPerPage) {
        var table, rows, start, end, i;
        table = document.getElementById(tableId);
        rows = table.rows;
        start = pageIndex * rowsPerPage + 1;
        end = start + rowsPerPage;

        for (i = 1; i < rows.length; i++) {
            if (i >= start && i < end) {
                rows[i].style.display = "";
            } else {
                rows[i].style.display = "none";
            }
        }
    }

    function editInfo() {
        var cells = document.querySelectorAll("#BasicInfo td[contenteditable]");
        cells.forEach(cell => {
            cell.contentEditable = "true";
            cell.classList.add("editable");
        });
    }

    function cancelEdit() {
        var cells = document.querySelectorAll("#BasicInfo td[contenteditable]");
        cells.forEach(cell => {
            cell.contentEditable = "false";
            cell.classList.remove("editable");
        });
    }

    function submitInfo() {
        var info = {
            studentId: document.getElementById("studentId").innerText,
            name: document.getElementById("name").innerText,
            gender: document.getElementById("gender").innerText,
            idNumber: document.getElementById("idNumber").innerText,
            college: document.getElementById("college").innerText,
            major: document.getElementById("major").innerText,
            degreeType: document.getElementById("degreeType").innerText,
            tutor: document.getElementById("tutor").innerText
        };

        fetch('updateInfoEndpoint', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(info)
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('信息更新成功');
                    var cells = document.querySelectorAll("#BasicInfo td[contenteditable]");
                    cells.forEach(cell => {
                        cell.contentEditable = "false";
                        cell.classList.remove("editable");
                    });
                } else {
                    alert('信息更新失败');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('信息更新失败');
            });
    }

    document.addEventListener("DOMContentLoaded", function() {
        paginateTable("workExperienceTable", "workExperiencePagination", 10);
        paginateTable("educationExperienceTable", "educationExperiencePagination", 10);
    });
    function openTab(evt, tabName) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(tabName).style.display = "block";
        evt.currentTarget.className += " active";
    }

    var coll = document.getElementsByClassName("collapsible");
    for (var i = 0; i < coll.length; i++) {
        coll[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var content = this.nextElementSibling;
            if (content.style.display === "block") {
                content.style.display = "none";
            } else {
                content.style.display = "block";
            }
        });
    }

    // 默认显示第一个tab
    document.getElementsByClassName("tablinks")[0].click();

    // JavaScript 代码
    document.addEventListener("DOMContentLoaded", function() {
        // 获取 URL 中的参数 username
        var urlParams = new URLSearchParams(window.location.search);
        var studentId = urlParams.get('studentId');
        // 使用 username 从数据库中获取基础信息并填充到页面中
        fetch('getBasicInfoEndpoint?studentId=' + studentId)
            .then(response => response.json())
            .then(data => {
                // 填充基础信息到页面中
                document.getElementById("studentId").innerText = data.studentId;
                document.getElementById("name").innerText = data.name;
                document.getElementById("gender").innerText = data.gender;
                document.getElementById("idNumber").innerText = data.idNumber;
                document.getElementById("college").innerText = data.college;
                document.getElementById("major").innerText = data.major;
                document.getElementById("degreeType").innerText = data.degreeType;
                document.getElementById("tutor").innerText = data.tutor;
            })
            .catch(error => {
                console.error('Error:', error);
                alert('获取基础信息失败');
            });
    });

</script>

</body>
</html>

