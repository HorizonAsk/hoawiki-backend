<h1 style="text-align: center;">
  <br>
  <a href="">
<img src="" alt="还没放上来的HOA Wiki Logo" width="200">
</a>
  <br>
  HOAWiki-backend
  <br>
</h1>

## 关键特性

- Spring-boot框架, Spring-boot-security
- Mybatis-plus 对接数据库操作
- Gradle 依赖管理
- 简单的 docker 部署

### 当前的主要工作TODO

详见Projects(https://github.com/HorizonAsk/hoawiki-front/projects)

- API 接口文档
    - 统一的Request和Respond数据处理
    - 异常捕获和处理
- Page 实体类功能接口的实现
- User-Role-Permission授权接口实现的优化
- Github Action的配置，自动打包和部署
- 等待视觉设计

## 致开发者

### 关于开发环境

推荐您使用 [IDEA](https://www.jetbrains.com/zh-cn/idea/ ) 进行开发。如果您偏好在本地进行测试，建议您额外准备：

- 在IDEA中配置好的jdk
- [Postman](https://www.postman.com/downloads/ )或[Apifox](https://www.apifox.cn/ )等API接口测试套件
- 配置好的MySQL实例，并使用Mybatis-migration对`src\main\resources\db`目录下的sql脚本进行了migration初始化。具体见[关于数据库](#关于数据库)部分。
- (可选)docker desktop(对于Windows开发者)

如果您基于Github贡献代码，请按照流程进行PR和issue提交工作，则无需在本地进行过多环境设置。

### 使用IDEA

- 使用`git clone`到本地
- 使用IDEA打开项目，根据提示（如果有），配置好JDK等
- 在IDEA的`Run/Debug Configurations`中配置`Spring Boot`构建任务
- 并在Gradle build构建中提供以下环境变量：

```
MYSQL_ADDRESS=localhost:3306
MYSQL_USERNAME=spring-debug
MYSQL_PASSWORD=spring-debug
```

> 注：您应当根据数据库的实际情况进行修改，具体见[关于数据库](#关于数据库)部分。

### 关于数据库

项目目前使用Mybatis-plus作为Spring-boot和数据库交互的组件，并使用独立的`mybatis-migration`套件对预设SQL进行版本管理。

**通常建议简单导入数据库结构的开发者通过下列方式处理：**

- 在MySQL中确定创建好用于本项目的Schema，并配置好用户和权限
- 在MySQL中相应Schema下依次执行`src\main\resources\db\scripts`下的SQL
- （如果使用Docker）在`Gradle build`构建配置中提供数据库环境变量（此时数据库应当是和Docker相匹配的）
- （如果使用宿主机上的MySQL）在`Spring Boot`构建任务中设置好环境变量：`MYSQL_ADDRESS`、 `MYSQL_USERNAME`、`MYSQL_PASSWORD`

**如果希望使用Mybatis migration：**

- 参考 http://mybatis.org/migrations/
- 下载并按说明配置好使用migration命令的环境变量，对Windows用户而言，也可以直接使用`mybatis-migrations\bin\migrate.cmd`的绝对路径进行后续操作
- 确认`src\main\resources\db\drivers\mysql-connector-java-8.0.27.jar`符合你的MySQL数据库要求（通常没有太大的问题）
- 在`src\main\resources\db`下您可以执行以下命令：
    - `mybatis-migrations\bin\migrate.cmd status` 检查当前数据库migration状态
    - `mybatis-migrations\bin\migrate.cmd up` 执行所有未执行的migration
    - `mybatis-migrations\bin\migrate.cmd down 1` 撤销1次migration
    - `mybatis-migrations\bin\migrate.cmd new demo` 建立一个名为demo的新migration

## 约定

<span id="约定"></span>

### commit message

参考https://docs.google.com/document/d/1QrDFcIiPjSLDn3EL15IJygNPiHORgU1_OOAqWjiDU5Y/edit#

```
<type>(<scope>): <subject>
<BLANK LINE>
<body>
<BLANK LINE>
<footer>
```

|       英文       |                    （说明）                     |       |
|:--------------:|:-------------------------------------------:|:-----:|
|   **[feat]**   |                  (feature)                  | 功能特性  |
|   **[fix]**    | (bug fix, test fix, unsupported situations) | Bug修复 |
|   **[docs]**   |               (documentation)               |  文档   |
|  **[style]**   |    (formatting, missing semi colons, …)     |  格式   |
| **[refactor]** |                                             |  重构   |
|   **[test]**   |         (when adding missing tests)         | 添加测试  |
|  **[other]**   |       (maintain, dependencies, ci …)        |  其他   |

尽量使用英文标记，并维持subject简短。

在Footer中应当使用close #<issue number>来关联到issue

### 分支Branch

|  分支名   |  用途  |
|:------:|:----:|
|  main  |  发布  |
|  dev   | 开发测试 |
| feat-* | 开发分支 |
| fix-*  | 修复分支 |

## 相关

[hoawiki-front](https://github.com/HorizonAsk/hoawiki-front) - HOA-wiki 的前端

## 或许还有

- [地平线HOA论坛](https://horizonask.top/) - 目前运行的基于Flarum的论坛