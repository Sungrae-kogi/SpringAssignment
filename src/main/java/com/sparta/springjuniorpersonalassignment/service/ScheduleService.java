package com.sparta.springjuniorpersonalassignment.service;

import com.sparta.springjuniorpersonalassignment.dto.ScheduleRequestDto;
import com.sparta.springjuniorpersonalassignment.dto.ScheduleResponseDto;
import com.sparta.springjuniorpersonalassignment.entity.Schedule;
import com.sparta.springjuniorpersonalassignment.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ScheduleService {
    //실질적인 데이터 저장 비즈니스로직을 실행해주는 부분.
    private final JdbcTemplate jdbcTemplate;

    public ScheduleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //CREATE
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);

        Schedule saveSchedule = scheduleRepository.create(schedule);

        //Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(saveSchedule);

        return scheduleResponseDto;
    }

    //READ
    public ScheduleResponseDto getSchedule(Long id) {
        //고유식별자 ID를 이용하여 단건 조회
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);

        //조회한 Schedule 객체를 Dto로 변환
        return new ScheduleResponseDto(scheduleRepository.findId(id));
    }

    //READ ALL
    public List<ScheduleResponseDto> getAllSchedules() {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);

        return scheduleRepository.findAll();
    }

    //READ ALL BY CONDITION
    public List<ScheduleResponseDto> getConditionedSchedules(String updateDate, String writer) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);

        return scheduleRepository.findAllByCondition(updateDate, writer);
    }
}
