
--drop table component_stock1;

--DELETE from component_stock1 where name='aaaa';

--DELETE FROM component_stock1;

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

INSERT INTO component_stock1
VALUES ('����ȣ', '86', '91', '74', '94', '345', '86.25', 'B');

INSERT INTO component_stock1
VALUES ('�̱��', '90', '90', '100', '80', '360', '90.0', 'A');

INSERT INTO component_stock1
VALUES ('�����', '67', '80', '75', '98', '320', '80.0', 'B');

INSERT INTO component_stock1
VALUES ('������', '60', '74', '68', '71', '273', '68.25', 'D');

INSERT INTO component_stock1
VALUES ('�ڼ�ȣ', '57', '62', '81', '76', '276', '69.0', 'D');

INSERT INTO component_stock1
VALUES ('������', '60', '52', '66', '46', '224', '56.0', 'E');

INSERT INTO component_stock1
VALUES ('������', '80', '96', '67', '52', '295', '73.75', 'C');

INSERT INTO component_stock1
VALUES ('�����', '76', '71', '79', '76', '302', '75.5', 'C');

INSERT INTO component_stock1
VALUES ('������', '91', '90', '96', '90', '367', '91.75', 'A');

INSERT INTO component_stock1
VALUES ('�躽��', '81', '60', '76', '94', '311', '77.75', 'C');

SELECT * FROM component_stock1;

commit;