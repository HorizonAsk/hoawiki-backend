-- // roles_permission_table
-- Migration SQL that makes the change goes here.

CREATE TABLE `roles_permission`
(
    `role_id`       BIGINT NOT NULL,
    `permission_id` BIGINT NOT NULL,
    PRIMARY KEY (`role_id`, `permission_id`),
    INDEX `fk_roles_permission_permission_id_idx` (`permission_id` ASC) VISIBLE,
    CONSTRAINT `fk_roles_permission_role_id`
        FOREIGN KEY (`role_id`)
            REFERENCES `roles` (`role_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_roles_permission_permission_id`
        FOREIGN KEY (`permission_id`)
            REFERENCES `permissions` (`permission_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE `roles_permission`;
