-- // page_table
-- Migration SQL that makes the change goes here.

CREATE TABLE `concept_pages`
(
    `page_id`     BIGINT       NOT NULL AUTO_INCREMENT,
    `page_title`  VARCHAR(255) NOT NULL,
    `create_time` TIMESTAMP    NULL DEFAULT CURRENT_TIMESTAMP,
    `delete_time` TIMESTAMP    NULL DEFAULT NULL,
    `update_time` TIMESTAMP    NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`page_id`),
    KEY `delete_time_index` (`delete_time`),
    KEY `update_time_index` (`update_time` DESC),
    FULLTEXT KEY `fulltext_page_title` (`page_title`) WITH PARSER ngram
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE `concept_pages`;
