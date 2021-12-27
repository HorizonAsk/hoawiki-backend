-- // page_category_table
-- Migration SQL that makes the change goes here.

CREATE TABLE `page_categories`
(
    `page_cat_id` BIGINT NOT NULL AUTO_INCREMENT,
    `cat_id`      BIGINT NULL,
    `page_id`     BIGINT NULL,
    PRIMARY KEY (`page_cat_id`),
    INDEX `fk_page_cat_page_id_idx` (`page_id` ASC) VISIBLE,
    INDEX `fk_page_cat_cat_id_idx` (`cat_id` ASC) VISIBLE,
    CONSTRAINT `fk_page_cat_page_id`
        FOREIGN KEY (`page_id`)
            REFERENCES `pages` (`page_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_page_cat_cat_id`
        FOREIGN KEY (`cat_id`)
            REFERENCES `categories` (`cat_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE `page_categories`;
