select * from test1;

show tables;

create table test1 (
	idx int not null auto_increment primary key,
	name varchar(20) not null
);

desc test1;

select * from test1;

insert into test1 values (default, '홍길동');
insert into test1 values (default, '김말숙');
insert into test1 values (default, '이기자');
insert into test1 values (default, '소나무');
insert into test1 values (default, '대나무');
insert into test1 values (default, '감나무', 35);
insert into test1 values (default, '대추나무', 40, '경주', '남자');
insert into test1 values (default, '가가가', 40, '청주','남자');
insert into test1 values (default, '나나나', 24, '광주','남자');
insert into test1 values (default, '다다다', 25, '전주','남자');
insert into test1 values (default, '라라라', 26, '나주','남자');
insert into test1 values (default, '마마마', 67, '음성','여자');
insert into test1 values (default, '바바바', 80, '남양주','남자');
insert into test1 values (default, '사사사', 90, '광양','남자');
insert into test1 values (default, '아아아', 67, '인천','여자');
insert into test1 values (default, '자자자', 46, '괴산','남자');
insert into test1 values (default, '차차차', 42, '청주','여자');
insert into test1 values (default, '카카카', 12, '서울','남자');
insert into test1 values (default, '파파파', 21, '서울','여자');
insert into test1 values (default, '하하하', 37, '대전','남자');
insert into test1 values (연습1, 15);

drop table test1; -- 테이블의 구조까지 완전히 삭제한다.(데이터를 포함)

-- 컬럼 추가(add column)
-- test1 테이블에 age 컬럼을 추가(int default 15)
alter table test1 add column age int default 15; -- 테이블의 필드 추가(add column)

-- test1 테이블에 addr 컬럼을 추가(varchar(50))
alter table test1 add column addr varchar(50);

-- test1 테이블의 addr 컴럼을 address로 변경(change / mofify)하시오.
alter table test1 change column addr address varchar(50);

-- test1 테이블의 address 컬럼의 varchar(50)을 30Byte로 변경(change / modify)하시오.
alter table test1 modify column address varchar(30);

-- 컬럼 삭제(drop column)
alter table test1 drop column address;

-- address 컬럼을 추가(varchar(30) default '청주')
alter table test1 add column address varchar(30) default '청주';

-- 성별(gender)필드 추가(add column)
alter table test1 add column gender char(2) default '남자';

-- test1 테이블을 exam 테이블로 이름변경(rename)
alter table test1 rename exam;
alter table exam rename test1;

-- exam 테이블을 삭제하시오(구조를 완전삭제)
drop table exam;

--------------------------------------------------------------------------------------
-- DML(데이터 조작어)

desc test1;

-- test1 테이블 내용보기
select * from test1;


insert into test1 values (default, '가나다', 55, '제주');

-- test1 테이블의 '성명/나이' 정보의 모든 레코드를 보여주시오.
select name,age from test1;

-- 홍길동의 나이를 25세로 변경(update 테이블명 set 필드명 = '변경내용' where _ 조건 _;)
update test1 set age = 25 where name = '홍길동';

-- 홍길동의 성별을 여자로 변경
update test1 set gender = '여자' where name = '홍길동';

-- 이기자의 성별을 여자로 변경
update test1 set gender = '여자' where name = '이기자'

-- '소나무/대나무/가나다/나나나/마마마'의 성별을 '여자'로 변경
update test1 set gender = '여자' where name = '소나무' or name = '대나무' or name = '가나다' or name = '나나나' or name = '마마마';

-- '파파파/차차차/사사사'의 성별을 '여자'로 변경
update test1 set gender = '여자' where name in ('파파파','차차차','사사사');

-- 졍렬 : order by 필드명 _옵션_   : 옵션 생략 시 오름차순(asc), 내림차순(desc)
-- 성명 오름차순 출력
select * from test1 order by name;
select * from test1 order by name desc;

-- 나이 내림차순
select * from test1 order by age desc;

-- 성별 오름차순
select * from test1 order by gender;

-- 성별 오름차순, 성별이 같으면 두 번 째 키는 성명 내림차순 출력
select * from test1 order by gender, name desc

-- 성별 내림차순, 단, 성별이 같으면 나이가 적은 사람을 우선적으로 출력하시오
select * from test1 order by gender desc, age;

-- 성별 내림차순, 2차 성별이 같으면 주소 오름차순, 주소가 같으면 나이 내림차순, idx 내림차순
select * from test1 order by gender desc, address asc, age desc, idx desc;

-- 나이가 30대만 출력(30~39) : between _ and _ ;
select * from test1 where age>=30 and age<40;
select * from test1 where age between 30 and 40;


-- 나이가 30대 남자만 출력(30~39)
select * from test1 where age>=30 and age<40 and gender = '남자';

-- 나이가 30대 남자만 이름 내림차순 출력(30~39)
select * from test1 where age>=30 and age<40 and gender = '남자' order by name desc;

-- 나이가 30대 이거나 50인 자료만 출력?
select * from test1 where age>=30 and age<=40 or age>=50 and age<60;

-- 서울, 청주에 사는 사람 출력
select * from test1 where address = '서울' or address = '청주';
select * from test1 where address in('서울', '청주');

-- 서울, 청주에 사는 사람 중에서 나이가 20대 이하 출력
select * from test1 where (address = '서울' or address = '청주') and age<=20;
select * from test1 where address in('서울', '청주') and age<=20;

-- 서울에 살거나 나이가 20대인 남자만 출력
select * from test1 where address = '서울' or age between 20 and 29 and gender = '남자';

-- '성'이 '가'씨만 출력(like 연산자 : %(복수 개의 와일드 카드), _(단수 개의 와일드 카드)
select * from test1 where name like '가%';

-- 주소의 마지막 글자가 '주'로 끝나는 자료 출력
select * from test1 where address like '%주';

-- 이름 중에서 '나'자를 포함한 자료 출력
select * from test1 where name like '%나%';

-- 이름 중에서 두번쨰 글자가 '나'자를 포함한 자료 출력
select * from test1 where name like '_나%';

-- 이름 중에서 두번쨰 글자가 '나'자를 포함한 자료의 성명/주소 출력
select name, address from test1 where name like '_나%';

-- 자료를 10개만 출력하시오(제한 : limit)
select * from test1 limit 10;

-- 인덱스 번호 4에서부터 5개까지?(인덱스 번호 1은 0이다.)
select * from test1 limit 4,5; 

-- 주소의 마지막 글자가 '주'로 끝나는 자료의 나이를 1살 씩 추가
update test1 set age = age + 1 where address like '%주';

-- 서울에 사는 남자
select * from test1 where address = '서울' and gender = '남자';

-- 서울에 사는 남자 삭제
delete from test1 where address = '서울' and gender = '남자';

-- 나이가 많은 사람 5명만 출력
select * from test1 order by age desc limit 5;hoiwonhoiwon