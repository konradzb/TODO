package com.example.todo.dao;

import com.example.todo.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TaskDaoAccessService implements TaskDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskDaoAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertTask(UUID id, Task newTask) {
        final String sql = "INSERT INTO task VALUES " + id + ", " + newTask.getName() + ", "+ newTask.getText();

        jdbcTemplate.execute(sql);
        return 1;
    }

    @Override
    public List<Task> getAllTasks() {
        final String sql = "SELECT id, name, text FROM text";

        return jdbcTemplate.query(sql, ((resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String text = resultSet.getString("text");

            return new Task(id, name, text);
        }));
    }

    @Override
    public Optional<Task> selectTaskById(UUID id) {
        return Optional.empty();
    }

    @Override
    public int deleteTask(UUID id) {
        return 0;
    }

    @Override
    public Task getLastTask() {
        return null;
    }
}
