create table ${schema_name}.data_point
(
    id        integer primary key,
    file_id   varchar(100)     not null,
    date_time timestamp        not null,
    longitude double precision not null,
    latitude  double precision not null,
    depth     double precision not null
);

create sequence data_point_seq;
