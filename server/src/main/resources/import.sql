INSERT INTO roles (id, created, status, updated, name) VALUES ('e2de02a0-39f8-4274-b9aa-f4e4185a917d', '2020-08-23 15:52:40.000000', 'ACTIVE', '2020-08-23 15:52:54.000000', 'ROLE_USER')
INSERT INTO roles (id, created, status, updated, name) VALUES ('d0693c83-64da-46f2-8520-590d4b104822', '2020-08-23 15:52:40.000000', 'ACTIVE', '2020-08-23 15:52:54.000000', 'ROLE_ADMIN')
INSERT INTO users (id, created, status, updated, email, first_name, last_name, password, username) VALUES ('d32e3684-bafc-4068-8e08-e8816264e7b8', '2020-08-23 15:52:40.000000', 'ACTIVE', '2020-08-23 15:52:40.000000', 'bohdan@gmail.com', 'Bohdan', 'Zinkevych' , '$2y$12$Y.YwBhV4FssWH8xk/HPfRevr6TeEJZ6UzwI/emUdyqCmackkcqeiu', 'Bohdan');
INSERT INTO users (id, created, status, updated, email, first_name, last_name, password, username) VALUES ('0d7726be-f291-4545-8833-c1ac0302f86f', '2020-08-23 15:52:40.000000', 'ACTIVE', '2020-08-23 15:52:40.000000', null, 'User', 'UserLastName' , '$2y$12$Y.YwBhV4FssWH8xk/HPfRevr6TeEJZ6UzwI/emUdyqCmackkcqeiu', 'User');
INSERT INTO public.user_roles (user_id, role_id) VALUES ('d32e3684-bafc-4068-8e08-e8816264e7b8', 'e2de02a0-39f8-4274-b9aa-f4e4185a917d');
INSERT INTO public.user_roles (user_id, role_id) VALUES ('d32e3684-bafc-4068-8e08-e8816264e7b8', 'd0693c83-64da-46f2-8520-590d4b104822');
INSERT INTO public.user_roles (user_id, role_id) VALUES ('0d7726be-f291-4545-8833-c1ac0302f86f', 'e2de02a0-39f8-4274-b9aa-f4e4185a917d');
INSERT INTO public.test (id, branch, question) VALUES ('1364b107-639c-496d-be10-a838557b5e12', 'java', 'Accessing Local Variables of the Enclosing Scope');
INSERT INTO public.answers (id, answer, is_correct, test_id) VALUES ('3ac15249-8650-4488-a163-a795b8449ea9', 'However, unlike local and anonymous classes, lambda expressions do not have any shadowing issues', true, '1364b107-639c-496d-be10-a838557b5e12');
INSERT INTO public.answers (id, answer, is_correct, test_id) VALUES ('56c526fd-915a-4a77-8c68-6b6015e6f7f4', 'Lambda expressions are lexically scoped. This means that they do not inherit any names from a supertype or introduce a new level of scoping. Declarations in a lambda expression are interpreted just as they are in the enclosing environment', false, '1364b107-639c-496d-be10-a838557b5e12');



