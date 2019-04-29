create table CUSTOMER
(
    ID       int           not null,
    USER     VARCHAR2(255) not null,
    PASSWORD VARCHAR2(255) not null
);

create unique index CUSTOMER_ID_uindex
    on CUSTOMER (ID);

alter table CUSTOMER
    add constraint CUSTOMER_pk
        primary key (ID);

create sequence cus_seq;



