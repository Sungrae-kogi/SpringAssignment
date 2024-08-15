package com.sparta.springjuniorpersonalassignment.service;

import com.sparta.springjuniorpersonalassignment.dto.ScheduleRequestDto;
import com.sparta.springjuniorpersonalassignment.dto.ScheduleResponseDto;
import com.sparta.springjuniorpersonalassignment.entity.Schedule;
import com.sparta.springjuniorpersonalassignment.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class ScheduleService {
    //실질적인 데이터 저장 비즈니스로직을 실행해주는 부분.
    private final JdbcTemplate jdbcTemplate;

    public ScheduleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);

        Schedule saveSchedule = scheduleRepository.save(schedule);

        //Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(saveSchedule);

        return scheduleResponseDto;
    }
}
