-- // data_permissions
-- Migration SQL that makes the change goes here.

INSERT INTO permissions(`permission_id`,`permission_name`, `permission_url`, `permission_type`, `permission_class`,
                                      `permission_method`, `permission_sort`)
VALUES (1,"Administrator", "/admin", 1, "page:admin", "GET", 0),
       (2,"User administer-get", "/admin/users", 1, "page:admin:user:get", "GET", 0),
       (3,"User administer-post", "/admin/users", 1, "page:admin:user:post", "POST", 0);

-- //@UNDO
-- SQL to undo the change goes here.
