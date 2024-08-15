package com.sparta.springjuniorpersonalassignment.entity;

import com.sparta.springjuniorpersonalassignment.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private long id;
    private String title;
    private String Contents;
    private String writer;
    private String password;
    private String writedate;
    private String updatedate;

    public Schedule(ScheduleRequestDto scheduleRequestDto) {
        this.title = scheduleRequestDto.getTitle();
        this.Contents = scheduleRequestDto.getContents();
        this.writer = scheduleRequestDto.getWriter();
        this.password = scheduleRequestDto.getPassword();
        this.writedate = scheduleRequestDto.getWritedate();
        //생성시에는 생성날짜와 업데이트날짜가 동일하게.
        this.updatedate = scheduleRequestDto.getWritedate();
    }
}
