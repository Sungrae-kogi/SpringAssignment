package com.sparta.springjuniorpersonalassignment.controller;

import com.sparta.springjuniorpersonalassignment.dto.ScheduleRequestDto;
import com.sparta.springjuniorpersonalassignment.dto.ScheduleResponseDto;
import com.sparta.springjuniorpersonalassignment.service.ScheduleService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    // "/api"로 시작하는 요청을 dispatcherServlet으로부터 전달받은 Controller

    //https://velog.io/@dhktjr0204/JDBC-JdbcTemplate-%EC%82%AC%EC%9A%A9%EB%B2%95 참고해보기, 지금 시간이없어서 알고쓰는게아님.
    private final JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @PostMapping("/schedule")
    public ScheduleResponseDto schedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {

        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);

        return scheduleService.createSchedule(scheduleRequestDto);


    }
}
