create sequence id_generator start 2 increment 1;
create table phrase (
    phrase_id  bigserial not null,
    created_date varchar(255),
    description varchar(255),
    last_modified varchar(255),
    translation varchar(255),
    phrase_type varchar(255),
    primary key (phrase_id)
);
create table refresh_token (
    id  bigserial not null,
    created_date varchar(255),
    token varchar(255),
    primary key (id)
);
create table roles (
    role_id  bigserial not null,
    role_name varchar(255),
    primary key (role_id)
);
create table user_result (
    user_id int8 not null,
    created_date varchar(255),
    percent float8 not null,
    right_answers float8 not null,
    total_phrases float8 not null,
    wrong_answers float8 not null
);
create table users (
    user_id int8 not null,
    created_date varchar(255),
    last_modified varchar(255),
    name varchar(255) not null,
    password varchar(255) not null,
    primary key (user_id)
);
create table users_phrase (
    user_id int8 not null,
    phrase_id int8 not null,
    primary key (user_id, phrase_id)
);
create table users_roles (
    user_id int8 not null,
    role_id int8 not null,
    primary key (user_id, role_id)
);
create table verification_token (
    id  bigserial not null,
    created_date date,
    last_modified date,
    token varchar(255),
    primary key (id)
);
--alter table users drop constraint UK_3g1j96g94xpk3lpxl2qbl985x;
alter table users add constraint UK_3g1j96g94xpk3lpxl2qbl985x unique (name);
alter table user_result add constraint FKqgaix1r2x17dsy39ayq4pbneg foreign key (user_id) references users;
alter table users_phrase add constraint FKposqbdbii8k0nhyrrip74avyk foreign key (phrase_id) references phrase;

insert into roles VALUES (1, 'ROLE_USER');
insert into roles VALUES (2, 'ROLE_ADMIN');
insert into users VALUES (1, '2021-01-01 11:01', '2021-01-01 11:01', 'admin', '$2a$10$O2c3aEm06yQIZBqgu3gpBO1YHoRkwr/F6NpfPLyJkte/JFUSp9AN2');
insert into users_roles VALUES(1,1);
insert into users_roles VALUES(1,2);
