--           create table journal(id integer not null generated always as identity constraint activity_pk primary key, activity_date date,duration integer, comment varchar(255), assignment_id integer);