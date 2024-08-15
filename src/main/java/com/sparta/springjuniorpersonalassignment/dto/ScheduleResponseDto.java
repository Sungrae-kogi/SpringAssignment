package com.sparta.springjuniorpersonalassignment.dto;

import com.sparta.springjuniorpersonalassignment.entity.Schedule;
import lombok.Getter;


@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String writer;
    private String password;
    private String writedate;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.writer = schedule.getWriter();
        this.password = schedule.getPassword();
        this.writedate = schedule.getWritedate();
    }
}
