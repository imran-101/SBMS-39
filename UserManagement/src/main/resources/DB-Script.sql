create table city_master (city_id integer not null auto_increment, city_name varchar(255), state_id integer, primary key (city_id)) engine=InnoDB;

create table country_master (country_id integer not null auto_increment, country_name varchar(255), primary key (country_id)) engine=InnoDB;

create table state_master (state_id integer not null auto_increment, state_name varchar(255), country_id integer, primary key (state_id)) engine=InnoDB;

create table user_entity (user_id integer not null auto_increment, created_date date, email varchar(255), name varchar(255), phno bigint, pwd varchar(255), pwd_updated varchar(255), updated_date date, city_id integer, country_id integer, state_id integer, primary key (user_id)) engine=InnoDB;

alter table city_master add constraint FKfxtjuwt9iqx9n7xl6f8wl6uu4 foreign key (state_id) references state_master (state_id);

alter table state_master add constraint FKbit3kv24ddslslqs9sy3evpjn foreign key (country_id) references country_master (country_id);

alter table user_entity add constraint FKbk250jcd4xapg3q0mqagw8ysu foreign key (city_id) references city_master (city_id);

alter table user_entity add constraint FKo4hp7g9bbxoqt07mkehhjkroa foreign key (country_id) references country_master (country_id);

alter table user_entity add constraint FKli06vmmnabl31ym5y78ayt0ap foreign key (state_id) references state_master (state_id);




insert into country_master values(1, 'india');
insert into country_master values(2, 'usa');

insert into state_master values(1, 'bihar', 1);
insert into state_master values(2, 'telangana', 1);

insert into state_master values(3, 'ohio', 2);
insert into state_master values(4, 'washington', 2);

insert into city_master values(1, 'jamui', 1);
insert into city_master values(2, 'patna', 1);

insert into city_master values(3, 'hyderabad', 2);
insert into city_master values(4, 'warangal', 2);

insert into city_master values(5, 'miamisburg', 3);
insert into city_master values(6, 'reynoldsburg', 3);

insert into city_master values(7, 'seattle', 4);
insert into city_master values(8, 'redmond', 4);