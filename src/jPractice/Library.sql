show tables;

/* 도서 테이블(books) */
CREATE TABLE books (
    bookID      INT AUTO_INCREMENT PRIMARY KEY, -- 도서 ID
    title       VARCHAR(50) NOT NULL,           -- 도서 제목
    author      VARCHAR(30),                    -- 작가
    publisher   VARCHAR(30),                    -- 출판사
    genre       VARCHAR(30),                    -- 장르
    isAvailable BOOLEAN DEFAULT true            -- 대출 가능 여부
);

desc books;

/* 도서 테이블(books) - TestData */
INSERT INTO books
VALUES (default, '이것이 자바다', '신용권', '한빛미디어', '컴퓨터 공학', true),
       (default,'전설로 떠나는 월가의 영웅', '피터 린치', '국일증권경제연구소', '경제 경영', true),
       (default,'인간관계론', '데일 카네기', '현대지성 ', '자기계발', false);
       
select * from books;

--drop table books;

/* 회원 테이블 */
CREATE TABLE members (
    memberID INT AUTO_INCREMENT PRIMARY KEY, -- 회원 ID
    name     VARCHAR(50) NOT NULL,           -- 회원 이름
    password VARCHAR(255) NOT NULL,          -- 회원 비밀번호
    contact  VARCHAR(15),                    -- 연락처
    address  VARCHAR(50)                     -- 주소
);

desc members;

/* 회원 테이블 - TestData */
INSERT INTO members
VALUES (default, 'ADMIN', '1234', '010-1111-1111', '서울'),
       (default, '강감찬', 'kang2222', '010-2222-2222', '대전'),
       (default, '이순신', 'lee3333', '010-3333-3333', '청주');

select * from members;

--drop table members;

/* 대출 기록 테이블 */
CREATE TABLE loans (
    loanID     INT AUTO_INCREMENT PRIMARY KEY,                             -- 대출 ID
    memberID   INT,                                                        -- 회원 ID
    bookID     INT,                                                        -- 도서 ID
    loanDate   DATE,                                                       -- 대출일
    returnDate DATE,                                                       -- 반납일
    FOREIGN KEY (memberID) REFERENCES members(memberID) ON DELETE CASCADE, -- 외래 키: 회원 ID
    FOREIGN KEY (bookID) REFERENCES books(bookID) ON DELETE CASCADE        -- 외래 키: 도서 ID
);

desc loans;

/* 대출 기록 테이블 - TestData */
INSERT INTO loans (memberID, bookID, loanDate, returnDate)
VALUES (3, 3, '2023-09-22', '2023-10-05');

select * from loans;

--drop table loans;
