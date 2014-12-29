drop table activity;
drop table assignment;
drop table attachment;
drop table task;
drop table member;
drop table project;
drop table status;
drop table role;
drop table employee;
drop table position;

create table position(id integer not null generated always as identity constraint position_pk primary key, name varchar(255));
create table employee(id integer not null generated always as identity constraint employee_pk primary key, first_name varchar(255), last_name varchar(255),position_id integer,login varchar(255),password varchar(255));

create table attachment(id integer not null generated always as identity constraint attachment_pk primary key, name_file varchar(255),coded_name_file varchar(255), task_id integer,text varchar(255));
create table task(id integer not null generated always as identity constraint task_pk primary key, project_id integer, description varchar(255),psd date,ped date,asd date,aed date,status_id integer);
create table status(id integer not null generated always as identity constraint status_pk primary key, name varchar(255));

create table project(id integer not null generated always as identity constraint project_pk primary key, name varchar(255), description varchar(255),psd date,ped date,asd date,aed date,status_id integer);
create table member(id integer not null generated always as identity constraint members_pk primary key, project_id integer, employee_id integer,role_id integer);
create table role(id integer not null generated always as identity constraint role_pk primary key, name varchar(255));


--               assignment is correct
create table assignment(id integer not null generated always as identity constraint assignment_pk primary key, member_id integer,task_id integer,description varchar(255),psd date,ped date,asd date,aed date);
--               activity is correct
create table activity(id integer not null generated always as identity constraint activity_pk primary key, activity_date date,duration integer, comment varchar(255), project_id integer,assignment_id integer);

ALTER TABLE employee ADD CONSTRAINT employee_position_FK
Foreign Key (position_id) REFERENCES position (id);
ALTER TABLE member ADD CONSTRAINT member_role_FK
Foreign Key (role_id) REFERENCES role (id);
ALTER TABLE member ADD CONSTRAINT member_employee_FK
Foreign Key (employee_id) REFERENCES employee (id);
ALTER TABLE member ADD CONSTRAINT member_project_FK
Foreign Key (project_id) REFERENCES project (id);
ALTER TABLE project ADD CONSTRAINT project_status_FK
Foreign Key (status_id) REFERENCES status (id);
ALTER TABLE task ADD CONSTRAINT task_status_FK
Foreign Key (status_id) REFERENCES status (id);
ALTER TABLE task ADD CONSTRAINT task_project_FK
Foreign Key (project_id) REFERENCES project (id);

ALTER TABLE attachment ADD CONSTRAINT attachment_task_FK
Foreign Key (task_id) REFERENCES task (id);

ALTER TABLE assignment ADD CONSTRAINT assignment_task_FK
Foreign Key (task_id) REFERENCES task (id);
ALTER TABLE assignment ADD CONSTRAINT assignment_member_FK
Foreign Key (member_id) REFERENCES member (id);
ALTER TABLE activity ADD CONSTRAINT activity_assignment_FK
Foreign Key (assignment_id) REFERENCES assignment (id);

insert into position(name)values ('Administrator');
insert into position(name)values ('Manager');
insert into position(name)values ('Junior');
insert into position(name)values ('Lead');


insert into employee(first_name, last_name, position_id, login, password)values ('Viktar','Kapachou',1,'nev1l','123');
insert into employee(first_name, last_name, position_id, login, password)values ('Vitaliy','Gapanovich',2,'vitaliy','123');
insert into employee(first_name, last_name, position_id, login, password)values ('Aliaksei','Osipov',3,'osipov','123');

insert into status(name)values('"No started"');
insert into status(name)values('Started');
insert into status(name)values('Suspended');
insert into status(name)values('Finished');

insert into project(name, description,psd,ped,asd,aed,status_id)values('Airport','Access system for airport','2012-12-12','2012-12-16','2012-12-12','2012-12-16',1);
insert into project(name, description,psd,ped,asd,aed,status_id)values('Stadium','Security system','2012-12-12','2012-12-15','2012-12-12','2012-12-15',2);
insert into project(name, description,psd,ped,asd,aed,status_id)values('Web-project','Test project','2012-12-12','2012-12-15','2012-12-12','2012-12-14',3);
insert into project(name, description,psd,ped,asd,aed,status_id)values('Station','Shedule of trains','2012-12-12','2012-12-15','2012-12-12','2012-12-14',4);

insert into role(name)values('Administrator');
insert into role(name)values('Manager');
insert into role(name)values('Lead');	
insert into role(name)values('Developer');

insert into task(project_id,description, psd,ped,asd,aed,status_id)values(1,'Create database','2012-12-12','2012-12-13','2012-12-12','2012-12-13',1);
insert into task(project_id,description, psd,ped,asd,aed,status_id)values(2,'Inject spring in project','2012-12-12','2012-12-13','2012-12-12','2012-12-13',1);
insert into task(project_id,description, psd,ped,asd,aed,status_id)values(2,'Testing of project','2012-12-12','2012-12-13','2012-12-12','2012-12-13',2);
insert into task(project_id,description, psd,ped,asd,aed,status_id)values(3,'SQL queries','2012-12-12','2012-12-14','2012-12-12','2012-12-13',2);
insert into task(project_id,description, psd,ped,asd,aed,status_id)values(4,'Front-end','2012-12-12','2012-12-13','2012-12-12','2012-12-13',3);

insert into member(project_id,employee_id,role_id)values(1,1,1);
insert into member(project_id,employee_id,role_id)values(1,3,4);

select * from member;