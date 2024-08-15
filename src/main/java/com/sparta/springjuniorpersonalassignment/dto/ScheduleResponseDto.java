package com.sparta.springjuniorpersonalassignment.dto;

import com.sparta.springjuniorpersonalassignment.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String writer;
    private String writedate;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.writer = schedule.getWriter();
        this.writedate = schedule.getWritedate();
    }

    public ScheduleResponseDto(Long id, String title, String contents, String writer, String writedate) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.writedate = writedate;
    }
}
