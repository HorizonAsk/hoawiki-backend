-- // permission_table
-- Migration SQL that makes the change goes here.

CREATE TABLE `permissions`
(
    `permission_id`        BIGINT        NOT NULL AUTO_INCREMENT,
    `permission_name`      VARCHAR(50)   NOT NULL,
    `permission_url`       VARCHAR(1000) NULL DEFAULT NULL COMMENT 'type conceptPage route, if button, api',
    `permission_type`      INT(2)        NOT NULL COMMENT 'if conceptPage, 1, if button, 2',
    `permission_class`     VARCHAR(45)   NULL DEFAULT NULL COMMENT 'eg.: btn:monitor, conceptPage:admin',
    `permission_method`    VARCHAR(50)   NULL DEFAULT NULL COMMENT 'POST, GET, DELETE',
    `permission_sort`      INT(11)       NOT NULL COMMENT 'permission show order',
    PRIMARY KEY (`permission_id`),
    UNIQUE INDEX `permission_class_UNIQUE` (`permission_class` ASC) VISIBLE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE `permissions`;
