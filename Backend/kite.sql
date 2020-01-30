create table members
(
    memberid  INTEGER primary key,
    email     varchar(20),
    pw        varchar(20),
    lastname  varchar(20),
    firstname varchar(20),
    birthday  varchar(6),
    gender    boolean,
    area      varchar(100),
    job       varchar(50),
    company   varchar(50),
    image     varchar(50)
);

create table membertoken
(
    memberid      INTEGER,
    refresh_token varchar(200),
    foreign key (memberid) references members (memberid)
);

create table article
(
    articleid       INTEGER AUTO_INCREMENT PRIMARY KEY,
    company         VARCHAR(255)  NOT NULL,
    title           VARCHAR(4000) NOT NULL,
    summary         VARCHAR(4000) NOT NULL,
    newspaper       VARCHAR(255)  NOT NULL,
    publicationDate DATE          NOT NULL,
    url             VARCHAR(255)  NOT NULL UNIQUE,
    image           VARCHAR(255)  NOT NULL
);

-- 기사 - 내용 테이블 기사ID, 기사내용
CREATE TABLE article_content
(
    articleid INT,
    content   VARCHAR(4000) NOT NULL,
    FOREIGN KEY (articleid)
        REFERENCES article (articleid)
        ON UPDATE CASCADE
);
​
-- 회원 - 기사 테이블 회원ID, 기사ID
CREATE TABLE memeber_article
(
    memberid  INT,
    articleid INT,
    keyword   VARCHAR(4000),
    FOREIGN KEY (memberid)
        REFERENCES members (memberid)
        ON UPDATE CASCADE,
    FOREIGN KEY (articleid)
        REFERENCES article (articleid)
        ON UPDATE CASCADE
);
​
-- 나이대, 성별 - 키워드 테이블
CREATE TABLE agengen_keword
(
    age     INT,
    gender  VARCHAR(4),
    keyword VARCHAR(4000) NOT NULL,
    PRIMARY KEY (age, gender)
);
​
-- 직업별 - 키워드 테이블
CREATE TABLE job_keyword
(
    job     VARCHAR(255),
    keyword VARCHAR(4000) NOT NULL,
    PRIMARY KEY (job)
);
​
-- 기사 - 키워드 테이블 기사ID, 키워드
CREATE TABLE article_keyword
(
    articleid INT,
    keyword   VARCHAR(4000) NOT NULL,
    FOREIGN KEY (articleid)
        REFERENCES article (articleid)
        ON UPDATE CASCADE
);
​
-- 팔로우 - 팔로잉 확인 테이블 회원ID, 회원ID
CREATE TABLE follow
(
    followid    INT,
    followingid INT,
    FOREIGN KEY (followid)
        REFERENCES members (memberid)
        ON UPDATE CASCADE,
    FOREIGN KEY (followingid)
        REFERENCES members (memberid)
        ON UPDATE CASCADE
);
​
-- 친구 리스트 테이블 회원ID, 친구목록
CREATE TABLE friend
(
    memberid   INT,
    friendlist VARCHAR(4000) NOT NULL,
    FOREIGN KEY (memberid)
        REFERENCES members (memberid)
        ON UPDATE CASCADE
);
