create table todo
(
    id          bigserial primary key,
    name        varchar(255) not null,
    description text         null,
    created_on  timestamp default now(),
    updated_on  timestamp    null
)