-- // permission_table
-- Migration SQL that makes the change goes here.

CREATE TABLE `permissions`
(
    `permission_id`        BIGINT        NOT NULL AUTO_INCREMENT,
    `permission_name`      VARCHAR(50)   NOT NULL,
    `permission_url`       VARCHAR(1000) NULL DEFAULT NULL COMMENT 'if type==page(1), route; if button, api',
    `permission_type`      INT(2)        NOT NULL COMMENT 'if page, 1; if button, 2;',
    `permission_class`     VARCHAR(45)   NULL DEFAULT NULL COMMENT 'eg.: btn:monitor, page:admin',
    `permission_method`    VARCHAR(50)   NULL DEFAULT NULL COMMENT 'POST, GET, DELETE',
    `permission_sort`      INT(11)       NOT NULL COMMENT 'permission show order',
#     `permission_parent_id` BIGINT        NOT NULL COMMENT 'preceding permission',
    PRIMARY KEY (`permission_id`)
# ,
#     INDEX `fk_permission_parent_idx` (`permission_parent_id` ASC) VISIBLE,
#     CONSTRAINT `fk_permission_parent`
#         FOREIGN KEY (`permission_parent_id`)
#             REFERENCES `permissions` (`permission_id`)
#             ON DELETE CASCADE
#             ON UPDATE CASCADE
);

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE `permissions`;
