DROP DATABASE IF EXISTS erp_system;

CREATE DATABASE IF NOT EXISTS erp_system;

USE erp_system;

CREATE TABLE profiles
(
  id_profile  INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  start_date   DATE NOT NULL ,
  position    VARCHAR(100) NOT NULL ,
  department   VARCHAR(50) NOT NULL ,
  employment_status  VARCHAR(50) NOT NULL ,
  telephone  VARCHAR(30) NOT NULL ,
  email      VARCHAR(30) DEFAULT NULL UNIQUE ,
  photo      MEDIUMBLOB DEFAULT NULL,
  key_word   VARCHAR (50) DEFAULT NULL,
  answer_on_key_word VARCHAR (50) DEFAULT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE workers
(
  id_worker INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  login VARCHAR(30) NOT NULL UNIQUE ,
  password VARCHAR(30) NOT NULL,
  name VARCHAR(50) NOT NULL,
  id_profile INT NOT NULL,
  FOREIGN KEY (id_profile) REFERENCES profiles (id_profile) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE  project_tickets
(
  id_project_ticket INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL ,
  specification VARCHAR(500) NOT NULL ,
  status VARCHAR(50) NOT NULL ,
  start_ticket_date DATE DEFAULT NULL,
  end_ticket_date DATE DEFAULT NULL,
  deadline DATE NOT NULL,
  id_worker INT DEFAULT NULL,
  FOREIGN KEY (id_worker) REFERENCES workers (id_worker) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE comments_ticket
(
  id_comment INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  id_project_ticket INT NOT NULL ,
  id_worker INT NOT NULL ,
  comment VARCHAR(500) NOT NULL ,
  comment_date DATETIME NOT NULL ,
  FOREIGN KEY (id_project_ticket) REFERENCES project_tickets(id_project_ticket) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (id_worker) REFERENCES workers(id_worker) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE time_vocations
(
  id_time_vocation INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  id_worker INT NOT NULL ,
  start_voc_date DATE NOT NULL ,
  end_voc_date DATE DEFAULT NULL ,
  type VARCHAR(30) NOT NULL ,
  is_confirmed TINYINT(1) DEFAULT NULL ,
  FOREIGN KEY (id_worker) REFERENCES workers(id_worker) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE work_log
(
  id_work_log INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  id_project_ticket INT NOT NULL ,
  id_worker INT NOT NULL ,
  start_log_date DATE NOT NULL ,
  spent_time VARCHAR(50) DEFAULT NULL ,
  FOREIGN KEY (id_project_ticket) REFERENCES project_tickets(id_project_ticket) ON UPDATE CASCADE ON DELETE CASCADE ,
  FOREIGN KEY (id_worker) REFERENCES workers(id_worker) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE chat
(
  id_comment INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  id_worker INT NOT NULL ,
  comment_date DATETIME NOT NULL ,
  comment VARCHAR(500) NOT NULL ,
  FOREIGN KEY (id_worker) REFERENCES workers(id_worker) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8;