use mydatabase;
INSERT INTO `user` (`login`,
                    `password`,
                    `role`,
                    `email`,
                    `name`,
                    `phoneNumber`,
                    `address`)
VALUES ('admin',
        'a66abb5684c45962d887564f08346e8d', /* MD5 хэш пароля "admin123456" */
        1,
        'dinka222@mail.ru',
        'Diana Sazanchuk',
        291083264,
        'Dzerzinskogo 20, 20');