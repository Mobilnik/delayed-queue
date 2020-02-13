CREATE TABLE IF NOT EXISTS users
(
  id                BIGINT PRIMARY KEY,
  name              VARCHAR(255) NOT NULL,
  deactivation_time TIMESTAMP,
  is_active         BOOLEAN
);


CREATE SEQUENCE IF NOT EXISTS user_id_seq START WITH 1 MINVALUE 1 INCREMENT BY 1;


CREATE TABLE IF NOT EXISTS orders
(
  id                BIGINT PRIMARY KEY,
  deactivation_time TIMESTAMP,
  is_active         BOOLEAN
);


CREATE SEQUENCE IF NOT EXISTS order_id_seq START WITH 1 MINVALUE 1 INCREMENT BY 1;