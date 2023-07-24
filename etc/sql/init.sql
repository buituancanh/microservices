create database accounts;

create table t_user
(
    id      bigint primary key auto_increment,
    number  varchar(9)                not null,
    name    varchar(50)               not null,
    email   varchar(50)               not null,
    unique (number)
);

create table t_address
(
    id         bigint primary key auto_increment,
    user_id    bigint(9) not null,
    street     varchar(200) not null,
    city       varchar(100) not null
);

alter table t_address
    add foreign key (user_id)
        references t_user(id);

create database orders;

create table t_order
(
    id          bigint primary key auto_increment,
    user_number varchar(9)   not null,
    timestamp   timestamp    not null,
    email       varchar(100) not null,
    amount      bigint(100)  not null
);