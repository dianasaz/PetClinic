use mydatabase;
INSERT INTO `user` (`login`,
                    `password`,
                    `role`,
                    `email`,
                    `name`,
                    `phoneNumber`,
                    `address`)
VALUES ('diana',
        '65914b54fa170b139aa943baeddc9c51', /*string1999*/
        2,
        'din@mail.ru',
        'Dina Horty',
        291011102,
        'ул. Покровского, 13-1'),
       ('vadim',
        '853f6953382e0929f2f6e890cae73e56', /*user19999*/
        2,
        'dinqwe@mail.ru',
        'Vadim Kozlov',
        291981102,
        'pr. Nezavisimosti 32-1, 34');

INSERT INTO `doctor` (`name`)
VALUES ('Александра Гром'),
       ('Алексей Батура'),
       ('Владимир Левченко'),
       ('Анастасия Засим');

INSERT INTO `service` (`price`,
                       `name`)
VALUES (3400, 'Кастрация'),
       (5200, 'Чипирование'),
       (3200, 'Стерилизация'),
       (1200, 'Анализы'),
       (4500, 'Профилактика');

INSERT INTO `doctor_service` (`doctor_id`, `service_id`)
VALUES (1, 1),
       (1, 3),
       (2, 2),
       (2, 3),
       (3, 2),
       (4, 4),
       (4, 5);

