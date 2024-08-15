create database schedules;

CREATE TABLE IF NOT EXISTS SCHEDULE
(
    Id int primary key AUTO_INCREMENT COMMENT '스케쥴ID',
    Title VARCHAR(20) not null COMMENT '할일 제목',
    Contents TINYTEXT not null COMMENT '할일 내용',
    Writer VARCHAR(8) not null COMMENT '담당자',
    Password VARCHAR(16) not null COMMENT '비밀번호',
    WriteDate DATETIME not null COMMENT '생성 날짜'
);