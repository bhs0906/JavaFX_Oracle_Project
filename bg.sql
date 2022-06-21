create table component_stock1 (
name varchar2(20),
KOR number,
ENG number,
MATH number,
SCI number,
TOTAL number,
AVG number,
GRADE varchar2(20)
);

SELECT * FROM component_stock1;

drop table component_stock1;

DELETE from component_stock1 where name='aaa';

