CREATE database patient
(
    id         varchar(255) not null primary key AUTO_INCREMENT,
    lastname   varchar(255) not null,
    firstname  varchar(255) not null,
    birth      DATE note null,
    gender     varchar(1) not null,
    address    varchar(255) not null,
    phone      varchar(255) not null,

)