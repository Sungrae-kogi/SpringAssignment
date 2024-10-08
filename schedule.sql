create database schedules;

CREATE TABLE IF NOT EXISTS SCHEDULE
(
    Id int primary key AUTO_INCREMENT COMMENT '스케쥴ID',
    Title VARCHAR(20) not null COMMENT '할일 제목',
    Contents TINYTEXT not null COMMENT '할일 내용',
    Writer VARCHAR(20) not null COMMENT '담당자',
    Password VARCHAR(16) not null COMMENT '비밀번호',
    WriteDate DATETIME NOT NULL COMMENT '생성 날짜',
    UpdateDate DATETIME COMMENT '업데이트 날짜'
);