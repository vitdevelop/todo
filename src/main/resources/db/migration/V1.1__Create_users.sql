create table users
(
    id         bigserial primary key,
    username   varchar(255) not null,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    created_on timestamp default now(),
    updated_on timestamp null
)