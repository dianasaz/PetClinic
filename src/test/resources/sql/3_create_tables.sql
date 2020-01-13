USE `mydatabasetest`;

CREATE TABLE `user`
(
  `user_id`     int PRIMARY KEY     NOT NULL AUTO_INCREMENT,
  `login`       varchar(255) UNIQUE NOT NULL,
  `password`    varchar(255)        NOT NULL,
  `role`        ENUM ('administrator', 'visitor'),
  `name`        varchar(255),
  `email`       varchar(255) UNIQUE NOT NULL,
  `phoneNumber` INTEGER,
  `address`     varchar(255)
) ENGINE = INNODB;

CREATE TABLE `pet`
(
  `id`            int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name`          varchar(255),
  `kind`          ENUM ('cat', 'dog', 'turtle', 'parrot', 'hamster'),
  `date_of_birth` date,
  `user_id`       int             NOT NULL
) ENGINE = INNODB;

CREATE TABLE `doctor`
(
  `id`   int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(255)
) ENGINE = INNODB;

CREATE TABLE `coupon`
(
  `id`         int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `time`       datetime,
  `doctor_id`  int             NOT NULL,
  `user_id`    int             NOT NULL,
  `pet_id`     int             NOT NULL,
  `service_id` int             NOT NULL
) ENGINE = INNODB;

CREATE TABLE `service`
(
  `id`    int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `price` int,
  `name`  varchar(255)
) ENGINE = INNODB;


CREATE TABLE `doctor_service`
(
  `doctor_id`  int NOT NULL,
  `service_id` int,
  CONSTRAINT PrKy PRIMARY KEY (`doctor_id`, `service_id`)
) ENGINE = INNODB;

ALTER TABLE `coupon`
  ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION ;

ALTER TABLE `coupon`
  ADD FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION ;

ALTER TABLE `coupon`
  ADD FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION ;

ALTER TABLE `coupon`
  ADD FOREIGN KEY (`service_id`) REFERENCES `service` (`id`)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION ;

ALTER TABLE `doctor_service`
  ADD FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE;

ALTER TABLE `doctor_service`
  ADD FOREIGN KEY (`service_id`) REFERENCES `service` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE;

ALTER TABLE `pet`
  ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE;



