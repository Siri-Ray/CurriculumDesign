# 开发文档

## 要求简介

**研究生学籍管理系统**

  研究生学籍是研究生日常管理中非常重要的基础信息，包括基础信息、在校信息、入学信息和扩展信息（包括个人联系信息、工作经历和教育经历等）以及照片信息，具体如下图所示。

![img](file:///C:\Users\151505~1\AppData\Local\Temp\ksohtml15028\wps1.jpg) 

![img](file:///C:\Users\151505~1\AppData\Local\Temp\ksohtml15028\wps2.jpg) 

![img](file:///C:\Users\151505~1\AppData\Local\Temp\ksohtml15028\wps3.jpg) 

![img](file:///C:\Users\151505~1\AppData\Local\Temp\ksohtml15028\wps4.jpg) 

  现要开发一套研究生学籍管理系统，姓名、性别、身份证号、学院、专业、学位类型、导师等基础信息由学院研究生秘书导入（学号由**基于专业编码、学位类型的规则自动生成**）或逐条录入，研究生院管理员审核后即可。研究生登录（帐号为学号，密码默认为身份证号后8位），第一次登录时必须修改密码，登录后可以查看自己的学籍信息，也可以修改除**姓名、性别、身份证号、学院、专业、学位类型、导师**以外的信息；导师能查询、查看所有指导的研究生学籍信息；学院研究生秘书、学院领导可以管理本学院所有研究生的学籍信息，如修改姓名、性别、身份证号、学院、专业、学位类型、导师等基础信息，需研究生院管理员审核后方可生效；研究生院管理员可以管理全校的研究生学籍信息；研究生院领导、学校领导可以查询、查看全校的研究生学籍信息。该系统对研究生学籍信息的隐私保护要求较高，要求按等保三级和国密算法对敏感信息进行隐私保护，具体要求如下：

（1）身份鉴别

密码复杂度要求，长度8位以上，包含数字、大小字母、特殊字符等混合组合；定期要求90天以上需更换一次密码；登录失败5次锁定30分钟，超时30分钟自动退出；要求对密码采用基于国产密码算法SM3进行加密保存。

（2）访问控制

配置研究生、导师、学院研究生秘书、学院领导、研究生院管理员、研究生院领导、学校领导、系统管理员、审计管理员，实现管理用户和研究生信息的权限分离。对学籍信息的数据资源进行严格的管理权限划分，形成完整的资源分级和访问权限控制结构体系，依据安全标记控制主体对信息资源的访问，比如导师只能查看自己指导的研究生学籍信息，学院研究生秘书、学院领导只能查看和管理本学院的研究生学籍信息，研究生院管理员、研究生院领导、学校领导能查询和管理全校的研究生学籍信息和导师信息，系统管理员可以设置用户的角色和权限以及研究生院管理员和审计管理员，审计管理员只允许查看系统的日志信息。

（3）数据完整性和保密性

对学籍信息中涉及密码、身份证号、联系电话、住址、地址、邮箱等重要数据存储时，采用基于国产密码算法SM系列的校验技术或密码技术，保证其在存储过程中数据的完整性和保密性，如密码用国密SM3加密存储，身份证号、联系电话、住址、地址、邮箱等用SM2或SM4加密存储，并在页面上对关键信息进行脱敏显示（部分内容用*代替）。

（4）安全审计

对所有用户的登录、查看学籍信息、修改学籍信息等重要的操作日志保存至数据库的日志表，审计管理员可对日志进行查询、查看等审计操作，日志保存半年以上。选做：采用基于国产密码算法对日志记录进行完整性保护，可对日志记录进行 HMAC-SM3运算，并定期比对日志记录和HMAC值。

## 需求分析

### 前端页面

#### 统一登录界面

可选身份

学号/用户名

密码

后端返回：jwt令牌，解析，对应信息展示页面

#### 修改密码

默认为身份证号后六位

#### 研究生信息展示

根据不同身份，展示不同内容

#### 研究生信息导入

所有信息导入/检测合法/生成学号（后端实现）

#### 用户进行信息查询

查询框/查询全部

查询结果，分页

点击结果进入信息展示

分栏div，分页，回显

#### 系统管理界面

查询/所有

修改

添加研究生院管理员和审计管理员

#### 审计日志展示

分页

#### 添加用户页面



### 权限级别

研究生：查看，修改部分


导师：查询查看所有指导

学院研究生秘书：查看查询本学院所有，导入，修改基础信息

学院领导：查看查询本学院所有，修改基础信息

研究生院管理员：审核，管理全校

研究生院领导：查询、查看全校

学校领导：查询、查看全校



审计管理员：查看系统的日志信息

系统管理员：设置**用户**的角色和权限，设置研究生院管理员和审计管理员

用户名，密码，用户角色/权限，学院

### 页面逻辑

进入登陆页面，可选学生或老师/管理员

根据结果，如果失败则提示重新输入，返回登录失败5次提示锁定30分钟

根据返回信息跳转到对应页面



## 数据库设计

（研究生表）学号，密码表，修改时间，上一次登陆尝试时间，尝试登录次数

```sql
CREATE TABLE graduate_students (
    student_id VARCHAR(20) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    last_password_change_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_login_attempt_time TIMESTAMP DEFAULT NULL,
    login_attempt_count INT DEFAULT 0
);

```

(管理员表)用户名，密码，用户角色，学院

```sql
CREATE TABLE administrators (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    user_role INT NOT NULL,
    department VARCHAR(100) NOT NULL
);

```

基础信息表（在大查询时使用）学号，姓名、性别、身份证号、学院、专业、学位类型、导师

```sql
CREATE TABLE basic_information (
    student_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    id_number VARCHAR(18) UNIQUE NOT NULL,
    department VARCHAR(100) NOT NULL,
    major VARCHAR(100) NOT NULL,
    degree_type VARCHAR(50) NOT NULL,
    supervisor VARCHAR(50) NOT NULL,
    FOREIGN KEY (student_id) REFERENCES graduate_students(student_id)
);

```

日志表 

```sql
CREATE TABLE operation_logs (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    operation VARCHAR(50) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    details TEXT,
    FOREIGN KEY (username) REFERENCES administrators(username)
);

```



其他信息表：学号，





## 接口规范

小驼峰对应匈牙利式

常量：

请求基础信息表

地址

请求内容

```json
{
"username":"admin",
"password":"123456"
}
```

响应内容

```json
{
"code": 0,
"msg": "success",
"data": {
// 这里是具体的数据
}
}
```

jwt暂定内容：

```json
{
"sub": "1234567890",
"name": "John Doe",
"college": ["all"],
"permissions": ["read", "write"],
"iat": 1596472760,
"exp": 1596476360
}
```

权限分级
+登录超时

### 1.登陆页面学生/用户登陆接口

1.登录判断graduateLoginServlet或graduateLoginServlet
1.1传输的数据
```json //data
{
"username":"admin",
"password":"123456"
}
```
1.2接收的数据
```json //data
{
"code":"0,1,2,3"
}
```

前端：学号，SM3加密后的密码
后端：失败次数+是否是第一次登录
根据结果跳转：修改密码/信息界面

0登录失败
1研究生第一次登陆成功
2研究生之后登陆成功
3其余用户登陆成功
4审计管理员登陆成功——日志
5系统管理员——用户查询界面

### 修改页面修改密码接口
1、修改密码changePasswordServlet
1.1数据传输
```json //data
{
"studentId":"学号",
"password":"密码",
"confirmPassword":"确认密码"
}
```
1.2数据返回
```json //data
{
"success":"1/0",
"message":""
}
```


学号，密码

是否成功

### 大查询页面分页查询接口

```json
{
  "studentId": "20210001",
  "name": "张三",
  "college": "计算机学院"
}
```

学号/姓名/学院

**基本信息列表10条（装入json list）**

学号、姓名、性别、身份证号、学院、专业、学位类型、导师



### 研究生信息展示页面查询接口
1、发起搜索请求searchGraduateServlet
1.1传输数据
```json
{
  "queryType": "studentId/name/college/all",
  "queryValue": "查询值",
  "dataCode": "用户类型"
}
```
queryType是all时,展示所有可查询的学生信息

1.2接收数据

 ```json //data
 {
  "studentId": "学号",
 "name": "姓名",
 "gender":"性别",
 "idCard":"学号",
 "college": "学院",
 "major": "专业",
 "degreeType": "学位类型",
 "tutor": "导师"
}
```





学号

**基本信息+其他信息（json），若空则提示**

### 研究生信息展示页面修改接口
基础信息

1、修改功能updateInfoEndpoint
1.1传输数据
 ```json //info
 {
  "studentId": "学号",
 "name": "姓名",
 "gender":"性别",
 "idCard":"学号",
 "college": "学院",
 "major": "专业",
 "degreeType": "学位类型",
 "tutor": "导师"
}
```
1.2接收数据
```json //data
{
  "success": ""
}
```
2、获取学生信息getBasicInfoEndpoint
2.1获取数据
```json //info
 {
  "studentId": "学号",
 "name": "姓名",
 "gender":"性别",
 "idCard":"学号",
 "college": "学院",
 "major": "专业",
 "degreeType": "学位类型",
 "tutor": "导师"
}
```


其他信息：

```json
{
  "studentId": "20210001",
  "name": "张三",
  "college": "计算机学院"
}
```

（修改的）id+基本信息/其他信息

成功与否，刷新

### 信息导入页面添加研究生接口
1、Servlet映射地址addGraduateServlet
1.1传输的数据格式
 ```json //formData
 {
 "name": "姓名",
 "gender":"性别",
 "idCard":"学号",
 "college": "学院",
 "major": "专业",
 "degreeType": "学位类型",
 "tutor": "导师"
}
```
姓名、性别、身份证号、学院、专业、学位类型、导师

1.2接收的格式
```json  //data
{
"message": "添加的信息", 
  "success":1 ,
}
```

基本信息

是否成功

### 审核页面审核接口2

1、待审核请求获取getPendingReviewsServlet
1.1接收格式
 ```json //data
 {
  "reviewId": "待审核请求序号",
 "name": "姓名",
 "gender":"性别",
 "idCard":"学号",
 "college": "学院",
 "major": "专业",
 "degreeType": "学位类型",
 "tutor": "导师"
}
```

1.**需要审核的信息（list10条）**返回：待审核id，姓名、性别、身份证号、学院、专业、学位类型、导师

审核通过：id
审核不通过（删除）：id
2、审查实现reviewGraduateServlet
2.1传输数据
 ```json //data
{
  "reviewId": "待审核请求序号",
  "status": "通过/拒绝"
}
```
2.2接收数据
 ```json //data
{
  "reviewId": "待审核请求序号",
  "message": "", //用于弹窗提示
  "success": "1或0"
}
```

表格
是否成功

### 用户管理页面用户分页查询接口
1、查询功能searchUsersServlet
1.1 传输数据
 ```json //data
{
  "type": "username/name/college/all",
  "keyword": "查询值", 
  "page": "页数"
}
```
1.2返回
 ```json //data
{
  "username": "username",
  "password": "password", 
  "name": "name",
  "college": "college",
  "role": "role",
  "total": "数据有几页"
}
```




用户id/姓名/.学院 可选

**用户信息列表10条**

用户名，密码，用户角色，学院

### 用户管理页面用户修改接口
1、修改信息editUserServlet
1.1传输
 ```json //data
{
  "username": "username",
  "name": "name",
  "password": "password",
  "role": "role",
  "college": "学院"
}
```
1.2返回
 ```json //data
{
  "success": "1/0",
  "message": "具体情况"
}
```
密码，用户角色，学院

修改的信息+用户名

成功与否

### 用户添加页面用户添加接口

用户信息：用户名，密码，用户角色，学院

成功与否

1、添加用户信息addUserServlet
1.1传输的数据格式
 ```json //data
 {
  "username":"账号",
 "name": "姓名",
 "password":"密码",
  "role": "角色",
 "college": "学院"

}
```

1.2接收的格式
```json  //data
{
"message": "添加的信息", 
  "success":1 
}
```
### 审计页面日志查询接口

无

**日志列表10条**



## Dao层

查询学号密码是否正确、

查询最大id

第一次添加学号密码

根据学号/姓名/学院/空白查询基础信息

修改基础信息

修改其他信息

添加日志（触发器实现）

查询日志

添加管理员

查询管理员

删除管理员

修改管理员表









​    

