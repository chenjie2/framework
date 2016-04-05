CREATE TABLE users
(
  id character(36) NOT NULL,
  age integer,
  nice_name character(32) DEFAULT NULL,
  user_name character(32) DEFAULT NULL,
  CONSTRAINT pk_users PRIMARY KEY (id)
)