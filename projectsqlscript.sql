create database expense_db;
create table users (
id bigint auto_increment primary key,
username varchar(255) not null unique,
email varchar(255)not null unique,
pass varchar(255) not null 
);
