-- // data_users
-- Migration SQL that makes the change goes here.

INSERT INTO `users` (`is_super_admin`, `username`, `email`, `password`)
VALUES (true, 'superadmin', 'superadmin@admin.com', '$2a$10$YH8YvsiFvlmzFpWNRGHGaexUZrRpA0AqAW.bYAZfxKh05Q7XwtNQm'),
       (false, 'admin', 'admin@admin.com', '$2a$10$YKsRAmhtxLyFuqD1p2p2tuPCDzq4ZNAZOeo2AXgfdwVux7vHt/uxa');

-- //@UNDO
-- SQL to undo the change goes here.
