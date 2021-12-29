-- // category_link_table
-- Migration SQL that makes the change goes here.

CREATE TABLE `category_links`
(
    `cat_link_id`          BIGINT       NOT NULL AUTO_INCREMENT,
    `cat_father_id`        BIGINT       NULL,
    `cat_children_id`      BIGINT       NULL,
    `cat_link_description` VARCHAR(255) NULL,
    PRIMARY KEY (`cat_link_id`),
    INDEX `fk_cat_link_father_id_idx` (`cat_father_id` ASC) VISIBLE,
    INDEX `fk_cat_link_children_id_idx` (`cat_children_id` ASC) VISIBLE,
    CONSTRAINT `fk_cat_link_father_id`
        FOREIGN KEY (`cat_father_id`)
            REFERENCES `categories` (`cat_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_cat_link_children_id`
        FOREIGN KEY (`cat_children_id`)
            REFERENCES `categories` (`cat_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE `category_links`;
