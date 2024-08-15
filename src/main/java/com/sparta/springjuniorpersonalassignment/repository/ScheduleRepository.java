package com.sparta.springjuniorpersonalassignment.repository;

import com.sparta.springjuniorpersonalassignment.dto.ScheduleResponseDto;
import com.sparta.springjuniorpersonalassignment.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //DB 저장
    public Schedule save(Schedule schedule) {
        //DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO schedule (title, contents, writer, password, writedate) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getTitle());
                    preparedStatement.setString(2, schedule.getContents());
                    preparedStatement.setString(3, schedule.getWriter());
                    preparedStatement.setString(4, schedule.getPassword());
                    preparedStatement.setString(5, schedule.getWritedate());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인   ->  이 부분이 뭔지 모름
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        return schedule;
    }

    //단일 정보 조회
    public Schedule findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getLong("id"));
                schedule.setTitle(resultSet.getString("title"));
                schedule.setContents(resultSet.getString("contents"));
                schedule.setWriter(resultSet.getString("writer"));
                schedule.setWritedate(resultSet.getString("writedate"));
                return schedule;
            } else {
                return null;
            }
        }, id);



    }

    //모든 정보 조회
    public List<ScheduleResponseDto> findAll() {
        // DB 조회
        String sql = "SELECT * FROM schedule";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
            Long id = rs.getLong("id");
            String title = rs.getString("title");
            String contents = rs.getString("contents");
            String writer = rs.getString("writer");
            String writedate = rs.getString("writedate");
            return new ScheduleResponseDto(id, title, contents, writer, writedate);
        });
    }

    //단일 정보 조회
    public Schedule findId(Long id) {
        // DB 에서 ID로 찾아내는 쿼리
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setTitle(resultSet.getString("title"));
                schedule.setContents(resultSet.getString("contents"));
                schedule.setWriter(resultSet.getString("writer"));
                schedule.setWritedate(resultSet.getString("writedate"));
                return schedule;
            } else {
                return null;
            }
        }, id);

    }

}
