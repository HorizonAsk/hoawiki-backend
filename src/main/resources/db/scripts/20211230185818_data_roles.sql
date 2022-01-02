-- // data_roles
-- Migration SQL that makes the change goes here.
INSERT INTO `roles` (`role_id`,`role_name`, `role_description`)
VALUES
    (1,'admin', "Admin"),
    (2,'user', "User") ;


-- //@UNDO
-- SQL to undo the change goes here.
