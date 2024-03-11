-- CREATE USER
INSERT INTO `alternative-voting-app`.`user` (`id`, `username`, `password`, `enabled`) VALUES ('1', 'voter1', '$2a$10$DJYiZRmrtiwjNKZ0njKPCunYNo/dw3fALwuHgdzpYpTfm7yT9rCxO', '1');
-- test password = password1

-- CREATE ROLE
INSERT INTO `alternative-voting-app`.`role` (`id`, `name`) VALUES ('1', 'ROTE_VOTER');

-- CREATE USER_ROLE
INSERT INTO `alternative-voting-app`.`user_role` (`user_id`, `role_id`) VALUES ('1', '1');