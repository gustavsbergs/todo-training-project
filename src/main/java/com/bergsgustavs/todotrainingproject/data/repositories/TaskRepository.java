package com.bergsgustavs.todotrainingproject.data.repositories;

import com.bergsgustavs.todotrainingproject.data.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
