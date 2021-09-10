SET NAMES utf8;
DROP DATABASE IF EXISTS db_pay;

DROP USER 'user'@'localhost';

CREATE DATABASE db_pay CHARACTER SET utf8 COLLATE utf8_bin;

USE db_pay;

-- ENUM tables

CREATE TABLE en_roles
(

    id   INTEGER     NOT NULL PRIMARY KEY,
    name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO en_roles
VALUES (0, 'admin');
INSERT INTO en_roles
VALUES (1, 'client');


CREATE TABLE en_doc_statuses
(
    id   INTEGER     NOT NULL PRIMARY KEY,
    name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO en_doc_statuses
VALUES (0, 'prepared');
INSERT INTO en_doc_statuses
VALUES (1, 'sent');


CREATE TABLE en_user_statuses
(
    id   INTEGER     NOT NULL PRIMARY KEY,
    name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO en_user_statuses
VALUES (0, 'valid');
INSERT INTO en_user_statuses
VALUES (1, 'blocked');
INSERT INTO en_user_statuses
VALUES (2, 'new');

-- references tables

CREATE TABLE ref_users
(

    id         INTEGER     NOT NULL auto_increment PRIMARY KEY,
    login      VARCHAR(20) NOT NULL UNIQUE,
    password   VARCHAR(20) NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    last_name  VARCHAR(20) NOT NULL,
    job_id     INTEGER     NOT NULL REFERENCES en_roles (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    status_id  INTEGER     NOT NULL REFERENCES en_roles (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    locale     VARCHAR(3) DEFAULT 'ru'
);

INSERT INTO ref_users
VALUES (DEFAULT, 'admin', 'admin', 'Alex', 'Savelev', 0, 0, 'en');
INSERT INTO ref_users
VALUES (DEFAULT, 'client', 'client', 'Nick', 'Vasilchuk', 1, 0, 'en');
INSERT INTO ref_users
VALUES (DEFAULT, 'борисенко', 'борисенко', 'Михаил', 'Борисенко', 1, 1, 'en');

INSERT INTO ref_users
VALUES (DEFAULT, 'васильев', 'васильев', 'Иван', 'Васильев', 1, 0, DEFAULT);

CREATE TABLE ref_counts
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    count      VARCHAR(34) UNIQUE NOT NULL,
    user_id    INT                NOT NULL REFERENCES ref_users (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    is_blocked TINYINT            NOT NULL DEFAULT 0
);

INSERT INTO ref_counts
VALUES (DEFAULT, 'UA593510050000026201111111111', 2, DEFAULT);
INSERT INTO ref_counts
VALUES (DEFAULT, 'UA593510050000026202222222222', 2, 1);
INSERT INTO ref_counts
VALUES (DEFAULT, 'UA593510050000026203333333333', 3, DEFAULT);


CREATE TABLE ref_credit_cards
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    number     BIGINT UNIQUE NOT NULL,
    expiration DATETIME      NOT NULL,
    cvv        INT           NOT NULL,
    count_id   INT           NOT NULL REFERENCES ref_counts (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT

);

INSERT INTO ref_credit_cards
VALUES (DEFAULT, 5555444488887231, '2025-09-10 00:00:00', 223, 1);
INSERT INTO ref_credit_cards
VALUES (DEFAULT, 5555333388886342, '2025-10-11 00:00:00', 698, 2);
INSERT INTO ref_credit_cards
VALUES (DEFAULT, 5555666688885453, '2021-12-12 00:00:00', 258, 2);
INSERT INTO ref_credit_cards
VALUES (DEFAULT, 5555777788884364, '2023-01-13 00:00:00', 369, 3);

-- register tables

CREATE TABLE reg_unlock_requests
(
    count_id INT      NOT NULL UNIQUE REFERENCES ref_counts (id),
    actual   TINYINT  NOT NULL DEFAULT 1,
    date     DATETIME NOT NULL DEFAULT NOW()
);

INSERT INTO reg_unlock_requests
VALUES (2, DEFAULT, DEFAULT);

-- documents tables

CREATE TABLE doc_payments
(

    id             INT PRIMARY KEY AUTO_INCREMENT,
    date           DATETIME    NOT NULL DEFAULT NOW(),
    user_id        INT         NOT NULL REFERENCES ref_users (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    count_id       INT         NOT NULL REFERENCES ref_counts (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    count_receiver VARCHAR(34) NOT NULL,
    sum            DECIMAL(15, 2)       DEFAULT 0.00,
    status_id      INT         NOT NULL REFERENCES en_doc_statuses (id)

);

INSERT INTO doc_payments
VALUES (DEFAULT, '2021-09-05 14:05:31', 2, 1, 'UA593510050000026208888888888', 7000.45, 0);
INSERT INTO doc_payments
VALUES (DEFAULT, '2021-09-05 14:15:31', 2, 1, 'UA593510050000026208888825893', 8000.60, 1);
INSERT INTO doc_payments
VALUES (DEFAULT, '2021-09-05 14:26:00', 2, 1, 'UA593510050000026206548888898', 9009.00, 0);


CREATE USER 'user'@'localhost' IDENTIFIED BY 'pass';
GRANT ALL PRIVILEGES ON db_pay.* TO 'user'@'localhost';

-- You can unrem next lines for test creation tables.
/*SELECT * FROM en_jobs;
SELECT * FROM en_statuses;
SELECT * FROM ref_counts;
SELECT * FROM ref_credit_cards;
SELECT * FROM ref_users;
SELECT * FROM doc_payments;*/


