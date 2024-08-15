package com.sparta.springjuniorpersonalassignment.controller;

import com.sparta.springjuniorpersonalassignment.dto.ScheduleRequestDto;
import com.sparta.springjuniorpersonalassignment.dto.ScheduleResponseDto;
import com.sparta.springjuniorpersonalassignment.service.ScheduleService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    // "/api"로 시작하는 요청을 dispatcherServlet으로부터 전달받은 Controller

    //https://velog.io/@dhktjr0204/JDBC-JdbcTemplate-%EC%82%AC%EC%9A%A9%EB%B2%95 참고해보기, 지금 시간이없어서 알고쓰는게아님.
    private final JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //일정 추가
    @PostMapping("/schedule")
    public ScheduleResponseDto schedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {

        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);

        return scheduleService.createSchedule(scheduleRequestDto);

    }

    //일정 조회
    @GetMapping("/schedule/{id}")
    public ScheduleResponseDto getSchedule(@PathVariable Long id) {
        // 객체간 이동 위해 ScheduleService 객체 생성
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);

        return scheduleService.getSchedule(id);

    }

    @GetMapping("/schedule/all")
    public List<ScheduleResponseDto> getAllSchedules() {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.getAllSchedules();
    }

    //일정 목록 조회  -    수정일, 담당자명 으로 일정 목록을 전부 조회가능
    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getSchedules(@RequestParam(required = false) String updateDate, @RequestParam(required = false) String writer){
        //조건 중 한 가지만, 두 가지다 충족x, 둘 모두 충족 할 수있다.
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);

        return scheduleService.getConditionedSchedules(updateDate,writer);
    }
}
