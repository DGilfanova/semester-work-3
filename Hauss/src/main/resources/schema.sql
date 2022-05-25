create table if not exists persistent_logins
(
    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used varchar(64) not null
);

-- insert into style (name) values ('Classic');
-- insert into style (name) values ('Modern');
-- insert into style (name) values ('Traditional');
-- insert into style (name) values ('Scandinavian');
--
-- insert into currency (name) values ('RUB');
-- insert into currency (name) values ('USD');
-- insert into currency (name) values ('EUR');
--
-- insert into idea_category (name) values ('House');
-- insert into idea_category (name) values ('Garage');
-- insert into idea_category (name) values ('Garden');
-- insert into idea_category (name) values ('Bathroom');
-- insert into idea_category (name) values ('Bedroom');
-- insert into idea_category (name) values ('Hall');
--
-- insert into offer_category (name) values ('Designer');
-- insert into offer_category (name) values ('Architect');
-- insert into offer_category (name) values ('Developer');
-- insert into offer_category (name) values ('Painter');
