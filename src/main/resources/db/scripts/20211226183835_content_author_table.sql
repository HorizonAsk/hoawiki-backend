-- // content_author_table
-- Migration SQL that makes the change goes here.

CREATE TABLE `content_authors`
(
    `content_authors_id` BIGINT    NOT NULL,
    `content_id`         BIGINT    NULL,
    `user_id`            BIGINT    NULL,
    `create_time`        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `minor_revise`       TINYINT   NOT NULL DEFAULT 0,
    PRIMARY KEY (`content_authors_id`),
    INDEX `fk_content_author_content_id_idx` (`content_id` ASC) VISIBLE,
    INDEX `fk_content_author_user_id_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_content_author_content_id`
        FOREIGN KEY (`content_id`)
            REFERENCES `contents` (`content_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_content_author_user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `users` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE `content_authors`;
