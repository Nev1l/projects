connect 'jdbc:derby://localhost:1527/db;create=true;user=user;password=123';
/*drop table activity;
drop table assignment;
drop table attachment_task;
drop table attachment_project;
drop table task;
drop table member;
drop table project;
drop table status;
drop table role;
drop table employee;*/
drop table position;

create table position(id integer not null generated always as identity constraint position_pk primary key, name varchar(50));