-- // data_roles_permission
-- Migration SQL that makes the change goes here.
INSERT INTO `roles_permission` (`role_id`, `permission_id`)
VALUES (1, 1),
       (1, 2),
       (1, 3);

-- //@UNDO
-- SQL to undo the change goes here.
