CREATE TABLE IF NOT EXISTS users
(
  id                BIGINT PRIMARY KEY,
  name              VARCHAR(255) NOT NULL,
  deactivation_time TIMESTAMP,
  is_active         BOOLEAN
);


CREATE SEQUENCE IF NOT EXISTS user_id_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;