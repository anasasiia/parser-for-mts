create table tariffs (
    tariff_id bigserial not null,
    name varchar(255),
    description varchar(255),
    benefits varchar(255),
    internet varchar(255),
    minutes varchar(255),
    tv varchar(255),
    wifi varchar(255),
    price bigint,
    primary key (tariff_id)
    )