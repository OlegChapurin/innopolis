create database db22 with template default encoding 'UTF-8';
CREATE TABLE if not exists public.user (id bigserial primary key,name varchar(150) NOT NULL,login varchar(15) NOT NULL,
password varchar(15) NOT NULL);
CREATE TABLE if not exists public.mail (id bigserial primary key,email varchar(150) NOT NULL,
user_id int REFERENCES public.user(id));
CREATE TABLE if not exists public.telephone (id bigserial primary key,telephone varchar(15) NOT NULL,
user_id int REFERENCES public.user(id));