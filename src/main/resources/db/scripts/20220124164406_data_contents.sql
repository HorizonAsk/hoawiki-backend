-- // data_contents
-- Migration SQL that makes the change goes here.

INSERT INTO `contents` (`content_text`)
VALUES ('HOA曾经是……'),
       ('HOA是……'),
       ('物理是……'),
       ('化学是……'),
       ('亚洲是……'),
       ('HOA Content 更新！
 ## markdown编辑器
 ### toc在保存后更新
 ## 公式
 ### 基本的katex语法
 $\alpha$
 $$\sum $$
 $$E=mc^2$$
 ## emoji
 🤭
 ## 表格
 |标题加粗|column2|column3|
 |:---:|:-|-:|
 |content1|content2|content3|
 |中|左|右|

 ##### 很长很长很长很长很长很长很长很长很长很长很长很长的小标题'),
       ('# 定义
 ## 研究范围
 物理学（physics）是研究物质最一般的运动规律和物质基本结构的学科。
 ## 研究领域
 力、热、光、电、原
 '),
       ('## 中国历史
 中国历史的信息'),
       ('## 历史
 中国历史参见[中国历史](/pages/3)
 ## 脚注测试
 我引用了一些文章[^1]
 <a href="javascript:alert(''XSS'')">Click Me</a>

 [^1]: 这是脚注1');

-- //@UNDO
-- SQL to undo the change goes here.


