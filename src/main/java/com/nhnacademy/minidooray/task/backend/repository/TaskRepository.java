package com.nhnacademy.minidooray.task.backend.repository;

import com.nhnacademy.minidooray.task.backend.domain.TaskDto;
import com.nhnacademy.minidooray.task.backend.entity.Task;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT new com.nhnacademy.minidooray.task.backend.domain.TaskDto(t.id, t.name, t.detail) from Task t inner join Project p ON t.project.id = p.id WHERE p.id = :projectId")
    List<TaskDto> taskListByProjectId(@Param("projectId") Long projectId);
    Optional<TaskDto> findTaskById(Long taskId);
    void deleteTaskById(Long taskId);
    Task getTaskById(Long taskId);
}