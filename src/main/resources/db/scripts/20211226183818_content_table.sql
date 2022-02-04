-- // content_table
-- Migration SQL that makes the change goes here.

CREATE TABLE `contents`
(
    `content_id`   BIGINT    NOT NULL AUTO_INCREMENT,
    `content_text` LONGTEXT  NULL,
    `create_time` TIMESTAMP NULL     DEFAULT CURRENT_TIMESTAMP,
    `delete_time` TIMESTAMP NULL,
    PRIMARY KEY (`content_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE `contents`;
