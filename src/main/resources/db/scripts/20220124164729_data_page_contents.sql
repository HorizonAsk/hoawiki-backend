-- // data_page_contents
-- Migration SQL that makes the change goes here.

INSERT INTO `page_contents` (`page_id`, `content_id`)
VALUES ('1', '1'),
       ('1', '2'),
       ('8', '4'),
       ('10', '3'),
       ('5', '5');

-- //@UNDO
-- SQL to undo the change goes here.


