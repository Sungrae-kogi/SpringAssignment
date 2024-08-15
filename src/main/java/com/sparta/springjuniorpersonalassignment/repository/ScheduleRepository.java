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
    public Schedule create(Schedule schedule) {
        //DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        //입력에서는 받지않았지만, Schedule 생성자에서 write값을 update값에 맞춰서 가져왔므로 여기서는 같이 저장해야함.
        String sql = "INSERT INTO schedule (title, contents, writer, password, writedate, updatedate) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, schedule.getTitle());
                    preparedStatement.setString(2, schedule.getContents());
                    preparedStatement.setString(3, schedule.getWriter());
                    preparedStatement.setString(4, schedule.getPassword());
                    preparedStatement.setString(5, schedule.getWritedate());
                    preparedStatement.setString(6, schedule.getUpdatedate());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인   ->  이 부분이 뭔지 모름
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        return schedule;
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
                schedule.setUpdatedate(resultSet.getString("updatedate"));
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
            String updatedate = rs.getString("updatedate");
            return new ScheduleResponseDto(id, title, contents, writer, writedate, updatedate);
        });
    }

    // 특정 조건을 만족하는 모든 정보 조회     -   날짜 형식 YYYY-MM-DD, 수정일 기준 내림차순 정렬
    public List<ScheduleResponseDto> findAllByCondition(String updateDate, String writer) {
        //조건이 충족되는 분기 처리
        //updateDate와 writer가 모두 있을때
        if (updateDate != null && writer != null) {
            String sql = "SELECT * FROM schedule WHERE DATE_FORMAT(updateDate, '%Y-%m-%d') = ? AND writer = ? ORDER BY updateDate DESC";

            return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
                @Override
                public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                    // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                    Long id = rs.getLong("id");
                    String title = rs.getString("title");
                    String contents = rs.getString("contents");
                    String writer = rs.getString("writer");
                    String writedate = rs.getString("writedate");
                    String updatedate = rs.getString("updatedate");
                    return new ScheduleResponseDto(id, title, contents, writer, writedate, updatedate);
                }
            }, updateDate, writer);
            //updateDate는 없고 writer만 있을 때
        } else if (updateDate == null && writer != null) {
            String sql = "SELECT * FROM schedule WHERE writer = ? ORDER BY updateDate DESC";

            return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
                @Override
                public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                    // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                    Long id = rs.getLong("id");
                    String title = rs.getString("title");
                    String contents = rs.getString("contents");
                    String writer = rs.getString("writer");
                    String writedate = rs.getString("writedate");
                    String updatedate = rs.getString("updatedate");
                    return new ScheduleResponseDto(id, title, contents, writer, writedate, updatedate);
                }
            }, writer);
            //updateDate가 있고 writer가 없을 때
        } else if (updateDate != null && writer == null) {
            String sql = "SELECT * FROM schedule WHERE DATE_FORMAT(updateDate, '%Y-%m-%d') = ? ORDER BY updateDate DESC";

            return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
                @Override
                public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                    // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                    Long id = rs.getLong("id");
                    String title = rs.getString("title");
                    String contents = rs.getString("contents");
                    String writer = rs.getString("writer");
                    String writedate = rs.getString("writedate");
                    String updatedate = rs.getString("updatedate");
                    return new ScheduleResponseDto(id, title, contents, writer, writedate, updatedate);
                }
            }, updateDate);
            //둘 다 없을때
        } else {
            String sql = "SELECT * FROM schedule ORDER BY updateDate DESC";

            return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
                @Override
                public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                    // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                    Long id = rs.getLong("id");
                    String title = rs.getString("title");
                    String contents = rs.getString("contents");
                    String writer = rs.getString("writer");
                    String writedate = rs.getString("writedate");
                    String updatedate = rs.getString("updatedate");
                    return new ScheduleResponseDto(id, title, contents, writer, writedate, updatedate);
                }
            });
        }
        
        //검색 종료
    }
}
