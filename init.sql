create sequence project_id_seq start with 1 increment by 50;

create table projects
(
    id         bigint       not null default nextval('project_id_seq'),
    title      varchar(200) not null,
    owner      varchar(100) not null,
    created_at timestamp    not null default now(),
    updated_at timestamp,
    primary key (id)
);

insert into projects(title, owner, created_at) values
('E-commerce Platform', 'John Smith', '2021-06-26'),
('Mobile Banking App', 'Sarah Johnson', '2021-10-10'),
('Data Analytics Dashboard', 'Mike Chen', '2023-12-05'),
('Customer Portal', 'Emily Davis', '2024-08-15');