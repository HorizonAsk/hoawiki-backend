-- // content_table
-- Migration SQL that makes the change goes here.

CREATE TABLE `contents`
(
    `content_id`   BIGINT    NOT NULL AUTO_INCREMENT,
    `content_text` LONGTEXT  NULL,
    `created_time` TIMESTAMP NULL     DEFAULT CURRENT_TIMESTAMP,
    `deleted_time` TIMESTAMP NULL,
    `latest_flag`  TINYINT   NOT NULL DEFAULT 0,
    PRIMARY KEY (`content_id`)
);

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE `contents`;
