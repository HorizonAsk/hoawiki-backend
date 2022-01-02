-- // page_content_table
-- Migration SQL that makes the change goes here.

CREATE TABLE `page_contents`
(
    `page_content_id` BIGINT NOT NULL AUTO_INCREMENT,
    `page_id`         BIGINT NOT NULL,
    `content_id`      BIGINT NOT NULL,
    PRIMARY KEY (`page_content_id`),
    INDEX `fk_page_content_page_id_idx` (`page_id` ASC) VISIBLE,
    INDEX `fk_page_content_content_id_idx` (`content_id` ASC) VISIBLE,
    CONSTRAINT `fk_page_content_page_id`
        FOREIGN KEY (`page_id`)
            REFERENCES `pages` (`page_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_page_content_content_id`
        FOREIGN KEY (`content_id`)
            REFERENCES `contents` (`content_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE `page_contents`;
