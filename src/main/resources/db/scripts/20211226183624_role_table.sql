-- // role_table
-- Migration SQL that makes the change goes here.

CREATE TABLE `roles`
(
    `role_id`          BIGINT       NOT NULL AUTO_INCREMENT,
    `role_name`        VARCHAR(45)  NOT NULL,
    `role_description` VARCHAR(100) NULL,
    `create_time`      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE INDEX `role_id_UNIQUE` (`role_id` ASC) VISIBLE,
    PRIMARY KEY (`role_id`),
    UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC) VISIBLE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE `roles`;
