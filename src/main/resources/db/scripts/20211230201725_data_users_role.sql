-- // data_users_role
-- Migration SQL that makes the change goes here.

INSERT INTO `users_role` (`user_id`, `role_id`)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2);

-- //@UNDO
-- SQL to undo the change goes here.
