-- // page_link_table
-- Migration SQL that makes the change goes here.

CREATE TABLE `page_links`
(
    `page_link_id` BIGINT NOT NULL AUTO_INCREMENT,
    `page_from`    BIGINT NULL,
    `page_to`      BIGINT NULL,
    PRIMARY KEY (`page_link_id`),
    INDEX `fk_page_links_page_from_idx` (`page_from` ASC) VISIBLE,
    INDEX `fk_page_links_page_to_idx` (`page_to` ASC) VISIBLE,
    CONSTRAINT `fk_page_links_page_from`
        FOREIGN KEY (`page_from`)
            REFERENCES `pages` (`page_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_page_links_page_to`
        FOREIGN KEY (`page_to`)
            REFERENCES `pages` (`page_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE `page_links`;
