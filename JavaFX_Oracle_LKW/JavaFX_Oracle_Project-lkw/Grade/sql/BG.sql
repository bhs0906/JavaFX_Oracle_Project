SELECT * FROM component_stock;

create table component_stockl (

    COMPONENT_NAME varchar2(100) unique not null,
    COMPONENT_CODE varchar2(100),
    COM_GUK  NUMBER(10),
    COM_eng  NUMBER(10),
    COM_math  NUMBER(10),
    COM_total  NUMBER(10),
    COM_avg  NUMBER(10),
    COM_rank  NUMBER(10)
--    constraint pk_COMPONENT_NAME primary key(COMPONENT_NAME)
);

SELECT * FROM component_stockl;

insert into component_stockl
VALUES ('박현서', '1', '60', '90', '85', '235', '78', '1');
