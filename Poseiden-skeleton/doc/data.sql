START TRANSACTION;

CREATE TABLE IF NOT EXISTS bid (
  bid_list_id INTEGER NOT NULL AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  `type` VARCHAR(30) NOT NULL,
  bid_quantity DOUBLE NOT NULL,
  ask_quantity DOUBLE,
  bid DOUBLE ,
  ask DOUBLE,
  benchmark VARCHAR(125),
  bid_list_date TIMESTAMP,
  commentary VARCHAR(125),
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  book VARCHAR(125),
  creation_name VARCHAR(125),
  creation_date TIMESTAMP ,
  revision_name VARCHAR(125),
  revision_date TIMESTAMP ,
  deal_name VARCHAR(125),
  deal_type VARCHAR(125),
  source_list_id VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS curve_point (
  id INTEGER NOT NULL AUTO_INCREMENT,
  curve_id INTEGER NOT NULL,
  as_of_date TIMESTAMP,
  term DOUBLE NOT NULL,
  `value` DOUBLE NOT NULL,
  creation_date TIMESTAMP,

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS rating (
  id INTEGER NOT NULL AUTO_INCREMENT,
  moodys_rating VARCHAR(125) NOT NULL,
  sand_p_rating VARCHAR(125) NOT NULL,
  fitch_rating VARCHAR(125) NOT NULL,
  order INTEGER NOT NULL,

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS rule (
  id INTEGER NOT NULL AUTO_INCREMENT,
  name VARCHAR(125) NOT NULL,
  description VARCHAR(125) NOT NULL,
  json VARCHAR(125) NOT NULL,
  template VARCHAR(512) NOT NULL,
  `sql_str` VARCHAR(125) NOT NULL,
  `sql_part` VARCHAR(125) NOT NULL,

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS trade (
  id INTEGER NOT NULL AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  `type` VARCHAR(30) NOT NULL,
  buy_quantity DOUBLE NOT NULL,
  sell_quantity DOUBLE,
  buy_price DOUBLE ,
  sell_price DOUBLE,
  trade_date TIMESTAMP,
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  benchmark VARCHAR(125),
  book VARCHAR(125),
  creation_name VARCHAR(125),
  creation_date TIMESTAMP ,
  revision_name VARCHAR(125),
  revision_date TIMESTAMP ,
  deal_name VARCHAR(125),
  deal_type VARCHAR(125),
  source_list_id VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users (
  id INTEGER NOT NULL AUTO_INCREMENT,
  username VARCHAR(125) NOT NULL,
  password VARCHAR(125) NOT NULL,
  fullname VARCHAR(125) NOT NULL,
  role VARCHAR(125) NOT NULL,

  PRIMARY KEY (id)
);

ALTER TABLE users ADD CONSTRAINT username UNIQUE (username);

insert into bid_list(id, account, type, bid_quantity) values
(1, 'Account Name 1', 'Account Type 1', 10),
(2, 'Account Name 2', 'Account Type 2', 20);

insert into trade(id, account, type, buy_quantity) values
(1, 'Account Name 1', 'Account Type 1', 10),
(2, 'Account Name 2', 'Account Type 2', 20);

insert into curve_point(id, curve_id, term, value) values
(1, 11, 31, 10),
(2, 22, 32, 20);

insert into rating(id, moodys_rating, sand_p_rating, fitch_rating, order_number) values
(1, 'Moodys Rating 1', 'Sand P Rating 1', 'Fitch Rating 1', 10),
(2, 'Moodys Rating 2', 'Sand P Rating 2', 'Fitch Rating 2', 20);

insert into rule_name(id, name, description, json, template, sql_str, sql_part) values
(1, 'Name 1', 'description 1', 'Json 1', 'Template 1', 'Sql Str 1', 'Sql Part 1'),
(2, 'Name 2', 'description 2', 'Json 2', 'Template 2', 'Sql Str 2', 'Sql Part 2');

insert into users(id, fullname, username, password, role) values
(1, 'Administrator', 'admin', '$2a$10$TO.V.deBj.MJqREwpJp.GO3RY6XVkPJdaPO5bXFiFcF5Z/pbFZL0C', 'ADMIN'),
(2, 'User', 'user', '$2a$10$YW6j2AEFb0M0ZpjVyXYnL.ovwXJue0/jqXRJkoqUcAufHjC4ccDf.', 'USER');

COMMIT;
