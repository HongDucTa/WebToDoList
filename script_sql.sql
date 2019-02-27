create database todolist;

CREATE TABLE `todolist`.`account` (
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `role` ENUM ('instructor','student'),
  PRIMARY KEY (`username`));

CREATE TABLE `todolist`.`todo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(400) NULL,
  `date` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

insert into todolist.account values ('Francois','motdepasse','instructor');
insert into todolist.account values ('Marc','marcmotdepasse','student');
select * from todolist.account;

insert into todolist.todo values (1,'Rappel deadline rendu projet : 28/10','2018-10-23 18:05:23');
insert into todolist.todo values (null,'Hello again','2018-10-24 13:54:08');
insert into todolist.todo values (null,'Test time',current_timestamp());
select * from todolist.todo;

/*
drop table todolist.todo;
drop table todolist.account;
drop database todolist;
*/