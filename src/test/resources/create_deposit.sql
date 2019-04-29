create table DEPOSIT
(
    ID INTEGER not null,
    CUSTOMER_ID INTEGER NOT NULL ,
    AMOUNT DOUBLE,
    constraint DEPOSIT_CUSTOMER_ID_fk
        foreign key (CUSTOMER_ID) references CUSTOMER (ID)
);

create unique index DEPOSIT_ID_uindex
    on DEPOSIT (ID);

alter table DEPOSIT
    add constraint DEPOSIT_pk
        primary key (ID);

create sequence deposit_generator;

