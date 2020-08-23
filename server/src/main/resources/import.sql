INSERT INTO roles (id, created, status, updated, name) VALUES (1, '2020-08-23 15:52:40.000000', 'ACTIVE', '2020-08-23 15:52:54.000000', 'ROLE_USER')
INSERT INTO roles (id, created, status, updated, name) VALUES (2, '2020-08-23 15:52:40.000000', 'ACTIVE', '2020-08-23 15:52:54.000000', 'ROLE_ADMIN')
INSERT INTO users (id, created, status, updated, email, first_name, last_name, password, username) VALUES (1, '2020-08-23 15:52:40.000000', 'ACTIVE', '2020-08-23 15:52:40.000000', null, 'Bohdan', 'Zinkevych' , '$2y$12$Y.YwBhV4FssWH8xk/HPfRevr6TeEJZ6UzwI/emUdyqCmackkcqeiu', 'Bohdan');
INSERT INTO users (id, created, status, updated, email, first_name, last_name, password, username) VALUES (2, '2020-08-23 15:52:40.000000', 'ACTIVE', '2020-08-23 15:52:40.000000', null, 'User', 'UserLastName' , '$2y$12$Y.YwBhV4FssWH8xk/HPfRevr6TeEJZ6UzwI/emUdyqCmackkcqeiu', 'User');
INSERT INTO public.user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO public.user_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO public.user_roles (user_id, role_id) VALUES (2, 1);



