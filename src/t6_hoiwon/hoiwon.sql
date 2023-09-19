show tables;

create table hoiwon (
	idx int not null auto_increment primary key,
	name varchar(20) not null,
	age int default 20,
	address varchar(50),
	gender char(2) default '여자'
);

desc hoiwon;

insert into hoiwon values (default, '대추나무', 40, '경주', '남자');
insert into hoiwon values (default, '가가가', 40, '청주','남자');
insert into hoiwon values (default, '나나나', 24, '광주','남자');
insert into hoiwon values (default, '다다다', 25, '전주','남자');
insert into hoiwon values (default, '라라라', 26, '나주','남자');
insert into hoiwon values (default, '마마마', 67, '음성','여자');
insert into hoiwon values (default, '바바바', 80, '남양주','남자');
insert into hoiwon values (default, '사사사', 90, '광양','남자');
insert into hoiwon values (default, '아아아', 67, '인천','여자');
insert into hoiwon values (default, '자자자', 46, '괴산','남자');
insert into hoiwon values (default, '차차차', 42, '청주','여자');
insert into hoiwon values (default, '카카카', 12, '서울','남자');
insert into hoiwon values (default, '파파파', 21, '서울','여자');
insert into hoiwon values (default, '하하하', 37, '대전','남자');

select * from hoiwon;

delete from hoiwon where name = 'null';











