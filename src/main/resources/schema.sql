create table actor
(
   id BIGINT not null,
   login VARCHAR(255) not null,
   avatar VARCHAR(255) not null,
   max_streak BIGINT not null,
   running_streak BIGINT not null,
   primary key(id)
);

create table repo
(
   id BIGINT not null,
   name VARCHAR(255) not null,
   url VARCHAR(255) not null,
   primary key(id)
);

create table event
(
   id BIGINT not null,
   type VARCHAR(255) not null,
   actorid BIGINT not null,
   repoid BIGINT not null,
   created_at TIMESTAMP not null,
   primary key(id),
   foreign key (actorid) references actor(id),
   foreign key (repoid) references repo(id),
);