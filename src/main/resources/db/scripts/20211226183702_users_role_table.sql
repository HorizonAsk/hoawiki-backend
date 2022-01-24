-- // users_role_table
-- Migration SQL that makes the change goes here.

CREATE TABLE `users_role`
(
    `user_id` BIGINT NOT NULL,
    `role_id` BIGINT NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    INDEX `fk_users_role_role_id_idx` (`role_id` ASC) VISIBLE,
    CONSTRAINT `fk_users_role_role_id`
        FOREIGN KEY (`role_id`)
            REFERENCES `roles` (`role_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_users_role_user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `users` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE `users_role`;
