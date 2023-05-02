create table friends_requests
(
	user_id bigint      not null,
	friend_id bigint    not null,
    created_on          timestamp default now(),
	foreign key (user_id) references users(id),
	foreign key (friend_id) references users(id)
)