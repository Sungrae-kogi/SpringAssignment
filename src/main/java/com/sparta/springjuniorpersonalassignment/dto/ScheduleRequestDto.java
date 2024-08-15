package com.sparta.springjuniorpersonalassignment.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ScheduleRequestDto {
    private String title;
    private String contents;
    private String writer;
    private String password;
    private String writedate;
}
