insert into repo(id, name, url) values(1, 'Repo1', 'url1');
insert into repo(id, name, url) values(2, 'Repo1', 'url1');
insert into repo(id, name, url) values(3, 'Repo1', 'url1');
insert into repo(id, name, url) values(4, 'Repo1', 'url1');
insert into repo(id, name, url) values(5, 'Repo1', 'url1');

insert into actor(id, avatar, login, max_streak, running_streak) values(1, 'Actor1', 'avatar1', 0, 0);
insert into actor(id, avatar, login, max_streak, running_streak) values(2, 'Actor2', 'avatar1', 0, 0);
insert into actor(id, avatar, login, max_streak, running_streak) values(3, 'Actor3', 'avatar1', 0, 0);

insert into event(id, type, actorid, repoid, created_at) values(1, 'Type1', 1, 2, {ts '2018-03-03 18:47:52.69'});
insert into event(id, type, actorid, repoid, created_at) values(2, 'Type2', 3, 1, {ts '2018-03-03 18:47:52.69'});
insert into event(id, type, actorid, repoid, created_at) values(3, 'Type2', 2, 4, {ts '2018-03-03 18:47:52.69'});
insert into event(id, type, actorid, repoid, created_at) values(4, 'Type1', 1, 2, {ts '2018-03-03 18:47:52.69'});