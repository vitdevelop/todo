create table Todo_to_Friends
(
    user_id     BIGINT not null,
    friend_id   BIGINT not null,
    todo_id     BIGINT not null,
    editable    bool not null
)