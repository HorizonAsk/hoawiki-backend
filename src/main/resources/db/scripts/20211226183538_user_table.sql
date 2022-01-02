-- // user_table
-- Migration SQL that makes the change goes here.

CREATE TABLE `users`
(
    `user_id`           BIGINT       NOT NULL AUTO_INCREMENT,
    `is_super_admin`    TINYINT      NOT NULL DEFAULT 0 COMMENT 'Superadmin flag, 1 superadmin, else not.',
    `username`          VARCHAR(255) NOT NULL,
    `email`             VARCHAR(255) NOT NULL,
    `email_verified_at` TIMESTAMP    NULL,
    `password`          VARCHAR(255) NOT NULL,
    `remember_token`    VARCHAR(255) NULL,
    `create_time`       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `delete_time`       TIMESTAMP    NULL,
    `update_time`       TIMESTAMP    NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
    UNIQUE INDEX `token_UNIQUE` (`remember_token` ASC) VISIBLE
);

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE `users`;
