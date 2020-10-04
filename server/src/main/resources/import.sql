INSERT INTO roles (id, created, status, updated, name) VALUES ('e2de02a0-39f8-4274-b9aa-f4e4185a917d', '2020-08-23 15:52:40.000000', 'ACTIVE', '2020-08-23 15:52:54.000000', 'ROLE_USER')
INSERT INTO roles (id, created, status, updated, name) VALUES ('d0693c83-64da-46f2-8520-590d4b104822', '2020-08-23 15:52:40.000000', 'ACTIVE', '2020-08-23 15:52:54.000000', 'ROLE_ADMIN')
INSERT INTO users (id, created, status, updated, email, first_name, last_name, password, username) VALUES ('d32e3684-bafc-4068-8e08-e8816264e7b8', '2020-08-23 15:52:40.000000', 'ACTIVE', '2020-08-23 15:52:40.000000', 'bohdan@gmail.com', 'Bohdan', 'Zinkevych' , '$2y$12$Y.YwBhV4FssWH8xk/HPfRevr6TeEJZ6UzwI/emUdyqCmackkcqeiu', 'Bohdan');
INSERT INTO users (id, created, status, updated, email, first_name, last_name, password, username) VALUES ('0d7726be-f291-4545-8833-c1ac0302f86f', '2020-08-23 15:52:40.000000', 'ACTIVE', '2020-08-23 15:52:40.000000', null, 'User', 'UserLastName' , '$2y$12$Y.YwBhV4FssWH8xk/HPfRevr6TeEJZ6UzwI/emUdyqCmackkcqeiu', 'User');
INSERT INTO public.user_roles (user_id, role_id) VALUES ('d32e3684-bafc-4068-8e08-e8816264e7b8', 'e2de02a0-39f8-4274-b9aa-f4e4185a917d');
INSERT INTO public.user_roles (user_id, role_id) VALUES ('d32e3684-bafc-4068-8e08-e8816264e7b8', 'd0693c83-64da-46f2-8520-590d4b104822');
INSERT INTO public.user_roles (user_id, role_id) VALUES ('0d7726be-f291-4545-8833-c1ac0302f86f', 'e2de02a0-39f8-4274-b9aa-f4e4185a917d');


